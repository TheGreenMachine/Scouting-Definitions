package com.edinarobotics.scouting.manager.database.types;

import junit.framework.TestCase;
import com.edinarobotics.scouting.manager.database.types.Integer;

public class TestInteger extends TestCase{
	public Integer int0;
	public Integer int1;
	public Integer intNeg1;
	public Integer intLongMax;
	public Integer intLongMin;
	
	public TestInteger(){
		super("Integer");
	}
	
	/**
	 * This method sets up all variables used in testing.
	 * It creates various {@link Integer} objects
	 * that are used in the tests.
	 */
	public void setUp(){
		int0 = new Integer((long)0);
		int1 = new Integer("1");
		intNeg1 = new Integer((int)-1);
		intLongMax = new Integer(Long.MAX_VALUE);
		intLongMin = new Integer(Long.MIN_VALUE);
	}
	
	/**
	 * This method tests the operation of the {@link Integer#longValue()} method.
	 * It checks that {@link Integer} objects store their given
	 * {@code long} values correctly.
	 */
	public void testStoresLongValuesCorrectly(){
		assertEquals("Integer object does not store 0 correctly!", 0, int0.longValue());
		assertEquals("Integer object does not store 1 correctly!", 1, int1.longValue());
		assertEquals("Integer object does not store -1 correctly!", -1, intNeg1.longValue());
		assertEquals("Integer object does not store Long.MAX_VALUE correctly!", Long.MAX_VALUE, intLongMax.longValue());
		assertEquals("Integer object does not store Long.MIN_VALUE correctly!", Long.MIN_VALUE, intLongMin.longValue());
	}
	
	/**
	 * This method tests the operation of the {@link Integer#intValue()} method.
	 * It checks that {@link Integer} objects can turn their {@code long}
	 * values into {@code int} values when requested.
	 */
	public void testStoresIntValuesCorrectly(){
		assertEquals("Integer object does not (int) cast 0 correctly!", 0, int0.intValue());
		assertEquals("Integer object does not (int) cast 1 correctly!", 1, int1.intValue());
		assertEquals("Integer object does not (int) cast -1 correctly!", -1, intNeg1.intValue());
		assertEquals("Integer object does not (int) cast Long.MAX_VALUE correctly!", (int)Long.MAX_VALUE, intLongMax.intValue());
		assertEquals("Integer object does not (int) cast Long.MIN_VALUE correctly!", (int)Long.MIN_VALUE, intLongMin.intValue());
	}
	
	/**
	 * This method tests the operation of the {@link Integer#doubleValue()} method.
	 * It checks that {@link Integer} objects can turn their {@code long}
	 * values into {@code double} values when requested.
	 */
	public void testStoresDoubleValuesCorrectly(){
		final double DELTA = 1e-20;
		assertEquals("Integer object does not (double) cast 0 correctly!", 0, int0.doubleValue(), DELTA);
		assertEquals("Integer object does not (double) cast 1 correctly!", 1, int1.doubleValue(), DELTA);
		assertEquals("Integer object does not (double) cast -1 correctly!", -1, intNeg1.doubleValue(), DELTA);
		assertEquals("Integer object does not (double) cast Long.MAX_VALUE correctly!", Long.MAX_VALUE, intLongMax.doubleValue(), DELTA);
		assertEquals("Integer object does not (double) cast Long.MIN_VALUE correctly!", Long.MIN_VALUE, intLongMin.doubleValue(), DELTA);
	}
	
	/**
	 * This method tests the operation of the {@link Integer#doubleValue()} method.
	 * It checks that {@link Integer} objects can turn their {@code long}
	 * values into {@code float} values when requested.
	 */
	public void testStoresFloatValuesCorrectly(){
		final double DELTA = 1e-20;
		assertEquals("Integer object does not (float) cast 0 correctly!", 0, int0.floatValue(), DELTA);
		assertEquals("Integer object does not (float) cast 1 correctly!", 1, int1.floatValue(), DELTA);
		assertEquals("Integer object does not (float) cast -1 correctly!", -1, intNeg1.floatValue(), DELTA);
		assertEquals("Integer object does not (float) cast Long.MAX_VALUE correctly!", Long.MAX_VALUE, intLongMax.floatValue(), DELTA);
		assertEquals("Integer object does not (float) cast Long.MIN_VALUE correctly!", Long.MIN_VALUE, intLongMin.floatValue(), DELTA);
	}
	
	/**
	 * This method tests the operation of the {@link Integer#equals(Object)} method.
	 * It checks that {@link Integer} objects properly follow the contract
	 * of the {@link Object#equals(Object)} method.
	 * @see Object#equals(Object)
	 */
	public void testEquals(){
		assertTrue("Integer objects with the same value do not register as equal! (0)", int0.equals(new Integer(0)));
		assertTrue("Integer objects with the same value do not register as equal! (1)", int1.equals(new Integer(1)));
		assertTrue("Integer objects with the same value do not register as equal! (-1)", intNeg1.equals(new Integer(-1)));
		assertTrue("Integer objects with the same value do not register as equal! (Long.MAX_VALUE)", intLongMax.equals(new Integer(Long.MAX_VALUE)));
		assertTrue("Integer objects with the same value do not register as equal! (Long.MIN_VALUE)", intLongMin.equals(new Integer(Long.MIN_VALUE)));
		assertTrue("Integer objects should be equal to themselves!", intLongMin.equals(intLongMin));
	}
	
	/**
	 * This method tests the operation of the {@link Integer#equals(Object)} method.
	 * It checks that {@link Integer} objects do not report that they are equal
	 * when they should not according to the contract of {@link Object#equals(Object)}.
	 * @see Object#equals(Object)
	 */
	public void testNotEquals(){
		assertTrue("Integer objects with different values register as equal. (0 == 5)?",!int0.equals(new Integer(5)));
		assertTrue("Integer objects are not supposed to be equal to java.lang.Integer objects!", !int0.equals(5));
		assertTrue("Integer objects should never be equal to Strings!", !int0.equals("0"));
		assertTrue("Integer objects should never be equal to null!", !intLongMin.equals(null));
	}
	
	/**
	 * This method tests the {@link Integer#compareTo(Integer)} method.
	 * It tests that {@link Integer} objects properly implement its contract.
	 * @see Comparable#compareTo(Object)
	 */
	public void testCompareTo(){
		assertTrue("0 should be less than Long.MAX_VALUE!",int0.compareTo(intLongMax)<0);
		assertTrue("Long.MAX_VALUE should be greater than 0!",intLongMax.compareTo(int0)>0);
		assertTrue("0 should be equal to 0!",int1.compareTo(int1)==0);
	}
	
	/**
	 * This method tests the {@link Integer#toString()} (and by proxy {@link Integer#serializeToString()})
	 * method.
	 * It tests that {@link Integer} objects are able to be compared to other
	 * {@link Integer objects}.
	 */
	public void testToString(){
		assertTrue("Integer(0) does not generate the correct toString() value!", int0.toString().equals("0"));
		assertTrue("Integer(1) does not generate the correct toString() value!", int1.toString().equals(new Long(1).toString()));
		assertTrue("Integer(-1) does not generate the correct toString() value!", intNeg1.toString().equals(new Long(-1).toString()));
		assertTrue("Integer(Long.MAX_VALUE) does not generate the correct toString() value!", intLongMax.toString().equals(new Long(Long.MAX_VALUE).toString()));
		assertTrue("Integer(Long.MIN_VALUE) does not generate the correct toString() value!", intLongMin.toString().equals(new Long(Long.MIN_VALUE).toString()));
	}
	
	/**
	 * This method tests that the {@link Integer#serializeToString()} method returns values that
	 * can be used to reconstruct {@link Integer objects} through the {@link Integer#Integer(String)}
	 * constructor.
	 * @see Integer#serializeToString()
	 * @see Integer#Integer(String)
	 * @see Data#serializeToString()
	 */
	public void testToStringReconstruction(){
		assertTrue("Integer(0) cannot be reconstructed from its toString() value!", int0.equals(new Integer(int0.toString())));
		assertTrue("Integer(1) cannot be reconstructed from its toString() value!", int1.equals(new Integer(int1.toString())));
		assertTrue("Integer(-1) cannot be reconstructed from its toString() value!", intNeg1.equals(new Integer(intNeg1.toString())));
		assertTrue("Integer(Long.MAX_VALUE) cannot be reconstructed from its toString() value!", intLongMax.equals(new Integer(intLongMax.toString())));
		assertTrue("Integer(Long.MIN_VALUE) cannot be reconstructed from its toString() value!", intLongMin.equals(new Integer(intLongMin.toString())));
	}
}
