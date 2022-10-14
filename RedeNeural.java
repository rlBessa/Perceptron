package redeneural;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JOptionPane;

public class RedeNeural {
    public static void main(String[] args) {
        int i=0, desejado, obtido, erro, menu=0, n1, n2, config=0;
        int []vetor;
        float acertos=0, epocas=0;
        boolean peso_aleatorio = false;
        String aux;
        
        Random aleatorio = new Random();
        Object[] opcoes_menu = {"Treinar Algoritmo", "Testar Algoritmo", "Configurações", "Sair"};
        Object[] opcoes_config = {"Pesos Iniciais", "Valor do BIAS", "Ordem da Lista", "Taxa de Aprendizado","Voltar"};
        
        
        double taxa_acertos=1; //taxa de acertos para controle do loop
        
        //inicializando lista de pessoas
        ArrayList<Dado> pessoas = new ArrayList<>(); 
            
        //iniciar perceptron com pesos 0, 0, 0
        Perceptron perceptron = new Perceptron(0, 0, 0);
        
       
        //adicionar as pessoas à lista 
        pessoas.add(new Dado("Bach", 0, 0, 0));
        pessoas.add(new Dado("Beethoven", 0, 1, 0));
        pessoas.add(new Dado("Einstein", 1, 0, 1));
        pessoas.add(new Dado("Kepler", 1, 1, 1));
        
        
        //determinar a ordem em que serão executados os cientistas
        vetor = new int[]{3,2,1,0};
        
        
        
        while(menu!=4 && menu!=3){
            menu=JOptionPane.showOptionDialog(null , "Selecione uma opção:" , "Rede Neural - Perceptron" , JOptionPane.YES_NO_CANCEL_OPTION , JOptionPane.INFORMATION_MESSAGE  ,null , opcoes_menu , 0);
            switch(menu){
                case 0 -> {
                    acertos=0;
                    epocas=0;
                    if(peso_aleatorio){
                        perceptron = new Perceptron(aleatorio.nextDouble(),aleatorio.nextDouble(),aleatorio.nextDouble());
                    }
                    else{
                        perceptron = new Perceptron(0, 0, 0);
                    }
                    System.out.println("Pesos de entrada para o FeedFoward:\nWb = " + perceptron.getWb() + "\nW1 = " + perceptron.getW1() + "\nW2 = " + perceptron.getW2() + "\n");
                    while(acertos<taxa_acertos){
                        epocas+=1;
                        acertos+=0.25;
                        desejado = pessoas.get(vetor[i]).getEsperado();
                        obtido = perceptron.FeedFoward(pessoas.get(vetor[i]).getN1(), pessoas.get(vetor[i]).getN2());
                        erro =  desejado - obtido;
                        if(erro!=0){
                            perceptron.BackPropagation(pessoas.get(vetor[i]).getN1(), pessoas.get(vetor[i]).getN2(), erro);
                            acertos-=0.25;
                        }
                        System.out.println("Nome: " + pessoas.get(vetor[i]).getNome());
                        if(obtido==1){
                            System.out.println("Saída: " + "CIENTISTA");
                        }
                        else{
                            System.out.println("Saída: " + "COMPOSITOR");
                        }
                        System.out.println("Erro: " + erro);
                        if(erro!=0){
                            System.out.println("\nPesos após BackPropagation:\nWb = " + perceptron.getWb() + "\nW1 = " + perceptron.getW1() + "\nW2 = " + perceptron.getW2());
                        }
                        System.out.println("\n-------------------------------------\n");
                        System.out.println("Pesos de entrada para o FeedFoward:\nWb = " + perceptron.getWb() + "\nW1 = " + perceptron.getW1() + "\nW2 = " + perceptron.getW2());
                        
                        if(i==3){
                            i=-1;
                            if(acertos<taxa_acertos){
                                acertos=0;
                            }
                        }
                        i++;
                    } 
                    JOptionPane.showMessageDialog(null, 
                            "O algoritmo foi treinado!\n\nIterações:  "
                            + epocas
                            + "\nÉpocas:  "
                            + epocas/pessoas.size()
                            + "\nPesos:   [Wb] = "
                            + perceptron.getWb()
                            + "\n                [W1] = "
                            + perceptron.getW1()
                            + "\n                [W2] = "
                            + perceptron.getW2()
                            + "\n\n");
                }
                case 1 -> {
                    if(epocas==0){
                        JOptionPane.showMessageDialog(null, "O algoritmo ainda não foi treinado!\n Faça o treinamento antes de utilizar esta função.");
                    }
                    else{
                        n1=Integer.parseInt(JOptionPane.showInputDialog("Digite o valor da variável N1:")) ; 
                        n2=Integer.parseInt(JOptionPane.showInputDialog("Digite o valor da variável N2:")) ;
                        if(perceptron.FeedFoward(n1, n2)==1){
                            JOptionPane.showMessageDialog(null, "N1 = " + n1 + "\nN2 = " + n2 + "\nCientista");
                        }
                        else JOptionPane.showMessageDialog(null, "N1 = " + n1 + "\nN2 = " + n2 + "\nCompositor");
                    }
                    
                }
                case 2 -> {
                    config=0;
                    while(config!=4){
                        
                        config=JOptionPane.showOptionDialog(null , "Definição dos pesos iniciais:     [ "
                                                                + perceptron.Pesos(peso_aleatorio)
                                                                + " ]\n\nValor do BIAS:     [ "
                                                                + perceptron.getBias()
                                                                + " ]\n\nTaxa de Aprendizado:     [ "
                                                                + perceptron.getTaxaApren()
                                                                + " ]\n\nOrdem da Lista:  [ "
                                                                + pessoas.get(vetor[0]).getNome() + " >> "
                                                                + pessoas.get(vetor[1]).getNome() + " >> "
                                                                + pessoas.get(vetor[2]).getNome() + " >> "
                                                                + pessoas.get(vetor[3]).getNome() 
                                                                + " ]\n\n\nSelecione uma opção:\n\n" , "Configurações" , JOptionPane.YES_NO_CANCEL_OPTION , JOptionPane.INFORMATION_MESSAGE  ,null , opcoes_config , 0);
                        
                       
                        switch(config){
                            case 0 -> {
                            //tamanho da pop
                                aux=JOptionPane.showInputDialog("Digite o valor para o modo de definição\ndos pesos iniciais:\n1 - Padrão (0, 0, 0)\n2 - Aleatório\n") ;                                
                                if(aux!=null){
                                    if(Integer.parseInt(aux)==2){
                                        peso_aleatorio = true;
                                        JOptionPane.showMessageDialog(null, "Operação realizada com sucesso!\n");
                                    }
                                    else peso_aleatorio = false;
                                }
                                
                            }
                            case 1 -> {
                            //geracoes
                                aux=JOptionPane.showInputDialog("Digite o novo valor para o BIAS:");
                                if(aux!=null){
                                    perceptron.setBias(Integer.parseInt(aux));
                                    JOptionPane.showMessageDialog(null, "Operação realizada com sucesso!\n");
                                }
                            }
                            case 2 -> {
                                aux=JOptionPane.showInputDialog("Digite a ordem que deseja executar a lista (padrão 0123):\n0 - Bach\n1 - Beethoven\n2 - Einstein\n3 - Kepler\n");
                                if(aux!=null){                           
                                    
                                    vetor[0] = Integer.parseInt(aux)/1000;
                                    vetor[1] = (Integer.parseInt(aux)%1000-Integer.parseInt(aux)%100)/100;
                                    vetor[2] = (Integer.parseInt(aux)%100-Integer.parseInt(aux)%10)/10;
                                    vetor[3] = Integer.parseInt(aux)%10;
                                    JOptionPane.showMessageDialog(null, "Operação realizada com sucesso!\n");
                                }
                                
                            }
                            case 3 -> {
                                aux=JOptionPane.showInputDialog("Digite a taxa de aprendizado (entre 0 e 1):");
                                if(aux!=null){                           
                                    perceptron.setTaxaApren(Double.parseDouble(aux));
                                    JOptionPane.showMessageDialog(null, "Operação realizada com sucesso!\n");
                                }                 
                            }                           
                            default -> {
                                config=4;
                                break;
                            }
                        }
                    }
                }
                default -> menu=3;
            }
        }
        
        
        
    }
    
}


// taxa de aprendizado - padrao 1
// ordem das pessoas - padrao 3,2,1,0
// peso inicial - padrao 0, 0, 0
// bia - padrao 1