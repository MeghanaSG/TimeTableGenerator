package com.example.sois.timetablescheduler;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Add_class extends AppCompatActivity {
    EditText txt1;
    Button b;
    int i, no;
    String value;
    List<EditText> allEds = new ArrayList<EditText>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_class);
        txt1 = (EditText) findViewById(R.id.txtno);
        b = (Button) findViewById(R.id.bnext);
        Public_status.db = openOrCreateDatabase("TIMETABLE", Context.MODE_PRIVATE, null);
       /* Cursor c1= Public_status.db.rawQuery("SELECT * FROM TblClass", null);
        if(c1.getCount()==0)
        {
            showMessage("Error", "No records found");
            return;
        }
        StringBuffer bu=new StringBuffer();
        while(c1.moveToNext())
        {
            bu.append(" id : "+c1.getString(0)+"\n");
            bu.append(" c name : "+c1.getString(1)+"\n");
        }
        c1.close();
        showMessage("Details",bu.toString());*/
    }

    public void Submit(View view) {
        value = txt1.getText().toString();
        no = Integer.parseInt(value);
        final TableLayout tb = (TableLayout) findViewById(R.id.tbl);
        for (i = 0; i < no; i++)
        {
            EditText EditText1 = new EditText(this);
            EditText1.setHint("Enter Classes");
            EditText1.setId(i);
            allEds.add(EditText1);
            tb.addView(EditText1);
        }
        b.setVisibility(View.VISIBLE);
    }

    public void showMessage(String er, String Msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(er);
        builder.setMessage(Msg);
        builder.show();

    }

    public void Next(View view) {
        String[] start = new String[(allEds.size())];
        for (int i = 0; i < allEds.size(); i++) {
            start[i] = allEds.get(i).getText().toString();
        }
        int i;
        for (i = 0; i < allEds.size(); i++)
        {
            start[i]=start[i].toUpperCase();
            Public_status.db.execSQL("INSERT INTO TblClass(ClassName) VALUES('" + start[i] + "');");

        }
        Toast.makeText(this, "done", Toast.LENGTH_LONG).show();
        /*Cursor c =  Public_status.db.rawQuery("SELECT * FROM TblClass", null);
        if (c.getCount() == 0) {
            showMessage("Error", "No records found");
            return;
        }

        StringBuffer bu = new StringBuffer();
        while (c.moveToNext()) {

            bu.append("id" + c.getString(0) + "\n");
            bu.append("class" + c.getString(1) + "\n");
        }

        showMessage("Details", bu.toString());
        c.close();*/
        Intent intent = new Intent(this,Add_subject.class);
        startActivity(intent);
/*
        Intent intent = new Intent(this, Add_day.class);
        startActivity(intent);*/
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
