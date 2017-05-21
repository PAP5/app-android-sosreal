package pap.com.sosreal;

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

public class MainActivity extends AppCompatActivity {
    private Usuario usu = new Usuario();
    private UsuarioService service = new UsuarioService();
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

    public boolean verificaUsuario() {
        EditText usuario = (EditText) findViewById(R.id.txtUsuario);
        EditText senha = (EditText) findViewById(R.id.txtSenha);


        try {
            usu = new UsuarioService().buscarPorUsuESenha(usuario.getText().toString(), senha.getText().toString());
            return true;

        } catch (IllegalArgumentException e) {
            return false;
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

                Intent i = new Intent(MainActivity.this, CriarPerfilpf1Activity.class);
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
                return service.getByUsuarioESenha(usuarioTxtConteudo, senhaTxtConteudo);
            }catch (RuntimeException e){
                return null;
            }
        }
    }
}
