package vo.configurationvo;

import po.configurationdata.enums.ConfigurationEnum;
import po.memberdata.StaffTypeEnum;

public class SalaryStrategyVO {
	private ConfigurationEnum ID;
	private StaffTypeEnum staff;
	private int base;
	private int commission;
	private int bonus;
}