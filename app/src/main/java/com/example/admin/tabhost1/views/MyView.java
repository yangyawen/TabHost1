  package com.example.admin.tabhost1.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Looper;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.admin.tabhost1.R;

/**
 * TODO: document your custom view class.
 */
public class MyView extends View {
    private static final String LOG_TAG = "MyView";
    private float alpha;
    private String mExampleString; // TODO: use a default from R.string...
    private int mExampleColor = Color.RED; // TODO: use a default from R.color...
    private float mExampleDimension = 0; // TODO: use a default from R.dimen...
    private Bitmap tabIcon;

    private TextPaint mTextPaint;
    private float mTextWidth;
    private float mTextHeight;

    Paint paint;
    private Rect rect;

    public MyView(Context context) {
        this(context, null);
        //init(null, 0);
    }

    public MyView(Context context, AttributeSet attrs){
        this(context, attrs, 0);
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint = new Paint(); //设置一个笔刷大小是3的黄色的画笔
        paint.setColor(Color.YELLOW);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(3);

        init(attrs, defStyleAttr);
    }

    private void init(AttributeSet attrs, int defStyle){
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.MyView, defStyle, 0);

        if (a.hasValue(R.styleable.MyView_drawable)) {
            BitmapDrawable drawable = (BitmapDrawable) a.getDrawable(R.styleable.MyView_drawable);
            tabIcon = drawable.getBitmap();

        }

        a.recycle();
    }

    /*
    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public MyView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        // Load attributes
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.MyView, defStyle, 0);

        mExampleString = a.getString(
                R.styleable.MyView_exampleString);
        mExampleColor = a.getColor(
                R.styleable.MyView_exampleColor,
                mExampleColor);
        // Use getDimensionPixelSize or getDimensionPixelOffset when dealing with
        // values that should fall on pixel boundaries.
        mExampleDimension = a.getDimension(
                R.styleable.MyView_exampleDimension,
                mExampleDimension);

        if (a.hasValue(R.styleable.MyView_exampleDrawable)) {
            mExampleDrawable = a.getDrawable(
                    R.styleable.MyView_exampleDrawable);
            mExampleDrawable.setCallback(this);
        }

        a.recycle();

        // Set up a default TextPaint object
        mTextPaint = new TextPaint();
        mTextPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setTextAlign(Paint.Align.LEFT);

        // Update TextPaint and text measurements from attributes
        invalidateTextPaintAndMeasurements();
    }

    private void invalidateTextPaintAndMeasurements() {
        mTextPaint.setTextSize(mExampleDimension);
        mTextPaint.setColor(mExampleColor);
        mTextWidth = mTextPaint.measureText(mExampleString);

        Paint.FontMetrics fontMetrics = mTextPaint.getFontMetrics();
        mTextHeight = fontMetrics.bottom;
    }*/

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec){
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.e(LOG_TAG, "onMeasure");
        Log.e(LOG_TAG, "getMeasuredWidth(): "+getMeasuredWidth()+"  getPaddingLeft(): "+getPaddingLeft()+"  getPaddingRight(): "+getPaddingRight());
        int iconWidth = Math.min(getMeasuredWidth() - getPaddingLeft()
                - getPaddingRight(), getMeasuredHeight() - getPaddingTop()
                - getPaddingBottom());

        int left = getMeasuredWidth() / 2 - iconWidth / 2;
        int top = getMeasuredHeight() / 2 - iconWidth / 2;
        rect = new Rect(left, top, left + iconWidth, top + iconWidth);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        Log.e(LOG_TAG, "onDraw");
        super.onDraw(canvas);

        //Rect rect = new Rect(0, 0, 100, 100);
        //Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.btn_createnew);
        canvas.drawBitmap(tabIcon, null, rect, null);
        //canvas.drawColor(Color.BLUE);
        //canvas.drawCircle(100, 100, 90, paint);
        /*
        super.onDraw(canvas);

        // TODO: consider storing these as member variables to reduce
        // allocations per draw cycle.
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();

        int contentWidth = getWidth() - paddingLeft - paddingRight;
        int contentHeight = getHeight() - paddingTop - paddingBottom;

        // Draw the text.
        canvas.drawText(mExampleString,
                paddingLeft + (contentWidth - mTextWidth) / 2,
                paddingTop + (contentHeight + mTextHeight) / 2,
                mTextPaint);

        // Draw the example drawable on top of the text.
        if (mExampleDrawable != null) {
            mExampleDrawable.setBounds(paddingLeft, paddingTop,
                    paddingLeft + contentWidth, paddingTop + contentHeight);
            mExampleDrawable.draw(canvas);
        }*/
    }

    public void setIconAlpha(float alpha){
        Log.e(LOG_TAG, "setIconAlpha: "+alpha);
        this.alpha = alpha;
        invalidateView();
    }

    /**
     * 重绘
     */
    private void invalidateView(){
        //Log.e(LOG_TAG, "invalidateView");
        if (Looper.getMainLooper() == Looper.myLooper()){
            invalidate();
        } else{
            postInvalidate();
        }
    }

    @Override
    public void setSelected(boolean selected){
        super.setSelected(selected);
        
    }
}
