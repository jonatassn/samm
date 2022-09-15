/*
 * Universidade Estadual do Paraná - Unespar
 * Núcleo de Tecnologia da Informação - NTI
 * Copyright (c) 2022 - Todos os direitos reservados.
 */
package br.com.jonatassn.samm.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
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
 * @author Jonatas.Silveira - Unespar <jonatas.silveira@unespar.edu.br>
 *
 */
@Data
@Table(name="tb_pesquisador", schema="samm")
public class Pesquisador implements Serializable{
	public static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="cd_pesquisador")
	private Long id;
	
	@OneToOne
	@JoinColumn(name="cd_usuario")
	private Usuario usuario;
	
	@Column(name="nm_pesquisador")
	private String nome;
	
	@Column(name="sbnm_pesquisador")
	private String sobrenome;
	
	@Column(name="cpf_pesquisador")
	private String cpf;
	
	@Column(name="url_externa")
	private String urlExterna;
	
	@Column(name="st_ativo")
	private Boolean status;
	
	@Column(name="criado_em")
	@Temporal(TemporalType.TIMESTAMP)
	private Date criadoEm;
	
	@Column(name="alterado_em")
	@Temporal(TemporalType.TIMESTAMP)
	private Date alteradoEm;
}
