<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
        <h2>Log in to Cubic Design Community</h2>
        <hr>
    </div>
    <div class="container center-text">
        <form action="/login" method="post">
            <div class="row">
                <div class="col-md-4 col-md-offset-4">
                    <c:if test="${param.error}">
                        <p class="text-danger">Unable to log in using those credentials.</p>
                    </c:if>
                    <c:if test="${param.logout}">
                        <p class="text-danger">Logged out successfully.</p>
                    </c:if>
                    <c:if test="${param.registered}">
                        <p class="text-danger">Registration successful. Please log in.</p>
                    </c:if>
                    <input class="form-control" placeholder="Username" type="text" name="username"/>
                    <input class="form-control" placeholder="Password" type="password" name="password"/>
                    <button class="btn btn-primary" type="submit" value="Sign In">Log in</button>
                </div>
            </div>
        </form>
    </div>
</section>
<script src="js/jquery.js"></script>
<script src="js/bootstrap.min.js"></script>
</body>
</html>