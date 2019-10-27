/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.chat.bean;

import br.com.chat.DAO.MetodosDAO;
import br.com.chat.entidade.Login;
import br.com.chat.entidade.Usuario;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;


/**
 *
 * @author Gabriel
 */

@ManagedBean
@SessionScoped
public class ChatBean {
    
    private Usuario usuario = new Usuario();//objeto que herda os metodos get e set da classe Mensagem
    private MetodosDAO mtd_dao = new MetodosDAO();//objeto que herda os metodos inserir deletar e listar e excluir da 
    //da classe MensagemDAO
    private Login lg = new Login();
    
    private List<Usuario> lista = new ArrayList<>();
    
    public void cadastrar() throws ClassNotFoundException, SQLException {
        // getMsg_dao().inserir(getMensagem());//executando o metodo inserir da classe DAO
        // setMensagem(new Mensagem());//passando o objeto mensagem para limpar a memoria
        Integer res;
        Integer ress;
        ress = new MetodosDAO().validarEmail(usuario);
        res = new MetodosDAO().validarApelido(usuario);
        
        if(res == 1){
            
            new MetodosDAO().inserir(usuario);
        
        }
        else{
            System.out.println("erro");
        }
    }
    
    public void listar() throws ClassCastException, SQLException {
        try {
            lista = mtd_dao.listarUsuario(usuario);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ChatBean.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    
    public void validarLogar() throws ClassNotFoundException, SQLException {
        try {
            boolean r;
            r = mtd_dao.Logar(usuario);
            if(r == true ){
            FacesContext.getCurrentInstance().getExternalContext().redirect("perfil.xhtml");
            }
            else {
                FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
            }
        } catch (Exception e) {
            System.out.println("erro");
        }
    }

    public Login getLg() {
        return lg;
    }

    public void setLg(Login lg) {
        this.lg = lg;
    }
    
    

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public MetodosDAO getMtd_dao() {
        return mtd_dao;
    }

    public void setMsg_dao(MetodosDAO mtd_dao) {
        this.mtd_dao = mtd_dao;
    }

    public List<Usuario> getLista() {
        return lista;
    }

    public void setLista(List<Usuario> listamsg) {
        this.lista = listamsg;
    }
    
    
}
