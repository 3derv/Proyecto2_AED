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
    
   
    
    <body>
        
        <% 
            //Se rescata desde los atributos de la sesión la lista de registro del cliente
            List<String[]> registrodelcliente  = (List<String[]>) session.getAttribute("RegistroCliente");
            //Se crea la tabla donde se mostrarán los datos del registro del cliente,´para ello se crearán las  cabeceras
            out.println("<table border='1' align='center' >");
                out.println("<th>Operacion</th>");
                out.println("<th>Resultado</th>");
                out.println("<th>Fecha</th>");
                //Se entra a un ciclo for recorriendo todos los datos de la lista de registro del cliente
                //Por cada elemento en la lista se creará una fila
                for (String[] regcl : registrodelcliente){
                    out.println("<tr>");
                    //Una vez que se entra a una fila, se recorre un for de 1 a 3 generando 3 casillas con los datos del elemento 
                    //de la lista que corresponde al indice índice
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
