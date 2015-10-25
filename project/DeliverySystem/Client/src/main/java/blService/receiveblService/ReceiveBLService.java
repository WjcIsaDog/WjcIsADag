package blService.receiveblService;

import java.util.ArrayList;

import blService.FormBLService;
import message.CheckFormMessage;
import message.OperationMessage;
import po.deliverdata.DeliverPO;
import po.receivedata.ReceivePO;
import vo.ordervo.OrderVO;
import vo.receivevo.ReceiveVO;
import vo.transitvo.TransitVO;

public interface ReceiveBLService extends FormBLService{
	/**
	 * 检查到达单
	 * @param form 到达单信息
	 * @return 返回检查结果列表
	 */
	public ArrayList<CheckFormMessage> checkFormat(ReceiveVO form, boolean isFinal);
	
	/**
	 * 提交到达单
	 * @param form 到达单信息
	 * @return 返回操作结果
	 */
	public OperationMessage submit(ReceiveVO form);
	
	/**
	 * 保存到达单草稿
	 * @return 返回操作结果
	 */
	public OperationMessage saveDraft(ReceiveVO form);
	
	/**
	 * 载入到达单草稿
	 * @return 到达单信息
	 */
	public ReceiveVO loadDraft();
	
	/**
	 * 载入订单信息
	 * @param orderID 订单号
	 * @return 订单信息
	 */
	public OrderVO getOrderVO(String orderID);
	
	/**
	 * 载入中转单信息（已经载入订单信息）
	 * @return 中转单信息
	 */
	public TransitVO getTransitVO();
}
