<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="test.css">
<title> Student Registration LGN001 </title>
</head>
<body class="login-page-body"> 
  
    <div class="login-page">
      <div class="form">
        <div class="login">
          <div class="login-header">
            <h1>Welcome!</h1>
     
  
          <p>${error}</p>
          
          
          </div>
        </div>
        <form class="login-form" action="LoginController" method="post" >
          <input type="text" placeholder="UserMail( defult = admin@gmail.com" value="" name ="loginName"/>
          <input type="password" placeholder="Password( defult = 123)" value="" name="loginPassword"/>
          <button type="submit">login</button>
          <p class="message">Not registered? <a href="#">Create an account</a></p>
        </form>
      </div>
    </div>
</body>

</html>