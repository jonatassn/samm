/*
 * Sistema de Auxílio ao Módulo de Monitoramento
 * Copyright (c) 2022 - Todos os direitos reservados.
 */
package br.com.jonatassn.samm.service;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;

import br.com.jonatassn.samm.dao.UsuarioDao;
import br.com.jonatassn.samm.dao.impl.UsuarioDaoImpl;
import br.com.jonatassn.samm.model.Usuario;

/**
 * @author Jonatas Silveira - <jonatassn.com.br>
 *
 */
public class AuthenticationServiceImpl implements AuthenticationService {

	private Session session = Sessions.getCurrent();
	
	@Override
	public Boolean login(String email, String senha) throws Exception {
		UsuarioDao usuarioDao = new UsuarioDaoImpl();
		Usuario usuario = usuarioDao.localizar(email, senha);
		
		if(usuario != null) {
			this.session.setAttribute("user_autenticado", usuario);
			return true;
		}
		return false;
	}

	@Override
	public void logout() {
		session.removeAttribute("user_autenticado");
		Executions.sendRedirect("/login");
	}

	@Override
	public Boolean isAdmin() {
		if(this.getUserCredential() != null)
			if(this.getUserCredential().getAdmin())
				return true;
		
		return false;
	}

	@Override
	public Usuario getUserCredential() {
		return (Usuario) this.session.getAttribute("user_autenticado");
	}

}
