public class Teacher extends Staff {
    private int teaID;
    private String teaQual;

    public Teacher() {
    }

    public Teacher(int staID, String staName, String staAddress,
                   String staNI, Double staSalary, String userName, String password,
                   String position, int teaID, String teaQual) {
        super(staID, staName, staAddress, staNI, staSalary, userName, password, position);
        this.teaID = teaID;
        this.teaQual = teaQual;
    }

    public void setTeaID(int teaID) {
        this.teaID = teaID;
    }

    public void setTeaQual(String teaQual) {
        this.teaQual = teaQual;
    }

    public int getTeaID() {
        return teaID;
    }

    public String getTeaQual() {
        return teaQual;
    }
}
