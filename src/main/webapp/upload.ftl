<html lang="en">
<#include "upload.ftl">

<#macro title>
    exception details
</#macro>

<#macro contect>
    <p>Upload files</p>
    <form action="upload" method="post">
        <input type="file" name="file">
        <br>
        <input type="submit" value="upload">
    </form>

</#macro>

</html>