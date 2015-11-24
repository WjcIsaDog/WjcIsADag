package bl.blImpl.orderbl;

import java.util.ArrayList;

import message.CheckFormMessage;
import message.OperationMessage;
import po.FormEnum;
import po.FormPO;
import rmi.examineService.ExamineSubmitService;
import rmi.orderdata.OrderDataService;
import vo.ordervo.OrderVO;
import vo.ordervo.PredictVO;
import bl.blService.FormatCheckService.FormatCheckService;
import bl.blService.orderblService.OrderBLService;
import bl.clientNetCache.CacheHelper;
import tool.draft.DraftService;
import tool.vopo.VOPOFactory;

public class OrderBLController implements OrderBLService{
	private DraftService draftService;
	private VOPOFactory vopoFactory;
	private FormatCheckService formatCheckService;
	//
	public OrderBLController(VOPOFactory vopoFactory,DraftService draftService,FormatCheckService formatCheckService){
		this.vopoFactory=vopoFactory;
		this.draftService=draftService;
		this.formatCheckService=formatCheckService;
	}
	public OrderVO loadDraft() {
		return (OrderVO)draftService.getDraft(FormEnum.ORDER);
	}

	public OperationMessage saveDraft(OrderVO form) {
		return draftService.saveDraft(form);
	}

	public ArrayList<CheckFormMessage> checkFormat(OrderVO form, boolean isFinal) {
		// TODO Auto-generated method stub
		ArrayList<CheckFormMessage> result=new ArrayList<CheckFormMessage>();
		CheckFormMessage stub=new CheckFormMessage();
		result.add(stub);
		return result;
	}

	public OperationMessage submit(OrderVO form) {
		try {
			FormPO ready=(FormPO)vopoFactory.transVOtoPO(form);
			ExamineSubmitService examineSubmitService=CacheHelper.getExamineSubmitService();
			return examineSubmitService.submit(ready);
		} catch (Exception e) {
			//TODO handle the exception
			return new OperationMessage(false,"");
		}
	}

	public PredictVO predict(OrderVO vo) {
		// TODO Auto-generated method stub
		return new PredictVO();
	}

	/* (non-Javadoc)
	 * @see bl.blService.FormBLService#newID()
	 */
	public String newID() {
		// TODO Auto-generated method stub
		try {
			OrderDataService orderDataService=CacheHelper.getOrderDataService();
			String id=orderDataService.newID("");
			return id;
		} catch (Exception e) {
			return null;
		}
		
	}


}
