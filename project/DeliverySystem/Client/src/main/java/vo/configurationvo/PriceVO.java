package vo.configurationvo;

import java.util.Map;

import po.configurationdata.enums.ConfigurationEnum;
import po.configurationdata.enums.DeliveryTypeEnum;

public class PriceVO {
	private ConfigurationEnum ID;
	private Map<DeliveryTypeEnum,Integer> price;
}
