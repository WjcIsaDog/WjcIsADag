package ui.common;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import userinfo.UserInfo;

import java.io.File;
import java.io.IOException;

/**
 * Created by Sissel on 2015/12/27.
 */
public class SettingDialogController {
    public TextField workspace_Field;
    public TextField ip_Field;
    public TextField port_Field;

    private Stage thisStage;

    public static Stage newDialog(Stage owner){
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("系统设置");
        stage.initOwner(owner);

        try {
            FXMLLoader loader = new FXMLLoader(SettingDialogController.class.getResource("settingDialog.fxml"));
            Pane pane = loader.load();
            SettingDialogController controller = loader.getController();
            stage.setScene(new Scene(pane));

            controller.thisStage = stage;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return stage;
    }

    @FXML
    public void initialize(){
        ip_Field.setText(UserInfo.getIP());
        port_Field.setText(UserInfo.getPort());
        workspace_Field.setText(UserInfo.getWorkPath());
    }

    @FXML
    public void browse(ActionEvent actionEvent) {
        DirectoryChooser dChooser = new DirectoryChooser();
        dChooser.setTitle("选择工作目录");
        File file = dChooser.showDialog(thisStage);
        if(file != null && file.isDirectory()){
            workspace_Field.setText(file.getAbsolutePath());
        }
    }

    @FXML
    public void cancel(ActionEvent actionEvent) {
        thisStage.close();
    }

    @FXML
    public void commit(ActionEvent actionEvent) {
        UserInfo.setIP(ip_Field.getText());
        UserInfo.setPort(port_Field.getText());
        UserInfo.setWorkPath(workspace_Field.getText());
        thisStage.close();
    }
}
