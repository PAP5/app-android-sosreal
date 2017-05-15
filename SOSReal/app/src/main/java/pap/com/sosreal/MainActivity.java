package pap.com.sosreal;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    Usuario usu = new Usuario();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void entrar(View view){
        EditText usuario = (EditText) findViewById(R.id.txtUsuario);
        EditText senha = (EditText) findViewById(R.id.txtSenha);
        TextView text = (TextView) findViewById(R.id.lblErro);

            if (verificaUsuario() == true) {
                Bundle dados = new Bundle();
                dados.putString("usuario",usu.getUsuario());
                dados.putString("email",usu.getEmail());
                dados.putInt("id",usu.getId());

                Intent i = new Intent(this, PrincipalActivity.class);
                i.putExtras(dados);
                startActivity(i);
                finish();

            }else if(senha.getText().toString().equals("") || usuario.getText().toString().equals("")){
                text.setText(R.string.erroVazio);
            }
            else {

                text.setText(R.string.erroLogin);
            }


    }

    public boolean verificaUsuario(){
        EditText usuario = (EditText) findViewById(R.id.txtUsuario);
        EditText senha = (EditText) findViewById(R.id.txtSenha);


        try {
            usu = new UsuarioDB().buscarPorUsuESenha(usuario.getText().toString(), senha.getText().toString());
            return true;
        }
        catch(IllegalArgumentException e){
            return false;
        }
    }

    public void criarConta(View view){

        Intent i = new Intent(this,CriarUsuarioActivity.class);
        startActivityForResult(i,1);
    }

    public void alterarSenha(View view){
        Intent i = new Intent(this,SenhaAleatoriaActivity.class);
        startActivityForResult(i,2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                Bundle dados = data.getExtras();

                EditText usuario = (EditText) findViewById(R.id.txtUsuario);
                EditText senha = (EditText) findViewById(R.id.txtSenha);

                usuario.setText(dados.getString("usuario"), TextView.BufferType.EDITABLE);
                senha.setText(dados.getString("senha"), TextView.BufferType.EDITABLE);
            }
        }
    }

    public static boolean validar(String email)
    {
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
}
