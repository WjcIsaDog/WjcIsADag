package rmiImpl.configurationdata;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JOptionPane;

import message.OperationMessage;
import po.companydata.CarPO;
import po.configurationdata.CityDistancePO;
import po.configurationdata.PackPO;
import po.configurationdata.PricePO;
import po.configurationdata.ProportionPO;
import po.configurationdata.SalaryStrategyPO;
import po.configurationdata.enums.DeliveryTypeEnum;
import po.configurationdata.enums.PackEnum;
import rmi.configurationdata.ConfigurationDataService;
import rmiImpl.ConnecterHelper;

/**
 * 目前全是stub
 * 
 * @author cxworks 2015/10/25
 */
public class ConfigurationDataImpl extends UnicastRemoteObject implements
		ConfigurationDataService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String City2D;
	private String SalaryStrategy;
	private String Pack;
	private String Price;
	private String Proportion;
	private Connection conn = null;
	private PreparedStatement statement = null;

	public ConfigurationDataImpl() throws RemoteException {
		// TODO Auto-generated constructor stub
		super();
		City2D = "city2d";
		SalaryStrategy = "salary_strategy";
		Pack = "pack";
		Price = "price";
		Proportion = "proportion";
		conn = ConnecterHelper.connSQL(conn);
	}

	public Connection getConn() {
		return conn;
	}

	public ArrayList<CityDistancePO> getCityDistance() throws RemoteException {
		// TODO Auto-generated method stub
		ArrayList<CityDistancePO> result = new ArrayList<CityDistancePO>();
		CityDistancePO stub = new CityDistancePO();
		result.add(stub);
		return result;
	}

	public OperationMessage modifyCityDistance(CityDistancePO after)
			throws RemoteException {
		// TODO Auto-generated method stub
		return new OperationMessage();
	}

	public ArrayList<SalaryStrategyPO> getSalaryStrategy()
			throws RemoteException {
		// TODO Auto-generated method stub
		String selectAll = "select * from " + SalaryStrategy;
		ResultSet rs = null;
		SalaryStrategyPO temp = null;
		ArrayList<SalaryStrategyPO> result = new ArrayList<SalaryStrategyPO>();
		try {
			statement = conn.prepareStatement(selectAll);
			rs = statement.executeQuery(selectAll);
			while (rs.next()) {
				temp = new SalaryStrategyPO(rs.getInt("base"),
						rs.getInt("commission"), rs.getInt("bonus"),
						rs.getString("staff"));
				result.add(temp);
			}
		} catch (SQLException e) {
			System.err.println("查找数据库时出错：");
			e.printStackTrace();
		}

		return result;
	}

	public OperationMessage modifySalaryStrategy(SalaryStrategyPO po)
			throws RemoteException {
		// TODO Auto-generated method stub
		OperationMessage result = new OperationMessage();
		String delete = "delete from " + SalaryStrategy + " where staff= '"
				+ po.getStaff().toString() + "'";
		try {
			statement = conn.prepareStatement(delete);
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			result = new OperationMessage(false, "数据库中没有对应信息");
			e.printStackTrace();
			return result;
		}
		String insert = "insert into " + SalaryStrategy
				+ "(staff,base,commission,bonus) " + "values('"
				+ po.getStaff().toString() + "','" + po.getBase() + "','"
				+ po.getCommission() + "','" + po.getBonus() + "')";

		try {
			statement = conn.prepareStatement(insert);
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			result = new OperationMessage(false, "修改的信息有误");
			e.printStackTrace();
		}

		return result;
	}

	public PackPO getPack() throws RemoteException {
		// TODO Auto-generated method stub
		String selectAll = "select * from " + Pack;
		ResultSet rs = null;
		PackPO result = null;
		HashMap<PackEnum, Double> packPrice = new HashMap();
		try {
			statement = conn.prepareStatement(selectAll);
			rs = statement.executeQuery(selectAll);
			while (rs.next()) {
				switch (rs.getString("name")) {
				case "PAPER":
					packPrice.put(PackEnum.PAPER, rs.getDouble("money"));
					break;
				case "PACKAGE":
					packPrice.put(PackEnum.PACKAGE, rs.getDouble("money"));
					break;
				case "WOOD":
					packPrice.put(PackEnum.WOOD, rs.getDouble("money"));
					break;
				case "OTHER":
					packPrice.put(PackEnum.OTHER, rs.getDouble("money"));
					break;
				}

			}
			result = new PackPO(packPrice);
		} catch (SQLException e) {
			System.err.println("查找数据库时出错：");
			e.printStackTrace();
		}

		return result;
	}

	public OperationMessage modifyPack(PackPO pack) throws RemoteException {
		// TODO Auto-generated method stub
		OperationMessage result = new OperationMessage();
		ArrayList<String> operations = new ArrayList<String>();
		operations.add("update " + Pack + "set money='" + pack.getByType(PackEnum.OTHER) + "' where name= 'OTHER'");
		operations.add("update " + Pack + "set money='" + pack.getByType(PackEnum.PACKAGE) + "' where name= 'PACKAGE'");
		operations.add("update " + Pack + "set money='" + pack.getByType(PackEnum.PAPER) + "' where name= 'PAPER'");
		operations.add("update " + Pack + "set money='" + pack.getByType(PackEnum.WOOD) + "' where name= 'WOOD'");
		try {
			for (String tmp : operations) {
				statement = conn.prepareStatement(tmp);
				statement.executeUpdate();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			result = new OperationMessage(false, "更新时出错：");
			System.err.println("更新时出错：");
			e.printStackTrace();
		}
		return result;
	}

	public PricePO getPrice() throws RemoteException {
		// TODO Auto-generated method stub
		String selectAll = "select * from " + Price;
		ResultSet rs = null;
		PricePO result = null;
		HashMap<DeliveryTypeEnum, Integer> price = new HashMap();
		try {
			statement = conn.prepareStatement(selectAll);
			rs = statement.executeQuery(selectAll);
			while (rs.next()) {
				switch (rs.getString("name")) {
				case "ECONOMIC":
					price.put(DeliveryTypeEnum.ECONOMIC, rs.getInt("money"));
					break;
				case "USUAL":
					price.put(DeliveryTypeEnum.USUAL, rs.getInt("money"));
					break;
				case "FAST":
					price.put(DeliveryTypeEnum.FAST, rs.getInt("money"));
					break;
				}

			}
			result = new PricePO(price);
		} catch (SQLException e) {
			System.err.println("查找数据库时出错：");
			e.printStackTrace();
		}

		return result;
	}

	public OperationMessage modifyPrice(PricePO price) throws RemoteException {
		// TODO Auto-generated method stub
		OperationMessage result = new OperationMessage();
		ArrayList<String> operations = new ArrayList<String>();
		operations.add("update " + Price + "set money='" + price.getByType(DeliveryTypeEnum.ECONOMIC) + "' where name= 'ECONOMIC'");
		operations.add("update " + Price + "set money='" + price.getByType(DeliveryTypeEnum.USUAL) + "' where name= 'USUAL'");
		operations.add("update " + Price + "set money='" + price.getByType(DeliveryTypeEnum.FAST) + "' where name= 'FAST'");
		try {
			for (String tmp : operations) {
				statement = conn.prepareStatement(tmp);
				statement.executeUpdate();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			result = new OperationMessage(false, "更新时出错：");
			System.err.println("更新时出错：");
			e.printStackTrace();
		}
		return result;
	}

	public ProportionPO getProportion() throws RemoteException {
		// TODO Auto-generated method stub
		String selectAll = "select * from " + Proportion;
		ResultSet rs = null;
		ProportionPO result = null;
		HashMap<DeliveryTypeEnum, Integer> target = new HashMap();
		try {
			statement = conn.prepareStatement(selectAll);
			rs = statement.executeQuery(selectAll);
			while (rs.next()) {
				switch (rs.getString("name")) {
				case "ECONOMIC":
					target.put(DeliveryTypeEnum.ECONOMIC, rs.getInt("num"));
					break;
				case "USUAL":
					target.put(DeliveryTypeEnum.USUAL, rs.getInt("num"));
					break;
				case "FAST":
					target.put(DeliveryTypeEnum.FAST, rs.getInt("num"));
					break;
				}

			}
			result = new ProportionPO(target);
		} catch (SQLException e) {
			System.err.println("查找数据库时出错：");
			e.printStackTrace();
		}

		return result;
	}

	public OperationMessage modifyProportion(ProportionPO proportion)
			throws RemoteException {
		// TODO Auto-generated method stub
		OperationMessage result = new OperationMessage();
		ArrayList<String> operations = new ArrayList<String>();
		operations.add("update " + Proportion + "set num='" + proportion.getByType(DeliveryTypeEnum.ECONOMIC) + "' where name= 'ECONOMIC'");
		operations.add("update " + Proportion + "set num='" + proportion.getByType(DeliveryTypeEnum.USUAL) + "' where name= 'USUAL'");
		operations.add("update " + Proportion + "set num='" + proportion.getByType(DeliveryTypeEnum.FAST) + "' where name= 'FAST'");
		try {
			for (String tmp : operations) {
				statement = conn.prepareStatement(tmp);
				statement.executeUpdate();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			result = new OperationMessage(false, "更新时出错：");
			System.err.println("更新时出错：");
			e.printStackTrace();
		}
		return result;
	}

	public Object getInstitutionDistance() throws RemoteException {
		// TODO Auto-generated method stub
		return new Object();
	}

	public OperationMessage modifyInstitutionDistance(String ID, Object ob)
			throws RemoteException {
		// TODO Auto-generated method stub
		return new OperationMessage();
	}

	public Object[] newInstitutionDistanceSearch(String ID)
			throws RemoteException {
		// TODO Auto-generated method stub
		Object[] stub = new Object[2];
		return stub;
	}

	public OperationMessage newInstitutionDistanceInsert(String ID, Object[] ob)
			throws RemoteException {
		// TODO Auto-generated method stub
		return new OperationMessage();
	}

}
