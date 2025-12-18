package LojaPDV.PDV.service;

import java.util.List;

import org.springframework.aop.ThrowsAdvice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import LojaPDV.PDV.entity.Produto;
import LojaPDV.PDV.repository.ProdutoRepository;

//regra de negocio e orquestra ações
@Service
public class ProdutoService {
	
	//objeto repository pra q eu possa conjurar os metodos de persistencia
	private final ProdutoRepository repo;
	public ProdutoService(ProdutoRepository repo) {
		this.repo = repo;
	}
	//metodo cadastro
	public String save(Produto p) {
		ValidarProduto(p);
		this.repo.save(p);
		return "Produto Salvo com sucesso.";
	}
	//metodo de listagem de todos objetos
	public List<Produto> findAll(){
		List<Produto> lista = this.repo.findAll();
		return lista;
	}
	//metodo de listagem individual
	public Produto findById(long id) {
		Produto produto = this.repo.findById(id).orElseThrow(() -> new RuntimeException("Erro! Não existe este Produto"));
		return produto;
		
	}
	//metodo de deletar
	public String delete(long id) {
		this.repo.deleteById(id);
		return "Produto deletado com sucesso.";
	}
	//metodo de atualizar
	public String update(Produto p, long id) {
		p.setId(id);
		ValidarProduto(p);
		this.repo.save(p);
		return "Produto Atualizado.";
	}
	//metodo de validação de estoque e preço
	public void ValidarProduto(Produto p) {
		if(p.getPreco() <= 0) {
			throw new IllegalArgumentException("Não pode preço negativo");
		}
		if(p.getEstoque() <= 0) {
			throw new IllegalArgumentException("Não pode preço estoque negativo");
		}
	}
	

}
