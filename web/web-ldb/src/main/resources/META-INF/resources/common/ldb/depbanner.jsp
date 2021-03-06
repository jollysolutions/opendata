<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" uri="http://uktra.in/tld/opendata" %>
<%@ taglib prefix="d" uri="http://uktra.in/tld/darwin" %>
<c:choose>
    <c:when test="${empty location and empty train}">
        <%--
                <div id="logo">
                    <a href="/">
                        <img src="/images/375-logo.png"/>
                    </a>
                </div>
        --%>
        <a class="ldbbutton" href="/">Choose a Station</a>
    </c:when>
    <c:when test="${not empty backTo}">
        <a class="ldbbutton" onclick="document.location = '/mldb/${backTo.crs}'">Back to ${backTo.location}</a>
    </c:when>
    <c:otherwise>
        <a class="ldbbutton" href="/">Choose Another Station</a>
    </c:otherwise>
</c:choose>
<a class="ldbbutton" href="/about">About</a>
<a class="ldbbutton" href="/contact">Contact Us</a>
