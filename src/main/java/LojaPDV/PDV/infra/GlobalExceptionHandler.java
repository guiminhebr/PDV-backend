package LojaPDV.PDV.infra;

import org.hibernate.query.NativeQuery.ReturnableResultNode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import LojaPDV.PDV.exceptions.EstoqueInsuficienteException;
import LojaPDV.PDV.exceptions.ItemNaoEncontradoException;
import LojaPDV.PDV.exceptions.ItemNaoPertenceAVendaException;
import LojaPDV.PDV.exceptions.ProdutoNaoExisteException;
import LojaPDV.PDV.exceptions.VendaNaoAbertaException;
import LojaPDV.PDV.exceptions.VendaNaoEncontradaException;
import LojaPDV.PDV.exceptions.VendaSemItensException;
@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(VendaNaoEncontradaException.class)
	public ResponseEntity<String> handleVendaNaoEncontrada(VendaNaoEncontradaException ex){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	}
	@ExceptionHandler(VendaNaoAbertaException.class)
	public ResponseEntity<String> handleVendaNaoAberta(VendaNaoAbertaException ex){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	}
	@ExceptionHandler(EstoqueInsuficienteException.class)
	public ResponseEntity<String> handleEstoqueInsuficiente(EstoqueInsuficienteException ex){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	}
	@ExceptionHandler(ItemNaoPertenceAVendaException.class)
	public ResponseEntity<String> handleItemNaoPertenceAVenda(ItemNaoPertenceAVendaException ex){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	}
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleErroGenerico(Exception e){
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno do servidor");
	}
	@ExceptionHandler(ProdutoNaoExisteException.class)
	public ResponseEntity<String> handleProdutoNaoExiste(ProdutoNaoExisteException ex){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	}
	@ExceptionHandler(VendaSemItensException.class)
	public ResponseEntity<String> handleVendaSemItens(VendaSemItensException ex){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	}
	@ExceptionHandler(ItemNaoEncontradoException.class)
	public ResponseEntity<String> handleItemNaoEncontrado(ItemNaoEncontradoException ex){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	}
	

}
