package ui.orderui;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import bl.blService.orderblService.OrderBLService;
import bl.blService.receiveblService.ReceiveBLService;
import factory.FormFactory;
import message.OperationMessage;
import tool.ui.OrderVO2ColumnHelper;
import ui.financeui.CheckRevenueFormController;
import vo.managevo.car.CarVO;
import vo.ordervo.OrderVO;
import vo.receivevo.ReceiveVO;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class PoepleReceiveFormController {



	public TextField name_Field;
	public DatePicker receive_DatePicker;
	public TableView<OrderVOCheckItem> order_TableView;
	public TableColumn<OrderVOCheckItem,String> id_Column;
	public TableColumn<OrderVOCheckItem,String> address_Column;
	public TableColumn<OrderVOCheckItem,String> name_Column;

	public TextField id_Field;

	private OrderVO selected = null;
//			new OrderVO(null, null, null, 
//			null, null, null, null, null, null, null, 
//			null, null, null, null, null, null, null, null, null);
	private OrderVOCheckItem orderVoCheckItem = new OrderVOCheckItem(selected);
	private List<OrderVOCheckItem> orders;

	ReceiveBLService receivebl=FormFactory.getReceiveBLService();
	OrderBLService obl = FormFactory.getOrderBLService();
	//	ReceiveBLService receiveBLService = FormFactory.getReceiveBLService();

	public static Parent launch() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(PoepleReceiveFormController.class.getResource("peopleReceiveForm.fxml"));
		Pane pane=loader.load();
		return pane;
	}

	@FXML
	public void initialize(){
		id_Column.setCellValueFactory(
				cellData -> new SimpleStringProperty(cellData.getValue().getVo().getFormID())
				);
		address_Column.setCellValueFactory(
				cellData -> new SimpleStringProperty(cellData.getValue().getVo().getAddressTo())
				);
		name_Column.setCellValueFactory(
				cellData -> new SimpleStringProperty(cellData.getValue().getVo().getNameTo())
				);
		order_TableView.getSelectionModel().selectedItemProperty().addListener(
				(observable, oldValue, newValue) -> {
					// TODO test
					System.out.println("selected " + newValue.getVo());}
				);
//		selected=newValue.getVo();
//		orderVoCheckItem=newValue;


		receive_DatePicker.setValue(LocalDate.now());
	}

	public void search(ActionEvent actionEvent){
		//TODO
		String id = id_Field.getText();
		OrderVO result=receivebl.getOrderVO(id);
	//	order_TableView.setFocusModel();
	}


	public void commit(ActionEvent actionEvent) {

		OrderVO selected =  order_TableView.getSelectionModel().getSelectedItem().getVo();
		OperationMessage msg = obl.submit(generateOrderVO(selected));
		if(msg.operationResult){
			System.out.println("commit successfully");
			clear(null);
		}else{
			System.out.println("commit fail: " + msg.getReason());
		}

	}

	public OrderVO generateOrderVO(OrderVO ovo) {
		//TODO

		return null;
	}

	public void clear(ActionEvent actionEvent) {
		receive_DatePicker.setValue(LocalDate.now());
		name_Field.clear();
	}

	public void saveDraft(ActionEvent actionEvent) {
		OrderVO selected =  order_TableView.getSelectionModel().getSelectedItem().getVo();
		OrderVO ovo= generateOrderVO(selected);
		obl.saveDraft(ovo);
	}




}
