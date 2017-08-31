package com.rstang.core.page;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.rstang.core.config.GlobalConfig;
import com.rstang.util.CookieUtils;



public class PageView<T> {

	private int currentPage = 1; // 当前页码
	private int pageSize = Integer.valueOf(GlobalConfig.getConfig("page.pageSize")); // 页面大小，设置为“-1”表示不进行分页（分页无效）
	
	private long count;// 总记录数，设置为“-1”表示不查询总数
	private int first;// 首页索引
	private int last;// 尾页索引
	private int prev;// 上一页索引
	private int next;// 下一页索引	
	
	private boolean firstPage;//是否是第一页
	private boolean lastPage;//是否是最后一页

	private int length = 8;// 显示页面长度
	private int slider = 1;// 前后显示页面长度
	
	private List<T> records = new ArrayList<T>();
	
	private String orderBy = ""; // 标准查询有效， 实例： updatedate desc, name asc

	private String funcName = "page"; // 设置点击页码调用的js函数名称，默认为page，在一页有多个分页对象时使用。
	
	private String funcParam = ""; // 函数的附加参数，第三个参数值。
	
	private String message = ""; // 设置提示消息，显示在“共n条”之后
	
	public PageView() {
		this.pageSize = -1;
	}
	
	/**
	 * 构造方法
	 * @param request 传递 repage 参数，来记住页码
	 * @param response 用于设置 Cookie，记住页码
	 */
	public PageView(HttpServletRequest request, HttpServletResponse response){
		this(request, response, -2);
	}

	/**
	 * 构造方法
	 * @param request 传递 repage 参数，来记住页码
	 * @param response 用于设置 Cookie，记住页码
	 * @param defaultPageSize 默认分页大小，如果传递 -1 则为不分页，返回所有数据
	 */
	public PageView(HttpServletRequest request, HttpServletResponse response, int defaultPageSize){
		// 设置页码参数（传递repage参数，来记住页码）
		String no = request.getParameter("currentPage");
		if (StringUtils.isNumeric(no)){
			CookieUtils.setCookie(response, "currentPage", no);
			this.setCurrentPage(Integer.parseInt(no));
		} else if (request.getParameter("repage") != null) {
			no = CookieUtils.getCookie(request, "currentPage");
			if (StringUtils.isNumeric(no)){
				this.setCurrentPage(Integer.parseInt(no));
			}
		}
		// 设置页面大小参数（传递repage参数，来记住页码大小）
		String size = request.getParameter("pageSize");
		if (StringUtils.isNumeric(size)){
			CookieUtils.setCookie(response, "pageSize", size);
			this.setPageSize(Integer.parseInt(size));
		} else if (request.getParameter("repage") != null){
			size = CookieUtils.getCookie(request, "pageSize");
			if (StringUtils.isNumeric(size)){
				this.setPageSize(Integer.parseInt(size));
			}
		} else if (defaultPageSize != -2){
			this.pageSize = defaultPageSize;
		}
		// 设置页面分页函数
        String funcName = request.getParameter("funcName");
        if (StringUtils.isNotBlank(funcName)){
            CookieUtils.setCookie(response, "funcName", funcName);
            this.setFuncName(funcName);
        }else if (request.getParameter("repage")!=null){
            funcName = CookieUtils.getCookie(request, "funcName");
            if (StringUtils.isNotBlank(funcName)){
                this.setFuncName(funcName);
            }
        }
		// 设置排序参数
		String orderBy = request.getParameter("orderBy");
		if (StringUtils.isNotBlank(orderBy)){
			this.setOrderBy(orderBy);
		}
	}
	
	/**
	 * 构造方法
	 * @param currentPage 当前页码
	 * @param pageSize 分页大小
	 */
	public PageView (int currentPage, int pageSize) {
		this(currentPage, pageSize, 0);
	}
	
	/**
	 * 构造方法
	 * @param currentPage 当前页码
	 * @param pageSize 分页大小
	 * @param count 数据条数
	 */
	public PageView(int currentPage, int pageSize, long count) {
		this(currentPage, pageSize, count, new ArrayList<T>());
	}
	
	/**
	 * 构造方法
	 * @param currentPage 当前页码
	 * @param pageSize 分页大小
	 * @param count 数据条数
	 * @param records 本页数据对象列表
	 */
	public PageView (int currentPage, int pageSize, long count, List<T> records) {
		this.setCount(count);
		this.setCurrentPage(currentPage);
		this.pageSize = pageSize;
		this.records = records;
	}
	
	/**
	 * 初始化参数
	 */
	public void initialize(){
				
		//1
		this.first = 1;
		
		this.last = (int)(count / (this.pageSize < 1 ? 20 : this.pageSize) + first - 1);
		
		if (this.count % this.pageSize != 0 || this.last == 0) {
			this.last++;
		}

		if (this.last < this.first) {
			this.last = this.first;
		}
		
		if (this.currentPage <= 1) {
			this.currentPage = this.first;
			this.firstPage=true;
		}

		if (this.currentPage >= this.last) {
			this.currentPage = this.last;
			this.lastPage=true;
		}

		if (this.currentPage < this.last - 1) {
			this.next = this.currentPage + 1;
		} else {
			this.next = this.last;
		}

		if (this.currentPage > 1) {
			this.prev = this.currentPage - 1;
		} else {
			this.prev = this.first;
		}
		
		//2
		if (this.currentPage < this.first) {// 如果当前页小于首页
			this.currentPage = this.first;
		}

		if (this.currentPage > this.last) {// 如果当前页大于尾页
			this.currentPage = this.last;
		}
		
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize <= 0 ? 10 : pageSize;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	public int getFirst() {
		return first;
	}

	public void setFirst(int first) {
		this.first = first;
	}

	public int getLast() {
		return last;
	}

	public void setLast(int last) {
		this.last = last;
	}

	public int getPrev() {
		if (isFirstPage()) {
			return currentPage;
		} else {
			return currentPage - 1;
		}
	}

	public void setPrev(int prev) {
		this.prev = prev;
	}

	public int getNext() {
		if (isLastPage()) {
			return currentPage;
		} else {
			return currentPage + 1;
		}
	}

	public void setNext(int next) {
		this.next = next;
	}

	public boolean isFirstPage() {
		return firstPage;
	}

	public void setFirstPage(boolean firstPage) {
		this.firstPage = firstPage;
	}

	public boolean isLastPage() {
		return lastPage;
	}

	public void setLastPage(boolean lastPage) {
		this.lastPage = lastPage;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getSlider() {
		return slider;
	}

	public void setSlider(int slider) {
		this.slider = slider;
	}

	public List<T> getRecords() {
		return records;
	}

	public void setRecords(List<T> records) {
		this.records = records;
	}
	
	/**
	 * 设置本页数据对象列表
	 * @param records
	 */
	public PageView<T> setList(List<T> records) {
		this.records = records;
		initialize();
		return this;
	}

	public String getOrderBy() {
		// SQL过滤，防止注入 
		String reg = "(?:')|(?:--)|(/\\*(?:.|[\\n\\r])*?\\*/)|"
					+ "(\\b(select|update|and|or|delete|insert|trancate|char|into|substr|ascii|declare|exec|count|master|into|drop|execute)\\b)";
		Pattern sqlPattern = Pattern.compile(reg, Pattern.CASE_INSENSITIVE);
		if (sqlPattern.matcher(orderBy).find()) {
			return "";
		}
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public String getFuncName() {
		return funcName;
	}

	public void setFuncName(String funcName) {
		this.funcName = funcName;
	}

	public String getFuncParam() {
		return funcParam;
	}

	public void setFuncParam(String funcParam) {
		this.funcParam = funcParam;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	/**
	 * 默认输出当前分页标签 
	 * <div class="page">${page}</div>
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		if (currentPage == first) {// 如果是首页
			sb.append("<li class=\"disabled\"><a href=\"javascript:\">&#171; 上一页</a></li>\n");
		} else {
			sb.append("<li><a href=\"javascript:\" onclick=\"" + funcName + "("+prev+","+pageSize+",'"+funcParam+"');\">&#171; 上一页</a></li>\n");
		}

		int begin = currentPage - (length / 2);

		if (begin < first) {
			begin = first;
		}

		int end = begin + length - 1;

		if (end >= last) {
			end = last;
			begin = end - length + 1;
			if (begin < first) {
				begin = first;
			}
		}

		if (begin > first) {
			int i = 0;
			for (i = first; i < first + slider && i < begin; i++) {
				sb.append("<li><a href=\"javascript:\" onclick=\""+funcName+"("+i+","+pageSize+",'"+funcParam+"');\">"
						+ (i + 1 - first) + "</a></li>\n");
			}
			if (i < begin) {
				sb.append("<li class=\"disabled\"><a href=\"javascript:\">...</a></li>\n");
			}
		}

		for (int i = begin; i <= end; i++) {
			if (i == currentPage) {
				sb.append("<li class=\"active\"><a href=\"javascript:\">" + (i + 1 - first)
						+ "</a></li>\n");
			} else {
				sb.append("<li><a href=\"javascript:\" onclick=\""+funcName+"("+i+","+pageSize+",'"+funcParam+"');\">"
						+ (i + 1 - first) + "</a></li>\n");
			}
		}

		if (last - end > slider) {
			sb.append("<li class=\"disabled\"><a href=\"javascript:\">...</a></li>\n");
			end = last - slider;
		}

		for (int i = end + 1; i <= last; i++) {
			sb.append("<li><a href=\"javascript:\" onclick=\""+funcName+"("+i+","+pageSize+",'"+funcParam+"');\">"
					+ (i + 1 - first) + "</a></li>\n");
		}

		if (currentPage == last) {
			sb.append("<li class=\"disabled\"><a href=\"javascript:\">下一页 &#187;</a></li>\n");
		} else {
			sb.append("<li><a href=\"javascript:\" onclick=\""+funcName+"("+next+","+pageSize+",'"+funcParam+"');\">"
					+ "下一页 &#187;</a></li>\n");
		}

		sb.append("<li class=\"disabled controls\"><a href=\"javascript:\">当前 ");
		sb.append("<input type=\"text\" value=\""+currentPage+"\" onkeypress=\"var e=window.event||event;var c=e.keyCode||e.which;if(c==13)");
		sb.append(funcName+"(this.value,"+pageSize+",'"+funcParam+"');\" onclick=\"this.select();\"/> / ");
		sb.append("<input type=\"text\" value=\""+pageSize+"\" onkeypress=\"var e=window.event||event;var c=e.keyCode||e.which;if(c==13)");
		sb.append(funcName+"("+currentPage+",this.value,'"+funcParam+"');\" onclick=\"this.select();\"/> 条，");
		sb.append("共 " + count + " 条"+(message!=null?message:"")+"</a></li>\n");
		sb.insert(0,"<ul>\n").append("</ul>\n");
		sb.append("<div style=\"clear:both;\"></div>");
		return sb.toString();
	}
	
	/**
	 * 获取分页HTML代码
	 * @return
	 */
	public String getHtml(){
		return toString();
	}
	
	/**
	 * 获取 Hibernate FirstResult
	 */
	public int getFirstResult(){
		int firstResult = (getCurrentPage() - 1) * getPageSize();
		if (firstResult >= getCount()) {
			firstResult = 0;
		}
		return firstResult;
	}
	
	/**
	 * 获取 Hibernate MaxResults
	 */
	public int getMaxResults(){
		return getPageSize();
	}
}
