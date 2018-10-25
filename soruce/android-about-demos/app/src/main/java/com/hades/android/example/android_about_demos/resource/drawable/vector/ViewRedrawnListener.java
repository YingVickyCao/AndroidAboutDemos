package com.hades.android.example.android_about_demos.resource.drawable.vector;

/**
 * Listener that notifies when a view has finished drawing
 */
public interface ViewRedrawnListener {

    /**
     * Called when the drawing of the view finished, with the duration of the draw.
     *
     * @param miliseconds the time took to draw the view.
     */
    void onDrawFinished(double miliseconds);
}