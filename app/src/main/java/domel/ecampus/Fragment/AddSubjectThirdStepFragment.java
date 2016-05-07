package domel.ecampus.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import domel.ecampus.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddSubjectThirdStepFragment extends Fragment {


    public AddSubjectThirdStepFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_subject_third_step, container, false);
    }

}
