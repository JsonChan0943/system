package chen.huai.jie.base.exception;

/**
 * 自定义异常类
 * 
 * @author chenhuaijie
 * 
 */
public class SystemException extends Exception {
	private static final long serialVersionUID = -2598878442370811170L;

	private String errorCode;
	private String errorMsg;

	public SystemException(String errorMsg) {
		super(errorMsg);
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public static void main(String[] args) {
		try {
			throw new SystemException("错误");
		} catch (SystemException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
}
