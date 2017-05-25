package pap.com.sosreal.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

import pap.com.sosreal.BO.Instituicao;
import pap.com.sosreal.BO.PessoaJuridica;
import pap.com.sosreal.Services.InstituicaoService;
import pap.com.sosreal.Services.PessoaFisicaService;
import pap.com.sosreal.Services.PessoaJuridicaService;
import pap.com.sosreal.R;
import pap.com.sosreal.Static;
import pap.com.sosreal.BO.Usuario;
import pap.com.sosreal.Services.UsuarioService;


public class CriarPerfilpj2Activity extends AppCompatActivity {
    private Bundle dados;
    private PessoaJuridicaService service = PessoaJuridicaService.getInstance();
    private UsuarioService serviceUsu = UsuarioService.getInstance();
    private InstituicaoService insService = InstituicaoService.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.criar_perfilpj2);

        Intent i = getIntent();
        dados = i.getExtras();
    }

    public void confirmar(View view) {
        if (((EditText) findViewById(R.id.txtCNPJ)).getText().toString().isEmpty() ||
                ((EditText) findViewById(R.id.txtTelCont)).getText().toString().isEmpty() ||
                ((EditText) findViewById(R.id.txtTelCel)).getText().toString().isEmpty()) {
            ((TextView) findViewById(R.id.lblErro)).setText(R.string.erroVazio);
        } else if(!Static.validarCNPJ(((EditText) findViewById(R.id.txtCNPJ)).getText().toString())) {
            ((TextView) findViewById(R.id.lblErro)).setText(R.string.erroCNPJ);
        } else{
            new CadastrarPJ().execute();
        }
    }

    private class CadastrarPJ extends AsyncTask<String, Void, Void> {
        private ProgressDialog dialog;
        private String razao;
        private String cnpj;
        private String inscricao;
        private String responsavel;
        private char infotribut;
        private String telcontat;
        private String telcel;
        private Date datainscricao;
        private Usuario usu;
        private PessoaJuridica pj2;

        class PJnovo {
            private String razao;
            private String cnpj;
            private String inscricao;
            private String responsavel;
            private char infotribut;
            private String telcontat;
            private String telcel;
            private Date datainscricao;
            private Usuario usuario;

            public PJnovo(String razao, String cnpj, String inscricao, String responsavel,
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

            public PJnovo() {
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

        @Override
        protected void onPreExecute() {

            dialog = new ProgressDialog(CriarPerfilpj2Activity.this);

            razao = dados.getString("razao");
            infotribut = dados.getString("tributo").charAt(0);
            responsavel = dados.getString("responsavel");
            inscricao = dados.getString("inscricao");
            cnpj = ((EditText) findViewById(R.id.txtCNPJ)).getText().toString();
            telcontat = ((EditText) findViewById(R.id.txtTelCont)).getText().toString();
            telcel = ((EditText) findViewById(R.id.txtTelCel)).getText().toString();
            try {

                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                Date data = new Date();
                String data2 = df.format(data);
                datainscricao = df.parse(data2);


                dialog.show();
            } catch (Exception e) {
                ((TextView) findViewById(R.id.lblErro)).setText(R.string.erroCadastro);
                Log.d("erro", e.getMessage());
            }

        }


        @Override
        protected Void doInBackground(String... params) {
            try {
                usu = serviceUsu.getById(dados.getInt("id"));
                PJnovo pj = new PJnovo(razao, cnpj, inscricao, responsavel,
                        infotribut, telcontat, telcel, datainscricao, usu);
                service.post(pj);

               // pj2 = (PessoaJuridica) insService.getByIdUsuario(usu.getId());
            } catch (RuntimeException e) {
                Log.d("erro", e.getMessage());
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void v) {

            Intent i = new Intent(CriarPerfilpj2Activity.this, PrincipalActivity.class);

            Bundle dadosUsu = new Bundle();
            dadosUsu.putString("usuario", usu.getUsuario());
            dadosUsu.putString("email", usu.getEmail());
            dadosUsu.putInt("id", usu.getId());
            dadosUsu.putBoolean("temPJ", true);
            //dadosUsu.putInt("idPerfil", pj2.getId());
            i.putExtras(dadosUsu);

            startActivity(i);
            dialog.dismiss();
            dialog = null;
            finish();
        }
    }
}
