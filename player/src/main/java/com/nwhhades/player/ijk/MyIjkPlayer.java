package com.nwhhades.player.ijk;

import android.content.Context;

import tv.danmaku.ijk.media.player.IjkMediaPlayer;
import xyz.doikki.videoplayer.ijk.IjkPlayer;

public class MyIjkPlayer extends IjkPlayer {

    public MyIjkPlayer(Context context) {
        super(context);
    }

    @Override
    public void setOptions() {
        mMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "mediacodec", 1);
        mMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "mediacodec-auto-rotate", 1);
        mMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "mediacodec-handle-resolution-change", 1);
        mMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "max_cached_duration", 3000);
        mMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_FORMAT, "analyzeduration", 1);
    }

}
