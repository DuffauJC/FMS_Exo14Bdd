package fr.fms.entities;

public class Article {

	private int idArticle;
	private String description;
	private String brand;
	private double unitaryPrice;
	private int qty=1;
	private int idCategory;
	private String catName;
	private String descriptions;

	/**
	 * 
	 * @param idArticle
	 * @param description
	 * @param brand
	 * @param unitaryPrice
	 */
	public Article(int idArticle, String description, String brand, double unitaryPrice) {

		setIdArticle(idArticle);
		setDescription(description);
		setBrand(brand);
		setUnitaryPrice(unitaryPrice);
		setQty(qty);

	}
	/**
	 * 
	 * @param description
	 * @param brand
	 * @param unitaryPrice
	 */
	public Article(String description, String brand, double unitaryPrice) {

		setDescription(description);
		setBrand(brand);
		setUnitaryPrice(unitaryPrice);

	}
	/**
	 * 
	 * @param description
	 * @param brand
	 */
	public Article(String description, String brand) {

		setDescription(description);
		setBrand(brand);


	}
	/**
	 * 
	 * @param idArticle
	 * @param description
	 * @param brand
	 * @param unitaryPrice
	 * @param catName
	 */
	public Article(int idArticle, String description, String brand, double
			unitaryPrice, String catName) {
		setIdArticle(idArticle); 
		setDescription(description); 
		setBrand(brand);
		setUnitaryPrice(unitaryPrice); 
		setCatName(catName); 
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

	public int getIdCategory() {
		return idCategory;
	}

	public void setIdCategory(int idCategory) {
		this.idCategory = idCategory;
	}

	public String getCatName() {
		return catName;
	}

	public void setCatName(String catName) {
		this.catName = catName;
	}

	public String getDescriptions() {
		return descriptions;
	}

	public void setDescriptions(String descriptions) {
		this.descriptions = descriptions;
	}

	@Override
	public String toString() {
		return "Article [idArticle=" + idArticle + ", description=" + description + ", brand=" + brand
				+ ", unitaryPrice=" + unitaryPrice + "]";
	}




}
