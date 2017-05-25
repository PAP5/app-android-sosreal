package pap.com.sosreal.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import pap.com.sosreal.R;


public class PrincipalActivity extends AppCompatActivity {
    private Bundle dados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.principal);

        Intent i = getIntent();
        this.dados = i.getExtras();

        if(dados.getBoolean("temPF") == false && dados.getBoolean("temPJ") == false){
            Intent intent = new Intent(this, SelecionarPfPjActivity.class);
            intent.putExtras(this.dados);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent i = getIntent();
        this.dados = i.getExtras();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    private void trocarSenha(MenuItem item) {
        Intent intent = new Intent(getBaseContext(), TrocarSenhaActivity.class);
        intent.putExtras(dados);
        startActivityForResult(intent, 2);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.trocarSenha) {
            trocarSenha(item);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void novaDoacao(View view){
        Intent novaDoacao = new Intent(PrincipalActivity.this, InstituicoesActivity.class);
        novaDoacao.putExtras(dados);
        startActivity(novaDoacao);
    }

    public void consultarDoacao(View view){

    }
}
