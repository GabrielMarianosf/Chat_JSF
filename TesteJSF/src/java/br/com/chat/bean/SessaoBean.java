/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.chat.bean;

import br.com.chat.entidade.Usuario;
import br.com.chat.util.Sessao;
import javax.faces.application.Application;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Gabriel
 */

@ManagedBean
@SessionScoped
public class SessaoBean {
    private Usuario us;
    
    public void iniciar(){
        us = (Usuario) Sessao.getSessao("us");
    }

    public Usuario getUs() {
        return us;
    }

    public void setUs(Usuario us) {
        this.us = us;
    }
    
    
    
}
