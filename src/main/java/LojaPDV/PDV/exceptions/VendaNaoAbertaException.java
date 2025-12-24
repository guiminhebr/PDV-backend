package LojaPDV.PDV.exceptions;

import java.security.PublicKey;

public class VendaNaoAbertaException extends RuntimeException {
	public VendaNaoAbertaException() {
		super("Venda já foi finalizada.");
	}
}
