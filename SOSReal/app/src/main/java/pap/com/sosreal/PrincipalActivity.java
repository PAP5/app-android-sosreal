package pap.com.sosreal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class PrincipalActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.principal);

        Intent i = getIntent();
        Bundle dados = i.getExtras();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    private void trocarSenha(MenuItem item) {
        Intent intent = new Intent(getBaseContext(), SenhaAleatoriaActivity.class);
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
        
    }

    public void consultarDoacao(View view){

    }



}
