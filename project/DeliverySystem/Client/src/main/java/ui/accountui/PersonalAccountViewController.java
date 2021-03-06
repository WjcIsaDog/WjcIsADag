package ui.accountui;

import java.io.IOException;

import bl.blService.accountblService.AccountBLManageService;
import factory.AccountFactory;
import main.Main;
import ui.common.MainPaneController;
import ui.financeui.AccountEditDialogController;
import ui.hallui.RevenueFormController;
import ui.informui.InformController;
import userinfo.UserInfo;
import vo.accountvo.AccountVO;
import vo.financevo.BankAccountVO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**
 * Created by Sissel on 2015/11/27.
 */
public class PersonalAccountViewController {
    public ImageView icon_ImageView;
    public Label staff_Label;
    public Label id_Label;

    private MainPaneController mpc;
    private AccountBLManageService accountManageService = AccountFactory.getManageService();

	public static Parent launch(MainPaneController mpc) throws IOException {
		FXMLLoader loader = new FXMLLoader(PersonalAccountViewController.class.getResource("personAccountView.fxml"));
        Pane pane = loader.load();
        PersonalAccountViewController controller = loader.getController();
        controller.mpc = mpc;

        return pane;
    }

	@FXML
    public void initialize(){
	    id_Label.setText("ID: "+UserInfo.getUserID());
	}


    public void editPassword(ActionEvent actionEvent)  throws IOException {
        AccountVO AccountVO = accountManageService.getAccountVO(UserInfo.getUserID());
        personAccountViewEditDialogController controller = personAccountViewEditDialogController.newDialog(AccountVO);

        controller.stage.showAndWait();
    }

    public void logout(ActionEvent actionEvent) {
        mpc.popUpPersonView(null);
        Main.logOut();
    }
}
