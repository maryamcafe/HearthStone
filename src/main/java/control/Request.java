package control;

public class Request{

    private String name;
    private boolean isInternal;
    private String[] information;

    public Request(String name, boolean isInternal, String[] information){
        this.name = name;
        this.isInternal = isInternal;
        this.information = information;
    }

    public String getName() {
        return name;
    }

    //internal Requests are which can be handled in each state
    //external (=not internal) Requests should be handled in Admin
    public boolean isInternal() {
        return isInternal;
    }

    public String[] getInformation() {
        return information;
    }

}
