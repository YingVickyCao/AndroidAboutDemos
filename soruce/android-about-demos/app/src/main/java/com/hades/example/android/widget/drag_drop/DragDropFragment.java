package com.hades.example.android.widget.drag_drop;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.hades.example.android.R;
import com.hades.example.android.lib.base.BaseFragment;

/**
 * todo 自定义添加view
 */
public class DragDropFragment extends BaseFragment {

    private ImageView imageView;
    private FrameLayout frameLayout;
    private FrameLayout frameLayout2;

    private static final String TAG_IMAGE_VIEW = "image_view";


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.widget_drag_and_drop, container, false);

        imageView = view.findViewById(R.id.imageView);
        frameLayout = view.findViewById(R.id.content_fragment);
        frameLayout2 = view.findViewById(R.id.content_fragment2);

        imageView.setTag(TAG_IMAGE_VIEW);

        imageView.setOnLongClickListener(new View.OnLongClickListener() {

            public boolean onLongClick(View v) {
                v.getBackground().setAlpha(100);

                ClipData.Item item = new ClipData.Item(v.getTag().toString());
                ClipData dragData = new ClipData(v.getTag().toString(), new String[]{ClipDescription.MIMETYPE_TEXT_PLAIN}, item);

                // Instantiates the drag shadow builder.
                View.DragShadowBuilder myShadow = new View.DragShadowBuilder(v);
                v.startDrag(dragData,  // the data to be dragged
                        myShadow,  // the drag shadow builder
                        null,    // no need to use local data
                        0
                        // flags (not currently used, set to 0)
                );

                return true;

            }
        });

        frameLayout.setOnDragListener(new myDragEventListener());
        frameLayout2.setOnDragListener(new myDragEventListener());
        imageView.setOnDragListener(new myDragEventListener());
        return view;

    }

    protected class myDragEventListener implements View.OnDragListener {

        // This is the method that the system calls when it dispatches a drag event to the
        // listener.
        public boolean onDrag(View v, DragEvent event) {

            // Defines a variable to store the action type for the incoming event
            final int action = event.getAction();

            // Handles each of the expected events
            switch (action) {

                case DragEvent.ACTION_DRAG_STARTED:

                    // Determines if this View can accept the dragged data
                    if (event.getClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) {

                        v.invalidate();

                        // returns true to indicate that the View can accept the dragged data.
                        return true;

                    }

                    // Returns false. During the current drag and drop operation, this View will
                    // not receive events again until ACTION_DRAG_ENDED is sent.
                    return false;

                case DragEvent.ACTION_DRAG_ENTERED:

                    // Applies a green tint to the View. Return true; the return value is ignored

                    // Invalidate the view to force a redraw in the new tint
                    v.invalidate();

                    return true;

                case DragEvent.ACTION_DRAG_LOCATION:

                    // Ignore the event
                    return true;

                case DragEvent.ACTION_DRAG_EXITED:

                    // Re-sets the color tint to blue. Returns true; the return value is ignored.

                    // Invalidate the view to force a redraw in the new tint
                    v.invalidate();

                    return true;

                case DragEvent.ACTION_DROP:

                    // Gets the item containing the dragged data
                    ClipData.Item item = event.getClipData().getItemAt(0);

                    // Gets the text data from the item.
                    String dragData = item.getText().toString();

                    // Displays a message containing the dragged data.
                    Toast.makeText(getContext2(), "Dragged data is " + dragData, Toast.LENGTH_LONG).show();

                    // Turns off any color tints
                    if (v instanceof FrameLayout) {
                        ((ViewGroup) imageView.getParent()).removeView(imageView);
                        ((FrameLayout) v).addView(imageView);
                    }
                    // Invalidates the view to force a redraw
                    v.invalidate();

                    // Returns true. DragEvent.getResult() will return true.
                    return true;

                case DragEvent.ACTION_DRAG_ENDED:

                    // Turns off any color tinting

                    // Invalidates the view to force a redraw
                    v.invalidate();

                    // Does a getResult(), and displays what happened.
                    if (event.getResult()) {
                        Toast.makeText(getContext2(), "The drop was handled.", Toast.LENGTH_LONG).show();

                    } else {
                        Toast.makeText(getContext2(), "The drop didn't work.", Toast.LENGTH_LONG).show();

                    }
                    imageView.getBackground().setAlpha(255);
                    // returns true; the value is ignored.
                    return true;

                // An unknown action type was received.
                default:
                    Log.e("DragDrop Example", "Unknown action type received by OnDragListener.");
                    break;
            }

            return false;
        }
    }

    private Context getContext2() {
        return getActivity();
    }
}