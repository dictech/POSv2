package com.pos.inventory.model;

import java.math.BigDecimal;

public class testProCat {
	public static void main(String args[]) {
		
		ProductCategory category =  new ProductCategory();
		category.setName("FOOD");
		category.setDesc("Edible things");
		ProductCategoryDAO.createCategory(category);
		
		ProductCategoryDAO.getAllCategory();
		
		
//		ProductCategory category2 = ProductCategoryDAO.getCategory(new BigDecimal(1));
//		category2.setDesc("Stuff used to clean your bathroom");
//		ProductCategoryDAO.updateCategory(category2);
		
	//	ProductCategoryDAO.deleteCategory(new BigDecimal(1));
		
	}
}
