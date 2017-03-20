<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Uploading Files Example with Spring Boot, Freemarker</title>
        <link href="css/bootstrap.min.css" rel="stylesheet" />
        <link href="css/jumbotron-narrow.css" rel="stylesheet" />
    </head>

    <body >
    <div class="container">
        <div class="jumbotron">
            <h1>File upload complete!</h1>
        </div>
        <div class="row marketing">
        <p></p>
            <div>Uploaded files:</div>
            <ul>
            <#list files as file>
            <li>
            ${file.getName()}
            </li>
            </#list>
            </ul>
        <p></p>
        <a class="btn btn-success" href=/upload>Add more files</a>
        </div>
    <div>
    </body>
</html>
