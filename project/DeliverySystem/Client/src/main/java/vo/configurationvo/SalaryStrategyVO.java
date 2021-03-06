package vo.configurationvo;

import po.InfoEnum;
import po.configurationdata.SalaryStrategyPO;
import po.configurationdata.enums.PackEnum;
import po.memberdata.StaffTypeEnum;
import userinfo.UserInfo;

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
		this.staff=po.getStaff();
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
	public SalaryStrategyPO toPO(){
		SalaryStrategyPO salaryStrategyPO= new SalaryStrategyPO(base, commission, bonus, staff);
		salaryStrategyPO.setCache_OperatorID(UserInfo.getUserID());
		return salaryStrategyPO;
	}
	public void setBase(int base) {
		this.base = base;
	}
	public void setCommission(int commission) {
		this.commission = commission;
	}
	public void setBonus(int bonus) {
		this.bonus = bonus;
	}
	/**
	 * @param infoEnum
	 * @param staff
	 * @param base
	 * @param commission
	 * @param bonus
	 */
	public SalaryStrategyVO(StaffTypeEnum staff, int base,
			int commission, int bonus) {
		super(InfoEnum.SALARY);
		this.staff = staff;
		this.base = base;
		this.commission = commission;
		this.bonus = bonus;
	}
	public void setStaff(StaffTypeEnum staff) {
		this.staff = staff;
	}
	
}
