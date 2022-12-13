package br.com.jonatassn.samm.controller;

import org.zkoss.zul.Include;
import org.zkoss.zul.Window;


/**
 * @author Jonatas.Silveira - IFPR <jonatassn01@gmail.com>
 *
 */
@SuppressWarnings("serial")
public class IndexController extends Window { 

    private Window mainWindow;
    private Include conteudo;

    public void onCreate() {
        this.mainWindow = ( Window ) getFellow("mainWindow");
        this.conteudo = (Include) getFellow("conteudo");

        this.mainWindow.setVisible(true);

        abrePaginaInicial();

    }

    private void abrePaginaInicial() {
        this.conteudo.setSrc("/home/index.zul");
    }

}
