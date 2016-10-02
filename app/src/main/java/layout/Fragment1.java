package layout;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.admin.assignment3.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Fragment1.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Fragment1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment1 extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
   // private static final String ARG_PARAM1 = "param1";
   // private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    //private String mParam1;
    //private String mParam2;
    EditText name;
    EditText roll_no;
    EditText cur_sem;
    Button saveDetails;
    Button resetDetails;

    private OnFragmentInteractionListener mListener;

    public Fragment1() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static Fragment1 newInstance() {
        Fragment1 fragment = new Fragment1();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //if (getArguments() != null) {
            //mParam1 = getArguments().getString(ARG_PARAM1);
            //mParam2 = getArguments().getString(ARG_PARAM2);
        //}
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragmentView1= inflater.inflate(R.layout.fragment_fragment1, container, false);
        name=(EditText)fragmentView1.findViewById(R.id.name);
        roll_no=(EditText)fragmentView1.findViewById(R.id.roll_no);
        cur_sem=(EditText)fragmentView1.findViewById(R.id.cur_sem);
        saveDetails=(Button)fragmentView1.findViewById(R.id.saveButton);
        resetDetails=(Button)fragmentView1.findViewById(R.id.resetButton);

        saveDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences=Fragment1.this.getActivity().getSharedPreferences(getString(R.string.PREF_FILE),Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString(getString(R.string.NAME),name.getText().toString());
                editor.putString(getString(R.string.ROLL_NO),roll_no.getText().toString());
                editor.putString(getString(R.string.CUR_SEM),cur_sem.getText().toString());
                editor.commit();
                Toast.makeText(getActivity(),"Details saved successfully",Toast.LENGTH_SHORT).show();
            }
        });

        resetDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name.setText("");
                roll_no.setText("");
                cur_sem.setText("");
                //Toast.makeText(getActivity(),"Reset:1",Toast.LENGTH_SHORT).show();
            }
        });

        return fragmentView1;
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
}
