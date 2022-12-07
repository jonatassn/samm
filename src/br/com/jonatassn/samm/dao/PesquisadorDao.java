/*
 * Sistema de Auxílio ao Módulo de Monitoramento
 * Copyright (c) 2022 - Todos os direitos reservados.
 */
package br.com.jonatassn.samm.dao;

import java.util.List;

import br.com.jonatassn.samm.model.Pesquisador;


/**
 * @author Jonatas Silveira - <jonatassn.com.br>
 *
 */
public interface PesquisadorDao {
	void salvar(Pesquisador entidade);
	Pesquisador localizar(Long id);
	List<Pesquisador> listarTodos();
}
