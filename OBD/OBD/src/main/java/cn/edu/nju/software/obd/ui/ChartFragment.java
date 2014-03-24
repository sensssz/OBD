package cn.edu.nju.software.obd.ui;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GraphViewSeries;
import com.jjoe64.graphview.LineGraphView;

import java.util.Calendar;

import cn.edu.nju.software.obd.R;


/**
 * Created by rogers on 3/18/14.
 */
public class ChartFragment extends Fragment {
    private TextView monthText;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chart, container, false);


        GraphViewSeries exampleSeries = new GraphViewSeries(new GraphView.GraphViewData[]{
                new GraphView.GraphViewData(1, 3.5d)
                , new GraphView.GraphViewData(2, 4.5d)
                , new GraphView.GraphViewData(3, 6.5d)
                , new GraphView.GraphViewData(4, 7.0d)
                , new GraphView.GraphViewData(5, 5.0d)
                , new GraphView.GraphViewData(6, 8.0d)
                , new GraphView.GraphViewData(7, 3.0d)
                , new GraphView.GraphViewData(8, 2.0d)
                , new GraphView.GraphViewData(9, 6.0d)
                , new GraphView.GraphViewData(10, 5.0d)
                , new GraphView.GraphViewData(11, 3.0d)
                , new GraphView.GraphViewData(12, 6.0d)
        });

        GraphView graphView = new LineGraphView(
                getActivity().getApplicationContext() // context
                , "" // heading
        );

        graphView.addSeries(exampleSeries); // data
        graphView.setHorizontalLabels(new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"});
        graphView.setHorizontalLabels(new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"});
        LinearLayout layout = (LinearLayout) view.findViewById(R.id.graph);
        layout.addView(graphView);

        monthText = (TextView)view.findViewById(R.id.chartTextView);
        Calendar calendar = Calendar.getInstance();
        monthText.setText(calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1));
        monthText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                DatePickerDialog datePickerDialog = createDialogWithoutDateField();
                datePickerDialog.show();

            }
        });
        return view;
    }
    private class mDateSetListener implements DatePickerDialog.OnDateSetListener  {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            monthText.setText(year + "-" + (monthOfYear + 1));
        }
    }

    private DatePickerDialog createDialogWithoutDateField(){
        Calendar c = Calendar.getInstance();
        DatePickerDialog dpd = new DatePickerDialog(getActivity(), new mDateSetListener(),
                c.get(Calendar.YEAR),c.get(Calendar.MONTH),c.get(Calendar.DAY_OF_MONTH));
        try{
            java.lang.reflect.Field[] datePickerDialogFields = dpd.getClass().getDeclaredFields();
            for (java.lang.reflect.Field datePickerDialogField : datePickerDialogFields) {
                if (datePickerDialogField.getName().equals("mDatePicker")) {
                    datePickerDialogField.setAccessible(true);
                    DatePicker datePicker = (DatePicker) datePickerDialogField.get(dpd);
                    java.lang.reflect.Field[] datePickerFields = datePickerDialogField.getType().getDeclaredFields();
                    for (java.lang.reflect.Field datePickerField : datePickerFields) {
                        if ("mDaySpinner".equals(datePickerField.getName())) {
                            datePickerField.setAccessible(true);
                            Object dayPicker = new Object();
                            dayPicker = datePickerField.get(datePicker);
                            ((View) dayPicker).setVisibility(View.GONE);
                        }
                    }
                }

            }
        }catch(Exception ex){
        }
        return dpd;

    }

}
