package LojaPDV.PDV.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import DTO.ItemVendaDTO;
import LojaPDV.PDV.entity.ItemVenda;
import LojaPDV.PDV.entity.Venda;
import LojaPDV.PDV.service.VendaService;

@RestController
@RequestMapping("/venda")
public class VendaController {
	@Autowired
	private VendaService service;
	
	@PostMapping("/abrir")
	public Venda abrirVenda() {
		return service.abrirVenda();
	}
	@PostMapping("/vendas/{vendaId}/itens")
	public ResponseEntity<?> adicionarItem(@PathVariable Long vendaId, @RequestBody ItemVendaDTO itemdto){
		service.adicionarItem(vendaId, itemdto.getProdutoId(), itemdto.getQuantidade());
		return ResponseEntity.ok("Item adicionado");
	}
	
	@PostMapping("/vendas/{id}/finalizar")
	public ResponseEntity<?> finalizar(@PathVariable Long id) {
	    service.finalizarVenda(id);
	    return ResponseEntity.ok("Venda finalizada");
	}

}
