/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package analisador_sintatico;

/**
 *
 * @author jackson
 */
public class Sintatico {

    private String er;
    private int linha;
    private String erro;

    public Sintatico(int linha, String erro) {
        this.er = "Erro de Sintaxe";
        this.linha = linha;
        this.erro = erro;

    }

    public String getEr() {
        return er;
    }

    public int getLinha() {
        return linha;
    }

    public String getErro() {
        return erro;
    }

    public Sintatico() {  }

}
