/*
 * Sistema de Auxílio ao Módulo de Monitoramento
 * Copyright (c) 2022 - Todos os direitos reservados.
 */
package br.com.jonatassn.samm.dao;

import java.util.List;

import br.com.jonatassn.samm.model.Pesquisador;
import br.com.jonatassn.samm.model.Usuario;


/**
 * @author Jonatas Silveira - <jonatassn.com.br>
 *
 */
public interface PesquisadorDao {
	void salvar(Pesquisador entidade);
	Pesquisador localizar(Long id);
	Pesquisador localizar(Usuario usuario);
	List<Pesquisador> listarTodos();
	List<Pesquisador> listarTodos(String busca);
}
