package pap.com.sosreal;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    private Usuario usu = new Usuario();
    private List<Usuario> lista = new ArrayList<Usuario>();
    private UsuarioService service = new UsuarioService();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void entrar(View view) {
        EditText usuario = (EditText) findViewById(R.id.txtUsuario);
        EditText senha = (EditText) findViewById(R.id.txtSenha);
        TextView text = (TextView) findViewById(R.id.lblErro);

        if (verificaUsuario() == true) {
            new CarregarUsuario().execute();

        } else if (senha.getText().toString().equals("") || usuario.getText().toString().equals("")) {
            text.setText(R.string.erroVazio);
        } else {

            text.setText(R.string.erroLogin);
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

    public static boolean validar(String email) {
        boolean isEmailIdValid = false;
        if (email != null && email.length() > 0) {
            String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
            Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(email);
            if (matcher.matches()) {
                isEmailIdValid = true;
            }
        }
        return isEmailIdValid;
    }

    /*@Override
    public void onDestroy() {
        super.onDestroy();
        if (dialog != null) {
            dialog.dismiss();
            dialog = null;
        }
    }*/

    private class CarregarUsuario extends AsyncTask<String, Void, Usuario> {
        private ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            dialog = new ProgressDialog(MainActivity.this);
            dialog.show();
        }

        @Override
        protected void onPostExecute(Usuario usuario) {
            usu = usuario;
            Bundle dados = new Bundle();
            dados.putString("usuario", usu.getUsuario());
            dados.putString("email", usu.getEmail());
            dados.putInt("id", usu.getId());

            Intent i = new Intent(MainActivity.this, PrincipalActivity.class);
            i.putExtras(dados);
            startActivity(i);
            dialog.dismiss();
            dialog = null;
            finish();
        }

        @Override
        protected Usuario doInBackground(String... params) {
            return service.getById(1);
        }
    }
}
