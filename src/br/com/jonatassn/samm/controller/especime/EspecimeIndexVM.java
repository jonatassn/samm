/*
 * Sistema de Auxílio ao Módulo de Monitoramento
 */
package br.com.jonatassn.samm.controller.especime;

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

import br.com.jonatassn.samm.dao.EspecimeDao;
import br.com.jonatassn.samm.dao.impl.EspecimeDaoImpl;
import br.com.jonatassn.samm.model.Especime;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Jonatas.Silveira - IFPR <jonatassn01@gmail.com>
 *
 */
public class EspecimeIndexVM {
	List<Especime> especimes = new ArrayList<Especime>();
	
	@Getter @Setter
	String filtroBusca = "";
	
	public List<Especime> getEspecimes(){
		EspecimeDao especimeDao = new EspecimeDaoImpl();
		if(!this.filtroBusca.isEmpty()) {
			this.especimes = especimeDao.listarTodos(filtroBusca);
		}
		else {
			this.especimes = especimeDao.listarTodos();
		}
		return this.especimes;
	}
	
	@Command
	@NotifyChange("especimes")
	public void buscar() {
	}
	
	@GlobalCommand
	@NotifyChange("especimes")
	public void atualizarListaEspecimes() {
	}
	
	@Command
	public void novoEspecime() {
		try {
			final Window window = (Window) Executions.createComponents( "/especime/especime.zul", null, null);
			window.doModal();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Command
	public void editarEspecime(@BindingParam("especime") Especime especime) {
		Map parametros = new HashMap<>();
		parametros.put("especimeEditado", especime);
		try {
			final Window window = (Window) Executions.createComponents( "/especime/especime.zul", null, parametros);
			window.doModal();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Command
	@NotifyChange("especimes")
	public void ativarEspecime(@BindingParam("especime") Especime especime) {
		EspecimeDao especimeDao = new EspecimeDaoImpl();
		especime.setIsAtivo(true);
		especimeDao.salvar(especime);
	}
}
