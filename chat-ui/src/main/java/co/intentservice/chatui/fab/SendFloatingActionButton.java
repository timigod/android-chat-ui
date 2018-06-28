package co.intentservice.chatui.fab;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.util.AttributeSet;

import co.intentservice.chatui.R;

public class SendFloatingActionButton extends FloatingActionButton {
  int mPlusColor;

  public SendFloatingActionButton(Context context) {
    this(context, null);
  }

  public SendFloatingActionButton(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public SendFloatingActionButton(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
  }

  @Override
  void init(Context context, AttributeSet attributeSet) {
    TypedArray attr = context.obtainStyledAttributes(attributeSet, R.styleable.SendFloatingActionButton, 0, 0);
    mPlusColor = attr.getColor(R.styleable.SendFloatingActionButton_fab_plusIconColor, getColor(android.R.color.white));
    attr.recycle();

    super.init(context, attributeSet);
  }

  /**
   * @return the current Color of plus icon.
   */
  public int getPlusColor() {
    return mPlusColor;
  }

  public void setPlusColorResId(@ColorRes int plusColor) {
    setPlusColor(getColor(plusColor));
  }

  public void setPlusColor(int color) {
    if (mPlusColor != color) {
      mPlusColor = color;
      updateBackground();
    }
  }

  @Override
  public void setIcon(@DrawableRes int icon) {
    throw new UnsupportedOperationException("Use FloatingActionButton if you want to use custom icon");
  }
}
