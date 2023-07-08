/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package analisador_sintatico;
import java.io.IOException;

/**
 *
 * @author jackson
 */
public class MainSintatico {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here       
        
         Analisador analex = new Analisador();    
         analex.analise(analex.analisador());
         System.out.println("Numero de erros: " + analex.analisador().size());
    	
    	// Parte 2
    	
    }
    
}
