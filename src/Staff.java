public class Staff {
    private int staID;
    private String staName;
    private String staAddress;
    private String staNI;
    private Double staSalary;
    private String UserName;
    private String Password;
    private String Position;

    public Staff() {
    }

    public Staff(int staID, String staName, String staAddress, String staNI,
                 Double staSalary, String userName, String password, String position) {
        this.staID = staID;
        this.staName = staName;
        this.staAddress = staAddress;
        this.staNI = staNI;
        this.staSalary = staSalary;
        UserName = userName;
        Password = password;
        Position = position;
    }

    public void setStaID(int staID) {
        this.staID = staID;
    }

    public void setStaName(String staName) {
        this.staName = staName;
    }

    public void setStaAddress(String staAddress) {
        this.staAddress = staAddress;
    }

    public void setStaNI(String staNI) {
        this.staNI = staNI;
    }

    public void setStaSalary(Double staSalary) {
        this.staSalary = staSalary;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public void setPosition(String position) {
        Position = position;
    }

    public int getStaID() {
        return staID;
    }

    public String getStaName() {
        return staName;
    }

    public String getStaAddress() {
        return staAddress;
    }

    public String getStaNI() {
        return staNI;
    }

    public Double getStaSalary() {
        return staSalary;
    }

    public String getUserName() {
        return UserName;
    }

    public String getPassword() {
        return Password;
    }

    public String getPosition() {
        return Position;
    }
}
