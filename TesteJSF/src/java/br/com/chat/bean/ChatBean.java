/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.chat.bean;

import br.com.chat.DAO.MetodosDAO;
import br.com.chat.entidade.Usuario;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Gabriel
 */

@ManagedBean
@SessionScoped
public class ChatBean {
    
    private Usuario usuario = new Usuario();//objeto que herda os metodos get e set da classe Mensagem
    private MetodosDAO msg_dao = new MetodosDAO();//objeto que herda os metodos inserir deletar e listar e excluir da 
    //da classe MensagemDAO
    
    private List<Usuario> listamsg = new ArrayList<>();
    public void cadastrar() throws ClassNotFoundException, SQLException {
        // getMsg_dao().inserir(getMensagem());//executando o metodo inserir da classe DAO
        // setMensagem(new Mensagem());//passando o objeto mensagem para limpar a memoria
        new MetodosDAO().inserir(usuario);
        usuario = new Usuario();
    }
    
    

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public MetodosDAO getMsg_dao() {
        return msg_dao;
    }

    public void setMsg_dao(MetodosDAO msg_dao) {
        this.msg_dao = msg_dao;
    }

    public List<Usuario> getListamsg() {
        return listamsg;
    }

    public void setListamsg(List<Usuario> listamsg) {
        this.listamsg = listamsg;
    }
    
    
}
