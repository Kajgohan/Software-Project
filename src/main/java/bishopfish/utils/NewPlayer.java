package bishopfish.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

public class NewPlayer extends Thread {

    public static boolean stopped;

    private String filename;

    private Position curPosition;

    private final int EXTERNAL_BUFFER_SIZE = 524288; // 128Kb

    enum Position {
        LEFT, RIGHT, NORMAL
    };

    public NewPlayer(String wavfile) {
        filename = wavfile;
        curPosition = Position.NORMAL;
    }

    public NewPlayer(String wavfile, Position p) {
        filename = wavfile;
        curPosition = p;
    }

    public static void reset(){
        stopped = false;
    }

    public void run() {
        while (!stopped) {
            InputStream tmp = getClass().getClassLoader().getResourceAsStream(filename);
            File soundFile = new File(
                    filename
            );
            System.out.println(tmp);
//            if (!soundFile.exists()) {
//                System.err.println("Wave file not found: " + filename);
//                return;
//            }

            AudioInputStream audioInputStream = null;
            try {
                System.out.println("here1");
                InputStream bufferedIn = new BufferedInputStream(tmp);
                audioInputStream = AudioSystem.getAudioInputStream(bufferedIn);
                System.out.println("here2");
            } catch (UnsupportedAudioFileException e1) {
                e1.printStackTrace();
                return;
            } catch (IOException e1) {
                e1.printStackTrace();
                return;
            }

            AudioFormat format = audioInputStream.getFormat();
            SourceDataLine auline = null;
            DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);

            try {
                auline = (SourceDataLine) AudioSystem.getLine(info);
                auline.open(format);
            } catch (LineUnavailableException e) {
                e.printStackTrace();
                return;
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }

            if (auline.isControlSupported(FloatControl.Type.PAN)) {
                FloatControl pan = (FloatControl) auline
                        .getControl(FloatControl.Type.PAN);
                if (curPosition == Position.RIGHT)
                    pan.setValue(1.0f);
                else if (curPosition == Position.LEFT)
                    pan.setValue(-1.0f);
            }

            auline.start();
            int nBytesRead = 0;
            byte[] abData = new byte[EXTERNAL_BUFFER_SIZE];

            try {
                while (nBytesRead != -1) {
                    nBytesRead = audioInputStream.read(abData, 0, abData.length);
                    if (nBytesRead >= 0)
                        auline.write(abData, 0, nBytesRead);
                }
            } catch (IOException e) {
                e.printStackTrace();
                return;
            } finally {
                auline.drain();
                auline.close();
            }
        }
    }



    public static void main(String[] args){
        stopped = false;
        new NewPlayer("src\\main\\resources\\bishopfish\\FreeMindfulness3MinuteBreathing.wav").start();
    }
}
