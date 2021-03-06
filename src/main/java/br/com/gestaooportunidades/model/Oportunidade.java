package br.com.gestaooportunidades.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Oportunidade {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "oportunidade_seq")
	@SequenceGenerator(sequenceName = "oportunidade_id_seq", allocationSize = 1, name = "oportunidade_seq")
	private Long idOportunidade;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_empresa")
	private Empresa empresa;
	
	private String nome;
	
	private String descricao;

	private String valorString;
	
	public Date dataAprovacao;
	
	public Date dataReprovacao;
	
	public Date dataCancelamento;
	
	@Transient
	public boolean cadidatoOportunidade;

	public boolean isPendenteAprovacao() {
		return dataReprovacao == null && dataAprovacao == null;
	}
	
	public String status() {
		if(dataReprovacao != null)
			return "Reprovada";
		if(dataReprovacao == null && dataAprovacao == null)
			return "Pendente de Aprova��o";
		if(dataCancelamento != null)
			return"Cancelada";
		return "Ativa";
	}
	
}
