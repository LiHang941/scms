package com.scms.util.url;
/** 
 * @author  航
 * @version 创建时间：2017年2月23日 下午7:05:54 
 * 类说明 
 * 作为获取用户访问的url方便返回操作 
 */
public class ScmsUrl {
	//系统主页
	public static final String defaultUrl = "index.do";
	private String url ;
	
	private ScmsUrl() {
		this.url = defaultUrl;
	}
	private ScmsUrl(String url) {
		this.url = url;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((url == null) ? 0 : url.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ScmsUrl other = (ScmsUrl) obj;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		return true;
	}
	public static ScmsUrl getInstance(){
		return new ScmsUrl();
	}
	
	public static ScmsUrl getInstance(String url){
		//处理url   如果url满足不满足规格返回null
		
		
		return new ScmsUrl(url);
	}
	
	
}
