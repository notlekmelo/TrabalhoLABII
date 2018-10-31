public class Morador {

	private long cpf;
	private String nome, rua, cidade, bairro, estado, numCep, tel;
	private int  qtdDep, numCasa, numAp;
	private float rendaF;
	
	public long getCpf() {
		return cpf;
	}

	public String getNome() {
		return nome;
	}

	public String getRua() {
		return rua;
	}

	public String getCidade() {
		return cidade;
	}

	public String getBairro() {
		return bairro;
	}

	public String getEstado() {
		return estado;
	}

	public int getQtdDep() {
		return qtdDep;
	}

	public String getTel() {
		return tel;
	}

	public String getCep() {
		return numCep;
	}

	public int getNumCasa() {
		return numCasa;
	}

	public int getNumAp() {
		return numAp;
	}

	public float getRendaF() {
		return rendaF;
	}
	
	public Morador(long cpf, String nome, String rua, int numCasa, String cidade, String bairro, String estado, int numAp, int qtdDep, String tel, String numCep,
				   float rendaF) {
		//if (setCpf(cpf)) {
			this.cpf = cpf;
		//}
		this.nome = nome;
		this.rua = rua;
		this.numCasa = numCasa;
		this.cidade = cidade;
		this.bairro = bairro;
		this.estado = estado;
		this.qtdDep = qtdDep;
		this.numAp = numAp;
		this.tel = tel;
		this.numCasa = numCasa;
		this.numCep = numCep;
		this.rendaF = rendaF;
	}

	@Override
	public String toString() {
		return "|CPF " + cpf + " Nome " + nome + "\n|Qtde. Dependentes " + qtdDep + " - Renda Familiar " + rendaF + " \n|Telefone : " + tel + "\n|Rua " + rua + " "  + numCasa + ", AP " + numAp + " Bairro: " + bairro + "\n|Cidade: " + cidade + " Estado: " + estado +" CEP: " + numCep + "\n|";
	}

	public String ListagemSimples() {
		return "|CPF " + cpf + " - Nome " + nome + " - Renda Familiar: " + rendaF + "\n";
	}

//	public boolean setCpf(long cpf) {
//		long resto,multresult,nummult,soma=0,dig1,inicial,dig2;
//		boolean certo = false;
//		inicial = cpf/100;
//		nummult = 9;
//		dig2 = cpf%10;
//		dig1 = cpf%100 - dig2;
//		while (cpf>=1){
//			resto=cpf%10;
//			cpf=cpf/10;
//			multresult=resto*nummult;
//			soma=soma+multresult;
//			nummult=nummult-1;
//		}
//		if(dig1 == soma%11) {
//			cpf = (inicial*10)+dig1;
//			nummult=9;
//			soma=0;
//			while (cpf>0){
//				resto=cpf%10;
//				cpf=cpf/10;
//				multresult=resto*nummult;
//				soma=soma+multresult;
//				nummult=nummult-1;
//			}
//			if (dig2 == soma%11) {
//				certo = true;
//			}
//
//		}
//		return certo;
//	}
}
