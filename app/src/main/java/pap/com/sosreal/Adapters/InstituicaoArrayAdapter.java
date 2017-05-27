package pap.com.sosreal.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import pap.com.sosreal.BO.Instituicao;
import pap.com.sosreal.R;

public class InstituicaoArrayAdapter extends ArrayAdapter<Instituicao> {
    private Context context;
    private List<Instituicao> instituicaos;

    public InstituicaoArrayAdapter(Context context, int resource, List<Instituicao> objects) {
        super(context, resource, objects);
        this.context = context;
        this.instituicaos = objects;
    }

    public View getView(final int position, View convertView, final ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View linha = inflater.inflate(R.layout.listview, parent, false);
        TextView razao = (TextView) linha.findViewById(R.id.lblRazao);
        TextView descricao = (TextView) linha.findViewById(R.id.lblDescricao);

        Instituicao i = instituicaos.get(position);
        razao.setText(i.getRazao());
        descricao.setText(i.getDescricao());

        return linha;
    }
}
