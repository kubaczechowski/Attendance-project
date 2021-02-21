package sample.gui.util;

import javafx.geometry.Insets;
import javafx.geometry.Side;
import javafx.scene.chart.PieChart;

/**
 * class is used when drawing charts
 * @author kuba
 */
public class Charts {

    public static PieChart getChart(int absDays, int presentDays,
                                    boolean labelsVisible, int minSize, int prefSize,
                                    Side side, Insets insets) {
        PieChart chart = new PieChart();
        PieChart.Data absent = new PieChart.Data("Absent", absDays);
        PieChart.Data present = new PieChart.Data("Present", presentDays);
        chart.getData().addAll(present, absent);
        chart.setLabelsVisible(labelsVisible);
        chart.setMinSize(minSize, minSize);
        chart.setPrefSize(prefSize, prefSize);
        chart.setLabelLineLength(10);
        chart.setLegendSide(side);
        chart.setId("Chart");
       if(insets!=null)
           chart.setPadding(insets);
        System.out.println(chart.getPrefHeight());
        return chart;
    }
}
