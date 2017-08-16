package com.tiancuicui.marqueeview;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.ViewFlipper;

import java.util.List;

/**
 * created by tiancuicui on 2017/07/20
 * 垂直滚动的公告栏view
 */
public class MarqueeView<T> extends ViewFlipper {

    private Context context;
    private DataSet<T> dataSet;
    private boolean isSetAnimDuration = false;
    private OnItemClickListener<T> onItemClickListener;

    private int interval = 2000;
    private int animDuration = 500;
    private int textSize = 14;
    private int textColor = 0xffffffff;

    private boolean singleLine = false;
    private int gravity = Gravity.LEFT | Gravity.CENTER_VERTICAL;
    private static final int TEXT_GRAVITY_LEFT = 0, TEXT_GRAVITY_CENTER = 1, TEXT_GRAVITY_RIGHT = 2;

    public MarqueeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        this.context = context;
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.MarqueeViewStyle, defStyleAttr, 0);
        interval = typedArray.getInteger(R.styleable.MarqueeViewStyle_mvInterval, interval);
        isSetAnimDuration = typedArray.hasValue(R.styleable.MarqueeViewStyle_mvAnimDuration);
        singleLine = typedArray.getBoolean(R.styleable.MarqueeViewStyle_mvSingleLine, false);
        animDuration = typedArray.getInteger(R.styleable.MarqueeViewStyle_mvAnimDuration, animDuration);
        if (typedArray.hasValue(R.styleable.MarqueeViewStyle_mvTextSize)) {
            textSize = (int) typedArray.getDimension(R.styleable.MarqueeViewStyle_mvTextSize, textSize);
            textSize = Util.px2sp(context, textSize);
        }
        textColor = typedArray.getColor(R.styleable.MarqueeViewStyle_mvTextColor, textColor);
        int gravityType = typedArray.getInt(R.styleable.MarqueeViewStyle_mvGravity, TEXT_GRAVITY_LEFT);
        switch (gravityType) {
            case TEXT_GRAVITY_CENTER:
                gravity = Gravity.CENTER;
                break;
            case TEXT_GRAVITY_RIGHT:
                gravity = Gravity.RIGHT | Gravity.CENTER_VERTICAL;
                break;
        }
        typedArray.recycle();

        setFlipInterval(interval);
    }

    // 根据公告字符串列表启动轮播
    public void startWithList(List<T> notices) {
        dataSet = new DataSet<>(notices);
        start();
    }

    public void startWithList(List<T> notices, DataSet.Formatter<T> formatter) {
        dataSet = new DataSet<>(notices);
        dataSet.setFormatter(formatter);
        start();
    }

    // 启动轮播
    private boolean start() {
        if (dataSet == null || dataSet.dataSize() == 0) return false;
        removeAllViews();
        resetAnimation();

        for (int i = 0; i < dataSet.dataSize(); i++) {
            final TextView textView = createTextView(dataSet.format(i), i);
            final int finalI = i;
            textView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClick(finalI, dataSet.getDataByPosition(finalI));
                    }
                }
            });
            addView(textView);
        }

        if (dataSet.dataSize() > 1) {
            startFlipping();
        } else {
            stopFlipping();
        }
        return true;
    }

    private void resetAnimation() {
        clearAnimation();

        Animation animIn = AnimationUtils.loadAnimation(context, R.anim.anim_marquee_in);
        if (isSetAnimDuration) animIn.setDuration(animDuration);
        setInAnimation(animIn);

        Animation animOut = AnimationUtils.loadAnimation(context, R.anim.anim_marquee_out);
        if (isSetAnimDuration) animOut.setDuration(animDuration);
        setOutAnimation(animOut);
    }

    // 创建ViewFlipper下的TextView
    private TextView createTextView(CharSequence text, int position) {
        TextView tv = new TextView(context);
        tv.setGravity(gravity);
        tv.setText(text);
        tv.setTextColor(textColor);
        tv.setTextSize(textSize);
        tv.setSingleLine(singleLine);
        if (singleLine) {
            tv.setEllipsize(TextUtils.TruncateAt.END);
        }
        tv.setTag(position);
        return tv;
    }

    // 设置文字色彩
    public void setMarqueeTextColor(int color) {
        textColor = color;
        final int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            if (child instanceof TextView) {
                ((TextView)child).setTextColor(color);
            }
        }
    }

    public int getPosition() {
        return (int) getCurrentView().getTag();
    }

    public List<T> getNotices() {
        return dataSet.getData();
    }

    public void setOnItemClickListener(OnItemClickListener<T> onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener<T> {
        void onItemClick(int position, T notice);
    }

}
