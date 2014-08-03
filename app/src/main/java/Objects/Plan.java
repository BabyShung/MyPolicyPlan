package Objects;

import com.parse.Parse;
import com.parse.ParseObject;

public class Plan {
    String policyName;
    String policyNumber;
    Integer policyID;
    String phone;
    String website;

    public Plan(){}

    public Plan(ParseObject po) {
        this.policyName = po.getString("policyName");
        policyNumber = po.getString("policyNum");
        this.policyID = po.getInt("policyID");
        phone = po.getString("phoneNum");
        this.website = po.getString("website");
    }

    @Override
    public String toString(){
        return "the policy name is" + policyName + "\n" +
                "the policy number is" + policyNumber + "\n" +
                "the policy ID is" + policyID.toString()+ "\n" +
                "the phone number is" + phone + "\n" +
                "the plan website is" + website;
    }
}
