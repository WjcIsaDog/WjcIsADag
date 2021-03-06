package bl.blImpl.orderbl;

import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.HashMap;

import bl.NetReconnect.Reconnect;
import bl.clientNetCache.CacheHelper;
import po.configurationdata.City2DPO;
import po.configurationdata.PricePO;
import rmi.configurationdata.ConfigurationDataService;
import tool.vopo.VOPOFactory;
import vo.ordervo.OrderVO;
import vo.ordervo.PredictVO;

/**
 * Client//bl.blImpl.orderbl//Predicter.java
 * @author CXWorks
 * @date 2015年11月15日 下午5:07:20
 * @version 1.0
 */
public class Predicter {
	private VOPOFactory vopoFactory;
	public Predicter(VOPOFactory vopoFactory){
		
		this.vopoFactory=vopoFactory;
	}
	//
	public PredictVO calculatePredict(OrderVO orderVO){
		ConfigurationDataService configurationDataService=CacheHelper.getConfigurationDataService();
		try {
			double weight=orderVO.getWeightORVolume();
			PricePO pricePO=configurationDataService.getPrice();
			int priceKM=pricePO.getByType(orderVO.getType());
			//
			City2DPO city1=configurationDataService.getCity2D(orderVO.getAddressFrom().substring(0, 2));
			City2DPO city2=configurationDataService.getCity2D(orderVO.getAddressTo().substring(0, 2));
			double distance=city1.distance(city2);
			if (distance==0.0) {
				distance=30;
			}
			double price=priceKM*distance/1000*weight;
			Calendar date=Calendar.getInstance();
			date.add(Calendar.DAY_OF_MONTH, (int) (distance/400+1));
			//
			PredictVO vo=new PredictVO(String.format("%.2f", price), date);
			return vo;
		} catch (RemoteException e) {
			Reconnect.ReConnectFactory();
			return null;
		}
	}
}
