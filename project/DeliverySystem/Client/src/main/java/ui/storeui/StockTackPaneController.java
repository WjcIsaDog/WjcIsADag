package ui.storeui;

import bl.blService.storeblService.StockTackBLService;
import factory.FormFactory;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import model.store.StoreArea;
import model.store.StoreAreaCode;
import model.store.StoreLocation;
import model.store.StoreModel;
import tool.ui.Enum2ObservableList;
import tool.ui.OrderVO2ColumnHelper;
import tool.ui.SimpleEnumProperty;
import ui.informui.InformController;
import vo.ordervo.OrderVO;
import vo.storevo.StockTackVO;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Sissel on 2015/12/8.
 */
public class StockTackPaneController {

	public enum SelectingItem {
		AREA, ROW, SHELF, POSITION
	}

	public AnchorPane fatherPane;
	public TableView<Map.Entry<String, String>> message_TableView;
	public TableColumn<Map.Entry<String, String>, String> key_TableColumn;
	public TableColumn<Map.Entry<String, String>, String> value_TableColumn;

	public TableView<SimpleEnumProperty<StoreAreaCode>> area_TableView;
	public TableColumn<SimpleEnumProperty<StoreAreaCode>, String> area_Column;

	public TableView<StoreLocation> row_TableView;
	public TableColumn<StoreLocation, String> row_Column;

	public TableView<StoreLocation> shelf_TableView;
	public TableColumn<StoreLocation, String> shelf_Column;

	public TableView<StoreLocation> position_TableView;
	public TableColumn<StoreLocation, String> position_TableColumn;
	public TableColumn<StoreLocation, String> order_TableColumn;

	public StockTackBLService stockTackBLService;// 通过setStoreModel初始化
	public StoreModel storeModel;
	public StoreArea storeArea;
	public IntegerProperty selectedRow = new SimpleIntegerProperty();
	public IntegerProperty selectedShelf = new SimpleIntegerProperty();
	public IntegerProperty selectedPosition = new SimpleIntegerProperty();
	public SelectingItem selecting = null;

	private InformController informController;

	public void setStoreModel(StoreModel model) {
		storeModel = model;
		redirectArea(StoreAreaCode.AIR);
	}

	public void setStockTackBLService(StockTackBLService stockTackBLService) {
		this.stockTackBLService = stockTackBLService;
	}

	@FXML
	public void initialize() {
		// set cell value factories
		area_Column.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getEnum().getChinese()));
		row_Column.setCellValueFactory(cell -> {
			int row = cell.getValue().getRow();
			if (row < 0) {
				return new SimpleStringProperty("All");
			} else {
				return new SimpleStringProperty(row + "");
			}
		});
		shelf_Column.setCellValueFactory(cell -> {
			int shelf = cell.getValue().getShelf();
			if (shelf < 0) {
				return new SimpleStringProperty("All");
			} else {
				return new SimpleStringProperty(shelf + "");
			}
		});
		position_TableColumn.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getPosition() + ""));
		order_TableColumn.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getOrderID() + ""));
		key_TableColumn.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getKey()));
		value_TableColumn.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getValue()));

		// set onClickListener
		area_TableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			clear(row_TableView, shelf_TableView, position_TableView, message_TableView);
			redirectArea(newValue.getEnum());
			selecting = SelectingItem.AREA;
		});
		row_TableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			clear(shelf_TableView, position_TableView, message_TableView);
			selectedRow.setValue(newValue.getRow());
			selectedShelf.setValue(1);
			selectedPosition.setValue(1);
			selecting = SelectingItem.ROW;
			List<StoreLocation> storeLocations = storeArea.getByRow(selectedRow.getValue());
			setShelf_TableView(storeLocations);
			position_TableView.getItems()
					.addAll(storeArea.getByShelf(selectedRow.getValue(), selectedShelf.getValue()));
		});
		shelf_TableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			clear(position_TableView, message_TableView);
			selectedShelf.setValue(newValue.getShelf());
			selectedPosition.setValue(1);
			selecting = SelectingItem.SHELF;
			List<StoreLocation> storeLocations = storeArea.getByShelf(selectedRow.getValue(), selectedShelf.getValue());
			position_TableView.getItems().addAll(storeLocations);
		});
		position_TableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue == null||newValue.getOrderID()==null||newValue.getOrderID().length()!=10) {
				return;
			}
			clear(message_TableView);
			selectedPosition.setValue(newValue.getPosition());
			selecting = SelectingItem.POSITION;
//			System.out.println("stockTackBLService" + stockTackBLService);
			OrderVO orderVO = stockTackBLService.getOrder(newValue.getOrderID());
			if (orderVO != null) {
				message_TableView
						.setItems(FXCollections.observableArrayList(new OrderVO2ColumnHelper().VO2Entries(orderVO)));
			}
		});

		area_TableView.setItems(Enum2ObservableList.transit(StoreAreaCode.values()));

		// set resize properties
		AnchorPane.setTopAnchor(fatherPane, 0.0);
		AnchorPane.setBottomAnchor(fatherPane, 0.0);
		AnchorPane.setLeftAnchor(fatherPane, 0.0);
		AnchorPane.setRightAnchor(fatherPane, 0.0);

		position_TableView.prefWidthProperty().bind(fatherPane.widthProperty().divide(4));
		message_TableView.prefWidthProperty().bind(fatherPane.widthProperty().divide(3));
	}

	public void clear(TableView... tableViews) {
		for (TableView tableView : tableViews) {
			tableView.getItems().clear();
		}
	}

	public void setRow_TableView(List<StoreLocation> locations) {
		outer: for (StoreLocation location : locations) {
			ObservableList<StoreLocation> observableList = row_TableView.getItems();
			for (StoreLocation storeLocation : observableList) {
				if (storeLocation.getRow() == location.getRow()) {
					continue outer;
				}
			} // the row number in location has not been inserted
			observableList.add(location);
		}
	}

	public void setShelf_TableView(List<StoreLocation> locations) {
		outer: for (StoreLocation location : locations) {
			ObservableList<StoreLocation> observableList = shelf_TableView.getItems();
			for (StoreLocation storeLocation : observableList) {
				if (storeLocation.getShelf() == location.getShelf()) {
					continue outer;
				}
			} // the row number in location has not been inserted
			observableList.add(location);
		}
	}

	public void redirectArea(StoreAreaCode code) {
		storeArea = storeModel.getArea(code);
		selectedRow.setValue(1);
		selectedShelf.setValue(1);
		selectedPosition.setValue(1);
		// TODO may be bug here (hard code
		// exactly the shelf table view display the locations of the getByRow,
		// etc
		setRow_TableView(storeArea.getList());
		setShelf_TableView(storeArea.getByRow(selectedRow.getValue()));
		position_TableView.getItems().addAll(storeArea.getByShelf(selectedRow.getValue(), selectedShelf.getValue()));
	}

	public String generatePath() {
		String path = storeArea.getAreaID().getChinese() + "区/" + selectedRow.getValue() + "排/"
				+ selectedShelf.getValue() + "架/" + selectedPosition.getValue() + "号";
		return path;
	}

	/**
	 * TODO important not finished
	 */
	public void deleteRow() {

	}

	public void deleteShelf() {

	}

	public void deletePosition() {

	}
}
