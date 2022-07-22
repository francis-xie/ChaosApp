package com.basic.router.facade.callback;

import com.basic.router.facade.Postcard;

/**
 * Easy to use navigation callback.
 */
public abstract class NavCallback implements NavigationCallback {
    @Override
    public void onFound(Postcard postcard) {
        // Do nothing
    }

    @Override
    public void onLost(Postcard postcard) {
        // Do nothing
    }

    @Override
    public abstract void onArrival(Postcard postcard);

    @Override
    public void onInterrupt(Postcard postcard) {
        // Do nothing
    }
}
