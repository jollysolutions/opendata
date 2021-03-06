<%-- 
    Document   : home
    Created on : Oct 21, 2014, 11:20:44 AM
    Author     : Peter T Mount
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="d" uri="http://uktra.in/tld/darwin" %>
<c:if test="${not empty nearBy}">
    <table class="wikitable" width="100%">
        <caption>Near By Stations within 3 miles</caption>
        <tr>
            <th>Station</th>
            <th>Dist<br/>Miles</th>
        </tr>
        <c:forEach var="stn" items="${nearBy}">
            <tr>
                <td>
                    <d:tiploc value="${stn.tiploc}" link="true" prefix="/station/" />
                </td>
                <td align="right"><fmt:formatNumber value="${stn.distance}" pattern="#0.0"/>m</td>
            </tr>
        </c:forEach>
    </table>
</c:if>
