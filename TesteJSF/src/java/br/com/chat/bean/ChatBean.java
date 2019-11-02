/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.chat.bean;

import br.com.chat.DAO.MetodosDAO;
import br.com.chat.entidade.Login;
import br.com.chat.entidade.Mensagem;
import br.com.chat.entidade.Usuario;
import br.com.chat.util.Sessao;
import java.io.IOException;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import static org.jboss.logging.NDC.clear;

/**
 *
 * @author Gabriel
 */
@ManagedBean
@SessionScoped
public class ChatBean implements Serializable {

    private Usuario usuario = new Usuario();
    private Usuario up = new Usuario();
    private Mensagem msg = new Mensagem();
    private Mensagem lista_msg = new Mensagem();
    private MetodosDAO mtd_dao = new MetodosDAO();
    private Login lg = new Login();

    private List<Usuario> lista = new ArrayList<>();
    private List<Mensagem> listam = new ArrayList<>();

    public void cadastrar() throws Exception {
        
        boolean res = new MetodosDAO().validarApelido(usuario);
        boolean ress = new MetodosDAO().validarEmail(usuario);
        
        if(res == false ){
        if( ress == false){
            new MetodosDAO().inserir(usuario);
        }
        else{
            FacesContext context = FacesContext.getCurrentInstance();
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Email já cadastrado! Altere seu e-mail.", "Erro no cadastro de e-mail.");
            context.addMessage(null, message);
            context.validationFailed();
            System.out.println("Email já cadastrado ou inválido.");
        }
        }else{
            FacesContext context = FacesContext.getCurrentInstance();
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Apelido já cadastrado! Altere seu apelido.", "Erro no cadastro de apelido.");
            context.addMessage(null, message);
            context.validationFailed();
            System.out.println("Apelido já cadastrado.");
        }
    }
    
        public void inserirMensagem() throws ClassNotFoundException, SQLException, IOException {
        new MetodosDAO().inserirMensagem(msg, up);
        FacesContext.getCurrentInstance().getExternalContext().redirect("sala.xhtml");
    }
    
    public void updateUsuario() throws ClassNotFoundException, SQLException, IOException, NoSuchAlgorithmException, Exception {
        System.out.println(up.getSenha());
        boolean r = new MetodosDAO().validaSenha(up);
        if(r == true){
           new MetodosDAO().atualizarUsuario(up);
           FacesContext.getCurrentInstance().getExternalContext().redirect("perfil.xhtml");
        }
        else{
            FacesContext context = FacesContext.getCurrentInstance();
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Senha Inválida ! Digite sua senha Correta", "Erro no update user - senha errada!");
            context.addMessage(null, message);
            context.validationFailed();
            System.out.println("Senha incorreta !");
        }
    }
    
    public void excluirUsuario() throws ClassNotFoundException, SQLException, IOException {
        new MetodosDAO().deletarUsuario(up);
        FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
    }
    

    public void listarMensagens() throws ClassCastException, SQLException {
        try {
             listam = mtd_dao.listarMensagens();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ChatBean.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }

    
    public void validarLogar() throws ClassNotFoundException, SQLException, Exception {
        try {
            boolean r;
            r = mtd_dao.Logar(lg);
            if (r) {
                List<Usuario> list;
                list = mtd_dao.getCod(lg);
                Usuario us = list.get(0);
                up.setCodigo(us.getCodigo());
                lista  = mtd_dao.listarUsuario(up);
                Usuario res = lista.get(0);
                up.setNome (res.getNome());
                up.setSobrenome (res.getSobrenome());
                up.setEmail (res.getEmail());
                up.setApelido (res.getApelido());
                FacesContext.getCurrentInstance().getExternalContext().redirect("perfil.xhtml");
                System.out.println("Login efetuado com sucesso!");
            } else {
                FacesContext context = FacesContext.getCurrentInstance();
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Login ou senha inválidos!", "Erro de login.");
                context.addMessage(null, message);
                context.validationFailed();
            }
        } catch (Exception e) {
            System.out.println("erro");
        }
    }

    public Usuario getUp() {
        return up;
    }

    public void setUp(Usuario up) {
        this.up = up;
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

    public Mensagem getMensagem() {
        return msg;
    }

    public void setMensagem(Mensagem msg) {
        this.msg = msg;
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
    
    public List<Mensagem> getListam() {
        return listam;
    }

    public void setListam(List<Mensagem> listamsg) {
        this.listam = listamsg;
    }

    public Mensagem getLista_msg() {
        return lista_msg;
    }

    public void setLista_msg(Mensagem lista_msg) {
        this.lista_msg = lista_msg;
    }
    

}
