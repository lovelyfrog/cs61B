/**
 * Created by hug.
 */

import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Experiments {
    public static void experiment1() {
        Random r = new Random();
        List<Double> yValues = new ArrayList<>();
        List<Double> y2Values = new ArrayList<>();
        List<Integer> xValues = new ArrayList<>();
        BST<Double> tmp = new BST<Double>();
        for (int i=1; i<5000; i++) {
            tmp.add(r.nextDouble());
            xValues.add(i);
            yValues.add(tmp.averageDepth());
            y2Values.add(ExperimentHelper.optimalAverageDepth(i));
        }

        XYChart chart = new XYChartBuilder().width(800).height(600).xAxisTitle("x label").yAxisTitle("y label").build();
        chart.addSeries("random", xValues, yValues);
        chart.addSeries("optimal", xValues, y2Values);

        new SwingWrapper(chart).displayChart();
    }

    public static void experiment2() {
        Random r = new Random();
        List<Double> yValues = new ArrayList<>();
        List<Integer> xValues = new ArrayList<>();
        BST<Double> tmp = new BST<Double>();
        ExperimentHelper help = new ExperimentHelper();
        for (int i=1; i<5000; i++) {
            tmp.add(r.nextDouble());
        }
        for (int i=1; i<50000; i++) {
            xValues.add(i);
            help.randomDelete(tmp);
            help.randomInsert(tmp);
            yValues.add(tmp.averageDepth());
        }
        XYChart chart = new XYChartBuilder().width(800).height(600).xAxisTitle("x label").yAxisTitle("y label").build();
        chart.addSeries("experiment2", xValues, yValues);
        new SwingWrapper(chart).displayChart();
    }

    public static void experiment3() {
        Random r = new Random();
        List<Double> yValues = new ArrayList<>();
        List<Integer> xValues = new ArrayList<>();
        BST<Double> tmp = new BST<Double>();
        ExperimentHelper help = new ExperimentHelper();
        for (int i=1; i<5000; i++) {
            tmp.add(r.nextDouble());
        }
        for (int i=1; i<50000; i++) {
            xValues.add(i);
            help.randomDeleteRandom(tmp);
            help.randomInsert(tmp);
            yValues.add(tmp.averageDepth());
        }
        XYChart chart = new XYChartBuilder().width(800).height(600).xAxisTitle("x label").yAxisTitle("y label").build();
        chart.addSeries("experiment3", xValues, yValues);
        new SwingWrapper(chart).displayChart();
    }

    public static void main(String[] args) {
        experiment2();
    }
}
