import java.util.Date;

public class Register {

    private int regID;
    private String regDate;
    private String attendance;
    private int enuID;
    private int lesID;

    public Register() {
    }

    public Register(int regID, String regDate, String attendance, int enuID, int lesID) {
        this.regID = regID;
        this.regDate = regDate;
        this.attendance = attendance;
        this.enuID = enuID;
        this.lesID = lesID;
    }

    public void setRegID(int regID) {
        this.regID = regID;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    public void setAttendance(String attendance) {
        this.attendance = attendance;
    }

    public void setEnuID(int enuID) {
        this.enuID = enuID;
    }

    public void setLesID(int lesID) {
        this.lesID = lesID;
    }

    public int getRegID() {
        return regID;
    }

    public String getRegDate() {
        return regDate;
    }

    public String getAttendance() {
        return attendance;
    }

    public int getEnuID() {
        return enuID;
    }

    public int getLesID() {
        return lesID;
    }
}
