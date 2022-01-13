package FileIOStream;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

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
        fc.getExtensionFilters().addAll(new ExtensionFilter("cs file", "*.txt"));
        saveFile = fc.showSaveDialog(null);
        try {
            FileOutputStream fout = new FileOutputStream(
                    saveFile, true);
            String s = outputArea.getText();
            byte b[] = s.getBytes();//converting string into byte array
            fout.write(b);
            fout.close();
        } catch (Exception e) {
        }


        alert.setContentText("File is written succesfuly!");
        alert.showAndWait();

    }


    public void SaveAction(ActionEvent event) {
        try {
            fc.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.txt"));
            selectedFile = fc.showOpenDialog(null);
            FileInputStream fin = new FileInputStream(selectedFile);
            int i = 0;
            String textRead = "";
            while ((i = fin.read()) != -1) {
                textRead = textRead + (char) i;
                //System.out.print( (char)i);
            }
            outputArea.setText(textRead);


            fin.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }


}