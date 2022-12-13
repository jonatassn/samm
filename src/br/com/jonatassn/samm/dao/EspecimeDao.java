/*
 * Sistema de Auxílio ao Módulo de Monitoramento
 * Copyright (c) 2022 - Todos os direitos reservados.
 */
package br.com.jonatassn.samm.dao;

import java.util.List;

import br.com.jonatassn.samm.model.Especime;
import br.com.jonatassn.samm.model.Tag;


/**
 * @author Jonatas Silveira - <jonatassn.com.br>
 *
 */
public interface EspecimeDao {
	void salvar(Especime entidade);
	Especime localizar(Long id);
	Especime localizar(Tag tag);
	List<Especime> listarTodos();
	List<Especime> listarTodos(String busca);
}
