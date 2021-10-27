<%-- 
    Document   : paginaconsulta
    Created on : 26/10/2021, 04:59:56 AM
    Author     : Usuario
--%>

<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="clasearbol.*" %>
<!DOCTYPE html>
<html>
    <jsp:include page="index.jsp" ></jsp:include>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Resultado de la consulta</title>
    </head>
    
   <% out.print("La id de la sesion es " +session.getId().toString()); %>
    <body>
        <% List<String[]> registrodelcliente  = (List<String[]>) session.getAttribute("RegistroCliente");
        int tamanoregistro = registrodelcliente.size(); 
        
        out.println("<table border='1' align='center' >");
        out.println("<th>Operacion</th>");
        out.println("<th>Resultado</th>");
        out.println("<th>Fecha</th>");
        for (String[] regcl : registrodelcliente){
            out.println("<tr>");
            for (int c = 1; c<=3;c++){
                out.println("<td>");
                out.println(regcl[c]);
                out.println("</td>");
            }
            out.println("</tr>");
            }
out.print("</table>" ); %>
    </body>
</html>
