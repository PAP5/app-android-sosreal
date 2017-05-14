package pap.com.sosreal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;


public class PrincipalActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.principal);

        Intent i = getIntent();
        Bundle dados = i.getExtras();
        TextView id = (TextView) findViewById(R.id.id);
        TextView usuario = (TextView) findViewById(R.id.usuario);
        TextView email = (TextView) findViewById(R.id.email);

        id.setText(dados.getInt("id",0));
        usuario.setText(dados.getString("usuario"));
        email.setText(dados.getString("email"));
    }



}
