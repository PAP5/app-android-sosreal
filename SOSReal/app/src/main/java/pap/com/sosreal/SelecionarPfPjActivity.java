package pap.com.sosreal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class SelecionarPfPjActivity extends AppCompatActivity{
    private Bundle dados;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selecionar_pf_pj);

        Intent i = getIntent();
        dados = i.getExtras();
    }

    private void pf(View view){
        Intent i = new Intent(this, CriarPerfilpf1Activity.class);
        i.putExtras(dados);
        startActivity(i);
    }

    private void pj(View view){
        Intent i = new Intent(this, CriarPerfilpj1Activity.class);
        i.putExtras(dados);
        startActivity(i);
    }


}
