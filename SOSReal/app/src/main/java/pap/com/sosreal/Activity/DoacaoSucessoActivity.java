package pap.com.sosreal.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import pap.com.sosreal.R;

public class DoacaoSucessoActivity extends AppCompatActivity{
    Bundle dados;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doacao_sucesso);

        Intent i = getIntent();
        dados = i.getExtras();
    }

    public void finalizar(View view){
        Intent principal = new Intent(DoacaoSucessoActivity.this, PrincipalActivity.class);
        principal.putExtras(dados);
        startActivity(principal);
        finish();
    }
}
