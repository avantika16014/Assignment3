package layout;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.admin.assignment3.NewEntryActivity;
import com.example.admin.assignment3.R;
import com.example.admin.assignment3.RetrieveEntryActivity;
import com.example.admin.assignment3.UpdateEntryActivity;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Fragment3.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Fragment3#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment3 extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // TODO: Rename and change types of parameters
    private OnFragmentInteractionListener mListener;

    Button newEntry;
    Button updateEntry;
    Button retrieveEntry;

    public Fragment3() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static Fragment3 newInstance() {
        Fragment3 fragment = new Fragment3();
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
        View fragmentView3= inflater.inflate(R.layout.fragment_fragment3, container, false);
        newEntry=(Button)fragmentView3.findViewById(R.id.newEntry);
        retrieveEntry=(Button)fragmentView3.findViewById(R.id.retrieveEntry);
        updateEntry=(Button)fragmentView3.findViewById(R.id.updateEntry);
        newEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), NewEntryActivity.class);
                startActivity(intent);
            }
        });
        retrieveEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), RetrieveEntryActivity.class);
                startActivity(intent);
            }
        });
        updateEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), UpdateEntryActivity.class);
                startActivity(intent);
            }
        });
        return fragmentView3;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }


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
