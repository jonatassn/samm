/*
 * Sistema de Auxílio ao Módulo de Monitoramento
 */
package br.com.jonatassn.samm.controller.usuario;

import java.util.Map;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
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
public class UsuarioVM {
	
	@Getter @Setter
	private Usuario formUsuario = new Usuario();
	
	@Getter
	private String fieldType = "password";
	
	@SuppressWarnings("rawtypes")
	public UsuarioVM() {
		this.formUsuario.setStatus(true);
		this.formUsuario.setAdmin(false);
		Map parametros = Executions.getCurrent().getArg();
		if(parametros.get("usuarioEditado") != null)
			this.formUsuario = (Usuario) parametros.get("usuarioEditado");
	}

	
	public void salvar(@BindingParam("janela") Window janela) {
		try {
			UsuarioDao usuarioDao = new UsuarioDaoImpl();
			usuarioDao.salvar(this.formUsuario);
			BindUtils.postGlobalCommand(null, null, "atualizarListaUsuarios", null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		janela.detach();
	}
	
	public void cancelar(@BindingParam("janela") Window janela) {
		
		janela.detach();
	}
	
	@NotifyChange("fieldType")
	public void alternarSenha() {
		if(this.fieldType.equals("password"))
			this.fieldType = "text";
		else
			this.fieldType = "password";
	}
}
