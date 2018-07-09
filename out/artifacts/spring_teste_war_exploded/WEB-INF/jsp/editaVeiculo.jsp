<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edita Veiculo</title>
</head>
<body>

<h1>Edita Veiculo: </h1>

<form action="criaVeiculo.priv" id="cadastro" method="post">
    <input type="hidden" value="${veiculo.id}" name="id">
</form>
<br>
<label form="cadastro" for="placa">Placa</label>
<input form="cadastro" type="text" id="placa" name="placa" value="<c:out value='${veiculo.placa}'/>" required>

<p> Escolha um Cliente:  Cliente atual: <c:out value='${veiculo.cliente.nome}'/></p>
<select name="idCliente" form="cadastro">
    <option value="">Null</option>
    <c:forEach items="${clientes}" var="c">
        <option value="${c.id}"><c:out value="${c.nome}"/></option>
    </c:forEach>
</select>
<br>
<br>
<button form="cadastro" type="submit">Adicionar</button>

</body>
</html>
