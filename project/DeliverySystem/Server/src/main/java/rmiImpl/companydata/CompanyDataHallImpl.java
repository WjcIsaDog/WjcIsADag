package rmiImpl.companydata;

import java.util.ArrayList;

import message.OperationMessage;
import po.companydata.HallPO;
import rmi.companydata.companyDataHallService;

public class CompanyDataHallImpl implements companyDataHallService {

	public ArrayList<HallPO> getHall() {
		// TODO Auto-generated method stub
		ArrayList<HallPO> result =new ArrayList<HallPO>();
		HallPO stub=new HallPO();
		result.add(stub);
		return result;
	}

	public OperationMessage addHall(HallPO hall) {
		// TODO Auto-generated method stub
		return new OperationMessage();
	}

	public OperationMessage deleteHall(HallPO hall) {
		// TODO Auto-generated method stub
		return new OperationMessage();
	}

	public OperationMessage modifyHall(HallPO hall) {
		// TODO Auto-generated method stub
		return new OperationMessage();
	}


	public String newHallID() {
		// TODO Auto-generated method stub
		return "111111";
	}

}