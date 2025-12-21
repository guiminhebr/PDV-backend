package LojaPDV.PDV.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import LojaPDV.PDV.entity.Produto;
import LojaPDV.PDV.service.ProdutoService;

@RestController
@RequestMapping("/produto")
@CrossOrigin("*")
public class ProdutoController {
	// conjurar métodos do service
	private final ProdutoService service;

	public ProdutoController(ProdutoService service) {
		this.service = service;
	}
	//end point de salvamento de produto
	@PostMapping("/save")
	public ResponseEntity<String> save(@RequestBody Produto p) {
		try {
			String mensagem = this.service.save(p);
			return new ResponseEntity<String>(mensagem, HttpStatus.OK);
			
		}catch(Exception e) {
			return new ResponseEntity<String>("Cadastro Inválido", HttpStatus.BAD_REQUEST);
			
		}
	}
	
	// endpoint pra listar todos produtos
	@GetMapping("/findAll")
	public ResponseEntity<List<Produto>> findAll() {
		List<Produto> lista = this.service.findAll();
	    return ResponseEntity.ok(lista);
	}
	
	//endpoint lista um só produto
	@GetMapping("/findById/{id}")
	public ResponseEntity<Produto> findById(@PathVariable long id) {
		try {
			Produto produto = this.service.findById(id);
			return new ResponseEntity<Produto>(produto, HttpStatus.OK);
			
			
		}catch(Exception e) {
			Produto produto = null;
			return new ResponseEntity<Produto>(produto, HttpStatus.BAD_REQUEST);
		}
	}
	//endpoint deletar produto
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> delete(@PathVariable long id) {
		try {
			String mensagem = this.service.delete(id);
			return new ResponseEntity<String>(mensagem, HttpStatus.OK);
			
			
		}catch(Exception e) {
			
			return new ResponseEntity<String>("Deleção inválida.", HttpStatus.BAD_REQUEST);
		}
	}
	@PutMapping("/update/{id}")
	public ResponseEntity<String> update(@RequestBody Produto p, @PathVariable long id) {
		try {
			String mensagem = this.service.update(p, id);
			return new ResponseEntity<String>(mensagem, HttpStatus.OK);
			
			
		}catch(Exception e) {
			
			return new ResponseEntity<String>("Atualização inválida.", HttpStatus.BAD_REQUEST);
		}
	}
	


}
