package LojaPDV.PDV.entity;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
@Entity
public class Venda {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private LocalDateTime dataHora;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public LocalDateTime getDataHora() {
		return dataHora;
	}
	public void setDataHora(LocalDateTime dataHora) {
		this.dataHora = dataHora;
	}
	public StatusVenda getStatus() {
		return status;
	}
	public void setStatus(StatusVenda status) {
		this.status = status;
	}
	public List<ItemVenda> getLista() {
		return lista;
	}
	public void setLista(List<ItemVenda> lista) {
		this.lista = lista;
	}
	@Enumerated(EnumType.STRING)
	private StatusVenda status;
	//uma venda contem varios itemvenda
	@OneToMany(mappedBy = "venda", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<ItemVenda> lista = new ArrayList<>();
	
	private double total;

	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}

}
