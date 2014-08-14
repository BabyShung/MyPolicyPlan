package Objects;

import com.parse.ParseException;
import com.parse.ParseObject;

import java.util.List;

public abstract class FetchPlanCallBack {
    public abstract void finish(Boolean success, List<ParseObject> lst, ParseException ex);
}
