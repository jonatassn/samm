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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;


/**
 * @author Jonatas Silveira - <jonatassn.com.br>
 *
 */
@Data
@Table(name="tb_usuarios", schema="samm")
@Entity
public class Usuario implements Serializable {
	public static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="cd_usuario")
	private Long id;
	
	@Column(name="email_usuario", length=256, nullable=false, unique=true)
	private String email;
	
	@Column(name="passwd_usuario", length=40, nullable=false)
	private String password;
	
	@Column(name="is_admin", nullable=false)
	private Boolean admin;
	
	@Column(name="st_ativo", nullable=false)
	private Boolean status;
	
	@Column(name="criado_em", nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date criadoEm;
	
	@Column(name="alterado_em")
	@Temporal(TemporalType.TIMESTAMP)
	private Date alteradoEm;
	
}
