package com.imooc.pojo.VO;

import java.util.Date;

public class ReportVO {
	String id;
	String title;
	String content;
	String dealUsername;
	String dealVideoId;
	String videoPath;
	int status;
	String submitUsername;
	Date createDate;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getDealUsername() {
		return dealUsername;
	}
	public void setDealUsername(String dealUsername) {
		this.dealUsername = dealUsername;
	}
	public String getDealVideoId() {
		return dealVideoId;
	}
	public void setDealVideoId(String dealVideoId) {
		this.dealVideoId = dealVideoId;
	}
	public String getVideoPath() {
		return videoPath;
	}
	public void setVideoPath(String videoPath) {
		this.videoPath = videoPath;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getSubmitUsername() {
		return submitUsername;
	}
	public void setSubmitUsername(String submitUsername) {
		this.submitUsername = submitUsername;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
}
