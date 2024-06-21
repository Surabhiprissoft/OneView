package com.sbi.oneview.ui.transitScreen;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.sbi.oneview.R;

public class CustomIndicatorView extends View {
    private int numItems;
    private int activeIndex;
    private Paint activePaint;
    private Paint inactivePaint;

    public CustomIndicatorView(Context context) {
        super(context);
        init();
    }

    public CustomIndicatorView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // Extract attributes from XML
        // Initialize with default values if needed
        init();
    }

    private void init() {
        activePaint = new Paint();
        activePaint.setColor(getResources().getColor(R.color.page_indicator_active_color));
        activePaint.setAntiAlias(true);

        inactivePaint = new Paint();
        inactivePaint.setColor(getResources().getColor(R.color.page_indicator_inactive_color));
        inactivePaint.setAntiAlias(true);


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = getWidth();
        int height = getHeight();
        int itemWidth = width / numItems;
        int circleRadius = Math.min(itemWidth / 4, height / 4);
        float lineExpansionFactor = 2f;
        // Define the aspect ratio of the rectangle (width to height ratio)
        float aspectRatio = 3.0f; // Adjust this value as needed

        // Calculate the rectangle's height based on the aspect ratio
        int rectangleHeight = (int) (itemWidth / aspectRatio);

        // Calculate space between rectangles
        int spaceBetweenRectangles = (itemWidth - rectangleHeight) / 2;

        for (int i = 0; i < numItems; i++) {
            int cx = itemWidth * i + itemWidth / 2; // Center X of the item
            int cy = height / 2; // Center Y remains the same
            Paint paint = (i == activeIndex) ? activePaint : inactivePaint;
            if(i==activeIndex)
            {
             /*   activePaint.setStyle(Paint.Style.STROKE); // Set to stroke for the line
                activePaint.setStrokeWidth(circleRadius *1.2f); // Set the stroke width
                int startX = (int) (cx - circleRadius * lineExpansionFactor);
                int endX = (int) (cx + circleRadius * lineExpansionFactor);
                int startY = cy;
                int endY = cy;
                canvas.drawLine(startX, startY, endX, endY, activePaint);*/

                activePaint.setStyle(Paint.Style.FILL); // Set to fill for the rounded rect
                activePaint.setStrokeWidth(circleRadius * 1.2f); // Set the stroke width
                int lineWidth = (int) (circleRadius * lineExpansionFactor * 2);
                int startX = (int) (cx - lineWidth / 2);
                int endX = (int) (cx + lineWidth / 2);
                int startY = cy - (rectangleHeight / 2);
                int endY = cy + (rectangleHeight / 2);
                int cornerRadius = circleRadius; // Adjust this value to control the corner radius

                canvas.drawRoundRect(startX, startY, endX, endY, cornerRadius, cornerRadius, activePaint);
            }
            else
            {
                canvas.drawCircle(cx, cy, circleRadius, paint);
            }


        }
    }



    public void setNumItems(int numItems) {
        this.numItems = numItems;
        requestLayout();
    }

    public void setActiveIndex(int activeIndex) {
        this.activeIndex = activeIndex;
        invalidate();
    }
}
