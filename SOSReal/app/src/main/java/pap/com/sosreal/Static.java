package pap.com.sosreal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Static {

    public static boolean validar(String email) {
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

    public static boolean validarSenhasDiferentes(String senha1, String senha2){
        if(senha1.equals(senha2)){
            return true;
        }
        return false;
    }

}
