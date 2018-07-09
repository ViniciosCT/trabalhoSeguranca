<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cria Cliente</title>
</head>
<body>

    <h1>Criar novo Cliente: </h1>

    <form action="criaCliente.priv" id="cadastro" method="post">
    </form>
    <br>
    <label form="cadastro" for="nome">Nome</label>
    <input form="cadastro" type="text" id="nome" name="nome" required>

    <label> Veiculos:
        <c:forEach items="${veiculos}" var="v">
            <label form="cadastro" for="${v.id}">
                <input form="cadastro" name="idsVeiculos" value="${v.id}" type="checkbox" id="${v.id}" />
                <span><c:out value="${v.placa}"/></span>
            </label>
        </c:forEach>
    </label>
    <br>
    <br>
    <button form="cadastro" type="submit">Adicionar</button>

</body>
</html>
