package br.com.jonatassn.samm.controller;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;

import br.com.jonatassn.samm.service.AuthenticationService;
import br.com.jonatassn.samm.service.AuthenticationServiceImpl;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Jonatas Silveira - <jonatassn.com.br>
 *
 */
public class LoginVM {
	
	@Getter @Setter
	private String email = "";
	
	@Getter @Setter
	private String password = "";
	
	@Getter
	private String msgErro = "";
	
	@Command
	@NotifyChange("msgErro")
	public void entrar() {
		AuthenticationService servico = new AuthenticationServiceImpl();
		try {
			if(servico.login(email, password))
				Executions.sendRedirect("/");
			else
				this.msgErro = "Usuário ou senha incorretos";
		} catch (Exception e) {
		}
	}
	
    @NotifyChange("msgErro")
    @Command
    public void limpaErro() {
    	this.msgErro = "";
    }
}
