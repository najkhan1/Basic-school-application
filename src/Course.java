public class Course {

    private int corID;
    private String corName;
    private String corStatus;

    public Course() {
    }

    public Course(int corID, String corName, String corStatus) {
        this.corID = corID;
        this.corName = corName;
        this.corStatus = corStatus;
    }

    public void setCorID(int corID) {
        this.corID = corID;
    }

    public void setCorName(String corName) {
        this.corName = corName;
    }

    public void setCorStatus(String corStatus) {
        this.corStatus = corStatus;
    }

    public int getCorID() {
        return corID;
    }

    public String getCorName() {
        return corName;
    }

    public String getCorStatus() {
        return corStatus;
    }
}
