package com.example.admin.assignment3;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RetrieveEntryActivity extends AppCompatActivity {

    private DBHelper myDb;
    EditText getRollNo;
    TextView name;
    TextView rollNo;
    TextView cur_sem;
    Button retrieve;
    Button delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_retrieve_entry);

        myDb=new DBHelper(this);
        getRollNo=(EditText)findViewById(R.id.getRollNo);
        name=(TextView)findViewById(R.id.name);
        rollNo=(TextView) findViewById(R.id.roll_no);
        cur_sem=(TextView) findViewById(R.id.cur_sem);
        retrieve=(Button)findViewById(R.id.retrieveButton);
        delete=(Button)findViewById(R.id.deleteButton);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(getRollNo.getText().toString()!=null)
                {
                    if(myDb.deleteEntry(getRollNo.getText().toString()))
                    {
                        Toast.makeText(getApplicationContext(),"Entry Deleted",Toast.LENGTH_SHORT).show();
                        name.setText("");
                        rollNo.setText("");
                        cur_sem.setText("");
                    }
                    else
                        Toast.makeText(getApplicationContext(),"Entry not found",Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(getApplicationContext(),"Please enter a roll no",Toast.LENGTH_SHORT).show();
            }
        });

        retrieve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(getRollNo.getText().toString()!=null)
                {
                    Cursor cursor=myDb.retrieveEntry(getRollNo.getText().toString());
                    if(cursor.getCount()==0)
                        Toast.makeText(getApplicationContext(),"Entry not found",Toast.LENGTH_SHORT).show();
                    else
                    {
                        cursor.moveToFirst();
                        rollNo.setText(cursor.getString(0));
                        name.setText(cursor.getString(1));
                        cur_sem.setText(cursor.getString(2));
                    }
                }
                else
                    Toast.makeText(getApplicationContext(),"Please enter a roll no",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
