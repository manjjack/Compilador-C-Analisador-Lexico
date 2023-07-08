/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analisador_semantico;

/**
 *
 * @author user
 */
public class Semantico {
    
    public Semantico(){ } 
    private String er;

    public String getEr() {
        return er;
    }

    public void setEr(String er) {
        this.er = er;
    }

    public int getLinha() {
        return linha;
    }

    public void setLinha(int linha) {
        this.linha = linha;
    }

    public String getErro() {
        return erro;
    }

    public void setErro(String erro) {
        this.erro = erro;
    }
    private int linha;
    private String erro;

    public Semantico(int linha, String erro) {
        this.er = "Erro de Semantico";
        this.linha = linha;
        this.erro = erro;

    }
}
