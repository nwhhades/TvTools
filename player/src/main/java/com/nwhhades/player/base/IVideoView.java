package com.nwhhades.player.base;

public interface IVideoView {

    void init();

    void addLoadingView();

    void showLoadingView();

    void hideLoadingView();

    void setPlayerView(IPlayerView playerView);

    IPlayerView getPlayerView();

    void setControllerView(IControllerView controllerView);

    IControllerView getControllerView();

    void create();

    void setTitle(String title);

    void play(String url, boolean looping);

    void resume();

    void pause();

    void stop();

    void destroy();

}
