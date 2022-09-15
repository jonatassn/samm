/*
 * Universidade Estadual do Paraná - Unespar
 * Núcleo de Tecnologia da Informação - NTI
 * Copyright (c) 2022 - Todos os direitos reservados.
 */
package br.com.jonatassn.samm.dao;

import java.util.List;

import br.com.jonatassn.samm.model.Usuario;

/**
 * @author Jonatas.Silveira - Unespar <jonatas.silveira@unespar.edu.br>
 *
 */
public interface UsuarioDao {
	void salvar(Usuario entidade);
	Usuario localizar(Long id);
	List<Usuario> listarTodos();
}
