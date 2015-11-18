package po.configurationdata;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import po.CommonPO;
import po.InfoPO;
import po.configurationdata.enums.PackEnum;

public class PackPO extends InfoPO implements Serializable{
	private Map<PackEnum,Double> packPrice;
	
	public PackPO() {
		// TODO Auto-generated constructor stub
		this.packPrice=new HashMap();
		this.packPrice.put(PackEnum.WOOD, (double) 10);
		this.packPrice.put(PackEnum.PAPER, (double) 5);
		this.packPrice.put(PackEnum.PACKAGE, (double) 1);
		this.packPrice.put(PackEnum.OTHER, (double)0);
	}
	public double getByType(PackEnum type){
		return this.packPrice.get(type);
	}
	public Map<PackEnum, Double> getPackPrice() {
		return packPrice;
	}
	
}
