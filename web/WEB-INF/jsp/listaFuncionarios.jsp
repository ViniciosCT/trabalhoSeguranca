<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Lista de Funcionarios</title>
</head>
<body>

    <c:import url="menu.jsp" />
    <br>

    <h2>Lista de funcionarios: </h2>

    <table>
        <tr>
            <th>Nome</th>
            <th>Login</th>
            <th>Veículo</th>
            <th>Opções</th>
        </tr>
            <c:forEach items="${funcionarios}" var="f">
                <tr>
                    <td> <c:out value="${f.nome}"/> </td>
                    <td> <c:out value="${f.login}"/> </td>
                    <td> <c:out value="${f.veiculo.placa}"/> </td>
                    <td>
                        <a href="paginaEditaFuncionario.priv?&id=${f.id}"> Editar </a>
                        <a href="removeFuncionario.priv?&id=${f.id}"> Remover </a>
                    </td>
                </tr>
            </c:forEach>
        <br>
        <a href="paginaCriaFuncionario.priv">Criar novo funcionário</a>
    </table>

</body>
</html>
