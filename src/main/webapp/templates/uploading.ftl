<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Uploading Files Example with Spring Boot, Freemarker</title>
        <link href="css/bootstrap.min.css" rel="stylesheet" />
        <link href="css/jumbotron-narrow.css" rel="stylesheet" />
    </head>

    <body onload="updateSize();">
    <div class="container">
        <div class="jumbotron">
            <h1>File Upload</h1>
        </div>

        <form name="uploadingForm" enctype="multipart/form-data" action="/upload" method="POST">
            <p>
                <input class="file" id="fileInput" type="file" name="uploadingFiles" onchange="updateSize();" multiple>
                selected files: <span id="fileNum">0</span>;
                total size: <span id="fileSize">0</span>
            </p>
            <p>
                <input class="btn btn-success" type="submit" value="Upload files">
            </p>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
        </form>
        <div class="row marketing">
            <div>Uploaded files:</div>
            <ul>
            <#list files as file>
            <li>
            ${file.getName()}
            </li>
            </#list>
            </ul>
        </div>
        <script>
            function updateSize() {
                var nBytes = 0,
                        oFiles = document.getElementById("fileInput").files,
                        nFiles = oFiles.length;
                for (var nFileId = 0; nFileId < nFiles; nFileId++) {
                    nBytes += oFiles[nFileId].size;
                }

                var sOutput = nBytes + " bytes";
                // optional code for multiples approximation
                for (var aMultiples = ["KiB", "MiB", "GiB", "TiB", "PiB", "EiB", "ZiB", "YiB"], nMultiple = 0, nApprox = nBytes / 1024; nApprox > 1; nApprox /= 1024, nMultiple++) {
                    sOutput = nApprox.toFixed(3) + " " + aMultiples[nMultiple] + " (" + nBytes + " bytes)";
                }
                // end of optional code

                document.getElementById("fileNum").innerHTML = nFiles;
                document.getElementById("fileSize").innerHTML = sOutput;
            }
        </script>
    </div>
    </body>
</html>
