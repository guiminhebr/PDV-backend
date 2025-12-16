package LojaPDV.PDV.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import LojaPDV.PDV.entity.Produto;

//Acesso ao banco de dados
public interface ProdutoRepository extends JpaRepository<Produto, Long>{

}
