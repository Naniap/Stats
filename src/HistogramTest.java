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
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.data.statistics.HistogramDataset;
import org.jfree.data.statistics.SimpleHistogramBin;
import org.jfree.data.statistics.SimpleHistogramDataset;
import org.jfree.data.xy.IntervalXYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;


public class HistogramTest extends ApplicationFrame {
	private static int dice;
	private static int rolls;
	private static int sides;
    public HistogramTest(String title) {
        super(title);
        JPanel chartPanel = crearPanel();
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 475));
        setContentPane(chartPanel);
    }

    private static IntervalXYDataset crearDataset() {
    	SimpleHistogramDataset dataset2 = new SimpleHistogramDataset("Test");
		Scanner scan = new Scanner(System.in);
		System.out.println("How many dice: ");
		dice = scan.nextInt();
		System.out.println("How many rolls: ");
		rolls = scan.nextInt();
		System.out.println("How many sides: ");
		sides = scan.nextInt();
		scan.close();
		for (int i = dice; i <= sides * dice + 1; i++) { 
			dataset2.addBin(new SimpleHistogramBin(i, i+1, true, false)); 
		}
		for (int i = 0; i < rolls; i++) { 
			double num = 0;
			for (int j = 0; j < dice; j++) { 
				num += (double)(ThreadLocalRandom.current().nextInt(1, sides + 1));
			}
			dataset2.addObservation(num);
		}
        return dataset2;
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
        ValueAxis domainAxis = plot.getDomainAxis();
        domainAxis.setRange(dice, dice * sides + 1);
        domainAxis.setAutoRangeMinimumSize(1);
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
  