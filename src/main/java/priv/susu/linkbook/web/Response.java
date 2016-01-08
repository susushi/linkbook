package priv.susu.linkbook.web;

public class Response<T> {

	/**
	 * 响应代码，等于0为正常响应，其他为异常响应（存在错误）, 默认为0
	 */
	private int code = 0;

	/**
	 * 响应信息，在出现异常时，该字段保存错误信息相关的提示文案，例如"connection timeout", "invalid user"等。
	 */
	private String message = "";

	/**
	 * 当响应成功时，该字段保存服务端返回的数据对象
	 */
	private T data = null;

	/**
	 * 空构造函数
	 */
	public Response() {

	}

	/**
	 * 使用指定的响应代码及响应消息构造当前响应对象
	 * 
	 * @param code
	 *            响应代码
	 * @param message
	 *            响应消息
	 */
	public Response(int code, String message) {
		super();
		this.code = code;
		this.message = message;
	}

	/**
	 * 获取响应代码，大于等于0为正常响应，小于0为异常响应（存在错误）, 默认为0
	 * 
	 * @return 响应代码
	 */
	public int getCode() {
		return code;
	}

	/**
	 * 设置响应代码，大于等于0为正常响应，小于0为异常响应（存在错误）, 默认为0
	 * 
	 * @param code
	 */
	public void setCode(int code) {
		this.code = code;
	}

	/**
	 * 获取响应信息，在出现异常时，该字段保存错误信息相关的提示文案，例如"connection timeout", "invalid user"等。
	 * 
	 * @return 响应信息
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * 设置响应信息，在出现异常时，该字段保存错误信息相关的提示文案，例如"connection timeout", "invalid user"等。
	 * 
	 * @param message
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * 获取当响应成功时，服务端返回的数据对象
	 * 
	 * @return data
	 */
	public T getData() {
		return data;
	}

	/**
	 * 设置当响应成功时，服务端返回的数据对象
	 * 
	 * @param data
	 */
	public void setData(T data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "ClientResponse [code=" + code + ", message=" + message + ", data=" + data + "]";
	}

}
