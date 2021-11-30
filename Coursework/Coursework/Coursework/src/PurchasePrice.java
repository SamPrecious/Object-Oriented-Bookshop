
public class PurchasePrice {  //Creates a price for the book being purchased that we can access within button actions
	private float cost;
	public PurchasePrice() {
		this.cost = 0;
	}
	public float getCost() {
		return(cost);
	}
	public void setCost(float cost) {
		this.cost = cost;
	}
}
