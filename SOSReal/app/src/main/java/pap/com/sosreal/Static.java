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

}
