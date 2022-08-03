<%--
  Created by IntelliJ IDEA.
  User: anatolysalov
  Date: 29.07.22
  Time: 00:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>User</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">

</head>
<body>
<div class="container text-center">
    <div class="row">
        <div class="col">

        </div>
        <div class="col">
            <form action="http://127.0.0.1:8080/classwork33lokalization/addfile" method="post" enctype="multipart/form-data">
                <div class="mb-3">
                    <label class="form-label" for="customFile">File input</label>
                    <input type="file" class="form-control" id="customFile" name="file"  />
                    <button type="submit"  class="btn btn-success">Success</button>
                </div>
            </form>
        </div>
        <div class="col">

        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa" crossorigin="anonymous"></script>
</body>
</html>
