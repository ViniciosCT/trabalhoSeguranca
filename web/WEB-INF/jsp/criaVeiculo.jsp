<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cria Veiculo</title>
</head>
<body>

    <h1>Cria novo Veiculo: </h1>

    <form action="criaVeiculo.priv" id="cadastro" method="post">
    </form>
    <br>
    <label form="cadastro" for="placa">Placa</label>
    <input form="cadastro" type="text" id="placa" name="placa" required>

    <p> Escolha um Cliente:</p>
    <select name="idCliente" form="cadastro">,
        <option value="">Null</option>
        <c:forEach items="${clientes}" var="c">
            <option value="${c.id}">${c.nome}</option>
        </c:forEach>
    </select>
    <br>
    <br>
    <button form="cadastro" type="submit">Adicionar</button>

</body>
</html>
