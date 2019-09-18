package ch01.ex14;

import java.util.ArrayList;
import java.util.List;

public class Walkman {
    private boolean isPowerOn;
    private List<String> listMusic = new ArrayList<String>() {
        {
            add("musicA");
            add("musicB");
            add("musicC");
        }
    };
    private int nowMusicIndex;

    public void setPowerOn(boolean powerOn) {
        isPowerOn = powerOn;
    }

    public void setNowMusicIndex (int index) {
        this.nowMusicIndex = index;
    }

    public String getNowMusic () {
        return listMusic.get(nowMusicIndex);
    }

    public void listenMusic () {
        System.out.println(getNowMusic() + " sound monaural");
    }
}
