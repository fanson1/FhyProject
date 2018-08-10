package com.base.textviewdemo.anim;

import android.animation.TypeEvaluator;

/**
 * 描述: 类的描述
 *
 * @author : <a href="mailto:fanhuayong@yinli56.com">Fanhy</a>
 * @version : Ver 1.0
 * @date : 2018-08-08 17:18
 */

public class PointEvaluator implements TypeEvaluator<Point> {
    @Override
    public Point evaluate(float fraction, Point startPoint, Point endPoint) {
        float currentX = startPoint.getX() + (fraction * (endPoint.getX() - startPoint.getX()));
        float currentY = startPoint.getY() + (fraction * (endPoint.getY() - startPoint.getY()));
        return new Point(currentX, currentY);
    }
}
