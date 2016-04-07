<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<nav class="navbar navbar-default navbar-static-top">
    <div class="container">
        <div class="navbar-header">
            <a href="/" class="navbar-brand">Cubic Design Community</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <li><a href="/download">Download</a></li>
                <li><a href="/screenshots">Screenshots</a></li>
                <li><a href="/comments">Comments</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <sec:authorize access="isAuthenticated()">
                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                        <li><a href="/admin">Admin Panel</a></li>
                    </sec:authorize>
                    <li><a href="/profile">My Account</a></li>
                    <li><a href="/logout">Log out</a></li>
                </sec:authorize>
                <sec:authorize access="!isAuthenticated()">
                    <li><a href="/register">Register</a></li>
                    <li><a href="/login">Log in</a></li>
                </sec:authorize>
            </ul>
        </div>
    </div>
</nav>