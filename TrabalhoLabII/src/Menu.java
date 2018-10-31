import java.util.Scanner;

public class Menu {
	static Scanner sc = new Scanner(System.in);
	static CDicionario M1 = new CDicionario();
	static CDicionario M2 = new CDicionario();
	static CFila espera = new CFila();
	static int m,n, qtd=1;

	public static void CadastroMorador(){
		String nome, rua, bairro, estado, cidade, telefone, cep;
		long cpf;
		int qntd,numCasa, numAp;
		float renda;

		System.out.println("|------------------------------------------------------------------------------|");
		System.out.print("|Digite o nome do morador: ");
		nome = sc.nextLine();
		System.out.print("|Digite o cpf do morador(Apenas n�meros): ");
		cpf = sc.nextInt();
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
		System.out.print("|Digite o numero na rua: ");
		numCasa = sc.nextInt();
		sc.nextLine();
		System.out.print("|Digite o numero do apartamento: ");
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
	}

	public static void Adiciona(long cpf, String nome, String rua, int numCasa, String cidade, String bairro, String estado, int numAp, int qtdDep, String tel, String cep, float rendaF){
		Morador novo = new Morador(cpf, nome, rua, numCasa, cidade, bairro, estado, numAp, qtdDep, tel, cep, rendaF);
		if(rendaF <= 954.0) {
			if(qtd <= m) {
				M1.adiciona(cpf,novo);
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
					M2.adiciona(cpf,novo);
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
		System.out.println("Tem certeza que deseja excluir: ");
		int faixa = Pesquisar(cpf);
		switch(faixa) {
		case 1:
		int escolha;
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
				break;
			}
			break;
		case 2:
				System.out.println("/n Digite 1 para sim e 2 para nao");
				escolha = sc.nextInt();
				switch(escolha) {
				case 1:
					M2.exclui(cpf);
					System.out.println("Excluido com sucesso");
					if(espera.quantidade()>0) {
						Morador transferido = (Morador)espera.desenfileira();
						M2.adiciona(transferido.getCpf(),transferido);
					}
						qtd--;
					break;
				case 2:
					System.out.println("Cancelando...");
					System.out.println("pressione alguma tecla para continuar.");
					sc.nextLine();
					break;
				}
				break;
			}

	}

	public static int Pesquisar(long cpf){
		Morador pesquisado = (Morador)M1.recebeValor(cpf);
		int faixa=0;
		if (pesquisado.equals(null)){
			pesquisado = (Morador)M2.recebeValor(cpf);
			if (pesquisado.equals(null)){
				if(espera.contemMorador(cpf))
					System.out.println("O morador pesquisado esta na lista de espera.");
				else
					System.out.println("O morador nao esta na lista de espera.");
			}
			else faixa = 2;
		}
		else faixa = 1;
		if (!pesquisado.equals(null)){
			System.out.println(pesquisado + "Renda Familiar: " + pesquisado.getRendaF() + " Dependentes: " + pesquisado.getQtdDep());
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
				CadastroMorador();
				break;
			case 2:
				Listar();
				break;
			case 3:
				espera.mostra();
				break;
			case 4:
				System.out.println("|------------------------------------------------------------------------------|");
				System.out.print("|Digite o CPF do morador a ser pesquisado: ");
				long cpf = sc.nextLong();
				sc.nextLine();
				System.out.println("|------------------------------------------------------------------------------|");
				Pesquisar(cpf);
				break;
			case 5:
				System.out.println("|------------------------------------------------------------------------------|");
				System.out.print("|Digite o CPF do morador a ser exclu�do: ");
				cpf = sc.nextLong();
				sc.nextLine();
				System.out.println("|------------------------------------------------------------------------------|");
				excluir(cpf);
				break;
			case 6:
				Sorteio();
				break;                   
			case 7:
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
				M1.mostraSimples();
				escolha = 0;
				break;
			case 2:
				M1.mostraCompleto();
				escolha = 0;
				break;
			}
		}while(escolha != 0);
	}

	private static void jump() {
		for (int i = 0; i < 20; i++)
			System.out.println();
		System.out.println();
	}

}