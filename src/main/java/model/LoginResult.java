package model;

public class LoginResult {
    private Usuario usuario;
    private String tipo;

    public LoginResult(Usuario usuario, String tipo) {
        this.usuario = usuario;
        this.tipo = tipo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public String getTipo() {
        return tipo;
    }
}

