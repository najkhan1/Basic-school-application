import javax.swing.*;
import java.sql.*;
public class SaveToDB {


    ConnectToDB conn = new ConnectToDB();


    public SaveToDB() {
    }

    public void setStaff(int id, String name, String address, String ni, Double salary, String userN, String password, String postion) {
        Staff staff = new Staff(id, name, address, ni, salary, userN, password, postion);

        conn.connect();
        System.out.println("set staff to db");

        try {
            Statement stmt = conn.con.createStatement();
            stmt.executeUpdate("INSERT INTO Staff" +
                    " values(null" + ",'" + staff.getStaName() + "','" + staff.getStaAddress() + "','" +
                    staff.getStaNI() + "'," +
                    staff.getStaSalary() + ",'" + staff.getUserName() + "','" + staff.getPassword() + "','" +
                    staff.getPosition() + "');");

            System.out.println("staff data saved");
            conn.disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("failed to add staff");
        }


    }
    public void EditDetails(String query){
        conn.connect();
        try{Statement stmt = conn.con.createStatement();
            stmt.executeUpdate(query);
            System.out.println("Edit data saved");
            conn.disconnect();
        }catch(SQLException e){
            System.out.println("failed to EDIT");
        }
    }

    public void setStaff(int id, String name, String address, String ni, Double salary, String userN, String password, String postion, String qual) {
        int teaID = getIDA("Teachers", "TeaID");
        Teacher staff = new Teacher(id, name, address, ni, salary, userN, password, postion, teaID, qual);
        setStaff(id, name, address, ni, salary, userN, password, postion);

        conn.connect();
        System.out.println("set staff to db");

        try {
            Statement stmt = conn.con.createStatement();
            stmt.executeUpdate("INSERT INTO Teachers" +
                    " values(null,'" + staff.getStaID() + "','" + staff.getTeaQual() + "');");

            System.out.println("staff data saved");
            conn.disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("failed to add staff");
        }
    }
    public void setLesson(int ID,String DOW, int duration, int tID,int cID, int room,int start){
        Lesson lesson = new Lesson(ID,DOW,duration,tID,cID,room,start);
        conn.connect();
        System.out.println("set staff to db");

        try {
            Statement stmt = conn.con.createStatement();
            stmt.executeUpdate("INSERT INTO Lessons" +
                    " values(null,'" + lesson.getDayOfWeek() + "'," + lesson.getLStart() +
                    ","+lesson.getlDuration()+","+lesson.getTeaID()+","+lesson.getCorID()+","+
                    lesson.getRoomNo()+");");

            System.out.println("Lesson data saved");
            conn.disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Lesson to add staff");
            JOptionPane.showMessageDialog(null,
                    "Lesson failed to add lesson");

        }

    }





    public void setStudent(int stuID, String stuName, String stuAddress, String DOB){
        Student student = new Student(stuID,stuName,stuAddress,DOB);
        System.out.println("student name = "+ student.getDOB());
        conn.connect();
        System.out.println("set stu to db");
        try{Statement stmt = conn.con.createStatement();
            stmt.executeUpdate("INSERT INTO Students" +
                    " values(null"+",'"+student.getStuAddress() +"',"+
                    "Date '"+student.getDOB()+"'" +",'"+ student.getStuName() +"');");
            System.out.println("student data saved");
            conn.disconnect();
        }catch(SQLException e){
            System.out.println("failed to add student");
        }
        //conn.disconnect();
    }

    private Object[][] resultToArray(ResultSet rs){
        int length = 0;
        int count = 0;
        Object[][] rowData = {};

        try{
            ResultSetMetaData meta = rs.getMetaData();
            int columns = meta.getColumnCount();
            rs.last();
            int rowNum = rs.getRow();
            rowData = new Object [rowNum][columns];
            rs.beforeFirst();

            while(rs.next()){


                for(int i = 1;i<=columns;i++){
                    String cName = meta.getColumnName(i);
                    String val = rs.getString(cName);
                    rowData[rs.getRow()-1][i-1] = val;


                }


            }
        }catch(SQLException e){
            System.out.println("no connnection");
        }

        return rowData;

    }

    public void setEnrolments(int ID,String date1,String grade,int stuID,int corID){
        Enrolments enrolments = new Enrolments(ID,date1,grade,stuID,corID);

        conn.connect();
        try{Statement stmt = conn.con.createStatement();
            String query = "INSERT INTO Enrolments" +
                    " values(null"+"," +
                    "Date '"+enrolments.getEnuDate()+"'" +",'"+ enrolments.getGrade() +"'," +
                    enrolments.getStuID() +","+enrolments.getCorID()+");";
            stmt.executeUpdate(query);

            conn.disconnect();
        }catch(SQLException e){
            e.printStackTrace();
            System.out.println("failed to add enrolments");
        }


    }
    public void setCourse(int ID, String name,String status){
        Course course = new Course(ID,name,status);
        conn.connect();
        System.out.println("set cor to db");
        try{Statement stmt = conn.con.createStatement();
            stmt.executeUpdate("INSERT INTO Courses" +
                    " values(null"+",'"+course.getCorName() +"',"+
                    "'"+course.getCorStatus()+"');");
            System.out.println("Course saved");
            conn.disconnect();
        }catch(SQLException e){
            System.out.println("failed to add course");
        }


    }
    public int getIDA(String table,String id){
        conn.connect();
        System.out.println("get id");
        int rowNum = 0;
        try{Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT MAX("+id+ ") FROM "+table);
            Object[][] temp = new RetriveData().resultToArray(rs);
                try {
                    if (!temp[temp.length - 1][0].toString().equals(null)) {
                        rowNum = Integer.parseInt(temp[temp.length - 1][0].toString());
                        conn.disconnect();
                        System.out.println("rownum " + temp[temp.length - 1][0].toString() );
                        return rowNum;
                    }
                }catch(Exception ae){
                    System.out.println("no data in the table");
                }



            //System.out.println("student data saved");

        }catch(SQLException e){
            System.out.println("no ID");
        }
        return 0;
    }

}
