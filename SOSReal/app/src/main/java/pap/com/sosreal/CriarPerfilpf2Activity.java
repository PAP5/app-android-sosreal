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

        @Override
        protected void onPreExecute() {

            dialog = new ProgressDialog(CriarPerfilpf2Activity.this);

            nome = dados.getString("nome");
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            try {
                Date penis = format.parse(dados.getString("dataNasc"));
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                String data = df.format(penis);
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
                PessoaFisica pf = new PessoaFisica(nome, sexo, datanasc,
                        CPF, telCel, telCont,
                        datainsc, usu);
                service.post(pf);


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
            i.putExtras(dadosUsu);

            startActivity(i);
            dialog.dismiss();
            dialog = null;
            finish();
        }
    }
}
