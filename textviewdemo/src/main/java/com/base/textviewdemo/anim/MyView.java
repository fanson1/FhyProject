package com.base.textviewdemo.anim;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * 描述: 绘制一个实心圆
 *
 * @author : <a href="mailto:fanhuayong@yinli56.com">Fanhy</a>
 * @version : Ver 1.0
 * @date : 2018-08-08 16:58
 */

public class MyView extends View {
    /**
     *
     * 半径
     */
    private float radius = 50;
    /**
     *
     * 当前圆心位置
     */
    private Point currentPoint;
    /**
     *
     * 实心圆颜色
     */
    private String color;
    /**
     *
     * 画笔
     */
    private Paint paint;

    public MyView(Context context) {
        this(context, null);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint = new Paint();
        paint.setColor(Color.parseColor("#FF9421"));
        paint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (currentPoint == null) {
            currentPoint = new Point(radius, radius);
            drawCircle(canvas);
            startAnimation();
        } else {
            drawCircle(canvas);
        }
    }

    private void startAnimation() {
        Point startPoint = new Point(radius, radius);
        Point endPoint = new Point(getWidth() - radius, getHeight() - radius);
        //ValueAnimator animator = ValueAnimator.ofObject(new PointEvaluator(), startPoint, endPoint);// 沿对角线执行移动
        ValueAnimator animator = ValueAnimator.ofObject(
                new BizierEvaluator2(new Point(
                (startPoint.getX() + endPoint.getX()) / 2,
                        (startPoint.getY() + endPoint.getY()) / 2)),
                startPoint, endPoint);// 抛物线移动
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                currentPoint = (Point) valueAnimator.getAnimatedValue();
                invalidate();
            }
        });
        animator.setDuration(3000);
        animator.start();
    }

    private void drawCircle(Canvas canvas) {
        canvas.drawCircle(currentPoint.getX(), currentPoint.getY(), radius, paint);
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
        paint.setColor(Color.parseColor(color));
        invalidate();
    }
}
