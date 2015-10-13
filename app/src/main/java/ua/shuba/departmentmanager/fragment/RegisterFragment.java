package ua.shuba.departmentmanager.fragment;

import android.app.Activity;
import android.support.design.widget.Snackbar;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import ua.shuba.departmentmanager.R;
import ua.shuba.departmentmanager.firebase.FirebaseClient;
import ua.shuba.departmentmanager.global.Constants;

@EFragment(R.layout.fragment_register)
public class RegisterFragment extends BaseFragment {

    @ViewById(R.id.etEmail_FR)              EditText etEmail;
    @ViewById(R.id.etPassword_FR)           EditText etPassword;
    @ViewById(R.id.etConfirmPassword_FR)    EditText etConfirmPassword;
    @ViewById(R.id.btnRegister_FR)          Button btnRegister;

    private RegisterFragmentListener mListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (RegisterFragmentListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement RegisterFragmentListener");
        }
    }

    @Click(R.id.btnRegister_FR)
    void onRegisterClicked() {
        if (validateFields()) {
            showProgress();
            FirebaseClient.getClient().registerUser(etEmail.getText().toString(),
                    etPassword.getText().toString(),
                    new Firebase.ResultHandler() {
                        @Override
                        public void onSuccess() {
                            if (isAdded()) {
                                dismissProgress();
                                mListener.onRegisterSuccess();
                                Snackbar.make(getView(), R.string.success_register,
                                        Snackbar.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onError(FirebaseError _firebaseError) {
                            if (isAdded()) {
                                dismissProgress();
                                Snackbar.make(getView(), _firebaseError.getMessage(),
                                        Snackbar.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }

    private boolean validateFields() {
        final String email = etEmail.getText().toString();
        final String password = etPassword.getText().toString();
        final String confirmPassword = etConfirmPassword.getText().toString();

        if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            Snackbar.make(getView(), R.string.error_fill_all_fields, Snackbar.LENGTH_SHORT).show();
            return false;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Snackbar.make(getView(), R.string.error_invalid_email, Snackbar.LENGTH_SHORT).show();
            return false;
        }

        if (password.length() < Constants.MIN_PASS_LENGTH) {
            Snackbar.make(getView(), R.string.error_password_short, Snackbar.LENGTH_SHORT).show();
            return false;
        }

        if (password.length() > Constants.MAX_PASS_LENGTH) {
            Snackbar.make(getView(), R.string.error_password_long, Snackbar.LENGTH_SHORT).show();
            return false;
        }

        if (!password.equals(confirmPassword)) {
            Snackbar.make(getView(), R.string.error_confirm_password, Snackbar.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface RegisterFragmentListener {
        void onRegisterSuccess();
    }

}
