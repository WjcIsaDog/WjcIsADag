package rmiImpl.examineImpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import message.ChatMessage;
import message.OperationMessage;
import model.examine.ExamineQueue;
import po.FormPO;
import rmi.chatRemindService.ChatNewService;
import rmi.examineService.ExamineSubmitService;
import rmiImpl.chatRemindImpl.Reminder;

public class ExamineSubmitImpl extends UnicastRemoteObject implements
		ExamineSubmitService {
	private static final long serialVersionUID = 1L;
	/* 消息队列 */
	private ExamineQueue queue;
	/* 负责新建消息 */
	private ChatNewService addMessage;
	/* 总经理的ID */
	private String managerID = "01000001";

	public ExamineSubmitImpl(ExamineQueue queue) throws RemoteException {
		super();
		this.queue = queue;
		addMessage = new Reminder();
	}

	public synchronized OperationMessage submit(FormPO form)
			throws RemoteException {
		this.queue.addForm(form);
		ChatMessage mes = new ChatMessage(form.getCreatorID(), managerID,
				"有新的表单等待审批：" + form.getFormID());
		addMessage.add(managerID, mes);
		return new OperationMessage();
	}

}
