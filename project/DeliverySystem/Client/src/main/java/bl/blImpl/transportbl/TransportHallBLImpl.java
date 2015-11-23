package bl.blImpl.transportbl;

import java.util.ArrayList;

import message.CheckFormMessage;
import message.OperationMessage;
import po.transportdata.LoadPO;
import vo.managevo.car.CarVO;
import vo.managevo.staff.StaffVO;
import vo.ordervo.OrderVO;
import vo.transitvo.LoadVO;
import bl.blService.transportblService.TransportHallBLService;

public class TransportHallBLImpl implements TransportHallBLService {

	
		
		public LoadVO loadDraft() {
			// TODO Auto-generated method stub
			return new LoadVO("050010001201511230000002");
		}
		public OperationMessage saveDraft(LoadVO form) {
			// TODO Auto-generated method stub
			return new OperationMessage ();
		}
		public ArrayList<CheckFormMessage> checkFormat(LoadVO form,
				boolean isFinal) {
			// TODO Auto-generated method stub
			ArrayList<CheckFormMessage> result=new ArrayList<CheckFormMessage>();
			CheckFormMessage stub=new CheckFormMessage();
			result.add(stub);
			return result;

		}
		public OperationMessage submit(LoadVO form) {
			// TODO Auto-generated method stub
			return new OperationMessage();
		}
		/* (non-Javadoc)
		 * @see bl.blService.FormBLService#newID()
		 */
		public String newID() {
			// TODO Auto-generated method stub
			return "";
		}
		/* (non-Javadoc)
		 * @see bl.blService.transportblService.TransportHallBLService#getDrivers(java.lang.String)
		 */
		public ArrayList<StaffVO> getDrivers(String hallID) {
			// TODO Auto-generated method stub
			ArrayList<StaffVO> result=new ArrayList<StaffVO>();
			StaffVO stub=new StaffVO();
			result.add(stub);
			return result;
		}
		/* (non-Javadoc)
		 * @see bl.blService.transportblService.TransportHallBLService#getCars(java.lang.String)
		 */
		public ArrayList<CarVO> getCars(String hallID) {
			// TODO Auto-generated method stub
			ArrayList<CarVO> result=new ArrayList<CarVO>();
			CarVO stub=new CarVO();
			result.add(stub);
			return result;
		}
		/* (non-Javadoc)
		 * @see bl.blService.transportblService.TransportHallBLService#getLocation(java.lang.String)
		 */
		public ArrayList<Object> getLocation(String hallID) {
			// TODO Auto-generated method stub
			ArrayList<Object> result=new ArrayList<Object>();
			Object stub=new Object();
			result.add(stub);
			return result;
		}
		/* (non-Javadoc)
		 * @see bl.blService.transportblService.TransportHallBLService#getOrder(java.lang.String)
		 */
		public OrderVO getOrder(String orderID) {
			// TODO Auto-generated method stub
			return new OrderVO("1123000001");
		}
}
