package domel.ecampus.Component;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewPager;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import domel.ecampus.R;

/**
 * TODO: document your custom view class.
 */
public class RestrictiveViewPager extends ViewPager {

    private boolean allow_swipe;

    public RestrictiveViewPager(Context context) {
        super(context);
        allow_swipe = false;
    }

    public RestrictiveViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        allow_swipe = false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        //do not allow swipping if allow_swipe is set to false
        if(!allow_swipe){
            return true;
        }else{
            return super.onTouchEvent(event);
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        //do not allow swipping if allow_swipe is set to false
        if(!allow_swipe){
            return true;
        }else{
            return super.onTouchEvent(event);
        }
    }

    public boolean isAllow_swipe() {
        return allow_swipe;
    }

    public void setAllow_swipe(boolean allow_swipe) {
        this.allow_swipe = allow_swipe;
    }
}
