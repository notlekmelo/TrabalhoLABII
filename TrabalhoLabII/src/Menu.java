import java.util.Scanner;
public class Menu {
	static Scanner sc = new Scanner(System.in);
	static CDicionario M1 = new CDicionario();
	static CDicionario M2 = new CDicionario();
	static CFila espera = new CFila();
	static int m,n, qtd=1;
	public static void Adiciona(long cpf, String nome, String rua,int numCasa, String cidade, String bairro, String estado, int qtdDep, int tel,
			float rendaF){
		Morador novo = new Morador(cpf,nome, rua, numCasa, cidade, bairro,estado, qtdDep, tel, rendaF);
		if(rendaF <= 954.0) {
			if(qtd <= m) {
				M1.adiciona(novo, cpf);
				qtd++;
			}
			else {
				if(qtd>m && qtd <=n){
					espera.enfileira(novo);
					qtd++;
				}
			}
		}
		else {
			if(rendaF > 954.0 && rendaF < 2862.0) {
				if(qtd <= m) {
					M2.adiciona(novo, cpf);
					qtd++;
				}
				else {
					if(qtd>m && qtd <=n){
						espera.enfileira(novo);
						qtd++;
					}
				}
			}
		}
	}
	public static void Parametros(int lista, int fila) {
		m = lista;
		n = fila + lista;
	}
	public static void excluir(long cpf) {
		int escolha;
		do {
			System.out.println("Tem certeza que deseja excluir: ");
			System.out.println(M1.recebeValor(cpf));
			System.out.println("/n Digite 1 para sim e 2 para não");
			escolha = sc.nextInt();
			switch(escolha) {
			case 1:
				M1.exclui(cpf);
				System.out.println("Excluído com sucesso");
				if(espera.quantidade()>0) {
					Morador transferido = (Morador)espera.desenfileira();
					M1.adiciona(transferido.getCpf(),transferido);
				}
				else {
					qtd--;
				}
				break;
			case 2:
				System.out.println("Cancelando...");
				System.out.println("pressione alguma tecla para continuar.");
				sc.nextLine();
			}
		} while (escolha != 1 || escolha != 2);
	}

	public static void Pesquisar(long cpf){
		Morador pesquisado = (Morador)M1.recebeValor(cpf);
		if (pesquisado.equals(null)){
			pesquisado = (Morador)M2.recebeValor(cpf);
			if (pesquisado.equals(null)){
				if(espera.contem(cpf)) // vai dar errado porque tem que comprar com cpf não com o que está na lista
					System.out.println("O morador pesquisado está na lista de espera.");
				else
					System.out.println("O morador não está na lista de espera.");
			}
			if (!pesquisado.equals(null)){
				System.out.println();

			}
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int escolha;
		do {
			System.out.println("--------------------------------------------------------------------------------");
			System.out.println("|  Menu de Opções                                                              |");
			System.out.println("|                                                                              |");
			System.out.println("| [1] Cadastrar moderador                                                      |");
			System.out.println("| [2] Imprimir Lista de Moderador cadastrado                                   |");
			System.out.println("| [3] Imprimir fila de espera                                                  |");
			System.out.println("| [4] Pesquisar Moderador                                                      |");
			System.out.println("| [5] Desistência/Exclusão                                                     |");
			System.out.println("| [6] Sorteio                                                                  |");
			System.out.println("| [7] Parâmetros                                                               |");
			System.out.println("|                                                                              |");
			System.out.println("| [0] Sair                                                                     |");
			System.out.println("--------------------------------------------------------------------------------");
			escolha = sc.nextInt();
			switch (escolha){
			case 1:
				System.out.println("escolha 1");
				break;
			case 2:
				Menu2();
				break;
			case 3:
				System.out.println("escolha 3");
				break;
			case 4:
				System.out.println("escolha 4");
				break;
			case 5:
				System.out.println("escolha 5");
				break;
			case 6:
				System.out.println("escolha 6");
				break;                   
			case 7:
				System.out.println("escolha 7");
				break;                  
			}
		}while(escolha != 0);



		sc.close();
	}

	public static void Menu2(){
		int escolha;
		do {
			System.out.println("--------------------------------------------------------------------------------");
			System.out.println("|  Menu de Listagens                                                           |");
			System.out.println("|                                                                              |");
			System.out.println("| [1] Listagem Simples(CPF e Nome)                                             |");
			System.out.println("| [2] Listagem Completa(todos os dados)                                        |");
			System.out.println("|                                                                              |");
			System.out.println("--------------------------------------------------------------------------------");
			escolha = sc.nextInt();
			switch (escolha){
			case 1:
				System.out.println("escolha 2.1");
				escolha = 0;
				break;
			case 2:
				System.out.println("escolha 2.2");
				escolha = 0;
				break;
			}
		}while(escolha != 0);
	}

}


