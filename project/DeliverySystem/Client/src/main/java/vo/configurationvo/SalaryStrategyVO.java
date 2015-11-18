package vo.configurationvo;

import po.InfoEnum;
import po.configurationdata.SalaryStrategyPO;
import po.configurationdata.enums.PackEnum;
import po.memberdata.StaffTypeEnum;

public class SalaryStrategyVO extends ConfigurationVO{
	private StaffTypeEnum staff;
	private int base;
	private int commission;
	private int bonus;
	public SalaryStrategyVO(){
		super(InfoEnum.SALARY);
	}
	public SalaryStrategyVO(SalaryStrategyPO po){
		this();
		this.base=po.getBase();
		commission=po.getCommission();
		bonus=po.getBonus();
	}
	public int getBase() {
		return base;
	}
	public int getCommission() {
		return commission;
	}
	public int getBonus() {
		return bonus;
	}
	public StaffTypeEnum getStaff() {
		return staff;
	}
	
	
}
