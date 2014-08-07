package validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
    private Pattern pattern;
    private Matcher matcher;

    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    public Validator() {
        pattern = Pattern.compile(EMAIL_PATTERN);
    }

    public boolean validate(final String email) {

        if (email == null) {return false;}
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public boolean validateEmailRequire(String email) {
        if (email == "") return false;
        return true;
    }

    public boolean validatePswRequire(String password) {
        if (password == "") return false;
        return true;
    }

    public boolean verifyAll(String email, String psw){
        if (!validateEmailRequire(email)){
            System.out.println("Please input your email");
            return false;
        }
        if (!validatePswRequire(psw)){
            System.out.println("Please input your password");
            return false;
        }

       if (!validate(email)){
           System.out.println("Please input a valid email address");
           return false;
       }
        System.out.println("verification success");
        return true;
    }
}
