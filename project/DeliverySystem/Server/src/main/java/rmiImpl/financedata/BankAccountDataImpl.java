package rmiImpl.financedata;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import database.ConnecterHelper;
import database.MySql;
import database.RMIHelper;
import database.enums.TableEnum;
import message.OperationMessage;
import po.FormPO;
import po.financedata.BankAccountPO;
import po.financedata.PaymentPO;
import po.financedata.RevenuePO;
import po.systemdata.LogPO;
import rmi.financedata.BankAccountDataService;

public class BankAccountDataImpl extends UnicastRemoteObject implements BankAccountDataService {

	private Connection conn = null;
	private PreparedStatement statement = null;
	private static String CompanyAccount = "001";
	private int ID_MAX;

	public BankAccountDataImpl() throws RemoteException {
		super();
		conn = ConnecterHelper.getConn();
		this.getNewBankID();
		ID_MAX--;
	}

	public Connection getConn() {
		return conn;
	}

	@Override
	public String getNewBankID() throws RemoteException {
		String selectAll = MySql.select(TableEnum.BANK_ACCOUNT, null);
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
		String select = MySql.select(TableEnum.BANK_ACCOUNT, new HashMap<String, String>() {
			{
				put("accountName", name);
			}
		});
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
		String select = MySql.select(TableEnum.BANK_ACCOUNT, new HashMap<String, String>() {
			{
				put("formID", bankID);
			}
		});
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
		OperationMessage result = new OperationMessage();
		String insert = MySql.insert(TableEnum.BANK_ACCOUNT, new HashMap<String, String>() {
			{
				put("bankID", po.getBankID());
				put("accountName", po.getAccountName());
				put("balance", po.getBalance());
			}
		});
		try {
			statement = conn.prepareStatement(insert);
			statement.executeUpdate();
		} catch (SQLException e) {
			result = new OperationMessage(false, "新建时出错：");
			System.err.println("新建时出错：");
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public OperationMessage delete(String id) throws RemoteException {
		OperationMessage result = new OperationMessage();
		String delete = MySql.delete(TableEnum.BANK_ACCOUNT, new HashMap<String, String>() {
			{
				put("bankID", id);
			}
		});
		try {
			statement = conn.prepareStatement(delete);
			statement.executeUpdate();
		} catch (SQLException e) {
			result = new OperationMessage(false, "删除时出错：");
			System.err.println("删除时出错：");
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public OperationMessage update(BankAccountPO po) throws RemoteException {
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
		OperationMessage result = new OperationMessage();
		String clear = MySql.delete(TableEnum.BANK_ACCOUNT, null);
		try {
			statement = conn.prepareStatement(clear);
			statement.executeUpdate();
		} catch (SQLException e) {
			result = new OperationMessage(false, "清空数据库时出错：");
			System.err.println("清空数据库时出错：");
			e.printStackTrace();
		}
		return result;
	}

	// public static void main(String[] args) throws RemoteException {
	// BankAccountDataImpl t = new BankAccountDataImpl();
	// ArrayList<BankAccountPO> s = t.getAll();
	// System.out.println(s.size());
	// }

	@Override
	public ArrayList<BankAccountPO> getAll() throws RemoteException {
		String selectAll = MySql.select(TableEnum.BANK_ACCOUNT, null);
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
		String select_pay = MySql.select(TableEnum.PAYMENT, new HashMap<String, String>() {
			{
				put("payerAccID", accID);
			}
		});
		// String select_rev = "select * from `revenue` where `payerAccID` = '"
		// + accID + "'";//这地方不知道怎么搞
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
						rs.getString("item"), rs.getString("note"),rs.getString("creatorID"));
				pay.setFormState(rs.getString("formState"));
				result.add(pay);
			}
		} catch (SQLException e) {
			System.err.println("查找数据库时出错：");
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public OperationMessage modifyBalance(String ID, double money) throws RemoteException {
		OperationMessage result = new OperationMessage();
		double balance = 0;
		if (ID == null) {
			ID = CompanyAccount;
		}
		final String accID = ID;
		String select = MySql.select(TableEnum.BANK_ACCOUNT, new HashMap<String, String>() {
			{
				put("bankID", accID);
			}
		});

		ResultSet rs = null;
		try {
			statement = conn.prepareStatement(select);
			rs = statement.executeQuery(select);
			rs.next();
			balance = Double.parseDouble(rs.getString("balance")) + money;
			String operation = MySql.update(TableEnum.BANK_ACCOUNT,"balance",balance + "", new HashMap<String, String>() {
				{
					put("bankID", accID);
				}
			});
			statement = conn.prepareStatement(operation);
			statement.executeUpdate();
		} catch (SQLException e) {
			System.err.println("没有对应账户");
			e.printStackTrace();
			result = new OperationMessage(false, "调整账户余额出错");
		}

		return result;
	}

	@Override
	public String getBankIDByName(String name) throws RemoteException {
		String select = MySql.select(TableEnum.BANK_ACCOUNT, new HashMap<String, String>() {
			{
				put("accountName", name);
			}
		});
		ResultSet rs = null;
		try {
			statement = conn.prepareStatement(select);
			rs = statement.executeQuery(select);
			rs.next();
			return rs.getString("bankID");
		} catch (SQLException e) {
			System.err.println("查找数据库时出错：");
			e.printStackTrace();
			return null;
		}
	}

}
