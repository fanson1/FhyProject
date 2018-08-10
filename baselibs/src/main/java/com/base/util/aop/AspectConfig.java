package com.base.util.aop;

import android.util.Log;

import com.base.util.hint.LogUtil;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * 描述: 使用AspectJ对OnclickListener进行插桩
 *
 * @author : <a href="mailto:fanhuayong@yinli56.com">Fanhy</a>
 * @version : Ver 1.0
 * @date : 2018-08-06 14:08
 */

@Aspect
public class AspectConfig {
    final String TAG = AspectConfig.class.getSimpleName();
    private boolean canDoubleClick = false;

    @Before("@annotation(*..DoubleClick)")
    public void beforeEnableDoubleClcik(JoinPoint joinPoint) throws Throwable {
        canDoubleClick = true;
    }

    @Around("execution(* android.view.View.OnClickListener.onClick(..))")
    public void OnClickListener(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        LogUtil.logD("监听到点击事件");
        if (canDoubleClick || !NoDoubleClickUtils.isDoubleClick()) {
            proceedingJoinPoint.proceed();
            canDoubleClick = false;
        }
    }
}
