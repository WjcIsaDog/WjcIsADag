package po.companydata;

import java.io.Serializable;
import java.util.Calendar;

import javax.swing.ImageIcon;

import po.CommonPO;

public class CarPO extends CommonPO implements Serializable{
	private boolean free;
	private int carID;
	private Calendar useTime;
	private ImageIcon img;
	//以下为无用内容，67脑洞真大
	private int engineID;
	private int nameID;
	private int chassisID;//chassis是车辆底盘的意思
	private Calendar buyTime;
}
