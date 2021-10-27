/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clasearbol;

/**
 *
 * @author 3der
 * @param <T>
 * Clase Node que crea al objeto nodo que servirá tanto para la pila() como para los nodos del árbol
 * 
 */
public class Node<T> {
    //Se crea un una variable para almacenar el dato a almacenar y un nodo
    private T data;
    private Node<T> ndata;

    /**
     * Se crea un nodo izquierdo y se inicializa como nulo
     */
    public Node<T> izquierda = null;

    /**
     * Se crea un nodo derecho y se inicializa como nulo
     */
    public Node<T> derecha = null;

    /**
     * Se crea un nodo para el nodo siguiente y se inicializa como nodo nulo
     */
    public Node<T> next;

    /**
     * Se crea un nodo que será el nodo cabeza y se inicializa como nulo
     */
    public Node<T> head;

    /**
     * Se crea una variable de texto que almacenará el caracter del nodo
     */
    public String caracter;

    /**
     *
     * @param data
     * Constructor de la clase nodo que recibe un dato y lo almacena en la variable data y en el caracter
     * Se encarga de almacenar los datos de la pila tanto de la pila de operadores como de operandos
     */
    public Node(T data) {
        this.izquierda = null;
        this.data = (T) data;
        this.derecha = null;
        caracter = (String) data.toString();
    }

    /**
     *
     * @param _ndata -- Nodorecibido
     * @param _caracter -- Caracter a almacenar en la variable caracter
     * Es usado para crear los nodos que serán los nodos hijos del árbol
     * Almacena en ndata el nodo recibido y en caracter el caracter
     */
    public Node(Node _ndata, String _caracter) {
        this.izquierda = null;
        this.ndata = (Node) _ndata;
        this.derecha = null;
        caracter = (String) _caracter;
}
    /**
     *
     * @param op1 Nodo izquierdo
     * @param pop Caracter que opera a los valores de los nodos
     * @param op2 Nodo derecho
     * @param _caracter Caracter del nodo
     * Constructor de sub arbaoles recibe como parámetro un nodo izquierdo, un nodo derecho
     * un nodo central y el caracter que almacenará
     */
    public Node(Node op1, String pop, Node op2, String _caracter) {
        this.izquierda = op1;
        this.data=(T) pop;
        this.derecha= op2;
        caracter = (String) _caracter;
    }

    /**
     *
     * @return Nodo izquierdo
     * Método que regresa el nodo izquierdo de un nodo, si no hay nodo izquierso devuelve nulo
     */
    public Node getIzquierda() {
        if (izquierda == null){
            return null;
        }else{
            return this.izquierda;}
        }

    /**
     *
     * @return Nodo derecho
     * Método que regresa el nodo derecho de un nodo, si no hay nodo derecho devuelve nulo
     */
    public Node<T> getDerecha() {
        return this.derecha;
    }

    /**
     *
     * @return Nodo siguiente
     * Método que devuelve el nodo siguiente de un nodo en cuestión
     */
    public Node<T> getNext() {
        return this.next;
    }

    /**
     *
     * @return Dato del nodo
     * Metodo que devuelve el dato de un nodo (caracter u dato)
     */
    public T getData() {
        return this.data;
    }

    /**
     *
     * @return Nodo del nodo
     * Devuelve un nodo dentro del nodo
     */
    public Node <T> getNData() {
        return this.ndata;
    }

    /**
     *
     * @return Elcaracter del nodo
     * Devuelve el carácter almacenado en el nodo
     */
    public String getCaracter() {
        return caracter;
    }
}
