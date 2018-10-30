import java.util.Scanner;

public class Menu {
	static Scanner sc = new Scanner(System.in);
	static CDicionario M1 = new CDicionario();
	static CDicionario M2 = new CDicionario();
	static CFila espera = new CFila();
	static int m,n, qtd=1;

	public static void CadastroMorador(){
		String nome, rua, bairro, estado, cidade;
		int cpf, qntd, renda, numCasa, telefone;

		System.out.println("--------------------------------------------------------------------------------");
		System.out.print("|Digite o nome do morador: ");
		nome = sc.nextLine();
		System.out.print("|Digite o cpf do morador(Apenas números): ");
		cpf = sc.nextInt();
		sc.nextLine();
		System.out.print("|Digite a quantidade de dependentes : ");
		qntd = sc.nextInt();
		sc.nextLine();
		System.out.print("|Digite a renda familiar: ");
		System.out.print("R$");
		renda = sc.nextInt();
		sc.nextLine();
		System.out.print("|Digite o telefone de contato: ");
		telefone = sc.nextInt();
		sc.nextLine();
		System.out.print("|DIgite sua Rua: ");
		rua = sc.nextLine();
		System.out.print("|Digite o numero na rua: ");
		numCasa = sc.nextInt();
		sc.nextLine();
		System.out.print("|Digite a sua Cidade: ");
		cidade = sc.nextLine();
		System.out.print("|Digite o seu bairro bairro: ");
		bairro = sc.nextLine();
		System.out.print("|Digite o seu estado: ");
		estado = sc.nextLine();
		System.out.println("--------------------------------------------------------------------------------");

		Adiciona(cpf, nome, rua, numCasa, cidade, bairro, estado, qntd, telefone, renda);
	}

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
			System.out.println("/n Digite 1 para sim e 2 para nao");
			escolha = sc.nextInt();
			switch(escolha) {
			case 1:
				M1.exclui(cpf);
				System.out.println("Excluido com sucesso");
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
				if(espera.contemMorador(cpf))
					System.out.println("O morador pesquisado esta na lista de espera.");
				else
					System.out.println("O morador nao esta na lista de espera.");
			}
		}
		if (!pesquisado.equals(null)){
			System.out.println(pesquisado + "Renda Familiar: " + pesquisado.getRendaF() + " Dependentes: " + pesquisado.getQtdDep());
		}
	}

	public static void Sorteio() {
		System.out.println("Escolha o numero de moradias a serem sorteadas na primeira faixa: ");
		int esc1 = sc.nextInt();
		sc.nextLine();
		System.out.println("Escolha o numero de moradias a serem sorteadas na Faixa 2: ");
		int esc2 = sc.nextInt();
		sc.nextLine();
		System.out.println("LISTAGEM DE MORADORES sorteados \n ================================ \n FAIXA 1");
		for(int i = 1; i>= esc1; i++)
			System.out.println(M1.retornaQualquer());
		System.out.println("LISTAGEM DE MORADORES sorteados \n ================================ \n FAIXA 2");
		for(int i = 1; i>= esc2; i++)
			System.out.println(M2.retornaQualquer());
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int escolha;
		do {
			System.out.println("--------------------------------------------------------------------------------");
			System.out.println("|  Menu de Opcoes                                                              |");
			System.out.println("|                                                                              |");
			System.out.println("| [1] Cadastrar morador                                                        |");
			System.out.println("| [2] Imprimir Lista de Morador cadastrado                                     |");
			System.out.println("| [3] Imprimir fila de espera                                                  |");
			System.out.println("| [4] Pesquisar Morador                                                        |");
			System.out.println("| [5] Desistancia/Exclusao                                                     |");
			System.out.println("| [6] Sorteio                                                                  |");
			System.out.println("| [7] Parametros                                                               |");
			System.out.println("|                                                                              |");
			System.out.println("| [8] Sair                                                                     |");
			System.out.println("--------------------------------------------------------------------------------");
			escolha = sc.nextInt();
			switch (escolha){
			case 1:
				// receber todos os parametros e passar para a funcao Adiciona
				sc.nextLine();
				CadastroMorador();
				break;
			case 2:
				sc.nextLine();
				Listar();
				break;
			case 3:
				System.out.println("escolha 3");
				break;
			case 4:
				// Receber o dado do cpf e enviar para a funcao Pesquisar
				System.out.println("escolha 4");
				break;
			case 5:
				// receber o cpf e enviar para excluir
				System.out.println("escolha 5");
				break;
			case 6:
				System.out.println("escolha 6");
				break;                   
			case 7:
				// perguntar o limite total das listas e da fila e enviar para Parametros
				System.out.println("escolha 7");
				break;                  
			}
		}while(escolha != 8);
		sc.close();
	}

	public static void Listar(){
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