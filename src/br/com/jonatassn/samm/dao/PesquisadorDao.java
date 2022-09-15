/*
 * Universidade Estadual do Paran� - Unespar
 * N�cleo de Tecnologia da Informa��o - NTI
 * Copyright (c) 2022 - Todos os direitos reservados.
 */
package br.com.jonatassn.samm.dao;

import java.util.List;

import br.com.jonatassn.samm.model.Pesquisador;

/**
 * @author Jonatas.Silveira - Unespar <jonatas.silveira@unespar.edu.br>
 *
 */
public interface PesquisadorDao {
	void salvar(Pesquisador entidade);
	Pesquisador localizar(Long id);
	List<Pesquisador> listarTodos();
}
