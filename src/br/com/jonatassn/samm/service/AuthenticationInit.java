/*
 * Sistema de Auxílio ao Módulo de Monitoramento
 * Copyright (c) 2022 - Todos os direitos reservados.
 */
package br.com.jonatassn.samm.service;

import java.util.Map;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.util.Initiator;

import br.com.jonatassn.samm.model.Usuario;

/**
 * @author Jonatas Silveira - <jonatassn.com.br>
 *
 */
public class AuthenticationInit implements Initiator {

	@Override
	public void doInit(Page page, Map<String, Object> args) throws Exception {
		AuthenticationService servico = new AuthenticationServiceImpl();
		//Usuario usuario = servico.getUserCredential();
		//if(usuario == null || usuario.getId() == null)
			Executions.sendRedirect("login/");
		
	}

}
