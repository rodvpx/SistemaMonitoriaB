package model;

public class Disciplina {

    private String nome;
    private String codigo;
    private Monitor monitor;

    // Construtor padrão
    public Disciplina() {}

    // Construtor com parâmetros
    public Disciplina(String nome, String codigo) {
        this.codigo = codigo;
        this.nome = nome;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    @Override
    public String toString() {
        return nome; // Sobrescreve toString() para exibir o nome da disciplina
    }

}
