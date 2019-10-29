/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.chat.bean;

import br.com.chat.entidade.Usuario;
import br.com.chat.util.Sessao;
import java.io.IOException;
import java.util.List;
import javax.faces.application.Application;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Gabriel
 */
@ManagedBean
@SessionScoped

public class SessaoBean {
    private Usuario id = null;
    private int a = 0;
    
    public void init() {
        a = 1;
    }
    
    public void temSessao() throws IOException {
        Integer r = id.getCodigo();
        if (r == null) {
            FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
        } else {
            FacesContext.getCurrentInstance().getExternalContext().redirect("perfil.xhtml");
        }
    }
    public void iniciar() throws Exception {
        id = (Usuario) Sessao.getSessao("idusuario");
    }

    public Usuario getId() {
        return id;
    }

    public void setId(Usuario id) {
        this.id = id;
    }
}
