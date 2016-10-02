package com.example.admin.assignment3;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UpdateEntryActivity extends AppCompatActivity {

    private DBHelper myDb;
    EditText getRollNo;
    TextView rollNo;
    EditText name;
    EditText cur_sem;
    Button update;
    Button reset;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_entry);

        myDb=new DBHelper(this);
        getRollNo=(EditText)findViewById(R.id.getRollNo);
        rollNo=(TextView)findViewById(R.id.roll_no);
        name=(EditText)findViewById(R.id.name);
        cur_sem=(EditText)findViewById(R.id.cur_sem);
        update=(Button)findViewById(R.id.updateButton);
        reset=(Button)findViewById(R.id.resetButton);

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name.setText("");
                cur_sem.setText("");
                rollNo.setText("");
                getRollNo.setText("");
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(getRollNo.getText().toString()!=null)
                {
                    rollNo.setText(getRollNo.getText().toString());
                    if(myDb.retrieveEntry(rollNo.getText().toString()).getCount()==0)
                    {
                        Toast.makeText(getApplicationContext(),"Entry not found",Toast.LENGTH_SHORT).show();
                        getRollNo.setText("");
                        rollNo.setText("");
                    }
                    else
                    {
                        if(name.getText().toString().equals("") || cur_sem.getText().toString().equals(""))
                            Toast.makeText(getApplicationContext(),"Please fill in new details",Toast.LENGTH_SHORT).show();
                        else
                        {
                            myDb.updateEntry(rollNo.getText().toString(), name.getText().toString(), cur_sem.getText().toString());
                            Toast.makeText(getApplicationContext(), "Entry Updated", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                else
                    Toast.makeText(getApplicationContext(),"Enter a Roll No",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
