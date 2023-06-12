import java.io.File;
import java.io.IOException;
import java.lang.Math;
import java.util.HashMap;
import java.util.Scanner;
import java.io.*;
import java.util.List;
import java.util.ArrayList;


public class Polynomial {
	private static String filePath="d:/java/b07lab2.txt";
	//MyClass myObj = new MyClass("/path/to/file.txt"); // 绝对路径
	//MyClass myObj = new MyClass("relative/path/to/file.txt"); // 相对路径
	double[] coefficients;
	int[] exponents;

	public Polynomial() {
		coefficients= new double[1];
		exponents=new int[0];
		

	}
	public Polynomial(double[] coefficients,int[] exponents ) {
		HashMap pmap= new HashMap();
		int emax=0;
		for(int c=0;c<coefficients.length;c++) {
			double dint = coefficients[c];
			int eint = exponents[c];
			if(eint>emax)emax=eint;
			//System.out.println(dint+","+eint+","+c);
			pmap.put(eint,dint);
		}
		emax =emax+1;//位數會多一個X^0次方
		double[] tempc= new double[emax];
		int[] tempe= new int[emax];
		for(int m=0;m<emax;m++) {
			if(pmap.get(m)!=null) {
				tempc[m]=Double.parseDouble(pmap.get(m).toString());
			}else {tempc[m]=0;}
			tempe[m]=m;
			//System.out.println(tempc[m]+","+tempe[m]);
		}
		String content="";
		 for(int p=0;p<emax;p++) {
	        	if(p==0) {System.out.print(tempc[p]);}        	
	        	else {
	        		if(tempc[p]!=0) {
	        			String exString = "";
	        			if(tempc[p]>0) {exString = "+";}
	        			content+=exString+tempc[p]+"x^"+tempe[p];
	        			}
	        		}
	        }
		System.out.println(content);
		this.coefficients = tempc;
		this.exponents= tempe;
	}

	public double evaluate(double x) {
		  double count=0.0;
		//   System.out.println(this.coefficients.length);
			for (int i = 0; i < this.coefficients.length; i++)
				count += this.coefficients[i] * Math.pow(x, this.exponents[i]);
			return count;
	}
	
	/**Polynomial add tEST
	 * 	
	 * @param other
	 * @return
	 */
	public Polynomial add(Polynomial other) {
		double[] coe;
		int[] expo;
	
		int len1 = this.coefficients.length;
		int len2 = other.coefficients.length;
		int elen1 = this.exponents.length;
		int elen2 = other.exponents.length;
	
		int maxint = len1;
		if (len2 > len1)
			maxint = len2;
	
		List<Double> updatedCoefficients = new ArrayList<>();
		List<Integer> updatedExponents = new ArrayList<>();
	
		for (int i = 0; i < maxint; i++) {
			double coefficient = 0.0;
			if (i < len1)
				coefficient += this.coefficients[i];
			if (i < len2)
				coefficient += other.coefficients[i];
	
			if (coefficient != 0) {
				updatedCoefficients.add(coefficient);
				updatedExponents.add(i);
			}
		}
	
		coe = new double[updatedCoefficients.size()];
		expo = new int[updatedExponents.size()];
	
		for (int i = 0; i < updatedCoefficients.size(); i++) {
			coe[i] = updatedCoefficients.get(i);
			expo[i] = updatedExponents.get(i);
		}
	
        
        System.out.println("-----------add:---------");
        System.out.print("coefficients:");
        for(int p=0;p<coe.length;p++) {
        	System.out.print(coe[p]+",");
        }
        System.out.print("\nexponent:");
        for(int p=0;p<expo.length;p++) {
        	System.out.print(expo[p]+",");
        }
      //show polynomial 
        System.out.println("\n-----------add polynomial:---------");
        // for(int p=0;p<maxint;p++) {
        // 	if(p==0) {System.out.print(coe[p]);}        	
        // 	else {
        // 		if(coe[p]!=0) {
        // 			String exString = "";
        // 			if(coe[p]>0) {exString = "+";}
        // 			// System.out.print(exString+coe[p]+"x^"+expo[p]);
        // 			}
        // 		}
        // }
        return new Polynomial(coe,expo);

		
	}
	public Polynomial multiply(Polynomial other) {
		double[] coe;
		int[] expo;
	/*	6+5x-2x^3
		9-2x
		54+33x-10x^2-18x^3+4x^4
		*/
		int len1 = this.coefficients.length;
		int len2 = other.coefficients.length;
		int elen1 = this.exponents.length;
		int elen2 = other.exponents.length;
		double[] coe1 =this.coefficients;
		double[] coe2 =other.coefficients;
		int[] expo1 =this.exponents;
		int[] expo2 =other.exponents;
		//max=exponent max plus
		int maxint = this.exponents[elen1-1]+other.exponents[elen2-1]+1;
		
		coe =new double[maxint]; //final coe
		expo = new int[maxint];//final expo
        for (int i = 0; i < len1; i++) {
        		for(int j=0;j<len2;j++) {
        			double tempcoe=coe1[i]*coe2[j];
        			int tempexpo = expo1[i]+expo2[j];
        			// System.out.println("tempexpo:"+tempexpo+",tempcoe:"+tempcoe+","+coe[tempexpo]+","+tempexpo);
        			if(tempcoe!=0) {
        				coe[tempexpo]+=tempcoe;
        				expo[tempexpo]=tempexpo;
        				// System.out.println("tempexpo:"+tempexpo+",coe[tempexpo]:"+coe[tempexpo]+","+expo[tempexpo]);
            			
        			}
        		}
        }

      //show polynomial 
        System.out.println("\n-----------multi polynomial:---------");
        for(int p=0;p<maxint;p++) {
        	if(p==0) {System.out.print(coe[p]);      	
        	}
        	else {
        		if(coe[p]!=0) {
        			String exString = "";
        			if(coe[p]>0) {exString = "+";}
        			//System.out.print(exString+coe[p]+"x^"+expo[p]);
        			}
        		}
        }
        return new Polynomial(coe,expo);
				
	}

	public boolean hasRoot(double x) {
		boolean result = false;
		double root = this.evaluate(x);
		if(root==0) {
			result=true;
		}
		return result;
	
	}
	
// 	   public Polynomial  fileinput(File file) {
// 		   double[] c= {};
//            int[] e= {};
// 		    Polynomial px = new Polynomial(c,e);
// 		    String result="";
// 	    	file = new File(filePath);
	    	
// 			//System.out.println(p.fileinput(file));
// 	        try (FileInputStream fistream = new FileInputStream(file)) {
// 	            int data;
// 	            String content="";
// 	            while ((data = fistream.read()) != -1) {
// 	            	//5-3x2+7x8
// 	                //System.out.println((char) data+",");
// 	                content+=(char)data;
// 	            }
// 	            //System.out.println(content);
// 	            //String[] edata =content.split(content, data);
// 	            //System.out.println(edata[0]);
// 	            String[] numbers = content.replaceAll("X","x").replaceAll("-",";-").replaceAll("\\+",";+").split(";");
// 	           // System.out.println(numbers.length);
// 	            c= new double[numbers.length];
// 	            e= new int[numbers.length];
// 	            int emax =numbers.length;
// 	            for (int n=0;n<numbers.length;n++) {
// 	            	//System.out.println("numbers:"+numbers[n]);
// 	            	if(n==0 && numbers[n].indexOf("x")==-1) {
// 	            		c[n]=Double.parseDouble(numbers[n]);
// 	            		e[n]=0;
// 	            		//System.out.println("coefficients:"+c[n]+",exponents:"+e[n]);
// 	            	}else {
// 	            		String[] temp =numbers[n].split("x");
// 	            		c[n]=Double.parseDouble(temp[0]);
// 	            		e[n]=Integer.parseInt(temp[1]);
// 	            		//System.out.println("coefficients:"+c[n]+",exponents:"+e[n]);
	            		
// 	            	}
	            		
// 	            }
// 	            coefficients=c;
// 	        	exponents=e;
// 	        	//show polynomial 
// 	        	 for(int p=0;p<emax;p++) {
// 	             	if(p==0) {System.out.print(c[p]);}        	
// 	             	else {
// 	             		if(c[p]!=0) {
// 	             			String exString = "";
// 	             			if(c[p]>0) {exString = "+";}
// 	             			System.out.print(exString+c[p]+"x^"+e[p]);
// 	             			}
// 	             		}
// 	        	 }
// 	        } catch (IOException ex) {
// 	            ex.printStackTrace();
// 	        }
// 	       return px;
// 	    }
// 	   public void saveToFile(File file,String content) {
// 		   file = new File(filePath);
// 	        try (FileOutputStream fossss = new FileOutputStream(file)) {
// 	            fossss.write(content.getBytes());
// 	        } catch (IOException e) {
// 	            e.printStackTrace();
// 	        }
// 	    }
// }



public Polynomial(File filename) throws IOException {
    Scanner scan = new Scanner(filename);
    String code = scan.nextLine();
    scan.close();

    code = code.replace("-", "+-");
    String[] terms = code.split("\\+");

    this.coefficients = new double[terms.length];
    this.exponents = new int[terms.length];

    int k = 0;
    while (k < terms.length) {
        String[] ceoandexp = terms[k].split("x"); // "5x2" => ["5", "2"]
        this.coefficients[k] = Double.parseDouble(ceoandexp[0]);
        this.exponents[k] = (ceoandexp.length == 1) ? 0 : Integer.parseInt(ceoandexp[1]);
        k++;
    }
}

public void saveToFile(String file) throws IOException {
    FileWriter tryer = new FileWriter(file);
    String code = "";
    int i = 0;
    while (i < this.coefficients.length) {
        code += this.coefficients[i] + "x" + this.exponents[i] + "+";
        i++;
    }
    code += "REPLACEMENT_STRING";
    code = code
        .replace("+-", "-")     //
        .replace("x0", "")      //
        .replace(".0", "")      //
        .replace("+REPLACEMENT_STRING", "");  // to remove the last '+' sign
    tryer.write(code);
    tryer.close();
}


}
