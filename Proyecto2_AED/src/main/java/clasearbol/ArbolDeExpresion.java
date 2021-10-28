/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clasearbol;

import java.util.*;



/**
 *
 * /Clase ArbolDeExpresion
 * @author 3der
 * Clase Arbol de expresion, se crea con dos objeto de tipo pila los cuales almacenarán los operandos y operadores
 * Tambien instancia dos cadenas de tipo string, uno llmado blanco y el otro operadores, en estos se almacenbará el string 
 * equivalente al espacio en blanco y la lista de operadores
 */
public class ArbolDeExpresion {
    Pila pOperandos;               // Pila de operandos
    Pila pOperadores;              // Pila de operadores
    final String blanco;           // Cadena de espacios en blanco
    final String operadores;       // Cadena con operadores para expresiones

  /**
   * Constructor por omision, crea dos objetos de la clase pila
   * alamcena en la variable blanco \t que es el texto en un string de un espacio en blanco
   */
    public ArbolDeExpresion() {
	pOperandos = new Pila();
	pOperadores = new Pila();
	blanco = " \t";
	operadores = ")+-*/%^(";  //acomodados por precedencia;
    }

  /**
   * Metodo para construir un arbol para una expresion aritmetica dada.
   * @param expresion -- Cadena con la expresion aritmetica
   * @return NodoArbol -- nodo raiz del arbol creado
   */
    public Node construirArbol(String expresion) {
        //Se crea un objeto de tipo tokenizer, servirá para recorrer los caracteres de la expresión
       	StringTokenizer tokenizer;
        //Se crfea un objeto de tipo cadena de texto, llamado token, almacenará un caracter
	String token;
        //Se crea un nodo raíz y se inicializa como nulo
	Node raiz;
        //Se crea un objeto de tipo tokenizer conn el texto de la operación, con los separadores del espacio 
        //en blanco y los operadores, e donde estos también se contarán como nodos
	tokenizer = new StringTokenizer(expresion, blanco+operadores, true);
        //Se entrará en un ciclo while cual no acabará hasra que la expresión no tenga mas tokens
	while (tokenizer.hasMoreTokens()) {
            //Se almacena en el token el siguiente elemento de la lista
	    token = tokenizer.nextToken();
            // Es un espacio en blanco, se ignora
	    if (blanco.contains(token));     
            // Prueba si no es un operador, si es así lo guarda como nodo de la pila de operandos
	    else if (!operadores.contains(token)) {
                Node ntoken = new Node(token);
		pOperandos.push(ntoken);
            
            //Saca elementos hasta encontrar un parentesis izquierdo "("
	    } else if(token.equals(")")) { 
                //Mientras la pila de operadores no está vacía y el elemento superior de la pila no sea un paréntesis izquierdo
                //se guardará un sub árbol con los elemntos adentro de los parentesis
		while (!pOperadores.estaVacia() && !pOperadores.top().getData().equals("(")) {
		    guardarSubArbol();
		}
                //Se saca el parentesis izquierdo
		pOperadores.pop();  
	    } else {
                //operador diferente de cualquier parentesis
		if (!token.equals("(") && !pOperadores.estaVacia()) {
                    //Almacena el elemento que está encima de la pila
		    String op = (String) pOperadores.top().getData();
                    //Entra a un while, mientras el operador no sea un paréntesis, la pila de operadores no esté vacía
                    //Y la posición de precedencia en la cadena de operadores del operador encima de la pila sea mayor
                    //o igual que la del token en custión entonces se guardará un sub árbol
		    while (!op.equals("(") && !pOperadores.estaVacia() && operadores.indexOf(op) >= operadores.indexOf(token)) {
			guardarSubArbol();
                        //Verifica si la pila de operadores no está vacía, si no lo está cambia el valor de op
                        //al actual operador que esté en la cima de la pila de operadores
			if (!pOperadores.estaVacia()) 
			    op = (String) pOperadores.top().getData();
		    }
		}
                //Si se sabe que es un operador pero este es es tiene un orden de evaluación menor al del operador
                //que está en la cima de la pila de operadores entonces se crea un nodo y se coloca en la pila de operadores
		Node ntoken = new Node(token);
                pOperadores.push(ntoken);              
	    }
	}
	//Si ya se evalúo toda la expresión y se agregó a sus respectivas pilas, entonces se procede a terminar de crear el árbol
	//Para este fin asigna como raiz al primer nodo de la pila de operandos, y entra a un whhile en el cual estará construyendo
        //el árbol con los otros operandos, hasta que la pila de operandos se vacíe
        raiz = (Node)pOperandos.top();
	while (!pOperadores.estaVacia()) {
            //Si es un parentesis lo saca y sigue
	    if (pOperadores.top().getData().equals("(")) {
		pOperadores.pop();
	    } else {
            //Se guarda un sub árbol y se almacena este como la raíz
	    guardarSubArbol();
	    raiz = (Node) pOperandos.top();
	    }
	}
        //Se devuelve la raíz del árbol
	return raiz;
    }

  /*
   * Metodo privado para almacenar en la pila un subarbol
   */
    private void guardarSubArbol() {
        //Construye el nodo op1 con los datos del nodo en la cima de la pila y su caracter, luego saca ese nodo
	Node op1 = new Node(pOperandos.top(), pOperandos.pop().getCaracter());
        //Construye el nodo op2 con los datos del nodo en la cima de la pila y su caracter, luego saca ese nodo
	Node op2 = new Node(pOperandos.top(), pOperandos.pop().getCaracter());
        //Se crea el nodo padre sel sub arbol con op2 por la izquierda, op1 por la derecha, y pasa como caracter el
        //caracter del nodo que se encuentra en la cima de la pila
        Node suba = new Node(op2, pOperadores.top().getCaracter(),  op1, pOperadores.pop().getCaracter());
        //Inserta el sub arbol en la pila de operandos
        pOperandos.push(suba);
        
    }

    /**
     *
     * @param nodo al cual se calcularán sus hijos izquierdo y derecho
     * @return un numero double con el resultado de operar los nodos 
     */
    public double calcular(Node nodo) {
        //Verifica si el nodo no tiene hijos, en caso de no tenerlos es porque es una hoja, por lo cual es un número y se puede convertir entero
    if (nodo.getIzquierda()==null && nodo.getDerecha()==null) {
        return Integer.parseInt(nodo.getCaracter());
    } else {
        //Instancia el nodo para luego calcularle el valor de las operaciones de sus hijos
        Node operacionNodo =  (Node)nodo;
        //Calcula el nodo izquierdo al recurrir en el método calcular con el nodo izquierdo
        double valorIzquierda = calcular(operacionNodo.getIzquierda().getNData());
        //Calcula el nodo izquierdo al recurrir en el método calcular con el nodo derecho
        double valorDerecha = calcular(operacionNodo.getDerecha().getNData());
        //Entra a un switch case con el caracter del nodo de opercion en cuestión, depende del caracter realiza cierta operación
        switch(operacionNodo.getCaracter()) {
            case "+":
                //Si es un simbolo de suma entonces devuelve el resultado de sumar el valor izquierdo y derecho
                return valorIzquierda + valorDerecha;
            case "-":
                //Si es un simbolo de resta entonces devuelve el resultado de restar el valor izquierdo y derecho
                return valorIzquierda - valorDerecha;
            case "*":
                //Si es un simbolo de multiplicación entonces devuelve el resultado de multiplicar el valor izquierdo y derecho
                return valorIzquierda * valorDerecha;
            case "/":
                ////Si es un simbolo de dividión entonces devuelve el resultado de dividir el valor izquierdo entre el derecho
                return valorIzquierda / valorDerecha;
            case "%":
                //Si es un simbolo de porcentaje entonces devuelve el residuo de dividir el valor izquierdo entre el derecho
                return valorIzquierda % valorDerecha;
            case "^":
                //Si es in símbolo de exponente entonces devuelve el resultado del valor izquierdo elvado al valor derecho
                return Math.pow(valorIzquierda, valorDerecha);
            default:
                //Si no corresponde a ninguno entoncea imprime un mensaje de operación iválida
                throw new RuntimeException("Operación inválida: " + operacionNodo.getCaracter());
        }
    }
}
    /**
     *
     * @param _operacion operacion que es recibida para evaluarla en el árbol
     * @return Se devuelve el resultado de operar el árbol
     * Metodo que encapsulará todo el proceso sw construir el árbol
     */
    public double construirElArbol(String _operacion){
    //Se crea la variable de tipo double resultado
    double resultado = 0;
    //Se comprueba que la operación no esté en blanco, si no lo está contruye el árbol, y calcula su resultado
    if (!"".equals(_operacion)){
        Node raiz = construirArbol(_operacion);
        resultado = calcular(raiz);
        System.out.println("El resultado de la operación es:"+resultado);
        }
    //Se devuelve el valor almacenado en resultado, estaba en blanco seguirá en cero, sino el resultado del árbol
    return resultado;      
    }

}

