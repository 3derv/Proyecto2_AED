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
    <!-- Se incluye al principio la página de inicio -->
    <jsp:include page="index.jsp" ></jsp:include>
    <body>
        
    <% 
        //Se instancia un objeto de la clase ArvolDeExpresion(), se obtiene el parámetro de la solicitud el cual es la operacion 
        //ingresada en forma de texto, este se tomará y operará dicha expresion
        ArbolDeExpresion arbole = new ArbolDeExpresion();
        double resultado = arbole.construirElArbol(request.getParameter("laoperacion"));
        //Se crean las variables que se usarán para el archivo csv
        String operacion = request.getParameter("laoperacion");
        String id = request.getRequestedSessionId();
        String sresultado = String.valueOf(resultado);
        String fecha = new Date().toString();
        
    %>
    <% 
        //Se crea una tabla con los espacios para mostrar la operacion y su resultado 
        out.println("<table border='1' >");
        
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
            
        out.println("</table>" ); 
    %>
   
    <% 
        
        //Se instancia el archivo que de REgistros donde se almacenarán los registros de los clientes
        String file = "D:/Usuario/Documents/NetBeansProjects/Proyecto2_AED/src/main/java/clasearbol/Registros.csv";
        //Se crea una cadena de texto con los datos de la id del cliente, la operacion, resultado y fecha de la cpnsulta
        String[] datos = {id,operacion,sresultado,fecha};
        //Se crea una lista de Strings donde se guardarán los registros del que coincidan con el id del cliente
        List<String[]> registrocliente= new ArrayList<>();
        
        //Se crea un writer del archivo, se le pasa el directorio y la variable booleana true para que el archivo 
        //conserve sus datos, y se le agrega al final la cadena de texto almacenada en datos
        CSVWriter writer = new CSVWriter(new FileWriter(file, true));
        writer.writeNext(datos);
        //Se cierra el writer como buena practica de programacion, así como para evitar choques por tener el árchivo abierto en otra ventana
        writer.close();
        
        //Se crea un lector del archivo y se le pasa el directorio del archivo, luego se leeran todos los datos 
        //del archivo csv y se almacenarán en una lista de cadenas de texto
        CSVReader csvReader = new CSVReader(new FileReader(file));
        List<String[]> todoslosregistros = csvReader.readAll();
        //Se cierra el lector como buena practica de programacion, así como para evitar choques por tener el árchivo abierto en otra ventana
        csvReader.close();
        
        
        //Se entra a un for donde se recorren todos los regiustros  
        for (String[] cell : todoslosregistros) {
            //Se agregan a la lista de registro de cliente solo aquellas que coincidan con la id de la session del cliente
            if (cell[0].toString().equals(id) && cell!=null){
                String[] celln = {cell[0].toString(),cell[1].toString(),cell[2].toString(),cell[3].toString()};
                registrocliente.add(celln);
                
            }
        
        }
        //Se crea un atributo de sesión llamado RegistroCliente que tendrá los registros del cliente
        session.setAttribute("RegistroCliente", registrocliente);

    %> 
    <!--Se crea un boton con la accion de realizar una consulta -->
    <form name="realizarConsulta" action="paginaconsulta.jsp">
        <input type="submit" value="Realizar consulta" name="btnConsulta" />
    </form>
    
      
   
    </body>
</html>
