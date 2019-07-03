package com.hades.example.android.resource.drawable.state.custom_selector_drawable;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.hades.example.android.R;

@SuppressLint("AppCompatCustomView")
public class CustomImageView extends ImageView {
    private boolean messageRead;
    /**
     * Custom message unread state variable for use with a {@link android.graphics.drawable.StateListDrawable}.
     */
    private static final int[] STATE_MESSAGE_READ = {R.attr.state_message_read};

    public CustomImageView(Context context) {
        super(context);
    }

    public CustomImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CustomImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public int[] onCreateDrawableState(int extraSpace) {
        if (null == STATE_MESSAGE_READ) {
            return super.onCreateDrawableState(extraSpace);
        }
        
        if (messageRead) {
            // We are going to add 1 extra state.
            final int[] drawableState = super.onCreateDrawableState(extraSpace + 1);
            mergeDrawableStates(drawableState, STATE_MESSAGE_READ);

            return drawableState;
        } else {
            return super.onCreateDrawableState(extraSpace);
        }
    }

    public void setMessageRead(boolean messageRead) {
        // Performance optimisation: only update the state if it has changed.
        if (this.messageRead != messageRead) {
            this.messageRead = messageRead;
            // Refresh the drawable state so that it includes the message unread state if required.
            refreshDrawableState();
        }
    }
}
