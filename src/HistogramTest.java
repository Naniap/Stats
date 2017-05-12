import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import java.awt.event.ActionListener;
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
import javax.swing.SwingConstants;
import javax.swing.JSplitPane;

/***
 * 
 * @author Michael Coache
 *
 */

public class HistogramTest extends ApplicationFrame implements ActionListener {
	private static int dice;
	private static int rolls;
	private static int sides;
	private SimpleHistogramDataset dataset;
	private JTextArea box;
	private static JFrame parent;
	private JFrame frame;
    public HistogramTest(String title, int rolls, int dice, int sides, JFrame parent) {
        super(title);
        frame = this;
        HistogramTest.dice = dice;
        HistogramTest.rolls = rolls;
        HistogramTest.sides = sides;
        HistogramTest.parent = parent;
        JPanel chartPanel = crearPanel();
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 475));
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(chartPanel);
	        Color c = new Color(0,0,0,100);
	        
	        JPanel panel = new JPanel();
	        getContentPane().add(panel, BorderLayout.SOUTH);
	        
	        JButton btnReset = new JButton("Reset");
	        btnReset.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent arg0) {
	        		if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, "You will lose this data, are you sure you wish to reset?", "Warning!", JOptionPane.YES_NO_OPTION)) {
	        			parent.setVisible(true);
	        			frame.dispose();
	        		}
	        	}
	        });
	        btnReset.setHorizontalAlignment(SwingConstants.LEFT);
	        panel.add(btnReset);
	        JButton button = new JButton("Add observations: ");
	        panel.add(button);
	        button.setHorizontalAlignment(SwingConstants.LEFT);
	        box = new JTextArea("20");
	        box.setColumns(10);
	        panel.add(box);
	        box.setBackground(Color.WHITE);
	        button.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
		if (box.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Please make sure all fields are filled in.");
			return;
		}
		try {
	    	if (Integer.parseInt(box.getText()) <= 10000000) {
	    		doWork(Math.ceil(Integer.parseInt(box.getText())/2));
	    		doWork(Math.floor(Integer.parseInt(box.getText())/2));
	    	}
	    	else { 
	    		doWork(Math.ceil(Integer.parseInt(box.getText())/4));
	    		doWork(Math.ceil(Integer.parseInt(box.getText())/4));
	    		doWork(Math.floor(Integer.parseInt(box.getText())/4));
	    		doWork(Math.floor(Integer.parseInt(box.getText())/4));
	    	}
		}
		catch (NullPointerException | NumberFormatException ex) {
			JOptionPane.showMessageDialog(null, "Please enter integer values only.");
    	}
    }
    private void doWork(double num) {
    	 Thread t = new Thread() {
    		 public void run() {
    				for (int i = 0; i < num; i++) { 
    					double num = 0;
    					for (int j = 0; j < dice; j++) { 
    						num += (double)(ThreadLocalRandom.current().nextInt(1, sides + 1));
    					}
    					dataset.addObservation(num);
    				}
    		 }
    	 };
    	 t.start();
    }
    private IntervalXYDataset crearDataset() {
    	dataset = new SimpleHistogramDataset("Die Toss");
		for (int i = dice; i <= sides * dice + 1; i++) { 
			dataset.addBin(new SimpleHistogramBin(i, i+1, true, false)); 
		}
		for (int i = 0; i < rolls; i++) { 
			double num = 0;
			for (int j = 0; j < dice; j++) { 
				num += (double)(ThreadLocalRandom.current().nextInt(1, sides + 1));
			}
			dataset.addObservation(num);
		}
        return dataset;
    }
    private JFreeChart crearChart(IntervalXYDataset dataset) {
        JFreeChart chart = ChartFactory.createHistogram(
                "",
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

    public JPanel crearPanel() {
        JFreeChart chart = crearChart(crearDataset());
        return new ChartPanel(chart);
    }

    public static void main(String[] args) throws IOException {
    	HistogramTest histo = new HistogramTest("", rolls, dice, sides, parent);
        histo.pack();
        RefineryUtilities.centerFrameOnScreen(histo);
        histo.setVisible(true);
    }
}
  