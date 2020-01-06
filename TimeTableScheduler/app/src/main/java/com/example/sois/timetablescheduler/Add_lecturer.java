package com.example.sois.timetablescheduler;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Add_lecturer extends AppCompatActivity{

    EditText txt1,c;
    Button b;
    int i, no,count,nosub;
    String value,c1;
   // SQLiteDatabase db;
    List<EditText> allEds = new ArrayList<EditText>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_lecturer);
        txt1 = (EditText) findViewById(R.id.txtno);
        Bundle bun=getIntent().getExtras();
        nosub=bun.getInt("nosub");



        c= (EditText) findViewById(R.id.txtcount);
        b = (Button) findViewById(R.id.bnext);
        Public_status.db = openOrCreateDatabase("TIMETABLE", Context.MODE_PRIVATE, null);
       /* Cursor c1= Public_status.db.rawQuery("SELECT * FROM TblTeacher", null);
        if(c1.getCount()==0)
        {
            showMessage("Error", "No records found");
            return;
        }
        StringBuffer bu=new StringBuffer();
        while(c1.moveToNext())
        {

            bu.append(" id     : "+c1.getString(0)+"\n");
            bu.append(" L name : "+c1.getString(1)+"\n");
            bu.append(" s name : "+c1.getString(2)+"\n");



        }
        c1.close();
        showMessage("Details",bu.toString());*/
        // Public_status.db.execSQL("DELETE FROM TblSubject");
    }

    public void Submit(View view)
    {
        value = txt1.getText().toString();
        no = Integer.parseInt(value);
        c1 = txt1.getText().toString();
        count = Integer.parseInt(value);

        final TableLayout tb = (TableLayout) findViewById(R.id.tbl);
        for (i = 0; i < no; i++) {

            EditText EditText3 = new EditText(this);
            EditText3.setHint("Enter Lecturer ID");
            EditText3.setId(i+200);
            allEds.add(EditText3);
            tb.addView(EditText3);


            EditText EditText1 = new EditText(this);
            EditText1.setHint("Enter Lecturer Name");
            EditText1.setId(i);
            allEds.add(EditText1);
            tb.addView(EditText1);
            //subject id
            EditText EditText2 = new EditText(this);
            EditText2.setHint("Enter Subject");
            EditText2.setId(i+100);
            allEds.add(EditText2);
            tb.addView(EditText2);

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
    public void Next(View view)
    {
        String[] start = new String[(allEds.size())];
        for (int i = 0; i < allEds.size(); i++)
        {
            start[i] = allEds.get(i).getText().toString();

        }

        int j;
        for(i=0,j=0; i < allEds.size(); i=i+3,j++) {
			start[i + 1]=start[i + 1].toUpperCase();

            Public_status.db.execSQL("INSERT INTO TblTeacher(Teacher_id,Name,Subject_id)VALUES(" + start[i] + ",'" + start[i + 1] + "'," + start[i + 2] + ");");

        }
       /* for(i=0,j=0; i < allEds.size(); i=i+2,j++) {


            Public_status.db.execSQL("INSERT INTO TblTeacher(Name,Subject_id)VALUES('" + start[i] + "'," + start[i + 1] + ");");

        }*/
   Cursor c1= Public_status.db.rawQuery("SELECT * FROM TblTeacher", null);
        if(c1.getCount()==0)
        {
            showMessage("Error", "No records found");
            return;
        }
        StringBuffer bu=new StringBuffer();
        while(c1.moveToNext())
        {

            bu.append(" id     : "+c1.getString(0)+"\n");
            bu.append(" L name : "+c1.getString(1)+"\n");
            bu.append(" s name : "+c1.getString(2)+"\n");



        }
        c1.close();
        showMessage("Details",bu.toString());
        Toast.makeText(this, "done", Toast.LENGTH_LONG).show();
   /*
        Cursor c =  Public_status.db.rawQuery("SELECT * FROM TblTeacher", null);
        if (c.getCount() == 0) {
            showMessage("Error", "No records found");
            return;
        }
        StringBuffer b= new StringBuffer();
        while (c.moveToNext()) {

            b.append("id    :" + c.getString(0) + "\n");
            b.append("L NAME: " + c.getString(1) + "\n");
            b.append("sUB   :" + c.getString(2) + "\n");
            //bu.append("count" + c.getString(3) + "\n");
        }
        c.close();
        showMessage("Details", b.toString());

*/
       Intent intent = new Intent(this,Generate.class);
       intent.putExtra("nosub",nosub);
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
