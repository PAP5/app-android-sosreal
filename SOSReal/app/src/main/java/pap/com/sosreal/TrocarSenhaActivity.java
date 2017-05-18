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

public class TrocarSenhaActivity extends AppCompatActivity {
    private Bundle dados;
    private UsuarioService service = new UsuarioService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trocar_senha);

        Intent i = getIntent();
        this.dados = i.getExtras();
    }

    public void confirmar(View view) {
        EditText senha = (EditText) findViewById(R.id.txtSenhaAtual);
        EditText novaSenha = (EditText) findViewById(R.id.txtNovaSenha);
        EditText confirmarNovaSenha = (EditText) findViewById(R.id.txtConfirmarNovaSenha);
        TextView lblErro = (TextView) findViewById(R.id.lblErro);

        if (senha.getText().toString().isEmpty() || novaSenha.getText().toString().isEmpty() || confirmarNovaSenha.getText().toString().isEmpty()) {
            lblErro.setText(R.string.erroVazio);
        } else if (!Static.validarSenhasDiferentes(novaSenha.getText().toString(), confirmarNovaSenha.getText().toString())) {
            lblErro.setText(R.string.erroSenhas);
        } else {
            new AtualizarUsuario().execute();
        }

    }

    private class AtualizarUsuario extends AsyncTask<String, Void, Void> {
        private ProgressDialog dialog;
        private Usuario usu;
        //private Usuario usuAntigo = new Usuario();
        private boolean flag = false;
        private String senhaAtual;
        private String usuarioDados;
        private String senhaNova;
        String emailDados;

        @Override
        protected void onPreExecute() {
            dialog = new ProgressDialog(TrocarSenhaActivity.this);
            usuarioDados = dados.getString("usuario");
            senhaAtual = ((EditText) findViewById(R.id.txtSenhaAtual)).getText().toString();
            emailDados = dados.getString("email");
            senhaNova = ((EditText) findViewById(R.id.txtNovaSenha)).getText().toString();

            usu = new Usuario(usuarioDados, senhaNova, emailDados);
            dialog.show();
        }


        @Override
        protected Void doInBackground(String... params) {
            try {
                usu = service.getByUsuarioESenha(usuarioDados, senhaAtual);
                if (usu != null) {
                    usu.setSenha(senhaNova);
                    service.post(usu);
                    flag = true;
                }
            } catch (RuntimeException e) {
                Log.d("e", e.getMessage());
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void loco) {
            if (flag == false) {
                ((TextView) findViewById(R.id.lblErro)).setText(R.string.erroSenhaAntiga);
                dialog.dismiss();
                dialog = null;
            } else {
                Bundle dados2 = new Bundle();

                dados2.putString("senha", usu.getSenha());
                dados2.putString("usuario", usu.getUsuario());
                dados2.putInt("id", usu.getId());
                dados2.putString("email", usu.getEmail());
                Intent returnIntent = new Intent();
                returnIntent.putExtras(dados);

                setResult(Activity.RESULT_OK, returnIntent);
                finish();
            }

        }
    }

}
