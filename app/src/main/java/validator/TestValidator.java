package validator;

public class TestValidator {

    private String arg;
    private static Validator validator;
    private Boolean res;

    public TestValidator(){
        validator = new Validator();
    }

    public void test(String email){
        Boolean res = validator.validate(email);
        String isValid = (res)? "valid":"invalid";
        System.out.println(email + " is " + isValid);
    }
}
