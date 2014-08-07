package validator;

public class TestValidator {

    private String arg;
    private static Validator validator;
    private Boolean res;

    public TestValidator(){
        validator = new Validator();
    }

    public void test(String email, String psw){
        validator.verifyAll(email, psw);
    }
}
