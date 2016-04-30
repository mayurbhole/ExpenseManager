
package our.isaacmayur.expensemanager;


import org.achartengine.ChartFactory;
import org.achartengine.model.CategorySeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
public class PieChartWeek {
    public Intent getIntent(Context context,int w1,int w2,int w3,int w4,int w5){
      // this is my data of performance; data is collected in array.
     /*  int []Performance = {100, 250, 250,250};  // [0] for Call, [1] for Meeting, [2] for Email
        CategorySeries series = new CategorySeries("pie"); // adding series to charts. //collect 3 value in array. therefore add three series.
            series.add("Call",Performance[0]);            
            series.add("Meeting",Performance[1]);
            series.add("Email",Performance[2]);
            series.add("Hi",Performance[2]);*/

    	Log.e("w2",w2+"");
    	CategorySeries series = new CategorySeries("pie");
    	 series.add("Day 1-7",w1);            
         series.add("Day 8-14",w2);
         series.add("Day 15-21",w3);
         series.add("Day 22-28",w4);
         series.add("After 28",w5);
    	
    	
    	
// add three colors for three series respectively            
            int []colors = new int[]{Color.MAGENTA, Color.WHITE, Color.GREEN,Color.YELLOW,Color.RED};
// set style for series
            DefaultRenderer renderer = new DefaultRenderer();
            for(int color : colors){
                SimpleSeriesRenderer r = new SimpleSeriesRenderer();
                r.setColor(color);
                r.setDisplayBoundingPoints(true);
                r.setDisplayChartValuesDistance(5);
                r.setDisplayChartValues(true);
                r.setChartValuesTextSize(15);
                renderer.addSeriesRenderer(r);
            }
            renderer.isInScroll();
            renderer.setZoomButtonsVisible(true);   //set zoom button in Graph
            renderer.setApplyBackgroundColor(true);
            renderer.setBackgroundColor(Color.BLACK); //set background color
            renderer.setChartTitle("Week Expense Pie Chart");
            renderer.setChartTitleTextSize((float) 30);
            renderer.setShowLabels(true);  
            renderer.setLabelsTextSize(20);
            renderer.setLegendTextSize(25);
            renderer.setDisplayValues(true);
        
            return ChartFactory.getPieChartIntent(context, series, renderer, "PieChart");
          
        }
    
    
          

    
    }