package base;

public class ProductVO {
	private int appleCount;
	private int bananaCount;
	private int halabongCount;
	
	public ProductVO() {
	}
	
	public ProductVO(int appleCount, int bananaCount, int halabongCount) {
		this.appleCount = appleCount;
		this.bananaCount = bananaCount;
		this.halabongCount = halabongCount;
	}


	public int getAppleCount() {
		return appleCount;
	}
	public void setAppleCount(int count) {
		this.appleCount += count;
	}
	public int getBananaCount() {
		return bananaCount;
	}
	public void setBananaCount(int count) {
		this.bananaCount += count;
	}
	public int getHalabongCount() {
		return halabongCount;
	}
	public void setHalabongCount(int count) {
		this.halabongCount += count;
	}
	
	
	
	

}
