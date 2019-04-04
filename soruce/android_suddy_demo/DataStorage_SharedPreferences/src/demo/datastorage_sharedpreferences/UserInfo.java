package demo.datastorage_sharedpreferences;

/**
 * <pre>
 * 用户信息
 * </pre>
 * 
 * @author caoying
 * 
 */
public class UserInfo {
	String name;
	String pwd;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	@Override
	public String toString() {
		return "UserInfo [name=" + name + ", pwd=" + pwd + "]";
	}
	
}
