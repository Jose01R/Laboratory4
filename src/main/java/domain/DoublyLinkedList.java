package domain;

public class DoublyLinkedList implements List {
    private Node first; //apuntador al inicio de la lista

    //Constructor
    public DoublyLinkedList() {
        this.first = null;
    }

    @Override
    public int size() throws ListException {
        if (isEmpty())
            throw new ListException("Doubly Linked List is empty");

        int counter = 0; //contador de nodos
        Node aux = first; //aux para moverme por la lista y no perder el puntero al inicio
        while (aux != null) {
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
        return first == null;
    }

    @Override
    public boolean contains(Object element) throws ListException {
        if (isEmpty())
            throw new ListException("Doubly Linked List is empty");

        Node aux = first;
        while (aux != null) {
            if (util.Utility.compare(aux.data, element) == 0) return true; //ya lo encontro
            aux = aux.next; //muevo aux al nodo sgte
        }
        return false; //significa que no encontro el elemento
    }

    @Override
    public void add(Object element) {
        Node newNode = new Node(element);
        if (isEmpty())
            first = newNode;
        else {
            Node aux = first; //aux para moverme por la lista y no perder el puntero al inicio
            while (aux.next != null) {
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
        if (isEmpty())
            first = newNode;
        else {
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
            throw new ListException("Doubly Linked List is empty");
        //Caso 1: El elemento a suprimir es el primero de la lista
        if (util.Utility.compare(first.data, element) == 0) {
            first = first.next;
            first.prev = null; //actualizo el enlace al nodo anteior
        }
        //Caso 2. El elemento puede estar en el medio o al final
        else {
            Node prev = first; //nodo anterior
            Node aux = first.next; //nodo sgte
            while (aux != null && !(util.Utility.compare(aux.data, element) == 0)) {
                prev = aux;
                aux = aux.next;
            }
            //se sale del while cuanda alcanza nulo
            //o cuando encuentra el elemento
            if (aux != null && util.Utility.compare(aux.data, element) == 0) {
                //debo desenlazar  el nodo
                prev.next = aux.next;
                //mantengo el doble enlace
                if (aux.next != null)
                    aux.next.prev = prev;
            }
        }
    }

    @Override
    public Object removeFirst() throws ListException {
        if (isEmpty())
            throw new ListException("Doubly Linked List is empty");

        Object value = first.data;
        first = first.next; //movemos el apuntador al nodo sgte
        //rompo el doble enlace puede que deba quitarlo
        if (first != null)
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

        while (aux.next != null) { //SE SALE CUANDO AUX.NEXT == NULL
            last = aux;
            aux = aux.next;
        }
        last.next = null; //ELIMNA EL ULTIMO ELEMENTO

        return last.data; //RETORNA EL ULTIMO ELEMENTO DE LA LISTA
    }

    @Override
    public void sort() throws ListException {//me falta implementar
        if (isEmpty())
            throw new ListException("Doubly Linked List is empty");

        bubbleSort();
    }

    @Override
    public int indexOf(Object element) throws ListException {
        if (isEmpty())
            throw new ListException("Doubly Linked List is empty");

        Node aux = first;
        int index = 1; //el primer indice de la lista es 1
        while (aux != null) {
            if (util.Utility.compare(aux.data, element) == 0) return index;
            index++;
            aux = aux.next;
        }
        return -1; //significa q el elemento no existe en la lista
    }

    @Override
    public Object getFirst() throws ListException {
        if (isEmpty())
            throw new ListException("Doubly Linked List is empty");

        return first.data;
    }

    @Override
    public Object getLast() throws ListException {
        if (isEmpty())
            throw new ListException("Doubly list is empty");

        Node aux = first;
        Node last = null;
        while (aux != null) {
            last = aux;
            aux = aux.next;
        }

        return last.data;
    }

    @Override
    public Object getPrev(Object element) throws ListException {
        if (isEmpty())
            throw new ListException("Doubly list is empty");

        Node current = first;
        Node prev = null;

        while (current != null) {
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

        while (aux != null) {
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
        if (isEmpty())
            throw new ListException("Doubly Linked List is empty");
        Node aux = first;
        int i = 1; //posicion del primer nodo
        while (aux != null) {
            if (util.Utility.compare(i, index) == 0) {
                return aux;
            }
            i++;
            aux = aux.next; //lo movemos al sgte nodo
        }
        return null;
    }

    @Override
    public String toString() {
        if (isEmpty()) return "Doubly Linked List is empty";
        String result = "Doubly Linked List Content\n";
        Node aux = first; //aux para moverme por la lista y no perder el puntero al inicio
        while (aux != null) {
            result += aux.data + " ";
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


    private void bubbleSort() throws ListException {
        boolean swapped;
        do {
            swapped = false;
            Node aux = first;
            while (aux != null && aux.next != null) {
                Register register1 = (Register) aux.data;
                Register register2 = (Register) aux.next.data;

                // Comparar por el nombre del estudiante en los registros
                String studentName1 = util.Utility.getStudentNameById(register1.getStudentId());
                String studentName2 = util.Utility.getStudentNameById(register2.getStudentId());

                // Comparar los nombres
                if (studentName1.compareTo(studentName2) > 0) {
                    // Intercambiar los nodos
                    Object temp = aux.data;
                    aux.data = aux.next.data;
                    aux.next.data = temp;

                    swapped = true;
                }

                aux = aux.next;
            }
        } while (swapped); // Continuar hasta que no haya intercambios


    }
}