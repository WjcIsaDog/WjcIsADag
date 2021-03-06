package po.receivedata;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Calendar;

import po.FormEnum;
import po.FormPO;
import po.receivedata.StateEnum;
import util.DataType;

/**
 *
 * @author wjc 2015/10/24
 */

public class ReceivePO extends FormPO implements Serializable {

	private String orderID;
	private String transitID;
	private Calendar date;
	private String depature;
	private StateEnum state;

	public ReceivePO(String formID,String orderID, String transitID, Timestamp date,
			String depature, String state,String creatorID) {
		super(FormEnum.RECEIVE, formID,creatorID);
		this.orderID = orderID;
		this.transitID = transitID;
		Calendar temp = Calendar.getInstance();
		temp.setTime(date);
		this.date = temp;
		this.depature = depature;
		this.setState(state);
		this.formType = FormEnum.RECEIVE;
	}

	public ReceivePO(String formID,String orderID, String transitID, Calendar date,
			String depature, String state,String creatorID) {
		super(FormEnum.RECEIVE,formID,creatorID);
		this.orderID = orderID;
		this.transitID = transitID;
		this.date = date;
		this.depature = depature;
		this.setState(state);
		this.formType = FormEnum.RECEIVE;
	}

	public String getOrderID() {
		return orderID;
	}

	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}

	public String getTransitID() {
		return transitID;
	}

	public void setTransitID(String transitID) {
		this.transitID = transitID;
	}

	public String getDepature() {
		return depature;
	}

	public void setDepature(String depature) {
		this.depature = depature;
	}

	public StateEnum getState() {
		return state;
	}

	public void setState(String state) {
		switch (state) {
		case "Complete":
			this.state = StateEnum.Complete;
			break;
		case "Damage":
			this.state = StateEnum.Damage;
			break;
		case "Lose":
			this.state = StateEnum.Lose;
			break;
		}
	}

	public Calendar getDate() {
		return date;
	}

	public Timestamp getDateForSQL() {
		return new Timestamp(this.date.getTimeInMillis());
	}

}
