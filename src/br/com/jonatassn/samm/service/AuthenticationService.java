/*
 * Sistema de Auxílio ao Módulo de Monitoramento
 * Copyright (c) 2022 - Todos os direitos reservados.
 */
package br.com.jonatassn.samm.service;

import br.com.jonatassn.samm.model.Usuario;

/**
 * @author Jonatas Silveira - <jonatassn.com.br>
 *
 */
public interface AuthenticationService {
	Boolean login(String email, String senha) throws Exception;
	void logout();
	Boolean isAdmin();
	Usuario getUserCredential();
}
