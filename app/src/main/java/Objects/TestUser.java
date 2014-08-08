package Objects;

import com.parse.ParseException;
import com.parse.ParseUser;

/**
 * Created by maggieyang829 on 8/1/2014.
 */
public class TestUser {
    User user;
    public TestUser(){
    }

    public void testRegister(String name, String psw, String email ){
        User.register(name, psw, email);
    }

    public void testLogIn(String email, String psw) throws ParseException {
        User.logIn(email,psw,new LoginCallBack() {
            @Override
            public void finish(Boolean success, ParseException error) {

            }
        });
    }
    public void testChangeUserName(String newName){}
    public void testResetPassword(){}
    public void testToString(){}
    public void testLogOut(){}
}
