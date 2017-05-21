package pap.com.sosreal;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;


public class CriarPerfilpf2Activity extends AppCompatActivity {
    private Bundle dados;
    private PessoaFisicaService service = new PessoaFisicaService();
    private UsuarioService serviceUsu = new UsuarioService();

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
        } else {
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

        @Override
        protected void onPreExecute() {
            dialog = new ProgressDialog(CriarPerfilpf2Activity.this);

            nome = dados.getString("nome");
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            try {

                datanasc = format.parse(dados.getString("dataNasc"));
            } catch (Exception e) {
                ((TextView) findViewById(R.id.lblErro)).setText(R.string.erroCadastro);
                Log.d("erro", e.getMessage());
            }
            datainsc = new Date();
            sexo = dados.getString("sexo").charAt(0);
            CPF = ((EditText) findViewById(R.id.txtCPF)).getText().toString();
            telCont = ((EditText) findViewById(R.id.txtTelCont)).getText().toString();
            telCel = ((EditText) findViewById(R.id.txtTelCel)).getText().toString();

            dialog.show();
        }

        @Override
        protected void onPostExecute(Void v) {
            dialog.dismiss();
            dialog = null;
            finish();
        }

        @Override
        protected Void doInBackground(String... params) {
            try {
                Usuario usu = new Usuario();
                usu = serviceUsu.getById(dados.getInt("id"));
                PessoaFisica pf = new PessoaFisica(nome, sexo, datanasc,
                        CPF, telCel, telCont,
                        datainsc, usu);
                ;
                 service.post(pf);

            } catch (RuntimeException e) {
                Log.d("erro", e.getMessage());
            }
            return null;
        }
    }
}
