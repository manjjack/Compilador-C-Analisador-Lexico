/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package analisador_lexico;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author jackson junior
 */
public class FileReaderJ {

    char[] simbolos = {'<', '>', '(', ')', '"', '!', ',', ';', '+', '-', '*', '/', '{', '}'};

    public void getCodigoFonte(ArrayList<Character> lexema, ArrayList<Integer> linhaC) throws FileNotFoundException, IOException {
        int i = 0;
        //File arquivo = new File("CodigoFonte.txt");
        BufferedReader br = new BufferedReader(new FileReader("CodigoFonte.txt"));
        //Scanner scanner = new Scanner(arquivo);
        String nomeArquivo = "codigoFonte.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(nomeArquivo))) {
            int j = 1;
            int caractere;
             while ((caractere = reader.read()) != -1) {
                if (caractere != ' ') {
                    char conv = (char) caractere;
                    if (conv == '\n') {
                        lexema.add('$');
                        linhaC.add(j);
                        j++;
                    } else {
                        lexema.add(conv);
                        linhaC.add(j);
                    }
                } else {
                    lexema.add('$');
                    linhaC.add(j);
                }
            }

        } catch (IOException e) {
            System.err.println("Erro ao ler arquivo: " + e.getMessage());
        }

    }



    public boolean contains(String palavra) {
        for (int i = 0; i < palavra.length(); i++) {
            for (int j = 0; j < simbolos.length; j++) {

                if (palavra.charAt(i) == simbolos[j]) {
                    return true;
                }
            }

        }
        return false;
    }

    

    public void getPalavrasReservadas(ArrayList<String> palavrasReservadas) throws FileNotFoundException {
        int i = 0;
        File arquivo = new File("PalavrasReservadas.txt");
        Scanner scanner = new Scanner(arquivo);
        while (scanner.hasNextLine()) {
            String linha = scanner.nextLine();

            // Dividir a linha em palavras
            String[] palavras = linha.split(" ");

            // Guarda cada palavra
            for (String palavra : palavras) {
                palavrasReservadas.add(palavra);

            }

        }
        scanner.close();

    }
}
