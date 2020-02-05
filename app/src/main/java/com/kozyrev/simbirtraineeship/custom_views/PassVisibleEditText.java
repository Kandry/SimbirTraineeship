package com.kozyrev.simbirtraineeship.custom_views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.EditText;

import androidx.annotation.Nullable;

import com.kozyrev.simbirtraineeship.R;

@SuppressLint("AppCompatCustomView")
public class PassVisibleEditText extends EditText {

    private Drawable drawableRight;
    private Rect rBounds;

    public PassVisibleEditText(Context context) {
        super(context);
    }

    public PassVisibleEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PassVisibleEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public PassVisibleEditText(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void setCompoundDrawables(@Nullable Drawable left, @Nullable Drawable top, @Nullable Drawable right, @Nullable Drawable bottom) {
        if (right != null) drawableRight = right;
        super.setCompoundDrawables(left, top, right, bottom);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();

        if (event.getAction() == MotionEvent.ACTION_UP && drawableRight != null){
            rBounds = drawableRight.getBounds();

            if (x <= (this.getRight())
                    && x >= (this.getRight() - rBounds.width())
                    && y >= this.getPaddingTop()
                    && y <= (this.getHeight() - this.getPaddingBottom())) {

                if (this.getInputType() == (InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD)){
                    this.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    this.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.close, 0);
                }
                else {
                    this.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    this.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.open, 0);
                }
            }
        }

        return super.onTouchEvent(event);
    }
}
