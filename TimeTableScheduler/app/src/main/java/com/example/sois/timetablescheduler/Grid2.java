package com.example.sois.timetablescheduler;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.GridLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Grid2 extends AppCompatActivity {
    int CCOL,CROW,gno,countl=0,getl,lunch=4;
   // SQLiteDatabase  Public_status.db;
    int time_id,subject_id,teacher_id,week_id,class_id,g_id;
    String subname,lecname,classname;
    public RelativeLayout r;
    String[] start,arrweek ;

    String cellname,cell1,cell2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid2);
        Cursor ctime, cweek, cclass,csubject,cgen;
        r = (RelativeLayout) findViewById(R.id.rl);
        Public_status.db = openOrCreateDatabase("TIMETABLE", Context.MODE_PRIVATE, null);
        //Set_Table();
        ctime =  Public_status.db.rawQuery("SELECT * FROM TblTime", null);
        cweek =  Public_status.db.rawQuery("SELECT * FROM TblWeek", null);
        CROW = cweek.getCount();
        CCOL = ctime.getCount();
       /* while (ctime.moveToNext())
        {
            getl=Integer.parseInt(cweek.getString(3));
            countl++;
            if(getl==1)
            {
                lunch=countl;
                break;
            }

        }*/

                ctime.close();
                cweek.close();
                CROW++;
                CCOL++;
                arrweek = new String[CROW];
                start = new String[CCOL];
                //get class count to create no of grid
                cclass =  Public_status.db.rawQuery("SELECT * FROM TblClass", null);
                gno = cclass.getCount();

                while (cclass.moveToNext())
                {
                    cellname = cclass.getString(1);
                    class_id=  Integer.parseInt(cclass.getString(0));
                    GridLayout gridLayout = new GridLayout(this);
                    TextView[][] textViews = new TextView[CROW][CCOL];
                    //define how many rows and columns to be used in the layout
                    gridLayout.setRowCount(CROW);
                    gridLayout.setColumnCount(CCOL);

                    for (int i = 0; i < CROW; i++) {
                        for (int j = 0; j < CCOL; j++) {

                            textViews[i][j] = new TextView(this);
                            //setPos(textViews[i][j], i, j);
                            cellname = "break";
                            int countc = 0;
                            String cn;
                            // class 0
                            if (i == 0 && j == 0)
                            {
                                settext(textViews[i][j], i, j, cellname);
                            }
                            else if (i == 0)
                            {
                                ctime =  Public_status.db.rawQuery("SELECT * FROM TblTime", null);
                                int countt = 0;
                                String atime;
                                while (ctime.moveToNext())
                                {

                                    // getl=Integer.parseInt(ctime.getString(2));

                                    cell1 = ctime.getString(1) + "-";
                                    cell2 = ctime.getString(2);
                                    atime = cell1 + cell2;
                                    //add to array

                                    start[countt] = atime;
                                    countt++;


                                }
                                cellname = start[j - 1];
                                ctime.close();
                                settext(textViews[i][j], i, j, cellname);
                            }
                            else if (j == 0)
                            {
                                cweek =  Public_status.db.rawQuery("SELECT * FROM TblWeek", null);
                                int countweek = 0;
                                //String atime;
                                while (cweek.moveToNext())
                                {
                                    cellname = cweek.getString(1);
                                    //add to array
                                    arrweek[countweek] = cellname;
                                    countweek++;

                                }
                                cellname = arrweek[i - 1];
                                settext(textViews[i][j], i, j, cellname);
                                cweek.close();
                            }
                       /* if(i>0 &&j ==lunch)
                        {
                            settext(textViews[i][j], i, j, cellname);

                        }*/
                            else
                            {
                                //else part to select subject and teacher from grnrtate_tbl where class =class_id ,isused =0 ,
                                //take value col=time row =week
                                //if i==row j==coll
                                //cgen =  Public_status.db.rawQuery("SELECT * FROM TblGenerate WHERE Class_id=" + class_id + " AND isUsed=0 ",null );
                           /* if(i>1 && j==lunch)
                            {
                                settext(textViews[i][j], i, j, cellname);
                            }*/
                                cgen =  Public_status.db.rawQuery("SELECT * FROM TblGenerate WHERE Class_id=" + class_id + " AND isUsed=0 AND Teacher_Id!=NULL And Subject_Id!=NULL",null);
                                while (cgen.moveToNext())
                                {
                                    g_id = Integer.parseInt(cgen.getString(0));
                                    time_id = (Integer.parseInt(cgen.getString(1)));
                                    week_id = (Integer.parseInt(cgen.getString(2)));
                                    teacher_id = Integer.parseInt(cgen.getString(3));
                                    subject_id = Integer.parseInt(cgen.getString(4));

                                    if (i==week_id && j==time_id)
                                    {
                                        csubject =  Public_status.db.rawQuery("SELECT * FROM TblSubject WHERE Subject_id=" + subject_id, null);
                                        while (csubject.moveToNext())
                                        {
                                            subname = csubject.getString(1);
                                        }
                                        cellname=subname;
                                        settext(textViews[i][j], i, j, cellname);
                                    }
                                    settext(textViews[i][j], i, j, cellname);
                                }
                                // update generate set isused=1
                                Public_status.db.execSQL("Update TblGenerate SET isUsed=1 WHERE Class_id=" + class_id + " AND Time_id=" + time_id + " AND Week_id=" + week_id);


                                cgen.close();

                                // settext(textViews[i][j], i, j, cellname);
                            }
                            //settext(textViews[i][j], i, j, cellname);
                            gridLayout.addView(textViews[i][j]);
                        }
                    }

                    r.addView(gridLayout);

                }
                cclass.close();
/*
        Cursor c1= Public_status.db.rawQuery("SELECT * FROM TblGenerate", null);
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
            }

            public void settext(TextView textView, int row, int column,String name) {

                GridLayout.LayoutParams param = new GridLayout.LayoutParams();
                param.width = 200;
                param.height = 200;
                param.setGravity(Gravity.CENTER);
                param.rowSpec = GridLayout.spec(row);
                param.columnSpec = GridLayout.spec(column);
                textView.setLayoutParams(param);
                textView.setText(name);
            }

    /* public  void Next(View view) {
           Intent intent = new Intent(this,Add_Batch.class);
           startActivity(intent);

       }*/

            public void showMessage(String er,String Msg)
            {
                AlertDialog.Builder builder=new AlertDialog.Builder(this);
                builder.setCancelable(true);
                builder.setTitle(er);
                builder.setMessage(Msg);
                builder.show();

            }

}






