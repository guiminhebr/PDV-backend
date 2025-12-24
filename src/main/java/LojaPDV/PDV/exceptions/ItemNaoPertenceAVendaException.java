package LojaPDV.PDV.exceptions;

public class ItemNaoPertenceAVendaException extends RuntimeException {
	public ItemNaoPertenceAVendaException() {
		super("Item não pertence a esta venda");
	}

}
