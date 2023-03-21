package redeneural;
public class Dado {
    private  String nome;
    private  int n1;
    private  int n2;
    private  int esperado;

    public Dado(String nome, int n1, int n2, int saida) {
        this.nome=nome;
        this.n1 = n1;
        this.n2 = n2;
        this.esperado = saida;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getN1() {
        return n1;
    }

    public void setN1(int n1) {
        this.n1 = n1;
    }

    public int getN2() {
        return n2;
    }

    public void setN2(int n2) {
        this.n2 = n2;
    }

    public int getEsperado() {
        return esperado;
    }

    public void setEsperado(int esperado) {
        this.esperado = esperado;
    }
    
        
    
}
