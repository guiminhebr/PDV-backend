package LojaPDV.PDV.service;

import java.time.LocalDateTime;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import LojaPDV.PDV.entity.ItemVenda;
import LojaPDV.PDV.entity.Produto;
import LojaPDV.PDV.entity.StatusVenda;
import LojaPDV.PDV.entity.Venda;
import LojaPDV.PDV.repository.ItemVendaRepository;
import LojaPDV.PDV.repository.ProdutoRepository;
import LojaPDV.PDV.repository.VendaRepository;
import jakarta.transaction.Transactional;

@Service
public class VendaService {
	
	@Autowired
	private VendaRepository repoVenda; //-> venda cria e fecha
	@Autowired
	private ProdutoRepository repoProduto; //-> valida estoque e preço
	@Autowired
	private ItemVendaRepository repoItemVenda; //-> adiciona itens e calcula acrescismos e descontos
	
	//1)abre a venda
	//2)adicione itens
	//3)calcule o total
	//4)finaliza ou cancela. os metodos inicialmente são ações, não crud
	
	public Venda abrirVenda() {
		Venda venda = new Venda(); //crio o objeto venda
		venda.setDataHora(LocalDateTime.now()); //seto que a data e hora wue se inicializou a venda seja o atual instante
		venda.setStatus(StatusVenda.ABERTA); //seto que a venda está aberta
		venda.setTotal(0.0); //valor 0 na venda, ainda não adicionado produtos
		
		return this.repoVenda.save(venda); //retorna a venda pro fornt consumir o ID.
				
		
	}
	@Transactional //ou tudo salva ou tudo não salva
	public void adicionarItem(Long vendaId, Long produtoId, int quantidade) {
		//puxo a venda que acabei de faezr acima utilizando o findByid
		Venda venda = repoVenda.findById(vendaId).orElseThrow(() -> new RuntimeException("Não existe essa venda"));
		//verifico se a venda está aberta, se não tiver jogo uma exceção.
		if (venda.getStatus() != StatusVenda.ABERTA) {
			throw new RuntimeException("Venda não está aberta");
		}
		//criar e puxar o objeto do produto que quero adicionar.
		Produto produto = this.repoProduto.findById(produtoId).orElseThrow(() -> new RuntimeException("Não existe esse produto"));
		
		//valida se o estoque está de acordo com a quantidade (lembrando que a requisição já trouxe a quantidade do item pra validar)
		if (produto.getEstoque() < quantidade) {
			throw new RuntimeException("Estoque insuficiente");

		}
		ItemVenda item = new ItemVenda();
		item.setVenda(venda);
		item.setProduto(produto);
		item.setQuantidade(quantidade);
		item.setPrecoUnitario(produto.getPreco());
		item.setDesconto(0);
		item.setAcrescimo(0);
		
		
		
		double subtotal = quantidade*produto.getPreco();
		item.setSubtotal(subtotal);
		repoItemVenda.save(item);
		
		//atualizo o estoque do produto em questão
		produto.setEstoque(produto.getEstoque() - quantidade);
		repoProduto.save(produto);
		
		//atualiza o total da venda
		venda.setTotal(venda.getTotal()+subtotal);
		venda.getLista().add(item); //garante que não de erro de exceção
		repoVenda.save(venda);
		
			
		
	}
	public void finalizarVenda(Long vendaId) {
		Venda venda = this.repoVenda.findById(vendaId).orElseThrow(() -> new RuntimeException("Venda não encontrada"));
		
		if (venda.getLista().isEmpty()) {
			throw new RuntimeException("Venda sem Itens");
		}
		venda.setStatus(StatusVenda.FINALIZADA);
		this.repoVenda.save(venda);
		
	}

}
