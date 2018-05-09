package kr.ac.yjc.wdj.capstondesignproject;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;




public class SeasonPickerDialog extends DialogFragment {

    public void setListener(DatePickerDialog.OnDateSetListener listener) {
        this.listener = listener;
    }



    SimpleDateFormat forYear = new SimpleDateFormat("yyyy");
    String getYear = forYear.format(new Date(System.currentTimeMillis()));


    private final int MAX_YEAR = Integer.parseInt(getYear);
    private final int MIN_YEAR = 2000;                              // 서버에서 해당 학생의 입학년도 받아야됨

    private DatePickerDialog.OnDateSetListener listener;
    public Calendar cal = Calendar.getInstance();


    Button btnConfirm;
    Button btnCancel;



    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View dialog = inflater.inflate(R.layout.season_picker, null);

        btnConfirm = dialog.findViewById(R.id.btn_confirm);
        btnCancel = dialog.findViewById(R.id.btn_cancel);

        final NumberPicker seasonPicker = (NumberPicker) dialog.findViewById(R.id.picker_season);
        final NumberPicker yearPicker = (NumberPicker) dialog.findViewById(R.id.picker_year);

        btnCancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                SeasonPickerDialog.this.getDialog().cancel();
            }
        });

        btnConfirm.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                listener.onDateSet(null, yearPicker.getValue(), seasonPicker.getValue(), 0);
                SeasonPickerDialog.this.getDialog().cancel();
            }
        });


        seasonPicker.setMinValue(1);
        seasonPicker.setMaxValue(2);
        seasonPicker.setValue(1);
        seasonPicker.setValue(2);

        int year = cal.get(Calendar.YEAR);
        yearPicker.setMinValue(MIN_YEAR);
        yearPicker.setMaxValue(MAX_YEAR);
        yearPicker.setValue(year);


        builder.setView(dialog);

        return builder.create();
    }
}
