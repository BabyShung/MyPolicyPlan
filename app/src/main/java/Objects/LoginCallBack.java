package Objects;

import com.parse.ParseException;

public abstract class LoginCallBack {
    public abstract void finish(Boolean success, ParseException error);
}

