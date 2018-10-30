
public class CCelulaDicionario{  
	// Atributos  public 
	Object key, value;  
	public CCelulaDicionario prox;
	
	// Construtora que anula os tres atributos da celula
	public CCelulaDicionario(){    
		key = null;
		value = null;
		prox = null;
	}
	
	// Construtora que inicializa key e value com os argumentos passados  // por parametro e anula a referencia e proxima celula
	public CCelulaDicionario(Object chave, Object valor){ 
		key = chave;   
		value = valor;
		prox = null; 
	}
	
	// Construtora que inicializa todos os atribulos da celula com os argumentos  // passados por parametro
	public CCelulaDicionario(Object chave, Object valor, CCelulaDicionario proxima){    
		key = chave;
		value = valor;
		prox = proxima;  
		} 
}
