package ua.shuba.departmentmanager.activity;

import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public abstract class BaseActivity extends AppCompatActivity {

    public final <T extends View> T findView(@IdRes int id) {
        return (T) findViewById(id);
    }
}
