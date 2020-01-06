package com.example.sois.timetablescheduler;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.icu.text.SimpleDateFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static android.R.id.edit;
import android.widget.TimePicker;
public class Batch_Add extends AppCompatActivity {
    EditText txt1,txtbreak;
    Button b;
    int i,no;
    int lunch;
    String value,breakvalue;
    List<EditText> allEds = new ArrayList<EditText>();
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        try {
            setContentView(R.layout.activity_batch__add);
        }
        catch (Exception e)
        {
            String strM = e.getMessage();
            e.printStackTrace();
        }

        txt1 = (EditText)findViewById(R.id.txtno);
        txtbreak = (EditText)findViewById(R.id.txtbreak);
        Public_status.db=openOrCreateDatabase("TIMETABLE", Context.MODE_PRIVATE, null);
        b = (Button) findViewById(R.id.bnext);
    }
    public void onBackPressed()
    {
        finish();
        Intent intent = new Intent(getApplicationContext(), Main.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("EXIT", true);
        startActivity(intent);
    }

    public  void Next(View view)
    {
        String[] start = new String[(allEds.size())];
        for(int i=0; i < allEds.size(); i++)
        {
            start[i] = allEds.get(i).getText().toString();
        }
        int j,i;
        for(i=0,j=0; i < allEds.size(); i=i+2,j++)
        {

            int brk = 0;
            if (j == lunch) {
                brk = 1;
            }
            Public_status.db.execSQL("INSERT INTO TblTime(StartTime,EndTime,LunchBreak) VALUES ('" + start[i] + "','" + start[i + 1] + "'," + brk + ");");
        }
        Toast.makeText(this,"Inserted",Toast.LENGTH_LONG).show();

        Cursor c= Public_status.db.rawQuery("SELECT * FROM TblTime", null);
        if(c.getCount()==0)
        {
            showMessage("Error", "No records found");
            return;
        }
        StringBuffer bu=new StringBuffer();
        while(c.moveToNext())
        {

            bu.append("id"+c.getString(0)+"\n");
            bu.append("start"+c.getString(1)+"\n");
            bu.append("end"+c.getString(2)+"\n");
            bu.append("break"+c.getString(3)+"\n");


        } c.close();
        showMessage("Details",bu.toString());


      Intent intent = new Intent(this,Add_day.class);
        startActivity(intent);
    }
    public  void Submit(View view)
    {
        if(txt1.getText().toString() == "" || txtbreak.getText().toString()=="")
        {
            Toast.makeText(this,"Enter All the Fields",Toast.LENGTH_LONG).show();

        }
        value= txt1.getText().toString();
        no=Integer.parseInt(value);

        breakvalue= txtbreak.getText().toString();
        lunch=Integer.parseInt(breakvalue);
        //lunch=1;

        final TableLayout tb = (TableLayout) findViewById(R.id.tbl);
        // final ScrollView tb = (ScrollView) findViewById(R.id.tbl);
        //   final LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linear_layout);
        for(i=0;i<no;i++)
        {

            EditText EditText1 = new EditText(Batch_Add.this);
            EditText1.setHint("Enter start time");
            //EditText1.setInputType(InputType.TYPE_DATETIME_VARIATION_TIME);
            EditText1.setId( i+100 );
            allEds.add(EditText1);
            EditText1.setInputType(InputType.TYPE_DATETIME_VARIATION_TIME);
            tb.addView(EditText1);

            EditText EditText2 = new EditText(Batch_Add.this);
            allEds.add(EditText2);
            //  EditText1.setText("TextView " + i);
            EditText2.setHint("Enter end time");
            EditText2.setInputType(InputType.TYPE_DATETIME_VARIATION_TIME);
            // EditText2.setMaxWidth(5);
            EditText2.getCompoundPaddingBottom();
            EditText2.setId(i+200);
            //  EditText2.setLayoutParams(layoutparams);
            tb.addView(EditText2);

        }
        b.setVisibility(View.VISIBLE);
        //  counter++;

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
