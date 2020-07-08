public class Enrolments {

    private int enuID;
    private String enuDate;
    private String grade;
    private int stuID;
    private int corID;

    public Enrolments() {
    }

    public Enrolments(int enuID, String enuDate, String grade, int stuID, int corID) {
        this.enuID = enuID;
        this.enuDate = enuDate;
        this.grade = grade;
        this.stuID = stuID;
        this.corID = corID;
    }
    public Enrolments(int enuID, String grade, int stuID, int corID) {
        this.enuID = enuID;

        this.grade = grade;
        this.stuID = stuID;
        this.corID = corID;
    }

    public void setEnuID(int enuID) {
        this.enuID = enuID;
    }

    public void setEnuDate(String enuDate) {
        this.enuDate = enuDate;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public void setStuID(int stuID) {
        this.stuID = stuID;
    }

    public void setCorID(int corID) {
        this.corID = corID;
    }

    public int getEnuID() {
        return enuID;
    }

    public String getEnuDate() {
        return enuDate;
    }

    public String getGrade() {
        return grade;
    }

    public int getStuID() {
        return stuID;
    }

    public int getCorID() {
        return corID;
    }
}
