package domain;

import util.Utility;

public class DoublyLinkedList implements List {
    private Node first; //apuntador al inicio de la lista
    private Node last; //apuntador al inicio de la lista

    //Constructor
    public DoublyLinkedList(){

        this.first = null;
        this.last = null;
    }

    @Override
    public int size() throws ListException {
        if(isEmpty())
            throw new ListException("Doubly Linked List is empty");

        int counter = 0; //contador de nodos
        Node aux = first; //aux para moverme por la lista y no perder el puntero al inicio
        while(aux!=null){
            counter++;
            aux = aux.next;
        }
        return counter;
    }

    @Override
    public void clear() {
        this.first = null; //anula la lista
    }

    @Override
    public boolean isEmpty() {
        return first ==null;
    }

    @Override
    public boolean contains(Object element) throws ListException {
        if(isEmpty())
            throw new ListException("Doubly Linked List is empty");

        Node aux = first;
        while(aux!=null){
            if(Utility.compare(aux.data, element)==0) return true; //ya lo encontro
            aux = aux.next; //muevo aux al nodo sgte
        }
        return false; //significa que no encontro el elemento
    }

    @Override
    public void add(Object element) {
        Node newNode = new Node(element);
        if(isEmpty())
            first = newNode;
        else{
            Node aux = first; //aux para moverme por la lista y no perder el puntero al inicio
            while(aux.next!=null){
                aux = aux.next; //mueve aux al nodo sgte
            }
            //se sale del while cuando aux esta en el ult nodo
            aux.next = newNode;
            //hago el doble enlace
            newNode.prev = aux;
        }
    }

    @Override
    public void addFirst(Object element) {
        Node newNode = new Node(element);
        if(isEmpty())
            first = newNode;
        else{
            newNode.next = first;
            //hago el doble enlace
            first.prev = newNode;
            first = newNode;
        }
    }

    @Override
    public void addLast(Object element) {
        add(element);
    }

    @Override
    public void addInSortedList(Object element) {
        Node newNode = new Node(element);

        // Caso 1: La lista está vacía o el nuevo elemento es menor que el primero
        if (isEmpty() || Utility.compare(element, first.data) < 0) {
            newNode.next = first;
            first = newNode;
            return;
        }

        // Caso 2: Buscar la posición correcta para insertar
        Node current = first;

        while (current.next != null && Utility.compare(current.next.data, element) < 0) {
            current = current.next;
        }

        newNode.next = current.next;
        current.next = newNode;
    }

    @Override
    public void remove(Object element) throws ListException {
        if (isEmpty()) {
            throw new ListException("Doubly Linked List is empty");
        }

        // Caso 1: El elemento a suprimir es el primero de la lista
        if (Utility.compare(first.data, element) == 0) {
            if (first == this.last) {
                // Si la lista tiene un solo nodo
                first = last = null;
            } else {
                first = first.next;
                if (first != null) {
                    first.prev = null; // Actualizo el enlace al nodo anterior
                }
            }
        }
        // Caso 2: El elemento puede estar en el medio o al final
        else {
            Node prev = first; // Nodo anterior
            Node aux = first.next; // Nodo siguiente

            while (aux != null && Utility.compare(aux.data, element) != 0) {
                prev = aux;
                aux = aux.next;
            }

            // Si encuentra el elemento a eliminar
            if (aux != null && Utility.compare(aux.data, element) == 0) {
                // Caso 1: El nodo a eliminar es el último
                if (aux == last) {
                    last = prev;  // Actualizo el último nodo
                    last.next = null; // Elimino la referencia al siguiente nodo
                } else {
                    prev.next = aux.next;
                    if (aux.next != null) {
                        aux.next.prev = prev;
                    }
                }
            }
        }
    }


    @Override
    public Object removeFirst() throws ListException {
        if(isEmpty())
            throw new ListException("Doubly Linked List is empty");

        Object value = first.data;
        first = first.next; //movemos el apuntador al nodo sgte
        //rompo el doble enlace puede que deba quitarlo
        if(first!=null)
            first.prev = null;
        return value;
    }

    @Override
    public Object removeLast() throws ListException {
        if (isEmpty())
            throw new ListException("Singly list is empty");

        //CASO 1: SOLO HAY 1 ELEMENTO EN LA LSTA
        if (first.next == null) {
            Object data = first.data;
            first = null; // La lista queda vacía
            return data;
        }

        Node aux = first;
        Node last = null;

        while(aux.next!=null){ //SE SALE CUANDO AUX.NEXT == NULL
            last = aux;
            aux = aux.next;
        }
        last.next = null; //ELIMNA EL ULTIMO ELEMENTO

        return last.data; //RETORNA EL ULTIMO ELEMENTO DE LA LISTA
    }

    @Override
    public void sort() throws ListException {
        if(isEmpty())
            throw new ListException("Doubly Linked List is empty");

        bubbleSort();
    }

    @Override
    public int indexOf(Object element) throws ListException {
        if(isEmpty())
            throw new ListException("Doubly Linked List is empty");

        Node aux = first;
        int index = 1; //el primer indice de la lista es 1
        while(aux!=null){
            if(Utility.compare(aux.data, element)==0) return index;
            index++;
            aux = aux.next;
        }
        return -1; //significa q el elemento no existe en la lista
    }

    @Override
    public Object getFirst() throws ListException {
        if(isEmpty())
            throw new ListException("Doubly Linked List is empty");

        return first.data;
    }

    @Override
    public Object getLast() throws ListException {
        if (isEmpty())
            throw new ListException("Doubly list is empty");

        Node aux = first;
        Node last = null;
        while(aux!=null){
            last = aux;
            aux = aux.next;
        }

        return last.data;    }

    @Override
    public Object getPrev(Object element) throws ListException {
        if (isEmpty())
            throw new ListException("Doubly list is empty");

        Node current = first;
        Node prev = null;

        while(current!=null){
            if (Utility.compare(current.data, element) == 0) {
                return (prev != null) ? prev.data : null; //NUL SI ELEMENTO ESTA AL INICIO
            }
            prev = current;
            current = current.next;
        }

        return null;
    }

    @Override
    public Object getNext(Object element) throws ListException {
        if (isEmpty())
            throw new ListException("Doubly list is empty");

        Node aux = first;

        while(aux!=null){
            if (Utility.compare(aux.data, element) == 0) {
                return (aux.next != null) ? aux.next.data : null; // Si es el último nodo, retorna null
            }
            aux = aux.next;
        }

        //throw new ListException("Element not found in the list");
        return null;
    }

    @Override
    public Node getNode(int index) throws ListException {
        if(isEmpty())
            throw new ListException("Doubly Linked List is empty");
        Node aux = first;
        int i = 1; //posicion del primer nodo
        while(aux!=null){
            if(Utility.compare(i, index)==0){
                return aux;
            }
            i++;
            aux = aux.next; //lo movemos al sgte nodo
        }
        return null;
    }

    @Override
    public String toString() {
        if(isEmpty()) return "Doubly Linked List is empty";
        String result = "Doubly Linked List Content\n";
        Node aux = first; //aux para moverme por la lista y no perder el puntero al inicio
        while(aux!=null){
            result+=aux.data+" ";
            aux = aux.next; //lo muevo al sgte nodo
        }
        return result;
    }

    public void sortByName() {
        boolean swapped;
        do {
            swapped = false;
            Node aux = first;
            while (aux != null && aux.next != null) {
                // Comparar por nombre
                //Se castea a Course para obtener nombre (getName)
                Course course1 = (Course) aux.data;
                Course course2 = (Course) aux.next.data;

                if (course1.getName().compareTo(course2.getName()) > 0) {
                    // Intercambiar los nodos
                    Object temp = aux.data;
                    aux.data = aux.next.data;
                    aux.next.data = temp;

                    swapped = true;
                }
                aux = aux.next;
            }
        } while (swapped);
    }

    private void bubbleSort() {
        boolean swapped;
        do {
            swapped = false;
            Node aux = first;

            while (aux.next != null) {
                if (Utility.compare(aux.data, aux.next.data) > 0) {
                    // Intercambia los nodos
                    Object temp = aux.data;
                    aux.data = aux.next.data;
                    aux.next.data = temp;

                    swapped = true;
                }
                aux = aux.next;
            }
        } while (swapped);
    }


}