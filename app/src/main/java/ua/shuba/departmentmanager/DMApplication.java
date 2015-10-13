package ua.shuba.departmentmanager;

import android.app.Application;

import com.firebase.client.Firebase;

public class DMApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
    }
}
