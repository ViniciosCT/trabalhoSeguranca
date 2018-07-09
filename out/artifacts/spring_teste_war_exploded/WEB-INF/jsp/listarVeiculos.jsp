<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Lista de Veículos em Processo de Lavagem</title>
</head>
<body>

    <c:import url="menu.jsp" />
    <br>

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
                <td><c:out value="${v.placa}"/></td>
                <td><c:out value="${v.cliente.nome}"/></td>
                <td>${v.dataEntrada}</td>
                <td>
                    <a href="paginaEditaVeiculo.priv?&id=${v.id}"> Editar </a>
                    <a href="removeVeiculo.priv?&id=${v.id}"> Remover </a>
                </td>
            </tr>
        </c:forEach>
        <br>
        <a href="paginaCriaVeiculo.priv">Criar novo veiculo</a>
    </table>

</body>
</html>
