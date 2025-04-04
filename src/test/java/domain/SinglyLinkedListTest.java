package domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SinglyLinkedListTest {

    /*
                        TODO
    Cree un algoritmo countNames(SinglyLinkedList list, String name), que retorne
    un valor entero que indique el número de veces que se repite el nombre del
    estudiante indicado en la lista
    Pruebe: Buscamos cuantos Carlos tenemos en la lista: "
    +countNames(list, "Carlos") → Debe retornar: 3


    Cree un algoritmo findNames(SinglyLinkedList list, String name), que retorne
    true si el nombre indicado ha sido agregado a la lista
    Pruebe:
    ¿En la lista existe una estudiante con el nombre Karla? false
    ¿En la lista existe una estudiante con el nombre Fabiana? true

     */

    @Test
    void test1(){
        SinglyLinkedList list = new SinglyLinkedList();
        list.add(new Student("1", "Maria", 20, "Cartago"));
        list.add(new Student("2", "Carlos", 22, "San Jose"));
        list.add(new Student("3", "Laura,", 20, "Paraiso"));
        list.add(new Student("4", "Paula,", 18, "Turrialba"));
        list.add(new Student("5", "Carlos", 21, "Limón"));
        list.add(new Student("6", "Fabiana,", 19, "Paraiso"));
        list.add(new Student("7", "María,", 23, "Guanacaste"));
        list.add(new Student("8", "Carlos", 25, "San carlos"));
        list.add(new Student("9", "Laura,", 20, "Turrialba"));
        list.add(new Student("10", "Pedro,", 24, "Heredia"));

        System.out.println(list);
<<<<<<< HEAD
        try {
            System.out.println(list.contains(new Student("1")) );
            System.out.println(list.contains(new Student("2")) );
=======
>>>>>>> 761dbc6348733c10c6be4bbd9f6db08c0512aae6

        try {
            //PROBAMOS CONTAINS
            System.out.println("¿Exists Pedro, Id = 20? " + list.contains(new Student("20")) );
            System.out.println("¿Exists Paula, Id = 4? " + list.contains(new Student("4")) );
            System.out.println("¿Exists Carlos, Id = 5? " + list.contains(new Student("5")) );
            System.out.println("¿Exists Carlos, Id = 8? " + list.contains(new Student("8")) );

            System.out.println("\n");

            //PROBAMOS GET NODE
            for (int i = 1; i <= list.size(); i++) {
                System.out.println("The element in position " + i + " is: " + list.getNode(i).data);
            }

            //PROBAMOS INDEX OF
            Student studentToSeach1 = new Student("2");
            Student studentToSeach2 = new Student("100");
            System.out.println("\n");
            System.out.println("The student Carlos with Id=8 is in position: " + list.indexOf(studentToSeach1));
            System.out.println("The student Carlos with Id=100 is in position: " + list.indexOf(studentToSeach2));

            System.out.println("\n");

            //PROBAMOS REMOVE
            Student studentSearched1 = new Student("1");
            Student studentSearched2 = new Student("3");
            Student studentSearched3 = new Student("5");
            if (list.contains(studentSearched1)) {
                list.remove(studentSearched1);
                System.out.println("The student with ID = [" + studentSearched1.getId() + "] has been deleted");
            }
            if (list.contains(studentSearched2)) {
                list.remove(studentSearched2);
                System.out.println("The student with ID = [" + studentSearched2.getId() + "] has been deleted");
            }
            if (list.contains(studentSearched3)) {
                list.remove(studentSearched3);
                System.out.println("The student with ID = [" + studentSearched3.getId() + "] has been deleted");
            }
            System.out.println(list);

            //PROBAMOS GET LAST Y GET PREV
            System.out.println(list.getLast());
            System.out.println(list.getPrev(studentToSeach1));
            //PROBAMOS GET NEXT
            System.out.println(list.getNext(studentToSeach1));

            //PROBAMOS REMOVE LAST
            System.out.println("\n");
            System.out.println("LAST ELEMENT: "+ list.removeLast());
            System.out.println("\n" + list);


        } catch (ListException e) {
            throw new RuntimeException(e);
        }

    }
<<<<<<< HEAD
    void test2(){
=======

    @Test
    void testSort() {
        SinglyLinkedList list = new SinglyLinkedList();
        list.add(new Student("5", "Pedro", 24, "Alajuela"));
        list.add(new Student("2", "Carlos", 22, "San Jose"));
        list.add(new Student("4", "Paula", 18, "Turrialba"));
        list.add(new Student("1", "Maria", 20, "Cartago"));

        System.out.println("Before sort:");
        System.out.println(list);

        try {
            list.sort();
            System.out.println("After sort:");
            System.out.println(list);
        } catch (ListException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    @Test
    void testAddInSortedList() {
        SinglyLinkedList sortedList = new SinglyLinkedList();
        sortedList.addInSortedList(new Student("3", "Laura", 20, "Paraiso"));
        sortedList.addInSortedList(new Student("1", "Ana", 19, "Heredia"));
        sortedList.addInSortedList(new Student("4", "Pedro", 25, "Cartago"));
        sortedList.addInSortedList(new Student("2", "Carlos", 22, "Alajuela"));

        System.out.println("List with addInSortedList:");
        System.out.println(sortedList);
    }



    public void test2(){
>>>>>>> 761dbc6348733c10c6be4bbd9f6db08c0512aae6
        SinglyLinkedList list = new SinglyLinkedList();
        list.addFirst(20);
        list.addFirst(10);
        list.addFirst(30);

        list.add(70);
        System.out.println(list);

        //ES LA ULTIMA CAPA POR ESO USAR TRY & CATCH
        //NO HAY MAS ALLA - SE USA EN LA PARTE FRONT END
        try {
            System.out.println("List size: " + list.size());
            System.out.println("Removed first item: " + list.removeFirst());
            System.out.println("List size: " + list.size() + "\n" + list);

            for (int i = 0; i < 50; i++) {
                list.add(util.Utility.random(50));
            }

            System.out.println("\n" + list + "\n");

            for (int i = 0; i < 10; i++){
                int value = util.Utility.random(50);
                System.out.println(list.contains(value)
                        ? "The element: ["  + value + "] exist in the list" + " - Index: " + list.indexOf(value)
                        : "The element: [" + value + "] doesn't exist in the list");

                //PROBAMOS REMOVE
                if (list.contains(value)) {
                    list.remove(value);
                    System.out.println("The element[" + value + "] has been deleted");
                }
            }

            System.out.println(list);



        } catch (ListException e) {
            throw new RuntimeException(e);
        }


    }

}