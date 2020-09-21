package com.materialchips;


import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.core.content.ContextCompat;

import com.materialchips.model.ChipInterface;
import com.materialchips.util.LetterTileProvider;

public class ChipView extends RelativeLayout {

    private static final String TAG = ChipView.class.toString();
    // attributes
    private static final int NONE = -1;
    // context
    private Context mContext;
    // xml elements
    private RelativeLayout mContentLayout;
    private TextView mFullNameTextView;
    private ImageButton mDeleteButton;
    private String mFullName;
    private int fontSize = 10;
    private ColorStateList mFullNameColor;
    private boolean mDeletable = false;
    private Drawable mDeleteIcon;
    private Integer mChipBackground;
    private ColorStateList mDeleteIconColor;
    private ColorStateList mBackgroundColor;
    // letter tile provider
    private LetterTileProvider mLetterTileProvider;
    // chip
    private ChipInterface mChip;

    public ChipView(Context context) {
        super(context);
        mContext = context;
        init(null);
    }

    public ChipView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init(attrs);
    }

    private static ChipView newInstance(Builder builder) {
        ChipView chipView = new ChipView(builder.context);
        chipView.mFullName = builder.FullName;
        chipView.mFullNameColor = builder.FullNameColor;
        chipView.mDeletable = builder.deletable;
        chipView.mDeleteIcon = builder.deleteIcon;
        chipView.mDeleteIconColor = builder.deleteIconColor;
        chipView.mBackgroundColor = builder.backgroundColor;
        chipView.mChipBackground = builder.chipBackground;
        chipView.fontSize = builder.fontSize;
        chipView.mChip = builder.chip;
        chipView.inflateWithAttributes();

        return chipView;
    }

    /**
     * Inflate the view according to attributes
     *
     * @param attrs the attributes
     */
    private void init(AttributeSet attrs) {
        // inflate layout
        View rootView = inflate(getContext(), R.layout.chip_view, this);

        mContentLayout = rootView.findViewById(R.id.content);
        mFullNameTextView = rootView.findViewById(R.id.FullName);
        mDeleteButton = rootView.findViewById(R.id.delete_button);

        // letter tile provider
        mLetterTileProvider = new LetterTileProvider(mContext);

        // attributes
        if (attrs != null) {
            TypedArray a = mContext.getTheme().obtainStyledAttributes(
                    attrs,
                    R.styleable.ChipView,
                    0, 0);

            try {
                // FullName
                mFullName = a.getString(R.styleable.ChipView_label);
                mFullNameColor = a.getColorStateList(R.styleable.ChipView_label);

                mDeletable = a.getBoolean(R.styleable.ChipView_deletable, false);
                mDeleteIconColor = a.getColorStateList(R.styleable.ChipView_deleteIconColor);
                int deleteIconId = a.getResourceId(R.styleable.ChipView_deleteIcon, NONE);
                mChipBackground = a.getResourceId(R.styleable.ChipView_chipBackground, R.drawable.ripple_chip_view);
                if (deleteIconId != NONE)
                    mDeleteIcon = ContextCompat.getDrawable(mContext, deleteIconId);
                // background color
                mBackgroundColor = a.getColorStateList(R.styleable.ChipView_backgroundColor);
            } finally {
                a.recycle();
            }
        }

        // inflate
        inflateWithAttributes();
    }

    /**
     * Inflate the view
     */
    private void inflateWithAttributes() {
        // FullName
        setFullName(mFullName);
        if (mFullNameColor != null)
            setFullNameColor(mFullNameColor);


        // delete button
        setDeletable(mDeletable);

        // background color
        if (mBackgroundColor != null)
            setChipBackgroundColor(mBackgroundColor);

        setChipBackground(mChipBackground);
    }

    public void inflate(ChipInterface chip) {
        mChip = chip;
        // FullName
        mFullName = mChip.getFullName();

        // inflate
        inflateWithAttributes();
    }


    /**
     * Get FullName
     *
     * @return the FullName
     */
    public String getFullName() {
        return mFullName;
    }

    /**
     * Set FullName
     *
     * @param FullName the FullName to set
     */
    public void setFullName(String FullName) {
        mFullName = FullName;
        mFullNameTextView.setText(FullName);
        mFullNameTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, fontSize);
    }

    /**
     * Set FullName color
     *
     * @param color the color to set
     */
    public void setFullNameColor(ColorStateList color) {
        mFullNameColor = color;
        mFullNameTextView.setTextColor(color);
    }

    /**
     * Set FullName color
     *
     * @param color the color to set
     */
    public void setFullNameColor(@ColorInt int color) {
        mFullNameColor = ColorStateList.valueOf(color);
        mFullNameTextView.setTextColor(color);
    }


    /**
     * Set chip background
     *
     * @param chipBackground the chip background
     */
    public void setChipBackground(Integer chipBackground) {
        if (chipBackground != null) {
            mChipBackground = chipBackground;
            mContentLayout.setBackgroundResource(chipBackground);
        }
    }


    /**
     * Show or hide delte button
     *
     * @param deletable true to show, false to hide
     */
    public void setDeletable(boolean deletable) {
        mDeletable = deletable;
        if (!mDeletable) {
            // hide delete icon
            mDeleteButton.setVisibility(GONE);
            // adjust padding

        } else {
            // show icon
            mDeleteButton.setVisibility(VISIBLE);
            // set icon
            if (mDeleteIcon != null)
                mDeleteButton.setImageDrawable(mDeleteIcon);
            if (mDeleteIconColor != null)
                mDeleteButton.getDrawable().mutate().setColorFilter(mDeleteIconColor.getDefaultColor(), PorterDuff.Mode.SRC_ATOP);
        }
    }

    /**
     * Set delete icon color
     *
     * @param color the color to set
     */
    public void setDeleteIconColor(ColorStateList color) {
        mDeleteIconColor = color;
        mDeletable = true;
        inflateWithAttributes();
    }

    /**
     * Set delete icon color
     *
     * @param color the color to set
     */
    public void setDeleteIconColor(@ColorInt int color) {
        mDeleteIconColor = ColorStateList.valueOf(color);
        mDeletable = true;
        inflateWithAttributes();
    }

    /**
     * Set delete icon
     *
     * @param deleteIcon the icon to set
     */
    public void setDeleteIcon(Drawable deleteIcon) {
        mDeleteIcon = deleteIcon;
        mDeletable = true;
        inflateWithAttributes();
    }

    /**
     * Set background color
     *
     * @param color the color to set
     */
    public void setChipBackgroundColor(ColorStateList color) {
        mBackgroundColor = color;
        setChipBackgroundColor(color.getDefaultColor());
    }

    /**
     * Set background color
     *
     * @param color the color to set
     */
    public void setChipBackgroundColor(@ColorInt int color) {
        mBackgroundColor = ColorStateList.valueOf(color);
        mContentLayout.getBackground().setColorFilter(color, PorterDuff.Mode.SRC_ATOP);
    }

    /**
     * Set the chip object
     *
     * @param chip the chip
     */
    public void setChip(ChipInterface chip) {
        mChip = chip;
    }

    /**
     * Set OnClickListener on the delete button
     *
     * @param onClickListener the OnClickListener
     */
    public void setOnDeleteClicked(OnClickListener onClickListener) {
        mDeleteButton.setOnClickListener(onClickListener);
    }

    /**
     * Set OnclickListener on the entire chip
     *
     * @param onClickListener the OnClickListener
     */
    public void setOnChipClicked(OnClickListener onClickListener) {
        mContentLayout.setOnClickListener(onClickListener);
    }

    /**
     * Builder class
     */
    public static class Builder {
        private Context context;
        private String FullName;
        private ColorStateList FullNameColor;
        private boolean deletable = false;
        private Drawable deleteIcon;
        private Integer chipBackground;
        private int fontSize;
        private ColorStateList deleteIconColor;
        private ColorStateList backgroundColor;
        private ChipInterface chip;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder FullName(String fullName) {
            this.FullName = fullName;
            return this;
        }

        public Builder FullNameColor(ColorStateList fullNameColor) {
            this.FullNameColor = fullNameColor;
            return this;
        }


        public Builder deletable(boolean deletable) {
            this.deletable = deletable;
            return this;
        }

        public Builder deleteIcon(Drawable deleteIcon) {
            this.deleteIcon = deleteIcon;
            return this;
        }

        public Builder chipBackground(Integer chipBackground) {
            this.chipBackground = chipBackground;
            return this;
        }

        public Builder deleteIconColor(ColorStateList deleteIconColor) {
            this.deleteIconColor = deleteIconColor;
            return this;
        }

        public Builder backgroundColor(ColorStateList backgroundColor) {
            this.backgroundColor = backgroundColor;
            return this;
        }

        public Builder fontSize(int fontSize) {
            this.fontSize = fontSize;
            return this;
        }

        public Builder chip(ChipInterface chip) {
            this.chip = chip;
            this.FullName = chip.getFullName();
            return this;
        }

        public ChipView build() {
            return newInstance(this);
        }
    }
}
