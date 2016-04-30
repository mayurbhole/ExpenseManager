package our.isaacmayur.expensemanager;

import org.achartengine.ChartFactory;
import org.achartengine.model.CategorySeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;

public class PieChartMonth {
	public Intent getIntent(Context context, int m1, int m2, int m3, int m4,
			int m5, int m6, int m7, int m8, int m9, int m10, int m11, int m12) {
		// this is my data of performance; data is collected in array.
		/*
		 * int []Performance = {100, 250, 250,250}; // [0] for Call, [1] for
		 * Meeting, [2] for Email CategorySeries series = new
		 * CategorySeries("pie"); // adding series to charts. //collect 3 value
		 * in array. therefore add three series.
		 * series.add("Call",Performance[0]);
		 * series.add("Meeting",Performance[1]);
		 * series.add("Email",Performance[2]); series.add("Hi",Performance[2]);
		 */

		CategorySeries series = new CategorySeries("pie");
	/*	series.add("month 1", m1);
		series.add("month 2", m2);
		series.add("month 3", m3);
		series.add("month 4", m4);
		series.add("month 5", m5);
		series.add("month 6", m6);
		series.add("month 7", m7);
		series.add("month 8", m8);
		series.add("month 9", m9);
		series.add("month 10", m10);
		series.add("month 11", m11);
		series.add("month 12", m12);*/
		
		
		series.add("Jan", m1);
		series.add("Feb",m2 );
		series.add("March",m3 );
		series.add("April",m4 );
		series.add("May",m5 );
		series.add("June", m6);
		series.add("July",m7 );
		series.add("Aug",m8 );
		series.add("Sept",m9 );
		series.add("Oct", m10);
		series.add("Nov",m11 );
		series.add("Dec",m12 );

		// add three colors for three series respectively
		int[] colors = new int[] { Color.MAGENTA, Color.WHITE, Color.GREEN,
				Color.YELLOW, Color.RED,  Color.BLUE,Color.CYAN, Color.DKGRAY, Color.LTGRAY,Color.MAGENTA, Color.GREEN,  Color.RED };
		// set style for series
		DefaultRenderer renderer = new DefaultRenderer();
		for (int color : colors) {
			SimpleSeriesRenderer r = new SimpleSeriesRenderer();
			r.setColor(color);
			r.setDisplayBoundingPoints(true);
			r.setDisplayChartValuesDistance(5);
			r.setDisplayChartValues(true);
			r.setChartValuesTextSize(15);
			renderer.addSeriesRenderer(r);
		}
		renderer.isInScroll();
		renderer.setZoomButtonsVisible(true); // set zoom button in Graph
		renderer.setApplyBackgroundColor(true);
		renderer.setBackgroundColor(Color.BLACK); // set background color
		renderer.setChartTitle("Month Expense Pie Chart");
		renderer.setChartTitleTextSize((float) 30);
		renderer.setShowLabels(true);
		renderer.setLabelsTextSize(20);
		renderer.setLegendTextSize(25);
		renderer.setDisplayValues(true);
		return ChartFactory.getPieChartIntent(context, series, renderer,
				"PieChart");
	}

}