package bl.blService.accountblService;

import java.util.List;

import po.accountdata.AccountPO;
import message.OperationMessage;
import vo.accountvo.AccountVO;

/**
 * 
 * @author mx
 *2015/10/25
 */
public interface AccountBLManageService {
	
	/**
	 * 显示账户信息
	 * @return 返回所有账户信息
	 */
	public List<AccountVO> getAccountVOs();
	
	/**
	 * 显示ID对应账户信息
	 * @param accountID 账户名
	 * @return 返回ID对应账户信息
	 */
	public AccountVO getAccountVO(String accountID);
	
	/**
	 * 添加账户
	 * @param po 新增账户信息
	 * @return 返回操作结果
	 */
	public OperationMessage addAccount(AccountVO vo);
	
	/**
	 * 删除账户
	 * @param po 需删除账户信息
	 * @return 返回操作结果
	 */
	public OperationMessage deleteAccount(AccountVO vo);
	
	/**
	 * 修改账户
	 * @param po 已修改账户信息
	 * @return 返回操作结果
	 */
	public OperationMessage modifyAccount(AccountVO vo);
	/**
	 * 模糊查找
	 * @param key 关键字
	 * @return
	 */
	public List<AccountVO> fuzzySearch(String key);
	
	

}
