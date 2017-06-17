package com.scms.bulletin.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.scms.user.entity.User;

/**
 * @author 航
 * @version 创建时间：2017年1月11日 下午9:07:09 类说明
 * 
 *          新闻公告
 */
public class Bulletin implements Serializable{
	// 主键
	private int id;
	// 2. 发布部门  (宿管部, 组织部, 学习部,其他)
	private String deptManagerName;
	// 3. 类别  类别表 id
	private Category category ;
	// 4. 时间
	private Date date;
	// 5. 作者
	private User user;
	// 6. 标题
	private String title;
	// 7. 内容
	private String content;
	// 8. 备注
	private String record;
	// 9. 状态(发布，停用)  -1表示发布  1 停用
	private int state = BULLETIN_STATE_TRUE;
	//浏览消息次数 .
	private long browseInforMationCount ;
	/**
	 * 公开
	 */
	public static int BULLETIN_STATE_TRUE = -1;
	/**
	 * 未公开
	 */
	public static int BULLETIN_STATE_FALSE = 1;
	
	
	public String getStateStr (){
		String temp = getStateMap().get(this.state);
		if(temp == null)
			temp = "未公开";
		return temp;
	}
	
	public static Map<Integer,String> getStateMap(){
		Map<Integer,String> map = new HashMap<Integer,String>();
		map.put(BULLETIN_STATE_TRUE, "公开");
		map.put(BULLETIN_STATE_FALSE, "未公开");
		return  map;
	}
	
	///////////////
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDeptManagerName() {
		return deptManagerName;
	}
	public void setDeptManagerName(String deptManagerName) {
		this.deptManagerName = deptManagerName;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
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
	public String getRecord() {
		return record;
	}
	public void setRecord(String record) {
		this.record = record;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	
	public long getBrowseInforMationCount() {
		return browseInforMationCount;
	}

	public void setBrowseInforMationCount(long browseInforMationCount) {
		this.browseInforMationCount = browseInforMationCount;
	}

	
}
