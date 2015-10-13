package ua.shuba.departmentmanager.fragment;

import android.support.v4.app.Fragment;

import ua.shuba.departmentmanager.dialog.ProgressDialog;

public class BaseFragment extends Fragment {

    protected void showProgress() {
        ProgressDialog.newInstance("").show(getFragmentManager());
    }

    protected void showProgress(final String _message) {
        ProgressDialog.newInstance(_message).show(getFragmentManager());
    }

    protected void dismissProgress() {
        ProgressDialog dialog = findFragmentByTag(ProgressDialog.TAG);

        if (dialog != null) {
            dialog.dismiss();
        }
    }

    protected final <F extends Fragment> F findFragmentByTag(final String _tag){
        return (F) getFragmentManager().findFragmentByTag(_tag);
    }

}
