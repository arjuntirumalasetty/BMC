package businessojects;

/**
 * Created by Arjun on 3/31/2017.
 */

public class Timings {
    private String from;
    private String to;

    public Timings(String from,String to){
        this.from = from;
        this.to = to;
    }
    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
}
