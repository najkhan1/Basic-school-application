public class Student {
    private int stuID;
    private String stuName;
    private String stuAddress;
    private String DOB;

    public Student() {
    }

    public Student(int stuID, String stuName, String stuAddress, String DOB) {
        this.stuID = stuID;
        this.stuName = stuName;
        this.stuAddress = stuAddress;
        this.DOB = DOB;
    }

    public void setStuID(int stuID) {
        this.stuID = stuID;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public void setStuAddress(String stuAddress) {
        this.stuAddress = stuAddress;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public int getStuID() {
        return stuID;
    }

    public String getStuName() {
        return stuName;
    }

    public String getStuAddress() {
        return stuAddress;
    }

    public String getDOB() {
        return DOB;
    }
}
