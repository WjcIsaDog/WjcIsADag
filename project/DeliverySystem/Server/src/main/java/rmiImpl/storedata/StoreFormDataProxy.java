package rmiImpl.storedata;

import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Calendar;

import message.OperationMessage;
import po.FormPO;
import po.storedata.StoreInPO;
import po.storedata.StoreOutPO;
import rmi.storedata.StoreFormDataService;
import rmiImpl.initaldata.InitialDataProxy;

public class StoreFormDataProxy extends UnicastRemoteObject implements StoreFormDataService {

	StoreFormDataService storeFormDataService = new StoreFormDataImpl();

	public StoreFormDataProxy() throws RemoteException, MalformedURLException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public Connection getConn() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OperationMessage insertStoreInPO(StoreInPO po) throws RemoteException {
		// TODO Auto-generated method stub
		if(!InitialDataProxy.isSystem_on_initial())
			return storeFormDataService.insertStoreInPO(po);
		return null;
	}

	@Override
	public OperationMessage insertStoreOutPO(StoreOutPO po) throws RemoteException {
		// TODO Auto-generated method stub
		if(!InitialDataProxy.isSystem_on_initial())
			return storeFormDataService.insertStoreOutPO(po);
		return null;
	}

	@Override
	public OperationMessage deleteStoreInPO(String id) throws RemoteException {
		// TODO Auto-generated method stub
		if(!InitialDataProxy.isSystem_on_initial())
			return storeFormDataService.deleteStoreInPO(id);
		return null;
	}

	@Override
	public OperationMessage deleteStoreOutPO(String id) throws RemoteException {
		// TODO Auto-generated method stub
		if(!InitialDataProxy.isSystem_on_initial())
			return storeFormDataService.deleteStoreOutPO(id);
		return null;
	}

	@Override
	public OperationMessage updateStoreInPO(StoreInPO po) throws RemoteException {
		// TODO Auto-generated method stub
		if(!InitialDataProxy.isSystem_on_initial())
			return storeFormDataService.updateStoreInPO(po);
		return null;
	}

	@Override
	public OperationMessage updateStoreOutPO(StoreOutPO po) throws RemoteException {
		// TODO Auto-generated method stub
		if(!InitialDataProxy.isSystem_on_initial())
			return storeFormDataService.updateStoreOutPO(po);
		return null;
	}

	@Override
	public OperationMessage clearStoreInPO() throws RemoteException {
		// TODO Auto-generated method stub
		if(!InitialDataProxy.isSystem_on_initial())
			return storeFormDataService.clearStoreInPO();
		return null;
	}

	@Override
	public OperationMessage clearStoreOutPO() throws RemoteException {
		// TODO Auto-generated method stub
		if(!InitialDataProxy.isSystem_on_initial())
			return storeFormDataService.clearStoreOutPO();
		return null;
	}

	@Override
	public String newIDStoreInPO(String unitID) throws RemoteException {
		// TODO Auto-generated method stub
		if(!InitialDataProxy.isSystem_on_initial())
			return storeFormDataService.newIDStoreInPO(unitID);
		return null;
	}

	@Override
	public String newIDStoreOutPO(String unitID) throws RemoteException {
		// TODO Auto-generated method stub
		if(!InitialDataProxy.isSystem_on_initial())
			return storeFormDataService.newIDStoreOutPO(unitID);
		return null;
	}

	@Override
	public StoreInPO getStoreInPO(String id) throws RemoteException {
		// TODO Auto-generated method stub
		if(!InitialDataProxy.isSystem_on_initial())
			return storeFormDataService.getStoreInPO(id);
		return null;
	}

	@Override
	public StoreOutPO getStoreOutPO(String id) throws RemoteException {
		// TODO Auto-generated method stub
		if(!InitialDataProxy.isSystem_on_initial())
			return storeFormDataService.getStoreOutPO(id);
		return null;
	}

	@Override
	public ArrayList<StoreInPO> getAllStoreInPO() throws RemoteException {
		// TODO Auto-generated method stub
		if(!InitialDataProxy.isSystem_on_initial())
			return storeFormDataService.getAllStoreInPO();
		return null;
	}

	@Override
	public ArrayList<StoreOutPO> getAllStoreOutPO() throws RemoteException {
		// TODO Auto-generated method stub
		if(!InitialDataProxy.isSystem_on_initial())
			return storeFormDataService.getAllStoreOutPO();
		return null;
	}

	@Override
	public ArrayList<FormPO> getInOutInfo(Calendar start, Calendar end) throws RemoteException {
		// TODO Auto-generated method stub
		if(!InitialDataProxy.isSystem_on_initial())
			return storeFormDataService.getInOutInfo(start, end);
		return null;
	}

}