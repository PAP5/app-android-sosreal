package pap.com.sosreal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    Usuario usu = new Usuario();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void entrar(View view){

            if (verificaUsuario() == true) {
                Bundle dados = new Bundle();
                dados.putString("usuario",usu.getUsuario());
                dados.putString("email",usu.getEmail());
                dados.putInt("id",usu.getId());

                Intent i = new Intent(this, PrincipalActivity.class);
                i.putExtras(dados);
                startActivity(i);
                finish();

            } else {
                TextView text = (TextView) findViewById(R.id.lblErro);
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
        startActivity(i);
        finish();
    }



}
