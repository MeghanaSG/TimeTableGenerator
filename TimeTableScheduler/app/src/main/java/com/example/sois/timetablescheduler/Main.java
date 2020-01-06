package com.example.sois.timetablescheduler;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.ViewPropertyAnimatorCompatSet;
import android.view.View;

public class Main extends AppCompatActivity {
    //SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Public_status.db=openOrCreateDatabase("TIMETABLE", Context.MODE_PRIVATE, null);
        try {
            Set_Table();
            Public_status.db.execSQL("Update TblSubject set Counter=0");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onProceed(View view)
    {
       Intent i=new Intent(this,Batch_Add.class);
        startActivity(i);
    }
    private void Set_Table() {
            String strQuery = "";
            int intID;

            try
            {
           /* Public_status.db.execSQL("DROP TABLE IF EXISTS TblTime;");
                Public_status.db.execSQL("DROP TABLE IF EXISTS TblWeek;");
                Public_status.db.execSQL("DROP TABLE IF EXISTS TblClass;");
                Public_status.db.execSQL("DROP TABLE IF EXISTS TblTeacher;");
                Public_status.db.execSQL("DROP TABLE IF EXISTS TblSubject;");*/
               Public_status.db.execSQL("DROP TABLE IF EXISTS TblGenerate;");

                Public_status.db.execSQL("CREATE TABLE IF NOT EXISTS TblTime(Time_id INTEGER PRIMARY KEY AUTOINCREMENT, StartTime VARCHAR, EndTime VARCHAR, LunchBreak bit);");
                //Public_status.db.execSQL("CREATE TABLE IF NOT EXISTS TblTime(Time_Id INTEGER PRIMARY KEY AUTOINCREMENT,StartTime VARCHAR ,EndTime VARCHAR,LunchBreak bit);");

                Public_status.db.execSQL("CREATE TABLE IF NOT EXISTS TblWeek(Week_id INTEGER PRIMARY KEY AUTOINCREMENT, Days VARCHAR NOT NULL);");
                // Public_status.db.execSQL("CREATE TABLE IF NOT EXISTS TblWeek(Week_id INTEGER PRIMARY KEY AUTOINCREMENT, Days VARCHAR);");

                Public_status.db.execSQL("CREATE TABLE IF NOT EXISTS TblClass(Class_id INTEGER PRIMARY KEY AUTOINCREMENT, ClassName VARCHAR NOT NULL);");
                //Public_status.db.execSQL("CREATE TABLE IF NOT EXISTS TblClass(Class_id INTEGER PRIMARY KEY AUTOINCREMENT ,ClassName VARCHAR);");

                Public_status.db.execSQL("CREATE TABLE IF NOT EXISTS TblSubject(Subject_id INTEGER PRIMARY KEY AUTOINCREMENT, Name VARCHAR ,Class_id int, Counter int);");
              //  Public_status.db.execSQL("CREATE TABLE IF NOT EXISTS TblSubject(Subject_id INTEGER PRIMARY KEY AUTOINCREMENT, Name VARCHAR,Class_id int,Counter int);");
                Public_status.db.execSQL("CREATE TABLE IF NOT EXISTS TblTeacher(Teacher_id  int, Name VARCHAR NOT NULL, Subject_id int NOT NULL);");
              //  Public_status.db.execSQL("CREATE TABLE IF NOT EXISTS TblTeacher(Teacher_id  INTEGER PRIMARY KEY AUTOINCREMENT, Name VARCHAR NOT NULL, Subject_id int NOT NULL);");
                //Public_status.db.execSQL("CREATE TABLE IF NOT EXISTS TblTeacher(Teacher_id INTEGER PRIMARY KEY AUTOINCREMENT,Name VARCHAR,Subject_id int);");
                Public_status.db.execSQL("CREATE TABLE IF NOT EXISTS TblGenerate(Generate_id INTEGER PRIMARY KEY AUTOINCREMENT, Time_id int, Week_id int, Teacher_id int, Subject_id int, Class_id int, isUsed Bit );");
                /*strQuery="insert into TblSubject(Name, Class_id, Counter) " +
                        "Values('J',1,0)";
                Public_status.db.execSQL(strQuery);
                strQuery="insert into TblSubject(Name, Class_id, Counter) " +
                        "Values('A',1,0)";
                Public_status.db.execSQL(strQuery);*/
                //Public_status.db.execSQL("CREATE TABLE IF NOT EXISTS TblGenerate(Generate_id INTEGER PRIMARY KEY AUTOINCREMENT, Time_id int NOT NULL, Week_id int NOT NULL, Teacher_id int, Subject_id int, Class_id int, isUsed Bit );");
/*
                Cursor objcur = null;
                // Insert into TblTime
                strQuery="insert into TblTime(Time_id,StartTime, EndTime, LunchBreak) " +
                        "Values (1,'9.00','10.00',0)";
                Public_status.db.execSQL(strQuery);
                strQuery="insert into TblTime(Time_id,StartTime, EndTime, LunchBreak) " +
                        "Values (2,'10.00','11.00',0)";
                Public_status.db.execSQL(strQuery);
                strQuery="insert into TblTime(Time_id,StartTime, EndTime, LunchBreak) " +
                        "Values (3,'11.00','12.00',0)";
                Public_status.db.execSQL(strQuery);
                strQuery="insert into TblTime(Time_id,StartTime, EndTime, LunchBreak) " +
                        "Values (4,'12.00','1.30',1)"; // Lunch Break
                Public_status.db.execSQL(strQuery);
                strQuery="insert into TblTime(Time_id,StartTime, EndTime, LunchBreak) " +
                        "Values (5,'1.30','2.30',0)";
                Public_status.db.execSQL(strQuery);
                strQuery="insert into TblTime(Time_id,StartTime, EndTime, LunchBreak) " +
                        "Values (6,'2.30','3.30',0)";
                Public_status.db.execSQL(strQuery);
                strQuery="insert into TblTime(Time_id,StartTime, EndTime, LunchBreak) " +
                        "Values (7,'3.30','4.30',0)";
                Public_status.db.execSQL(strQuery);

                // Insert into TblWeek
                strQuery="insert into TblWeek(Week_id,Days) " +
                        "Values (1,'Mon')";
                Public_status.db.execSQL(strQuery);
                strQuery="insert into TblWeek(Week_id,Days) " +
                        "Values (2,'Tue')";
                Public_status.db.execSQL(strQuery);
                strQuery="insert into TblWeek(Week_id,Days) " +
                        "Values (3,'Wed')";
                Public_status.db.execSQL(strQuery);
                strQuery="insert into TblWeek(Week_id,Days) " +
                        "Values (4,'Thu')";
                Public_status.db.execSQL(strQuery);
                strQuery="insert into TblWeek(Week_id,Days) " +
                        "Values (5,'Fri')";
                Public_status.db.execSQL(strQuery);

                // Insert into TblClass
                strQuery="insert into TblClass(Class_id,ClassName) " +
                        "Values (1,'PU-1')";
                Public_status.db.execSQL(strQuery);
                strQuery="insert into TblClass(Class_id,ClassName) " +
                        "Values (2,'PU-2')";
                Public_status.db.execSQL(strQuery);

                //TblSubject
                strQuery="insert into TblSubject(Subject_id,Name, Class_id, Counter) " +
                        "Values(1,'Maths',1,0)";
                Public_status.db.execSQL(strQuery);
                strQuery="insert into TblSubject(Subject_id,Name, Class_id, Counter) " +
                        "Values(2,'Sci',1,0)";
                Public_status.db.execSQL(strQuery);
                strQuery="insert into TblSubject(Subject_id,Name, Class_id, Counter) " +
                        "Values(3,'Soc',2,0)";
                Public_status.db.execSQL(strQuery);
                strQuery="insert into TblSubject(Subject_id,Name, Class_id, Counter) " +
                        "Values(4,'Soc',2,0)";
                Public_status.db.execSQL(strQuery);


                //TblTeacher
                strQuery="insert into TblTeacher(Teacher_id,Name, Subject_id) " +
                        "Values(1,'L1',1)";
                Public_status.db.execSQL(strQuery);
                strQuery="insert into TblTeacher(Teacher_id,Name, Subject_id) " +
                        "Values(2,'L2',2)";
                Public_status.db.execSQL(strQuery);
                strQuery="insert into TblTeacher(Teacher_id,Name, Subject_id) " +
                        "Values(3,'L3',3)";
                Public_status.db.execSQL(strQuery);
                strQuery="insert into TblTeacher(Teacher_id,Name, Subject_id) " +
                        "Values(4,'L4',4)";
                Public_status.db.execSQL(strQuery);
                strQuery="insert into TblTeacher(Teacher_id,Name, Subject_id) " +
                        "Values(5,'L5',2)";
                Public_status.db.execSQL(strQuery);
                strQuery="insert into TblTeacher(Teacher_id,Name, Subject_id) " +
                        "Values(6,'L6',4)";
                Public_status.db.execSQL(strQuery);

              //  Create_TimeTable();
            /*
            // INSERT INTO GENERATE TABLE
            strQuery="insert into TblGenerate(Time_Id,Week_Id,Teacher_Id,Subject_Id,Class_Id,isUsed) " +
                    "Values (1,1,1,1,1,0)";
            Public_status.db.execSQL(strQuery);

            strQuery="insert into TblGenerate(Time_Id,Week_Id,Teacher_Id,Subject_Id,Class_Id,isUsed) " +
                    "Values (2,1,3,2,1,0)";
            Public_status.db.execSQL(strQuery);

            strQuery="insert into TblGenerate(Time_Id,Week_Id,Teacher_Id,Subject_Id,Class_Id,isUsed) " +
                    "Values (3,1,5,3,1,0)";
            Public_status.db.execSQL(strQuery);

            strQuery="insert into TblGenerate(Time_Id,Week_Id,Teacher_Id,Subject_Id,Class_Id,isUsed) " +
                    "Values (4,1,2,4,1,0)";
            Public_status.db.execSQL(strQuery);

            strQuery="insert into TblGenerate(Time_Id,Week_Id,Teacher_Id,Subject_Id,Class_Id,isUsed) " +
                    "Values (5,1,4,5,1,0)";
            Public_status.db.execSQL(strQuery);

            strQuery="insert into TblGenerate(Time_Id,Week_Id,Teacher_Id,Subject_Id,Class_Id,isUsed) " +
                    "Values (1,2,1,1,1,0)";
            Public_status.db.execSQL(strQuery);

            strQuery="insert into TblGenerate(Time_Id,Week_Id,Teacher_Id,Subject_Id,Class_Id,isUsed) " +
                    "Values (2,2,3,2,1,0)";
            Public_status.db.execSQL(strQuery);

            strQuery="insert into TblGenerate(Time_Id,Week_Id,Teacher_Id,Subject_Id,Class_Id,isUsed) " +
                    "Values (3,2,5,3,1,0)";
            Public_status.db.execSQL(strQuery);

            strQuery="insert into TblGenerate(Time_Id,Week_Id,Teacher_Id,Subject_Id,Class_Id,isUsed) " +
                    "Values (4,2,2,4,1,0)";
            Public_status.db.execSQL(strQuery);

            strQuery="insert into TblGenerate(Time_Id,Week_Id,Teacher_Id,Subject_Id,Class_Id,isUsed) " +
                    "Values (5,2,4,5,1,0)";
            Public_status.db.execSQL(strQuery);

            strQuery="insert into TblGenerate(Time_Id,Week_Id,Teacher_Id,Subject_Id,Class_Id,isUsed) " +
                    "Values (1,3,1,1,1,0)";
            Public_status.db.execSQL(strQuery);

            strQuery="insert into TblGenerate(Time_Id,Week_Id,Teacher_Id,Subject_Id,Class_Id,isUsed) " +
                    "Values (2,3,3,2,1,0)";
            Public_status.db.execSQL(strQuery);

            strQuery="insert into TblGenerate(Time_Id,Week_Id,Teacher_Id,Subject_Id,Class_Id,isUsed) " +
                    "Values (3,3,5,3,1,0)";
            Public_status.db.execSQL(strQuery);

            strQuery="insert into TblGenerate(Time_Id,Week_Id,Teacher_Id,Subject_Id,Class_Id,isUsed) " +
                    "Values (4,3,2,4,1,0)";
            Public_status.db.execSQL(strQuery);

            strQuery="insert into TblGenerate(Time_Id,Week_Id,Teacher_Id,Subject_Id,Class_Id,isUsed) " +
                    "Values (5,3,4,5,1,0)";
            Public_status.db.execSQL(strQuery);

            strQuery="insert into TblGenerate(Time_Id,Week_Id,Teacher_Id,Subject_Id,Class_Id,isUsed) " +
                    "Values (1,4,1,1,1,0)";
            Public_status.db.execSQL(strQuery);

            strQuery="insert into TblGenerate(Time_Id,Week_Id,Teacher_Id,Subject_Id,Class_Id,isUsed) " +
                    "Values (2,4,3,2,1,0)";
            Public_status.db.execSQL(strQuery);

            strQuery="insert into TblGenerate(Time_Id,Week_Id,Teacher_Id,Subject_Id,Class_Id,isUsed) " +
                    "Values (3,4,5,3,1,0)";
            Public_status.db.execSQL(strQuery);

            strQuery="insert into TblGenerate(Time_Id,Week_Id,Teacher_Id,Subject_Id,Class_Id,isUsed) " +
                    "Values (4,4,2,4,1,0)";
            Public_status.db.execSQL(strQuery);

            strQuery="insert into TblGenerate(Time_Id,Week_Id,Teacher_Id,Subject_Id,Class_Id,isUsed) " +
                    "Values (5,4,4,5,1,0)";
            Public_status.db.execSQL(strQuery);
            */

            }
            catch (Exception e)
            {
                String s = e.getMessage();
                e.printStackTrace();
            }
            return;
        }


    public void showMessage(String er, String Msg)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(er);
        builder.setMessage(Msg);
        builder.show();

    }

}
