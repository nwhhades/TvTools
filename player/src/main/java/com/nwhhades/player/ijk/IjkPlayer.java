package com.nwhhades.player.ijk;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.nwhhades.player.impl.BasePlayerView;

import xyz.doikki.videoplayer.player.BaseVideoView;

public class IjkPlayer extends BasePlayerView {

    private IjkView mView;

    @Override
    public View getView(Context context) {
        if (mView == null) {
            mView = new IjkView(context);
        }
        return mView;
    }

    @Override
    public void initView(ViewGroup root) {
        super.initView(root);
        mView.setClickable(false);
        mView.setFocusable(false);
        mView.setFocusableInTouchMode(false);
        mView.addOnStateChangeListener(new BaseVideoView.SimpleOnStateChangeListener() {
            @Override
            public void onPlayStateChanged(int playState) {
                switch (playState) {
                    case BaseVideoView.STATE_ERROR:
                        setErrState(new Exception("播放错误"));
                        break;
                    case BaseVideoView.STATE_PREPARING:
                        setPlaySate(PlayState.STATE_PREPARING);
                        break;
                    case BaseVideoView.STATE_PREPARED:
                        setPlaySate(PlayState.STATE_PREPARED);
                        break;
                    case BaseVideoView.STATE_PLAYING:
                        setPlaySate(PlayState.STATE_PLAYING);
                        break;
                    case BaseVideoView.STATE_PAUSED:
                        setPlaySate(PlayState.STATE_PAUSED);
                        break;
                    case BaseVideoView.STATE_PLAYBACK_COMPLETED:
                        setCompletion();
                        break;
                    case BaseVideoView.STATE_BUFFERING:
                        setPlaySate(PlayState.STATE_BUFFERING);
                        break;
                    case BaseVideoView.STATE_BUFFERED:
                        setPlaySate(PlayState.STATE_BUFFERED);
                        break;
                    default:
                        break;
                }
            }
        });
    }

    @Override
    public void setUrl(String url) {
        mView.setUrl(url);
    }

    @Override
    public void replay() {
        mView.replay(true);
    }

    @Override
    public void prepare() {
        setPlaySate(PlayState.STATE_PREPARING);
    }

    @Override
    public void start() {
        prepare();
        mView.start();
    }

    @Override
    public void resume() {
        mView.resume();
    }

    @Override
    public void pause() {
        mView.pause();
    }

    @Override
    public void stop() {
        mView.pause();
    }

    @Override
    public void release() {
        mView.release();
    }

    @Override
    public void seekTo(long ms) {
        mView.seekTo(ms);
    }

    @Override
    public boolean isPlaying() {
        return mView.isPlaying();
    }

    @Override
    public void setMute(boolean mute) {
        mView.setMute(mute);
        mMute = mute;
        setPlaySate(mute ? PlayState.STATE_MUTE : PlayState.STATE_UN_MUTE);
    }

    @Override
    public long getCurrentPosition() {
        return mView.getCurrentPosition();
    }

    @Override
    public long getDuration() {
        return mView.getDuration();
    }

}
