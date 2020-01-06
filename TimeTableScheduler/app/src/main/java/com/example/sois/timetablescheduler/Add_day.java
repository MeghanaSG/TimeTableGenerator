package com.example.sois.timetablescheduler;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

public class Add_day extends AppCompatActivity {
    CheckBox cmon,ctue,cwed,cthu,cfri,csat,csun;
    String day;
    //SQLiteDatabase  Public_status.db;
    static  int i=0,j;
    int[] id = new int[7];
    String[] ds = new String[7];
    StringBuffer b=new StringBuffer();
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_day);
        Public_status.db=openOrCreateDatabase("TIMETABLE", Context.MODE_PRIVATE, null);
        cmon=(CheckBox) findViewById(R.id.cmon);
        ctue=(CheckBox)findViewById(R.id.ctue);
        cwed=(CheckBox)findViewById(R.id.cwed);
        cthu=(CheckBox)findViewById(R.id.cthu);
        cfri=(CheckBox) findViewById(R.id.cfri);
        csat=(CheckBox)findViewById(R.id.csat);
        csun=(CheckBox)findViewById(R.id.csun);

    }

    public void Submit(View view)
    {

        if (cmon.isChecked())
        {
            day="MON";
                    //cmon.getText().toString();
            insert(day);
        }
        if (ctue.isChecked())
        {
            day="TUE";
                  //  ctue.getText().toString();
            insert(day);
        }
        if (cwed.isChecked())
        {
            day="WED";
                    //cwed.getText().toString();
            insert(day);
        }
        if (cthu.isChecked())
        {
            day="THU";
                    //cthu.getText().toString();
            insert(day);
        }
        if (cfri.isChecked())
        {
            day="FRI";
                    //cfri.getText().toString();
            insert(day);
        }
        if (csat.isChecked())
        {
            day="SAT";
            //csat.getText().toString();
            insert(day);
        }
        if (csun.isChecked())
        {
            day="SUN";
            //csun.getText().toString();
            insert(day);
        }
/*
        for(int k=0; k <= id.length; k++)
        {
            b.append("id:" + id[k] + "\n");
            b.append("day  :" + ds[k] + "\n");

            //  b.append("Rollno"+c.getString(4)+"\n");

        }*/
      // showMessage("Details",b.toString());


/*
        Cursor c= Public_status.db.rawQuery("SELECT * FROM TblWeek", null);
        if(c.getCount()==0)
        {
            showMessage("Error", "No records found");
            return;
        }
        StringBuffer bu=new StringBuffer();
        while(c.moveToNext())
        {

            bu.append("id"+c.getString(0)+"\n");
            bu.append("days"+c.getString(1)+"\n");


        } c.close();
        showMessage("Details",bu.toString());

*/
       Intent intent = new Intent(this,Add_class.class);
        startActivity(intent);

    }
    public void onBackPressed()
    {
        finish();
        Intent intent = new Intent(getApplicationContext(), Main.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("EXIT", true);
        startActivity(intent);
    }
    public void insert(String day)
    {


        /* Public_status.db.execSQL("INSERT INTO TblWeek(Week_id,Days) VALUES(0,'Monday')");
         Public_status.db.execSQL("INSERT INTO TblWeek(Week_id,Days) VALUES(1,'Tuesday')");*/
        Public_status.db.execSQL("INSERT INTO TblWeek(Days) VALUES('" + day + "');");
        Toast.makeText(this,"done",Toast.LENGTH_LONG).show();

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
