package po.companydata;
import java.io.Serializable;
import java.util.ArrayList;

import po.CommonPO;
import po.memberdata.StaffPO;

public class HallPO extends CommonPO implements Serializable{
	private String hallID;
	private String city;
	private String area;
	private ArrayList<StaffPO> driver;
	private ArrayList<StaffPO> deliver;
	private ArrayList<StaffPO> counterman;
	private String nearCenterID;
}
