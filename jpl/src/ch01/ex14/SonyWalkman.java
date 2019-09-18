package ch01.ex14;

public class SonyWalkman extends Walkman {

    @Override
    public void listenMusic () {
        System.out.println(getNowMusic() + " sound stereo");
    }
}
