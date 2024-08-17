package model;

public class Supervisor extends Usuario {

    public Supervisor(String matricula, String nome, String email, String senha) {
        super(matricula, nome, email, senha, "S");
    }

}
