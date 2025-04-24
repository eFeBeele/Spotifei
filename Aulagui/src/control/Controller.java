/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;
import view.Janela;
import model.Calcular;
/**
 *
 * @author unifflopes
 */
public class Controller {
    private Janela janela;
    private String operando = "";
    private String operacao = "";
    private double numero01;
    public Controller (Janela janela){
        this.janela = janela;
    }
    public void calcular (){
        double numero02 = Double.parseDouble(this.operando);
        System.out.println("Chegou aqui"+numero01+operacao+numero02);
        if(operacao.equals("+")){
            janela.getTxtVisor().setText(Double.toString(Calcular.soma(numero01,numero02)));
        }
        else if(operacao.equals("-")){
            janela.getTxtVisor().setText(Double.toString(Calcular.sub(numero01,numero02)));
        }
        else if(operacao.equals("/")){
            janela.getTxtVisor().setText(Double.toString(Calcular.div(numero01,numero02)));
        }
        else if(operacao.equals("*")){
            janela.getTxtVisor().setText(Double.toString(Calcular.mult(numero01,numero02)));
        }
    }
    public void juntarNumeros(int numero){
        operando = operando + numero;
        janela.getTxtVisor().setText(operando);
    }
    public void selecionaOperacao(String operacao){
        this.operacao = operacao;
        this.numero01 = Double.parseDouble(operando);
        this.operando = "";
        this.janela.getTxtVisor().setText(operando);
        System.out.println(this.numero01);
    }
}
