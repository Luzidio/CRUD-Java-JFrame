package controller;
import java.util.ArrayList;
import Model.Estudante;
public class ControllerEstudante {

	static ArrayList<Estudante> estudantes = new ArrayList<Estudante>();
	public static void adicionar(int codigo, String nome, double nota1, double nota2) {
		estudantes.add(new Estudante(codigo,nome, nota1, nota2));
	}
	
	public static void remover(int posicao) {
		estudantes.remove(posicao);
	}
	
	public static void editar(int position, int codigo, String nome, double nota1, double nota2) {
		Estudante estudante = estudantes.get(position);
		estudante.setCodigo(codigo);
		estudante.setNome(nome);
		estudante.setNota1(nota1);
		estudante.setNota2(nota2);
	}
	
	public static ArrayList<Estudante> listar() {
	    return estudantes;
	}
}
