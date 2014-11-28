package com.walmart.ui;

public enum SortOption {
	BEST_MATCH("Best Match"),

	PRICE_LOT_TO_HIGHT("Price: Low to High"),

	PRICE_HIGHT_TO_LOW("Price: High to Low"),

	NEW("New"),

	BEST_SELLERS("Best Sellers"),

	CUSTOMERS_RATING_HIGH_TO_LOW("Customer Rating: High to Low"),

	ITEM_A_Z("Item Name: A-Z"),

	ITEM_Z_A("Item Name: Z-A");

	private final String text;

	private SortOption(final String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return text;
	}
}
