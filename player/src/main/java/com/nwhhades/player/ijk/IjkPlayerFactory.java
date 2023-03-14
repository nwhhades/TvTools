package com.nwhhades.player.ijk;

import com.nwhhades.player.base.IPlayerViewFactory;

public class IjkPlayerFactory implements IPlayerViewFactory<IjkPlayer> {
    @Override
    public IjkPlayer getPlayerView() {
        return new IjkPlayer();
    }
}
