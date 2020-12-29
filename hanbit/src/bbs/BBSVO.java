package bbs;

import java.util.Date;

public class BBSVO {
	private int rn;
	private int seq;
	private String title;
	private String content;
	private String writer;
	private Date wdate;
	
	public int getRn() {
		return rn;
	}
	public void setRn(int rn) {
		this.rn = rn;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public Date getWdate() {
		return wdate;
	}
	public void setWdate(Date wdate) {
		this.wdate = wdate;
	}
	
	@Override
	public String toString() {
		return "BBSVO [rn=" + rn + ", seq=" + seq + ", title=" + title + ", content=" + content + ", writer=" + writer
				+ ", wdate=" + wdate + "]";
	}
}
