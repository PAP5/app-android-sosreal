package pap.com.sosreal;

public class Usuario {
    private int id;
    private String usuario;
    private String senha;
    private String email;

    public Usuario(int id, String usuario, String senha, String email) {
        this.id = id;
        this.usuario = usuario;
        this.senha = senha;
        this.email = email;
    }

    public Usuario() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
