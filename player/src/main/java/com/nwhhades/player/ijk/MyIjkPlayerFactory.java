package com.nwhhades.player.ijk;

import android.content.Context;

import xyz.doikki.videoplayer.player.PlayerFactory;

public class MyIjkPlayerFactory extends PlayerFactory<MyIjkPlayer> {

    public static MyIjkPlayerFactory create() {
        return new MyIjkPlayerFactory();
    }

    @Override
    public MyIjkPlayer createPlayer(Context context) {
        return new MyIjkPlayer(context);
    }
}
