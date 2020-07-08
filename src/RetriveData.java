import javax.swing.*;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;

public class RetriveData {
    static Object[] stuCols = {"Student ID","Student Name","Address","Date of birth"};


    static Object[] staffCols = {"Staff ID", "Staff name", "Staff Address", "Staff NI","Staff Salary","User name", "Position"};



    static Object[] enrolCols = {"EnuID","EnuDate","Grade", "StuID", "CorID"};


    static Object[] lesCols = {"Lesson ID", "Day of Week","Start","Duration","Teacher ID","Course ID","RoomNo"};

    static Object[] checkedCorCapCols = {"Course ID","Course Name","Remaining Capacity"};

    static  Object[] regCols = {"Register ID","Date","Attendance","Enrolment ID","Lesson ID"};


    static Object[] corCols = {"Course ID", "Course Name", "Course Status"};
    private ConnectToDB conn = new ConnectToDB();
    public RetriveData(){

    }
    public int loginCheck(String userName, String pass){
        String colReq = " StaID , StaPassword, Position ";
        String colN = " UserName ";
        Object[][] logAr = searchByName(userName," Staff ", colN, colReq);
        //System.out.println("id of staff " + logAr[0][0]);
        if(logAr.length == 0){
            JOptionPane.showMessageDialog(null,
                    "User Name: "+ userName + " , Does not exist in database.");

        }else if (!logAr[0][1].toString().equals(pass)){
            JOptionPane.showMessageDialog(null,
                    "Password does not match user name");

        }else if(logAr[0][1].toString().equals(pass)){
            if(logAr[0][2].toString().equals("Manager")){
                return -1;
            }else if (logAr[0][2].toString().equals("Teacher")){
                int teaID = Integer.parseInt(logAr[0][0].toString());
                Object[][] tea = searchByID(teaID,"TeaID","Teachers", "StaID");
               return Integer.parseInt(tea[0][0].toString());
            }else if (logAr[0][2].toString().equals("Staff")){
                return -2;
            }
        }
        return 0;
    }

    public  Object [][] searchByName(String name, String table, String colN, String colReq){
        Object[][] temp = new Object[10][4];
        if(!name.equals(null)){
            conn.connect();



            try{Statement sta = conn.con.createStatement();
                ResultSet rs = sta.executeQuery("select "+colReq+" from "+table+" where "+colN+" = '" +
                        name+"';");
                temp = resultToArray(rs);

            }catch(SQLException e){
                e.printStackTrace();
                System.out.println("no connnection");
            }
        }
//        System.out.println("name == " + temp[0][2]);
        return temp;
    }
    public Object[][] getCurEnrolments(String query){
        conn.connect();

        try{Statement sta = conn.con.createStatement();
            //curCID,"Lesson ID", "Lesson start","Duration"
            ResultSet rs = sta.executeQuery(query);
             return resultToArray(rs);

        }catch(SQLException e){
            e.printStackTrace();
            System.out.println("no connnection");
        }
        return null;
    }
    public  Object [][] validateEnu(int ID,int corID){
        System.out.println("inside Validate enu");
        Object[][] temp = {};
        Object[][] temp2 = {};

        Object[][] returnAr = new Object[20][8];
        int countR = 0;
        if(ID != 0){
            conn.connect();
            try{Statement sta = conn.con.createStatement();
                    Statement sta2 = conn.con.createStatement();
                ResultSet rs = sta.executeQuery("SELECT l.LesID,l.LesDay,l.LesStart,l.CorID,l.LesDuration from lessons l where l.CorID IN(SELECT c.CorID " +
                        "from enrolments c WHERE c.StuID =  "+ID+");");
                //SELECT l.LesID,l.LesDay,l.LesStart,l.CorID,l.LesDuration from lessons l where l.CorID IN(SELECT c.CorID from enrolments c WHERE c.StuID = 78)
              ResultSet rs2 = sta2.executeQuery("SELECT l.LesID,l.LesStart,l.LesDuration,l.LesDay,l.CorID from lessons l where l.CorID IN (SELECT c.CorID from courses " +
                      "c where c.CorID = "+corID+");");
              temp = resultToArray(rs);
              temp2 = resultToArray(rs2);
              long[] [] enrolC = new long[10][10];


              for(int i =0;i<temp.length;i++) {
                  for (int j = 0; j< temp2.length; j++) {
                      if (temp[i] != null && temp2[j] != null) {
                          if (temp[i][1].toString().equals(temp2[j][3].toString())) {
                              long lesEn = makedate(Integer.parseInt(temp[i][2].toString()));
                              long lesEnNot = makedate(Integer.parseInt(temp2[j][1].toString()));
                              long curLesDuration = Integer.parseInt(temp[i][4].toString()) * 60;
                              long nCorLesDuration = Integer.parseInt(temp2[j][2].toString())*60;
                              System.out.println("current :" + (lesEn - lesEnNot));

                            if((lesEn - lesEnNot) == 0){
                                System.out.println(temp[i][2].toString());
                                Object[] returnArSub ={temp[i][0],temp[i][3],temp[i][2],temp[i][1],temp2[j][0],temp2[j][4],temp2[j][1],temp[j][4]};
                                returnAr[countR] = returnArSub;
                                countR++;
                            }else if(lesEn<lesEnNot&&(-(lesEn - lesEnNot)<curLesDuration)&&(lesEn - lesEnNot)+curLesDuration>0){
                                System.out.println(temp[i][2].toString());
                                Object[] returnArSub ={temp[i][0],temp[i][3],temp[i][2],temp[i][1],temp2[j][0],temp2[j][4],temp2[j][1],(((lesEn - lesEnNot)+curLesDuration)/60)};
                                returnAr[countR] = returnArSub;
                                countR++;
                            }else if(lesEn>lesEnNot &&((lesEn - lesEnNot)<nCorLesDuration)&&((lesEn - lesEnNot)-nCorLesDuration)<0){
                                System.out.println(temp[i][2].toString());
                                Object[] returnArSub ={temp[i][0],temp[i][3],temp[i][2],temp[i][1],temp2[j][0],temp2[j][4],temp2[j][1],(-((lesEn - lesEnNot)-nCorLesDuration)/60)};
                                returnAr[countR] = returnArSub;
                                countR++;
                            }
                          }
                      }
                  }
              }
            }catch(SQLException e){
                e.printStackTrace();

                System.out.println("no connnection 2");
            }
        }
//        System.out.println("name == " + temp[0][2]);
        return returnAr;
    }
    public  int validateLes(int ID,Object[] col){
        System.out.println("inside Validate enu");
        Object[][] temp = {};
        Object[] temp2 = col;

        Object[][] returnAr = {};
        int countR = 0;
        if(ID != 0){
            conn.connect();
            try{Statement sta = conn.con.createStatement();
                Statement sta2 = conn.con.createStatement();
                ResultSet rs = sta.executeQuery("SELECT l.LesID,l.LesDay,l.LesStart,l.CorID,l.LesDuration from lessons l where l.TeaID = "+ID+" AND  l.LesDay = '"+temp2[3]+"'; ");
                temp = resultToArray(rs);
                for(int i =0;i<temp.length;i++){
                    if(temp[i][0] != null){
                        long lesOneS = makedate(Integer.parseInt(temp[i][2].toString()));
                        long lesTwoS = makedate(Integer.parseInt(temp2[1].toString()));
                        long lesOneDuration = Integer.parseInt(temp[i][4].toString()) * 60;
                        long lesTwoDuration = Integer.parseInt(temp2[2].toString())*60;

                        if((lesOneS>lesTwoS)&&(lesTwoS-lesOneS)+lesTwoDuration>0){

                            JOptionPane.showMessageDialog(null,
                                    "chosen time before choosen course ID:"+temp2[4]+" lesson over lap lessonID  " + temp[i][0] + " at time " +temp[i][2]+ " at day "+temp[i][1] +" by "+
                                            ((lesTwoS - lesOneS)+lesTwoDuration)/60 + "mins");
                            return 1;

                        }else if ((lesOneS<lesTwoS)&&(lesTwoS-lesOneS)-lesOneDuration<0&&(lesTwoS-lesOneS)<lesOneDuration){
                            System.out.println("inside 2nd eslse leson start:" +Integer.parseInt(temp[i][2].toString()));
                            JOptionPane.showMessageDialog(null,
                                    "choosen time of the lesson overlaps, lesson with lessonID  " + temp[i][0] + " at time " +temp[i][2]+ " on "+temp[i][1] +" by "+
                                            -((lesTwoS - lesOneS)-lesOneDuration)/60 + "mins");
                            return 1;
                        }

                    }
                }
            }catch(SQLException e){
                e.printStackTrace();
                System.out.println("no connnection 2");
            }
        }
        return 0;
    }
    public long makedate(int date){

        String dat = date+"";
        try{
        if(dat.length() == 3){
            char c = dat.charAt(0);
            String hour =  c+"";
            int hr = Integer.parseInt(hour);
            long hr1 = hr *60*60;
            System.out.println("hour "+hour);
            String tim = dat.charAt(1) + ""+dat.charAt(2);
            int ht = Integer.parseInt(tim);
            long hr2 = ht*60;
            long h1 = hr1 + hr2;

            return h1;
        }else if(dat.length() == 4){
            char c = dat.charAt(0);
            String hour =  c+""+dat.charAt(1);
            int hr = Integer.parseInt(hour);
            long hr1 = hr *60*60;
            System.out.println("hour "+hour);
            String tim = dat.charAt(2) + ""+dat.charAt(3);
            int ht = Integer.parseInt(tim);
            long hr2 = ht*60;
            long h1 = hr1 + hr2;

            return h1;
        }
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    public Object[][] searchByID(int ID, String colList,String table, String colName){
        Object[][] temp = new Object[10][4];
        if(ID != 0){
            conn.connect();

            try{Statement sta = conn.con.createStatement();
                ResultSet rs = sta.executeQuery("select "+colList+" from "+table+" where "+colName+" = " +
                        ID+";");
                temp = resultToArray(rs);

            }catch(SQLException e){
                e.printStackTrace();
                System.out.println("no connnection");
            }
        }
//        System.out.println("name == " + temp[0][1]);
        return temp;
    }

    public Object[][] resultToArray(ResultSet rs){
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
                for(int i = 1;i <= columns;i++){
                    String cName = meta.getColumnName(i);
                    String val = rs.getString(cName);
                    rowData[rs.getRow()-1][i-1] = val;
                }
            }
        }catch(SQLException e){
            System.out.println("no connnection result to array");
        }
        return rowData;
    }

    public void addRegForLes(int lesID){
        //SELECT e.EnuID FROM enrolments e WHERE e.CorID IN (SELECT c.CorID FROM courses c WHERE c.CorID IN (SELECT l.CorID FROM lessons l where l.LesID = 10))
        conn.connect();
        Object[][] temp = {};
        try{Statement sta = conn.con.createStatement();
            Statement sta2 = conn.con.createStatement();
            ResultSet rs = sta.executeQuery("SELECT e.EnuID FROM enrolments e WHERE e.CorID IN (SELECT c.CorID FROM courses c " +
                    "WHERE c.CorID IN (SELECT l.CorID FROM lessons l where l.LesID = "+lesID+"))");
            temp = resultToArray(rs);
            for(int i = 0;i<temp.length;i++){
                System.out.println("enuid in addregforles = " + temp[i][0]);
                sta2.executeUpdate("INSERT INTO Register(RegID,EnuID,LesID) values(null,"+temp[i][0]+","+lesID+")");
            }

        }catch(SQLException e){
            e.printStackTrace();
            System.out.println("no connnection");
        }
    }

    public void addRegForEn(int enuID){
        //SELECT l.LesID FROM lessons l WHERE l.CorID IN (SELECT c.CorID FROM courses c WHERE c.CorID IN (SELECT e.CorID FROM enrolments e WHERE e.EnuID = 77) )
        conn.connect();
        Object[][] temp = {};
        SaveToDB sv = new SaveToDB();
        try{Statement sta = conn.con.createStatement();
            ResultSet rs = sta.executeQuery("SELECT l.LesID FROM Lessons l WHERE l.CorID IN (SELECT c.CorID FROM Courses c" +
                    " WHERE c.CorID IN (SELECT e.CorID FROM enrolments e WHERE e.EnuID = "+enuID+"))");
            temp = resultToArray(rs);
            System.out.println(" temp lenth in rgeforles " + temp.length);
            for(int i = 0;i<temp.length;i++){
                System.out.println("lesseon id in addRegfroEnu =" + temp[i][0]);
                String query = "INSERT INTO Register values(null,null,null,"+enuID+","+temp[i][0]+")";
                sv.EditDetails(query);
            }
            conn.disconnect();
        }catch(SQLException e){
            e.printStackTrace();
            System.out.println("no connnection");
        }
    }
}
