package ua.shuba.departmentmanager.fragment;

import android.app.Activity;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import ua.shuba.departmentmanager.R;
import ua.shuba.departmentmanager.firebase.FirebaseClient;

@EFragment(R.layout.fragment_login)
public class LogInFragment extends BaseFragment implements Firebase.AuthResultHandler {

    @ViewById(R.id.btnLogin_FL)             Button btnLogin;
    @ViewById(R.id.btnRegister_FL)          Button btnRegister;
    @ViewById(R.id.etEmail_FL)              EditText etEmail;
    @ViewById(R.id.etPassword_FL)           EditText etPassword;
    @ViewById(R.id.tilEmailLayout_FL)       TextInputLayout tilEmailLayout;
    @ViewById(R.id.tilLayoutPassword_FL)    TextInputLayout tilLayoutPassword;

    LoginCallbacks mListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        mListener = ((LoginCallbacks) activity);
    }

    @Click(R.id.btnLogin_FL)
    void onLoginClicked() {
        if (validateFields()) {
            showProgress();
            FirebaseClient.getClient().login(etEmail.getText().toString(),
                    etPassword.getText().toString(), this);
        }
    }

    private boolean validateFields() {
        final String email = etEmail.getText().toString();
        final String password = etPassword.getText().toString();

        if (email.isEmpty() || password.isEmpty()) {
            Snackbar.make(getView(), R.string.error_fill_all_fields, Snackbar.LENGTH_SHORT).show();
            return false;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Snackbar.make(getView(), R.string.error_invalid_email, Snackbar.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    @Click(R.id.btnRegister_FL)
    void onRegisterClicked() {
        mListener.onSignUp();
    }

    @Override
    public void onAuthenticated(AuthData _authData) {
        if (isAdded()) {
            dismissProgress();
            Snackbar.make(getView(), R.string.success_login, Snackbar.LENGTH_SHORT).show();
            mListener.onLoginSuccess();
        }
    }

    @Override
    public void onAuthenticationError(FirebaseError _firebaseError) {
        if (isAdded()) {
            dismissProgress();
            switch (_firebaseError.getCode()) {
                case FirebaseError.USER_DOES_NOT_EXIST:
                case FirebaseError.INVALID_PASSWORD:
                    Snackbar.make(getView(), R.string.error_wrong_credentials, Snackbar.LENGTH_SHORT).show();
                    break;
                default:
                    Snackbar.make(getView(), _firebaseError.getMessage(), Snackbar.LENGTH_SHORT).show();
                    break;
            }
        }
    }

    public interface LoginCallbacks {
        void onLoginSuccess();

        void onSignUp();
    }
}
