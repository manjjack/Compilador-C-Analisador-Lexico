/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package analisador_sintatico;

import java.io.IOException;
import java.util.ArrayList;

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
        ArrayList<Sintatico> sint = analex.analisador();
        
        analex.analise(sint);
    }
    
}
