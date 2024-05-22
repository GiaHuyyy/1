package dao;

import java.util.List;
import java.util.Map;

import entities.Candidate;
import entities.Position;

public interface emDAO {

	List<Position> listPositions(String name, double salaryFrom, double salaryTo);

	Map<Candidate, Long> listCadidatesByCompanies();

}
