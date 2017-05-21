package pap.com.sosreal;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import java.util.Calendar;

public class CriarPerfilpj1Activity extends AppCompatActivity {

    private DateFormat dhf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    private DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    private DateFormat hf = new SimpleDateFormat("HH:mm");
    Bundle dados = new Bundle();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.criar_perfilpf1);

        Intent i = getIntent();
        dados = i.getExtras();


        Spinner spinner = (Spinner) findViewById(R.id.spinner_sexo);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.sexo_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        Calendar calendario = Calendar.getInstance();
        adicionarDatePicker((EditText) findViewById(R.id.txtDataNasc), calendario);


    }

    private void adicionarDatePicker(final EditText edit, final Calendar date) {
        edit.setText(df.format(date.getTime()));

        final DatePickerDialog.OnDateSetListener dpd = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                date.set(Calendar.YEAR, year);
                date.set(Calendar.MONTH, monthOfYear);
                date.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                edit.setText(df.format(date.getTime()));
            }
        };

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(CriarPerfilpj1Activity.this, dpd, date.get(Calendar.YEAR),
                        date.get(Calendar.MONTH), date.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    public void proximo(View view) {

        TextView lblErro = (TextView) findViewById(R.id.lblErro);


        String nome = ((EditText) findViewById(R.id.txtNome)).getText().toString();
        nome += " " + ((EditText) findViewById(R.id.txtSobrenome)).getText().toString();
        String datanasc = ((EditText) findViewById(R.id.txtDataNasc)).getText().toString();
        String sexo = ((Spinner) findViewById(R.id.spinner_sexo)).getSelectedItem().toString();

        if (((EditText) findViewById(R.id.txtNome)).getText().toString().isEmpty() ||
                ((EditText) findViewById(R.id.txtSobrenome)).getText().toString().isEmpty() ||
                datanasc.isEmpty() ||
                sexo.equals("Selecione")) {
            lblErro.setText(R.string.erroVazio);
        }
        if (!Static.validarData(datanasc)) {
            lblErro.setText(R.string.erroData);
        } else {
            dados.putString("nome", nome);
            dados.putString("dataNasc", datanasc);
            if (sexo.equals("Masculino")) {
                dados.putString("sexo", "m");
            }
            if (sexo.equals("Feminino")) {
                dados.putString("sexo", "f");
            }

            Intent i = new Intent(this, CriarPerfilpj2Activity.class);
            i.putExtras(dados);
            startActivity(i);
        }


    }

}

