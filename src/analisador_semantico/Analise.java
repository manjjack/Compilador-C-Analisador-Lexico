/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analisador_semantico;

import analisador_lexico.Analex2;
import analisador_lexico.Token;
import analisador_sintatico.Gramatica;
import analisador_sintatico.Sintatico;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author user
 */
public class Analise {
    
    	Analex2 analisadorLexico = new Analex2();
	ArrayList<Token> tokens = new ArrayList<>();
	Token token = new Token();
	ArrayList<Semantico> semanticos = new ArrayList<>();
	Gramatica gramatica;

	public static void analise(ArrayList<Semantico> semantico) {
		// System.out.println("LEXEMA\t\t\t\tLinha\t\t\t\tTOKEN\n");
		for (Semantico sint : semantico) {
			System.out.println(sint.getEr() + "|\t" + "Linha: " + sint.getLinha() + "|\t" + sint.getErro());
			System.out.println("____________________________________________________________________________________");
		}
	}

	public ArrayList analisador() throws IOException {

		ArrayList<Token> tokens = analisadorLexico.analisador();
		Gramatica gramatica = new Gramatica(tokens);
		gramatica.analise();

		return gramatica.errosSematicos();

	}
}
