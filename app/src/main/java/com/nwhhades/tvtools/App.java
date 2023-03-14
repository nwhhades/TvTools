package com.nwhhades.tvtools;

import com.nwhhades.common.base.BaseApplication;
import com.nwhhades.common.net.RequestServer;
import com.nwhhades.common.utils.UrlUtils;
import com.nwhhades.player.config.PlayerConfigUtils;
import com.nwhhades.player.config.VideoPlayer;
import com.nwhhades.player.config.VideoPlayerConfig;

import xyz.doikki.videoplayer.player.VideoViewConfig;
import xyz.doikki.videoplayer.player.VideoViewManager;

public class App extends BaseApplication {

    @Override
    protected void init() {

    }

    @Override
    protected void initUrlUtils() {
        UrlUtils.INSTANCE.setImpl(new UrlUtils.Impl() {
            @Override
            public String actionUrl(String url, String tag) {
                return url;
            }
        });
    }

    @Override
    protected void initPermissions() {

    }

}
