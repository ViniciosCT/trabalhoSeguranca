<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Lista de Veículos em Processo de Lavagem</title>
</head>
<body>

<h2>Lista de veículos: </h2>

<table>
    <tr>
        <th>Placa</th>
        <th>Cliente</th>
        <th>Data de Entrada</th>
        <th>Opções</th>
    </tr>
    <c:forEach items="${veiculos}" var="v">
        <tr>
            <td>${v.placa}</td>
            <td>${v.cliente.nome}</td>
            <td>${v.dataEntrada}</td>
            <td><a href="removeVeiculo.priv?&id=${v.id}"> Remover </a></td>
        </tr>
    </c:forEach>
    <br>
    <a href="paginaCriaVeiculo.priv">Criar novo veiculo</a>
</table>

</body>
</html>
