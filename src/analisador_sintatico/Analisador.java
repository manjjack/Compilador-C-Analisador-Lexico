/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package analisador_sintatico;

import analisador_lexico.Analex2;
import analisador_lexico.Token;
import java.io.IOException;
import java.util.ArrayList;


/**
 *
 * @author jackson
 */
public class Analisador {
    
    Analex2 analisadorLexico = new Analex2();
    ArrayList<Token> tokens = new ArrayList<>();
    Token token = new Token();
    ArrayList<Sintatico> sintaticos = new ArrayList<>();
    Gramatica gramatica = new Gramatica();
    
    
    public void analise(ArrayList<Sintatico> sintatico) {
       // System.out.println("LEXEMA\t\t\t\tLinha\t\t\t\tTOKEN\n");
        for (Sintatico sint : sintatico) {
            System.out.println(sint.getEr() + "|\t" +"Linha: " + sint.getLinha() + "|\t" + sint.getErro());
            System.out.println("____________________________________________________________________________________");
        }   
}
    
    
 public ArrayList analisador() throws IOException{
     
     tokens = analisadorLexico.analisador();

     gramatica.program(tokens, sintaticos);

     gramatica.tipo(tokens, sintaticos);


   
        return sintaticos;
    }

}
