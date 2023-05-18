/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package analisador_lexico;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author jackson junior 
 */
public class Analex {
    
    private BufferedReader fileC;
    FileReaderJ file = new FileReaderJ();
    private int linha = 1;
    private char letraC;
    private int linhaFicheiro;
    
    
    
    public boolean palavraReservada(String pal) throws FileNotFoundException {

        ArrayList<String> palReservada = new ArrayList<>();
        file.getPalavrasReservadas(palReservada);// palavras reservadas
        for (String rs : palReservada) {
            if (rs.equals(pal)) {
                return true;
            }
        }
        return false;
    }
    
    public boolean comman(String com){
        char vog = '.';
        if(!(commanfinal(com))){
            return com.contains(String.valueOf(vog));
        }
       return false;
    }
    
    
   
  
    
    public boolean commanfinal(String com){
        
        int cont = com.length();
        return com.charAt( (cont -1)) == '.';
    }
    
    public void analise(ArrayList<Token> token){
        System.out.println("LEXEMA\t\t\t\tTOKEN\t\t\t\tLINHA\n" );
        for(Token tokens : token){
             System.out.println(tokens.getLexema() + "\t\t\t\t" + tokens.getToken() + "\t\t\t\t\t" + tokens.getLinha());
             System.out.println("____________________________________________________________________________________");
        }
    }
    
    public ArrayList analisador() throws FileNotFoundException, IOException{
         
       
        ArrayList<Token> token = new ArrayList<>();
        Token auxToken = new Token();

        String letra = " ";
        
        ArrayList<Character> codigoFonte = new ArrayList<>();
        ArrayList<Integer> linhaC = new ArrayList<>();
        ArrayList<Character> palavras = new ArrayList<>();
        
        int i = 0;
        int estado = 0;
        
        String join = "";
        
        boolean validar = true;
        FileReaderJ rd = new FileReaderJ();
        
        rd.getCodigoFonte(codigoFonte, linhaC);
        //char[] letra = palavras.toCharArray();

            for(int auxCont = 0; auxCont < codigoFonte.size() ;auxCont++ ) {
                
                //estado = 0;
                linha = linhaC.get(auxCont);
                letraC = codigoFonte.get(auxCont);
                switch (estado) {
                   
                    case 0:
                        if (letraC == ';') {
                            
                            auxToken = new Token(String.valueOf(letraC),"TOK_PNO_VIRGULA", linha );
                            token.add(auxToken);
                        
                        } 
                        
                        else if (letraC == '(') {
                            
                            auxToken = new Token(String.valueOf(letraC),"TOK_Abre_Parent",linha );
                            token.add(auxToken);
                           
                            
                        } 
                        
                        else if (letraC == ')') {
                            
                            auxToken = new Token(String.valueOf(letraC), "TOK_Fecha_Parent", linha);
                            token.add(auxToken);
                            
                        } 
                        
                        else if (letraC == '{') {
                            auxToken = new Token(String.valueOf(letraC), "TOK_Abre_Chavet" , linha);
                            token.add(auxToken);
                            
                                                    
                        } 
                        
                        else if (letraC == '}') {
                            auxToken = new Token(String.valueOf(letraC), "TOK_Fecha_Chavet", linha);
                            token.add(auxToken);
                            
                            
                                                    
                        } 
                        
                        else if (letraC == '[') {
                            auxToken = new Token(String.valueOf(letraC), "TOK_Abre_Parent_Rectos", linha);
                            token.add(auxToken);
                            
                            
                            
                        } 
                        
                        else if (letraC == ']') {
                            auxToken = new Token(String.valueOf(letraC), "TOK_Fecha_Parent_Rectos", linha);
                            token.add(auxToken);
                            
                            
                                              
                        } 
                        else if(letraC == '#'){
                            
                            auxToken = new Token(String.valueOf(letraC), "TOK_Asteriscos", linha);
                            token.add(auxToken);
                            
                         
                                              
                        }
                        
                        
                        else if (letraC == '!') {
                            
                            auxToken = new Token(String.valueOf(letraC), "TOK_Neg", linha);
                            token.add(auxToken);
                            
                                                  
                        }
                        
                        else if (letraC == '^') {
                            auxToken = new Token(String.valueOf(letraC), "TOK_Pot", linha);
                            token.add(auxToken);
                            
                           
                        }
                        
                        else if (letraC == '+') {
                                   
                                join += String.valueOf(letraC);
                                estado = 15; 
    
                        }
                        
                        else if (letraC == '-') {

                            join += String.valueOf(letraC);
                            
                            estado = 19; // += ++ outro

                        }
                        
                        else if (letraC == '*') {

                             
                                
                                join += String.valueOf(letraC);
                                estado = 20; // += ++ outro
  

                        }
                        
                        else if (letraC == '/') {
             
                            
                                join += String.valueOf(letraC);
                                estado = 26; // += ++ outro
                                

                        }
                         
                        else if (letraC == '=') {

                            if (letra.length() <= 1) {
                            
                            auxToken = new Token(String.valueOf(letraC), "TOK_equals", linha);
                            token.add(auxToken);
                            
                                         
                                validar = false;
                            } else {
                                join += String.valueOf(letraC);
                                estado = 23; // += ++ outro
                                
                            }

                        }
                        else if (letraC == '>') {

                            if (letra.length() <= 1) {
                                auxToken = new Token(String.valueOf(letraC), "TOK_Maior", linha);
                                token.add(auxToken);
                                
                                validar = false;
                                
                            } else {
                                join += String.valueOf(letraC);
                                estado = 37; // 
                                
                            }

                        }
                        
                        else if (letraC == '<') {

                            if (letra.length() <= 1) {
                                auxToken = new Token(String.valueOf(letraC), "TOK_Menor", linha);
                                token.add(auxToken);
                                validar = false;
                                
                            } else {
                                join += String.valueOf(letraC);
                                estado = 37; // 
                                
                            }

                        }
                        
                        else if ((letraC > 'a' && letraC < 'z') || (letraC > 'A' && letraC < 'Z') || (letraC == '_')){
                           // join += String.valueOf(letraC);
                            //auxCont++;
                            palavras.add(letraC);
                            estado = 1;
                            
                        }
                        //incompleto
                        else if(letraC == 'n'){
                            //System.out.println(letra.charAt(i) + "                     TOK_ML                        " + linha.get(j));
                        }
                        
                         else if(letraC == '"'){
                             auxToken = new Token(String.valueOf(letraC), "TOK_Aspas", linha);
                             token.add(auxToken);
                             
                        }
                             
                        else if( (letraC > '0' || letraC < '9')){
                            join +=String.valueOf(letraC);
                            estado = 3;
                            
                        }
                        
                        else if(letraC == '&'){
                            if (letra.length() <= 1) {
                                
                                auxToken = new Token(String.valueOf(letraC), "TOK_And", linha);
                                token.add(auxToken);
                                auxCont++;         
                                validar = false;
                            } else {
                                join += letraC;
                                estado = 27; // 
                                
                            }
                        }
                        
                        else if(letraC == '|'){
                            if (letra.length() == 1) {
                                auxToken = new Token(String.valueOf(letraC), " erro ", linha);
                                token.add(auxToken);
                                auxCont++;
                            } else {
                                join += String.valueOf(letraC);
                                estado = 40;
                                
                            }
                        }
                        else{
                            auxToken = new Token(String.valueOf(letraC), " erro ", linha);
                            token.add(auxToken);
                            
                        }
                        break;
                        
                        
                    case 1:
                            auxCont++;
                            letraC = codigoFonte.get(auxCont);
                            if((letraC > 'a' && letraC < 'z') || (letraC > 'A' && letraC < 'Z') || (letraC == '_') || (letraC > '0' && letraC < '9')) {
                               // join += String.valueOf(letraC);
                                palavras.add(letraC);
                                //auxCont++;
                                estado = 1;
                            }
                            else{
                                estado = 2;
                            }
    
                        break;
                        
                    case 2:
                        
                   
                        if (palavraReservada(join)) {

                            auxToken = new Token(join, "TOK_Palavra_Reservada", linha);
                            token.add(auxToken);
                            join = "";
                            estado = 0;
                            palavras.clear();
                            

                        } else if (!(palavraReservada(join))) {

                            auxToken = new Token(join, "TOK_ID", linha);
                            token.add(auxToken);
                            join = "";
                            estado = 0;
                            palavras.clear();
                        }

                        break;
                    case 3:
                        
                        auxCont++;
                        letraC = codigoFonte.get(auxCont);

                        if ((letraC > '0' && letraC < '9') || letraC == '.') {
                            join += String.valueOf(letraC);
                            estado = 3;

                        }

                        for (int j = 0; j < palavras.size(); j++) {
                            join += palavras.get(j);
                        }
                        if (comman(join)) {

                            auxToken = new Token(join, "TOK_Float", linha);
                            token.add(auxToken);
                            join = "";
                            
                                                        
                        } 
                        
                        else if ((!(comman(join))) && (!(commanfinal(join)))) {
                            
                            auxToken = new Token(join, "TOK_Inteiro", linha);
                            token.add(auxToken);
                             join =""; 
                             
                        } 
                        
                        else if (commanfinal(join)) {
                            
                            auxToken = new Token(join, "erro", linha);
                            token.add(auxToken);
                            join ="";         
                            
                        }
                        
                        validar = false;
                        break;
                        
                    case 15:
                        auxCont++;
                        letraC = codigoFonte.get(auxCont);
                        if (letraC == '+') {
                            join += String.valueOf(letraC);
                            auxToken = new Token(join, " TOK_Plus_Plus", linha);
                            token.add(auxToken);
                            join = "";
                            estado = 0;
                        } else if (letraC == '=') {
                            join += String.valueOf(letraC);
                            auxToken = new Token(join, " TOK_Plus_igual", linha);
                            token.add(auxToken);
                            join = "";
                            estado = 0;
                        } else {
                            auxToken = new Token(join, " TOK_Plus", linha);
                            token.add(auxToken);
                            join = "";
                            estado = 0;
                            auxCont--;

                        }

                    case 19:
                        
                        auxCont++;
                        letraC = codigoFonte.get(auxCont);
                        switch (letraC) {
                            case '-':
                                //i++;
                                join += String.valueOf(letraC);
                                auxToken = new Token(join, " TOK_Menos_Menos ", linha);
                                token.add(auxToken);
                                 join = "";
                                validar = false;
                                
                                break;
                            case '=':
                                // i++;
                                join += String.valueOf(letraC);
                                auxToken = new Token(join, " TOK_Menos_igual ", linha);
                                token.add(auxToken);
                                join="";                                  
                                validar = false;
                                
                                break;
                                
                            default: 
                                
                                auxToken = new Token(String.valueOf(letraC), "TOK_Menos", linha);
                                token.add(auxToken);
                                auxCont--;
                                break;
                        }
                        break;
                       
                       
                    case 20:
                        auxCont++;
                        letraC = codigoFonte.get(auxCont);
                        switch (letraC) {
                            case '*':
                                //i++;
                                join += String.valueOf(letraC);
                                auxToken = new Token(join, "  TOK_Multipl_Multipli  ", linha);
                                token.add(auxToken);
                                join="";            
                                
                                break;
                            case '=':
                                // i++;
                                join += String.valueOf(letraC);
                                auxToken = new Token(join, "  TOK_Multipli_igual     ", linha);
                                token.add(auxToken);
                                join="";             
 
                                break;
                                
                            default:
                                auxToken = new Token(String.valueOf(letraC), "TOK_Multipli", linha);
                                token.add(auxToken);
                                join=""; 
                                break;
                        }

                        break;

                    case 26:
                        
                        auxCont++;
                        letraC = codigoFonte.get(auxCont);
                        
                        switch (letraC) {
                            
                            case '/':
                            
                                join += String.valueOf(letraC);
                                auxToken = new Token(join, "   TOK_Comentario   ", linha);
                                token.add(auxToken);
                                join = "";           
                                validar = false;
                                
                                break;
                                
                            case '=':
                              
                                join += String.valueOf(letraC);
                                auxToken = new Token(join, " TOK_Div_igual ", linha);
                                token.add(auxToken);
                                join = "";
                                validar = false;

                                break;
                            default:
                                auxToken = new Token(String.valueOf(letraC), "TOK_Div", linha);
                                token.add(auxToken);
                                join="";
                                break;

                        }

                        break;

                    case 23:
                        i = 1;
                        switch (letraC) {
                            case '=':
                                //i++;
                                join += String.valueOf(letraC);
                                auxToken = new Token(join, " TOK_eQUALS_EQUAL ", linha);
                                token.add(auxToken);
                                join ="";        
                                validar = false;
                                
                                break;
                            case '!':
                                // i++;
                                join += String.valueOf(letraC);
                                auxToken = new Token(join, " TOK_DIFE ", linha);
                                token.add(auxToken);
                                join = "";       
                                validar = false;
                                
                                break;
                        }

                        break;
                        
                    case 27: 
                        i = 1;
                        switch (letraC) {
                            case '/':
                                //i++;
                                join += String.valueOf(letraC);
                                auxToken = new Token(join, " TOK_eQUALS_EQUAL  ", linha);
                                token.add(auxToken);
                                join = "";
                                validar = false;
                                
                                break;
                            case '!':
                                // i++;
                                join += String.valueOf(letraC);
                                auxToken = new Token(join, " TOK_DIFE ", linha);
                                token.add(auxToken);
                                join = "";
                                validar = false;
                                
                                break;
                        }

                        break;
                        
                        
                    case 37:
                        i = 1;
                        switch (letraC) {
                            case '&':
                                //i++;
                                join += String.valueOf(letraC);
                                auxToken = new Token(join, " TOK_AND_AND ", linha);
                                token.add(auxToken);
                                join = "";                         
                                validar = false;
                                auxCont++;
                                break;
                         
                        }
                        break;
                    case 43:
                        i = 1;
                        switch (letraC) {
                            case '/':
                                //i++;
                                join += String.valueOf(letraC);
                               // System.out.println(join + "                             TOK_eQUALS_EQUAL                        " + linha.get(j));
                                validar = false;
                                
                                break;
                            case '!':
                                // i++;
                                join += String.valueOf(letraC);
                               // System.out.println(join + "                             TOK_DIFE                          " + linha.get(j));
                                validar = false;
                                
                                break;
                        }

                     break;

                    case 40:
                        
                        i = 1;
                        switch (letraC) {
                            case '|':
                                //i++;
                                join += String.valueOf(letraC);
                                auxToken = new Token(join, " TOK_OR ", linha);
                                token.add(auxToken);
                                join ="";
                                validar = false;
                                
                                break;
                          
                        }
                        break;

                    default:
                        
                        auxToken = new Token(String.valueOf(letraC), " erro ", linha);
                        token.add(auxToken);
                        

        
                        validar = false;
                        break;
                }
                
               // validar = false;

            // estado = 0;
            }
           
            
        
       return token;
    }

}
