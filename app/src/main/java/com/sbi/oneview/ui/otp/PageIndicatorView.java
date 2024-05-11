package com.sbi.oneview.ui.otp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class PageIndicatorView extends View {
    private static final int SELECTED_COLOR = Color.parseColor("#FF0000");
    private static final int UNSELECTED_COLOR = Color.parseColor("#CCCCCC");

    private boolean isSelected = false;

    public PageIndicatorView(Context context) {
        super(context);
        init();
    }

    public PageIndicatorView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PageIndicatorView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        // Set initial state
        setSelected(false);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint = new Paint();
        paint.setAntiAlias(true);

        // Draw circle based on selected state
        paint.setColor(isSelected ? SELECTED_COLOR : UNSELECTED_COLOR);
        canvas.drawCircle(getWidth() / 40f, getHeight() / 40f, getWidth() / 40f, paint);
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
        invalidate(); // Redraw view
    }
}

