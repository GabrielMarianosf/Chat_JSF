/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.chat.bean;

import br.com.chat.DAO.MetodosDAO;
import br.com.chat.entidade.Login;
import br.com.chat.entidade.Usuario;
import com.sun.xml.ws.client.RequestContext;
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

/**
 *
 * @author Gabriel
 */
@ManagedBean
@SessionScoped
public class ChatBean {

    private Usuario usuario = new Usuario();
    private Usuario up = new Usuario();
    private MetodosDAO mtd_dao = new MetodosDAO();

    private Login lg = new Login();

    private List<Usuario> lista = new ArrayList<>();

    public void cadastrar() throws ClassNotFoundException, SQLException, NoSuchAlgorithmException {
        
        Integer res;
        Integer ress;
        ress = new MetodosDAO().validarEmail(usuario);
        res = new MetodosDAO().validarApelido(usuario);
        new MetodosDAO().inserir(usuario);
    }

    public void listar() throws ClassCastException, SQLException, ClassNotFoundException {
        //lista = mtd_dao.listarUsuario();
    }

    public void validarLogar() throws ClassNotFoundException, SQLException, Exception {
        try {
            boolean r;
            r = mtd_dao.Logar(lg);
            if (r) {
                List<Usuario> list = new ArrayList<>();
                list = mtd_dao.getCod(lg);
                Usuario us = list.get(0);
                up.setCodigo(us.getCodigo());
                
                lista = mtd_dao.listarUsuario(up);
                Usuario res = lista.get(0);
                up.setNome(res.getNome());
                up.setSobrenome(res.getSobrenome());
                up.setEmail(res.getEmail());
                up.setApelido(res.getApelido());
                
                FacesContext.getCurrentInstance().getExternalContext().redirect("perfil.xhtml");
            } else {
                FacesContext context = FacesContext.getCurrentInstance();
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Login ou Senha inválidos !", "erro de login");
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
