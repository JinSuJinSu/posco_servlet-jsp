package vo;

// 값을 보관하는 용도는 VO로 생성한다.
public class VisitorVO {
	// VO => Value Object
	private int id;	
	private String name;
	private String writeDate;
	private String memo;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getWriteDate() {
		return writeDate;
	}
	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	@Override
	public String toString() {
		return "VisitorVO [id=" + id + ", name=" + name + ", writeDate=" + writeDate + ", memo=" + memo + "]";
	}
	
}
