package pap.com.sosreal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class CriarUsuarioActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.criarusuario);

    }

    public void proximo(View view){
        EditText senha = (EditText) findViewById(R.id.txtNovaSenha);
        EditText confirmarSenha = (EditText) findViewById(R.id.txtConfirmarSenha);
        EditText usuario = (EditText) findViewById(R.id.txtNovoUsuario);
        EditText email = (EditText) findViewById(R.id.txtNovoEmail);

        String senhaTxt = senha.getText().toString();
        String confirmarSenhaTxt = confirmarSenha.getText().toString();
        String emailTxt = email.getText().toString();
        String usuarioTxt = usuario.getText().toString();
        TextView lblErro = (TextView) findViewById(R.id.lblErro);

        if(!(senhaTxt.equals(confirmarSenhaTxt))){
            lblErro.setText(R.string.erroSenhas);
        }
        else if(senhaTxt.equals("") || confirmarSenhaTxt.equals("") || emailTxt.equals("") || usuarioTxt.equals("")){
            lblErro.setText(R.string.erroVazio);
        }
        else{

            Intent i = new Intent(this,CriarPerfilActivity.class);
            startActivity(i);
            finish();

        }

    }



}