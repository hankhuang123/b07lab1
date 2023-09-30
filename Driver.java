package winter_lab2;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;

public class Driver {
    public static void main(String [] args) {

        double[] c1 = {6, -2, 5};
        int[]    e1 = {0, 1, 3};
        Polynomial p1 = new Polynomial(c1, e1);
//30-10*1+25*3+36*4-12*5+30*7
        double[] c2 = {5, 6};
        int[]    e2 = {0, 4};
        Polynomial p2 = new Polynomial(c2, e2);

        System.out.println("testing add");
        Polynomial r1 = p1.add(p2);

        String coetemp1 = "";//暫存顯示的資料
        String expotemp1 ="";
        for (int i = 0; i < r1.coefficients.length; i++) {
            if(r1.coefficients[i]!=0) {
            	BigDecimal BDTemp = new BigDecimal(r1.coefficients[i]);
            	coetemp1 +=BDTemp.setScale(0) + " ";
            	expotemp1 += r1.exponents[i] + " ";
            }
        }
       // System.out.println("--------3");
        System.out.println(coetemp1+"\n"+expotemp1);
        //System.out.println("testing multiply");
        Polynomial r2 = p1.multiply(p2);
        //System.out.println("--------2");
        
        String coetemp = "";//暫存顯示的資料
        String expotemp ="";
        for (int i = 0; i < r2.coefficients.length; i++) {
            if(r2.coefficients[i]!=0) {
            	BigDecimal BDTemp = new BigDecimal(r2.coefficients[i]);
            	coetemp +=BDTemp.setScale(0) + " ";
            	expotemp += r2.exponents[i] + " ";
            }
        }
       // System.out.println("--------3");
        System.out.println(coetemp+"\n"+expotemp);

        System.out.println();

        try {
            r1.saveToFile("output.txt");
            r2.saveToFile("output.txt");

            File file = new File("output.txt");
            Polynomial p3 = new Polynomial(file);
            System.out.println(p3);
//            System.out.println(file); // toString()

        } catch (IOException ex1) {
            // TODO:
        }



    }
}
