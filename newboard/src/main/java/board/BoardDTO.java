package board;

import java.util.Date;

public class BoardDTO {
	private int boardNO;
	private String userID;
	private String title;
	private String content;
	private int readCount;
	private String writeDate;
	
	public BoardDTO() {
	}
	
	public BoardDTO(int boardNo, String userID, String title,
			String content, int readCount, String writeDate) {
		this.boardNO = boardNo;
		this.userID = userID;
		this.title = title;
		this.content = content;
		this.readCount = readCount;
		this.writeDate = writeDate;
		
	}
	
	public int getBoardNO() {
		return boardNO;
	}
	public void setBoardNO(int boardNO) {
		this.boardNO = boardNO;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public  String getContent() {
		return content;
	}
	public void seContent(String content) {
		this.content = content;
	}
	public int getReadCount() {
		return readCount;
	}
	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}
	public String getWriteDate() {
		return writeDate;
	}
	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}
	
	

}
