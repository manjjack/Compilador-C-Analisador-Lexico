/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package analisador_sintatico;


import analisador_lexico.Token;
import java.util.ArrayList;

/**
 *
 * @author jackson
 */

public class Gramatica {
    
    Sintatico sintatico = new Sintatico();
    //ArrayList<Sintatico> sintaticos = new ArrayList<>();
   
    public int verificaToken( String Token, ArrayList<Token> tokens){

        int i = 0;
        for (Token tok : tokens) {
            if (Token.equals(tok.getToken())) {
                i++;
            }
        }

        return i;
    }

    public void program(ArrayList<Token> token, ArrayList<Sintatico> sintaticos) {
        
        int i = 0;
        boolean valida = false;
        
        while (i < token.size()) {

            if (token.get(i).getToken().equals("TOK_AST") || token.get(i).getToken().equals("include")
                    || token.get(i).getToken().equals("Tok_menor") || token.get(i).getToken().equals("Biblioteca")
                    || token.get(i).getToken().equals("Tok_maior")) {
                
                if (token.get(i).getToken().equals("TOK_AST")) {

                        valida = true;
                        i++;
                    
                } else {
                    sintatico = new Sintatico(token.get(i).getLinha(), " Esperava um #");
                    sintaticos.add(sintatico);

                }

                if (token.get(i).getToken().equals("include")) {
                    valida = true;
                    i++;

                } else {

                    sintatico = new Sintatico(token.get(i).getLinha(), " Esperava um include");
                    sintaticos.add(sintatico);

                }

                if (token.get(i).getToken().equals("Tok_menor")) {
                    valida = true;
                    i++;

                } else {

                    sintatico = new Sintatico(token.get(i).getLinha(), " Esperava um <");
                    sintaticos.add(sintatico);

                }

                if (token.get(i).getToken().equals("Biblioteca")) {

                    valida = true;
                    i++;

                } else {

                    sintatico = new Sintatico(token.get(i).getLinha(), " Esperava um Biblioteca");
                    sintaticos.add(sintatico);

                }

                if (token.get(i).getToken().equals("Tok_maior")) {
                    valida = true;

                } else {

                    sintatico = new Sintatico(token.get(i).getLinha(), " Esperava um >");
                    sintaticos.add(sintatico);
                }

            }else{
                        
                    valida = false;
            }
            i++;
        }
        
        if (!(valida)) {
            sintatico = new Sintatico(token.get(0).getLinha()-1, " Esperava uma declaracao de biblioteca");
            sintaticos.add(sintatico);
        }



    }

    public void decalaraco(ArrayList<Token> token) {

    }

    public void tipo(ArrayList<Token> token, ArrayList<Sintatico> sintaticos) {
        int i = 0;
        while (i < token.size()) {

            if (token.get(i).getToken().equals("int") || token.get(i).getToken().equals("float") || token.get(i).getToken().equals("char")
                    || token.get(i).getToken().equals("void") || token.get(i).getToken().equals("double") || token.get(i).getToken().equals("struct")) {
                i++;
                boolean valida = id(token, i, sintaticos);
                if (valida) {
                    i++;
                    varFinal(token, i, sintaticos);
                } else {
                    sintatico = new Sintatico(token.get(i).getLinha(), " Esperava um id");
                    sintaticos.add(sintatico);
                }

            } else {

            }
            i++;
        }

    }

    public boolean id(ArrayList<Token> token, int aux, ArrayList<Sintatico> sintaticos) {
        boolean valida = false;
        while (aux < token.size()) {

            if (token.get(aux).getToken().equals("TOK_ID")) {

                valida = true;
                int i = aux - 1;
                if ((token.get(i).getToken().equals("int") || token.get(i).getToken().equals("float") || token.get(i).getToken().equals("char")
                        || token.get(i).getToken().equals("void") || token.get(i).getToken().equals("double") || token.get(i).getToken().equals("struct"))) {

                } else {
                    sintatico = new Sintatico(token.get(aux).getLinha(), " Esperava um tipo de dado");
                    sintaticos.add(sintatico);
                }
            }
            aux++;
        }

        return valida;
    }

    public void varFinal(ArrayList<Token> token, int i, ArrayList<Sintatico> sintaticos) {
        
        boolean valida = false;

        if (token.get(i).getToken().equals("TOK_APR") || token.get(i).getToken().equals("TOK_Inteiro")
                || token.get(i).getToken().equals("TOK_FPR") || token.get(i).getToken().equals("TOK_FPR")
                && !(token.get(i).getToken().equals("TOK_PNV"))) {

            if (token.get(i).getToken().equals("TOK_APR")) {
                i++;
            } else {
                 i--;
                sintatico = new Sintatico(token.get(i).getLinha(), " Esperava um [");
                sintaticos.add(sintatico);
            }

            if (token.get(i).getToken().equals("TOK_Inteiro")) {
                i++;

            } else {
                i--;
                sintatico = new Sintatico(token.get(i).getLinha(), " Esperava um inteiro entre [ ]");
                sintaticos.add(sintatico);

            }

            if (token.get(i).getToken().equals("TOK_FPR")) {
                i++;

            } else {
                 i--;
                sintatico = new Sintatico(token.get(i).getLinha(), " Esperava um inteiro entre  ]");
                sintaticos.add(sintatico);

            }

        }else if( token.get(i).getToken().equals("TOK_PNV") ){
            
        }else if( token.get(i).getToken().equals("TOK_equals")){
            
            
        }
        else{
             
            sintatico = new Sintatico(token.get(i).getLinha(), " Esperava um ; ou uma declaracao");
            sintaticos.add(sintatico);
        }

    }

    public int functionParm( ArrayList<Token> token,int i, ArrayList<Sintatico> sintaticos){
           i++;
          boolean valida = false;
          
        if (token.get(i).getToken().equals("TOK_AP")) {
            valida = true;
            i++;
           
        } else {

            sintatico = new Sintatico(token.get(i).getLinha(), " Esperava um (");
            sintaticos.add(sintatico);
           
        }

        if (token.get(i).getToken().equals("TOK_FP")) {
            valida = true;
 
            i++;
        } else {
            valida = true;
 

            sintatico = new Sintatico(token.get(i).getLinha(), " Esperava um )");
            sintaticos.add(sintatico);
        }

    
        return i;
    }

}
