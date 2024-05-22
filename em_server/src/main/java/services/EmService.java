package services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import dao.emDAO;
import entities.Candidate;
import entities.Certificate;
import entities.Position;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

public class EmService implements emDAO {
	private EntityManager entityManager;

	public EmService(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	// A.Liệt kê danh sách các vị trí công việc khi biết tên vị trí (tìm tương đối)
	// và
	// mức lương khoảng từ,
	// kết quả sắp xếp theo tên vị trí công việc.
	@Override
	public List<Position> listPositions(String name, double salaryFrom, double salaryTo) {
		List<Position> positions = new ArrayList<Position>();
		try {
			TypedQuery<Position> query = entityManager.createQuery(
					"SELECT p FROM Position p WHERE p.name LIKE :name AND p.salary BETWEEN :salaryFrom AND :salaryTo ORDER BY p.name",
					Position.class);
			query.setParameter("name", "%" + name + "%");
			query.setParameter("salaryFrom", salaryFrom);
			query.setParameter("salaryTo", salaryTo);
			return positions = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return positions;
	}

	// B.Liệt kê danh sách các ứng viên và số công ty mà các ứng viên này từng làm.
	@Override
	public Map<Candidate, Long> listCadidatesByCompanies() {
		try {
			Query query = entityManager.createQuery(
					"SELECT c, COUNT(cc.id) FROM Candidate c JOIN Experience cc ON c.id = cc.candidate.id GROUP BY c.id");
			List<Object[]> results = query.getResultList();

			Map<Candidate, Long> map = new HashMap<>();
			for (Object[] result : results) {
				Candidate candidate = (Candidate) result[0];
				Long count = (Long) result[1];
				map.put(candidate, count);
			}
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// C.Tìm danh sách các ứng viên đã làm việc trên một vị trí công việc nào đó có
	// thời gian làm lâu nhất.
	public Map<Candidate, Position> listCandidatesWithLongestWorking() {
		try {
			Query query = entityManager.createQuery(
					"SELECT e.candidate, e.position, MAX(DATEDIFF(e.to_date, e.from_date)) FROM Experience e GROUP BY e.position.id");
			List<Object[]> results = query.getResultList();

			Map<Candidate, Position> map = new HashMap<>();
			for (Object[] result : results) {
				Candidate candidate = (Candidate) result[0];
				Position position = (Position) result[1];
				map.put(candidate, position);
			}
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// D. Thêm một ứng viên mới. Trong đó mã số ứng viên phải bắt đầu là C, theo sau
	// ít nhất là 3 ký số.
	public boolean addCandidate(Candidate candidate) {
		if (!candidate.getId().matches("^C\\d{3,}$")) {
			System.out.println("Mã số ứng viên phải bắt đầu là C, theo sau ít nhất là 3 ký số.");
			return false;
		}

		EntityTransaction trans = entityManager.getTransaction();
		try {
			trans.begin();
			entityManager.persist(candidate);
			trans.commit();
			return true;
		} catch (Exception e) {
			if (trans.isActive()) {
				trans.rollback();
			}
			e.printStackTrace();
			return false;
		}
	}

	// E. Tính số năm làm việc trên một vị trí công việc nào đó khi biết mã số ứng
	// viên.
	public Map<Position, Integer> listYearsOfExperienceByPosition(String candidate_id) {
		try {
			Query query = entityManager.createQuery(
					"SELECT e.position, SUM(DATEDIFF(e.to_date, e.from_date) / 365.0) FROM Experience e WHERE e.candidate.id = :candidate_id GROUP BY e.position.id");
			List<Object[]> results = query.setParameter("candidate_id", candidate_id).getResultList();

			Map<Position, Integer> map = new HashMap<>();
			for (Object[] result : results) {
				Position position = (Position) result[0];
				Double years = (Double) result[1];
				map.put(position, years.intValue());
			}
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// F.Liệt kê danh sách các ứng viên và danh sách bằng cấp của từng ứng viên.
	public Map<Candidate, Set<Certificate>> listCandidatesAndCertificates() {
		TypedQuery<Candidate> query = entityManager.createQuery("SELECT c FROM Candidate c JOIN FETCH c.certificate",
				Candidate.class);

		List<Candidate> candidates = query.getResultList();
		Map<Candidate, Set<Certificate>> result = new HashMap<>();

		for (Candidate candidate : candidates) {
			result.put(candidate, candidate.getCertificate());
		}

		return result;
	}
}
