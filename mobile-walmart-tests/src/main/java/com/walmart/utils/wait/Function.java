package com.walmart.utils.wait;

/**
 * 
 * @author Maryia_Sakalouskaya
 * 
 * @param <F>
 * @param <T>
 * 
 *            Interface to use as condition for Waits
 */
public interface Function<F, T> {

	T apply(F input);

	@Override
	boolean equals(Object object);
}
