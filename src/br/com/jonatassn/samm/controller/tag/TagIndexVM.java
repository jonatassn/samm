/*
 * Sistema de Auxílio ao Módulo de Monitoramento
 */
package br.com.jonatassn.samm.controller.tag;

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

import br.com.jonatassn.samm.dao.TagDao;
import br.com.jonatassn.samm.dao.impl.TagDaoImpl;
import br.com.jonatassn.samm.model.Tag;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Jonatas.Silveira - IFPR <jonatassn01@gmail.com>
 *
 */
public class TagIndexVM {
	List<Tag> tags = new ArrayList<Tag>();
	
	@Getter @Setter
	String filtroBusca = "";
	
	public List<Tag> getTags(){
		TagDao tagDao = new TagDaoImpl();
		if(!this.filtroBusca.isEmpty()) {
			this.tags = tagDao.listarTodos(filtroBusca);
		}
		else {
			this.tags = tagDao.listarTodos();
		}
		return this.tags;
	}
	
	@Command
	@NotifyChange("tags")
	public void buscar() {
	}
	
	@GlobalCommand
	@NotifyChange("tags")
	public void atualizarListaTags() {
	}
	
	@Command
	public void novaTag() {
		try {
			final Window window = (Window) Executions.createComponents( "/tag/tag.zul", null, null);
			window.doModal();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Command
	public void editarTag(@BindingParam("tag") Tag tag) {
		Map parametros = new HashMap<>();
		parametros.put("tagEditada", tag);
		try {
			final Window window = (Window) Executions.createComponents( "/tag/tag.zul", null, parametros);
			window.doModal();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Command
	@NotifyChange("tags")
	public void ativarTag(@BindingParam("tag") Tag tag) {
		TagDao tagDao = new TagDaoImpl();
		tag.setIsAtivo(true);
		tagDao.salvar(tag);
	}
}
