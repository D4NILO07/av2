package sistema.view;

import sistema.dao.PacienteDAO;
import sistema.model.Paciente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CadastroPaciente extends JFrame {

    private JTextField nomeField, cpfField, telefoneField;
    private JButton btnCadastrar;

    public CadastroPaciente() {
        setTitle("Cadastro de Paciente");
        setLayout(new GridLayout(4, 2));
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Labels
        add(new JLabel("Nome:"));
        nomeField = new JTextField();
        add(nomeField);

        add(new JLabel("CPF:"));
        cpfField = new JTextField();
        add(cpfField);

        add(new JLabel("Telefone:"));
        telefoneField = new JTextField();
        add(telefoneField);

        // Botão cadastrar
        btnCadastrar = new JButton("Cadastrar");
        add(btnCadastrar);

        // Evento de cadastro
        btnCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrarPaciente();
            }
        });

        setVisible(true);
    }

    private void cadastrarPaciente() {
        String nome = nomeField.getText();
        String cpf = cpfField.getText();
        String telefone = telefoneField.getText();

        if (nome.isEmpty() || cpf.isEmpty() || telefone.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos os campos devem ser preenchidos!");
            return;
        }

        Paciente paciente = new Paciente(0, nome, cpf, telefone);
        PacienteDAO pacienteDAO = new PacienteDAO();
        pacienteDAO.inserirPaciente(paciente);

        JOptionPane.showMessageDialog(this, "Paciente cadastrado com sucesso!");

        // Limpar campos após cadastro
        nomeField.setText("");
        cpfField.setText("");
        telefoneField.setText("");
    }

    public static void main(String[] args) {
        new CadastroPaciente();
    }
}
