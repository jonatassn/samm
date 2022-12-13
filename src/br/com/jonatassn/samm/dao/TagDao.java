/*
 * Sistema de Auxílio ao Módulo de Monitoramento
 * Copyright (c) 2022 - Todos os direitos reservados.
 */
package br.com.jonatassn.samm.dao;

import java.util.List;

import br.com.jonatassn.samm.model.Tag;


/**
 * @author Jonatas Silveira - <jonatassn.com.br>
 *
 */
public interface TagDao {
	void salvar(Tag entidade);
	Tag localizar(Long id);
	Tag localizar(String codigo);
	List<Tag> listarTodos();
	List<Tag> listarTodos(String busca);
}
