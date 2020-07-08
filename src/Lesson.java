import java.sql.Date;
import java.time.DayOfWeek;

public class Lesson {
    private int lesID;
    private String dayOfWeek;
    private int lStart;
    private int lDuration;
    private int teaID;
    private int corID;
    private int roomNo;

    public Lesson() {

    }

    public Lesson(int lesID, String dayOfWeek, int lDuration, int teaID, int corID, int roomNo, int lStart) {
        this.lesID = lesID;
        this.dayOfWeek = dayOfWeek;
        this.lDuration = lDuration;
        this.teaID = teaID;
        this.corID = corID;
        this.roomNo = roomNo;
        this.lStart = lStart;
    }

    public void setLesID(int lesID) {
        this.lesID = lesID;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public void setlDuration(int lDuration) {
        this.lDuration = lDuration;
    }

    public void setTeaID(int teaID) {
        this.teaID = teaID;
    }

    public void setCorID(int corID) {
        this.corID = corID;
    }

    public void setRoomNo(int roomNo) {
        this.roomNo = roomNo;
    }

    public int getLesID() {
        return lesID;
    }
    public int getLStart() {
        return lStart;
    }

    public void setLStart(int lStart) {
        this.lStart = lStart;
    }
    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public int getlDuration() {
        return lDuration;
    }

    public int getTeaID() {
        return teaID;
    }

    public int getCorID() {
        return corID;
    }

    public int getRoomNo() {
        return roomNo;
    }
}
