<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Chức năng quản lý</title>
</head>
<body style="display: flex; flex-direction: column; align-items: center; justify-content: center;">
	
		<h1 style="text-align: center;">Chức năng quản lý</h1>
		<div style="display: flex; gap: 10px; justify-content: center;">
    		<a href="DanhSachDienThoaiNCCServlet">Danh sách điện thoại</a>
    		<a href="DienThoaiForm">Thêm điện thoại</a>
    		<a href="QuanLyForm">Chức năng quản lý</a>
		</div>

		
		<form method="post" action="${pageContext.request.contextPath}/QuanLyForm" onsubmit="return confirm('Are you sure?')">
			<table border="1">
					<thead>
						<tr>
							<th>Mã điện thoại</th>
							<th>Tên điện thoại</th>
							<th>Năm sản xuất</th>
							<th>Cấu hình</th>
							<th>Nhà cung cấp</th>
							<th>Hình ảnh</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="dt" items="${listDT}">
							<tr>
								<td>${dt.maDT}</td>
								<td>${dt.tenDT}</td>
								<td>${dt.namSanXuat}</td>
								<td>${dt.cauHinh}</td>
								<td>${dt.nhaCungCap.tenNCC}</td>
								<td> <img alt="hinhanh" src="${dt.hinhAnh}"></td>
								<td> <button type="submit" name="action" value="delete_${dt.maDT}">Xóa</button> </td>
								<td> <button type="submit" name="action" value="update_${dt.maDT}">CapNhat</button> </td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
		</form>
	
</body>
</html>