package transposeAlgo;

/* CONSIDERATIONS - 

    images will be folderised ie. 
        
    Top level folder 
        ¦
        ¦ Gnome 1 
        ¦  ¦
        ¦  accessories 1.
        ¦  ¦
        ¦  accessories 2. 
        ¦  ¦
        ¦  background
        ¦
        ¦ Gnome 2 
        ¦  ¦
        ¦  accessories 1.
        ¦  ¦
        ¦  accessories 2. 
        ¦  ¦
        ¦  background

    However any number of gnomes / accessories may be used. 

    */

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.Console;
import java.io.File;
import java.io.IOException;
 
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
 
public class transposeAlgo {
 
    public static void main(String args[]) {

        Console c = initialiseConsole();

        String inputPath = c.readLine("Enter top level folder: ");

        String[] pathnames = returnContents(inputPath);
        
        System.out.println("\n Second level folders - \n"); 

        for (String pathname : pathnames) {
            // Print the names of files and directories
            System.out.println(pathname);
        }

        
        if (assertConfirm(c) ) {

        /* process images one folder at a time. 
        start for-each loop for all folders inside top level fodler (TLD)
            1. delve into first folder, check for files. 
            2. If files found, call 'constructGnome' if not, 
            3. get list of folders again.
            4. if files found... 
            5. Construct Gnome: 
                begin for-each loops to combine all images into all permutations. May take a while. 
        end-for each loop.         
          

        */
        }
    }




        /**

        // For each pathname in the pathnames array
        for (String pathname : pathnames) {
            // Print the names of files and directories
            System.out.println(pathname);
        }

        // end path code 

        BufferedImage gnomes[] = readImage(gnomelist)

        BufferedImage bgImage = readImage("C:/temp/myBGImage.jpg");

        BufferedImage fgImage = readImage("C:/temp/myFGImage.jpg");
 
        // Do the overlay of foreground image on background image
         
        BufferedImage overlayedImage = overlayImages(bgImage, fgImage);
 
        
         //Write the overlayed image back to file
         
        if (overlayedImage != null){
            writeImage(overlayedImage, "C:/temp/overLayedImage.png", "PNG");
            System.out.println("Overlay Completed...");
        }else
            System.out.println("Problem With Overlay...");
        }
 
         */
    
    public static boolean assertConfirm(Console c) {    

        String confirm = c.readLine("Please confirm exec with Y/N: ");

        if (confirm.toUpperCase() == "Y")
        {
            return true;
        } else { 
            return false;
        }

    }

    public static Console initialiseConsole() {
        
        Console console = System.console();

        return console;

    }

    public static String[] returnContents(String path) {

        String[] contentsList = new String[] {""} ;

        try {
            File f = new File(path);
            
            contentsList =f.list();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return contentsList;
    }
 
    /**
     * Method to overlay Images
     *
     * @param bgImage --> The background Image
     * @param fgImage --> The foreground Image
     * @return --> overlayed image (fgImage over bgImage)
     */
    public static BufferedImage overlayImages(BufferedImage bgImage,
            BufferedImage fgImage) {
 
        /**
         * Doing some preliminary validations.
         * Foreground image height cannot be greater than background image height.
         * Foreground image width cannot be greater than background image width.
         *
         * returning a null value if such condition exists.
         */
        if (fgImage.getHeight() > bgImage.getHeight()
                || fgImage.getWidth() > fgImage.getWidth()) {
            JOptionPane.showMessageDialog(null,
                    "Foreground Image Is Bigger In One or Both Dimensions"
                            + "nCannot proceed with overlay."
                            + "nn Please use smaller Image for foreground");
            return null;
        }
 
        /**Create a Graphics  from the background image**/
        Graphics2D g = bgImage.createGraphics();
        /**Set Antialias Rendering**/
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        /**
         * Draw background image at location (0,0)
         * You can change the (x,y) value as required
         */
        g.drawImage(bgImage, 0, 0, null);
 
        /**
         * Draw foreground image at location (0,0)
         * Change (x,y) value as required.
         */
        g.drawImage(fgImage, 0, 0, null);
 
        g.dispose();
        return bgImage;
    }
 
    /**
     * This method reads an image from the file
     * @param fileLocation -- > eg. "C:/testImage.jpg"
     * @return BufferedImage of the file read
     */
    public static BufferedImage readImage(String fileLocation) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(fileLocation));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }
 
    /**
     * This method writes a buffered image to a file
     * @param img -- > BufferedImage
     * @param fileLocation --> e.g. "C:/testImage.jpg"
     * @param extension --> e.g. "jpg","gif","png"
     */
    public static void writeImage(BufferedImage img, String fileLocation,
            String extension) {
        try {
            BufferedImage bi = img;
            File outputfile = new File(fileLocation);
            ImageIO.write(bi, extension, outputfile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
