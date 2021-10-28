<%-- 
    Document   : index.jsp
    Created on : 27/10/2021, 04:41:32 PM
    Author     : Usuario
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <title>Calculadora</title>
        <meta charset="UTF-8">
        
    </head>
    <body>            
        <form action="paginarespuesta.jsp" method="get">
            <table>
                <tr>
                    <td><label>Introduzca una operacion</label></td>
                    <td><input type="text" name="laoperacion"></td>
                </tr>
                <tr>
                    <td><input type="submit" name="name" value="Enviar operacion" />  </td>  
                </tr>
            </table>
        </form>
        <% 
        session.setAttribute("SesionActual", session.getId().toString());
        %>
    </body>
</html>
