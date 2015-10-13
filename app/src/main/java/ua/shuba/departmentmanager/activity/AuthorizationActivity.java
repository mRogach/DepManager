package ua.shuba.departmentmanager.activity;

import android.os.Bundle;

import org.androidannotations.annotations.EActivity;

import ua.shuba.departmentmanager.R;
import ua.shuba.departmentmanager.fragment.LogInFragment.LoginCallbacks;
import ua.shuba.departmentmanager.fragment.LogInFragment_;
import ua.shuba.departmentmanager.fragment.RegisterFragment.RegisterFragmentListener;
import ua.shuba.departmentmanager.fragment.RegisterFragment_;

@EActivity
public class AuthorizationActivity extends BaseFragmentActivity
        implements RegisterFragmentListener, LoginCallbacks {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authorization);

        if (getFragmentManager().findFragmentById(getContainerId()) == null) {
            replaceFragmentWithOutBackStack(LogInFragment_.builder().build());
        }
    }

    @Override
    protected int getContainerId() {
        return R.id.flContainer_AA;
    }

    @Override
    public void onRegisterSuccess() {
        popBackStack();
    }

    @Override
    public void onLoginSuccess() {

    }

    @Override
    public void onSignUp() {
        replaceFragmentWithBackStack(RegisterFragment_.builder().build());
    }
}
