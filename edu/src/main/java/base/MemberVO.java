package base;

public class MemberVO {
	private String name;
	private String phoneNumber;
	private String id;
	private String password;
	
	public MemberVO() {
	}	
	public MemberVO(String name, String phoneNumber, String id, String password) {
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.id = id;
		this.password = password;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
