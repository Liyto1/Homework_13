package org.example.FirstTask;

public class Company {
    private static String companyName;
    private static String catchPhrase;
    private static String bs;

    public Company(String companyName, String catchPhrase, String bs){
        this.companyName = companyName;
        this.catchPhrase = catchPhrase;
        this.bs = bs;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        Company.companyName = companyName;
    }

    public String getCatchPhrase() {
        return catchPhrase;
    }

    public void setCatchPhrase(String catchPhrase) {
        Company.catchPhrase = catchPhrase;
    }

    public String getBs() {
        return bs;
    }

    public static void setBs(String bs) {
        Company.bs = bs;
    }
}
