/*
 * Sistema de Auxílio ao Módulo de Monitoramento
 */
package br.com.jonatassn.samm.controller.usuario;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zul.Window;

import br.com.jonatassn.samm.dao.UsuarioDao;
import br.com.jonatassn.samm.dao.impl.UsuarioDaoImpl;
import br.com.jonatassn.samm.model.Usuario;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Jonatas.Silveira - IFPR <jonatassn01@gmail.com>
 *
 */
public class UsuarioIndexVM {
	List<Usuario> usuarios = new ArrayList<Usuario>();
	
	@Getter @Setter
	String filtroBusca = "";
	
	public List<Usuario> getUsuarios(){
		UsuarioDao usuarioDao = new UsuarioDaoImpl();
		if(!this.filtroBusca.isEmpty()) {
			this.usuarios = usuarioDao.listarTodos(filtroBusca);
		}
		else {
			this.usuarios = usuarioDao.listarTodos();
		}
		return this.usuarios;
	}
	
	@Command
	@NotifyChange("usuarios")
	public void buscar() {
	}
	
	@GlobalCommand
	@NotifyChange("usuarios")
	public void atualizarListaUsuarios() {
	}
	
	@Command
	public void novoUsuario() {
		try {
			final Window window = (Window) Executions.createComponents( "/usuario/usuario.zul", null, null);
			window.doModal();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Command
	public void editarUsuario(@BindingParam("usuario") Usuario usuario) {
		Map parametros = new HashMap<>();
		parametros.put("usuarioEditado", usuario);
		System.out.println(usuario.getEmail());
		try {
			final Window window = (Window) Executions.createComponents( "/usuario/usuario.zul", null, parametros);
			window.doModal();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Command
	@NotifyChange("usuarios")
	public void ativarUsuario(@BindingParam("usuario") Usuario usuario) {
		UsuarioDao usuarioDao = new UsuarioDaoImpl();
		usuario.setStatus(true);
		usuarioDao.salvar(usuario);
	}
}
