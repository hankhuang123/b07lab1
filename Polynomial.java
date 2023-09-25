public class Polynomial {

    double[] coefficients; // null

    public Polynomial() {
        coefficients = new double[1];
    }

    public Polynomial(double[] coefficients) {
        this.coefficients = coefficients;
    }

    public double evaluate(double x) {
        double ret = 0.0;
        for (int i = 0; i < this.coefficients.length; i++)
            ret += this.coefficients[i] * Math.pow(x, i);
        return ret;
    }

    public Polynomial add(Polynomial other) {
        double[] cf;

        int long1 = this.coefficients.length;
        int long2 = other.coefficients.length;

        cf = new double[Math.max(long1, long2)];

        int i = 0;
        while (i < long1) {
            cf[i] = this.coefficients[i];
            i++;
        }

        i = 0;
        while (i < long2) {
            cf[i] = cf[i] + other.coefficients[i];
            i++;
        }

        return new Polynomial(cf);
    }


    public boolean hasRoot(double x) {
        return evaluate(x) == 0.0;
    }


}