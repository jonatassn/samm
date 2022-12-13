package br.com.jonatassn.samm.controller;

import org.zkoss.zul.Image;
import org.zkoss.zul.Include;
import org.zkoss.zul.Label;
import org.zkoss.zul.Window;

import br.com.jonatassn.samm.dao.PesquisadorDao;
import br.com.jonatassn.samm.dao.impl.PesquisadorDaoImpl;
import br.com.jonatassn.samm.model.Pesquisador;
import br.com.jonatassn.samm.model.Usuario;
import br.com.jonatassn.samm.service.AuthenticationService;
import br.com.jonatassn.samm.service.AuthenticationServiceImpl;

/**
 * @author Jonatas.Silveira - IFPR <jonatassn01@gmail.com>
 *
 */
public class HeaderController extends Window {

    private Include conteudo;
    private Image profileImage;
    private Label profileName;
    private Label profileInfo;


    public void onCreate() {

        this.conteudo = (Include) getFellowIfAny("conteudo", true);
        this.profileName = (Label) getFellow("nome_profile");
        this.profileInfo = (Label) getFellow("departamento_profile");

        AuthenticationService servico = new AuthenticationServiceImpl();
        Usuario usuario = servico.getUserCredential();
        
        if (usuario != null)
            this.preencheDados(usuario);

    }


    private void preencheDados(Usuario usuario) {
        String nomeAbreviado = usuario.getEmail();
        PesquisadorDao pesquisadorDao = new PesquisadorDaoImpl();
        Pesquisador pesquisador = pesquisadorDao.localizar(usuario);
        
        this.profileName.setValue(pesquisador != null ? pesquisador.getNome() + " " + pesquisador.getSobrenome() : "Usuário não pesquisador");
        this.profileName.setTooltiptext(usuario.getEmail());
        this.profileInfo.setValue(nomeAbreviado);
    }

    public void sair() {
        AuthenticationService servico = new AuthenticationServiceImpl();
        servico.logout();
    }

   
}
