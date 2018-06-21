<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Lista de Clientes</title>
</head>
<body>

<h2>Lista de Clientes: </h2>

<table>
    <tr>
        <th>Nome</th>
        <th>Veiculos</th>
        <th>Opções</th>
    </tr>
    <c:forEach items="${clientes}" var="c">
        <tr>
            <td>${c.nome}</td>
            <td>
                <ul>
                    <c:forEach items="${c.veiculos}" var="v">
                        <li>${v.placa}</li>
                    </c:forEach>
                </ul>
            </td>
            <td><a href="removeCliente.priv?&id=${c.id}"> Remover </a></td>
        </tr>
    </c:forEach>
    <br>
    <a href="paginaCriaCliente.priv">Criar novo cliente</a>
</table>

</body>
</html>
