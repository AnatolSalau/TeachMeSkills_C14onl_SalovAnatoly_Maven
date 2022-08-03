<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 03.08.2022
  Time: 11:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Localization</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
    <%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
</head>
<body>
<div class="container text-center">
    <div class="row">
        <div class="col">
        </div>
        <div class="col">
            <div class="card" style="width: 18rem;">
                <img src="..." class="card-img-top" alt="...">
                <div class="card-body">
                    <p class="card-text">
                        <s:message code="label.button"/>
                    </p>
                </div>
            </div>
        </div>

            <div class="d-grid gap-2 d-md-block">
                <form  action="http://127.0.0.1:8080/classwork33lokalization/localization" method="get">
                <button class="btn btn-primary" type="submit" name="lang" value="ru">RU</button>
                </form>
                <form  action="http://127.0.0.1:8080/classwork33lokalization/localization" method="get">
                <button class="btn btn-primary" type="submit" name="lang" value="en">EN</button>
                </form>
            </div>
        <div class="col">

        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa" crossorigin="anonymous"></script>
</body>
</html>
