package sistema.view;

import sistema.dao.ConsultaDAO;
import sistema.dao.PacienteDAO;
import sistema.dao.MedicoDAO;
import sistema.model.Consulta;
import sistema.model.Paciente;
import sistema.model.Medico;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

public class ListarConsultas extends JFrame {

    private JTable tabelaConsultas;
    private JScrollPane scrollPane;

    public ListarConsultas() {
        setTitle("Listagem de Consultas");
        setLayout(new BorderLayout());
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Tabela de consultas
        tabelaConsultas = new JTable();
        scrollPane = new JScrollPane(tabelaConsultas);
        add(scrollPane, BorderLayout.CENTER);

        // Carregar dados na tabela
        carregarConsultas();

        setVisible(true);
    }

    private void carregarConsultas() {
        ConsultaDAO consultaDAO = new ConsultaDAO();
        List<Consulta> consultas = consultaDAO.listarConsultas();

        // Definir as colunas da tabela
        String[] colunas = {"ID", "Paciente", "Médico", "Data", "Hora", "Observação"};
        DefaultTableModel modelo = new DefaultTableModel(colunas, 0);

        // Preencher a tabela com os dados das consultas
        for (Consulta consulta : consultas) {
            PacienteDAO pacienteDAO = new PacienteDAO();
            Paciente paciente = pacienteDAO.buscarPacientePorId(consulta.getIdPaciente());

            MedicoDAO medicoDAO = new MedicoDAO();
            Medico medico = medicoDAO.buscarMedicoPorId(consulta.getIdMedico());

            Object[] linha = {
                    consulta.getId(),
                    paciente.getNome(),
                    medico.getNome(),
                    consulta.getData().toString(),
                    consulta.getHora().toString(),
                    consulta.getObservacao()
            };
            modelo.addRow(linha);
        }

        tabelaConsultas.setModel(modelo);
    }

    public static void main(String[] args) {
        new ListarConsultas();
    }
}
