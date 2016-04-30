<%--suppress ALL --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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
        <h2>Admin Control Panel</h2>
        <hr>
    </div>
    <div class="container">
        <div class="row">
            <div class="col-md-6">
                <div class="center-text">
                    <h3>User List</h3>
                </div>
                <hr>
                <ul>
                    <c:forEach items="${users}" var="user">
                        <li>${user.getUsername()}</li>
                    </c:forEach>
                </ul>
            </div>
            <div class="col-md-6">
                <div class="center-text">
                    <h3>Edit User Accounts</h3>
                </div>
                <hr>
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <h3 class="panel-title">Change a User's Password</h3>
                    </div>
                    <div class="panel-body row center-text">
                        <div class="col-md-10 col-md-offset-1">
                            <input type="text" class="form-control" id="pass-username" placeholder="Username">
                            <input class="form-control" id="password" placeholder="New Password" type="password"
                                   name="password"/>
                            <button class="btn btn-primary" id="changePassword" type="submit" value="change-password">
                                Change Password
                            </button>
                        </div>
                    </div>
                </div>
                <br>
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <h3 class="panel-title">Change a User's Email</h3>
                    </div>
                    <div class="panel-body row center-text">
                        <div class="col-md-10 col-md-offset-1">
                            <input type="text" class="form-control" id="email-username" placeholder="Username">
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
        <br>
        <div class="row last-row">
            <div class="col-md-6">
                <div class="center-text">
                    <h3>Change Game Download</h3>
                    <hr>
                    <p>Enter the path to the new game file</p>
                    <input type="text" class="form-control" placeholder="New file" id="new-download-link">
                    <button class="btn btn-primary" id="change-download">Change Download Link</button>
                </div>
            </div>
            <div class="col-md-6">
                <div class="center-text">
                    <h3>Approve Uploaded Images</h3>
                    <hr>
                    <p>Below is a list of unapproved images. Click on the image name to view the image.</p>
                </div>
                <br>
                <c:forEach items="${images}" var="image" varStatus="index">
                    <p><a href="/img/${image}">${image}</a><span class="pull-right"><a href="/approve_image?image=${image}" id="approve">Approve</a> : <a href="/reject_image?image=${image}" id="reject">Reject</a></span></p>
                </c:forEach>
            </div>
        </div>
    </div>
</section>
<script src="js/jquery.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/admin.js"></script>
</body>
</html>
