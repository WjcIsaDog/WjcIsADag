package bl.blService.storeblService;

import bl.blService.FormBLService;
import message.CheckFormMessage;
import message.OperationMessage;
import model.store.StoreAreaCode;
import model.store.StoreLocation;
import vo.ordervo.OrderVO;
import vo.storevo.StoreInVO;

import java.util.List;

/**
 * Created by Sissel on 2015/10/25.
 */
public interface StoreInBLService extends FormBLService<StoreInVO> {

    public StoreLocation getAvailableLocation (StoreAreaCode area);

    public String getNewStoreInID (String date);

    public OrderVO loadOrder(String orderNumber);

}
