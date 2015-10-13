package ua.shuba.departmentmanager.dialog;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ua.shuba.departmentmanager.R;

public final class ProgressDialog extends DialogFragment {

    
    private static final String KEY_PROGRESS_MESSAGE = "PROGRESS_MESSAGE";

    public static String TAG = ProgressDialog.class.getSimpleName();

    public static ProgressDialog newInstance(final String _title) {
        ProgressDialog dialog = new ProgressDialog();
        Bundle bundle = new Bundle();
        bundle.putString(KEY_PROGRESS_MESSAGE, _title);
        dialog.setArguments(bundle);
        return dialog;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.MyDialog);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        return inflater.inflate(R.layout.dialog_progress, container, false);
    }

//    @Override
//    public Dialog onCreateDialog(Bundle savedInstanceState) {
//        Bundle bundle = getArguments();
//        Dialog dialog = new Dialog(getActivity());
//        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
//        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
//        final View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_progress, null, false);
//        dialog.setContentView(view);
//        return dialog;
//    }

    public void show(FragmentManager _manager) {
        show(_manager, TAG);
    }

    @Override
    public void onCancel(DialogInterface dialog) {

        dismiss();
    }

    @Override
    public void onDismiss(DialogInterface dialog) {/*not implemented*/}
}
