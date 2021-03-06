package bl.blService.transportblService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import bl.blService.FormBLService;
import message.CheckFormMessage;
import message.OperationMessage;
import po.transportdata.LoadPO;
import vo.managevo.car.CarVO;
import vo.managevo.institution.InstitutionVO;
import vo.managevo.staff.DriverVO;
import vo.managevo.staff.StaffVO;
import vo.ordervo.OrderVO;
import vo.transitvo.CenterOutVO;
import vo.transitvo.LoadVO;

/**
 * 
 * @author mx
 *2015/10/25
 */
public interface TransportHallBLService extends FormBLService<LoadVO> {
	/**
	 * 
	 * 根据营业厅信息获取可用的司机列表
	 * @param hallID
	 * @return
	 */
	public ArrayList<DriverVO> getDrivers(String hallID);
	/**
	 * 获取当前可用车辆信息
	 * @param hallID
	 * @return
	 */
	public ArrayList<String> getCars(String hallID);
	
	/**
	 * 
	 * @param hallID
	 * @return 当前城市营业厅和中转中心编号列表 （还是给我Sring吧，，VO 里也没有InstitutionID啊？
	 */
	
	public ArrayList<String> getLocation(String hallID);
	
	public OrderVO getOrder(String orderID);
	/**
	 * 获得新的汽运编号
	 * @param unitID 机构编号
	 * @return 新的汽运编号
	 */
	public String newTransID(String unitID);
	/**
	 * 自动装车
	 * @param orderVOs
	 * @return
	 */
	public Map<Boolean, List<String>> computeLoadCar(List<OrderVO> orderVOs);
}
