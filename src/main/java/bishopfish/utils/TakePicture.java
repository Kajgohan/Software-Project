package bishopfish.utils;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Array;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

import bishopfish.db.DBUpdater;
import com.github.sarxos.webcam.Webcam;
import org.codehaus.jackson.map.deser.std.ObjectArrayDeserializer;

public class TakePicture {
    private static final Logger LOGGER = Logger.getLogger(DBUpdater.class.getName());

    public static String[] capture() {

        // get default webcam and open it
        Webcam webcam = Webcam.getDefault();
        webcam.open();

        // get image
        BufferedImage image = webcam.getImage();

        String folderName = "ImageCaptures";
        File dir = new File(folderName);
        boolean successful = dir.mkdir();

        //get date for file naming
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        String imageName =("Image taken on " + date.toString());
        imageName = imageName.replace('/','_');
        imageName = imageName.replace(':','_');
        imageName += ".png";
        String newImageName;
        if (successful) {
            newImageName = folderName + "/" + imageName;
        } else {
            newImageName = imageName;
        }

        // save image to PNG file
        try {
            ImageIO.write(image, "png", new File(newImageName));
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Photo failed to write", e);
            newImageName = null;

        }
        return new String[] {newImageName, date.toString()};
    }

    public static void main(String[] args){
        TakePicture tpk = new TakePicture();
        tpk.capture();
    }
}