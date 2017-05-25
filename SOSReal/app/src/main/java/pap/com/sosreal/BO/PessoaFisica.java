package pap.com.sosreal.BO;

import java.util.Date;

public class PessoaFisica {
    private String nome;
    private char sexo;
    private Date datanasc;
    private String cpf;
    private String telcel;
    private String telcontat;
    private Date datainscricao;
    private Usuario usuario;

    public PessoaFisica(String nome, char sexo, Date datanasc,
                        String cpf, String telcel, String telcontat,

                        Date datainscricao, Usuario usuario) {

        this.nome = nome;
        this.sexo = sexo;
        this.datanasc = datanasc;
        this.cpf = cpf;
        this.telcel = telcel;
        this.telcontat = telcontat;
        this.datainscricao = datainscricao;
        this.usuario = usuario;

    }

    public PessoaFisica() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public Date getdatanasc() {
        return datanasc;
    }

    public void setdatanasc(Date datanasc) {
        this.datanasc = datanasc;
    }

    public String getcpf() {
        return cpf;
    }

    public void setcpf(String cpf) {
        this.cpf = cpf;
    }

    public String gettelcel() {
        return telcel;
    }

    public void settelcel(String telcel) {
        this.telcel = telcel;
    }

    public String gettelcont() {
        return telcontat;
    }

    public void settelcont(String telcontat) {
        this.telcontat = telcontat;
    }

    public Date getdatainscricao() {
        return datainscricao;
    }

    public void setdatainscricao(Date datainscricao) {
        this.datainscricao = datainscricao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
