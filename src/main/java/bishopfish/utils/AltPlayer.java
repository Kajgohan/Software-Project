package bishopfish.utils;

import javafx.application.Application;
import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class AltPlayer {
    String bip = "src\\main\\resources\\FreeMindfulness3MinuteBreathing.wav";
    Media hit = new Media(new File(bip).toURI().toString());
    MediaPlayer mediaPlayer = new MediaPlayer(hit);
    public void play(){
        mediaPlayer.play();
    }
    public void stop(){
        mediaPlayer.stop();
    }

    public static void main(String[] args){
        final JFXPanel fxPanel = new JFXPanel();
        AltPlayer AP = new AltPlayer();
        AP.play();
    }
}
