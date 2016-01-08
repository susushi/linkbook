package priv.susu.linkbook;

import java.util.ArrayList;
import java.util.List;

/**
 * 流信息数据对象，通常用于客户端下拉获取数据操作。
 * 
 */
public class FlowWrapper<T> {

	/**
	 * 信息流记录总数
	 */
	private int count = 0;

	/**
	 * 信息流记录列表
	 */
	private List<T> items = new ArrayList<T>();

	/**
	 * 断点信息，用于获取下一批信息流
	 */
	private String breakpoint = "";

	/**
	 * 获取信息流记录总数
	 * 
	 * @return 信息流记录总数
	 */
	public int getCount() {
		return count;
	}

	/**
	 * 设置信息流记录总数
	 * 
	 * @param count
	 */
	public void setCount(int count) {
		this.count = count;
	}

	/**
	 * 获取信息流记录列表，该方法不会返回null
	 * 
	 * @return 信息流记录列表
	 */
	public List<T> getItems() {
		return items;
	}

	/**
	 * 设置信息流记录列表
	 * 
	 * @param items
	 */
	public void setItems(List<T> items) {
		if (items != null) {
			this.items = items;
		} else {
			this.items = new ArrayList<T>();
		}
	}

	/**
	 * 获取断点信息,用于获取下一批信息流
	 * 
	 * @return 断点数据,用于获取下一批信息流
	 */
	public String getBreakpoint() {
		return breakpoint;
	}

	/**
	 * 设置断点信息,用于获取下一批信息流
	 * 
	 * @param breakpoint
	 */
	public void setBreakpoint(String breakpoint) {
		this.breakpoint = breakpoint;
	}

	@Override
	public String toString() {
		return "FlowWrapper [count=" + count + ", items=" + items + ", breakpoint=" + breakpoint + "]";
	}

}
