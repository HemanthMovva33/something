<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
	
	<style>
  	.navbar{
  	 min-height: 80px;
  	}
  	.footer{
position:fixed;
left:0;
bottom:0;
width:100%;
text-align:center;
}
  	</style>
	
</head>
<body>

	<nav class="navbar navbar-default" style="background-color: #0000CD;">
		<div class="container-fluid">
    	<div class="py-5">
     		<a class="navbar-brand" style="color: #FDF5E6" href="viewAllOffers">
     		<span><img src="data:image/png;base64,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" alt="" width="50" height="45" class="d-inline-block align-text-top">
     		Corporate Classifieds</span></a>
    	</div>
  		</div>
	</nav>


	<div id="form" class="col-lg-offset-4">
		<div class="transbox rounded-3">
			<div id="login">
				<div class="container">
					<div id="login-row"
						class="row justify-content-center align-items-center">
						<div id="login-column" class="col-md-6">
							<div id="login-box" class="col-md-12">
								<form id="login-form" class="form" action="/login" method="post">
									<h3 class="text-center text-primary">Login</h3>
									<div class="form-group">
										<label for="username" class="text-white">Username:</label><br>
										<input type="text" name="username" id="username"
											class="form-control" required>
									</div>
									<div class="form-group">
										<label for="password" class="text-white">Password:</label><br>
										<input type="password" name="password" id="password"
											class="form-control" required>
									</div>
									<div class="form-group">
										 <input type="submit"
											name="submit" class="btn btn-success btn-md" value="submit">
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- login form 
	<div class="container"> 
	<h3 style="color:blue;">Login</h3>
	<form name="Myform" onsubmit="return validateForm()" method="POST">
	<div class="form-group">
      <label for="username">Username</label>
      <input type="text" class="form-control" id="username" placeholder="Enter your name" name="username">
    </div>
    <div class="form-group">
      <label for="pwd">Password:</label>
      <input type="password" class="form-control" id="pwd" placeholder="Enter password" name="pwd">
    </div>
    <button type="submit" class="btn btn-default">Login</button>
	</form>
	</div>-->
	<!-- Footer Section-->
	<div class="footer">
		 <div style="background-color: rgba(0, 0, 0, 0.2);height: 50px;">
    		© 2020 Copyright:
  		</div>
  		</div> 
</body>
</html>
