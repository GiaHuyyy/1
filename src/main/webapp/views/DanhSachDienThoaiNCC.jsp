
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Danh sách điện thoại</title>
</head>
<body style="display: flex; flex-direction: column; align-items: center; justify-content: center;">
		<h1 style="text-align: center;">Danh sách điện thoại</h1>
		<div style="display: flex; gap: 10px; justify-content: center;">
    		<a href="DanhSachDienThoaiNCCServlet">Danh sách điện thoại</a>
    		<a href="DienThoaiForm">Thêm điện thoại</a>
    		<a href="QuanLyForm">Chức năng quản lý</a>
		</div>
		<form method="get" action="" style="display: flex; align-items: center; height: 22px;">
			<input type="text" name="search" style="width: 100%; ">
			<button type="submit" style="height: 100%;">Tìm</button>
		</form>
		<table border="1">
					<thead>
						<tr>
							<th>Mã nhà cung cấp</th>
							<th>Tên nhà cung cấp</th>
							<th>Địa chỉ</th>
							<th>Số điện thoại</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="ncc" items="${listNCC}">
							<tr>
								<td>${ncc.maNCC}</td>
								<td>${ncc.tenNCC}</td>
								<td>${ncc.diaChi}</td>
								<td>${ncc.soDienThoai}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				
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
							</tr>
						</c:forEach>
					</tbody>
				</table>
</body>
</html>
