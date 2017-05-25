package pap.com.sosreal.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import pap.com.sosreal.R;

public class CriarPerfilpj1Activity extends AppCompatActivity {
    private Bundle dados = new Bundle();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.criar_perfilpj1);

        Intent i = getIntent();
        dados = i.getExtras();


        Spinner spinner = (Spinner) findViewById(R.id.spinner_tributo);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.tributo_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    public void proximo(View view) {

        TextView lblErro = (TextView) findViewById(R.id.lblErro);


        String razao = ((EditText) findViewById(R.id.txtRazao)).getText().toString();
        String responsavel = ((EditText) findViewById(R.id.txtResponsavel)).getText().toString();
        String inscricao = ((EditText) findViewById(R.id.txtInscricao)).getText().toString();
        String tributo = ((Spinner) findViewById(R.id.spinner_tributo)).getSelectedItem().toString();

        if (razao.isEmpty() ||
                responsavel.isEmpty() ||
                inscricao.isEmpty() ||
                tributo.equals("Selecione")) {
            lblErro.setText(R.string.erroVazio);
        } else {
            dados.putString("razao", razao);
            dados.putString("responsavel", responsavel);
            dados.putString("inscricao", inscricao);
            if (tributo.equals("Contribuinte")) {
                dados.putString("tributo", "c");
            } else if (tributo.equals("Não contribuinte")) {
                dados.putString("tributo", "n");
            } else if (tributo.equals("Isento")) {
                dados.putString("tributo", "i");
            }

            Intent i = new Intent(this, CriarPerfilpj2Activity.class);
            i.putExtras(dados);
            startActivity(i);
        }
    }

}

