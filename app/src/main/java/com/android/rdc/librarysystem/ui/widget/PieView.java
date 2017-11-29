package com.android.rdc.librarysystem.ui.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.List;

public class PieView extends View {
    private Paint mPaint = new Paint();
    private List<MyPieData> mPieDataList;
    private float mStartAngle;
    private int mWidth;
    private int mHeight;
    private float mRadius;
    private RectF mRectF;
    // 颜色表 (注意: 此处定义颜色使用的是ARGB，带Alpha通道的)
    private int[] mColors = {0xFFCCFF00, 0xFF6495ED, 0xFFE32636, 0xFF800000, 0xFF808000, 0xFFFF8C69, 0xFF808080,
            0xFFE6B800, 0xFF7CFC00};

    public PieView(Context context) {
        this(context, null);
    }

    public PieView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PieView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
        mRadius = (float) (Math.min(mWidth, mHeight) / 2 * 0.8);
        mRectF = new RectF(-mRadius, -mRadius, mRadius, mRadius);

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mPieDataList == null) {
            return;
        }
        float currentAngle = mStartAngle;
        float sweepAngle;
        canvas.translate(mWidth / 2, mHeight / 2);//将画布坐标原点移动
        for (MyPieData pieData : mPieDataList) {
            mPaint.setColor(pieData.getColor());
            sweepAngle = pieData.getPercentage() * 360;
            canvas.drawArc(mRectF, currentAngle, sweepAngle, true, mPaint);
            currentAngle += sweepAngle;
        }
    }

    public void setStartAngle(float startAngle) {
        mStartAngle = startAngle;
    }

    public void setPieDataList(List<MyPieData> pieDataList) {
        mPieDataList = pieDataList;
        resolveDataList(pieDataList);
    }

    private void resolveDataList(List<MyPieData> pieDataList) {
        float sum = 0;
        for (MyPieData pieData : pieDataList) {
            sum += pieData.getValue();
        }
        for (int i = 0; i < pieDataList.size(); i++) {
            MyPieData pieData = pieDataList.get(i);
            pieData.setPercentage(pieData.getValue() / sum);
            pieData.setColor(mColors[i % mColors.length]);
        }
        invalidate();
    }
}
