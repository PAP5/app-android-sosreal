package pap.com.sosreal;

import java.util.Date;

public class PessoaFisica {
    private String nome;
    private char sexo;
    private Date Datanasc;
    private String CPF;
    private String telCel;
    private String telCont;
    private Date Dataincricao;
    private Usuario usuario;

    public PessoaFisica(String nome, char sexo, Date datanasc,
                        String CPF, String telCel, String telCont,
                        Date dataincricao, Usuario usuario) {

        this.nome = nome;
        this.sexo = sexo;
        Datanasc = datanasc;
        this.CPF = CPF;
        this.telCel = telCel;
        this.telCont = telCont;
        Dataincricao = dataincricao;
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

    public Date getDatanasc() {
        return Datanasc;
    }

    public void setDatanasc(Date datanasc) {
        Datanasc = datanasc;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getTelCel() {
        return telCel;
    }

    public void setTelCel(String telCel) {
        this.telCel = telCel;
    }

    public String getTelCont() {
        return telCont;
    }

    public void setTelCont(String telCont) {
        this.telCont = telCont;
    }

    public Date getDataincricao() {
        return Dataincricao;
    }

    public void setDataincricao(Date dataincricao) {
        Dataincricao = dataincricao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
