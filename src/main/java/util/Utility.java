package util;

import java.text.DecimalFormat;
import java.util.Random;

public class Utility {
    private static final Random random;

    //contructor estatico, inicializador estatico
    static{
        //semilla para el random
        long seed = System.currentTimeMillis();
        random = new Random(seed);
    }

    public static int random(int bound){
        //return (int) Math.floor(Math.random()*bound); forma 1
        return 1+random.nextInt(bound);
    }

    public static void fill(int[] a){ //es estatico porque no se instancia la clase
        for(int i = 0; i < a.length; i++){
            a[i] = random(99);
        }
    }

    public static String format (long n){
        return new DecimalFormat("###,###,###,###").format(n);
    }

    public static int min(int x, int y) {
        return x<y ? x : y;
    }

    public static int max(int x, int y) {
        return x>y ? x : y;
    }

    public static String show(int[] a){
        String result = "";

        for (int i = 0; i < a.length; i++) {
            if (a[i] == 0) { // Mostrar cualquier 0, incluso al final
                result += "0 ";
            } else { // Mostrar los valores diferentes de 0
                result += a[i] + " ";
            }
        }

        return result;
    }
}
