package com.example.sois.timetablescheduler;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;

public class Add_Batch extends AppCompatActivity {

    EditText txt1;
    Button b;
    int i;
    //SQLiteDatabase  Public_status.db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__batch);
        txt1 = (EditText) findViewById(R.id.txtno);
        b = (Button) findViewById(R.id.bnext);
        Public_status.db = openOrCreateDatabase("TIMETABLE",Context.MODE_PRIVATE, null);
        Cursor c1= Public_status.db.rawQuery("SELECT * FROM TblTime", null);
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
        showMessage("Details",bu.toString());

    }
    public void onBackPressed()
    {
        finish();
        Intent intent = new Intent(getApplicationContext(), Main.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("EXIT", true);
        startActivity(intent);
    }
    /* public  void Next(View view) {
         Intent intent = new Intent(this,Add_Batch.class);
         startActivity(intent);

     }*/
    public void Submit(View view) {

        //  EditText e =createEditText();

        String value = txt1.getText().toString();
        int no = Integer.parseInt(value);

        int i, j, k;
        final TableLayout tb = (TableLayout) findViewById(R.id.tbl);
        // final ScrollView tb = (ScrollView) findViewById(R.id.tbl);
        //   final LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linear_layout);
        for (i = 0; i < no; i++) {
//add lecture id
            EditText EditText1 = new EditText(Add_Batch.this);
            EditText1.setHint("Enter the Course Name");
            EditText1.setId(i);
            tb.addView(EditText1);
//add lecture name

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
