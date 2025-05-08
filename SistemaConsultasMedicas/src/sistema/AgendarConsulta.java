package sistema.view;

import sistema.dao.ConsultaDAO;
import sistema.dao.PacienteDAO;
import sistema.dao.MedicoDAO;
import sistema.model.Consulta;
import sistema.model.Paciente;
import sistema.model.Medico;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.Time;

public class AgendarConsulta extends JFrame {

    private JComboBox<Paciente> pacienteComboBox;
    private JComboBox<Medico> medicoComboBox;
    private JTextField dataField, horaField, observacaoField;
    private JButton btnAgendar;

    public AgendarConsulta() {
        setTitle("Agendamento de Consulta");
        setLayout(new GridLayout(6, 2));
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Labels e campos
        add(new JLabel("Paciente:"));
        pacienteComboBox = new JComboBox<>();
        add(pacienteComboBox);

        add(new JLabel("Médico:"));
        medicoComboBox = new JComboBox<>();
        add(medicoComboBox);

        add(new JLabel("Data (AAAA-MM-DD):"));
        dataField = new JTextField();
        add(dataField);

        add(new JLabel("Hora (HH:MM:SS):"));
        horaField = new JTextField();
        add(horaField);

        add(new JLabel("Observação:"));
        observacaoField = new JTextField();
        add(observacaoField);

        // Botão agendar
        btnAgendar = new JButton("Agendar");
        add(btnAgendar);

        // Carregar dados para ComboBox
        carregarPacientes();
        carregarMedicos();

        // Evento de agendamento
        btnAgendar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agendarConsulta();
            }
        });

        setVisible(true);
    }

    private void carregarPacientes() {
        PacienteDAO pacienteDAO = new PacienteDAO();
        for (Paciente paciente : pacienteDAO.listarPacientes()) {
            pacienteComboBox.addItem(paciente);
        }
    }

    private void carregarMedicos() {
        MedicoDAO medicoDAO = new MedicoDAO();
        for (Medico medico : medicoDAO.listarMedicos()) {
            medicoComboBox.addItem(medico);
        }
    }

    private void agendarConsulta() {
        Paciente paciente = (Paciente) pacienteComboBox.getSelectedItem();
        Medico medico = (Medico) medicoComboBox.getSelectedItem();
        String dataStr = dataField.getText();
        String horaStr = horaField.getText();
        String observacao = observacaoField.getText();

        if (paciente == null || medico == null || dataStr.isEmpty() || horaStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos os campos devem ser preenchidos!");
            return;
        }

        Date data = Date.valueOf(dataStr);
        Time hora = Time.valueOf(horaStr);

        Consulta consulta = new Consulta(0, paciente.getId(), medico.getId(), data, hora, observacao);
        ConsultaDAO consultaDAO = new ConsultaDAO();
        consultaDAO.agendarConsulta(consulta);

        JOptionPane.showMessageDialog(this, "Consulta agendada com sucesso!");
    }

    public static void main(String[] args) {
        new AgendarConsulta();
    }
}
