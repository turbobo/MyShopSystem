package shop.utils;

public class PageModel {
	//每页显示多少内容
	private int pageContent;
	//当前页
	private int currentPage;
	//首页
	private int firstPage=1;
	//末页
	private int lastPage;
	//总记录数
	private int totalContent;
	
	public PageModel(){
		
	}
	//创建对象时就设置每页要显示的内容量
	public PageModel(int pageContent){
		this.pageContent=pageContent;
	}
	
	public int getPageContent() {
		return pageContent;
	}
	public void setPageContent(int pageContent) {
		this.pageContent = pageContent;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getFirstPage() {
		return firstPage;
	}
	public void setFirstPage(int firstPage) {
		this.firstPage = firstPage;
	}
	public int getLastPage() {
		//有余数时，最后一页是结果加上1
		return totalContent%pageContent==0?totalContent/pageContent:totalContent/pageContent+1;
	}
	public int getTotalContent() {
		return totalContent;
	}
	public void setTotalContent(int totalContent) {
		this.totalContent = totalContent;
	}
	

}
