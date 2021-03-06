package bl.clientNetCache.save;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.Normalizer.Form;
import java.util.ArrayList;

import po.CommonPO;
import po.FormPO;
import po.InfoPO;
import userinfo.UserInfo;
import util.DataType;
import message.OperationMessage;

/** 
 * Client//bl.clientNetCache.save//CacheSaver.java
 * @author CXWorks
 * @date 2015年12月21日 上午9:14:37
 * @version 1.0 
 */
public class CacheSaver {
	private final String tail=".2333";
	
	public OperationMessage saveCache(CommonPO commonPO,String fileName){
		String path=UserInfo.getWorkPath();
		String filePath=path+fileName+tail;
		try {
			File file=new File(filePath);
			if (!file.exists()) {
				file.createNewFile();
			}
			ObjectOutputStream writer=new ObjectOutputStream(new FileOutputStream(file, false));
			writer.writeObject(commonPO);
			writer.close();
			return new OperationMessage();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new OperationMessage(false, e.getMessage());
		}
	}
	
	public OperationMessage saveCache(ArrayList<? extends CommonPO> src,String fileName){
		if (src.isEmpty()) {
			return new OperationMessage();
		}else {
			String path=UserInfo.getWorkPath();
			String filePath=path+fileName+tail;
			ObjectOutputStream writer;
			try {
				File file=new File(filePath);
				if (!file.exists()) {
					file.createNewFile();
				}
				writer = new ObjectOutputStream(new FileOutputStream(file, false));
				writer.writeObject(src);
				writer.close();
				return new OperationMessage();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return new OperationMessage(false, e.getMessage()); 
			}
			
		}
	}
	
	public CommonPO loadCache(String fileName)throws IOException{
		String path=UserInfo.getInstitutionID()+"cache/";
		String filePath=path+fileName+tail;
		try {
			File file=new File(filePath);
			if (!file.exists()) {
				throw new IOException();
			}
			ObjectInputStream reader=new ObjectInputStream(new FileInputStream(filePath));
			CommonPO ans=(CommonPO)reader.readObject();
			reader.close();
			return ans;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public ArrayList<? extends CommonPO> loadCache(String fileName,boolean differ) throws IOException{
		String path=UserInfo.getInstitutionID()+"cache/";
		String filePath=path+fileName+tail;
		
		try {
			File file=new File(filePath);
			if (!file.exists()) {
				throw new IOException();
			}
			ObjectInputStream reader=new ObjectInputStream(new FileInputStream(filePath));
			ArrayList<? extends CommonPO> ans=(ArrayList<? extends CommonPO>) reader.readObject();
			reader.close();
			return ans;
		} catch (  ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
