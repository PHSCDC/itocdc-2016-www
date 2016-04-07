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
        <h2>Screenshots</h2>
        <p>Here are some screenshots uploaded by our players. Feel free to upload your own to add to the collection!</p>
        <hr>
    </div>
    <sec:authorize access="isAuthenticated()">
        <div class="container center-text">
            <c:if test="${not empty message}"><p class="text-danger">${message}</p></c:if>
            <div class="row">
                <div class="col-md-4 col-md-offset-4">
                    <form action="/upload" method="POST" enctype="multipart/form-data">
                        <input class="upload-file" type="file" name="file"/>
                        <button type="submit" class="btn btn-primary">Upload</button>
                    </form>
                </div>
            </div>
        </div>
    </sec:authorize>
    <sec:authorize access="isAnonymous()">
        <div class="container center-text">
            <p><a href="/login">Login</a> to post your own screenshot!</p>
        </div>
    </sec:authorize>
    <br/>
    <div class="container">
        <p class="center-text">Below is a list of all uploaded images. Click on a thumbnail to see the full size
            image.</p>
        <div class="row">
            <c:forEach items="${images}" var="string" varStatus="index">
            <c:if test="${(index.count - 1) % 4 == 0 && index.count != 0}">
        </div>
        <div class="row">
            </c:if>
            <div class="col-md-3"><a href="/img/${string}"><img src="/img/${string}" class="img-thumbnail" width="300"
                                                                height="300"></a></div>
            </c:forEach>
        </div>
    </div>
</section>
<script src="js/jquery.js"></script>
<script src="js/bootstrap.min.js"></script>
</body>
</html>