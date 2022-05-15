<%@page import="model.Customer"%>


<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Customer details</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>

<script src="Components/user.js"></script>
</head>
<body>
	<nav class="navbar navbar-dark bg-dark">

		<a class="navbar-brand" href="#">ElectroGrid System</a>
		<form class="form-inline"></form>
		<a href="Home.jsp"><input id="btnHome" name="btnHome"
			type="button" value="Home" class="btn btn-primary btn-lg"></a>





	</nav>
	<br>
	<div class="container">
		<div class="row justify-content-center">
			<div class="col-7">

				<h2>Add here</h2>
				<form id="formItem" name="formItem">

					Customer ID: <input id="CustomerId" name="CustomerId" type="text"
						class="form-control form-control-sm"><br> Customer
					Name: <input id="customer_name" name="customer_name" type="text"
						class="form-control form-control-sm"><br> Address : <input
						id="address" name="address" type="text"
						class="form-control form-control-sm"><br> Mobile No:
					<input id="telephone_no" name="telephone_no" type="text"
						class="form-control form-control-sm"><br> <br> <input
						id="btnSave" name="btnSave" type="button" value="save"
						class="btn btn-primary btn-lg btn-block"> <input
						type="hidden" id="hidUserIDSave" name="hidUserIDSave" value="">

				</form>


				<div id="alertSuccess" class="alert alert-success"></div>
				<div id="alertError" class="alert alert-danger"></div>

				<br> <br>


			</div>
		</div>
	</div>
</body>
</html>