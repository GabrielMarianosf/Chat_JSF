/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.chat.DAO;

import br.com.chat.entidade.Login;
import br.com.chat.entidade.Mensagem;
import br.com.chat.entidade.Usuario;
import br.com.chat.util.Conexao;
import br.com.chat.util.Hash;
import java.security.NoSuchAlgorithmException;
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

    public void inserir(Usuario usuario) throws ClassNotFoundException, SQLException, NoSuchAlgorithmException {

        try {
            Connection conexao = (Connection) Conexao.getConexao();
            PreparedStatement pst;
            pst = conexao.prepareCall("INSERT INTO usuarios (id,Nome,Sobrenome,Senha,Apelido,email)"
                    + "values (null,?,?,?,?,?)");
            pst.setString(1, usuario.getNome());
            pst.setString(2, usuario.getSobrenome());
            String res;
            res = Hash.EncriptarSHA(usuario.getSenha());
            pst.setString(3, res);
            pst.setString(4, usuario.getApelido());
            pst.setString(5, usuario.getEmail());
            pst.execute();
            Conexao.fecharConexao();
        } catch (SQLException ex) {
            Logger.getLogger(MetodosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean validarApelido(Usuario user) throws Exception {
        try {
            Connection conexao = (Connection) Conexao.getConexao();
            PreparedStatement pst;
            pst = conexao.prepareCall("select COUNT(*) AS res from usuarios where apelido = ?");
            pst.setString(1, user.getApelido());
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
            return rs.getBoolean("res");
            }
        } catch (Exception e) {
            System.out.println("Apelido já cadastrado, " + e.getMessage());
        }
        return false;
    }

    public boolean validarEmail(Usuario user) throws Exception {
        try {
            Connection conexao = (Connection) Conexao.getConexao();
            PreparedStatement pst;
            pst = conexao.prepareCall("select COUNT(*) AS res from usuarios where email = ?");
            pst.setString(1, user.getEmail());
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
            return rs.getBoolean("res");
            }
        } catch (Exception e) {
            System.out.println("Email já cadastrado, " + e.getMessage());
        }
        return false;
    }

    public boolean Logar(Login log) throws Exception {
        try {
            Connection conexao = (Connection) Conexao.getConexao();
            PreparedStatement pst;
            pst = conexao.prepareCall("select COUNT(*) AS res from usuarios where email = ? and senha = ?");
            pst.setString(1, log.getEmail());
            String res;
            res = Hash.EncriptarSHA(log.getSenha());
            pst.setString(2, res);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                return rs.getBoolean("res");
            }
        } catch (ClassNotFoundException | NoSuchAlgorithmException | SQLException e) {
            System.out.println("Erro de login.");
        } finally {
            Conexao.fecharConexao();
        }
        return false;
    }

    public List<Usuario> getCod(Login log) throws Exception {
        try {
            
            Connection conexao = (Connection) Conexao.getConexao();
            PreparedStatement pst;
            pst = conexao.prepareCall("select id from usuarios where email = ? and senha = ?");
            pst.setString(1, log.getEmail());
            String res;
            res = Hash.EncriptarSHA(log.getSenha());
            pst.setString(2, res);
            ResultSet rs = pst.executeQuery();
            List<Usuario> lista = new ArrayList<>();
            while (rs.next()) {
                Usuario up = new Usuario();
                up.setCodigo(rs.getInt("id"));
                lista.add(up);
            }
            return lista;
        } catch (ClassNotFoundException | NoSuchAlgorithmException | SQLException e) {
            System.out.println("Erro de login.");
        } finally {
            Conexao.fecharConexao();
        }
        return null;
    }

    public void inserirMensagem(Mensagem msg, Usuario Usuario) throws ClassNotFoundException, SQLException {

        try {
            Connection conexao = (Connection) Conexao.getConexao();
            PreparedStatement pst;
            pst = conexao.prepareCall("INSERT INTO mensagem (id, msg, remetente)"
                    + "values (null,?,?)");
            pst.setString(1, msg.getMensagem());
            pst.setString(2, Usuario.getApelido());
            pst.execute();
            Conexao.fecharConexao();
        } catch (SQLException ex) {
            Logger.getLogger(MetodosDAO.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    public List<Usuario> listarUsuario(Usuario user) throws ClassNotFoundException, SQLException {
        try {
            Connection conexao = (Connection) Conexao.getConexao();
            PreparedStatement pst = conexao.prepareCall("SELECT nome,sobrenome,email,apelido FROM usuarios where id = ?");
            pst.setInt(1, user.getCodigo());
            ResultSet rs = pst.executeQuery();
            List<Usuario> lista = new ArrayList<>();
            while (rs.next()) {
                Usuario upp = new Usuario();
                upp.setNome(rs.getString("nome"));
                upp.setSobrenome(rs.getString("sobrenome"));
                upp.setEmail(rs.getString("email"));
                upp.setApelido(rs.getString("apelido"));
                lista.add(upp);
            }
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(MetodosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<Mensagem> listarMensagens() throws ClassNotFoundException, SQLException {

        try {
            Connection conexao = (Connection) Conexao.getConexao();
            PreparedStatement pst = conexao.prepareCall("SELECT msg,remetente FROM mensagem");
            ResultSet rs = pst.executeQuery();
            List<Mensagem> lista = new ArrayList<>();
            while (rs.next()) {
                Mensagem ms = new Mensagem();
                ms.setMensagem(rs.getString("msg"));
                ms.setRemetente(rs.getString("remetente"));
                lista.add(ms);
            }
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(MetodosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void deletarUsuario(Usuario usuario) throws ClassNotFoundException, SQLException {

        try {
            Connection conexao = (Connection) Conexao.getConexao();
            PreparedStatement pst;
            pst = conexao.prepareCall("DELETE FROM usuarios WHERE id=?");
            pst.setInt(1, usuario.getCodigo());
            pst.execute();
        } catch (SQLException ex) {
            Logger.getLogger(MetodosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean validaSenha(Usuario user) throws Exception {
        try {
            Connection conexao = (Connection) Conexao.getConexao();
            PreparedStatement pst;
            pst = conexao.prepareCall("select COUNT(*) AS res from usuarios where email = ? and senha = ?");
            pst.setString(1, user.getEmail());
            String res;
            res = Hash.EncriptarSHA(user.getSenha());
            pst.setString(2, res);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                return rs.getBoolean("res");
            }
        } catch (ClassNotFoundException | NoSuchAlgorithmException | SQLException e) {
            System.out.println("Erro de senha");
        } finally {
            Conexao.fecharConexao();
        }
        return false;
    }

    public void atualizarUsuario(Usuario uf) throws ClassNotFoundException, SQLException {
        try {
            Connection conexao = (Connection) Conexao.getConexao();
            PreparedStatement pst;
            pst = conexao.prepareCall("UPDATE usuarios SET Nome = ?, Sobrenome = ?, Apelido = ?, email = ? WHERE id = ?");
            pst.setString(1, uf.getNome());
            pst.setString(2, uf.getSobrenome());
            pst.setString(3, uf.getApelido());
            pst.setString(4, uf.getEmail());
            pst.setInt(5, uf.getCodigo());
            pst.execute();
        } catch (SQLException ex) {
            Logger.getLogger(MetodosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            Conexao.fecharConexao();
        }
    }
}
