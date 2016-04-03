<%@ include file="/WEB-INF/jspf/page.jspf" %>
<%@ include file="/WEB-INF/jspf/taglib.jspf" %>

<html>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<body>

<div id="wrapper">
    <section>
        <div>
            <div class="name">Name:</div>
            <div class="password">Password:</div>
        </div>
        <form action="controller" method="post">
            <input type="hidden" name="command" value="login">
            <div class="inputs">
                <input name="login" type="text" id="login"/>
                <input name="password" type="password" id="password"/>
            </div>
            <div><input type="submit" value="Log In" class="button"/></div>

        </form>

    </section>

</div>
</body>
</html>