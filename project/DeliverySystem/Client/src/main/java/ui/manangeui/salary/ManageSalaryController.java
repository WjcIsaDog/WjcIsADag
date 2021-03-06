/**
 * Client//ui.manangeui.salary//ManageSalaryController.java
 * 下午7:52:21
 * @Author CXWorks
 */
package ui.manangeui.salary;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import javax.swing.table.TableCellEditor;

import po.InfoEnum;
import po.memberdata.StaffTypeEnum;
import ui.hallui.RevenueFormController;
import ui.informui.InformController;
import vo.configurationvo.ConfigurationVO;
import vo.configurationvo.SalaryStrategyVO;
import vo.managevo.staff.StaffVO;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import bl.blService.configurationblService.ConfigurationBLService;
import factory.ConfigurationFactory;

public class ManageSalaryController {
	public TabPane tabPane;

	private ArrayList<SalaryStrategyVO> vo;
	private ObservableList<Tab> tabs;
	//
	private StaffTypeSalaryController staffTypeSalaryController;
	//
	private ConfigurationBLService configurationBLService = ConfigurationFactory.getConfigurationBLService();
	//
	private InformController informController;

	@FXML
	public static Parent launch() throws IOException {
		FXMLLoader loader = new FXMLLoader(ManageSalaryController.class.getResource("manageSalaryStrategy.fxml"));
		Pane pane = loader.load();
		ManageSalaryController controller = loader.getController();
		controller.informController = InformController.newInformController(pane);

		return controller.informController.stackPane;
	}

	private void initData() {
		List<ConfigurationVO> vo = configurationBLService.get(InfoEnum.SALARY);
		this.vo = new ArrayList<SalaryStrategyVO>(vo.size());
		for (ConfigurationVO configurationVO : vo) {
			this.vo.add((SalaryStrategyVO) configurationVO);
		}
	}

	@FXML
	public void initialize() {
		//
		tabs = tabPane.getTabs();

		this.initData();
		//
		try {
			this.seletedChange();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//
	@FXML
	private void seletedChange() throws IOException {
		ObservableList<Tab> nuo = tabPane.getTabs();
		this.initData();
		// System.out.println(this.vo.size());
		// if (this.vo.size()<6) {
		// FXMLLoader fxmlLoader=new FXMLLoader();
		// fxmlLoader.setLocation(StaffTypeSalaryController.class.getResource("salary.fxml"));
		// Parent son=fxmlLoader.load();
		// this.staffTypeSalaryController=(StaffTypeSalaryController)fxmlLoader.getController();
		//
		// tabPane.getSelectionModel().getSelectedItem().setContent(son);
		// return;
		// }

		for (Tab tab : nuo) {
			if (tab.isSelected()) {
				//
				for (SalaryStrategyVO salaryStrategyVO : vo) {
					System.out.println(salaryStrategyVO.getStaff().getChinese() + " " + tab.getText());
					if (salaryStrategyVO.getStaff().getChinese().equalsIgnoreCase(tab.getText())) {
						this.staffTypeSalaryController = StaffTypeSalaryController.launch();
						tab.setContent(staffTypeSalaryController.selfPane);
						this.staffTypeSalaryController.change(salaryStrategyVO);
						return;
					}
				}
				this.staffTypeSalaryController = StaffTypeSalaryController.launch();
				tab.setContent(staffTypeSalaryController.selfPane);
				staffTypeSalaryController.setStaffEnum(this.getStaffTypeEnum(tab.getText()));
				return;
			}
		}

	}

	private StaffTypeEnum getStaffTypeEnum(String chin) {
		switch (chin) {
		case "司机":
			return StaffTypeEnum.DRIVER;

		case "系统管理员":
			return StaffTypeEnum.ADMINISTRATOR;
		case "快递员":
			return StaffTypeEnum.DELIVER;
		case "营业厅业务员":
			return StaffTypeEnum.HALL_COUNTERMAN;
		case "中转中心业务员":
			return StaffTypeEnum.CENTER_COUNTERMAN;
		case "仓库管理员":
			return StaffTypeEnum.STOREMAN;
		case "财务人员":
			return StaffTypeEnum.BURSAR;
		}
		return StaffTypeEnum.MANAGER;
	}

}
