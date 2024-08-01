package model;

import java.util.ArrayList;

public class Monitor extends Aluno {

    private ArrayList<String> disciplina;
    private Horario horario;
    private Local local;

    public Monitor(String matricula, String nome, String email, String senha, ArrayList<String> disciplina, Local local, Horario horario) {
        super(matricula, nome, email, senha);
    }

    }