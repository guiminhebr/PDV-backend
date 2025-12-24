package LojaPDV.PDV.exceptions;

public class EstoqueInsuficienteException extends RuntimeException {
	public EstoqueInsuficienteException() {
		super("Estoque insuficiente para a quantidade informada");
	}

}
