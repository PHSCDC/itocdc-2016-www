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
        <h2>Comments</h2>
        <p>Tell us how you really feel</p>
        <hr>
    </div>
    <div class="container">
        <div class="row">
            <div class="col-md-8 col-md-offset-2">
                <c:forEach items="${comments}" var="comment">
                    <div class="panel panel-default">
                        <div class="panel-body">
                                ${comment.getComment()}
                        </div>
                        <div class="panel-footer">
                            Posted by ${comment.getAuthor()}
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
        <sec:authorize access="isAuthenticated()">
            <sec:authentication var="username" property="principal.username"/>
            <div class="row">
                <div class="col-md-6 col-md-offset-3">
                    <textarea name="comment" id="comment" rows="4" class="form-control"></textarea>
                    <input type="hidden" id="username" value="${username}">
                </div>
            </div>
            <div class="center-text">
                <button id="post-comment-button" class="btn btn-primary">Post Comment</button>
            </div>
        </sec:authorize>
        <sec:authorize access="isAnonymous()">
            <p class="center-text"><a href="/login">Log in</a> to post your own comment.</p>
        </sec:authorize>
    </div>
</section>
<script src="js/jquery.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/comments.js"></script>
</body>
</html>