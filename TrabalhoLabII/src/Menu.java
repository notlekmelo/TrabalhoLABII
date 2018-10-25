
public class Menu {
	static CDicionario M1 = new CDicionario();
	static int m,n;
	static int qtd=1;
	
	public static void Adiciona(long cpf, String nome, String rua,int numCasa, String cidade, String bairro, String estado, int qtdDep, int tel,
			float rendaF){
		Morador novo = new Morador(cpf,nome, rua, numCasa, cidade, bairro,estado, qtdDep, tel, rendaF);
		if(qtd <= m) {
		M1.adiciona(novo, cpf);
		qtd++;
		}
		// método a completar.
	}
	
	public static void Parametros(int lista, int fila) {
		m = lista;
		n = fila;
	}
	
	public static void excluir() {
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
