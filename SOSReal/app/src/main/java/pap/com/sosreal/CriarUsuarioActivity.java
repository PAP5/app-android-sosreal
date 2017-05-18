package pap.com.sosreal;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class CriarUsuarioActivity extends AppCompatActivity {
    private UsuarioService service = new UsuarioService();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.criar_usuario);

    }

    public void proximo(View view) {
        EditText senha = (EditText) findViewById(R.id.txtNovaSenha);
        EditText confirmarSenha = (EditText) findViewById(R.id.txtConfirmarSenha);
        EditText usuario = (EditText) findViewById(R.id.txtNovoUsuario);
        EditText email = (EditText) findViewById(R.id.txtNovoEmail);
        String senhaTxt = senha.getText().toString();
        String confirmarSenhaTxt = confirmarSenha.getText().toString();
        String emailTxt = email.getText().toString();
        String usuarioTxt = usuario.getText().toString();
        TextView lblErro = (TextView) findViewById(R.id.lblErro);

        if (!(Static.validarSenhasDiferentes(senhaTxt, confirmarSenhaTxt))) {
            lblErro.setText(R.string.erroSenhas);
        } else if (senhaTxt.equals("") || confirmarSenhaTxt.equals("") || emailTxt.equals("") || usuarioTxt.equals("")) {
            lblErro.setText(R.string.erroVazio);
        } else if (!Static.validar(emailTxt)) {
            lblErro.setText(R.string.erroEmail);
        } else {
            new SalvarUsuario().execute();
        }

    }

    private class SalvarUsuario extends AsyncTask<String, Void, Void> {
        private ProgressDialog dialog;
        private UsuNovo usu;

        class UsuNovo {
            private String usuario;
            private String senha;
            private String email;

            public UsuNovo(String usuario, String senha, String email) {
                this.usuario = usuario;
                this.senha = senha;
                this.email = email;
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

        @Override
        protected void onPreExecute() {
            dialog = new ProgressDialog(CriarUsuarioActivity.this);

            EditText senha = (EditText) findViewById(R.id.txtNovaSenha);
            EditText confirmarSenha = (EditText) findViewById(R.id.txtConfirmarSenha);
            EditText usuario = (EditText) findViewById(R.id.txtNovoUsuario);
            EditText email = (EditText) findViewById(R.id.txtNovoEmail);
            String senhaTxt = senha.getText().toString();
            String confirmarSenhaTxt = confirmarSenha.getText().toString();
            String emailTxt = email.getText().toString();
            String usuarioTxt = usuario.getText().toString();

            usu = new UsuNovo(usuarioTxt, senhaTxt, emailTxt);

            dialog.show();
        }


        @Override
        protected Void doInBackground(String... params) {
            try {
                service.post(usu);
            } catch (RuntimeException e) {
                Log.d("e", e.getMessage());
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void loco) {
            Bundle dados = new Bundle();
            dados.putString("senha", usu.getSenha());
            dados.putString("usuario", usu.getUsuario());
            dados.putString("email", usu.getEmail());
            Intent returnIntent = new Intent();
            returnIntent.putExtras(dados);
            dialog.dismiss();
            dialog = null;
            setResult(Activity.RESULT_OK, returnIntent);
            finish();
        }
    }
}