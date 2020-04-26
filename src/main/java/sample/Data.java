package sample;

public class Data {
    private String date;
    private String time;
    private String tag;
    private String mess;

    public Data() {
    }

    public Data(String date, String time, String tag, String mess) {
        this.date = date;
        this.time = time;
        this.tag = tag;
        this.mess = mess;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getMess() {
        return mess;
    }

    public void setMess(String mess) {
        this.mess = mess;
    }
}
