package br.com.jonatassn.samm.controller;

import org.zkoss.zul.Include;
import org.zkoss.zul.Label;
import org.zkoss.zul.Window;

import br.com.jonatassn.samm.model.Usuario;
import br.com.jonatassn.samm.service.AuthenticationService;
import br.com.jonatassn.samm.service.AuthenticationServiceImpl;



/**
 * @author Jonatas.Silveira - IFPR <jonatassn01@gmail.com>
 *
 */
public class FooterController extends Window {

    private Include conteudo;
    private Label lbSobre;

    public void onCreate() {

        this.conteudo = (Include) getFellowIfAny("conteudo", true); 
        this.lbSobre = (Label) getFellow("sobre");

        AuthenticationService servico = new AuthenticationServiceImpl();
        Usuario usuario = servico.getUserCredential();
        if (usuario != null) {
            imprimeVersao();
        }
        

    }

    private void imprimeVersao() {
        this.lbSobre.setValue("Sistema de Auxílio ao Módulo de Monitoramento");

    }

}
