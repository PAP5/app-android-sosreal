package pap.com.sosreal;


import java.util.Date;

public class PessoaJuridica {
    private String razao;
    private String cnpj;
    private String inscricao;
    private String responsavel;
    private char infotribut;
    private String telcontat;
    private String telcel;
    private Date datainscricao;
    private Usuario usuario;

    public PessoaJuridica(String razao, String cnpj, String inscricao, String responsavel,
                          char infotribut, String telcontat, String telcel, Date datainscricao,
                          Usuario usuario) {
        this.razao = razao;
        this.cnpj = cnpj;
        this.inscricao = inscricao;
        this.responsavel = responsavel;
        this.infotribut = infotribut;
        this.telcontat = telcontat;
        this.telcel = telcel;
        this.datainscricao = datainscricao;
        this.usuario = usuario;
    }

    public PessoaJuridica() {
    }

    public String getRazao() {
        return razao;
    }

    public void setRazao(String razao) {
        this.razao = razao;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getInscricao() {
        return inscricao;
    }

    public void setInscricao(String inscricao) {
        this.inscricao = inscricao;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    public char getInfotribut() {
        return infotribut;
    }

    public void setInfotribut(char infotribut) {
        this.infotribut = infotribut;
    }

    public String getTelcontat() {
        return telcontat;
    }

    public void setTelcontat(String telcontat) {
        this.telcontat = telcontat;
    }

    public String getTelcel() {
        return telcel;
    }

    public void setTelcel(String telcel) {
        this.telcel = telcel;
    }

    public Date getDatainscricao() {
        return datainscricao;
    }

    public void setDatainscricao(Date datainscricao) {
        this.datainscricao = datainscricao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
