<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE HTML>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>Cubic Design Community</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <link type="text/css" href="css/bootstrap.min.css" rel="stylesheet" />
    <link type="text/css" href="css/styles.css" rel="stylesheet" />
</head>
<body>
<jsp:include page="header.jsp"/>
<section>
    <div class="container center-text">
        <h2>Welcome to the Cubic Design Community website!</h2>
        <hr>
    </div>
    <div class="container">
        <div class="row">
            <sec:authorize access="isAuthenticated()">
                <sec:authentication property="principal.Username" var="username" />
                <div class="col-md-4">
                    <h2>Hello, ${username}</h2>
                    <p>Want to edit your account settings?  Visit the "My Profile" section of the website to get started!</p>
                    <p><a href="/profile" class="btn btn-default">My Profile</a></p>
                </div>
                <div class="col-md-4">
                    <h2>Logout</h2>
                    <p>Click here to log out from the Cubic Design Community website.  We hope to see you again soon!</p>
                    <p><a href="/logout" class="btn btn-default">Logout</a></p>
                </div>
            </sec:authorize>
            <sec:authorize access="isAnonymous()">
                <div class="col-md-4">
                    <h2>Login</h2>
                    <p>Click here to login to our website!  After logging in, you can upload in-game screenshots and leave comments.</p>
                    <p><a href="/login" class="btn btn-default">Log in</a></p>
                </div>
                <div class="col-md-4">
                    <h2>Register</h2>
                    <p>Click here to sign up for an account on the official Cubic Design Community website</p>
                    <p><a href="/register" class="btn btn-default">Register</a></p>
                </div>
            </sec:authorize>
            <div class="col-md-4">
                <h2>Screenshots</h2>
                <p>Check out some of the awesome screenshots uploaded by members of the community</p>
                <p><a href="/screenshots" class="btn btn-default">Screenshots</a></p>
            </div>
        </div>
    </div>
    <br>
    <br>
    <div class="container center-text">
        <img src="/simg/home.png">
    </div>
</section>
<script src="js/jquery.js"></script>
<script src="js/bootstrap.min.js"></script>
</body>
</html>