package com.example.sois.timetablescheduler;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Add_subject extends AppCompatActivity {
    EditText txt1,c;
    Button b;
    int notime,nodays,nosub,nospw;
    int i,j, no,count;
    String value,c1;
   // SQLiteDatabase db;
    List<EditText> allEds = new ArrayList<EditText>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            setContentView(R.layout.activity_add_subject);
        }
        catch (Exception e)
        {
            String strM = e.getMessage();
            e.printStackTrace();
        }
        txt1 = (EditText) findViewById(R.id.txtno);
        c= (EditText) findViewById(R.id.txtcount);

        b = (Button) findViewById(R.id.bnext);
        Public_status.db = openOrCreateDatabase("TIMETABLE", Context.MODE_PRIVATE, null);
        // Public_status.db.execSQL("DROP TABLE IF EXISTS TblSubject");
        //Public_status.db.execSQL("CREATE TABLE IF NOT EXISTS TblSubject(Subject_id INTEGER PRIMARY KEY AUTOINCREMENT,Name VARCHAR,Class_id int,Counter int);");
       //  Public_status.db.execSQL("DELETE FROM TblSubject");
        /*Cursor c1= Public_status.db.rawQuery("SELECT * FROM TblSubject", null);
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
       /*Cursor c1 = Public_status.db.rawQuery("SELECT * FROM TblTime", null);
        //c1.close();
        notime=c1.getCount();
        c1.close();
        c1 = Public_status.db.rawQuery("SELECT * FROM TblWeek", null);
        nodays=c1.getCount();
        c1.close();
        c1 = Public_status.db.rawQuery("SELECT * FROM TblSubject", null);
        nosub=c1.getCount();
        c1.close();
        nospw=((notime*nodays)-nodays)/nosub;*/
        c.setHint("Note:Max limit((Timeslot*no of week)-no of week)/no of sub");
        //c.setFilters(new InputFilter[]{ new InputFilterMinMax("1", "12")});

    }

    public void Submit(View view)
    {
        value = txt1.getText().toString();
        no = Integer.parseInt(value);
        c1 = c.getText().toString();
        count = Integer.parseInt(c1);

        final TableLayout tb = (TableLayout) findViewById(R.id.tbl);
        for (i = 0; i < no; i++) {

            EditText EditText1 = new EditText(this);
            EditText1.setHint("Enter Subject Name");
            EditText1.setId(i);
            allEds.add(EditText1);
            tb.addView(EditText1);
            //class id
            EditText EditText2 = new EditText(this);
            EditText2.setHint("Enter class id");
            EditText2.setId(i+100);

            allEds.add(EditText2);
            tb.addView(EditText2);
            //add spinner

        }
        b.setVisibility(View.VISIBLE);
    }
    public void showMessage(String er, String Msg)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(er);
        builder.setMessage(Msg);
        builder.show();

    }
    public void Next(View view) {
        String[] start = new String[(allEds.size())];
        for (int i = 0; i < allEds.size(); i++)
        {
            start[i] = allEds.get(i).getText().toString();
        }

        int j, i;
        String CLASSNAME;
        for (i = 0, j = 0; i < allEds.size(); i = i + 2, j++) {
         /*   Cursor c1=Public_status.db.rawQuery("select * from TblClass WHERE Class_id=" + start[i + 1] ,null);
            if(c1.getCount()==0)
            {
                showMessage("Error", "No records found");
                return;
            }
            while(c1.moveToNext())
            {
                CLASSNAME=c1.getString(1);
            }
            c1.close();*/
            start[i]=start[i].toUpperCase();
            Public_status.db.execSQL("INSERT INTO TblSubject(Name,Class_id,Counter)VALUES('" + start[i] + "'," + start[i + 1] + ",0);");

        }
         Cursor c1= Public_status.db.rawQuery("SELECT * FROM TblSubject", null);
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
        showMessage("Details",bu.toString());
        Intent intent = new Intent(this, Add_lecturer.class);
        intent.putExtra("nosub",count);
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

    }






