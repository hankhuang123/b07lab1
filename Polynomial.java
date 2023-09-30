package winter_lab2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.Math;
import java.util.HashMap;
import java.util.Scanner;
import java.math.BigDecimal;


public class Polynomial {
	//MyClass myObj = new MyClass("/path/to/file.txt"); // 绝对路径
	//MyClass myObj = new MyClass("relative/path/to/file.txt"); // 相对路径
	double[] coefficients;
	int[] exponents;

	public Polynomial() {
		coefficients= new double[0];
		

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
	        	//if(p==0) {}        	
	        	//else {
	        		if(tempc[p]!=0) {
	        			String exString = "";
	        			if(tempc[p]>0 && p!=0) {exString = "+";}
	        			BigDecimal BDTemp = new BigDecimal(tempc[p]);
	        			//使用 Math.round(double*100.0)/100.0 將一個雙精度數四捨五入到小數點後兩位
	        			int temp1=(int) (Math.round(tempc[p]*100)/100);//change data style
	        			content+=exString+BDTemp.setScale(0); //不要顯示.0
	        			if(p!=0) {
	        				content+="x"+tempe[p];
	        			}
	        		}
	        	//	}
	        }
		System.out.println(content);
		this.coefficients = tempc;
		this.exponents= tempe;
	}

	public double evaluate(double x) {
		  double count=0.0;
		  for(int i = 0; i< this.coefficients.length; i++) {
		   double expr=0.0;
		   if(i==0) {
		    expr=1;
		   }else {
		    expr=x;
		    for(int a=1;a<i;a++) {
		     expr =expr*x;
		    }
		   }
		   count += this.coefficients[i]*expr;
		   
		  }
		  //System.out.println("result:"+count);
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
		if(len2>len1)maxint=len2;
		
		coe =new double[maxint];
		//coe = new double[Math.max(len1, len2)];

        for (int i = 0; i < len1; i++)
            coe[i] = this.coefficients[i];

        for (int i = 0; i < len2; i++)
            coe[i] += other.coefficients[i];
        
        int emaxint = Math.max(elen1, elen2);
        expo =new int[emaxint];
        for (int a = 0; a < elen1; a++)
        	expo[a] = this.exponents[a];

        for (int a = 0; a < elen2; a++)
        	if(other.exponents[a]!=0) {
        		expo[a] = other.exponents[a];
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
        for(int p=0;p<maxint;p++) {
        	if(coe[p]!=0) {
        		String exString = "";
        		if(coe[p]>0) {exString = "+";}
        		//System.out.print(exString+coe[p]+"x"+expo[p]);
        		}
        	}
        return new Polynomial(coe,expo);
		
	}
	/**Polynomial add tEST
	 * 	
	 * @param other
	 * @return
	 */
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
        			//System.out.println("tempexpo:"+tempexpo+",tempcoe:"+tempcoe+","+coe[tempexpo]+","+tempexpo);
        			//if(tempcoe!=0) {
        				coe[tempexpo]+=tempcoe;
        				expo[tempexpo]=tempexpo;
        				//System.out.println("tempexpo:"+tempexpo+",coe[tempexpo]:"+coe[tempexpo]+","+expo[tempexpo]);
            			
        			//}
        		}
        }
     
      //show polynomial 
        System.out.println("\n-----------multi polynomial:---------");
        for(int p=0;p<maxint;p++) {
        	//if(p==0) {
        	//	BigDecimal BDTemp = new BigDecimal(coe[p]);
        	//	System.out.print(BDTemp.setScale(0));      	
        	//}
        //	else {
        		if(coe[p]!=0) {
        			String exString = "";
        			if(coe[p]>0) {exString = "+";}
        			//System.out.print(exString+coe[p]+"x^"+expo[p]);
        			}
        	//	}
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
	
    public Polynomial(File file) throws IOException {
        Scanner scanner = new Scanner(file);
        String line = scanner.nextLine();
        scanner.close();
        // line => "5-3x2+7x8"
        // terms => ["5", "-3x2", "7x8"]
        // insert a '+' sign in-front of every '-' sign
        line = line.replace("-", "+-");
        String[] terms = line.split("\\+");

        this.coefficients = new double[terms.length];
        this.exponents = new int[terms.length];

        for (int i = 0; i < terms.length; i++) {          //            ceo   exp
            String[] ceo_exp = terms[i].split("x"); // "-3x2" => ["-3", "2"]
            this.coefficients[i] = Double.parseDouble(ceo_exp[0]);
            this.exponents[i] = (ceo_exp.length == 1) ? 0 : Integer.parseInt(ceo_exp[1]);
        }
    }
	
	    public void saveToFile(String filename) throws IOException {
	        // TODO: use printStream ?
	        FileWriter writer = new FileWriter(filename);

	        String line = "";


	        for (int i = 0; i < this.coefficients.length; i++) {
	            if (coefficients[i]!=0) {
	            	line += this.coefficients[i] + "x" + this.exponents[i] + "+";
	            }

	            
	        }

	        line += "FUCK";

	        line = line
	                .replace("+-", "-")   //
	                .replace("x0", "")      //
	                //.replace("x1", "x")     // TODO: 問一下 這個要不要加
	                .replace(".0", "")  //
	                .replace("+FUCK", "");  // to remove the last '+' sign

	        writer.write(line);
	        writer.close();
	    }
	    
}

