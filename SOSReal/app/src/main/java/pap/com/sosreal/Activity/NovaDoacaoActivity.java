package pap.com.sosreal.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import pap.com.sosreal.R;

public class NovaDoacaoActivity extends AppCompatActivity{
    Bundle dadosUsu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nova_doacao);

        Intent i = getIntent();
        dadosUsu = i.getExtras();
    }
}
