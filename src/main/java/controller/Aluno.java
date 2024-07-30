package controller;

import java.util.ArrayList;

public class Aluno extends Usuario {

    public Aluno(String matricula, String nome, String email, String senha) {
        super(matricula, nome, email, senha, "A");
    }


    // Métodos específicos de Aluno

    public void visualizarMonitorias(ArrayList<Monitoria> monitorias) {
        // Implementação do método
    }

    public void inscreverMonitoria(Monitoria monitoria) {
        // Implementação do método
    }

    public void solicitarMonitoria(Monitoria monitoria) {
        // Implementação do método
    }

    public void candidatarMonitor(Monitoria monitoria) {
        // Implementação do método
    }
}
