
public class Result {
	private String title;
	private String price;
	
	public Result(String title, String price) {
		this.title = title;
		this.price = price;
	}
	public String getPrice() {
		return price;
	}
	
	public void setPrice(String price) {
		this.price = price;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
}
