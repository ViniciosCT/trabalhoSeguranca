<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Login</title>
  </head>
  <body>

  <h1>Efetuar login</h1>

  <form action="login.html" id="login" method="post">
  </form>
    <label for="username">Usuário</label>
    <input form="login" type="text" id="username" name="login" />

    <label for="userpass">Senha</label>
    <input form="login" type="password" id="userpass" name="senha" />

    <button form="login" type="submit">Entrar</button>
    <br>
    <c:if test="${not empty msgDoServidor}">
    <p>Dados incorretos! </p>
    </c:if>
  <br>
  <a href="paginaListaVeículos.html" >Lista de Veiculos</a>

  </body>
</html>
