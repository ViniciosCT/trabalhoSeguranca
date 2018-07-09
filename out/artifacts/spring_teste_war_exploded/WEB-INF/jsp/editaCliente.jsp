<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Editar Cliente</title>
</head>
<body>

<h1>Editar novo Cliente: </h1>

<form action="criaCliente.priv" id="cadastro" method="post">
    <input type="hidden" value="<c:out value='${cliente.id}'/>" name="id">
    <input type="hidden" value="<c:out value='${tokenAlteraCliente}'/>" name="token">
</form>
<br>
<label form="cadastro" for="nome">Nome</label>
<input form="cadastro" type="text" id="nome" name="nome" value="<c:out value='${cliente.nome}'/>" required>

<label> Veiculos:
    <c:forEach items="${veiculosChecked}" var="vOK">
        <label form="cadastro" for="${vOK.id}">
            <input form="cadastro" name="idsVeiculos" value="${vOK.id}" type="checkbox" id="${vOK.id}" checked/>
            <span><c:out value="${vOK.placa}"/></span>
        </label>
    </c:forEach>
    <c:forEach items="${veiculos}" var="v">
        <label form="cadastro" for="${v.id}">
            <input form="cadastro" name="idsVeiculos" value="${v.id}" type="checkbox" id="${v.id}" />
            <span><c:out value="${v.placa}"/></span>
        </label>
    </c:forEach>
</label>
<br>
<br>
<button form="cadastro" type="submit">Alterar</button>

</body>
</html>
