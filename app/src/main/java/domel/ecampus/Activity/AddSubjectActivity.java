package domel.ecampus.Activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;

import com.viewpagerindicator.CirclePageIndicator;
import com.viewpagerindicator.TitlePageIndicator;

import domel.ecampus.Adapters.AddStudentFragmentAdapter;
import domel.ecampus.Component.RestrictiveViewPager;
import domel.ecampus.Fragment.AddSubjectFirstStepFragment;
import domel.ecampus.Fragment.AddSubjectSecondStepFragment;
import domel.ecampus.Fragment.AddSubjectThirdStepFragment;
import domel.ecampus.Model.Subject;
import domel.ecampus.R;

public class AddSubjectActivity extends AppCompatActivity {

    private final static int LAST_PAGE = 2;
    private int last_page = -1;

    private RestrictiveViewPager pager;
    private AddStudentFragmentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_subject);

        //Set the pager with an adapter
        pager = (RestrictiveViewPager) findViewById(R.id.pager);
        if (pager != null) {
            adapter= new AddStudentFragmentAdapter(getSupportFragmentManager());
            pager.setAdapter(adapter);
        }

        if (pager != null) {
            pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    Log.d("position", Integer.toString(position));
                    Log.d("last_position", Integer.toString(last_page));
                    if(positionOffset == 0 && positionOffsetPixels == 0 && last_page != position) {

                        switch (position) {

                            case 0:
                                Log.d("inputmethod", "SHOW");
                                imm.toggleSoftInputFromWindow(pager.getWindowToken(), 0, 0);
                                break;
                            case 1:
                                Log.d("inputmethod", "HIDE");
                                imm.hideSoftInputFromWindow(pager.getWindowToken(), 0);
                                break;
                            case 2:
                                Log.d("inputmethod", "HIDE");
                                imm.hideSoftInputFromWindow(pager.getWindowToken(), 0);
                                break;

                        }
                        last_page = position;
                        Log.d("position_modified_to", Integer.toString(last_page));

                    }else if (position == LAST_PAGE && last_page == LAST_PAGE){
                        //submit the data and close the application
                        submitFormData();
                    }
                }

                @Override
                public void onPageSelected(int position) {

                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });

            pager.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {



                    if(pager.getCurrentItem() == 0){

                        AddSubjectFirstStepFragment f = (AddSubjectFirstStepFragment) adapter.getRegisteredFragment(0);
                        AppCompatEditText name = (AppCompatEditText) f.getView().findViewById(R.id.subject_name);
                        AppCompatEditText description = (AppCompatEditText) f.getView().findViewById(R.id.subject_description);

                        if(name.getText().length() == 0){
                            name.setError(getString(R.string.error_field_required));
                            name.requestFocus();
                            pager.setAllow_swipe(false);
                            return false;
                        }else if(description.getText().length() == 0){
                            description.setError(getString(R.string.error_field_required));
                            description.requestFocus();
                            pager.setAllow_swipe(false);
                            return false;
                        }else{
                            pager.setAllow_swipe(true);
                            return false;
                        }
                    }

                    return false;
                }
            });
        }

        //Bind the title indicator to the adapter
        CirclePageIndicator titleIndicator = (CirclePageIndicator)findViewById(R.id.pagination);
        if (titleIndicator != null) {
            titleIndicator.setViewPager(pager);
        }

        //logout button
        ImageView closeSesionButton = (ImageView) findViewById(R.id.close);
        if (closeSesionButton != null) {
            closeSesionButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    //logout
                    Intent intent = new Intent(AddSubjectActivity.this, LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();

                }

            });
        }


    }

    public void submitFormData(){

        AddSubjectFirstStepFragment firstStepFragment =  (AddSubjectFirstStepFragment) adapter.getRegisteredFragment(0);
        AddSubjectSecondStepFragment secondStepFragment =  (AddSubjectSecondStepFragment) adapter.getRegisteredFragment(1);
        AddSubjectThirdStepFragment thirdStepFragment =  (AddSubjectThirdStepFragment) adapter.getRegisteredFragment(2);

        Subject subject = new Subject();

        //process data from first fragment

        //process data from second fragment

        //process data from third fragment


    }

}
