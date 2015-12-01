package vo.storevo;

import java.util.Calendar;

import model.store.StoreLocation;
import po.FormEnum;
import po.FormStateEnum;
import po.storedata.StoreOutPO;
import po.transportdata.TransportationEnum;
import vo.FormVO;

/**
 * Created by Sissel on 2015/10/24.
 */
public class StoreOutVO extends StoreFormVO {
	private StoreOutVO(String formID){
		super(FormEnum.STORE_OUT,FormStateEnum.CONSTRUCTED,formID);
	}

    public StoreOutVO(String formID,String orderID, Calendar date, String destination,
			TransportationEnum transportation, String transID) {
		this(formID);
		this.orderID = orderID;
		this.date = date;
		this.destination = destination;
		this.transportation = transportation;
		this.transID = transID;
	}
    //
    public StoreOutVO(StoreOutPO po){
    	this(po.getFormID(),po.getOrderID(), po.getDate(), po.getDestination(), po.getTransportation(), po.getTransID());
    	this.setMoney(po.getMoney());
    	this.setLocation(po.getLocation());
    }

    private TransportationEnum transportation;
    private String	transID;

	//
    public StoreOutPO toPO(){
    	return new StoreOutPO(formID, orderID, date, destination, transportation, transID);
    }
}
