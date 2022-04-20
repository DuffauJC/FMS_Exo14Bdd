package fr.fms.entities;

public class Article {

	private int idArticle;
	private String description;
	private String brand;
	private double unitaryPrice;
	private int qty=1;

	public Article(int idArticle, String description, String brand, double unitaryPrice) {
		
		setIdArticle(idArticle);
		setDescription(description);
		setBrand(brand);
		setUnitaryPrice(unitaryPrice);
		setQty(qty);

	}

	public Article(String description, String brand, double unitaryPrice) {

		setDescription(description);
		setBrand(brand);
		setUnitaryPrice(unitaryPrice);

	}

	public Article(String description, String brand) {

		setDescription(description);
		setBrand(brand);


	}

	public int getIdArticle() {
		return idArticle;
	}
	public void setIdArticle(int idArticle) {
		this.idArticle = idArticle;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public double getUnitaryPrice() {
		return unitaryPrice;
	}

	public void setUnitaryPrice(double unitaryPrice) {
		this.unitaryPrice = unitaryPrice;
	}


	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	@Override
	public String toString() {
		return "Article [idArticle=" + idArticle + ", description=" + description + ", brand=" + brand
				+ ", unitaryPrice=" + unitaryPrice + ", qty=" + qty + "]";
	}


}
