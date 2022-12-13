/*
 * Sistema de Auxílio ao Módulo de Monitoramento
 */
package br.com.jonatassn.samm.controller.pesquisador;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zul.Window;

import br.com.jonatassn.samm.dao.PesquisadorDao;
import br.com.jonatassn.samm.dao.UsuarioDao;
import br.com.jonatassn.samm.dao.impl.PesquisadorDaoImpl;
import br.com.jonatassn.samm.dao.impl.UsuarioDaoImpl;
import br.com.jonatassn.samm.model.Pesquisador;
import br.com.jonatassn.samm.model.Usuario;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Jonatas.Silveira - IFPR <jonatassn01@gmail.com>
 *
 */
public class PesquisadorVM {
	
	@Getter @Setter
	private Pesquisador formPesquisador = new Pesquisador();
	
	private List<Usuario> usuarios = new ArrayList<Usuario>();
	
	@SuppressWarnings("rawtypes")
	public PesquisadorVM() {
		this.formPesquisador.setStatus(true);
		Map parametros = Executions.getCurrent().getArg();
		if(parametros.get("pesquisadorEditado") != null)
			this.formPesquisador = (Pesquisador) parametros.get("pesquisadorEditado");
	}

	public List<Usuario> getUsuarios(){
		UsuarioDao usuarioDao = new UsuarioDaoImpl();
		this.usuarios = usuarioDao.listarTodos();
		return this.usuarios;
	}
	
	
	public void salvar(@BindingParam("janela") Window janela) {
		try {
			PesquisadorDao pesquisadorDao = new PesquisadorDaoImpl();
			pesquisadorDao.salvar(this.formPesquisador);
			BindUtils.postGlobalCommand(null, null, "atualizarListaPesquisadores", null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		janela.detach();
	}
	
	public void cancelar(@BindingParam("janela") Window janela) {
		
		janela.detach();
	}

}
