package com.smartlines.buhwarfull.colon.ui.statistics;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.smartlines.buhwarfull.R;

import java.util.ArrayList;

public class StatisticsFragment extends Fragment {

    BarChart pieChart;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_statistics, container, false);
        pieChart = (BarChart) root.findViewById(R.id.grafico_pastel);
        fillGraphic();
        return root;
    }

    private void fillGraphic() {
        Description description = new Description();
        description.setText("Progreso mensual");
        description.setTextSize(28
        );

        pieChart.setDescription(description);

        ArrayList<BarEntry> pieEntrys = new ArrayList<BarEntry>();
        pieEntrys.add(new BarEntry(1, 2, "Octubre"));
        pieEntrys.add(new BarEntry(2, 3));
        pieEntrys.add(new BarEntry(3, 4));
        pieEntrys.add(new BarEntry(4, 5));
        BarDataSet pieDataSet = new BarDataSet(pieEntrys, "Meses");
        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);

        BarData pieData = new BarData(pieDataSet);

        pieChart.setData(pieData);
    }


}
