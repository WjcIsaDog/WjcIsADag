package rmi.companydata;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.util.ArrayList;

import message.OperationMessage;
import po.companydata.CarPO;
import rmi.DataService;

/** 
 * Connection//rmi.companydata//CompanyDataCarService.java
 * @author CXWorks
 * @date 2015年10月25日 下午2:53:09
 * @version 1.0 
 */
public interface CompanyDataCarService extends DataService<CarPO>{
	
	/** 接口的名称，RMI绑定时候的名称 */
	public static final String NAME = "CompanyDataCar";

	public ArrayList<CarPO> getCars(String unitID) throws RemoteException;
	
	public CarPO getCar(String carID) throws RemoteException;
	
	public String newCarID(String unitID) throws RemoteException;
	
	public OperationMessage addCar(CarPO car) throws RemoteException;
	
	public OperationMessage deleteCar(CarPO car) throws RemoteException;
	
	public OperationMessage modifyCar(CarPO car) throws RemoteException;
	
	public ArrayList<CarPO> availableCar(String unitID) throws RemoteException;
}
