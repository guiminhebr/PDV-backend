package LojaPDV.PDV.exceptions;

public class ProdutoNaoExisteException extends RuntimeException {
	public ProdutoNaoExisteException() {
		super("Produto não existe");
	}

}
