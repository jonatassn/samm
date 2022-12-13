/*
 * Sistema de Auxílio ao Módulo de Monitoramento
 */
package br.com.jonatassn.samm.controller.tag;

import java.util.Map;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
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
public class TagVM {
	
	@Getter @Setter
	private Tag formTag = new Tag();
	
	@SuppressWarnings("rawtypes")
	public TagVM() {
		Map parametros = Executions.getCurrent().getArg();
		if(parametros.get("tagEditada") != null)
			this.formTag = (Tag) parametros.get("tagEditada");
	}

	
	public void salvar(@BindingParam("janela") Window janela) {
		try {
			TagDao tagDao = new TagDaoImpl();
			tagDao.salvar(this.formTag);
			BindUtils.postGlobalCommand(null, null, "atualizarListaTags", null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		janela.detach();
	}
	
	public void cancelar(@BindingParam("janela") Window janela) {
		
		janela.detach();
	}
	
}
