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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

/**
 * @author Jonatas.Silveira - Unespar <jonatas.silveira@unespar.edu.br>
 *
 */
@Data
@Table(name="tb_usuario", schema="samm")
public class Usuario implements Serializable {
	public static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="cd_usuario")
	private Long id;
	
	@Column(name="email_usuario")
	private String email;
	
	@Column(name="passwd_usuario")
	private String password;
	
	@Column(name="is_admin")
	private Boolean admin;
	
	@Column(name="st_ativo")
	private Boolean status;
	
	@Column(name="criado_em")
	@Temporal(TemporalType.TIMESTAMP)
	private Date criadoEm;
	
	@Column(name="alterado_em")
	@Temporal(TemporalType.TIMESTAMP)
	private Date alteradoEm;
	
}
