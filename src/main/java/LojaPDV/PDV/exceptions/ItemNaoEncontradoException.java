package LojaPDV.PDV.exceptions;

import org.hibernate.query.NativeQuery.ReturnableResultNode;

public class ItemNaoEncontradoException extends RuntimeException {
	public ItemNaoEncontradoException() {
			super("Item não encontrado");
	}

}

