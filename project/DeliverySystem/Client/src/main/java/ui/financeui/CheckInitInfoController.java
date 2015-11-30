package ui.financeui;

import bl.blService.initblService.InitializationBLService;
import factory.InitBLFactory;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import po.systemdata.SystemState;
import userinfo.UserInfo;
import vo.financevo.BankAccountVO;

import java.io.IOException;

/**
 * Created by Sissel on 2015/11/27.
 */
public class CheckInitInfoController {

    public static final String BANK_ACCOUNT_INIT = "账户期初信息";
    public static final String INSTITUTION_INIT = "机构期初信息";
    public static final String STAFF_INIT = "员工期初信息";
    public static final String STORE_INIT = "仓库期初信息";
    public static final String CAR_INIT = "车辆期初信息";

    private InitializationBLService initBLService = InitBLFactory.getInitializationBLService();

    public TableView info_TableView;
    public Label systemState_Label;
    public ChoiceBox<String> initType_ChoiceBox;

    public static Parent launch() throws IOException {
        return FXMLLoader.load(CheckInitInfoController.class.getResource("checkInitInfo.fxml"));
    }

    @FXML
    public void initialize(){
        // initialize choiceBox
        initType_ChoiceBox.setItems(
                FXCollections.observableArrayList
                        (BANK_ACCOUNT_INIT, INSTITUTION_INIT, STAFF_INIT, STORE_INIT, CAR_INIT)
        );
        initType_ChoiceBox.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    switch (newValue) {
                        case BANK_ACCOUNT_INIT:
                            showBankAccounts();
                            break;
                        case INSTITUTION_INIT:
                            showInstitutions();
                            break;
                        case STAFF_INIT:
                            showStaffs();
                            break;
                        case STORE_INIT:
                            showStroes();
                            break;
                        case CAR_INIT:
                            showCars();
                            break;
                    }
                }
        );
        initType_ChoiceBox.setValue(initType_ChoiceBox.getItems().get(0));
        showBankAccounts();

        // TODO test to be removed
        UserInfo.setSystemState(SystemState.NORMAL);
        systemState_Label.setText(UserInfo.getSystemState().getChinese());
    }

    public void applyForInitialization(ActionEvent actionEvent) {
        initBLService.requestInitData();
        // TODO jump
    }

    private void showBankAccounts(){
        TableColumn<BankAccountVO, String> name_TableColumn = new TableColumn("账户名");
        TableColumn<BankAccountVO, String> balance_TableColumn = new TableColumn("余额");

        name_TableColumn.setCellValueFactory(
                cellData -> new SimpleStringProperty(cellData.getValue().getAccountName())
        );
        balance_TableColumn.setCellValueFactory(
                cellData -> new SimpleStringProperty(cellData.getValue().getBalance())
        );

        info_TableView.getColumns().clear();
        info_TableView.getItems().clear();
        info_TableView.getColumns().addAll(name_TableColumn, balance_TableColumn);
        //info_TableView.getItems().addAll(initBLService.getAllAccounts());
        info_TableView.getItems().addAll(
                new BankAccountVO("123", "soft", "450"),
                new BankAccountVO("233", "hard", "99999")
        );
    }

    private void showCars(){

    }

    private void showInstitutions(){

    }

    private void showStaffs(){

    }

    private void showStroes(){

    }
}