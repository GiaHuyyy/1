<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Thêm điện thoại</title>
</head>
<body style="display: flex; flex-direction: column;, align-items: center; justify-content: center;">
	
		<h1 style="text-align: center;">Thêm điện thoại</h1>
		<div style="display: flex; gap: 10px; justify-content: center;">
    		<a href="DanhSachDienThoaiNCCServlet">Danh sách điện thoại</a>
    		<a href="DienThoaiForm">Thêm điện thoại</a>
    		<a href="QuanLyForm">Chức năng quản lý</a>
		</div>
         
         <form action="DienThoaiForm" method="post" style="margin: auto">
         <label>Tên điện thoại: </label> <br>
         <input type="text" name="ten"><br>
         <label>Năm sản xuất: </label> <br>
         <input type="text" name="nam"><br>
         <label>Cấu hình: </label> <br>
         <input type="text" name="cauhinh"><br>
         <label>Nhà cung cấp: </label> <br>
         <select name="nhacungcap">
         	<c:forEach var="ncc" items="${listNCC}">
         		<option value="${ncc.maNCC}">${ncc.tenNCC}</option>
         	</c:forEach>
         </select>
         <br>
         <label>Hình ảnh: </label> <br>
         <input type="file" name="hinhanh" accept="image/*"><br>
         <button type="submit">Thêm điện thoại</button>
         </form>
	
</body>
</html>