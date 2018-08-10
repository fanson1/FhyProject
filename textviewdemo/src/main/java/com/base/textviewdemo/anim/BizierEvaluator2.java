package com.base.textviewdemo.anim;

/**
 * 描述: 类的描述
 *
 * @author : <a href="mailto:fanhuayong@yinli56.com">Fanhy</a>
 * @version : Ver 1.0
 * @date : 2018-08-09 9:18
 */

import android.animation.TypeEvaluator;

/**
 * 贝塞尔曲线（二阶抛物线）
 * controllPoint 是中间的转折点
 * startValue 是起始的位置
 * endValue 是结束的位置
 */
public class BizierEvaluator2 implements TypeEvaluator<Point> {

    private Point controllPoint;

    public BizierEvaluator2(Point controllPoint) {
        this.controllPoint = controllPoint;
    }

    @Override
    public Point evaluate(float t, Point startValue, Point endValue) {
        int x = (int) ((1 - t) * (1 - t) * startValue.getX() + 2 * t * (1 - t) * controllPoint.getX() + t * t * endValue.getX());
        int y = (int) ((1 - t) * (1 - t) * startValue.getY() + 2 * t * (1 - t) * controllPoint.getY() + t * t * endValue.getY());
        return new Point(x, y);
    }
}