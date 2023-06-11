import java.io.File;
import java.io.IOException;
//HASH
public class Driver {
	 
	public static void main(String [] args) {
		//System.out.println(p.evaluate(3));
		Polynomial p = new Polynomial();
		// System.out.println("evaluate:"+p.evaluate(3));
		double [] c1 = {6,-2,5};
		int [] e1 = {0,1,3};
		Polynomial p1 = new Polynomial(c1,e1);
		System.out.println("-------------function1/2");
		double [] c2 = {5,6};
		int [] e2 = {0,4};
		Polynomial p2 = new Polynomial(c2,e2);
		Polynomial s = p1.add(p2);
		Polynomial m = p1.multiply(p2);
		System.out.println("\ns(0.1) = " + s.evaluate(0.1));

		try {
            s.saveToFile("result.txt");

            File file = new File("result.txt");
            Polynomial p3 = new Polynomial(file);

            System.out.println(p3); // toString()

        } catch (IOException ex1) {
		}   
		if(s.hasRoot(1))
		System.out.println("\n1 is a root of s");
		else
		System.out.println("\n1 is not a root of s");
		}
}
