package edu.ucsb.cs.cs185.easytrade;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

/**
 * Created by wengzixia on 6/6/15.
 */
public class EditFragment extends DialogFragment {

    private EditText edit1;
    private EditText edit2;
    private EditText edit3;
    private EditText edit4;



    public interface EditLicenseDialogListener {
        public void onDialogPositiveClick(DialogFragment dialog, String value1, String value2, String value3, String value4);

    }

    private EditLicenseDialogListener mListener;

    // Override the Fragment.onAttach() method to instantiate the NoticeDialogListener
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            mListener = (EditLicenseDialogListener) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement NoticeDialogListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("Edit Your Profile");

        LayoutInflater inflater = getActivity().getLayoutInflater();

        final View view = inflater.inflate(R.layout.edit_profile_fragment, null);

        edit1 = (EditText) view.findViewById(R.id.txt_team1);
        edit2 = (EditText) view.findViewById(R.id.txt_score1);
        edit3 = (EditText) view.findViewById(R.id.txt_team2);
        edit4 = (EditText) view.findViewById(R.id.txt_score2);

        edit1.setHint("GiantJason");
        edit2.setHint("8057083605");
        edit3.setHint("jasonG@gmail.com");
        edit4.setHint("6621 Abrego Rd., Apt.303");

        edit1.requestFocus();


        builder.setView(view)
                .setPositiveButton("Done", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {


                        String value1 = edit1.getText().toString();
                        String value2 = edit2.getText().toString();
                        String value3 = edit3.getText().toString();
                        String value4 = edit4.getText().toString();

                        if (value2.length() >= 10) {
                            value2 = new StringBuilder(value2).insert(0, "(").toString();
                            value2 = new StringBuilder(value2).insert(4, ")").toString();
                            value2 = new StringBuilder(value2).insert(8, "-").toString();
                        }


                        if (value1.equals("")) {
                            value1 = "";
                        }
                        if (value2.equals("")) {
                            value2 = "";
                        }
                        if (value3.equals("")) {
                            value3 = "";
                        }
                        if (value4.equals("")) {
                            value4 = "";
                        }

                        mListener.onDialogPositiveClick(EditFragment.this, value1, value2, value3, value4);
                    }

                });
        return builder.create();

    }
}