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

import org.hibernate.annotations.Type;

import lombok.Data;

/**
 * @author Jonatas Silveira - <jonatassn.com.br>
 *
 */
@Data
@Table(name="tb_modulos", schema="samm")
@Entity
public class Modulo implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="cd_modulo")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="desc_modulo")
	@Type(type="text")
	private String descricao;
	
	@Column(name="latitude")
	private Double latitude;
	
	@Column(name="longitude")
	private Double longitude;
	
	@Column(name="is_ativo", nullable=false)
	private Boolean isAtivo;
	
	@Column(name="criado_em", nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date criadoEm;
	
	@Column(name="criado_por", nullable=false)
	private String criadoPor;
	
	@Column(name="alterado_em")
	@Temporal(TemporalType.TIMESTAMP)
	private Date alteradoEm;
	
	@Column(name="alterado_por")
	private String alteradoPor;

}
