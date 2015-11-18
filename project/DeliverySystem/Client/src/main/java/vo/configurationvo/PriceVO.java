package vo.configurationvo;

import java.util.HashMap;
import java.util.Map;

import po.InfoEnum;
import po.configurationdata.PricePO;
import po.configurationdata.enums.DeliveryTypeEnum;

public class PriceVO extends ConfigurationVO{
	private HashMap<DeliveryTypeEnum,Integer> price;
	public PriceVO(){
		super(InfoEnum.PRICE);
	}
	public int getByType(DeliveryTypeEnum type){
		return price.get(type);
	}
	public PriceVO(PricePO po){
		this();
		this.price=po.getClonedPrice();
	}
}
