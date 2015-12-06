package rmiImpl.financedata;

import message.OperationMessage;
import model.bankAccount.BankAccountOperation;
import po.FormPO;
import po.financedata.BankAccountPO;
import po.financedata.PaymentPO;
import po.financedata.RevenuePO;
import po.receivedata.ReceivePO;
import po.systemdata.LogPO;
import rmi.financedata.BankAccountDataService;
import database.ConnecterHelper;
import database.RMIHelper;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;

public class BankAccountDataImpl extends UnicastRemoteObject implements BankAccountDataService {

	private String Table_Name;
	private Connection conn = null;
	private PreparedStatement statement = null;
	private int ID_MAX;

	public BankAccountDataImpl() throws RemoteException {
		// TODO Auto-generated constructor stub
		super();
		Table_Name = "bank_account";
		conn = ConnecterHelper.getConn();
		this.getNewBankID();
		ID_MAX--;
	}

	public Connection getConn() {
		return conn;
	}

	@Override
	public String getNewBankID() throws RemoteException {
		// TODO Auto-generated method stub
		String selectAll = "select * from `" + Table_Name + "`";
		ResultSet rs = null;
		try {
			statement = conn.prepareStatement(selectAll);
			rs = statement.executeQuery(selectAll);
			while (rs.next()) {
				ID_MAX = Math.max(ID_MAX, Integer.parseInt(rs.getString("bankID")));// 3位编号
			}
		} catch (SQLException e) {
			System.err.println("访问数据库时出错：");
			e.printStackTrace();
		}

		ID_MAX++;// 将该数字加一
		if (ID_MAX > 999)
			return null;
		String added = String.format("%03d", ID_MAX);

		return added;
	}

	@Override
	public OperationMessage checkIsNameUsed(String name) throws RemoteException {
		// TODO Auto-generated method stub
		String select = "select * from `" + Table_Name + "` where `accountName` = '" + name + "'";
		ResultSet rs = null;
		OperationMessage result = new OperationMessage(false, "没有被使用");
		try {
			statement = conn.prepareStatement(select);
			rs = statement.executeQuery(select);
			while (rs.next()) {
				return new OperationMessage(true, "已经被使用");
			}
		} catch (SQLException e) {
			System.err.println("查找数据库时出错：");
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public BankAccountPO getBankAccount(String bankID) throws RemoteException {
		// TODO Auto-generated method stub
		String select = "select * from `" + Table_Name + "` where `formID` = '" + bankID + "'";
		ResultSet rs = null;
		BankAccountPO result = null;
		try {
			statement = conn.prepareStatement(select);
			rs = statement.executeQuery(select);
			rs.next();
			result = new BankAccountPO(rs.getString("bankID"), rs.getString("accountName"), rs.getString("balance"));
		} catch (SQLException e) {
			System.err.println("查找数据库时出错：");
			e.printStackTrace();
			return null;
		}
		return result;
	}

	@Override
	public OperationMessage insert(BankAccountPO po) throws RemoteException {
		// TODO Auto-generated method stub
		OperationMessage result = new OperationMessage();
		String insert = "insert into `" + Table_Name + "`(bankID,accountName,balance) " + "values('" + po.getBankID()
				+ "','" + po.getAccountName() + "','" + po.getBalance() + "')";
		try {
			statement = conn.prepareStatement(insert);
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			result = new OperationMessage(false, "新建时出错：");
			System.err.println("新建时出错：");
			e.printStackTrace();
		}

		// 系统日志
		if (result.operationResult == true)
			RMIHelper.getLogDataService().insert(new LogPO("财务人员", Calendar.getInstance(), "新建银行账户:" + po.getBankID()));

		return result;
	}

	@Override
	public OperationMessage delete(String id) throws RemoteException {
		// TODO Auto-generated method stub
		OperationMessage result = new OperationMessage();
		String delete = "delete from `" + Table_Name + "` where `bankID` = '" + id + "'";
		try {
			statement = conn.prepareStatement(delete);
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			result = new OperationMessage(false, "删除时出错：");
			System.err.println("删除时出错：");
			e.printStackTrace();
		}

		// 系统日志
		if (result.operationResult == true)
			RMIHelper.getLogDataService().insert(new LogPO("财务人员", Calendar.getInstance(), "新建银行账户:" + id));

		return result;
	}

	@Override
	public OperationMessage update(BankAccountPO po) throws RemoteException {
		// TODO Auto-generated method stub
		OperationMessage result = new OperationMessage();
		if (!this.delete(po.getBankID()).operationResult)
			return result = new OperationMessage(false, "数据库中没有对应银行账户");
		if (!this.insert(po).operationResult)
			return result = new OperationMessage(false, "更新失败");
		else
			return result;
	}

	@Override
	public OperationMessage clear() throws RemoteException {
		// TODO Auto-generated method stub
		OperationMessage result = new OperationMessage();
		String clear = "delete from `" + Table_Name + "`";
		try {
			statement = conn.prepareStatement(clear);
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			result = new OperationMessage(false, "清空数据库时出错：");
			System.err.println("清空数据库时出错：");
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public ArrayList<BankAccountPO> getAll() throws RemoteException {
		// TODO Auto-generated method stub
		String selectAll = "select * from `" + Table_Name + "`";
		ResultSet rs = null;
		BankAccountPO temp = null;
		ArrayList<BankAccountPO> result = new ArrayList<BankAccountPO>();
		try {
			statement = conn.prepareStatement(selectAll);
			rs = statement.executeQuery(selectAll);
			while (rs.next()) {
				temp = new BankAccountPO(rs.getString("bankID"), rs.getString("accountName"), rs.getString("balance"));
				result.add(temp);
			}
		} catch (SQLException e) {
			System.err.println("查找数据库时出错：");
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public ArrayList<FormPO> getByAccID(String accID) throws RemoteException {
		String select_pay = "select * from `payment` where `payerAccID` = '" + accID + "'";
//		String select_rev = "select * from `revenue` where `payerAccID` = '" + accID + "'";//这地方不知道怎么搞
		ResultSet rs = null;
		PaymentPO pay = null;
		RevenuePO rev = null;
		ArrayList<FormPO> result = new ArrayList<FormPO>();
		try {
			statement = conn.prepareStatement(select_pay);
			rs = statement.executeQuery(select_pay);
			while (rs.next()) {
				pay = new PaymentPO(rs.getString("formID"), rs.getTimestamp("date"), rs.getString("amount"),
						rs.getString("payerAccID"), rs.getString("payerName"), rs.getString("payerAccount"),
						rs.getString("receiverAccID"), rs.getString("receiverName"), rs.getString("receiverAccount"),
						rs.getString("item"), rs.getString("note"));
				pay.setFormState(rs.getString("formState"));
				result.add(pay);
			}
		} catch (SQLException e) {
			System.err.println("查找数据库时出错：");
			e.printStackTrace();
		}

//		try {
//			statement = conn.prepareStatement(select_rev);
//			rs = statement.executeQuery(select_rev);
//			while (rs.next()) {
//				rev = new RevenuePO(rs.getString("formID"), rs.getTimestamp("date"), rs.getString("amount"),
//						rs.getString("deliverName"), rs.getString("hallID"), rs.getString("orderID"));
//				rev.setFormState(rs.getString("formState"));
//				result.add(rev);
//			}
//		} catch (SQLException e) {
//			System.err.println("查找数据库时出错：");
//			e.printStackTrace();
//		}

		return result;
	}

}
