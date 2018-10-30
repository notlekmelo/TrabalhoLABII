import java.util.Random;

public class CDicionario {
		private CCelulaDicionario primeira, ultima; 
		private int indice;

		public int getIndice() {
			return indice;
		}

		public CDicionario(){ 
			primeira = new CCelulaDicionario();   
			ultima = primeira;  
		} 

		public boolean vazio(){   
			return primeira==ultima;  
		} 

		public void adiciona(Object chave, Object valor){   
			boolean achou = false;   
			if (primeira != ultima) {  
				for (CCelulaDicionario aux = primeira.prox; aux != null && !achou; aux = aux.prox)        
					achou = aux.key.equals(chave);  
			} 
			if (!achou) {    
				ultima.prox = new CCelulaDicionario(chave,valor);   
				ultima = ultima.prox;
				indice++;
			}
		} 
		
		public void exclui(Object chave) {
			boolean achou = false;   
			if (primeira != ultima) {  
				for (CCelulaDicionario aux = primeira; aux != null && !achou; aux = aux.prox) {       
					achou = aux.prox.key.equals(chave);
					if(achou) {
						indice--;
						aux.prox = aux.prox.prox;
					}
				}
			} 
		}
		
		public Object recebeValor(Object chave){   
			boolean achou = false;  
			Object item = null;  
			if (primeira != ultima) {    
				for (CCelulaDicionario aux = primeira.prox; aux != null && !achou; aux = aux.prox) {   
					achou = aux.key.equals(chave);      
					if (achou)          item = aux.value;  
				}  
			}     
			return item; 
		} 
		
		public Object retornaQualquer(){   
			Object item = null;  
			Random ret = new Random();
			int escolha = ret.nextInt(indice);
			CCelulaDicionario aux = primeira.prox;
			if (primeira != ultima) {    
				for (int i = 1;i >= escolha ; aux = aux.prox) 
					i++;
				item = aux.value;
			}     
			return item;
		}

	public void mostra() {
		System.out.print("[ ");
		for (CCelulaDicionario c = primeira.prox; c != null; c = c.prox)
			System.out.print(c.value + " ");
		System.out.println("] ");
	}
}
