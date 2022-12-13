/*
 * Sistema de Auxílio ao Módulo de Monitoramento
 * Copyright (c) 2022 - Todos os direitos reservados.
 */
package br.com.jonatassn.samm.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

/**
 * @author Jonatas Silveira - <jonatassn.com.br>
 *
 */
@Data
@Table(name="tb_especimes", schema="samm")
@Entity
public class Especime implements Serializable {
	public static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="cd_especime")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="nm_especime", length=100)
	private String nome;
	
	@Column(name="idade_especime", length=1000)
	private Integer idade;
	
	@Column(name="sexo_especime")
	private String sexo;
	
	@Column(name="info_biometricas", length=45)
	private String informacoesBiometricas;
	
	@OneToOne
	@JoinColumn(name="cd_pesquisador")
	private Pesquisador pesquisador;
	
	@OneToOne
	@JoinColumn(name="cd_tag")
	private Tag tag;
	
	@Column(name="is_ativo")
	private Boolean isAtivo;
	
	@Column(name="criado_em")
	@Temporal(TemporalType.TIMESTAMP)
	private Date criadoEm;
	
	@Column(name="criado_por")
	private String criadoPor;
	
	@Column(name="alterado_em")
	@Temporal(TemporalType.TIMESTAMP)
	private Date alteradoEm;
	
	@Column(name="alterado_por")
	private String alteradoPor;
}
