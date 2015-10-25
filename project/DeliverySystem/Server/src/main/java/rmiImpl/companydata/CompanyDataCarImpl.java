package rmiImpl.companydata;

import java.util.ArrayList;

import message.OperationMessage;
import po.companydata.CarPO;
import rmi.companydata.CompanyDataCarService;

/** 
 * Server//rmiImpl.companydata//CompanyDataCarImpl.java
 * @author CXWorks
 * @date 2015年10月25日 下午2:56:26
 * @version 1.0 
 */
public class CompanyDataCarImpl implements CompanyDataCarService {

	/* (non-Javadoc)
	 * @see rmi.companydata.CompanyDataCarService#getCar()
	 */
	public ArrayList<CarPO> getCar() {
		// TODO Auto-generated method stub
		ArrayList<CarPO> result=new ArrayList<CarPO>();
		CarPO stub=new CarPO();
		result.add(stub);
		return result;
	}

	/* (non-Javadoc)
	 * @see rmi.companydata.CompanyDataCarService#newCarID()
	 */
	public String newCarID() {
		// TODO Auto-generated method stub
		return "11111";
	}

	/* (non-Javadoc)
	 * @see rmi.companydata.CompanyDataCarService#addCar(po.configurationdata.CarPO)
	 */
	public OperationMessage addCar(CarPO car) {
		// TODO Auto-generated method stub
		return new OperationMessage();
	}

	/* (non-Javadoc)
	 * @see rmi.companydata.CompanyDataCarService#deleteCar(po.configurationdata.CarPO)
	 */
	public OperationMessage deleteCar(CarPO car) {
		// TODO Auto-generated method stub
		return new OperationMessage();
	}

	/* (non-Javadoc)
	 * @see rmi.companydata.CompanyDataCarService#modifyCar(po.configurationdata.CarPO)
	 */
	public OperationMessage modifyCar(CarPO car) {
		// TODO Auto-generated method stub
		return new OperationMessage();
	}

}