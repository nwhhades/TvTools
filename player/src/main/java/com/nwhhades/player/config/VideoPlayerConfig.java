package com.nwhhades.player.config;

import androidx.annotation.NonNull;

import com.nwhhades.player.ijk.IjkPlayerFactory;
import com.nwhhades.player.simple.AndroidPlayerFactory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class VideoPlayerConfig implements Serializable {

    private static final long serialVersionUID = 6565912628345970900L;

    private static final String DEF_PLAYER_NAME = "原生播放器";
    private String curPlayerName = DEF_PLAYER_NAME;
    private List<VideoPlayer> list = new ArrayList<>();

    public VideoPlayerConfig() {
        list.add(new VideoPlayer(DEF_PLAYER_NAME, AndroidPlayerFactory.class.getName()));
        list.add(new VideoPlayer("IJK播放器", IjkPlayerFactory.class.getName()));
    }

    public List<VideoPlayer> getList() {
        return list;
    }

    public void setList(List<VideoPlayer> list) {
        this.list = list;
    }

    public List<String> getPlayerList() {
        List<String> stringList = new ArrayList<>();
        for (VideoPlayer videoPlayer : list) {
            stringList.add(videoPlayer.getName());
        }
        return stringList;
    }

    public String getPlayerClassName() {
        for (VideoPlayer videoPlayer : list) {
            if (videoPlayer.getName().equals(curPlayerName)) {
                return videoPlayer.getClassName();
            }
        }
        return null;
    }

    public String getCurPlayerName() {
        return curPlayerName;
    }

    public void setCurPlayerName(String curPlayerName) {
        this.curPlayerName = curPlayerName;
    }

    @NonNull
    @Override
    public String toString() {
        return "VideoPlayerConfig{" +
                "list=" + list +
                '}';
    }

}
