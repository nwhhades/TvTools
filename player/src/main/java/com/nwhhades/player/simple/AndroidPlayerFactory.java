package com.nwhhades.player.simple;

import com.nwhhades.player.base.IPlayerViewFactory;

public class AndroidPlayerFactory implements IPlayerViewFactory<AndroidPlayer> {

    @Override
    public AndroidPlayer getPlayerView() {
        return new AndroidPlayer();
    }

}
