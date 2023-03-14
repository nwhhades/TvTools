package com.nwhhades.player.base;

public interface IPlayerViewFactory<T extends IPlayerView> {

    T getPlayerView();

}
