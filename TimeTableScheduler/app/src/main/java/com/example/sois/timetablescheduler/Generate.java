package com.example.sois.timetablescheduler;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Generate extends AppCompatActivity
{
     int sub_per_week=5;
    static int m=0;
    String sessionId;
    //final Intent intent=new Intent();
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate);
     //   Public_status.db.execSQL("DROP TABLE IF EXISTS TblGenerate;");
        Public_status.db = openOrCreateDatabase("TIMETABLE",Context.MODE_PRIVATE, null);

       // Public_status.db.execSQL("CREATE TABLE IF NOT EXISTS TblGenerate(Generate_id INTEGER PRIMARY KEY AUTOINCREMENT, Time_id int , Week_id int , Teacher_id int, Subject_id int, Class_id int, isUsed Bit );");
       // sub_per_week=getIntent().getExtras();
     /*  Bundle bun=getIntent().getExtras();
        sub_per_week=bun.getInt("nosub");*/

        Button B_generate=(Button) findViewById(R.id.btngenerate);
        Boolean status=false;
        Cursor c= Public_status.db.rawQuery("select * from TblTime;",null);
        if(c.getCount()!=0)
        {
            c= Public_status.db.rawQuery("select * from TblWeek;",null);
            if(c.getCount()!=0)
            {
                c= Public_status.db.rawQuery("select * from TblClass;",null);
                if(c.getCount()!=0)
                {
                    c= Public_status.db.rawQuery("select * from TblSubject;",null);
                    if(c.getCount()!=0)
                    {
                        c= Public_status.db.rawQuery("select * from TblTeacher;",null);
                        if(c.getCount()!=0)
                        {
                            status=true;
                        }
                    }
                }
            }
        }
        c.close();
        StringBuffer bu=new StringBuffer();
        c= Public_status.db.rawQuery("select * from TblTime;",null);
        bu.append("time count : "+  c.getCount()+"\n");
        {
            c= Public_status.db.rawQuery("select * from TblWeek;",null);
            bu.append("week count: "+  c.getCount()+"\n");
            {
                c= Public_status.db.rawQuery("select * from TblClass;",null);
                bu.append("class count: "+  c.getCount()+"\n");
                bu.append("sub/week count: "+  sub_per_week +"\n");
                {
                    c= Public_status.db.rawQuery("select * from TblSubject;",null);
                    bu.append("sub count: "+  c.getCount()+"\n");
                    {
                        c= Public_status.db.rawQuery("select * from TblTeacher;",null);
                        bu.append("teacher count: "+  c.getCount()+"\n");
                        {
                            status=true;
                        }
                    }
                }
            }
        }
    c.close();
    showMessage("Details",bu.toString());
        B_generate.setEnabled(status);

    }
    private void Create_TimeTable()
    {
        int Counter=0;
        Cursor cteacher,csubject,cweek,ctime,cclass;
        cweek=Public_status.db.rawQuery("select * from TblWeek;",null);
        while(cweek.moveToNext())
        {
            ctime=Public_status.db.rawQuery("select * from TblTime where LunchBreak=0;",null);
            while(ctime.moveToNext())
            {
                cclass=Public_status.db.rawQuery("select * from TblClass;",null);
                while(cclass.moveToNext())
                {
                    int tempGenerate_id,tempTime_id,tempWeek_id,tempTeacher_id,
                            tempSubject_id,tempClass_id;
                    tempWeek_id=(cweek.getInt(0));
                    tempTime_id=(ctime.getInt(0));
                    tempClass_id=(cclass.getInt(0));
                    csubject=Public_status.db.rawQuery("select * from TblSubject where Class_id=" + tempClass_id + " and Counter<" + sub_per_week,null);
                    if(csubject.getCount()!=0)
                    {
                        csubject.moveToNext();
                        tempSubject_id = (csubject.getInt(0));
                        cteacher = Public_status.db.rawQuery("select * from TblTeacher t " +
                                "where Subject_id=" + tempSubject_id +
                                " and not exists (select * from  TblGenerate Where Time_id=" + tempTime_id +
                                " and Week_id= " + tempWeek_id + " and Teacher_id=t.Teacher_id)", null);
                        if (cteacher.getCount() != 0)
                        {
                            cteacher.moveToNext();
                            tempTeacher_id = (cteacher.getInt(0));
                            Public_status.db.execSQL("Insert into TblGenerate(Time_id,Week_id,Teacher_id,Subject_id,Class_id,isUsed)" +
                                    " Values(" + tempTime_id + "," + tempWeek_id + "," + tempTeacher_id + "," + tempSubject_id + "," + tempClass_id + ",0);");
                            Public_status.db.execSQL("Update TblSubject set Counter=Counter+1 " +
                                    "Where Subject_id=" + tempSubject_id +
                                    " And Class_id=" + tempClass_id);
                        }
                        else
                        {
                            // cteacher.moveToNext();
                            // tempTeacher_id=(cteacher.getInt(0));
                            Public_status.db.execSQL("Insert into TblGenerate(Time_id,Week_id,Teacher_id,Subject_id,Class_id,isUsed)" +
                                    " Values(" + tempTime_id + "," + tempWeek_id + ", NULL ,NULL," + tempClass_id + ",0);");
                            //Public_status.Public_status.db.execSQL("Update TblSubject set Counter=Counter+1 Where Subject_id" + csubject.getInt(csubject.getColumnIndex("Subject_id")) +" And Class_id=" + tempClass_id);
                        }
                    }
                    //else part of subject
                    else
                    {
                        Public_status.db.execSQL("Insert into TblGenerate(Time_id,Week_id,Teacher_id,Subject_id,Class_id,isUsed)" +
                                " Values(" + tempTime_id + "," + tempWeek_id + ", NULL ,NULL," + tempClass_id + ",0);");

                    }
                }//cclass.close();
            }//cweek.close();
        }//cweek.close();
    }
    public void onBackPressed()
    {
        finish();
         Intent intent = new Intent(getApplicationContext(), Main.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("EXIT", true);
        startActivity(intent);
    }
    public  void next(View v)
    {

      /*  Cursor c1= Public_status.db.rawQuery("SELECT * FROM TblSubject", null);
        if(c1.getCount()==0)
        {
            showMessage("Error", "No records found");
            return;
        }
        StringBuffer bu=new StringBuffer();
        while(c1.moveToNext())
        {

            bu.append(" id     : "+c1.getString(0)+"\n");
            bu.append(" S name : "+c1.getString(1)+"\n");
            bu.append(" C id   : "+c1.getString(2)+"\n");
            bu.append(" Count   : "+c1.getString(3)+"\n");


        }
        c1.close();
        showMessage("Details",bu.toString());*/
        Create_TimeTable();
        Cursor c1= Public_status.db.rawQuery("SELECT * FROM TblGenerate WHERE Subject_id IS NOT NULL AND Teacher_id IS NOT NULL", null);
        if(c1.getCount()==0)
        {
            showMessage("Error", "No records found");
            return;
        }
        StringBuffer bu=new StringBuffer();
        while(c1.moveToNext())
        {
            bu.append("g id : "+c1.getString(0)+"\n");
            bu.append("T id : "+c1.getString(1)+"\n");
            bu.append("W id : "+c1.getString(2)+"\n");
            bu.append("L id : "+c1.getString(3)+"\n");
            bu.append("S id : "+c1.getString(4)+"\n");
            bu.append("used : "+c1.getString(6)+"\n");
            bu.append("C id : "+c1.getString(5)+"\n\n");
        }
        c1.close();
        showMessage("Details",bu.toString());
        Intent intent = new Intent(this,Grid.class);
        startActivity(intent);

    }


    private void Set_Table()
    {
        String strQuery = "";
        int intID;

        try
        {/*

          //  Cursor objcur = null;

            // Insert into TblTime
            strQuery="insert into TblTime(Time_id,StartTime, EndTime, LunchBreak) " +
                    "Values (1,'1','2',0)";
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

            Create_TimeTable();
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

        } catch (Exception e)
        {
            String s = e.getMessage();
            e.printStackTrace();
        }
        return;
    }


    public void showMessage(String er,String Msg)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(er);
        builder.setMessage(Msg);
        builder.show();

    }

}

