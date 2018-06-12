package zhangbin.cumt.fore.action.parent;

import java.io.File;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class BaseAction<T> extends ActionSupport implements ModelDriven<T> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//分页查询的参数的获取
	protected int page;
	protected int rows;
	public void setPage(int page) {
		this.page = page;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	/**
	 * 针对datagride的分页请求进行相应
	 */
	protected Map<String,Object> map = new HashMap<>();
	public Map<String, Object> getMap() {
		return map;
	}
	/**
	 * 针对findAll请求返回的所有数据
	 */
	protected List<?> list;
	public List<?> getList() {
		return list;
	}
	/**
	 * 下面为文件上传定义的参数
	 */
	protected File upload;
	protected String uploadFileName;
	protected String uploadContentType;
	//结果标志，如判断上传是否成功、提示结果的返回等
	protected String result;
	public String getResult() {
		return result;
	}
	
	//校验手机号、邮箱是否被占用
	protected boolean flag;
	
	public boolean getFlag() {
		return flag;
	}
	public void setUpload(File upload) {
		this.upload = upload;
	}
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}
	
	
		
	//在创建子类action的时候，同时会初始化其父类的无参构造函数，这是对模型数据进行封装
	@SuppressWarnings("unchecked")
	public BaseAction() {
		try {
			
			Class<? extends BaseAction> clazz = this.getClass();
			Type superclass = clazz.getGenericSuperclass();
			ParameterizedType parameterizedType =(ParameterizedType)superclass;
			//Type rawType = parameterizedType.getRawType();
			Type[] types = parameterizedType.getActualTypeArguments();
			
			@SuppressWarnings("rawtypes")
			Class clazzz = (Class)types[0];
			model = (T)clazzz.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
	}
	protected T model;
	@Override
	public T getModel() {
		return model;
	}

}
