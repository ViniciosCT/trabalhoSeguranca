<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Lista de Veiculos na loja</title>
</head>
<body>

    <c:import url="menu.jsp" />
    <br>

    <h2>Lista de Veiculos na loja: </h2>

    <table>
        <tr>
            <th>Funcionario</th>
            <th>Veiculo</th>
            <th>Cliente</th>
        </tr>
        <c:forEach items="${funcionarios}" var="f">
            <tr>
                <td><c:out value="${f.nome}"/></td>
                <td><c:out value="${f.veiculo.placa}"/></td>
                <td><c:out value="${f.veiculo.cliente.nome}"/></td>
            </tr>
        </c:forEach>
    </table>

</body>
</html>
