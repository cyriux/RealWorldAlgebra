package com.martraire.eggfarm;

/**
 * Represents a quantity of a given product, e.g. "25 eggs".
 */
@ValueObject
public class Quantity {

	private final int quantity;
	private final Product product;

	public Quantity(int quantity, Product product) {
		this.quantity = quantity;
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public Product getProduct() {
		return product;
	}

	public Quantity add(Quantity other) {
		if (!product.equals(other.product)) {
			throw new IllegalArgumentException("Can not add " + product + " with " + other.product);
		}
		return new Quantity(quantity + other.quantity, product);
	}

	public Quantity times(int number) {
		return new Quantity(quantity * number, product);
	}

	@Override
	public int hashCode() {
		return 31 * (31 + product.hashCode()) + quantity;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Quantity)) {
			return false;
		}
		final Quantity other = (Quantity) obj;
		if (product != other.product) {
			return false;
		}
		if (quantity != other.quantity) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return quantity + " " + product;
	}

}
