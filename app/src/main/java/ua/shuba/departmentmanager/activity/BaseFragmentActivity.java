package ua.shuba.departmentmanager.activity;

import android.app.FragmentManager;
import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.view.inputmethod.InputMethodManager;

import ua.shuba.departmentmanager.fragment.BaseFragment;

public abstract class BaseFragmentActivity extends BaseActivity {

    private InputMethodManager mInputMethodManager;

    @IdRes
    protected abstract int getContainerId();

    protected final void addFragmentWithOutBackStack(final @NonNull BaseFragment _fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .add(getContainerId(), _fragment)
                .commit();
    }

    public final void replaceFragment(final @NonNull BaseFragment _fragment, final boolean _useBackstack) {
        if (_useBackstack) {
            replaceFragmentWithBackStack(_fragment);
        } else {
            replaceFragmentWithOutBackStack(_fragment);
        }
    }

    public final void replaceFragmentWithBackStack(final @NonNull BaseFragment _fragment) {
        hideKeyboard();
        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack(null)
                .replace(getContainerId(), _fragment)
                .commit();
    }

    public final void replaceFragmentWithOutBackStack(final @NonNull BaseFragment _fragment) {
        hideKeyboard();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(getContainerId(), _fragment)
                .commit();
    }

    public final void clearBackStack() {
        getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

    public final void popBackStack() {
        getSupportFragmentManager().popBackStack();
        hideKeyboard();
    }

    protected final void hideKeyboard() {
        if (mInputMethodManager == null) {
            mInputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        }
        mInputMethodManager.hideSoftInputFromWindow(findView(getContainerId()).getWindowToken(), 0);
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
            hideKeyboard();
        } else if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            popBackStack();
            hideKeyboard();
        } else {
            finish();
        }
    }
}
