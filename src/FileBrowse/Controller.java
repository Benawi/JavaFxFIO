package FileBrowse;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

import java.io.File;
import java.util.List;

public class Controller {
    @FXML
    public Button btn1, btn2;
    @FXML
    public ListView listview;
    @FXML
    public TextArea outputArea;
    List<File> selectedFiles;
    public File selectedFile;
    Alert alert = new Alert(Alert.AlertType.INFORMATION);

    /**
     * select single pdf file
     */
    public void Button1Action(ActionEvent event) {
        FileChooser fc = new FileChooser();
        // if we want to open fixed location
        //fc.setInitialDirectory(new File("D:\\\\directory"));

        // if we want to fixed file extension
        fc.getExtensionFilters().addAll(new ExtensionFilter("PDF Files", "*.PDF"));
        selectedFile = fc.showOpenDialog(null);

        if (selectedFile != null) {
            listview.getItems().add(selectedFile.getAbsoluteFile());
            analyzePath();
        } else {
            alert.setContentText("File is not selected!");
            alert.showAndWait();

        }
    }

    /**
     * Select multiple files
     */
    public void Button2Action(ActionEvent event) {
        FileChooser fc = new FileChooser();

        selectedFiles = fc.showOpenMultipleDialog(null);

        if (selectedFiles != null) {
            for (int i = 0; i < selectedFiles.size(); i++) {
                listview.getItems().add(selectedFiles.get(i).getAbsolutePath());
            }
        } else {
            alert.setContentText("File is not selected!");
            alert.showAndWait();
        }
    }



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