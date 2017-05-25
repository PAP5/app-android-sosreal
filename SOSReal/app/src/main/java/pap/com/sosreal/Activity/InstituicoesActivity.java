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

import pap.com.sosreal.BO.Instituicao;
import pap.com.sosreal.Adapters.InstituicaoArrayAdapter;
import pap.com.sosreal.Services.InstituicaoService;
import pap.com.sosreal.R;

public class InstituicoesActivity extends AppCompatActivity {
    private List<Instituicao> instituicoes = new ArrayList<>();
    private InstituicaoArrayAdapter adapter;
    private InstituicaoService service = new InstituicaoService();
    private Bundle dados;
    private Instituicao ins = new Instituicao();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.instituicoes);

        Intent i = getIntent();
        dados = i.getExtras();

        ListView list = (ListView) findViewById(R.id.listInstituicoes);
        list.setDivider(new ColorDrawable(0x50303F9F));   //0xAARRGGBB
        list.setDividerHeight(2);
        list.setBackgroundResource(R.color.colorAzulLista);

        new PesquisarInstituicoes().execute();

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                ins = instituicoes.get(position);
                Intent detalhesInstituicao = new Intent(InstituicoesActivity.this, DetalhesInstituicaoActivity.class);

                Bundle dadosIns = new Bundle();
                dadosIns.putInt("idInstituicao", ins.getId());
                dadosIns.putString("cnpj",ins.getCnpj());
                dadosIns.putString("descricao",ins.getDescricao());
                dadosIns.putString("cnpj",ins.getCnpj());
                dadosIns.putString("infotribut", ins.getInfotribut());
                dadosIns.putString("inscricao", ins.getInscricao());
                dadosIns.putString("razao",ins.getRazao());
                dadosIns.putString("responsavel",ins.getResponsavel());
                dadosIns.putString("telcontat1",ins.getTelcontat1());
                dadosIns.putString("telcontat2",ins.getTelcontat2());

                Bundle bundles = new Bundle();
                bundles.putBundle("bundleUsuario", dados);
                bundles.putBundle("bundleInstituicao", dadosIns);
                detalhesInstituicao.putExtras(bundles);
                startActivity(detalhesInstituicao);
            }
        });

    }

    private class PesquisarInstituicoes extends AsyncTask<String, Void, Void> {
        private ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            dialog = new ProgressDialog(InstituicoesActivity.this);
        }


        @Override
        protected Void doInBackground(String... params) {
            instituicoes = service.getAll();
            return null;
        }

        @Override
        protected void onPostExecute(Void v) {
            adapter = new InstituicaoArrayAdapter(InstituicoesActivity.this, 0, instituicoes);
            ((ListView) findViewById(R.id.listInstituicoes)).setAdapter(adapter);
            dialog.dismiss();
        }

    }
}
