/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package analisador_lexico;

/**
 *
 * @author hp
 */
public class Token {
    
    private String lexema;
    private String token;
    private int linha;
    
    public Token(){ }
    
    public Token(String lexema, String token, int linha){
        this.lexema = lexema;
        this.token = token;
        this.linha = linha;
    }

    public String getLexema() {
        return lexema;
    }

    public void setLexema(String lexema) {
        this.lexema = lexema;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getLinha() {
        return linha;
    }

    public void setLinha(int linha) {
        this.linha = linha;
    }
    
}
