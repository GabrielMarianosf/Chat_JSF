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

    public void inserir(Usuario usuario) throws ClassNotFoundException, SQLException {

        try {
            Connection conexao = (Connection) Conexao.getConexao();
            PreparedStatement pst;
            pst = conexao.prepareCall("INSERT INTO usuarios (id,Nome,Sobrenome,Senha,Apelido,email)"
                    + "values (null,?,?,?,?,?)");
            pst.setString(1, usuario.getNome());
            pst.setString(2, usuario.getSobrenome());
            pst.setString(3, usuario.getSenha());
            pst.setString(4, usuario.getApelido());
            pst.setString(5, usuario.getEmail());
            pst.execute();
            Conexao.fecharConexao();
        } catch (SQLException ex) {
            Logger.getLogger(MetodosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean validarApelido (Usuario user) throws SQLException, ClassNotFoundException{
            try {
            Connection conexao = (Connection) Conexao.getConexao();
            PreparedStatement pst;
            boolean result;
            pst = conexao.prepareCall("select apelido from usuarios where apelido=?");
            pst.setString(1, user.getApelido());
            result = pst.execute();
            Conexao.fecharConexao();
            if(result == false){
                return true;
            }
            else{
                return false;
            }                
        } catch (Exception e) {
            return false;
        }       
    }

    public void inserirMensagem(Mensagem mensagem, Usuario Usuario) throws ClassNotFoundException, SQLException {

        try {
            Connection conexao = (Connection) Conexao.getConexao();
            PreparedStatement pst;
            pst = conexao.prepareCall("INSERT INTO mensagem (id, msg, remetente)"
                    + "values (null,?,?)");
            pst.setString(1, mensagem.getMensagem());
            pst.setString(2, Usuario.getApelido());
        } catch (SQLException ex) {
            Logger.getLogger(MetodosDAO.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    public List<Usuario> listarUsuario(Usuario Usuario) throws ClassNotFoundException, SQLException {

        try {
            Connection conexao = (Connection) Conexao.getConexao();
            PreparedStatement pst = conexao.prepareCall("SELECT * FROM usuarios");
            ResultSet rs = pst.executeQuery();
            List<Usuario> lista = new ArrayList<>();
            while (rs.next()) {
            Usuario usuario = new Usuario();
            usuario.setNome(rs.getString("nome"));
            usuario.setSobrenome(rs.getString("sobrenome"));
            usuario.setEmail(rs.getString("email"));
            usuario.setSenha(rs.getString("senha"));
            usuario.setApelido(rs.getString("apelido"));
            lista.add(usuario);
            }
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(MetodosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<Mensagem> listarMensagens(Mensagem mensagem) throws ClassNotFoundException, SQLException {

        try {
            Connection conexao = (Connection) Conexao.getConexao();
            PreparedStatement pst = conexao.prepareCall("SELECT * FROM mensagem");
            ResultSet rs = pst.executeQuery();
            List<Mensagem> lista = new ArrayList<>();
            while (rs.next()) {
            mensagem.setMensagem(rs.getString("mensagem"));
            mensagem.setRemetente(rs.getString("remetente"));
            lista.add(mensagem);
            return lista;
            }
        } catch (SQLException ex) {
            Logger.getLogger(MetodosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void deletarUsuario(Usuario Usuario) throws ClassNotFoundException, SQLException {

        try {
            Connection conexao = (Connection) Conexao.getConexao();
            PreparedStatement pst;
            pst = conexao.prepareCall("DELETE FROM patrimonio WHERE codigo=?;");
            pst.setInt(1, Usuario.getCodigo());
            pst.execute();
        } catch (SQLException ex) {
            Logger.getLogger(MetodosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void atualizarUsuario(Usuario Usuario) throws ClassNotFoundException, SQLException {
        try {
            Connection conexao = (Connection) Conexao.getConexao();
            PreparedStatement pst;
            pst = conexao.prepareCall("UPDATE patrimonio set nome=?, sobrenome=?, email=?, apelido=? senha=? "
                    + "where codigo=?");
            pst.setInt(6, Usuario.getCodigo());
        } catch (SQLException ex) {
            Logger.getLogger(MetodosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
