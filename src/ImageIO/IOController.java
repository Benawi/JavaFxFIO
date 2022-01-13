package ImageIO;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

public class IOController {
    @FXML
    public Button btn1;
    @FXML
    public ImageView iv;

    @FXML
    public TextArea outputArea;
    public File selectedFile;
    public File saveFile;

    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    BufferedImage image = null;
    FileChooser fc = new FileChooser();

    /**
     * select single image file
     */
    public void Button1Action(ActionEvent event) throws FileNotFoundException {


        // if we want to fixed file extension
        fc.getExtensionFilters().addAll(new ExtensionFilter("jpg Files", "*.jpg"));
        selectedFile = fc.showOpenDialog(null);

        if (selectedFile != null) {
            InputStream stream = new FileInputStream(selectedFile);
            Image image = new Image(stream);

            //Setting image to the image view
            iv.setImage(image);
            analyzePath();
        } else {
            alert.setContentText("File is not selected!");
            alert.showAndWait();

        }
    }

    public void SaveAction(ActionEvent event) {
        ImageRead();
        imagewrite();
    }

    public void ImageRead() {
        int width = 963;    //width of the image
        int height = 640;   //height of the image


        //read image
        try {
            //image file path
            image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            image = ImageIO.read(selectedFile);
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }

    }

    //write image
    public void imagewrite() {
        try {
            saveFile = fc.showSaveDialog(null);
            //output file path
            ImageIO.write(image, "jpg", saveFile);
            alert.setContentText(" Successfuly saved");
            alert.showAndWait();
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
    }

    //Detail about file
    public void analyzePath() {
        // create File object based on user input
        File name = selectedFile.getAbsoluteFile();

        if (name.exists()) {
            outputArea.setText(String.format(
                    "%s%s\n%s\n%s\n%s\n%s%s\n%s%s\n%s%s\n%s%s\n%s%s",
                    name.getName(), " exists",
                    (name.isFile() ? "is a file" : "is not a file"),
                    (name.isDirectory() ? "is a directory"
                            : "is not a directory"),
                    (name.isAbsolute() ? "is absolute path"
                            : "is not absolute path"), "Last modified: ",
                    name.lastModified(), "Length: ", name.length(),
                    "Path: ", name.getPath(), "Absolute path: ",
                    name.getAbsolutePath(), "Parent: ", name.getParent()));

            if (name.isDirectory()) // output directory listing
            {
                String[] directory = name.list();
                outputArea.appendText("\n\nDirectory contents:\n");

                for (String directoryName : directory) {
                    outputArea.appendText(directoryName + "\n");
                }
            }
        } else {
            alert.setContentText(" does not exist.");
            alert.showAndWait();

        }
    }
}