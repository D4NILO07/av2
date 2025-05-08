package sistema.dao;

import sistema.model.Paciente;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PacienteDAO {

    // Método para inserir paciente
    public void inserirPaciente(Paciente paciente) {
        String sql = "INSERT INTO paciente (nome, cpf, telefone) VALUES (?, ?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, paciente.getNome());
            stmt.setString(2, paciente.getCpf());
            stmt.setString(3, paciente.getTelefone());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para listar todos os pacientes
    public List<Paciente> listarPacientes() {
        List<Paciente> pacientes = new ArrayList<>();
        String sql = "SELECT * FROM paciente";

        try (Connection conn = Conexao.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Paciente paciente = new Paciente(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("cpf"),
                        rs.getString("telefone")
                );
                pacientes.add(paciente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pacientes;
    }

    // Método para atualizar os dados de um paciente
    public void atualizarPaciente(Paciente paciente) {
        String sql = "UPDATE paciente SET nome = ?, cpf = ?, telefone = ? WHERE id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, paciente.getNome());
            stmt.setString(2, paciente.getCpf());
            stmt.setString(3, paciente.getTelefone());
            stmt.setInt(4, paciente.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para excluir um paciente
    public void excluirPaciente(int id) {
        String sql = "DELETE FROM paciente WHERE id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
