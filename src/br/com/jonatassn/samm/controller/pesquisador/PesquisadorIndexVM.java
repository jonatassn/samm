/*
 * Sistema de Auxílio ao Módulo de Monitoramento
 */
package br.com.jonatassn.samm.controller.pesquisador;

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

import br.com.jonatassn.samm.dao.PesquisadorDao;
import br.com.jonatassn.samm.dao.impl.PesquisadorDaoImpl;
import br.com.jonatassn.samm.model.Pesquisador;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Jonatas.Silveira - IFPR <jonatassn01@gmail.com>
 *
 */
public class PesquisadorIndexVM {
	List<Pesquisador> pesquisadores = new ArrayList<Pesquisador>();
	
	@Getter @Setter
	String filtroBusca = "";
	
	public List<Pesquisador> getPesquisadores(){
		PesquisadorDao pesquisadorDao = new PesquisadorDaoImpl();
		if(!this.filtroBusca.isEmpty()) {
			this.pesquisadores = pesquisadorDao.listarTodos(filtroBusca);
		}
		else {
			this.pesquisadores = pesquisadorDao.listarTodos();
		}
		return this.pesquisadores;
	}
	
	@Command
	@NotifyChange("pesquisadores")
	public void buscar() {
	}
	
	@GlobalCommand
	@NotifyChange("pesquisadores")
	public void atualizarListaPesquisadores() {
	}
	
	@Command
	public void novoPesquisador() {
		try {
			final Window window = (Window) Executions.createComponents( "/pesquisador/pesquisador.zul", null, null);
			window.doModal();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Command
	public void editarPesquisador(@BindingParam("pesquisador") Pesquisador pesquisador) {
		Map parametros = new HashMap<>();
		parametros.put("pesquisadorEditado", pesquisador);
		try {
			final Window window = (Window) Executions.createComponents( "/pesquisador/pesquisador.zul", null, parametros);
			window.doModal();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Command
	@NotifyChange("pesquisadores")
	public void ativarPesquisador(@BindingParam("pesquisador") Pesquisador pesquisador) {
		PesquisadorDao pesquisadorDao = new PesquisadorDaoImpl();
		pesquisador.setStatus(true);
		pesquisadorDao.salvar(pesquisador);
	}
}
