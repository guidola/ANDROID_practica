package domel.ecampus.Base;

import android.support.v7.app.AppCompatActivity;

import domel.ecampus.MyApplication;

/**
 * Created by Guillermo on 10/5/16.
 */
public class BaseActivity extends AppCompatActivity {

    public MyApplication getApp(){
        return (MyApplication)this.getApplication();
    }



}
