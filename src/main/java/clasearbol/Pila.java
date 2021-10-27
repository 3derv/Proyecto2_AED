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
 * Crea una clase pila, esta será usada para crear una pila de los operandos y otra de los operadores
 */
public class Pila <T> {
    //Se instancia un objeto de la clase lista enlazada de objetos T
    private ListaEnlazada<T> pila = new ListaEnlazada();

    /**
     *
     * @param _newElement
     * Método para agregar un nuevo elemento a la lista enlazada, recibe un nodo _newElement
     * y llama al método insertfirst para agregar el elemento a la pila
     */
    public void push(Node _newElement) {
        this.pila.insertFirst(_newElement);
    }

    /**
     *
     * @return
     * Metodo que saca el primer elemento de la pila y lo devuelve
     */
    public Node<T> pop() {
        return this.pila.deleteFirst();
    }

    /**
     *
     * @return
     * Método que devuelve el primer elemento de la pila
     */
    public Node<T> top() {
        return this.pila.getHead();
    }

    /**
     *
     * @return
     * Metodo que devuelve un valor booleano si la pila está vacía
     */
    public boolean estaVacia(){
        return this.pila.isEmpty();
    }
    
    /**
     *
     * @return
     * Metodo que devuelve la cantidad de elemento dentro de la lista
     */
    public int cantidad(){
        return pila.size();
    }
   
}


