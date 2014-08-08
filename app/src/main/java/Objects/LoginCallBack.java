package Objects;

import com.parse.ParseException;

/**
 * Created by maggieyang829 on 8/7/2014.
 */
public abstract class LoginCallBack {
    public abstract void finish(Boolean success, ParseException error);
}

