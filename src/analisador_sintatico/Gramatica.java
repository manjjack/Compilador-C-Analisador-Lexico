/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package analisador_sintatico;

import analisador_lexico.Analex2;
import analisador_lexico.Token;
import analisador_semantico.Semantico;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author jackson
 */
public class Gramatica {

    private Sintatico sintatico = new Sintatico();
    private Analex2 lexico = new Analex2();
    private int indice;
    ArrayList<Token> tokens;
    private Token token;
    private ArrayList<Sintatico> sintaticos = new ArrayList<>();
    private int i = 0;
    private Semantico semantico = new Semantico();
    private ArrayList<Semantico> semanticos = new ArrayList<>();
    String aux;
    private ArrayList<String> auxTipo = new ArrayList<>(); // guarda todas as variaveis do programa
    private ArrayList<String> auxTipoDado = new ArrayList<>();
    private ArrayList<String> auxB = new ArrayList<>();
    
     public Gramatica(ArrayList<Token> tokens) {
        this.tokens = tokens;
        this.indice = 0;
        this.token = tokens.get(0);

    }

    private String tokL[] = {"TOK_PNV", "TOK_AS", "TOK_AP", "TOK_FP", "TOK_APR", "TOK_FPR", "TOK_VG",
        "TOK_ACH", "TOK_FCH"};

    private char lexemanL[] = {',', '"', '(', ')', '[', ']', ',', '{', '}'};

    public boolean verificaTipoDado(String token) {

        return Arrays.asList("int", "void", "char", "double", "struct").contains(token);
    }

    public boolean verificaBiblioteca(String token) {
        return Arrays.asList("TOK_AST", "include", " Tok_menor", "Biblioteca", "Tok_menor").contains(token);
    }

    private void erro(String simbolo) {
        //if(indice > 1) indice--; 
        sintatico = new Sintatico(tokens.get(indice).getLinha(), (" Esperava: " + simbolo));
        sintaticos.add(sintatico);
    }

    private void erroS(String simbolo) {
        //if(indice > 1) indice--; 
        semantico = new Semantico(tokens.get(indice).getLinha(), (simbolo));
        semanticos.add(semantico);
    }

    private void proxToken() {

        indice++;

        if (indice < tokens.size()) {

            token = tokens.get(indice);

        }

    }

    private void verificarToken(String tokenEsperado, String simbolo) {
        if (token == null) {
            erro("Unexpected end of input");
            return;
        }

        if (token.getToken().equals(tokenEsperado)) {
            proxToken();
        } else {
            erro(simbolo);
        }
    }

    public ArrayList errosSintaticos() {
        return this.sintaticos;
    }

    public ArrayList errosSematicos() {
        return this.semanticos;
    }

    public void analise() {
        programa();

    }

    private void programa() {

        declaracaoBiblioteca();
        listaDeclaracoes();
    }

    private void listaDeclaracoes() {
        while (token.getToken().equals("int") || token.getToken().equals("float") || token.getToken().equals("char")
                || token.getToken().equals("TOK_ID")) {
            declaracao();
        }

    }

    private void declaracaoBiblioteca() {
        while (token.getToken().equals("TOK_AST")) {
            verificarToken("TOK_AST", "#");
            if (token.getToken().equals("include")) {
                verificarToken("include", "include");
                verificarToken("Tok_menor", "<");
                //semantico
                auxB.add(token.getLexema());
                verificaB(token.getLexema());
                //sintatico
                verificarToken("Biblioteca", "Biblioteca");
                verificarToken("Tok_maior", ">");
            }
            // verificarToken("TOK_PV", ";");
        }
    }

    private void declaracao() {
        aux = "";
        aux = token.getToken();
        if (token.getToken().equals("int")) {
            verificarToken("int", "int");
        } else if (token.getToken().equals("float")) {
            verificarToken("float", "float");
        } else if (token.getToken().equals("char")) {
            verificarToken("char", "char");
        } else {
            variavelDeclarada();
        }

        // verifica se tipo foi declarado como ID (Semantico)
        if (token.getToken().equals("int") || token.getToken().equals("float") || token.getToken().equals("char")) {
            erroS("ID invalido");
        }
        auxTipo.add(token.getLexema());
        auxTipoDado.add(token.getToken());
        verificaVariaveisSematico(token.getLexema());
        verificarToken("TOK_ID", "ID");

        declaracaoVariavel();

    }

    private void variavelDeclarada() {
        boolean valida = false;
        if (token.getToken().equals("TOK_ID")) {
            for (String tk : this.auxTipo) {
                if (!tk.equals(token.getToken())) {
                    valida = true;
                }

            }

            if (valida) {
                erroS("Variavel nao declarada");
            }

            verificarToken("TOK_ID", "ID");
            verificarToken("TOK_equals", "=");
            switch (token.getToken()) {
                case "TOK_Inteiro":
                    if (!aux.equals("int") && token.getToken().equals("TOK_Inteiro")) {
                        erroS("Esperava um inteiro");
                    }
                    verificarToken("TOK_Inteiro", "Inteiro");
                    break;
                case "TOK_Float":
                    if (!aux.equals("float") && token.getToken().equals("TOK_Float")) {
                        erroS("Esperava um Float");
                    }
                    verificarToken("TOK_Float", "Float");
                    break;
                case "TOK_ID":
                    if (!aux.equals("char") && token.getToken().equals("TOK_ID")) {
                        erroS("Esperava um char");
                    }
                    verificarToken("TOK_ID", "char");
                    break;
                default:
                    verificarToken("", "atribuicao");
                    break;
            }

        }
    }

    private void declaracaoVariavel() {

        switch (token.getToken()) {
            case "TOK_PNV":
                verificarToken("TOK_PNV", ";");
                break;
            case "TOK_APR":
                verificarToken("TOK_APR", "[");
                verificarToken("TOK_Inteiro", "Inteiro");
                verificarToken("TOK_FPR", "]");
                verificarToken("TOK_PNV", ";");
                break;
            case "TOK_AP":
                verificarToken("TOK_AP", "(");
                listaParametros();
                verificarToken("TOK_FP", ")");
                declaracaoComposta();
                break;
            case "TOK_equals":
                verificarToken("TOK_equals", "=");
                if (token.getToken().equals("TOK_Inteiro")) {
                    if (!aux.equals("int") && token.getToken().equals("TOK_Inteiro")) {
                        erroS("Esperava um inteiro");
                    }
                    verificarToken("TOK_Inteiro", "Inteiro");

                } else if (token.getToken().equals("TOK_Float")) {

                    if (!aux.equals("float") && token.getToken().equals("TOK_Float")) {
                        erroS("Esperava um Float");
                    }
                    verificarToken("TOK_Float", "Float");

                } else if (token.getToken().equals("TOK_ID")) {

                    if (!aux.equals("char") && token.getToken().equals("TOK_ID")) {
                        erroS("Esperava um char");
                    }
                    verificarToken("TOK_ID", "char");

                } else {
                    verificarToken("", "atribuicao");

                }
                verificarToken("TOK_PNV", ";");
                break;
            default:
                verificarToken("TOK_PNV", ";");

                break;
        }

    }

    // Primeiro Parametro da Funcao
    private void listaParametros() {
        if (!token.getToken().equals("TOK_FP")) {
            parametro();
            listaDeParametros();
        }
    }
    // Parametros da Funcao

    private void listaDeParametros() {
        if (token.getToken().equals("TOK_VG")) {
            verificarToken("TOK_VG", ",");
            parametro();
            listaDeParametros();
        }
    }

    private void parametro() {
        if (token.getToken().equals("int")) {
            verificarToken("int", "int");
        } else if (token.getToken().equals("float")) {
            verificarToken("float", "float");
        } else if (token.getToken().equals("char")) {
            verificarToken("TOK_ID", "char");
        } else {
            verificarToken("TIPO", "Tipo");
        }

        verificarToken("TOK_ID", "ID");
        parametroV();
    }

    // Vetor no parametro
    private void parametroV() {

        if (token.getToken().equals("TOK_APR")) {
            verificarToken("TOK_APR", "[");
            verificarToken("TOK_FPR", "]");
        }

    }

    // corpo da funcao
    private void declaracaoComposta() {
        verificarToken("TOK_ACH", "{");
        listaDeclaracoes();
        listaComandos();
        verificarToken("TOK_FCH", "}");
    }

    // corpo da funcao
    private void listaComandos() {
        while (!token.getToken().equals("TOK_FCH") && indice < tokens.size()) {
            comando();
        }
    }

    // corpo da funcao
    private void comando() {
        if (token.getToken().equals("TOK_PNV")) {
            verificarToken("TOK_PNV", ";");
        } else if (token.getToken().equals("TOK_ACH")) {
            declaracaoComposta();
        } else if (token.getToken().equals("if")) {
            comandoSelecao();
        } else if (token.getToken().equals("while") || token.getToken().equals("for")) {
            comandoIteracao();
        } else if (token.getToken().equals("return")) {
            comandoRetorno();
        } else {
            expressao();
            //  verificarToken("TOK_PNV", ";");
        }
    }

    private void comandoSelecao() {
        verificarToken("if", "if");
        verificarToken("TOK_AP", "(");
        expressaoA();
        verificarToken("TOK_FP", ")");
        comando();
        comandoElse();
    }

    private void comandoElse() {
        if (token.getToken().equals("else")) {
            verificarToken("else", "else");
            comando();
        }
    }

    private void expressaoA() {
        
            if (token.getToken().equals("TOK_ID")) {
               // Semantico
            if (!verificaV(token.getLexema())) {
                erroS("Variavel '" + token.getLexema() + "' nao declarada");
            }
            verificarToken("TOK_ID", "ID");
            switch (token.getToken()) {
                case "Tok_menor":
                    verificarToken("Tok_menor", "<");
                    break;
                case "Tok_maior":
                    verificarToken("Tok_maior", ">");
                    break;
                case "Tok_EQUALS_EQUALS":
                    verificarToken("Tok_EQUALS_EQUALS", "==");
                    break;
                default:
                    erroS("Erro na declaracao");
                    break;
            }
            verificarToken("TOK_Inteiro", "Inteiro");
        } else if (token.getToken().equals("Tok_Inteiro")) {
            verificarToken("TOK_Inteiro", "Inteiro");
            switch (token.getToken()) {
                case "Tok_menor":
                    verificarToken("Tok_menor", "<");
                    break;
                case "Tok_maior":
                    verificarToken("Tok_maior", ">");
                    break;
                case "Tok_EQUALS_EQUALS":
                    verificarToken("Tok_EQUALS_EQUALS", "==");
                    break;
                default:
                    erroS("Erro na declaracao");
                    break;
            }
            verificarToken("TOK_ID", "ID");
        } else {
            erroS("Erro na declaracao");
        }
    }

    // comandos for e while
    private void comandoIteracao() {
        if (token.getToken().equals("while")) {
            verificarToken("while", "while");
            verificarToken("TOK_AP", "(");
            expressaoA();

            verificarToken("TOK_FP", ")");
            comando();
        } else if (token.getToken().equals("for")) {
            verificarToken("for", "for");
            verificarToken("TOK_AP", "(");
            //expressao();
            if (token.getToken().equals("int")) {
                verificarToken("int", "int");
                verificarToken("TOK_ID", "ID");

            } else if (token.getToken().equals("TOK_ID")) {

            } else {
                erroS("Erro na Declaracao");
            }
            verificarToken("TOK_PNV", ";");
            expressao();
            verificarToken("TOK_PNV", ";");
            expressao();
            verificarToken("TOK_FP", ")");
            comando();
        }
    }

    //return 
    private void comandoRetorno() {
        verificarToken("return", "return");
        if (!token.getToken().equals("TOK_PNV")) {
            expressao();
        }
        verificarToken("TOK_PNV", ";");
    }

    private void expressao() {
        expressaoAtribuicao();
    }

    private void expressaoAtribuicao() {
        //  verificarToken("TOK_ID", "ID");

        if (token.getToken().equals("TOK_EQUALS_EQUALS ")) {
            while (token.getToken().equals("TOK_eQUALS_EQUAL ")) {
                verificarToken("TOK_EQUALS_EQUALS ", "==");
            }
            expressaoAtribuicao();
        } else {
            expressaoOr();
        }
    }

    private void expressaoOr() {
        expressaoAnd();
        expressaoOrP();
    }

    private void expressaoOrP() {
        while (token.getToken().equals("TOK_OR")) {
            verificarToken("TOK_OR", "||");
            expressaoAnd();
            expressaoOrP();
        }
    }

    private void expressaoAnd() {
        expressaoIgualdade();
        expressaoAndP();
    }

    private void expressaoAndP() {
        while (token.getToken().equals("TOK_&&")) {
            verificarToken("TOK_&&", "&&");
            expressaoIgualdade();

        }
    }

    private void expressaoIgualdade() {
        expressaoRelacional();
        expressaoIgualdadeP();
    }

    private void expressaoIgualdadeP() {

        while (token.getToken().equals("TOK_EQUALS_EQUALS") /*|| token.equals("!=") */) {
            verificarToken(token.getToken(), "==");
            expressaoRelacional();

        }
    }

    private void expressaoRelacional() {
        expressaoAditiva();
        expressaoRelacionalP();
    }

    private void expressaoRelacionalP() {
        while (token.getToken().equals("Tok_menor") || token.getToken().equals("Tok_maior") || token.getToken().equals("TOK_menor_igual ") || token.getToken().equals("TOK_maior_igual ")) {
            verificarToken(token.getToken(), " expressao");
            expressaoAditiva();

        }
        verificarToken("Tok_Inteiro", "Inteiro");

    }

    private void expressaoAditiva() {
        expressaoMultiplicativa();
        expressaoAditivaP();
    }

    private void expressaoAditivaP() {
        while (token.getToken().equals("TOK_plus") || token.getToken().equals("TOK_menos")) {
            verificarToken(token.getToken(), "expressao");
            expressaoMultiplicativa();

        }
    }

    private void expressaoMultiplicativa() {
        expressaoUnaria();
        expressaoMultiplicativaP();
    }

    private void expressaoMultiplicativaP() {
        while (token.getToken().equals("TOK_multipli") || token.getToken().equals("TOK_Div")
                || token.getToken().equals("Tok_Percentagem")) {

            verificarToken(token.getToken(), "expressao");
            expressaoUnaria();

        }
    }

    private void expressaoUnaria() {
        if (token.getToken().equals("TOK_menos") || token.getToken().equals("TOK_N")) {
            verificarToken(token.getToken(), "expressao");
            expressaoUnaria();
        } else {
            expressaoPostfixa();
        }
    }

    private void expressaoPostfixa() {
        expressaoPrimaria();
        expressaoPostfixaP();
    }

    private void expressaoPostfixaP() {
        if (token.getToken().equals("TOK_APR")) {
            verificarToken("TOK_APR", "{");
            expressao();
            verificarToken("TOK_FPR", "}");
            expressaoPostfixaP();
        } else if (token.getToken().equals("TOK_AP")) {
            verificarToken("TOK_AP", "(");
            listaExpressoesArgumento();
            verificarToken("TOK_FP", ")");
            expressaoPostfixaP();
        }
    }

    private void listaExpressoesArgumento() {
        if (!token.getToken().equals("TOK_FP")) {
            expressao();
            listaExpressoesArgumentoP();
        }
    }

    private void listaExpressoesArgumentoP() {
        if (token.getToken().equals("TOK_VG")) {
            verificarToken("TOK_VG", ",");
            expressao();
            listaExpressoesArgumentoP();
        }
    }

    private void expressaoPrimaria() {
        if (token.getToken().equals("TOK_ID")
                || token.getToken().equals("TOK_Inteiro")
                || token.getToken().equals("TOK_Float")) {
            verificarToken(token.getToken(), "int");
        } else if (token.getToken().equals("TOK_AP")) {
            verificarToken("TOK_AP", "(");
            expressao();
            verificarToken("TOK_FP", ")");
        } else {

        }
    }

    /* 
      Sematico (Verificações)
    
    
     */
    // variavel declarada 2 vezes
    private void verificaVariaveisSematico(String p2) {
        int cont = 0;
        for (String p1 : this.auxTipo) {
            if (p1.equals(p2)) {
                cont++;
            }

        }

        if (cont > 1) {
            erroS("Variavel '" + p2 + "' ja foi declarada");
        }

    }

    private boolean verificaV(String p2) {

        for (String p1 : this.auxTipo) {
            if (p1.equals(p2)) {
                return true;
            }

        }

        return false;
    }
    
    

    private void verificaMain() {
        boolean valida = false;
        for (String tk : this.auxTipo) {
            if (tk.equals("main")) {
                valida = true;
            }
        }

        if (valida) {
            erroS("Main nao declarado");
        }
    }
    
    // verifica Biblioteca
    
    private void verificaB(String p2) {

       int cont = 0;
        for (String p1 : this.auxB) {
            if (p1.equals(p2)) {
                cont++;
            }

        }

        if (cont > 1) {
            erroS("Biblioteca '" + p2 + "' ja foi declarada");
        }
    }


    
}
