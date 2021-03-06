import java.util.Scanner;

public class Menu {
	static Scanner sc = new Scanner(System.in);
	static CDicionario M1 = new CDicionario();
	static CDicionario M2 = new CDicionario();
	static CFila espera = new CFila();
	static int m,n, qtd=0;

	public static void CadastroMorador(){
		String nome, rua, bairro, estado, cidade, telefone, cep;
		long cpf;
		int qntd,numCasa, numAp;
		float renda;

		System.out.println("|------------------------------------------------------------------------------|");
		System.out.print("|Digite o nome do morador: ");
		nome = sc.nextLine();
		System.out.print("|Digite o cpf do morador(Apenas n�meros): ");
		cpf = sc.nextLong();
		sc.nextLine();
		System.out.print("|Digite a quantidade de dependentes : ");
		qntd = sc.nextInt();
		sc.nextLine();
		System.out.print("|Digite a renda familiar: ");
		System.out.print("R$");
		renda = sc.nextFloat();
		sc.nextLine();
		System.out.print("|Digite o telefone de contato: ");
		telefone = sc.nextLine();
		System.out.print("|Digite sua Rua: ");
		rua = sc.nextLine();
		System.out.print("|Digite o n�mero na rua: ");
		numCasa = sc.nextInt();
		sc.nextLine();
		System.out.print("|Digite o n�mero do apartamento: ");
		numAp = sc.nextInt();
		sc.nextLine();
		System.out.print("|Digite a sua Cidade: ");
		cidade = sc.nextLine();
		System.out.print("|Digite o seu bairro : ");
		bairro = sc.nextLine();
		System.out.print("|Digite o seu estado: ");
		estado = sc.nextLine();
		System.out.print("|Digite o seu CEP: ");
		cep = sc.nextLine();
		System.out.println("|------------------------------------------------------------------------------|");
		Adiciona(cpf, nome, rua, numCasa, cidade, bairro, estado, numAp, qntd, telefone, cep, renda);
		jump();
	}

	public static void Adiciona(long cpf, String nome, String rua, int numCasa, String cidade, String bairro, String estado, int numAp, int qtdDep, String tel, String cep, float rendaF){
		if(rendaF <= 954.0) {
			Morador novo = new Morador(cpf, nome, rua, numCasa, cidade, bairro, estado, numAp, qtdDep, tel, cep, rendaF, 1);
			if(qtd < m) {
				M1.adiciona(cpf,novo);
				qtd++;
			}
			else {
				if(qtd>(m-1) && qtd < n){
					espera.enfileira(novo);
					qtd++;
				}
			}
		}
		else {
			if(rendaF > 954.0 && rendaF < 2862.0) {
				Morador novo = new Morador(cpf, nome, rua, numCasa, cidade, bairro, estado, numAp, qtdDep, tel, cep, rendaF,2);
				if(qtd < m) {
					M2.adiciona(cpf,novo);
					qtd++;
				}
				else {
					if(qtd>(m-1) && qtd < n){
						espera.enfileira(novo);
						qtd++;
					}
				}
			}
			else System.out.println("Morador com renda familiar muito elevada.");
		}
		System.out.println("Pressione  Enter para continuar.");
		sc.nextLine();
	}

	public static void Parametros(int lista, int fila) {
		m = lista;
		n = fila + lista;
		System.out.println("Pressione  Enter para continuar.");
		sc.nextLine();
		jump();
	}

	public static void excluir(long cpf) {
		System.out.println("Tem certeza que deseja excluir: ");
		int faixa = Pesquisar(cpf);
		switch(faixa) {
		case 1:
			int escolha;
			System.out.println("\n Digite 1 para sim e 2 para nao");
			escolha = sc.nextInt();
			switch(escolha) {
			case 1:
				M1.exclui(cpf);
				qtd--;
				if(espera.quantidade()>0) {
					Morador transferido = (Morador)espera.desenfileira();
					if(transferido.getFaixa() == 1)
						M1.adiciona(transferido.getCep(), transferido);
					else M2.adiciona(transferido.getFaixa(), transferido);
				}
				break;
			case 2:
				System.out.println("Cancelando...");
				break;
			}
			break;
		case 2:
			System.out.println("\n Digite 1 para sim e 2 para nao");
			escolha = sc.nextInt();
			switch(escolha) {
			case 1:
				M2.exclui(cpf);
				System.out.println("Excluido com sucesso");
				qtd--;
				if(espera.quantidade()>0) {
					Morador transferido = (Morador)espera.desenfileira();
					if(transferido.getFaixa() == 1)
						M1.adiciona(transferido.getCep(), transferido);
					else M2.adiciona(transferido.getFaixa(), transferido);
				}
				break;
			case 2:
				System.out.println("Cancelando...");
				break;
			}
			break;
		}
		System.out.println("Pressione  Enter para continuar.");
		sc.nextLine();
		jump();
	}

	public static int Pesquisar(long cpf){
		Morador pesquisado = (Morador)M1.recebeValor(cpf);
		int faixa=0;
		if (pesquisado == null){
			pesquisado = (Morador)M2.recebeValor(cpf);
			if (pesquisado==null){
				if(espera.contemMorador(cpf))
					System.out.println("O morador pesquisado est� na lista de espera.");
				else
					System.out.println("O morador n�o est� na lista de espera.");
			}
			else faixa = 2;
		}
		else faixa = 1;
		if (pesquisado != null){
			System.out.println(pesquisado);
		}
		return faixa;
	}

	public static void Sorteio() {
		System.out.println("Escolha o numero de moradias a serem sorteadas na primeira faixa: ");
		int esc1 = sc.nextInt();
		sc.nextLine();
		System.out.println("Escolha o numero de moradias a serem sorteadas na Faixa 2: ");
		int esc2 = sc.nextInt();
		sc.nextLine();
		System.out.println("LISTAGEM DE MORADORES sorteados \n ================================ \n FAIXA 1");
		for(int i = 0; i< esc1; i++)
			System.out.println(M1.retornaQualquer());
		System.out.println("LISTAGEM DE MORADORES sorteados \n ================================ \n FAIXA 2");
		for(int i = 0; i< esc2; i++)
			System.out.println(M2.retornaQualquer());
		System.out.println("Pressione  Enter para continuar.");
		sc.nextLine();
		jump();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int escolha;
		do {
			System.out.println("|------------------------------------------------------------------------------|");
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
			System.out.println("|------------------------------------------------------------------------------|");
			escolha = sc.nextInt();
			sc.nextLine();
			switch (escolha){
			case 1:
				jump();
				CadastroMorador();
				break;
			case 2:
				jump();
				Listar();
				break;
			case 3:
				jump();
				espera.mostra();
				break;
			case 4:
				jump();
				System.out.println("|------------------------------------------------------------------------------|");
				System.out.print("|Digite o CPF do morador a ser pesquisado: ");
				long cpf = sc.nextLong();
				sc.nextLine();
				System.out.println("|------------------------------------------------------------------------------|");
				Pesquisar(cpf);
				break;
			case 5:
				jump();
				System.out.println("|------------------------------------------------------------------------------|");
				System.out.print("|Digite o CPF do morador a ser exclu�do: ");
				cpf = sc.nextLong();
				sc.nextLine();
				System.out.println("|------------------------------------------------------------------------------|");
				excluir(cpf);
				break;
			case 6:
				jump();
				Sorteio();
				break;                   
			case 7:
				jump();
				System.out.println("|------------------------------------------------------------------------------|");
				System.out.print("|Qual o limite total para as Faixas?: ");
				int m = sc.nextInt();
				sc.nextLine();
				System.out.print("|Qual o limite da lista de espera?: ");
				int n = sc.nextInt();
				sc.nextLine();
				System.out.println("|------------------------------------------------------------------------------|");
				Parametros(m,n);
				break;                  
			}
		}while(escolha != 8);
		sc.close();
	}

	public static void Listar(){
		int escolha;
		do {
			System.out.println("|------------------------------------------------------------------------------|");
			System.out.println("|  Menu de Listagens                                                           |");
			System.out.println("|                                                                              |");
			System.out.println("| [1] Listagem Simples(CPF e Nome)                                             |");
			System.out.println("| [2] Listagem Completa(todos os dados)                                        |");
			System.out.println("|                                                                              |");
			System.out.println("|------------------------------------------------------------------------------|");
			escolha = sc.nextInt();
			switch (escolha){
			case 1:
				System.out.println("|------------------------------------------------------|");
				System.out.println("|LISTAGEM DE MORADORES (PAGINA 1)                      |");
				System.out.println("|================================                      |");
				System.out.println("|FAIXA 1                                               |");
				M1.mostraSimples();
				System.out.println("|FAIXA 2                                               |");
				M2.mostraSimples();
				System.out.println("|------------------------------------------------------|");
				escolha = 0;
				break;
			case 2:
				System.out.println("|------------------------------------------------------|");
				System.out.println("|LISTAGEM DE MORADORES (PAGINA 1)                      |");
				System.out.println("|================================                      |");
				System.out.println("|FAIXA 1                                               |");
				M1.mostraCompleto();
				System.out.println("\n|FAIXA 2                                               |");

				M2.mostraCompleto();
				System.out.println("|------------------------------------------------------|");
				escolha = 0;
				break;
			}
		}while(escolha != 0);
		System.out.println("Pressione  Enter para continuar.");
		sc.nextLine();
		jump();
	}

	private static void jump() {
		for (int i = 0; i < 20; i++)
			System.out.println();
		System.out.println();
	}

}