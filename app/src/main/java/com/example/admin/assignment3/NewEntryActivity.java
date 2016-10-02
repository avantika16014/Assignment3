package com.example.admin.assignment3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NewEntryActivity extends AppCompatActivity {

    private DBHelper myDb;
    EditText name;
    EditText roll_no;
    EditText cur_sem;
    Button save;
    Button reset;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_entry);
        name=(EditText)findViewById(R.id.name);
        roll_no=(EditText)findViewById(R.id.roll_no);
        cur_sem=(EditText)findViewById(R.id.cur_sem);
        save=(Button)findViewById(R.id.saveButton);
        reset=(Button)findViewById(R.id.resetButton);
        myDb=new DBHelper(this);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name.setText("");
                roll_no.setText("");
                cur_sem.setText("");
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(name.getText()!=null && cur_sem.getText()!=null && roll_no.getText()!=null)
                {
                    if(myDb.retrieveEntry(roll_no.getText().toString()).getCount()==0) {
                        if (myDb.insertEntry(roll_no.getText().toString(), name.getText().toString(), cur_sem.getText().toString())) {
                            Toast.makeText(getApplicationContext(), "Details inserted into database", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Couldn't insert into database", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                        Toast.makeText(getApplicationContext(),"Roll No already exists",Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(getApplicationContext(),"Please fill all the sections",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
