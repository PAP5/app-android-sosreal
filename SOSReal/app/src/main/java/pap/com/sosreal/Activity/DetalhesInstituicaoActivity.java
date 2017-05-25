package pap.com.sosreal.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import pap.com.sosreal.R;

public class DetalhesInstituicaoActivity extends AppCompatActivity{
    private Bundle dadosUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalhes_instituicao);

        Intent i = getIntent();
        Bundle dados = i.getExtras();
        dadosUsuario = dados.getBundle("bundleUsuario");
        Bundle dadosIns = dados.getBundle("bundleInstituicao");

        ((EditText) findViewById(R.id.txtCNPJ)).setText(dadosIns.getString("cnpj"));
        ((EditText) findViewById(R.id.txtDescricao)).setText(dadosIns.getString("descricao"));
        ((EditText) findViewById(R.id.txtRazao)).setText(dadosIns.getString("razao"));
        ((EditText) findViewById(R.id.txtResponsavel)).setText(dadosIns.getString("responsavel"));
        ((EditText) findViewById(R.id.txtTel1)).setText(dadosIns.getString("telcontat1"));
        ((EditText) findViewById(R.id.txtTel2)).setText(dadosIns.getString("telcontat2"));

    }

    public void doar(View view){
        Intent doar = new Intent(DetalhesInstituicaoActivity.this, NovaDoacaoActivity.class);

    }
}
