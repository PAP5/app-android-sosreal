package pap.com.sosreal.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import pap.com.sosreal.BO.Doacao;
import pap.com.sosreal.BO.Instituicao;
import pap.com.sosreal.BO.Usuario;
import pap.com.sosreal.R;
import pap.com.sosreal.Services.DoacaoService;
import pap.com.sosreal.Services.InstituicaoService;
import pap.com.sosreal.Services.UsuarioService;

public class NovaDoacaoActivity extends AppCompatActivity {
    private Bundle dadosUsu;
    private Bundle dadosIns;
    private DoacaoService service = DoacaoService.getInstance();
    private UsuarioService serviceUsuario = UsuarioService.getInstance();
    private InstituicaoService serviceInstituicao = InstituicaoService.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nova_doacao);

        Intent i = getIntent();
        Bundle dados = i.getExtras();
        dadosUsu = dados.getBundle("bundleUsuario");
        dadosIns = dados.getBundle("bundleInstituicao");
    }

    public void confirmar(View view) {
        new Doar().execute();

    }

    private class Doar extends AsyncTask<String, String, Void> {
        private ProgressDialog dialog;
        private Usuario usuario;
        private Instituicao instituicao;
        private String valordoado;

        @Override
        protected void onPreExecute() {
            dialog = new ProgressDialog(NovaDoacaoActivity.this);
            dialog.show();

            valordoado = ((EditText) findViewById(R.id.txtValor)).getText().toString();
        }

        @Override
        protected Void doInBackground(String... params) {
            try{
                usuario = serviceUsuario.getById(dadosUsu.getInt("id"));
                instituicao = serviceInstituicao.getById(dadosIns.getInt("idInstituicao"));
                service.post(new Doacao(usuario, Double.valueOf(valordoado), instituicao));
            }catch (Exception e){
                Log.d("erro", e.getMessage());
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void v) {
            Intent sucesso = new Intent(NovaDoacaoActivity.this, DoacaoSucessoActivity.class);
            sucesso.putExtras(dadosUsu);
            startActivity(sucesso);
            dialog.dismiss();
            dialog = null;
            finish();
        }


    }
}
