/*
 * Sistema de Auxílio ao Módulo de Monitoramento
 * Copyright (c) 2022 - Todos os direitos reservados.
 */
package br.com.jonatassn.samm.dao;

import java.util.List;

import br.com.jonatassn.samm.model.Usuario;


/**
 * @author Jonatas Silveira - <jonatassn.com.br>
 *
 */
public interface UsuarioDao {
	void salvar(Usuario entidade);
	Usuario localizar(Long id);
	Usuario localizar(String email, String senha)  throws Exception;
	List<Usuario> listarTodos();
}
