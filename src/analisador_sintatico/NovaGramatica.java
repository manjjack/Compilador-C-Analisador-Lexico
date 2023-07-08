package analisador_sintatico;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import analisador_lexico.Analex2;
import analisador_lexico.Token;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author user
 */
public class NovaGramatica {

	private int cont = 0;
	private int escopo;
	private String tipo;
	private Analex2 lexico = new Analex2();
	private ArrayList<Token> tokens;
	private ArrayList<Sintatico> sintaticos = new ArrayList();
	private Sintatico sintatico;

	private void erro(String simbolo) {

		sintatico = new Sintatico(tokens.get(cont).getLinha(), (" Esperava: " + simbolo));
		sintaticos.add(sintatico);
	}
	
    public ArrayList errosSintaticos(){ 
        return this.sintaticos;
}

	public NovaGramatica() throws IOException {
		cont = 0;
		tokens = lexico.analisador();
		biblioteca();
		funcao();

		while (cont < tokens.size() - 1) {

		}
		fChaveta();
	}

	private boolean tipo() {
		if (tokens.get(cont).getLexema().equals("int")) {
			tipo = "int";
			cont++;
			return true;
		} else if (tokens.get(cont).getLexema().equals("char")) {
			tipo = "char";
			cont++;
			return true;
		} else if (tokens.get(cont).getLexema().equals("float")) {
			tipo = "float";
			cont++;
			return true;
		} else if (tokens.get(cont).getLexema().equals("double")) {
			tipo = "double";
			cont++;
			return true;
		} else if (tokens.get(cont).getLexema().equals("void")) {
			tipo = "void";
			cont++;
			return true;
		} else if (tokens.get(cont).getLexema().equals("bool")) {
			tipo = "bool";
			cont++;
			return true;
		}
		return false;
	}

	private void biblioteca() {

		while (cont < tokens.size()) {
			String tk = tokens.get(cont).getLexema();

			if (tk == "#") {
				// Encontrou um possível início de inclusão
				cont++;
				if (tk.equals("include")) {
					// Verifica se há <biblioteca> após o #include
					cont++;
					if (tokens.get(cont).getLexema().equals("<")) {
						cont++;
						if (tokens.get(cont).getToken().equals("biblioteca")) {
							cont++;
							if (tokens.get(cont).getLexema().equals(">")) {

							} else {
								erro(tk);
							}

						} else {
							erro(tokens.get(cont).getToken());
						}
					} else {
						erro(tk);
					}
				} else {
					erro(tk);
				}
			} else {
				erro(tk);
			}
		}

	}

	private void funcao() {

	}

	private boolean fChaveta() {
		if (cont < tokens.size()) {
			if (tokens.get(cont).getLexema().equals("}")) {
				escopo--;
				cont++;
				return true;
			} else {
				erro("}");
			}
		} else {
			erro("}");
		}
		return false;
	}

	private void id() {
		if (cont < tokens.size()) {
			if (tokens.get(cont).getToken().equals("ID")) {
				cont++;

			} else {
				erro("ID");
			}
		} else {
			erro("ID");
		}
	}

	private boolean aChaveta() {
		if (cont < tokens.size()) {
			if (tokens.get(cont).getLexema().equals("{")) {
				escopo--;
				cont++;
				return true;
			} else {
				erro("{");
			}
		} else {
			erro("{");
		}
		return false;
	}

	private boolean fParenteses() {
		if (cont < tokens.size()) {
			if (tokens.get(cont).getLexema().equals(")")) {
				cont++;
				return true;
			} else {
				erro(")");
			}
		} else {
			erro(")");
		}
		return false;
	}

	private boolean aParenteses() {
		if (cont < tokens.size()) {
			if (tokens.get(cont).getLexema().equals("(")) {
				cont++;
				return true;
			} else {
				erro("(");
			}
		} else {
			erro("(");
		}
		return false;
	}

	private boolean aPRetos() {
		if (cont < tokens.size()) {
			if (tokens.get(cont).getLexema().equals("[")) {
				cont++;
				return true;
			} else {
				erro("[");
			}
		}
		return false;
	}

	private boolean fPRetos() {
		if (cont < tokens.size()) {
			if (tokens.get(cont).getLexema().equals("]")) {
				cont++;
				return true;
			} else {
				erro("]");
			}
		}
		return false;
	}

	private boolean pontoVirgula() {
		if (cont < tokens.size()) {
			if (tokens.get(cont).getLexema().equals(";")) {
				cont++;
				return true;
			} else {
				erro(";");
			}
		}
		return false;
	}

	private boolean virgula() {
		if (cont < tokens.size()) {
			if (tokens.get(cont).getLexema().equals(",")) {
				cont++;
				return true;
			} else {
				erro(",");
			}
		}
		return false;
	}

	private boolean igual() {
		if (cont < tokens.size()) {
			if (tokens.get(cont).getLexema().equals("=")) {
				cont++;
				return true;
			} else {
				erro("=");
			}
		}
		return false;
	}

	private void parametros() {
		if (cont < tokens.size()) {
			if (tipo()) {
				if (cont < tokens.size()) {
					if (tokens.get(cont).getToken().equals("ID")) {
						cont++;
						if (cont < tokens.size()) {
							if (tokens.get(cont).getLexema().equals(",")) {
								cont++;
								parametros();
							} else if (tokens.get(cont).getLexema().equals(")")) {
								cont++;
							} else {
								erro(") ou ,");
							}
						} else {
							erro(" ) ou ;");

						}
					} else {
						erro("ID");
					}
				} else {
					erro("ID");
				}
			} else if (tokens.get(cont).getLexema().equals(")")) {
				cont++;
			} else {
				erro(" )");

			}
		} else {
			erro("Tipo de Dados");
		}
	}

	private boolean isValor() {
		if (cont < tokens.size()) {
			if (tokens.get(cont).getToken().equals("Tok_Inteiro") || tokens.get(cont).getToken().equals("Tok_Float")
					|| tokens.get(cont).getToken().equals("ID")) {
				cont++;
				return true;
			} else {
				erro("Valor");
				return false;
			}
		} else {
			erro("Valor");
			return false;
		}
	}
	

}
