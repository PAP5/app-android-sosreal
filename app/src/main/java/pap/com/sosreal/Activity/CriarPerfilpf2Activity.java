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
import pap.com.sosreal.BO.PessoaFisica;
import pap.com.sosreal.Services.InstituicaoService;
import pap.com.sosreal.Services.PessoaFisicaService;
import pap.com.sosreal.R;
import pap.com.sosreal.Static;
import pap.com.sosreal.BO.Usuario;
import pap.com.sosreal.Services.UsuarioService;


public class CriarPerfilpf2Activity extends AppCompatActivity {
    private Bundle dados;
    private PessoaFisicaService service = PessoaFisicaService.getInstance();
    private UsuarioService serviceUsu = UsuarioService.getInstance();
    private InstituicaoService insService = InstituicaoService.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.criar_perfilpf2);

        Intent i = getIntent();
        dados = i.getExtras();
    }

    public void confirmar(View view) {
        if (((EditText) findViewById(R.id.txtCPF)).getText().toString().isEmpty() ||
                ((EditText) findViewById(R.id.txtTelCont)).getText().toString().isEmpty() ||
                ((EditText) findViewById(R.id.txtTelCel)).getText().toString().isEmpty()) {
            ((TextView) findViewById(R.id.lblErro)).setText(R.string.erroVazio);
        } else if(!Static.validarCPF(((EditText) findViewById(R.id.txtCPF)).getText().toString())){
            ((TextView) findViewById(R.id.lblErro)).setText(R.string.erroCPF);
        }else{
            new CadastrarPF().execute();
        }
    }

    private class CadastrarPF extends AsyncTask<String, Void, Void> {
        private ProgressDialog dialog;
        private String nome;
        private Date datanasc;
        private Date datainsc;
        private char sexo;
        private String CPF;
        private String telCont;
        private String telCel;
        private Bundle dadosUsu = new Bundle();
        private Usuario usu;
        private PessoaFisica pf2;

        class PFnovo {
            private String nome;
            private char sexo;
            private Date datanasc;
            private String cpf;
            private String telcel;
            private String telcontat;
            private Date datainscricao;
            private Usuario usuario;

            public PFnovo(String nome, char sexo, Date datanasc,
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

            public PFnovo() {
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

        @Override
        protected void onPreExecute() {

            dialog = new ProgressDialog(CriarPerfilpf2Activity.this);

            nome = dados.getString("nome");
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            try {
                Date dataTemp = format.parse(dados.getString("dataNasc"));
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                String data = df.format(dataTemp);
                datanasc = df.parse(data);

                Date vagina = new Date();
                String dataVag = df.format(vagina);
                datainsc = df.parse(dataVag);

                sexo = dados.getString("sexo").charAt(0);
                CPF = ((EditText) findViewById(R.id.txtCPF)).getText().toString();
                telCont = ((EditText) findViewById(R.id.txtTelCont)).getText().toString();
                telCel = ((EditText) findViewById(R.id.txtTelCel)).getText().toString();

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
                PFnovo pf = new PFnovo(nome, sexo, datanasc,
                        CPF, telCel, telCont,
                        datainsc, usu);
                service.post(pf);

                //pf2 = (PessoaFisica) insService.getByIdUsuario(usu.getId());

            } catch (RuntimeException e) {
                Log.d("erro", e.getMessage());
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void v) {

            Intent i = new Intent(CriarPerfilpf2Activity.this, PrincipalActivity.class);


            dadosUsu.putString("usuario", usu.getUsuario());
            dadosUsu.putString("email", usu.getEmail());
            dadosUsu.putInt("id", usu.getId());
            dadosUsu.putBoolean("temPF", true);
            //dadosUsu.putInt("idPerfil", pf2.getId());
            i.putExtras(dadosUsu);

            startActivity(i);
            dialog.dismiss();
            dialog = null;
            finish();
        }
    }
}
