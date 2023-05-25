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
 * @author jackson
 */
public class Analex2 {
    
    FileReaderJ file = new FileReaderJ();
    
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

    public boolean commanfinal(String com) {

        int cont = com.length();
        return com.charAt((cont - 1)) == '.';
    }
    
    public String tipoDePalavras(String pal) throws FileNotFoundException {
        
        ArrayList<String> palReservada = new ArrayList<>();
        file.getPalavrasReservadas(palReservada);// palavras reservadas
        String palavra = null;
        for (String decl : palReservada) {
            if (pal.equals(decl)){
                palavra = decl;
            }
        }
        return palavra;
    }

    public boolean biblioteca(String pal) {

        for (int i = 0; i < pal.length(); i++) {
            if (pal.charAt(i) == '.') {
                return true;
            }
        }
        return false;
    }

    public boolean comman(String com) {
        char vog = '.';
        if (!(commanfinal(com))) {
            return com.contains(String.valueOf(vog));
        }
        return false;
    }

    public void analise(ArrayList<Token> token) {
        System.out.println("LEXEMA\t\t\t\tLinha\t\t\t\tTOKEN\n");
        for (Token tokens : token) {
            System.out.println(tokens.getLexema() + "\t\t\t\t" + tokens.getLinha() + "\t\t\t" + tokens.getToken());
            System.out.println("____________________________________________________________________________________");
        }
    }

    public ArrayList analisador() throws IOException {

        ArrayList<Token> token = new ArrayList<>();
        Token auxToken;

        ArrayList<Character> lexema = new ArrayList<>();
        ArrayList<Integer> linhaC = new ArrayList<>();
        ArrayList<Character> palavras = new ArrayList<>();
        ArrayList<Character> numeros = new ArrayList<>();

        BufferedReader br = new BufferedReader(new FileReader("CodigoFonte.txt"));
        String letras = br.readLine();

        char letraC;
        int linha = 1;
        int estado = 0;
        String join = "";
        String joinPalavras = "";
        boolean retorna = false;

        while (letras != null) {

            for (int i = 0; i < letras.length() ; i++) {

                letraC = letras.charAt(i);
               
                switch (estado) {

                    case 0:
                        if ((letraC >= 'a' && letraC <= 'z') || (letraC >= 'A' && letraC <= 'Z') || (letraC == '_')) {
                            
                            joinPalavras = joinPalavras + letraC;
                           
                            estado = 1;

                        } else if (letraC == '0' || letraC == '1' || letraC == '2' || letraC == '3' || letraC == '4' || letraC == '5'
                                || letraC == '6' || letraC == '7' || letraC == '8' || letraC == '9') {
                            numeros.add(letraC);
                            estado = 3;

                        } else if (letraC == '/') {
                            
                            palavras.add(letraC);
                            estado = 8;
                            
                        } else if (letraC == ';') {

                            auxToken = new Token(String.valueOf(letraC), "TOK_PNV", linha);
                            token.add(auxToken);

                        } else if (letraC == '(') {

                            auxToken = new Token(String.valueOf(letraC), "TOK_AP", linha);
                            token.add(auxToken);

                        } else if (letraC == ')') {

                            auxToken = new Token(String.valueOf(letraC), "TOK_FP", linha);
                            token.add(auxToken);

                        } else if (letraC == '{') {
                            auxToken = new Token(String.valueOf(letraC), "TOK_ACH", linha);
                            token.add(auxToken);

                        } else if (letraC == '}') {
                            auxToken = new Token(String.valueOf(letraC), "TOK_FCH", linha);
                            token.add(auxToken);

                        } else if (letraC == '[') {
                            auxToken = new Token(String.valueOf(letraC), "TOK_APR", linha);
                            token.add(auxToken);

                        } else if (letraC == ']') {
                            auxToken = new Token(String.valueOf(letraC), "TOK_FPR", linha);
                            token.add(auxToken);

                        } else if (letraC == '#') {

                            auxToken = new Token(String.valueOf(letraC), "TOK_AST", linha);
                            token.add(auxToken);

                        } else if (letraC == '!') {

                            auxToken = new Token(String.valueOf(letraC), "TOK_N", linha);
                            token.add(auxToken);

                        } else if (letraC == '^') {
                            auxToken = new Token(String.valueOf(letraC), "TOK_P", linha);
                            token.add(auxToken);

                        } else if (letraC == '=') {
                            palavras.add(letraC);
                            estado = 23;
                        } else if (letraC == '+') {
                            palavras.add(letraC);
                            estado = 15;
                        } else if (letraC == '-') {
                            palavras.add(letraC);
                            estado = 19;
                        } else if (letraC == '*') {
                            palavras.add(letraC);
                            estado = 22;
                        } else if (letraC == '&') {
                            palavras.add(letraC);
                            estado = 27;
                        } else if (letraC == '|') {
                            palavras.add(letraC);
                            estado = 30;
                        } else if (letraC == '\n') {
                            auxToken = new Token(String.valueOf(letraC), "TOK_ES", linha);
                            token.add(auxToken);
                        } else if (letraC == '%') {
                            palavras.add(letraC);
                            estado = 32;
                        } else if (letraC == '>') {
                            palavras.add(letraC);
                            estado = 36;
                        } else if (letraC == '<') {
                            palavras.add(letraC);
                            estado = 37;
                        } else if (letraC == '"') {
                            auxToken = new Token(String.valueOf(letraC), "TOK_AS", linha);
                            token.add(auxToken);

                        } else if (letraC == ':') {
                            auxToken = new Token(String.valueOf(letraC), "TOK_DP", linha);
                            token.add(auxToken);
                        } else if (letraC == ']') {
                            auxToken = new Token(String.valueOf(letraC), "TOK_FPR", linha);
                            token.add(auxToken);
                        } else if (letraC == '[') {
                            auxToken = new Token(String.valueOf(letraC), "TOK_APR", linha);
                            token.add(auxToken);
                        }

                        break;

                    case 1:

                        if ((letraC >='a' && letraC <= 'z') || (letraC >= 'A' && letraC <= 'Z') || (letraC == '_') || (letraC >= '0' && letraC <= '9') || (letraC=='.')) {
                            
                            joinPalavras = joinPalavras + letraC;
                            estado = 1;
                            
                        } else {

                            join = joinPalavras;

                            if (palavraReservada(join)) {
                                String nomePal = tipoDePalavras(join);
                                auxToken = new Token(join, nomePal, linha);
                                token.add(auxToken);

                            } else if (!(palavraReservada(join)) &&  !(biblioteca(join)) ) {

                                auxToken = new Token(join, "TOK_ID", linha);
                                token.add(auxToken);

                            } else if (biblioteca(join) &&  !(palavraReservada(join)) ) {
                                auxToken = new Token(join, "Biblioteca", linha);
                                token.add(auxToken);
                            }
                            join = "";
                            retorna = true;
                            joinPalavras = "";
                             i--;
                            palavras.clear();
                            estado = 0;
                        }

                        break;

                    case 3:
                        if (letraC == '0' || letraC == '1' || letraC == '2' || letraC == '3' || letraC == '4' || letraC == '5'
                                || letraC == '6' || letraC == '7' || letraC == '8' || letraC == '9' || letraC == '.') {

                            numeros.add(letraC);
                            estado = 3;

                        } else {

                            for (int j = 0; j < numeros.size(); j++) {
                                join += numeros.get(j);
                            }

                            if (comman(join)) {

                                auxToken = new Token(join, "TOK_Float", linha);
                                token.add(auxToken);

                            } else if ((!(comman(join))) && (!(commanfinal(join)))) {

                                auxToken = new Token(join, "TOK_Inteiro", linha);
                                token.add(auxToken);

                            } else if (commanfinal(join)) {

                                auxToken = new Token(join, "erro", linha);
                                token.add(auxToken);

                            }
                            join = "";
                            retorna = true;
                             i--;
                            numeros.clear();
                            estado = 0;
                           
                        }
                        break;

                    case 8:

                        if (letraC == '/') {
                     
                            auxToken = new Token("//", "   TOK_Comentario   ", linha);
                            token.add(auxToken);
                            
                            letras = br.readLine();
                            linha++;
                            i--;

                        } else if (letraC == '*') {
                            
                            auxToken = new Token( "/*", "   TOK_ComentarioI ", linha);
                            token.add(auxToken);
                            
                            boolean valida = false;
                            int aux = letras.length();
                            while (!valida) {

                                if (i < aux) {

                                    if (letras.charAt(i) == '*') {
                                        i++;
                                        if (letras.charAt(i) == '/') {
                                            valida = true;
                                        }
                                    }
                                } else {

                                    letras = br.readLine();
                                    
                                    if (letras.trim().isEmpty() || letras == null ) {
                                        letras = br.readLine();
                                    } else {
                                        aux = letras.length();
                                        linha++;
                                        i = 0;

                                    }

                                }
                                i++;
                            }

                            if (valida) {
                                auxToken = new Token("*/", "   TOK_ComentarioF   ", linha);
                                token.add(auxToken);
                            }

                        } else if (letraC == '=') {
                            for (int j = 0; j < palavras.size(); j++) {
                                join += palavras.get(j);
                            }
                            auxToken = new Token(join, " TOK_Div_igual ", linha);
                            token.add(auxToken);
                        } else {

                            for (int j = 0; j < palavras.size(); j++) {
                                join += palavras.get(j);
                            }
                            auxToken = new Token(join, "TOK_Div", linha);
                            token.add(auxToken);
                            retorna = true;
                        }
                        join = "";
                        i--;
                        estado = 0;
                        palavras.clear();
                        
                        break;

                    case 9:
                        break;
                        
                    case 10:
                        break;
                        
                    case 15:
                        if (letraC == '=') {
                            palavras.add(letraC);
                            for (int j = 0; j < palavras.size(); j++) {
                                join += palavras.get(j);
                            }
                            auxToken = new Token(join, " TOK_plus_EQUAL ", linha);
                            token.add(auxToken);
                        } else if (letraC == '+') {
                            palavras.add(letraC);
                            for (int j = 0; j < palavras.size(); j++) {
                                join += palavras.get(j);
                            }
                            auxToken = new Token(join, " TOK_plus_plus ", linha);
                            token.add(auxToken);

                        } else {

                            for (int j = 0; j < palavras.size(); j++) {
                                join += palavras.get(j);
                            }
                            auxToken = new Token(join, "TOK_plus", linha);
                            token.add(auxToken);
                            retorna = true;
                        }
                        join = "";
                          i--;
                        palavras.clear();
                        estado = 0;
                        break;

                    case 19:
                        if (letraC == '=') {
                            palavras.add(letraC);
                            for (int j = 0; j < palavras.size(); j++) {
                                join += palavras.get(j);
                            }
                            auxToken = new Token(join, " TOK_menos_EQUAL ", linha);
                            token.add(auxToken);
                        } else if (letraC == '+') {
                            palavras.add(letraC);
                            for (int j = 0; j < palavras.size(); j++) {
                                join += palavras.get(j);
                            }
                            auxToken = new Token(join, " TOK_menos_menos ", linha);
                            token.add(auxToken);

                        } else if (letraC == '>') {
                            auxToken = new Token(join, " TOK_atribuicao ", linha);
                            token.add(auxToken);
                        } else {
                            for (int j = 0; j < palavras.size(); j++) {
                                join += palavras.get(j);
                            }
                            auxToken = new Token(join, "TOK_menos", linha);
                            token.add(auxToken);
                            retorna = true;
                        }
                        join = "";
                          i--;
                        estado = 0;
                        palavras.clear();

                        break;
                    case 22:
                        if (letraC == '=') {
                            palavras.add(letraC);
                            for (int j = 0; j < palavras.size(); j++) {
                                join += palavras.get(j);
                            }
                            auxToken = new Token(join, " TOK_multi_EQUAL ", linha);
                            token.add(auxToken);
                        } else if (letraC == '*') {
                            palavras.add(letraC);
                            for (int j = 0; j < palavras.size(); j++) {
                                join += palavras.get(j);
                            }
                            auxToken = new Token(join, " TOK_multip_multipli ", linha);
                            token.add(auxToken);

                        } else {
                            for (int j = 0; j < palavras.size(); j++) {
                                join += palavras.get(j);
                            }
                            auxToken = new Token(join, "TOK_multipli", linha);
                            token.add(auxToken);
                            retorna = true;
                        }
                        join = "";
                          i--;
                        palavras.clear();
                        estado = 0;
                        break;
                    case 23:
                        if (letraC == '=') {
                            palavras.add(letraC);
                            for (int j = 0; j < palavras.size(); j++) {
                                join += palavras.get(j);
                            }
                            auxToken = new Token(join, " TOK_eQUALS_EQUAL ", linha);
                            token.add(auxToken);
                        } else if (letraC == '!') {
                            palavras.add(letraC);
                            for (int j = 0; j < palavras.size(); j++) {
                                join += palavras.get(j);
                            }
                            auxToken = new Token(join, " TOK_DIFE ", linha);
                            token.add(auxToken);

                        } else {
                            for (int j = 0; j < palavras.size(); j++) {
                                join += palavras.get(j);
                            }
                            auxToken = new Token(join, "TOK_equals", linha);
                            token.add(auxToken);
                            retorna = true;
                        }
                        join = "";
                          i--;
                        palavras.clear();
                        estado = 0;
                        break;

                    case 27:
                        if (letraC == '&') {
                            palavras.add(letraC);
                            for (int j = 0; j < palavras.size(); j++) {
                                join += palavras.get(j);
                            }
                            auxToken = new Token(join, " TOK_&& ", linha);
                            token.add(auxToken);
                        } else {

                            for (int j = 0; j < palavras.size(); j++) {
                                join += palavras.get(j);
                            }
                            auxToken = new Token(join, "TOK_&", linha);
                            token.add(auxToken);
                            retorna = true;
                        }
                        join = "";
                          i--;
                        palavras.clear();
                        estado = 0;
                        break;
                    case 30:
                        if (letraC == '|') {
                            palavras.add(letraC);
                            for (int j = 0; j < palavras.size(); j++) {
                                join += palavras.get(j);
                            }
                            auxToken = new Token(join, " TOK_OR ", linha);
                            token.add(auxToken);
                        } else {

                            for (int j = 0; j < palavras.size(); j++) {
                                join += palavras.get(j);
                            }
                            auxToken = new Token(join, "logico", linha);
                            token.add(auxToken);
                            retorna = true;
                        }
                        join = "";
                          i--;
                        palavras.clear();
                        estado = 0;
                        break;

                    case 32:
                        if (letraC == '=') {
                            palavras.add(letraC);
                            for (int j = 0; j < palavras.size(); j++) {
                                join += palavras.get(j);
                            }
                            auxToken = new Token(join, " TOK_percentagem_igual ", linha);
                            token.add(auxToken);
                        } else {

                            for (int j = 0; j < palavras.size(); j++) {
                                join += palavras.get(j);
                            }
                            auxToken = new Token(join, "Tok_Percentagem", linha);
                            token.add(auxToken);
                            retorna = true;
                        }
                        join = "";

                        palavras.clear();
                        estado = 0;
                        break;
                    case 36:
                        if (letraC == '>') {

                            palavras.add(letraC);

                            for (int j = 0; j < palavras.size(); j++) {
                                join += palavras.get(j);
                            }
                            auxToken = new Token(join, " TOK_maior_maior ", linha);
                            token.add(auxToken);
                        } else {

                            for (int j = 0; j < palavras.size(); j++) {
                                join += palavras.get(j);
                            }
                            auxToken = new Token(join, "Tok_maior", linha);
                            token.add(auxToken);
                            retorna = true;
                        }
                        join = "";
                          i--;
                        palavras.clear();
                        estado = 0;
                        break;
                    case 37:
                        if (letraC == '<') {

                            palavras.add(letraC);
                            for (int j = 0; j < palavras.size(); j++) {
                                join += palavras.get(j);
                            }
                            auxToken = new Token(join, " TOK_menor ", linha);
                            token.add(auxToken);
                        } else {

                            for (int j = 0; j < palavras.size(); j++) {
                                join += palavras.get(j);
                            }
                            auxToken = new Token(join, "Tok_menor", linha);
                            token.add(auxToken);
                            retorna = true;
                        }
                        join = "";
                          i--;
                        estado = 0;
                        palavras.clear();

                        break;

                }

            }
            
            letras = br.readLine();
            linha++;
        }

       return token;
    }

}
