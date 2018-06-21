<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Lista de Funcionarios</title>
</head>
<body>

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
                    <td>${f.nome}</td>
                    <td>${f.login}</td>
                    <td>${f.veiculo.placa}</td>
                    <td><a href="removeFuncionario.priv?&id=${f.id}"> Remover </a></td>
                </tr>
            </c:forEach>
        <br>
        <a href="paginaCriaFuncionario.priv">Criar novo funcionário</a>
    </table>

</body>
</html>
