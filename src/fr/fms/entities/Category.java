package fr.fms.entities;

public class Category {

	private int idCategory;
	private String catName;
	private String description;
	
	public Category(int idCategory, String catName, String description) {
		setIdCategory(idCategory);
		setCatName(catName);
		setDescription(description);
		
	}
	
	public Category( String catName, String description) {
		setCatName(catName);
		setDescription(description);
		
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Category [idCategory=" + idCategory + ", catName=" + catName + ", description=" + description + "]";
	}










}
