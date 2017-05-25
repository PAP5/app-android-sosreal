package pap.com.sosreal.BO;

import java.util.Date;

public class Doacao {

    private Usuario usuario;
    private double valordoado;
    private Instituicao instituicao;

    public Doacao(Usuario usu, double valordoado, Instituicao ins) {
        this.usuario = usu;
        this.valordoado = valordoado;
        this.instituicao = ins;
    }

    public Doacao() {
    }

    public Usuario getUsu() {
        return usuario;
    }

    public void setUsu(Usuario usu) {
        this.usuario = usu;
    }

    public double getValordoado() {
        return valordoado;
    }

    public void setValordoado(double valordoado) {
        this.valordoado = valordoado;
    }

    public Instituicao getIns() {
        return instituicao;
    }

    public void setIns(Instituicao ins) {
        this.instituicao = ins;
    }
}
