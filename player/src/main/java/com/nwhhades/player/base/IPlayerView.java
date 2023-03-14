package com.nwhhades.player.base;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

public interface IPlayerView {

    enum PlayState {
        STATE_ERROR,
        STATE_IDLE,
        STATE_PREPARING,
        STATE_PREPARED,
        STATE_PLAYING,
        STATE_PAUSED,
        STATE_PLAYBACK_COMPLETED,
        STATE_BUFFERING,
        STATE_BUFFERED,
        STATE_MUTE,
        STATE_UN_MUTE
    }

    enum ScaleType {
        SCALE_DEFAULT,
        SCALE_16_9,
        SCALE_4_3,
        SCALE_MATCH_PARENT,
        SCALE_ORIGINAL,
        SCALE_CENTER_CROP
    }

    interface OnPlayListener {

        void onPlayErr(Exception e);

        void onPlayCompleted();

        void onPlayState(PlayState state);

        void onProgress(int progress, long time1, long time2);

    }

    View getView(Context context);

    void addToVideoView(ViewGroup root);

    void delToVideoView(ViewGroup root);

    void initView(ViewGroup root);

    void destroyView(ViewGroup root);

    void setOnPlayListener(OnPlayListener onPlayListener);

    OnPlayListener getOnPlayListener();

    /**
     * 直接播放地址
     *
     * @param url 地址
     */
    void setUrl(String url);

    /**
     * 重新播放
     */
    void replay();

    /**
     * 预加载
     */
    void prepare();

    /**
     * 开始播放
     */
    void start();

    /**
     * 继续播放
     */
    void resume();

    /**
     * 暂停播放
     */
    void pause();

    /**
     * 停止播放
     */
    void stop();

    /**
     * 释放播放器
     */
    void release();

    /**
     * 移动到某个时间戳
     *
     * @param ms 时间戳
     */
    void seekTo(long ms);

    /**
     * 设置是否循环播放
     *
     * @param looping 是否循环
     */
    void setLooping(boolean looping);

    /**
     * 是否在播放
     *
     * @return 播放状态
     */
    boolean isPlaying();

    /**
     * 是否静音
     *
     * @return 静音状态
     */
    boolean isMute();

    /**
     * 设置静音
     *
     * @param mute 是否静音
     */
    void setMute(boolean mute);

    /**
     * 返回当前播放时间戳
     *
     * @return 时间戳
     */
    long getCurrentPosition();

    /**
     * 返回视频总时长
     *
     * @return 总时长
     */
    long getDuration();

    /**
     * 设置进度
     *
     * @param progress 进度
     */
    void setProgress(int progress);

    /**
     * 返回播放进度
     *
     * @return 播放进度
     */
    int getProgress();

    /**
     * 设置播放速度
     *
     * @param speed 播放速度
     */
    void setPlaySpeed(float speed);

    /**
     * 获取播放速度
     *
     * @return 播放速度
     */
    float getPlaySpeed();

    /**
     * 设置画面比例
     *
     * @param scaleType 画面比例
     */
    void setScaleType(ScaleType scaleType);

    /**
     * 获取画面比例
     *
     * @return 画面比例
     */
    ScaleType getScaleType();

    /**
     * 设置播放器状态
     *
     * @param playState 播放状态
     */
    void setPlaySate(PlayState playState);

    /**
     * 返回播放状态
     *
     * @return 播放状态
     */
    PlayState getPlaySate();

    /**
     * 设置异常状态
     *
     * @param e 异常
     */
    void setErrState(Exception e);

    /**
     * 设置播放完成状态
     */
    void setCompletion();

    void startF5Progress();

    void stopF5Progress();

    void f5Progress();

}
