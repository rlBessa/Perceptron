package redeneural;
public class Perceptron {
    private double w1, w2, wb;
    private int bias;
    private double taxaApren;

    public Perceptron(double w1, double w2, double wb) {
        this.w1 = w1;
        this.w2 = w2;
        this.wb = wb;
        this.bias = 1;
        this.taxaApren = 1;
    }
    
    public int FeedFoward(int n1,int n2){
        int sinal=0;
        double soma=0;
        soma=this.getW1()*n1 + this.getW2()*n2 + this.getWb()*this.getBias();
        if(soma>0){
            sinal=1;
        }
        return sinal;
    }
    
    public void BackPropagation(int n1,int n2, int erro){
        this.setW1(this.getW1()+(erro*this.taxaApren*n1));
        this.setW2(this.getW2()+(erro*this.taxaApren*n2));
        this.setWb(this.getWb()+(erro*this.taxaApren*this.bias));
    }

    public double getTaxaApren() {
        return taxaApren;
    }

    public void setTaxaApren(double taxaApren) {
        this.taxaApren = taxaApren;
    }
    
    
    public double getW1() {
        return w1;
    }

    public void setW1(double w1) {
        this.w1 = w1;
    }

    public double getW2() {
        return w2;
    }

    public void setW2(double w2) {
        this.w2 = w2;
    }

    public double getWb() {
        return wb;
    }

    public void setWb(double wb) {
        this.wb = wb;
    }

    public int getBias() {
        return bias;
    }

    public void setBias(int bias) {
        this.bias = bias;
    }
    
    public String Pesos(boolean aleatorio){
        if(aleatorio){
            return "Aleatório";
        }
        else return "Padrão (0, 0, 0)";
    }
    
}
