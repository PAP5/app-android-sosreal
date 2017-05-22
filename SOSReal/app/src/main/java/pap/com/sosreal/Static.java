package pap.com.sosreal;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Static {

    public static boolean validarEmail(String email) {
        boolean isEmailIdValid = false;
        if (email != null && email.length() > 0) {
            String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
            Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(email);
            if (matcher.matches()) {
                isEmailIdValid = true;
            }
        }
        return isEmailIdValid;
    }

    public static boolean validarSenhasDiferentes(String senha1, String senha2) {
        if (senha1.equals(senha2)) {
            return true;
        }
        return false;
    }

    public static boolean validarData(String data) {
        String[] separada = data.split("/");

        if (Integer.valueOf(separada[0]) > 31) {
            return false;
        } else if (Integer.valueOf(separada[1]) > 12) {
            return false;
        } else if (separada[2].length() < 4 || separada[2].length() > 4) {
            return false;
        }

        return true;
    }

    static public boolean validarCPF (String strCpf )
    {
        int     d1, d2;
        int     digito1, digito2, resto;
        int     digitoCPF;
        String  nDigResult;

        d1 = d2 = 0;
        digito1 = digito2 = resto = 0;

        for (int nCount = 1; nCount < strCpf.length() -1; nCount++)
        {
            digitoCPF = Integer.valueOf (strCpf.substring(nCount -1, nCount)).intValue();

            //multiplique a ultima casa por 2 a seguinte por 3 a seguinte por 4 e assim por diante.
            d1 = d1 + ( 11 - nCount ) * digitoCPF;

            //para o segundo digito repita o procedimento incluindo o primeiro digito calculado no passo anterior.
            d2 = d2 + ( 12 - nCount ) * digitoCPF;
        };

        //Primeiro resto da divisão por 11.
        resto = (d1 % 11);

        //Se o resultado for 0 ou 1 o digito é 0 caso contrário o digito é 11 menos o resultado anterior.
        if (resto < 2)
            digito1 = 0;
        else
            digito1 = 11 - resto;

        d2 += 2 * digito1;

        //Segundo resto da divisão por 11.
        resto = (d2 % 11);

        //Se o resultado for 0 ou 1 o digito é 0 caso contrário o digito é 11 menos o resultado anterior.
        if (resto < 2)
            digito2 = 0;
        else
            digito2 = 11 - resto;

        //Digito verificador do CPF que está sendo validado.
        String nDigVerific = strCpf.substring (strCpf.length()-2, strCpf.length());

        //Concatenando o primeiro resto com o segundo.
        nDigResult = String.valueOf(digito1) + String.valueOf(digito2);

        //comparar o digito verificador do cpf com o primeiro resto + o segundo resto.
        return nDigVerific.equals(nDigResult);
    }


        static public boolean validarCNPJ( String str_cnpj )
        {
            int soma = 0, aux, dig;
            String cnpj_calc = str_cnpj.substring(0,12);

            if ( str_cnpj.length() != 14 )
                return false;

            char[] chr_cnpj = str_cnpj.toCharArray();

            for( int i = 0; i < 4; i++ )
                if ( chr_cnpj[i]-48 >=0 && chr_cnpj[i]-48 <=9 )
                    soma += (chr_cnpj[i] - 48 ) * (6 - (i + 1)) ;
            for( int i = 0; i < 8; i++ )
                if ( chr_cnpj[i+4]-48 >=0 && chr_cnpj[i+4]-48 <=9 )
                    soma += (chr_cnpj[i+4] - 48 ) * (10 - (i + 1)) ;
            dig = 11 - (soma % 11);

            cnpj_calc += ( dig == 10 || dig == 11 ) ?
                    "0" : Integer.toString(dig);

            soma = 0;
            for ( int i = 0; i < 5; i++ )
                if ( chr_cnpj[i]-48 >=0 && chr_cnpj[i]-48 <=9 )
                    soma += (chr_cnpj[i] - 48 ) * (7 - (i + 1)) ;
            for ( int i = 0; i < 8; i++ )
                if ( chr_cnpj[i+5]-48 >=0 && chr_cnpj[i+5]-48 <=9 )
                    soma += (chr_cnpj[i+5] - 48 ) * (10 - (i + 1)) ;
            dig = 11 - (soma % 11);
            cnpj_calc += ( dig == 10 || dig == 11 ) ?
                    "0" : Integer.toString(dig);

            return str_cnpj.equals(cnpj_calc);
        }


}
