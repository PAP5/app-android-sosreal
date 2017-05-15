package pap.com.sosreal;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class TrocarSenhaActivity extends AppCompatActivity {
    Bundle dados;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trocar_senha);

        Intent i = getIntent();
        this.dados = i.getExtras();
    }

    public void confirmar(View view){
        EditText senha = (EditText) findViewById(R.id.txtSenhaAtual);
        EditText novaSenha = (EditText) findViewById(R.id.txtNovaSenha);
        EditText confirmarNovaSenha = (EditText) findViewById(R.id.txtConfirmarNovaSenha);
        TextView lblErro = (TextView) findViewById(R.id.lblErro);

        if(senha.getText().toString().isEmpty() || novaSenha.getText().toString().isEmpty() || confirmarNovaSenha.getText().toString().isEmpty()){
            lblErro.setText(R.string.erroVazio);
        }
        else if(!novaSenha.getText().toString().equals(confirmarNovaSenha.getText().toString())){
            lblErro.setText(R.string.erroSenhas);
        }
        else{
            finish();
        }

    }

}
