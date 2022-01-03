package board;

import java.util.Date;

public class BoardDTO {
	private static int boardNO;
	private static String userID;
	private static String title;
	private static String content;
	private static int readCount;
	private static Date writeDate;
	
	public BoardDTO() {
	}
	
	public BoardDTO(int boardNo, String userID, String title,
			String content, int readCount, Date wireDate) {
		this.boardNO = boardNo;
		this.userID = userID;
		this.title = title;
		this.content = content;
		this.readCount = readCount;
		this.writeDate = writeDate;
		
	}
	
	
	public static int getBoardNO() {
		return boardNO;
	}
	public static void setBoardNO(int boardNO) {
		BoardDTO.boardNO = boardNO;
	}
	public static String getUserID() {
		return userID;
	}
	public static void setUserID(String userID) {
		BoardDTO.userID = userID;
	}
	public static String getTitle() {
		return title;
	}
	public static void setTitle(String title) {
		BoardDTO.title = title;
	}
	public static String getContent() {
		return content;
	}
	public static void seContent(String count) {
		BoardDTO.content = content;
	}
	public static int getReadCount() {
		return readCount;
	}
	public static void setReadCount(int readCount) {
		BoardDTO.readCount = readCount;
	}
	public static Date getWriteDate() {
		return writeDate;
	}
	public static void setWriteDate(Date writeDate) {
		BoardDTO.writeDate = writeDate;
	}
	
	

}
