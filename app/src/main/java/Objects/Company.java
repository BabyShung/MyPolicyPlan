package Objects;

import com.parse.ParseObject;

import java.io.File;

public class Company {
    String companyID;
    String companyName;
    com.parse.ParseFile logo;

    public Company(){}

    public Company(ParseObject po) {
        companyID = po.getString("companyId");
        companyName = po.getString("companyName");
        logo = po.getParseFile("logo");
    }

    @Override
    public String toString(){
        return "the company ID is" + companyID + "\n" +
                "the company name is" + companyName;
    }
}
