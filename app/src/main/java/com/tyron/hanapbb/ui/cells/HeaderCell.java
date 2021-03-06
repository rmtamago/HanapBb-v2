package com.tyron.hanapbb.ui.cells;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;

import com.tyron.hanapbb.messenger.AndroidUtilities;
import com.tyron.hanapbb.ui.actionbar.BottomSheet;
import com.tyron.hanapbb.ui.actionbar.SimpleTextView;
import com.tyron.hanapbb.ui.actionbar.Theme;
import com.tyron.hanapbb.ui.components.LayoutHelper;

import java.util.ArrayList;

public class HeaderCell extends FrameLayout {
    private TextView textView;
    private SimpleTextView textView2;
    private int height = 40;

    public HeaderCell(Context context) {
        this(context, 21, 15, false);
    }

    public HeaderCell(Context context, int padding) {
        this(context, padding, 15, false);
    }
    public HeaderCell(Context context, int padding, int topMargin, boolean text2) {
        super(context);

        textView = new TextView(getContext());
        textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15);
       // textView.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
        textView.setEllipsize(TextUtils.TruncateAt.END);
        textView.setGravity((BottomSheet.LocaleController.isRTL ? Gravity.RIGHT : Gravity.LEFT) | Gravity.CENTER_VERTICAL);
        textView.setMinHeight(AndroidUtilities.dp(height - topMargin));
        textView.setTextColor(Color.parseColor("#f05252"));

        addView(textView, LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT, LayoutHelper.MATCH_PARENT, (BottomSheet.LocaleController.isRTL ? Gravity.RIGHT : Gravity.LEFT) | Gravity.TOP, padding, topMargin, padding, 0));

        if (text2) {
            textView2 = new SimpleTextView(getContext());
            textView2.setTextSize(13);
            textView2.setGravity((BottomSheet.LocaleController.isRTL ? Gravity.LEFT : Gravity.RIGHT) | Gravity.TOP);
            addView(textView2, LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT, LayoutHelper.MATCH_PARENT, (BottomSheet.LocaleController.isRTL ? Gravity.LEFT : Gravity.RIGHT) | Gravity.TOP, padding, 21, padding, 0));
        }

        ViewCompat.setAccessibilityHeading(this, true);
    }

    public void setHeight(int value) {
        textView.setMinHeight(AndroidUtilities.dp(height = value) - ((LayoutParams) textView.getLayoutParams()).topMargin);
    }

    public void setEnabled(boolean value, ArrayList<Animator> animators) {
        if (animators != null) {
            animators.add(ObjectAnimator.ofFloat(textView, "alpha", value ? 1.0f : 0.5f));
        } else {
            textView.setAlpha(value ? 1.0f : 0.5f);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(MeasureSpec.makeMeasureSpec(MeasureSpec.getSize(widthMeasureSpec), MeasureSpec.EXACTLY), MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
    }

    public void setText(CharSequence text) {
        textView.setText(text);
    }

    public void setText2(CharSequence text) {
        if (textView2 == null) {
            return;
        }
        textView2.setText(text);
    }

    public TextView getTextView() {
        return textView;
    }

    public SimpleTextView getTextView2() {
        return textView2;
    }

}
