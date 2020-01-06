package com.example.sois.timetablescheduler;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;

public class actTimeTableReport extends AppCompatActivity {
   // SQLiteDatabase Public_status.db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // Public_status.db = openOrCreateDatabase("TIMETABLE", Context.MODE_PRIVATE, null);
        try {
            setContentView(R.layout.activity_act_time_table_report);
        } catch (Exception e) {
            String strM = e.getMessage();
            e.printStackTrace();
        }
        Set_Table();
    }

         /*   LinearLayout linearLayout = (LinearLayout) findViewById(R.id.rootTimeTable);
            TableLayout tbl_layout_Time_Heading = (TableLayout)findViewById(R.id.tbl_layout_main_id);

            //ScrollView scroll = new ScrollView(this);
            //scroll.setBackgroundColor(android.R.color.transparent);
            //LinearLayout linearLayout = new LinearLayout(this);
            //linearLayout.setOrientation(LinearLayout.VERTICAL);
            //linearLayout.setBackgroundResource(R.drawable.img);

            LinearLayout Horizontal_Layout = new LinearLayout(this);
            Horizontal_Layout.setOrientation(LinearLayout.HORIZONTAL);

            int intGenerate_Id,intTime_Id, intWeek_Id, intTeacher_Id,intSubject_Id,intClass_Id;
// !@#$        //String str="SELECT DISTINCT Class_id FROM TblGenerate where isUsed=0 ORDER BY Class_id;";
            String str="SELECT DISTINCT Class_id FROM TblGenerate where Class_Id=1 ORDER BY Class_id;";
            Cursor curGenerate = Public_status.db.rawQuery(str, null);
            if (curGenerate.getCount() != 0)
            {
                if (curGenerate.moveToFirst())
                {
                    do
                    {
                        intClass_Id = curGenerate.getInt(curGenerate.getColumnIndex("Class_Id"));

                        String strClass="";
                        // Get Class.
                        Cursor cur_TmpClass=Public_status.db.rawQuery("select * from TblClass " +
                                "where Class_id=" + intClass_Id,null);
                        if(cur_TmpClass.getCount()!=0)
                        {
                            cur_TmpClass.moveToNext();
                            strClass = cur_TmpClass.getString(cur_TmpClass.getColumnIndex("ClassName"));
                        }

                        // Display Class at the top of Table View.
                        TextView txtvClass = new TextView(this);
                        txtvClass.setTextSize(40);
                        txtvClass.setTypeface(null, Typeface.BOLD);
                        txtvClass.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)); //
                        txtvClass.setTextColor(Color.parseColor("#212121"));
                        txtvClass.setText(strClass);
                        txtvClass.setGravity(View.TEXT_ALIGNMENT_CENTER);
                        linearLayout.addView((TextView) txtvClass);

                        // To Display the Heading -> Time.
                        String strTimeHead = "SELECT * FROM TblTime Order by Time_id;";
                        Cursor curTimeHead = Public_status.db.rawQuery(strTimeHead, null);
                        //TableLayout tbl_layout_Time_Heading = new TableLayout(this);
                        //tbl_layout_Time_Heading.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.FILL_PARENT));
                        if (curTimeHead.getCount() != 0)
                        {
                            if (curTimeHead.moveToFirst())
                            {
                                //TableRow trTimeHeading = new TableRow(this);
                                //TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
                                //trTimeHeading.setLayoutParams(lp);

                                TextView txtvDay = new TextView(this);
                                //txtvDay.setPadding(10,10,10,10);
                                txtvDay.setTextSize(20);
                                txtvDay.setTypeface(null, Typeface.BOLD);
                                txtvDay.setWidth(100);
                                txtvDay.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)); //WRAP_CONTENT
                                txtvDay.setTextColor(Color.parseColor("#212121"));
                                txtvDay.setText("Day  ");
                                //trTimeHeading.addView((TextView) txtvDay);
                                Horizontal_Layout.addView((TextView) txtvDay);
                                //linearLayout.addView(Horizontal_Layout);

                                // !@#$
                                //tbl_layout_Time_Heading.addView((TableRow) trTimeHeading);
                                //Horizontal_Layout.addView((TableRow) trTimeHeading);

                                TextView txtvTime = new TextView(this);
                                do
                                {
                                    //trTimeHeading = new TableRow(this);
                                    //lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
                                    //trTimeHeading.setLayoutParams(lp);

                                    String strStart = curTimeHead.getString(curTimeHead.getColumnIndex("StartTime"));
                                    String strEnd = curTimeHead.getString(curTimeHead.getColumnIndex("EndTime"));

                                    txtvTime = new TextView(this);
                                    txtvDay.setTextSize(20);
                                    //txtvDay.setPadding(10,10,10,10);
                                    txtvDay.setWidth(100);
                                    txtvClass.setGravity(View.TEXT_ALIGNMENT_GRAVITY);
                                    txtvTime.setTypeface(null, Typeface.BOLD);
                                    txtvTime.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                                    txtvTime.setTextColor(Color.parseColor("#212121"));
                                    txtvTime.setText("| " +strStart + "-" + strEnd + " |");
                                    //txtvTime.setId();
                                    try
                                    {
                                        Horizontal_Layout.addView((TextView) txtvTime);
                                        //tbl_layout_Time_Heading.addView((TableRow) trTimeHeading);
                                        //Horizontal_Layout.addView((TableRow) trTimeHeading);
                                        //linearLayout.addView(Horizontal_Layout);
                                    }
                                    catch (Exception e)
                                    {
                                        String strM=e.getMessage();
                                        e.printStackTrace();
                                    }
                                } while (curTimeHead.moveToNext());
                            }
                        } // End of -> Display the Heading -> Time.
                        linearLayout.addView(Horizontal_Layout);

                        //tbl_layout_Time_Heading.addView((TableRow) trTimeHeading);
                        //tbl_layout_Time_Heading.invalidate();
                        //linearLayout.addView((TableLayout) tbl_layout_Time_Heading);
//!@#$
                        //tbl_layout_Time_Heading.invalidate();


                        // Display Table View.
                        // Get Week
                        String strWeek= "SELECT * FROM TblWeek Order by Week_id ;";
                        //String strWeek= "SELECT * FROM TblWeek where Week_id=10 Order by Week_id;";
                        Cursor curWeek = Public_status.db.rawQuery(strWeek, null);
                        if (curWeek.getCount() != 0)
                        {
                            if (curWeek.moveToFirst())
                            {
                                do
                                {
                                    //TableLayout tbl_layout = new TableLayout(this);
                                    //tbl_layout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.FILL_PARENT));
                                    //TableRow trContent = new TableRow(this);

                                    Horizontal_Layout = new LinearLayout(this);
                                    Horizontal_Layout.setOrientation(LinearLayout.HORIZONTAL);

                                    intWeek_Id = curWeek.getInt(curWeek.getColumnIndex("Week_id"));
                                    String strDayOfWeek=curWeek.getString(curWeek.getColumnIndex("Days"));
                                    TextView txtvWeek = new TextView(this);
                                    txtvWeek.setTextSize(20);
                                    txtvWeek.setWidth(100);
                                    txtvWeek.setTypeface(null, Typeface.BOLD);
                                    txtvWeek.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                                    txtvWeek.setTextColor(Color.parseColor("#212121"));
                                    txtvWeek.setText(strDayOfWeek);
                                    //trContent.addView((TextView) txtvWeek);
                                    Horizontal_Layout.addView((TextView) txtvWeek);


                                    // Get Time
                                    String strTime = "SELECT * FROM TblTime Order by Time_id;";
                                    Cursor curTime = Public_status.db.rawQuery(strTime, null);
                                    if (curTime.getCount()!= 0)
                                    {
                                        if (curTime.moveToFirst())
                                        {
                                            do
                                            {
                                                int intTime_id = curTime.getInt(curTime.getColumnIndex("Time_id"));
                                                String strTTGenerate = "SELECT * FROM TblGenerate WHERE Class_id=" + intClass_Id +
                                                        " AND isUsed=0 AND Time_id=" + intTime_id + " AND Week_id=" +
                                                        intWeek_Id;
                                                Cursor curTTGenerate = Public_status.db.rawQuery(strTTGenerate, null);
                                                TextView txtvSub_Teacher = new TextView(this);
                                                if (curTTGenerate.getCount() != 0)
                                                {
                                                    String strSub="";
                                                    String strTeacher ="";

                                                    curTTGenerate.moveToFirst();

                                                    // Get Subject
                                                    Cursor cur_Tmpsubject=Public_status.db.rawQuery("select * from TblSubject " +
                                                            "where Class_id=" + intClass_Id +
                                                            " and Subject_Id=" + curTTGenerate.getInt(curTTGenerate.getColumnIndex("Subject_Id")),null);
                                                    if(cur_Tmpsubject.getCount()!=0)
                                                    {
                                                        cur_Tmpsubject.moveToNext();
                                                        strSub = cur_Tmpsubject.getString(cur_Tmpsubject.getColumnIndex("Name"));
                                                    }

                                                    // Get Teacher
                                                    Cursor cur_TmpTeacher=Public_status.db.rawQuery("select * from TblTeacher " +
                                                            "where Subject_id=" + curTTGenerate.getInt(curTTGenerate.getColumnIndex("Subject_Id")) +
                                                            " and Teacher_id=" + curTTGenerate.getInt(curTTGenerate.getColumnIndex("Teacher_Id")),null);
                                                    if(cur_TmpTeacher.getCount()!=0)
                                                    {
                                                        cur_TmpTeacher.moveToNext();
                                                        strTeacher = cur_TmpTeacher.getString(cur_TmpTeacher.getColumnIndex("Name"));
                                                    }
                                                    txtvSub_Teacher.setTextSize(20);
                                                    txtvSub_Teacher.setTypeface(null, Typeface.BOLD);
                                                    txtvSub_Teacher.setWidth(100);
                                                    txtvSub_Teacher.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                                                    txtvSub_Teacher.setTextColor(Color.parseColor("#17202A"));
                                                    //trContent.addView((TextView) txtvSub_Teacher);
                                                    txtvSub_Teacher.setText("| " +strSub + "-" + strTeacher + " |");

                                                    Horizontal_Layout.addView((TextView) txtvSub_Teacher);
                                                    String strUpdateTTGenerate = "Update TblGenerate SET isUsed=1 WHERE Class_id=" + intClass_Id +
                                                            " AND Time_id=" + intTime_id + " AND Week_id=" +intWeek_Id;
                                                    Public_status.db.execSQL(strUpdateTTGenerate);
                                                }
                                                else    // Not Assigned for this time.
                                                {
                                                    txtvSub_Teacher = new TextView(this);
                                                    txtvSub_Teacher.setTextSize(20);
                                                    txtvSub_Teacher.setTypeface(null, Typeface.BOLD);
                                                    txtvSub_Teacher.setWidth(100);
                                                    txtvSub_Teacher.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                                                    txtvSub_Teacher.setTextColor(Color.parseColor("#17202A"));
                                                    txtvSub_Teacher.setText("");
                                                    //trContent.addView((TextView) txtvSub_Teacher);
                                                    Horizontal_Layout.addView((TextView) txtvSub_Teacher);
                                                }
                                            }while (curTime.moveToNext());
                                            linearLayout.addView(Horizontal_Layout);
                                        }
                                    }
                                } while (curWeek.moveToNext());
                            }
                        }
                    } while (curGenerate.moveToNext());
                }
            }
        }*/

        private void Create_TimeTable()
        {
            int Counter=0;
            int sub_per_week=2;
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
                        int tempGenerate_id,tempTime_Id,tempWeek_id,tempTeacher_id,
                                tempSubject_id,tempClass_id;
                        tempWeek_id=(cweek.getInt(0));
                        tempTime_Id=(ctime.getInt(0));
                        tempClass_id=(cclass.getInt(0));
                        csubject=Public_status.db.rawQuery("select * from TblSubject where Class_id=" + tempClass_id + " and Counter<" + sub_per_week,null);
                        if(csubject.getCount()!=0)
                        {
                            csubject.moveToNext();
                            tempSubject_id = (csubject.getInt(0));
                            cteacher = Public_status.db.rawQuery("select * from TblTeacher t " +
                                    "where Subject_id=" + tempSubject_id +
                                    " and not exists (select * from  TblGenerate Where Time_Id=" + tempTime_Id +
                                    " and Week_id= " + tempWeek_id + " and Teacher_id=t.Teacher_id)", null);
                            if (cteacher.getCount() != 0)
                            {
                                cteacher.moveToNext();
                                tempTeacher_id = (cteacher.getInt(0));
                                Public_status.db.execSQL("Insert into TblGenerate(Time_Id,Week_id,Teacher_id,Subject_id,Class_id,isUsed)" +
                                        " Values(" + tempTime_Id + "," + tempWeek_id + "," + tempTeacher_id + "," + tempSubject_id + "," + tempClass_id + ",0);");
                                Public_status.db.execSQL("Update TblSubject set Counter=Counter+1 " +
                                        "Where Subject_id=" + tempSubject_id +
                                        " And Class_id=" + tempClass_id);
                            } else
                            {
                                // cteacher.moveToNext();
                                // tempTeacher_id=(cteacher.getInt(0));
                                Public_status.db.execSQL("Insert into TblGenerate(Time_Id,Week_id,Teacher_id,Subject_id,Class_id,isUsed)" +
                                        " Values(" + tempTime_Id + "," + tempWeek_id + ", NULL ,NULL," + tempClass_id + ",0);");
                                //Public_status.Public_status.db.execSQL("Update TblSubject set Counter=Counter+1 Where Subject_id" + csubject.getInt(csubject.getColumnIndex("Subject_id")) +" And Class_id=" + tempClass_id);
                            }
                        }
                        //else part of subject
                        else
                        {
                            Public_status.db.execSQL("Insert into TblGenerate(Time_Id,Week_id,Teacher_id,Subject_id,Class_id,isUsed)" +
                                    " Values(" + tempTime_Id + "," + tempWeek_id + ", NULL ,NULL," + tempClass_id + ",0);");

                        }
                    }
                }
            }

        }

    public void onc(View view)
    {
/*
        Cursor c1=Public_status.db.rawQuery("SELECT * FROM TblGenerate", null);
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
        showMessage("Details",bu.toString());*/

        Intent i=new Intent(this,Grid.class);
        startActivity(i);
    }
    public void showMessage(String er,String Msg)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(er);
        builder.setMessage(Msg);
        builder.show();

    }
        public void onBackPressed()
        {
            finish();
            Intent intent = new Intent(getApplicationContext(), Main.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("EXIT", true);
            startActivity(intent);
        }

        private void Set_Table() {/*
            String strQuery = "";
            int intID;

            try
            {
                Public_status.db.execSQL("DROP TABLE IF EXISTS TblTime;");
                Public_status.db.execSQL("DROP TABLE IF EXISTS TblWeek;");
                Public_status.db.execSQL("DROP TABLE IF EXISTS TblClass;");
                Public_status.db.execSQL("DROP TABLE IF EXISTS TblSubject;");
                Public_status.db.execSQL("DROP TABLE IF EXISTS TblTeacher;");
                Public_status.db.execSQL("DROP TABLE IF EXISTS TblGenerate;");

                Public_status.db.execSQL("CREATE TABLE IF NOT EXISTS TblTime(Time_id int," +
                        "StartTime VARCHAR, EndTime VARCHAR, LunchBreak bit);");

                Public_status.db.execSQL(
                        "CREATE TABLE IF NOT EXISTS TblWeek" +
                                "(" +
                                "Week_id int ," +
                                "Days VARCHAR NOT NULL" +
                                ");");
                Public_status.db.execSQL(
                        "CREATE TABLE IF NOT EXISTS TblClass" +
                                "(" +
                                "Class_id int NOT NULL," +
                                "ClassName VARCHAR NOT NULL" +
                                ");");
                Public_status.db.execSQL(
                        "CREATE TABLE IF NOT EXISTS TblSubject" +
                                "(" +
                                "Subject_id  int," +
                                "Name VARCHAR NOT NULL," +
                                "Class_id int NOT NULL," +
                                "Counter int " +
                                ");");
                Public_status.db.execSQL(
                        "CREATE TABLE IF NOT EXISTS TblTeacher" +
                                "(" +
                                "Teacher_id  INT ," +
                                "Name VARCHAR NOT NULL," +
                                "Subject_id int NOT NULL" +
                                ");");

                Public_status.db.execSQL(
                        "CREATE TABLE IF NOT EXISTS TblGenerate" +
                                "(" +
                                "Generate_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                                "Time_Id int NOT NULL," +
                                "Week_Id int NOT NULL," +
                                "Teacher_Id int ," +
                                "Subject_Id int ," +
                                "Class_Id int, " +
                                "isUsed Bit " +
                                ");");

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
/*
            } catch (Exception e)
            {
                String s = e.getMessage();
                e.printStackTrace();
            }
            return;
        }*/
        }
    }


