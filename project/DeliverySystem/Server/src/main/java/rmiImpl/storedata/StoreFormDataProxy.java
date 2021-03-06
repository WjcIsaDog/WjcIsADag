package rmiImpl.storedata;

import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import message.OperationMessage;
import po.FormPO;
import po.storedata.StoreInPO;
import po.storedata.StoreOutPO;
import po.systemdata.SystemState;
import rmi.storedata.StoreFormDataService;
import rmiImpl.initaldata.InitialDataProxy;

public class StoreFormDataProxy extends UnicastRemoteObject implements StoreFormDataService {

	StoreFormDataService storeFormDataService = new StoreFormDataImpl();

	public StoreFormDataProxy() throws RemoteException, MalformedURLException {
		super();
	}

	@Override
	public OperationMessage insertStoreInPO(StoreInPO po) throws RemoteException {
		if(InitialDataProxy.getState().equals(SystemState.NORMAL))
			return storeFormDataService.insertStoreInPO(po);
		return null;
	}

	@Override
	public OperationMessage insertStoreOutPO(StoreOutPO po) throws RemoteException {
		if(InitialDataProxy.getState().equals(SystemState.NORMAL))
			return storeFormDataService.insertStoreOutPO(po);
		return null;
	}

	@Override
	public OperationMessage deleteStoreInPO(String id) throws RemoteException {
		if(InitialDataProxy.getState().equals(SystemState.NORMAL))
			return storeFormDataService.deleteStoreInPO(id);
		return null;
	}

	@Override
	public OperationMessage deleteStoreOutPO(String id) throws RemoteException {
		if(InitialDataProxy.getState().equals(SystemState.NORMAL))
			return storeFormDataService.deleteStoreOutPO(id);
		return null;
	}

	@Override
	public OperationMessage updateStoreInPO(StoreInPO po) throws RemoteException {
		if(InitialDataProxy.getState().equals(SystemState.NORMAL))
			return storeFormDataService.updateStoreInPO(po);
		return null;
	}

	@Override
	public OperationMessage updateStoreOutPO(StoreOutPO po) throws RemoteException {
		if(InitialDataProxy.getState().equals(SystemState.NORMAL))
			return storeFormDataService.updateStoreOutPO(po);
		return null;
	}

	@Override
	public OperationMessage clearStoreInPO() throws RemoteException {
		if(InitialDataProxy.getState().equals(SystemState.NORMAL))
			return storeFormDataService.clearStoreInPO();
		return null;
	}

	@Override
	public OperationMessage clearStoreOutPO() throws RemoteException {
		if(InitialDataProxy.getState().equals(SystemState.NORMAL))
			return storeFormDataService.clearStoreOutPO();
		return null;
	}

	@Override
	public String newIDStoreInPO(String unitID) throws RemoteException {
		if(InitialDataProxy.getState().equals(SystemState.NORMAL))
			return storeFormDataService.newIDStoreInPO(unitID);
		return null;
	}

	@Override
	public String newIDStoreOutPO(String unitID) throws RemoteException {
		if(InitialDataProxy.getState().equals(SystemState.NORMAL))
			return storeFormDataService.newIDStoreOutPO(unitID);
		return null;
	}

	@Override
	public StoreInPO getStoreInPO(String id) throws RemoteException {
		if(InitialDataProxy.getState().equals(SystemState.NORMAL))
			return storeFormDataService.getStoreInPO(id);
		return null;
	}

	@Override
	public StoreOutPO getStoreOutPO(String id) throws RemoteException {
		if(InitialDataProxy.getState().equals(SystemState.NORMAL))
			return storeFormDataService.getStoreOutPO(id);
		return null;
	}

	@Override
	public ArrayList<StoreInPO> getAllStoreInPO(String centerID) throws RemoteException {
		if(InitialDataProxy.getState().equals(SystemState.NORMAL))
			return storeFormDataService.getAllStoreInPO(centerID);
		return null;
	}

	@Override
	public ArrayList<StoreOutPO> getAllStoreOutPO(String centerID) throws RemoteException {
		if(InitialDataProxy.getState().equals(SystemState.NORMAL))
			return storeFormDataService.getAllStoreOutPO(centerID);
		return null;
	}

	@Override
	public ArrayList<FormPO> getInOutInfo(String centerID,Calendar start, Calendar end) throws RemoteException {
		if(InitialDataProxy.getState().equals(SystemState.NORMAL))
			return storeFormDataService.getInOutInfo(centerID,start, end);
		return null;
	}

	@Override
	public List<StoreInPO> getHistoryIn(String creatorID) throws RemoteException {
		if(InitialDataProxy.getState().equals(SystemState.NORMAL))
			return storeFormDataService.getHistoryIn(creatorID);
		return null;
	}

	@Override
	public List<StoreOutPO> getHistoryOut(String creatorID) throws RemoteException {
		if(InitialDataProxy.getState().equals(SystemState.NORMAL))
			return storeFormDataService.getHistoryOut(creatorID);
		return null;
	}

}
