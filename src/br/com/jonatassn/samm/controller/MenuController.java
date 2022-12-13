package br.com.jonatassn.samm.controller;

import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.zkoss.zhtml.I;
import org.zkoss.zhtml.Li;
import org.zkoss.zhtml.Ul;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.A;
import org.zkoss.zul.Div;
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
public class MenuController extends Window {

    private Include conteudo;
    private Div sideBar, divUsuarios, divPesquisadores;
    
    public void onCreate() {

        this.conteudo = (Include) getFellowIfAny("conteudo", true);
        this.sideBar = (Div) getFellow("sidebar");
        this.divUsuarios = (Div) getFellow("divUsuarios");
        this.divPesquisadores = (Div) getFellow("divPesquisadores");
        AuthenticationService servico = new AuthenticationServiceImpl();

        Usuario usuario = servico.getUserCredential();
        if (usuario != null) {
            this.montaMenu(usuario);
            Boolean visible = servico.isAdmin();
            this.divUsuarios.setVisible(visible);
        	this.divPesquisadores.setVisible(visible);
        }
    }

    private void atualizaClasseDasTagsARecursivamente(Collection<Component> items, A tagASelecionado) {
        for (Component item : items) {
            Collection children = item.getChildren();
            atualizaClasseDasTagsARecursivamente(children, tagASelecionado);

            if (item.getWidgetClass().equals("zul.wgt.A")) {
                A itemMenu = (A) item;
                itemMenu.setClass("nav-menu");
                if (itemMenu.equals(tagASelecionado)) {
                    itemMenu.setClass("nav-menu mm-active");
                }
            }
        }
    }

    public void selecionar(A a) {
        Collection<Component> items = getFellows();
        this.atualizaClasseDasTagsARecursivamente(items, a);
        String href = a.getHref();
        this.conteudo.setSrc(null);

        this.conteudo.setSrc(href);

    }


    private void montaMenu(Usuario usuario) {

//        //Menu
//        Ul ul0 = new Ul();
//        ul0.setSclass("vertical-nav-menu");
//        ul0.setParent(this.sideBar);
//
//        //Titulo 1 (opcional)
//        Li liTitulo1 = new Li();
//        liTitulo1.setSclass("app-sidebar__heading");
//        liTitulo1.setParent(ul0);
//        
//        //Item pagina inicial
//        Li liPaginInicial = new Li();
//        liPaginInicial.setParent(ul0);
//
//        A aPaginaInicial = new A();
//        aPaginaInicial.setSclass("nav-menu mm-active");
//        aPaginaInicial.setHref("home/index.zul");
//        //aPaginaInicial.setHref("frm_graficos.zul");
//        aPaginaInicial.setClientAttribute("onClick", "event.preventDefault()");
//        aPaginaInicial.addEventListener(Events.ON_CLICK, new EventListener<Event>() {
//            @Override
//            public void onEvent(Event event) throws Exception {
//                selecionar((A) event.getTarget());
//            }
//        });
//        aPaginaInicial.setParent(liPaginInicial);
//
//        I iPaginaInicial = new I();
//        iPaginaInicial.setSclass("metismenu-icon fa fa-home");
//        iPaginaInicial.setParent(aPaginaInicial);
//
//        Label lPaginaInicial = new Label();
//        lPaginaInicial.setValue("Início");
//        lPaginaInicial.setParent(aPaginaInicial);

    }

    private String configuraIconeDoItemDoMenu(String url, String parametros) {

        if (parametros != null) {
            Pattern pattern = Pattern.compile("icon[e]*=([a-zA-Z\\-\\d]+)");
            Matcher matcher = pattern.matcher(parametros);
            if (matcher.find()) {
                return matcher.group(1).toLowerCase();
            }
        }

        if (url.trim().isEmpty()) {
            return "pe-7s-folder";
        }

        return "";
    }

}
