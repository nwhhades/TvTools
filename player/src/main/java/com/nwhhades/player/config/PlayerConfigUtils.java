package com.nwhhades.player.config;

import android.util.Log;

import com.blankj.utilcode.util.CacheDiskStaticUtils;
import com.nwhhades.player.base.IPlayerViewFactory;
import com.nwhhades.player.simple.AndroidPlayerFactory;

import java.util.List;

public enum PlayerConfigUtils {
    INSTANCE;

    public static class Version {
        private static String version = "1.0";

        public static void setVersion(String version) {
            Version.version = version;
        }
    }

    //提供一个接口来添加自定义的播放器
    public interface Impl {
        void action(VideoPlayerConfig config);
    }

    private static final String TAG = "PlayerConfigUtils";
    private final String key;
    private Impl impl;
    private VideoPlayerConfig playerConfig;

    PlayerConfigUtils() {
        key = TAG + Version.version;
        VideoPlayerConfig videoPlayerConfig = (VideoPlayerConfig) CacheDiskStaticUtils.getSerializable(key);
        if (videoPlayerConfig != null) {
            Log.d(TAG, "PlayerConfigUtils: 读取配置" + key);
            playerConfig = videoPlayerConfig;
        } else {
            Log.d(TAG, "PlayerConfigUtils: 初次使用");
        }
    }

    private void save() {
        CacheDiskStaticUtils.put(key, playerConfig);
    }

    private VideoPlayerConfig getPlayerConfig() {
        if (playerConfig == null) {
            playerConfig = new VideoPlayerConfig();
            if (impl != null) {
                impl.action(playerConfig);
            }
            save();
        }
        return playerConfig;
    }

    public void setImpl(Impl impl) {
        this.impl = impl;
        getPlayerConfig();
    }

    public void setCurPlayer(String player) {
        getPlayerConfig().setCurPlayerName(player);
        save();
    }

    public int getCurPlayer() {
        List<String> list = getPlayerList();
        if (list != null) {
            return list.indexOf(getPlayerConfig().getCurPlayerName());
        }
        return 0;
    }

    public List<String> getPlayerList() {
        return getPlayerConfig().getPlayerList();
    }

    public IPlayerViewFactory<?> getPlayerViewFactory() {
        try {
            Log.d(TAG, "getPlayerViewFactory: 使用播放器-" + playerConfig.getCurPlayerName());
            String className = playerConfig.getPlayerClassName();
            if (className != null) {
                Object o = Class.forName(className).newInstance();
                if (o instanceof IPlayerViewFactory) {
                    return (IPlayerViewFactory<?>) o;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new AndroidPlayerFactory();
    }

    public void clear() {
        CacheDiskStaticUtils.remove(key);
    }

}
