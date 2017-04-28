import java.awt.BasicStroke;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.data.statistics.HistogramDataset;
import org.jfree.data.xy.IntervalXYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;


public class HistogramTest extends ApplicationFrame {

    public HistogramTest(String title) {
        super(title);
        JPanel chartPanel = crearPanel();
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 475));
        setContentPane(chartPanel);
    }

    private static IntervalXYDataset crearDataset() {
        HistogramDataset dataset = new HistogramDataset();
		Scanner scan = new Scanner(System.in);
		System.out.println("How many dice: ");
		int dice = scan.nextInt();
		System.out.println("How many rolls: ");
		int rolls = scan.nextInt();
		System.out.println("How many sides: ");
		int sides = scan.nextInt();
		scan.close();
		List<Double> results = new ArrayList<>();
		for (int i = 0; i < rolls; i++) { 
			double num = 0;
			for (int j = 0; j < dice; j++) { 
				num += (double)(ThreadLocalRandom.current().nextInt(1, sides + 1));
			}
			results.add(num);
		}
        dataset.addSeries("Dice results", convertDoubles(results), (sides * dice) - 1);
        return dataset;
    }
    public static double[] convertDoubles(List<Double> doubles)
    {
        double[] ret = new double[doubles.size()];
        Iterator<Double> iterator = doubles.iterator();
        int i = 0;
        while(iterator.hasNext())
        {
            ret[i] = iterator.next();
            i++;
        }
        return ret;
    }
    private static JFreeChart crearChart(IntervalXYDataset dataset) {
        JFreeChart chart = ChartFactory.createHistogram(
                "Histogram",
                "Die Sum",
                "Die Toss",
                dataset,
                PlotOrientation.VERTICAL,
                false,
                true,
                false);       
        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setRangeGridlineStroke(new BasicStroke(1.0f));
        XYBarRenderer renderer = (XYBarRenderer) plot.getRenderer();
        renderer.setDrawBarOutline(false);
        return chart;
    }

    public static JPanel crearPanel() {
        JFreeChart chart = crearChart(crearDataset());
        return new ChartPanel(chart);
    }

    public static void main(String[] args) throws IOException {
    	HistogramTest histo = new HistogramTest("Histogram");
        histo.pack();
        RefineryUtilities.centerFrameOnScreen(histo);
        histo.setVisible(true);
    }
}
  