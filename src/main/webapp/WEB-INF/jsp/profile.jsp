<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE HTML>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>Cubic Design Community</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link type="text/css" href="css/bootstrap.min.css" rel="stylesheet"/>
    <link type="text/css" href="css/styles.css" rel="stylesheet"/>
    <sec:authentication var="username" property="principal.username"/>
</head>
<body>
<jsp:include page="header.jsp"/>
<section>
    <div class="container center-text">
        <h2>My Profile</h2>
        <hr>
    </div>
    <div class="container">
        <div class="row">
            <div class="col-md-6">
                <div class="center-text">
                    <h3>Account Information</h3>
                </div>
                <hr>
                <div class="panel panel-default">
                    <div class="panel-heading">
                        Username: <span class="pull-right">${username}</span>
                    </div>
                    <div class="panel-body">
                        Email: <span class="pull-right">${email}</span>
                    </div>
                </div>
            </div>
            <div class="col-md-6">
                <div class="center-text">
                    <h3>Edit Account Information</h3>
                </div>
                <hr>
                <input type="hidden" id="username" value="${username}">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <h3 class="panel-title">Change Password</h3>
                    </div>
                    <div class="panel-body row center-text">
                        <div class="col-md-10 col-md-offset-1">
                            <input class="form-control" id="currentPassword" placeholder="Current Password" type="password" name="current-password"/>
                            <input class="form-control" id="password" placeholder="New Password" type="password"
                                   name="password"/>
                            <button class="btn btn-primary" id="changePassword" type="submit" value="change-password">
                                Change Password
                            </button>
                            <hr>
                            <input class="form-control" id="email" placeholder="New Email" type="text"
                                   name="email"/>
                            <button class="btn btn-primary" id="changeEmail" type="submit" value="change-email">
                                Change Email
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<script src="js/jquery.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/profile.js"></script>
</body>
</html>
