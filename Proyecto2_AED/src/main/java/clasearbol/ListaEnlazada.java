/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clasearbol;

/**
 *
 * @author Usuario
 * @param <T>
 * Clase ListaEnlazada, crea un nodo head que es la cabeza y una variable de enteros llamada sixe que guardará el tamaño de la lista */
public class ListaEnlazada <T> {
    private Node head;
    private int size;

    /**
     *Constructor de la clase listaEnlazada, crea una lista con la cabeza nula y de tamaño cero
     */
    public ListaEnlazada() {
        this.head = null;
        this.size = 0;
    }

    /**
     * @return valor booleano, si la lista está vacía
     * Metodo para verificar si la lista enlazada está vacía, si no hay cabeza es porque no hay ningún elemento
     */
    public boolean isEmpty() {
        return this.head == null;
    }

    /**
     *
     * @return tamaño se la lista
     * 
     * Metodo que devuelve el valor entero almacenado en la variable size que guarda el tamaño de la lista
     */
    public int size() {
        return this.size;
    }

    /**
     *
     * @param _data dato a insertar
     * Metodo que recibe un nodo _data agrega un nuevo elemento en la cabeza (encima de la pila)
     * Asigna la cabeza a este nodo
     */
    public void insertFirst(Node _data) {
        Node newBNode = _data;
        newBNode.next = this.head;
        this.head = newBNode;
        this.size++;
    }

    /**
     *
     * @return El ´rimer elemento de la lista (la cabeza)
     * Metodo que elimina y devuelve el primer elemento de la lista enlazada
     */
    public Node deleteFirst() {
        //Verifica si la lista está vacía, si es así devuelve nulo,
        //Si no está vacía almacena la cabeza en un nodo temporal y cambia la cabeza al nodo siguiente
        if (this.head != null) {
            Node temp = this.head;
            this.head = this.head.next;
            this.size--;
            return temp;
        } else {
            return null;
        }
    }
   
    /**
     *
     * @return
     * Metodo que devuelve el primer elemento de la lista enlazada
     */
    public Node<T> getHead(){
      return this.head;
  }
  

}

