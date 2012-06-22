package com.edinarobotics.scouting.definitions.database.types;

/**
 * Defines an integer data type for use in scouting data.
 * This data can store any integer value that can be stored
 * in a java primitive {@code long} value.
 * Many of its methods are implemented similarly to
 * the methods in {@link java.lang.Long}.<br/>
 * Serializing this class using java's built-in serialization
 * ({@link java.io.Serializable}) is <em>not</em> recommended.
 * @see java.lang.Long
 */
@SuppressWarnings("serial")
public class Integer extends Number implements Data, Comparable<Integer> {
	private long value;
	
	/**
	 * Constructs a new {@link Integer} object representing the
	 * given {@code long} value.
	 * @param value The value to store in this object.
	 */
	public Integer(long value){
		this.value = value;
	}
	
	/**
	 * Constructs a new {@link Integer} object representing the
	 * given {@code int} value.
	 * @param value The value to store in this object.
	 */
	public Integer(int value){
		this((long)value);
	}
	
	/**
	 * Constructs a new {@link Integer} object representing the
	 * {@code long} value indicated by the given {@link String}.
	 * @param s The {@link String} representing the value to store in this object.
	 * @see Long#Long(String)
	 */
	public Integer(String s){
		this(new Long(s).longValue());
	}
	
	/**
	 * Returns the {@code long} value stored in this {@link Integer} object.
	 * @return The exact {@code long} value stored in this object.
	 */
	@Override
	public long longValue(){
		return this.value;
	}
	
	@Override
	public int intValue(){
		return (int)longValue();
	}
	
	@Override
	public float floatValue(){
		return (float)longValue();
	}
	
	@Override
	public double doubleValue(){
		return (double)longValue();
	}
	
	/**
	 * Compares this object to another object. The result is
	 * {@code true} if and only if the argument {@code obj}
	 * is also an {@link Integer} object that contains the
	 * same {@code long} value as this object.
	 * @param obj The object to be compared to this
	 * {@link Integer} object.
	 * @return {@code true} if the objects are equal as
	 * described above, {@code false} otherwise.
	 * @see Long#equals(Object)
	 */
	@Override
	public boolean equals(Object obj){
		if(obj instanceof Integer){
			return ((Integer)obj).longValue() == longValue();
		}
		return false;
	}
	
	/**
	 * Returns a hash code value for this object as described
	 * in {@link Object#hashCode()}. This method uses the
	 * implementation in {@link Long#hashCode()}.
	 * @return A hash code value for this object.
	 * @see Long#hashCode()
	 * @see Object#hashCode()
	 */
	@Override
	public int hashCode(){
		return new Long(longValue()).hashCode();
	}
	
	/**
	 * Implements comparisons between different {@link Integer}
	 * objects. This method uses the contract of
	 * {@link Comparable#compareTo(Object)} where the values
	 * compared are the results of calls to
	 * {@link #longValue()}.
	 * @param integer The {@link Integer} object to be
	 * compared to this one.
	 * @return The value {@code 0} if this {@link Integer} is
	 * equal to the argument {@code integer}, a value less than
	 * {@code 0} if this {@link Integer} is less than the
	 * argument {@code integer} and a value greater than
	 * {@code 0} if this {@link Integer} is numerically greater
	 * than the argument {@code integer}.
	 * @see Comparable#compareTo(Object)
	 */
	public int compareTo(Integer integer){
		return new Long(longValue()).compareTo(integer.longValue());
	}
	
	/**
	 * Returns a {@link String} object representing this
	 * {@link Integer} object's value. The value is calculated
	 * using {@link Long#toString()}.
	 * @return A {@link String} object representing the
	 * value of this object in base 10.
	 * @see Long#toString()
	 */
	public String toString(){
		return new Long(longValue()).toString();
	}
	
	/**
	 * Follows the contract of {@link Data#serializeToString()}.
	 * Gives a {@link String} representation of this object
	 * suitable to be passed to {@link Integer#Integer(String)}.
	 * @return A {@link String} representing the value
	 * stored in this {@link Integer} object.
	 * In this case it is an alias for {@link Integer#toString()}.
	 * @see Data#serializeToString()
	 * @see Integer#toString()
	 */
	public String serializeToString(){
		return toString();
	}
}
