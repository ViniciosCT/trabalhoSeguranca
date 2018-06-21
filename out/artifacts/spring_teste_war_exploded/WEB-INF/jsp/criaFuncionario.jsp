<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cria Funcionario</title>
</head>
<body>

    <h1>Criar novo Funcionario: </h1>

    <form action="criaFuncionario.priv" id="cadastro" method="post">
    </form>
    <br>
    <label form="cadastro" for="nome">Nome</label>
    <input form="cadastro" type="text" id="nome" name="nome" required>
    <br>
    <label form="cadastro" for="login">Login</label>
    <input form="cadastro" type="text" id="login" name="login" required>
    <br>
    <label form="cadastro" for="senha">Senha</label>
    <input form="cadastro" type="password" id="senha" name="senhaStr" required>
    <br>
    <p> Escolha um veíulo:</p>
    <select name="idVeiculo" form="cadastro">
        <c:forEach items="${veiculos}" var="v">
            <option value="${v.id}">${v.placa}</option>
        </c:forEach>
    </select>
    <br>
    <button form="cadastro" type="submit">Adicionar</button>
</body>
</html>
