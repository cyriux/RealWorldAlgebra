package com.martraire.eggfarm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Represents the chronicle of production at a regular pace, i.e. daily
 */
@ValueObject
public class ProductionChronicle implements Iterable<Production> {
	private final List<Production> productions;

	public static final ProductionChronicle of(Collection<Production> productions) {
		return new ProductionChronicle(new ArrayList<Production>(productions));
	}

	public ProductionChronicle(Production... productions) {
		this(Arrays.asList(productions));
	}

	private ProductionChronicle(List<Production> productions) {
		this.productions = productions;
		Collections.sort(this.productions);
	}

	public ProductionChronicle add(ProductionChronicle other) {
		final List<Production> merged = new ArrayList<Production>(productions);
		merged.addAll(other.productions);
		return new ProductionChronicle(merged);
	}

	public ProductionChronicle times(int number) {
		final List<Production> result = new ArrayList<Production>(productions.size());
		for (Production production : productions) {
			result.add(production.times(number));
		}
		return new ProductionChronicle(result);
	}

	public Production cumulatedBy(Product product) {
		Date endDate = null;
		// equivalent to filter -> reduce
		Quantity sum = new Quantity(0, product);
		for (Production p : productions) {
			final Quantity quantity = p.getQuantity();
			if (quantity.getProduct().equals(product)) {
				sum = sum.add(quantity);
			}
			endDate = p.getDate();
		}
		return new Production(sum, endDate);
	}

	@Override
	public Iterator<Production> iterator() {
		return productions.iterator();
	}

	public int size() {
		return productions.size();
	}

	@Override
	public int hashCode() {
		return 31 * 1 + productions.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof ProductionChronicle)) {
			return false;
		}
		final ProductionChronicle other = (ProductionChronicle) obj;
		return productions.equals(other.productions);
	}

	@Override
	public String toString() {
		return "ProductionChronicle:" + size() + " productions";
	}

}
