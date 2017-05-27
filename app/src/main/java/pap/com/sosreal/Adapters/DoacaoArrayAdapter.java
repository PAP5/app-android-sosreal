package pap.com.sosreal.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import pap.com.sosreal.BO.Doacao;
import pap.com.sosreal.R;

public class DoacaoArrayAdapter extends ArrayAdapter<Doacao> {
    private Context context;
    private List<Doacao> doacoes;

    public DoacaoArrayAdapter(Context context, int resource, List<Doacao> objects) {
        super(context, resource, objects);
        this.context = context;
        this.doacoes = objects;
    }

    public View getView(final int position, View convertView, final ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View linha = inflater.inflate(R.layout.listview, parent, false);
        TextView instituicao = (TextView) linha.findViewById(R.id.lblRazao);
        TextView valor = (TextView) linha.findViewById(R.id.lblDescricao);

        Doacao doacao = doacoes.get(position);
        instituicao.setText((doacao.getIns()).getRazao());
        valor.setText("R$: " + String.valueOf(doacao.getValordoado()));

        return linha;
    }
}
