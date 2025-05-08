package sistema.dao;

import sistema.model.Consulta;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConsultaDAO {

    // Método para inserir consulta
    public void agendarConsulta(Consulta consulta) {
        String sql = "INSERT INTO consulta (id_paciente, id_medico, data, hora, observacao) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, consulta.getIdPaciente());
            stmt.setInt(2, consulta.getIdMedico());
            stmt.setDate(3, consulta.getData());
            stmt.setTime(4, consulta.getHora());
            stmt.setString(5, consulta.getObservacao());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para listar todas as consultas
    public List<Consulta> listarConsultas() {
        List<Consulta> consultas = new ArrayList<>();
        String sql = "SELECT * FROM consulta";

        try (Connection conn = Conexao.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Consulta consulta = new Consulta(
                        rs.getInt("id"),
                        rs.getInt("id_paciente"),
                        rs.getInt("id_medico"),
                        rs.getDate("data"),
                        rs.getTime("hora"),
                        rs.getString("observacao")
                );
                consultas.add(consulta);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return consultas;
    }

    // Método para atualizar consulta
    public void atualizarConsulta(Consulta consulta) {
        String sql = "UPDATE consulta SET id_paciente = ?, id_medico = ?, data = ?, hora = ?, observacao = ? WHERE id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, consulta.getIdPaciente());
            stmt.setInt(2, consulta.getIdMedico());
            stmt.setDate(3, consulta.getData());
            stmt.setTime(4, consulta.getHora());
            stmt.setString(5, consulta.getObservacao());
            stmt.setInt(6, consulta.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para excluir consulta
    public void excluirConsulta(int id) {
        String sql = "DELETE FROM consulta WHERE id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
