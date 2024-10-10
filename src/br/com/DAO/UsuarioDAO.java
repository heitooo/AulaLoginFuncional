package br.com.DAO;

import br.com.DTO.UsuarioDTO;
import java.sql.*;
import javax.swing.JOptionPane;

public class UsuarioDAO {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    public boolean inserirUsuario(UsuarioDTO objUsuarioDTO) {
        String sql = "insert into tbUsuario(id_usu, usuario, login, senha) values(?, ?, ?, ?)";
        conexao = new ConexaoDAO().conector();

        try {
            pst = conexao.prepareStatement(sql);
            pst.setInt(1, objUsuarioDTO.getId_usuario());
            pst.setString(2, objUsuarioDTO.getNomeUsuario());
            pst.setString(3, objUsuarioDTO.getLoginUsuario());
            pst.setString(4, objUsuarioDTO.getSenhaUsuario());

            pst.execute();
            pst.close();
            return true;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, " Inserir Usuário " + e);
            return false;
        }
    }

    public void editar(UsuarioDTO objDTO) {
        String sql = "update tbusuario set usuario = ?, login = ?, senha = ? where id_usu = ?";
        conexao = ConexaoDAO.conector();
        try {
            pst = conexao.prepareStatement(sql);
            pst.setInt(4, objDTO.getId_usuario());
            pst.setString(1, objDTO.getNomeUsuario());
            pst.setString(2, objDTO.getLoginUsuario());
            pst.setString(3, objDTO.getSenhaUsuario());

            int add = pst.executeUpdate();
            if (add > 0) {
                conexao.close();
                JOptionPane.showMessageDialog(null, "Usuário editado com sucesso.");

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, " Método editar " + e);
        }
    }

    public void apagar(UsuarioDTO objDTO) {
        String sql = "delete from tbusuario where id_usuario = ?";
        conexao = ConexaoDAO.conector();

        try {
            pst = conexao.prepareStatement(sql);
            pst.setInt(1, objDTO.getId_usuario());
            int add = pst.executeUpdate();
            if (add > 0) {
                conexao.close();
                JOptionPane.showMessageDialog(null, "Usuário excluído com sucesso.");

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Método apagar " + e);
        }
    }

}
