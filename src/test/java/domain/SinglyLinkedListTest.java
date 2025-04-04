package domain;

import org.junit.jupiter.api.Test;

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
    void probarCountNamesYFindNames(){

        SinglyLinkedList list = new SinglyLinkedList();


        list.add(new Student("001", "Carlos", 20, "San José"));
        list.add(new Student("002", "Kristel", 30, "Cartago"));
        list.add(new Student("003", "Carlos", 40, "San vito"));
        list.add(new Student("004", "Luis",50, "Perez zeledon"));
        list.add(new Student("005", "Carlos", 83, "Heredia"));

        System.out.println("Carlos aparece en la lista: "+ countNames(list,"Carlos")+" veces");
        System.out.println("Ana aparece en la lista: "+ findNames(list,"Ana"));
        System.out.println("Kristel aparece en la lista: "+ findNames(list,"Kristel"));
    }

    int countNames(SinglyLinkedList list, String name){

        int count = 0;
        int index = 1;
        Node nodeData = null;
        while (true) {
            try {
                nodeData = list.getNode(index);
            } catch (ListException e) {
                throw new RuntimeException(e);
            }
            if (nodeData == null) break;

            if (util.Utility.compare(nodeData.data,name) == 0) {
                count++;
            }
            index++;
        }

        return count;
    }
    boolean findNames(SinglyLinkedList list,String name){

        boolean insertado= false;
        Node nodeData= null;
        int index=1;
        while (true){
            try {
                nodeData= list.getNode(index);
            } catch (ListException e) {
                throw new RuntimeException(e);
            }
            if(nodeData==null)break;

            if(util.Utility.compare(nodeData.data,name)==0)
                insertado=true;
            index++;

        }

        return insertado;
    }


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