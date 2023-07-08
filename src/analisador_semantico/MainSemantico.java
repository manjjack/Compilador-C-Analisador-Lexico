/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analisador_semantico;

import java.io.IOException;

/**
 *
 * @author user
 */
public class MainSemantico {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
                
         Analise analex = new Analise();    
         analex.analise(analex.analisador());
         System.out.println("Numero de erros: " + analex.analisador().size());
    }
    
}
