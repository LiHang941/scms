package com.scms.currency.exception;
/** 
 * @author  航
 * @version 创建时间：2017年3月3日 上午11:20:55 
 * 类说明 
 */
public abstract class SysException extends Exception{
		
		private String errorMsg;

		public SysException() {
			super();
		}

		public SysException(String message, Throwable cause) {
			super(message, cause);
			errorMsg = message;
		}

		public SysException(String message) {
			super(message);
			errorMsg = message;
		}

		public SysException(Throwable cause) {
			super(cause);
		}

		public String getErrorMsg() {
			return errorMsg;
		}

		public void setErrorMsg(String errorMsg) {
			this.errorMsg = errorMsg;
		}


}
