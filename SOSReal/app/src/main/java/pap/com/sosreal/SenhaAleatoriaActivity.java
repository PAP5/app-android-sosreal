package pap.com.sosreal;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SenhaAleatoriaActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.senha_aleatoria);
    }

    public void trocarSenha(View view){
        EditText email = (EditText) findViewById(R.id.txtEmail);
        TextView lblErro = (TextView) findViewById(R.id.lblErro);

        String emailTxt = email.getText().toString();
        if(!Static.validar(emailTxt)){
           lblErro.setText(R.string.erroEmail);
        }
        else{
            finish();
        }

    }

    public String gerarSenhaAleatoria() {
        String[] carct = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f", "g", "h", "i",
                "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "A", "B", "C", "D", "E",
                "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

        String senha = "";
        for (int x = 0; x < 8; x++) {
            int j = (int) (Math.random() * carct.length);
            senha += carct[j];
        }

        return senha;
    }
}
