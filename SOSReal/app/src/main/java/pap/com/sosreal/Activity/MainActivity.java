package pap.com.sosreal.Activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;

import pap.com.sosreal.BO.Instituicao;
import pap.com.sosreal.BO.Usuario;
import pap.com.sosreal.R;
import pap.com.sosreal.Services.InstituicaoService;
import pap.com.sosreal.Services.UsuarioService;

public class MainActivity extends AppCompatActivity {
    private Usuario usu = new Usuario();
    private UsuarioService service = new UsuarioService();
    private InstituicaoService insService = new InstituicaoService();
    String usuarioTxtConteudo;
    String senhaTxtConteudo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void entrar(View view) {
        EditText usuarioTxt = (EditText) findViewById(R.id.txtUsuario);
        EditText senhaTxt = (EditText) findViewById(R.id.txtSenha);
        TextView text = (TextView) findViewById(R.id.lblErro);

        if (usuarioTxt.getText().toString().equals("") || senhaTxt.getText().toString().equals("")) {
            text.setText(R.string.erroVazio);
        } else {
            new CarregarUsuario().execute();
        }


    }

    public void criarConta(View view) {

        Intent i = new Intent(this, CriarUsuarioActivity.class);
        startActivityForResult(i, 1);
    }

    public void alterarSenha(View view) {
        Intent i = new Intent(this, SenhaAleatoriaActivity.class);
        startActivityForResult(i, 2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                Bundle dados = data.getExtras();

                EditText usuario = (EditText) findViewById(R.id.txtUsuario);
                EditText senha = (EditText) findViewById(R.id.txtSenha);

                usuario.setText(dados.getString("usuario"), TextView.BufferType.EDITABLE);
                senha.setText(dados.getString("senha"), TextView.BufferType.EDITABLE);
            }
        }
    }


    private class CarregarUsuario extends AsyncTask<String, String, Usuario> {
        private ProgressDialog dialog;
        private boolean temPF;
        private boolean temPJ;
        private Usuario usu2 = new Usuario();
        private Instituicao ins = new Instituicao();
        @Override
        protected void onPreExecute() {
            dialog = new ProgressDialog(MainActivity.this);
            usuarioTxtConteudo = ((EditText) findViewById(R.id.txtUsuario)).getText().toString();
            senhaTxtConteudo = ((EditText) findViewById(R.id.txtSenha)).getText().toString();
            dialog.show();
        }

        @Override
        protected void onPostExecute(Usuario usuario) {
            usu = usuario;

            if (usu != null) {
                Bundle dados = new Bundle();
                dados.putString("usuario", usu.getUsuario());
                dados.putString("email", usu.getEmail());
                dados.putInt("id", usu.getId());
                dados.putBoolean("temPF", temPF);
                dados.putBoolean("temPJ", temPJ);

                Intent i = new Intent(MainActivity.this, PrincipalActivity.class);

                i.putExtras(dados);
                startActivity(i);
                dialog.dismiss();
                dialog = null;
                finish();
            } else {
                Log.d("passou", "passou");
                ((TextView) findViewById(R.id.lblErro)).setText(R.string.erroLogin);
                dialog.dismiss();
                dialog = null;
            }

        }

        @Override
        protected Usuario doInBackground(String... params) {
            try {
                usu2 = service.getByUsuarioESenha(usuarioTxtConteudo, senhaTxtConteudo);
                temPF = service.validarPerfilPF(usu2.getId());
                temPJ = service.validarPerfilPJ(usu2.getId());

                if (temPF == true) {
                    //ins = insService.getByIdUsuario(usu2.getId());
                } else if (temPJ == true) {

                }
                return usu2;

            } catch (RuntimeException e) {
                return null;
            }
        }
    }
}
