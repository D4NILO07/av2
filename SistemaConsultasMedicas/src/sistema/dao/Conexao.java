package sistema.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    private static final String URL = "jdbc:mysql://localhost:3306/sistema_consultas";
    private static final String USUARIO = "root"; // Troque se seu usuário for diferente
    private static final String SENHA = ""; // Coloque a senha do seu MySQL

    public static Connection conectar() throws SQLException {
        try {
            // Carrega o driver JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USUARIO, SENHA);
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver JDBC não encontrado!", e);
        }
    }
}
