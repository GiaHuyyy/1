
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
         
        <%
        //DienThoai dt = (DienThoai) request.getAttribute("dienthoai");
    	%>
    	<!--  
         <form action="DienThoaiFormUpdate" method="post" style="margin: auto">
         <label>Mã điện thoại: </label> <br>
         <input type="text" name="ma" value="<%= dt.getMaDT() %>" readonly="readonly"><br>
         <label>Tên điện thoại: </label> <br>
         <input type="text" name="ten" value="<%=dt.getTenDT()%>"><br>
         <label>Năm sản xuất: </label> <br>
         <input type="text" name="nam" value="<%=dt.getNamSanXuat()%>"><br>
         <label>Cấu hình: </label> <br>
         <input type="text" name="cauhinh" value="<%=dt.getCauHinh()%>"><br>
         <label>Nhà cung cấp: </label> <br>
         <input type="text" name="nhacungcap" value="<%=dt.getNhaCungCap().getMaNCC()%>"><br>
         <label>Hình ảnh: </label> <br>
         <input type="text" name="hinhanh" accept="image/*" value="<%=dt.getHinhAnh()%>" readonly="readonly"><br>
         <button type="submit">Cập nhật</button>
         </form>
		-->
</body>
</html>