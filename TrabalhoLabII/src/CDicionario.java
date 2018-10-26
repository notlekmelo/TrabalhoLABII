
public class CDicionario {
		private CCelulaDicionario primeira, ultima; 

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
			}
		} 
		
		public void exclui(Object chave) {
			boolean achou = false;   
			if (primeira != ultima) {  
				for (CCelulaDicionario aux = primeira; aux != null && !achou; aux = aux.prox) {       
					achou = aux.prox.key.equals(chave);
					if(achou) {
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
}
