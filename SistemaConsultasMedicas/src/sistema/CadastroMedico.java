package sistema.view;

import sistema.dao.MedicoDAO;
import sistema.model.Medico;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CadastroMedico extends JFrame {

    private JTextField nomeField, especialidadeField, crmField;
    private JButton btnCadastrar;

    public CadastroMedico() {
        setTitle("Cadastro de Médico");
        setLayout(new GridLayout(4, 2));
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Labels
        add(new JLabel("Nome:"));
        nomeField = new JTextField();
        add(nomeField);

        add(new JLabel("Especialidade:"));
        especialidadeField = new JTextField();
        add(especialidadeField);

        add(new JLabel("CRM:"));
        crmField = new JTextField();
        add(crmField);

        // Botão cadastrar
        btnCadastrar = new JButton("Cadastrar");
        add(btnCadastrar);

        // Evento de cadastro
        btnCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrarMedico();
            }
        });

        setVisible(true);
    }

    private void cadastrarMedico() {
        String nome = nomeField.getText();
        String especialidade = especialidadeField.getText();
        String crm = crmField.getText();

        if (nome.isEmpty() || especialidade.isEmpty() || crm.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos os campos devem ser preenchidos!");
            return;
        }

        Medico medico = new Medico(0, nome, especialidade, crm);
        MedicoDAO medicoDAO = new MedicoDAO();
        medicoDAO.inserirMedico(medico);

        JOptionPane.showMessageDialog(this, "Médico cadastrado com sucesso!");

        // Limpar campos após cadastro
        nomeField.setText("");
        especialidadeField.setText("");
        crmField.setText("");
    }

    public static void main(String[] args) {
        new CadastroMedico();
    }
}
