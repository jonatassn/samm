/*
 * Sistema de Auxílio ao Módulo de Monitoramento
 */
package br.com.jonatassn.samm.controller.especime;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zul.Window;

import br.com.jonatassn.samm.dao.EspecimeDao;
import br.com.jonatassn.samm.dao.PesquisadorDao;
import br.com.jonatassn.samm.dao.TagDao;
import br.com.jonatassn.samm.dao.impl.EspecimeDaoImpl;
import br.com.jonatassn.samm.dao.impl.PesquisadorDaoImpl;
import br.com.jonatassn.samm.dao.impl.TagDaoImpl;
import br.com.jonatassn.samm.model.Especime;
import br.com.jonatassn.samm.model.Pesquisador;
import br.com.jonatassn.samm.model.Tag;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Jonatas.Silveira - IFPR <jonatassn01@gmail.com>
 *
 */
public class EspecimeVM {
	
	@Getter @Setter
	private Especime formEspecime = new Especime();
	
	private List<Pesquisador> pesquisadores = new ArrayList<>();
	
	private List<Tag> tags = new ArrayList<>();
	
	@SuppressWarnings("rawtypes")
	public EspecimeVM() {
		Map parametros = Executions.getCurrent().getArg();
		if(parametros.get("especimeEditada") != null)
			this.formEspecime = (Especime) parametros.get("especimeEditada");
	}

	@NotifyChange("pesquisadores")
	public List<Pesquisador> getPesquisadores() {
		PesquisadorDao pesquisadorDao = new PesquisadorDaoImpl();
		this.pesquisadores = pesquisadorDao.listarTodos();
		return this.pesquisadores;
	}
	
	@NotifyChange("tags")
	public List<Tag> getTags() {
		TagDao tagDao = new TagDaoImpl();
		this.tags = tagDao.listarTodos();
		return this.tags;
	}
	
	public void salvar(@BindingParam("janela") Window janela) {
		try {
			EspecimeDao especimeDao = new EspecimeDaoImpl();
			especimeDao.salvar(this.formEspecime);
			BindUtils.postGlobalCommand(null, null, "atualizarListaEspecimes", null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		janela.detach();
	}
	
	public void cancelar(@BindingParam("janela") Window janela) {
		
		janela.detach();
	}
	
}
