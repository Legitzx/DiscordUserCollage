package me.legitzx.bot.processing;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ImageProcessor {
    /**
     * Creates the new base image, and appends all the avatars onto it
     * @param total     Total number of users in the discord
     */
    public void process(int total) {
        double sqrt = Math.sqrt(total) + 1; // Gets the height and width for the square
        final int width = (int) Math.floor(sqrt) * 125; // Multiplies the width by 125px
        final int height = (int) Math.floor(sqrt) * 125; // Multiplies the height by 125px

        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB); // Creating the new resized image
        Graphics2D g2d = bufferedImage.createGraphics();

        g2d.setColor(Color.BLACK); // Makes the background black
        g2d.fillRect(0,0, width, height);

        HashMap<Integer, String> arr = new HashMap<>(); // This HashMap will store all the user avatar file names

        for(int x = 0; x < total; x++) { // This just loops through all the user avatars that have been saved, and puts their file name into the HashMap

            String fileName = Integer.toString(x);
            arr.put(x, fileName);

        }

        int count = 0;

        for(int x = 0; x < width; x += 125) {   // x axis ( Basically moving the picture to the right 125px )
            for(int y = 0; y < height; y += 125) {   // y axis ( Basically moving the picture down 125px )
                String fileName;

                if(count == 0) { // Logic to get the proper file name
                    fileName = arr.get(count);
                    count++;
                } else {
                    fileName = arr.get(count);
                    count++;
                }


                BufferedImage img = null;
                try {
                    img = ImageIO.read(new File(fileName + ".png")); // Reads all the avatars
                } catch (IOException e) {
                }

                g2d.drawImage(img, y,x,null); // I had to switch the x and the y for it to display properly -- Not sure why; it just works....
            }
        }

        g2d.dispose(); // Release all system resources

        try {
            ImageIO.write(bufferedImage, "png", new File("collage.png")); // This saves the final image to the folder directory
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    /**
     * Downloads the avatars from discords cdn, then it resizes them all to 125x125
     * @param urlLink       The URL which the image is linked to
     * @param fileName      All the images have a unique file name started from 0, and increment by 1 each image
     */
    public void downloadImage(String urlLink, String fileName) {
        try {
            URL url = new URL(urlLink); // https://www.avajava.com/tutorials/lessons/how-do-i-save-an-image-from-a-url-to-a-file.html
            InputStream is = url.openStream();
            OutputStream os = new FileOutputStream(fileName);

            byte[] b = new byte[2048];
            int length;

            while ((length = is.read(b)) != -1) {
                os.write(b, 0, length);
            }

            BufferedImage img = null;
            try {
                img = ImageIO.read(new File(fileName));
            } catch (IOException e) {
                e.printStackTrace();
            }

            BufferedImage resizedImage = new BufferedImage(125, 125, BufferedImage.TYPE_INT_RGB); // Resizes all the avatars to 125x125
            Graphics2D g = resizedImage.createGraphics();
            g.drawImage(img, 0, 0, 125, 125, null);
            g.dispose();

            ImageIO.write(resizedImage, "png", new File(fileName));

            is.close();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
