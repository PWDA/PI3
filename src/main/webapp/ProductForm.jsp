<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title>CRUD de Produtos</title>
    </head>
    <body>
    <center>
        <h1>Admin de produtos</h1>
        <h2>
            <a href="new">Add novo produto</a>
            &nbsp;&nbsp;&nbsp;
            <a href="list">Lista de Produtos</a>

        </h2>
    </center>
    <div align="center">
        <c:if test="${product != null}">
            <form action="update" method="post">
        </c:if>
            <c:if test="${product == null}">
                <form action="insert" method="post">
                </c:if>
                <table border="1" cellpadding="5">
                    <caption>
                        <h2>
                            <c:if test="${product != null}">
                                Editar produto
                            </c:if>
                            <c:if test="${product == null}">
                                Add novo produto
                            </c:if>
                        </h2>
                    </caption>
                    <c:if test="${product != null}">
                        <input type="hidden" name="id" value="<c:out value='${product.id}' />" />
                    </c:if>            
                    <tr>
                        <th>Nome: </th>
                        <td>
                            <input type="text" name="name" size="45"
                                   value="<c:out value='${product.name}' />"
                                   />
                        </td>
                    </tr>
                    <tr>
                        <th>Descricao:</th>
                        <td>
                            <input type="text" name="description" size="45"
                                   value="<c:out value='${product.description}' />"
                                   />
                        </td>
                    </tr>
                    <tr>
                        <th>Preco de Compra:</th>
                        <td>
                            <input type="text" name="purchasePrice" size="5"
                                   value="<c:out value='${product.purchasePrice}' />"
                                   />
                        </td>
                    </tr>
                    <tr>
                        <th>Preco de venda:</th>
                        <td>
                            <input type="text" name="salePrice" size="5"
                                   value="<c:out value='${product.salePrice}' />"
                                   />
                        </td>
                    </tr>
                    <tr>
                        <th>Quantidade</th>
                        <td>
                            <input type="text" name="amount" size="5"
                                   value="<c:out value='${product.amount}' />"
                                   />
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" align="center">
                            <input type="submit" value="Save" />
                        </td>
                    </tr>
                </table>
            </form>
    </div>	
</body>
</html>
