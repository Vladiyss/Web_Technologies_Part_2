<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
    <head>
        <title>Faculties</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <style><%@include file="/WEB-INF/lib/styles/main.css"%></style>
        <style><%@include file="/WEB-INF/lib/styles/header.css"%></style>
    </head>
    <fmt:setLocale value='<%=request.getSession().getAttribute("lang")%>'/>
    <fmt:setBundle basename="lang" var="loc"/>
    <fmt:message bundle="${loc}" key="lang.label.enr_plan" var="enr_plan"/>
    <fmt:message bundle="${loc}" key="lang.label.singout" var="out"/>
    <fmt:message bundle="${loc}" key="lang.label.faculty" var="fac"/>
    <body>
        <div class="_header">
            <div class="_logo">JAVA_WT_2020</div>
            <div class="_nav">
                <nav>
                    <a class="_nav_link" href="faculties">${fac}</a>
                    <a class="_nav_link" href="login">${out}</a>
                </nav>
            </div>
        </div>
        <div class="courses_table">
            <tabel class="courses_l">
                    <c:forEach var="fac" items="${faculties}">
                        <h1><c:out value="${fac.getName()}"/></h1>
                        <tr class="course">
                            <c:forEach var="course" items="${fac.getCourses()}">
                                <th>
                                    <h3>${enr_plan}: ${course.getFeePlacesAmount() + course.getBudgetPlacesAmount()}</h3>
                                    <form method="post">
                                        <input type="hidden" name="link_button" value="${course.getName()}" />
                                        <input type="submit" value="${course.getName()}"/>
                                    </form>
                                </th>
                            </c:forEach>
                        </tr>
                    </c:forEach>
            </tabel>
            <h1>${error_message}</h1>
        </div>
    </body>
</html>