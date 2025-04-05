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
            System.out.println("\n");//para que en el panel de control haya un espacio
            System.out.println("¿Existe Informática Aplicada, Id=IF-6201? " +list.contains(new Course("IF-6201")) );
            System.out.println("¿Existe Algoritmos y Estructuras de Datos, Id=IF-3001? " +list.contains(new Course("IF-3001")) );
            System.out.println("¿Existe Sistemas Operativos, Id=IF-4001? " +list.contains(new Course("IF-4001")) );
            System.out.println("¿Existe Análisis y Diseño de Sistemas, Id=IF-6100? " +list.contains(new Course("IF-6100")) );

            System.out.println("\n");

            //prueba del metodo getNode(i) listo
            for (int i = 1; i <= list.size(); i++) {
                System.out.println("El elemento en la posicion " + i + " es: " + list.getNode(i).data);
            }

            //prueba del metodo indexOf(object) listo
            Course courseToSeach1 = new Course("IF-3001");
            Course courseToSeach2 = new Course("IF-6100");
            Course courseToSeach3 = new Course("IF-3000");
            Course courseToSeach4 = new Course("XS-0105");
            Course courseToSeach5 = new Course("IF-3100");
            System.out.println("\n");
            System.out.println("El curso Algoritmos y Estructuras de Datos, con Id=IF-3001 se encuentra en la posición: " + list.indexOf(courseToSeach1));
            System.out.println("El curso Análisis y Diseño de Sistemas, con con Id=IF-6100 se encuentra en la posicion: " + list.indexOf(courseToSeach2));
            System.out.println("El curso Programación 2, con Id=IF-3000 se encuentra en la posición: " + list.indexOf(courseToSeach3));
            System.out.println("El curso Estadísticas para Informáticos, conId=XS-0105 se encuentra en la posición: " + list.indexOf(courseToSeach4));
            System.out.println("El curso Lenguajes app Comerciales, con Id=IF-3100 se encuentra en la posición: " + list.indexOf(courseToSeach5));

            //prueba del suprimir "remove "los cursos con Id= IF-5000, IF-5100 listo
            Course deletedCourse1 = new Course("IF-5000");
            Course deletedCourse2 = new Course("IF-5100");
            System.out.println("\n");
            System.out.println("The course with ID selected they have been eliminated");
            if (list.contains(deletedCourse1)) {
                list.remove(deletedCourse1);
                System.out.println("The course with ID = [" + deletedCourse1.getId() + "] has been deleted");
            }
            if (list.contains(deletedCourse2)) {
                list.remove(deletedCourse2);
                System.out.println("The course with ID = [" + deletedCourse2.getId() + "] has been deleted");
            }
            System.out.println("\n");
            System.out.println(list);

            //PROBAMOS GET LAST Y GET PREV
            System.out.println("\n");
            System.out.println("Ultimo de la lista");
            System.out.println(list.getLast());
            System.out.println("\n");
            System.out.println("Prev");
            System.out.println(list.getPrev(courseToSeach3));
            //PROBAMOS GET NEXT
            System.out.println("\n");
            System.out.println("Next");
            System.out.println(list.getNext(courseToSeach3));

            //PROBAMOS REMOVE LAST
            System.out.println("\n");
            System.out.println("Ultimo elemento: "+ list.removeLast());
            System.out.println("\n" + list);

            //prueba de ordene la lista de cursos por nombre, con el metodo sort() no estoy seguro
            System.out.println("\n");
            System.out.println("The course list is sort the name courses");
            list.sort();
            System.out.println(list);

        } catch (ListException e) {
            throw new RuntimeException(e);
        }

    }


}