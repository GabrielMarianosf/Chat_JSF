/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.chat.bean;

import br.com.chat.entidade.Usuario;
import br.com.chat.util.Sessao;
import java.io.IOException;
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

    private Integer id;
    

     public void valSessao() throws IOException {
      if (id == null) {
        FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
        } 
        else{
        FacesContext.getCurrentInstance().getExternalContext().redirect("perfil.xhtml");
        }   
}   
    
    
    
    
    
    
    public void iniciar() throws Exception{
        id = (Integer) Sessao.getSessao("id");
        System.out.println("Sessao :"+id);
        //valSessao();
    }

    public Integer getUs() {
        return id;
    }

    public void setUs(Integer us) {
        this.id = id;
    }
}
