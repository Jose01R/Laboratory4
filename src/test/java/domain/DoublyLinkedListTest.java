package domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DoublyLinkedListTest {

    @Test
    void test1() {
        DoublyLinkedList list = new DoublyLinkedList();
        list.addFirst(20);
        list.addFirst(10);
        list.addFirst(30);
        list.addFirst(50);
        list.addFirst(40);
        list.add(70);
        list.add(5);
        System.out.println(list);
        try {
            System.out.println("List size: "+list.size());
            System.out.println("Removed first item: "+list.removeFirst());
            System.out.println("List size: "+list.size());
            System.out.println(list);
            System.out.println("Removed first item: "+list.removeFirst());
            System.out.println("List size: "+list.size());
            /*for (int i = 0; i < 6 ; i++) {
                list.removeFirst();
            }*/
            for (int i = 0; i < 50; i++) {
                list.add(util.Utility.random(50));
            }
            System.out.println(list);

            for (int i = 0; i <10 ; i++) {
                int value = util.Utility.random(50);
                System.out.println(
                        list.contains(value)
                                ? "The element ["+value+"] exists in the list. " +
                                "Index: "+list.indexOf(value)
                                :"The element ["+value+"] does not exist in the list"
                );

                //probamos remove
                if(list.contains(value)){
                    list.remove(value);
                    System.out.println("The element ["+value+"] has been deleted");
                }
            }

            System.out.println(list);
        } catch (ListException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void test2(){//ya probe el metodo contains(object)
        DoublyLinkedList list = new DoublyLinkedList();//lista de cursos
        list.add(new Course("IF-3001", "Algoritmos y Estructuras de Datos", 4));
        list.add(new Course("IF-4001", "Sistemas Operativos", 4));
        list.add(new Course("IF-2000", "Programación 1", 4));
        list.add(new Course("IF-3000", "Programación 2", 4));
        list.add(new Course("IF-4000", "Arquitectura", 4));
        list.add(new Course("IF-5000", "Redes", 3));
        list.add(new Course("IF-5100", "Bases de Datos", 4));
        list.add(new Course("IF-4101", "Lenguajes app Comerciales", 4));
        list.add(new Course("IF-3100", "Sistemas de Información", 3));

        System.out.println(list);

        try {
            //prueba del metodo contains(objetc) listo
            System.out.println("");//para que en el panel de control haya un espacio
            System.out.println(list.contains(new Course("IF-6201")) );
            System.out.println(list.contains(new Course("IF-3001")) );
            System.out.println(list.contains(new Course("IF-4001")) );
            System.out.println(list.contains(new Course("IF-6100")) );
            System.out.println("");//para que en el panel de control haya un espacio

            //prueba del metodo getNode(i) listo
            for (int i = 1; i <= list.size(); i++) {
                System.out.println("El elemento en la posicion " + i + " es: " + list.getNode(i).data);
            }

            //prueba del metodo indexOf(object)
            System.out.println("");
            for (int i = 1; i < list.size() ; i++) {
                System.out.println(
                        list.contains(new Course("IF-6001"))
                                ? "The course exists in the list: "+list.getNode(i).data + " Index: " + list.indexOf(list)
                                :"The course not exists in the list: "+list.getNode(i).data
                );
            }







//            //prueba de suprimir un curso con id con remove
//            if (list.contains(new Course("IF-5000"))) {
//                list.remove(list);
//                System.out.println("The element[" + value + "] has been deleted");
//            }

        } catch (ListException e) {
            throw new RuntimeException(e);
        }

    }


}