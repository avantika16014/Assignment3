package layout;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.admin.assignment3.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Fragment1.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Fragment1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment2 extends Fragment {
    EditText name;
    EditText roll_no;
    EditText cur_sem;
    EditText saveAs;
    Button saveDetails;
    Button resetDetails;
    RadioButton internal;
    RadioButton publicStorage;
    RadioButton external;
    Button readFile;

    public Fragment2() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static Fragment2 newInstance() {
        Fragment2 fragment = new Fragment2();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragmentView2= inflater.inflate(R.layout.fragment_fragment2, container, false);
        name=(EditText)fragmentView2.findViewById(R.id.name);
        roll_no=(EditText)fragmentView2.findViewById(R.id.roll_no);
        cur_sem=(EditText)fragmentView2.findViewById(R.id.cur_sem);
        saveDetails=(Button)fragmentView2.findViewById(R.id.saveButton);
        readFile=(Button)fragmentView2.findViewById(R.id.readFileButton);
        publicStorage=(RadioButton)fragmentView2.findViewById(R.id.publicStorage);
        resetDetails=(Button)fragmentView2.findViewById(R.id.resetButton);
        internal=(RadioButton)fragmentView2.findViewById(R.id.internal);
        external=(RadioButton)fragmentView2.findViewById(R.id.external);
        saveAs=(EditText)fragmentView2.findViewById(R.id.saveAs);

        saveDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!(name.getText().toString().equals("") || roll_no.getText().equals("") || cur_sem.getText().equals("") || saveAs.getText().toString().equals(""))) {
                    if (internal.isChecked()) {
                        try {
                            writeToInternal();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else if (external.isChecked()) {
                        if (externalStorageWritable()) {
                            try {
                                writeToExternal();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    else if(publicStorage.isChecked())
                    {
                        if (externalStorageWritable()) {
                            try {
                                writeToExternalPublic();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    Toast.makeText(getActivity(), "File saved successfully", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getActivity(), "Please fill in all the entries", Toast.LENGTH_SHORT).show();
                }
            }
        });

        resetDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name.setText("");
                roll_no.setText("");
                cur_sem.setText("");
                internal.setChecked(false);
                external.setChecked(false);
                saveAs.setText("");
            }
        });

        readFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (internal.isChecked()) {
                    try {
                        readFromInternal();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                else if (external.isChecked())
                {
                    if (externalStorageWritable())
                    {
                        try
                        {
                            readFromExternal();
                        }
                        catch (Exception e)
                        {
                            e.printStackTrace();
                        }
                    }
                }
                else if(publicStorage.isChecked())
                {

                }
            }
        });

        return fragmentView2;
    }

    // TODO: Rename method, update argument and hook method into UI event

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
    public void writeToInternal()
    {
        try {
            FileOutputStream fos =  getActivity().openFileOutput(saveAs.getText().toString(), Context.MODE_PRIVATE);
            String concatenated = name.getText().toString() + "\n" + roll_no.getText().toString() + "\n" + cur_sem.getText().toString() + "\n";
            fos.write(concatenated.getBytes());
            fos.close();
            Toast.makeText(getContext(), "File saved successfully", Toast.LENGTH_SHORT).show();
            name.setText("");
            roll_no.setText("");
            cur_sem.setText("");
            internal.setChecked(false);
            external.setChecked(false);
            saveAs.setText("");

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public boolean externalStorageWritable(){
        try
        {
            String state=Environment.getExternalStorageState();
            if(Environment.MEDIA_MOUNTED.equals(state))
                return true;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }

    public void writeToExternal()
    {
        try
        {
            File dir=Environment.getExternalStorageDirectory();
            File path=new File(dir.getAbsolutePath());
            path.mkdir();
            File file=new File(path,saveAs.getText().toString());
            FileOutputStream fos=new FileOutputStream(file);
            String concatenated=name.getText().toString()+" "+roll_no.getText().toString()+" " +cur_sem.getText().toString()+"\n";
            fos.write(concatenated.getBytes());
            fos.close();
            Toast.makeText(getActivity(),"File saved successfully",Toast.LENGTH_SHORT).show();
            name.setText("");
            roll_no.setText("");
            cur_sem.setText("");
            internal.setChecked(false);
            external.setChecked(false);
            saveAs.setText("");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void writeToExternalPublic()
    {
        try
        {
            File dir=Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
            File path=new File(dir.getAbsolutePath());
            path.mkdir();
            File file=new File(path,saveAs.getText().toString());
            FileOutputStream fos=new FileOutputStream(file);
            String concatenated=name.getText().toString()+" "+roll_no.getText().toString()+" " +cur_sem.getText().toString()+"\n";
            fos.write(concatenated.getBytes());
            fos.close();
            Toast.makeText(getActivity(),"File saved successfully",Toast.LENGTH_SHORT).show();
            name.setText("");
            roll_no.setText("");
            cur_sem.setText("");
            internal.setChecked(false);
            external.setChecked(false);
            saveAs.setText("");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public void readFromInternal()
    {
        try{
            FileInputStream fis=getActivity().openFileInput(saveAs.getText().toString());
            Scanner s=new Scanner(fis);
            name.setText(s.next());
            roll_no.setText(s.next());
            cur_sem.setText(s.next());
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public void readFromExternal()
    {

}
}
