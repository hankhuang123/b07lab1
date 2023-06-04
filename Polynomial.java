public class Polynomial {
	
	double[] coefficients;
	
	
	public Polynomial() {
		coefficients= new double[0];

	}
	
	public Polynomial(double[] coefficients) {
		this.coefficients = coefficients;
	}

	public Polynomial add(Polynomial a) {
		double[] var;
		
		int long1 = this.coefficients.length;
		int long2 = a.coefficients.length;
		
		int maxint = long1;
		if(long2>long1)
			maxint=long2;
		var =new double[maxint];
        for (int i = 0; i < long1; i++)
            var[i] = this.coefficients[i];
        for (int i = 0; i < long2; i++)
            var[i] += a.coefficients[i];
        return new Polynomial(var);
		
	}
	
	public double evaluate(double value) {
		double count=0.0;
		for(int i = 0; i< this.coefficients.length; i++) {
			double expr=0.0;
			if(i==0) {
				expr=1;
			}else {
				expr=value;
				for(int a=1;a<i;a++) {
					expr =expr*value;
				}
			}
			count += this.coefficients[i]*expr;
			
		}
		return count;
		}

	
	public boolean hasRoot(double has) {
		boolean answer = false;
		double root = this.evaluate(has);
		if(root==0) {
			answer=true;
		}
		return answer;
	
	}
}
