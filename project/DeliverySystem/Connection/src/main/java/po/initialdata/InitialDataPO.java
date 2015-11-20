package po.initialdata;

import model.store.StoreModel;
import po.CommonPO;
import po.InfoEnum;
import po.InfoPO;
import po.companydata.CarPO;
import po.companydata.CenterPO;
import po.companydata.HallPO;
import po.financedata.BankAccountPO;
import po.memberdata.StaffPO;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Sissel on 2015/10/26.
 */
public class InitialDataPO extends InfoPO implements Serializable{
    String version;
    String dbName;

    List<StoreModel> storeModels;
    List<BankAccountPO> bankAccounts;
    List<CarPO> cars;
    List<StaffPO> staffs;
    List<HallPO> halls;
    List<CenterPO> centers;
    public InitialDataPO(){
    	super(InfoEnum.INITIAL_DATA);
    }
}
