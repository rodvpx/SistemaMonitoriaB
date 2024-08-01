package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import static factory.conexao.getConexao;

public class Supervisor extends Usuario {

    public Supervisor(String matricula, String nome, String email, String senha) {
        super(matricula, nome, email, senha, "S");
    }


}
