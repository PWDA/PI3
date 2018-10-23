<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title>Pagina Inicial</title>
    </head>
    <body>
    <center>
        <h1>Loja de Informatica</h1>
        <h2>
            <a href="new">Add novo Produto</a>
            &nbsp;&nbsp;&nbsp;
            <a href="list">Lista de Produtos</a>

        </h2>
    </center>
    <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>Lista de produtos</h2></caption>
            <tr>
                <th>ID</th>
                <th>Nome</th>
                <th>Descricao</th>
                <th>Preco de venda</th>
                <th>Preco de compra</th>
                <th>Quantidade</th>
            </tr>
            <c:forEach var="product" items="${listProduct}">
                <tr>
                    <td><c:out value="${product.id}" /></td>
                    <td><c:out value="${product.name}" /></td>
                    <td><c:out value="${product.description}" /></td>
                    <td><c:out value="${product.purchasePrice}" /></td>
                    <td><c:out value="${product.salePrice}" /></td>
                    <td><c:out value="${product.amount}" /></td>

                    <td>
                        <a href="edit?id=<c:out value='${product.id}' />">Editar</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="delete?id=<c:out value='${product.id}' />">Deletar</a>                    	
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>	
</body>
</html>
