package model;

import java.util.ArrayList;

public class Aluno extends Usuario {

    public Aluno(String matricula, String nome, String email, String senha) {
        super(matricula, nome, email, senha, "A");
    }

}
