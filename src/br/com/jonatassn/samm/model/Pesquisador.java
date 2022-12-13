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
@Table(name="tb_pesquisadores", schema="samm")
@Entity
public class Pesquisador implements Serializable{
	public static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="cd_pesquisador")
	private Long id;
	
	@OneToOne
	@JoinColumn(name="cd_usuario", nullable=false)
	private Usuario usuario;
	
	@Column(name="nm_pesquisador", nullable=false)
	private String nome;
	
	@Column(name="sobrenm_pesquisador", nullable=false)
	private String sobrenome;
	
	@Column(name="cpf_pesquisador", nullable=false)
	private String cpf;
	
	@Column(name="url_externa")
	private String urlExterna;
	
	@Column(name="st_ativo", nullable=false)
	private Boolean status;
	
	@Column(name="criado_em", nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date criadoEm;
	
	@Column(name="alterado_em")
	@Temporal(TemporalType.TIMESTAMP)
	private Date alteradoEm;

}
