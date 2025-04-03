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

        System.out.println(list);
        try {
            System.out.println(list.contains(new Student("3")) );


        } catch (ListException e) {
            throw new RuntimeException(e);
        }

    }
    public void test2(){
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