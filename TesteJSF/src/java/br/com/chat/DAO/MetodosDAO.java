/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.chat.DAO;

import br.com.chat.entidade.Mensagem;
import br.com.chat.entidade.Usuario;
import br.com.chat.util.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gabriel
 */
public class MetodosDAO {
    
    public void inserir(Usuario Usuario) throws ClassNotFoundException, SQLException {
        
        try {
            Connection conexao = (Connection) Conexao.getConexao();
            PreparedStatement pst;
            pst = conexao.prepareCall("INSERT INTO usuarios (id,Nome,Sobrenome,Senha,Apelido,email)"
                    + "values (null,?,?,?,?,?)"); 
            pst.setString(1, Usuario.getNome());
            pst.setString(2, Usuario.getSobrenome());
            pst.setString(3, Usuario.getSenha());
            pst.setString(4, Usuario.getApelido());
            pst.setString(5, Usuario.getEmail());
            pst.execute();
            Conexao.fecharConexao();
        } catch (SQLException ex) {
            Logger.getLogger(MetodosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void inserirMensagem (Mensagem mensagem, Usuario Usuario) throws ClassNotFoundException, SQLException {
        
        try {
            Connection conexao = (Connection) Conexao.getConexao();
            PreparedStatement pst;
            pst = conexao.prepareCall("INSERT INTO mensagem (id, msg, remetente)"
                    + "values (null,?,?)");
            pst.setString(1, mensagem.getMensagem());
            pst.setString(2, Usuario.getApelido());
        }
        catch (SQLException ex) {
            Logger.getLogger(MetodosDAO.class.getName()).log(Level.SEVERE, null, ex);
            
        }
    }

    public List<Usuario> listarUsuario () throws ClassNotFoundException  {

        try {
            Connection conexao = (Connection) Conexao.getConexao();
            PreparedStatement pst = conexao.prepareCall("SELECT * FROM usuarios");
            ResultSet rs = pst.executeQuery();
            List<Usuario> lista = new ArrayList<>();
            while(rs.next()){
            Usuario user = new Usuario();
            user.setNome(rs.getString("nome"));
            user.setSobrenome(rs.getString("sobrenome"));
            user.setEmail(rs.getString("email"));
            user.setSenha(rs.getString("senha"));
            user.setApelido(rs.getString("apelido"));
            lista.add(user);
            }
            return lista;
        }            
        catch (SQLException ex) {
            Logger.getLogger(MetodosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
