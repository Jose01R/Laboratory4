package domain;

public class SinglyLinkedList implements List{

    private Node first; // CABEZA - HEAD
    //CONSTRUCTOR
    public SinglyLinkedList() {
        this.first = null;
    }

    @Override
    public int size() throws ListException {
        if (isEmpty()){
            throw new ListException("Singly list is empty");
        }
        Node aux = first; //aux para moverme por la lista y no perder el puntero al inicio
        int counter = 0; //contador de nodos

        while (aux!=null){
            aux = aux.next;
            counter++;
        }

        return counter;
    }

    @Override
    public void clear() {
        this.first = null;
    }

    @Override
    public boolean isEmpty() {
        return first == null;
    }

    @Override
    public boolean contains(Object element) throws ListException {
        if (isEmpty())
            throw new ListException("Singly list is empty");
        Node aux = first;
        while (aux!=null){
            if (util.Utility.compare(aux.data, element) == 0) return true; //TRUE = ELEMENTO ENCONTRADO
            aux = aux.next; //AVANZA AL SIGUIENTE NODE
        }

        return false; //ELEMENTO NO ENCONTRADO
    }

    @Override
    public void add(Object element) {
        Node newNode = new Node(element);

        if (isEmpty())
            first = newNode;
        else {
            Node aux = first;
            while (aux.next != null) {
                aux = aux.next;
            }
            //SE SALE DEL WHILE CUANDO AUX ESTA EN ULT NODE
            aux.next = newNode;
        }
    }

    @Override
    public void addFirst(Object element) {
        Node newNode = new Node(element);

        if (isEmpty()) {
            first = newNode;
        } else {
            newNode.next = first;
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
        if (isEmpty() || util.Utility.compare(element, first.data) < 0) {
            newNode.next = first;
            first = newNode;
            return;
        }

        // Caso 2: Buscar la posición correcta para insertar
        Node current = first;

        while (current.next != null && util.Utility.compare(current.next.data, element) < 0) {
            current = current.next;
        }

        newNode.next = current.next;
        current.next = newNode;

    }

    @Override
    public void remove(Object element) throws ListException {
        if (isEmpty())
            throw new ListException("Singly list is empty");
        //CASO 1:  ELEMENTO A SUPRIMIR ES EL PRIMERO
        if (util.Utility.compare(first.data, element)==0)
            first = first.next;
        //CASO 2: Elemento puede estar en el medio o al final
        else {
            Node prev = first; //Nodo anterior
            Node aux = first.next; // Nodo siguiente
            while (aux!=null && !(util.Utility.compare(aux.data, element) == 0)){
                prev = aux;
                aux = aux.next;
            }
            //SE SALE DEL WHILE CUANDO ALCANZA NULO O CUANDO ENCUENTRA EL ELEMENTO
            if (aux != null && util.Utility.compare(aux.data, element) == 0){
                //DEBO DESENLAZAR EL NODO
                prev.next = aux.next;
            }
        }
    }

    @Override
    public Object removeFirst() throws ListException {

        if (isEmpty()){
            throw new ListException("Singly list is empty");
        }
        Object value = first.data;
        first = first.next;

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
        if (isEmpty()) {
            throw new ListException("Singly list is empty");
        }

        bubbleSort();
}

    @Override
    public int indexOf(Object element) throws ListException {
        if (isEmpty())
            throw new ListException("Singly list is empty");
        Node aux = first;
        int index = 1; //PRIMERA POS

        while(aux!=null){
            if (util.Utility.compare(aux.data, element) == 0) return index;
            index++;
            aux = aux.next;
        }
        return -1; //NO EXISTE EL ELEMENTO EN LA LISTA
    }

    @Override
    public Object getFirst() throws ListException {
        if (isEmpty())
            throw new ListException("Singly list is empty");
        return first.data;
    }

    @Override
    public Object getLast() throws ListException {
        if (isEmpty())
            throw new ListException("Singly list is empty");

        Node aux = first;
        Node last = null;
        while(aux!=null){
            last = aux;
            aux = aux.next;
        }

        return last.data;
    }

    @Override
    public Object getPrev(Object element) throws ListException {
       if (isEmpty())
            throw new ListException("Singly list is empty");

        Node current = first;
        Node prev = null;

        while(current!=null){
            if (util.Utility.compare(current.data, element) == 0) {
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
            throw new ListException("Singly list is empty");

        Node aux = first;

        while(aux!=null){
            if (util.Utility.compare(aux.data, element) == 0) {
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
            throw new ListException("Singly Linked List is empty");
        Node aux = first;
        int i = 1; //posicion del primer nodo
        while(aux!=null){
            if(util.Utility.compare(i, index)==0){
                return aux;
            }
            i++;
            aux = aux.next; //lo movemos al sgte nodo
        }

        //throw new ListException("Element not found in the list");
        return null;
    }

    @Override
    public String toString() {
        if (isEmpty()) return "Singly linked list is empty";

        String result = "Singly linked list Content\n";
        Node aux = first; //aux para moverme por la lista y no perder el puntero al inicio

        while (aux!=null){
            result+= aux.data + "\n";
            aux = aux.next; // LO NUEVO AL SIGUIENTE NODO
        }
        return result;
    }

    private void bubbleSort() {
        boolean swapped;
        do {
            swapped = false;
            Node aux = first;
            while (aux.next != null) {
                if (util.Utility.compare(aux.data, aux.next.data) > 0) {
                    // Intercambia los datos
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
