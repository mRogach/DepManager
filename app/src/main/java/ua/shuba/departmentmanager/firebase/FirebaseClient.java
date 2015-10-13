package ua.shuba.departmentmanager.firebase;

import android.support.annotation.NonNull;

import com.firebase.client.Firebase;
import com.firebase.client.Firebase.AuthResultHandler;
import com.firebase.client.Firebase.ResultHandler;

/**
 * Created by Vitaliy on 10/10/2015.
 */
public class FirebaseClient {

    public static final String FIRE_BASE_URL = "https://department-manager.firebaseio.com";

    private static FirebaseClient mInstance;
    private Firebase mRef;

    public static FirebaseClient getClient() {
        if (mInstance == null) {
            mInstance = new FirebaseClient();
        }
        return mInstance;
    }

    private FirebaseClient() {
        mRef = new Firebase(FIRE_BASE_URL);
    }

    public void registerUser(final String _email, final String _password,
                                    final ResultHandler _resultHandler) {
        mRef.createUser(_email, _password, _resultHandler);
    }

    public void login(@NonNull final String _email,
                      @NonNull final String _password,
                      @NonNull final AuthResultHandler _handler) {
        mRef.authWithPassword(_email, _password, _handler);
    }
}
