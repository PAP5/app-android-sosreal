package pap.com.sosreal.BO;

public class Instituicao {
    private String cnpj;
    private String contabancaria;
    private String descricao;
    private int id;
    private String infotribut;
    private String inscricao;
    private String razao;
    private String responsavel;
    private String telcontat1;
    private String telcontat2;

    public Instituicao(String cnpj, String contabancaria, String descricao, int id,
                       String infotribut, String inscricao, String razao, String responsavel,
                       String telcontat1, String telcontat2) {
        this.cnpj = cnpj;
        this.contabancaria = contabancaria;
        this.descricao = descricao;
        this.id = id;
        this.infotribut = infotribut;
        this.inscricao = inscricao;
        this.razao = razao;
        this.responsavel = responsavel;
        this.telcontat1 = telcontat1;
        this.telcontat2 = telcontat2;
    }

    public Instituicao() {
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getContabancaria() {
        return contabancaria;
    }

    public void setContabancaria(String contabancaria) {
        this.contabancaria = contabancaria;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInfotribut() {
        return infotribut;
    }

    public void setInfotribut(String infotribut) {
        this.infotribut = infotribut;
    }

    public String getInscricao() {
        return inscricao;
    }

    public void setInscricao(String inscricao) {
        this.inscricao = inscricao;
    }

    public String getRazao() {
        return razao;
    }

    public void setRazao(String razao) {
        this.razao = razao;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    public String getTelcontat1() {
        return telcontat1;
    }

    public void setTelcontat1(String telcontat1) {
        this.telcontat1 = telcontat1;
    }

    public String getTelcontat2() {
        return telcontat2;
    }

    public void setTelcontat2(String telcontat2) {
        this.telcontat2 = telcontat2;
    }
}
