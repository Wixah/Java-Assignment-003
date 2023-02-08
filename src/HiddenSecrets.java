/**
 *
 * @author Trevor Hartman
 * @author <Rachelle Iloff>
 *
 * @since Version 1.0
 *
 * created on 2023/02/05
 * heavily revised on 2023/02/07
 */
import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Metadata;
import com.drew.metadata.Directory;
import com.drew.metadata.Tag;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;
import java.nio.file.Path;
// PUT YOUR IMPORTS HERE

public class HiddenSecrets {
    public static void getHiddenSecrets(File file) {
        try {
            Metadata metadata = ImageMetadataReader.readMetadata(
                    new FileInputStream(file)
            );
            for (Directory directory : metadata.getDirectories()) {
                for (Tag tag : directory.getTags()) {
                    System.out.format("[%s] - %s = %s%n",
                            directory.getName(), tag.getTagName(), tag.getDescription());
                }
                if (directory.hasErrors()) {
                    for (String error : directory.getErrors()) {
                        System.err.format("ERROR: %s%n", error);
                    }
                }
            }
        } catch (FileNotFoundException fnfe) {
            System.out.println("That file does not exist. Remember Ollie the Otter images only!");
        } catch (IOException ioe) {
            System.out.println("Problem reading from file stream.");
        } catch (ImageProcessingException ipe) {
            System.out.println("Failed to process the image meta-data");
        }
    }

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        System.out.println("I am a robot. Please enter filepath below. \nPlease note my AI brain has only been programmed to look for images of Ollie the Otter.\n");// Put your code to request a file path,
        String filePath = in.nextLine();// read in a string from System.in,
        Path newPath = Paths.get(filePath);  // convert that string into A Path type using Paths class,
        getHiddenSecrets(newPath.toFile());  // and call the getHiddenSecrets method to get the file's meta-data
       /* HERE */





    }
}
