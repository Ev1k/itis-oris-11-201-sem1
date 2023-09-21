<html lang="en">
<#include "base.ftl">
<#macro title>Users</#macro>
<#macro content>
    Hello,
    <br>
<#--    <#if users?has_content></#if>-->
    <#if users??>
        <#list users as u>
            ${u}
            <br>
        </#list>
    </#if>
</#macro>
</html>