package pap.com.sosreal.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;

import pap.com.sosreal.Adapters.DoacaoArrayAdapter;
import pap.com.sosreal.BO.Doacao;
import pap.com.sosreal.BO.Instituicao;
import pap.com.sosreal.Adapters.InstituicaoArrayAdapter;
import pap.com.sosreal.Services.DoacaoService;
import pap.com.sosreal.Services.InstituicaoService;
import pap.com.sosreal.R;

public class ConsultarDoacoesActivity extends AppCompatActivity {
    private List<Doacao> doacoes = new ArrayList<>();
    private DoacaoArrayAdapter adapter;
    private DoacaoService service = DoacaoService.getInstance();
    private Bundle dados;
    private Doacao doacao = new Doacao();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consultar_doacao);

        Intent i = getIntent();
        dados = i.getExtras();

        ListView list = (ListView) findViewById(R.id.listDoacoes);
        list.setDivider(new ColorDrawable(0x50303F9F));   //0xAARRGGBB
        list.setDividerHeight(2);
        list.setBackgroundResource(R.color.colorAzulLista);

        new PesquisarDoacoes().execute();
    }

    private class PesquisarDoacoes extends AsyncTask<String, Void, Void> {
        private ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            dialog = new ProgressDialog(ConsultarDoacoesActivity.this);
        }


        @Override
        protected Void doInBackground(String... params) {
            doacoes = service.getByIdUsuario(dados.getInt("id"));
            return null;
        }

        @Override
        protected void onPostExecute(Void v) {
            adapter = new DoacaoArrayAdapter(ConsultarDoacoesActivity.this, 0, doacoes);
            ((ListView) findViewById(R.id.listDoacoes)).setAdapter(adapter);
            dialog.dismiss();
        }

    }
}
