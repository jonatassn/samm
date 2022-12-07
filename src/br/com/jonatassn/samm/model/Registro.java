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
@Table(name="tb_registros")
@Entity
public class Registro implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="cd_registro")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="data_hora_registro", nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataHoraRegistro;
	
	@OneToOne
	@JoinColumn(name="cd_modulo", nullable=false)
	private Modulo modulo;
	
	@OneToOne
	@JoinColumn(name="cd_tag", nullable=false)
	private Tag tag;
}
