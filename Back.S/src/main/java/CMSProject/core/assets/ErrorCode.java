package CMSProject.core.assets;

public class ErrorCode  {
    protected int state = 0;
    protected String message = "";
    public ErrorCode (){}
    public ErrorCode (int state, String message){ this.state = state ; this.message = message;}
    public int getState () { return state; }
    public String getMessage () { return message; }
}
