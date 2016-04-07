<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>Cubic Design Community</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link type="text/css" href="css/bootstrap.min.css" rel="stylesheet"/>
    <link type="text/css" href="css/styles.css" rel="stylesheet"/>
</head>
<body>
<jsp:include page="header.jsp"/>
<section>
    <div class="container center-text">
        <h2>Sign up for a Cubic Design Community account</h2>
        <hr>
    </div>
    <div class="container center-text">
        <div class="row">
            <div class="col-md-4 col-md-offset-4">
                <p id="registerFail" class="text-danger hidden-message">Error - User already exists, please choose a different name.</p>
                <input class="form-control" placeholder="Username" type="text" name="username" id="username"/>
                <input class="form-control" placeholder="Password" type="password" name="password" id="password"/>
                <input class="form-control" placeholder="E-mail" type="text" name="email" id="email"/>
                <button class="btn btn-primary" id="registerButton" value="register">Register</button>
            </div>
        </div>
    </div>
</section>
<script src="js/jquery.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/register.js"></script>
</body>
</html>