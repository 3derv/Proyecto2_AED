<%-- 
    Document   : paginarespuesta
    Created on : 25/10/2021, 11:23:49 PM
    Author     : Usuario
--%>


<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.io.FileWriter"%>
<%@page import="java.io.FileReader"%>
<%@page import="java.util.Date"%>
<%@page import="com.opencsv.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="clasearbol.*" %>
<!DOCTYPE html>
<html>
    <meta charset="UTF-8">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset="UTF-8">
        <title>Se calculó un resultado</title>
    </head>
    <br>
    <jsp:include page="index.jsp" ></jsp:include>
    <body>
        
    <% 
        ArbolDeExpresion arbole = new ArbolDeExpresion();
        double resultado = arbole.construirElArbol(request.getParameter("laoperacion"));
        String operacion = request.getParameter("laoperacion");
        String id = request.getRequestedSessionId();
        id = session.getId().toString();
        String sresultado = String.valueOf(resultado);
        String fecha = new Date().toString();
        
    %>
    <% out.println("<table border='1' >");
        
            out.println("<tr>");
            out.println("<td>");
            out.println("La operacion fue ");
            out.println("</td>");
            out.println("<td>");
            out.println(operacion);
            out.println("</td>");
            out.println("</tr>");
            out.println("<tr>");
            out.println("<td>");
            out.println("Su resultadoe es ");
            out.println("</td>");
            out.println("<td>");
            out.println(sresultado);
            out.println("</td>");
            out.println("</tr>");
            out.println("</table>" ); %>
   

    <% 
        
    
    String file = "Registros.csv";
    CSVReader csvReader = new CSVReader(new FileReader(file));
    List<String[]> todoslosregistros = csvReader.readAll();
    csvReader.close();
    
    String[] datos = {id,operacion,sresultado,fecha}; 
        // El carácter \r\n es el que hace que salte una linia en el fichero después de escribir 
    todoslosregistros.add(todoslosregistros.size(),datos);
    List<String[]> registrocliente= new ArrayList<>();
    CSVWriter writer = new CSVWriter(new FileWriter(file, true));
    writer.writeNext(datos);
    writer.close();
    
        for (String[] cell : todoslosregistros) {
            
            if (cell[0].toString().equals(id) && cell!=null){
                String[] celln = {cell[0].toString(),cell[1].toString(),cell[2].toString(),cell[3].toString()};
                registrocliente.add(celln);
                
            }
        
        }
        
        session.setAttribute("RegistroCliente", registrocliente);
CSVWriter writern = new CSVWriter(new FileWriter(file));

writern.writeAll(todoslosregistros);

writern.close();
    %> 
    <form name="realizarConsulta" action="paginaconsulta.jsp">
        <input type="submit" value="Realizar consulta" name="btnConsulta" />
    </form>
    
      
   
    </body>
</html>
