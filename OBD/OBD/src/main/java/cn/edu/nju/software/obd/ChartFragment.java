package cn.edu.nju.software.obd;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.jjoe64.graphview.*;


/**
 * Created by rogers on 3/18/14.
 */
public class ChartFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chart, container, false);


        GraphViewSeries exampleSeries = new GraphViewSeries(new GraphView.GraphViewData[] {
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
        graphView.setHorizontalLabels(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"});
        graphView.setHorizontalLabels(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"});
        LinearLayout layout = (LinearLayout)view.findViewById(R.id.graph);
        layout.addView(graphView);
        return view;
    }


}
