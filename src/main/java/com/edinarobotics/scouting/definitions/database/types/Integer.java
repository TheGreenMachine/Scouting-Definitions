package com.edinarobotics.scouting.definitions.database.types;

/**
 * Defines an integer data type for use in scouting data.
 * This data can store any integer value that can be stored
 * in a Java primitive {@code long} value.
 * Many of its methods are implemented similarly to
 * the methods in {@link java.lang.Long}.<br/>
 * Serializing this class using Java's built-in serialization
 * ({@link java.io.Serializable}) is <em>not</em> recommended.
 * @see java.lang.Long
 */
@SuppressWarnings("serial")
public class Integer extends Real implements Data{
	private long value;
	
	/**
	 * Constructs a new {@link Integer} object representing the
	 * given {@code long} value.
	 * @param value The value to store in this object.
	 */
	public Integer(long value){
		super(value);
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
	 * given {@code double} value. The type conversion is performed
	 * with casting.
	 * @param value The value to store in this object.
	 */
	public Integer(double value){
		this((long)value);
	}
	
	/**
	 * Constructs a new {@link Integer} object representing the
	 * {@code long} value indicated by the given {@link String}.
	 * @param s The {@link String} representing the value to store in this object.
	 * @see Long#Long(String)
	 */
	public Integer(String s){
		this(new Long(s));
	}
	
	/**
	 * Returns the {@code long} value stored in this {@link Integer} object.
	 * @return The exact {@code long} value stored in this object.
	 */
	@Override
	public long longValue(){
		return this.value;
	}
	
	/**
	 * Returns the {@code long} value stored in this object as an
	 * {@code int}. The conversion is performed by casting.
	 * @return An {@code int} representation of the {@code double}
	 * value stored in this object.
	 */
	@Override
	public int intValue(){
		return (int)longValue();
	}
	
	/**
	 * Returns the {@code long} value stored in this object as an
	 * {@code float}. The conversion is performed by casting.
	 * @return A {@code float} representation of the {@code double}
	 * value stored in this object.
	 */
	@Override
	public float floatValue(){
		return (float)longValue();
	}
	
	/**
	 * Returns the {@code long} value stored in this object as an
	 * {@code double}. The conversion is performed by casting.
	 * @return A {@code double} representation of the {@code double}
	 * value stored in this object.
	 */
	@Override
	public double doubleValue(){
		return (double)longValue();
	}
	
	/**
	 * Compares this object to another object. The result is
	 * {@code true} if and only if the argument {@code obj}
	 * is also a {@link Real} or {@link Double} object that
	 * contains the same {@code long} or {@code double}
	 * value as this object.
	 * @param obj The object to be compared to this
	 * {@link Integer} object.
	 * @return {@code true} if the objects are equal as
	 * described above, {@code false} otherwise.
	 * @see Double#equals(Object)
	 * @see Long#equals(Object)
	 */
	@Override
	public boolean equals(Object obj){
		if(obj instanceof Integer){
			return ((Integer)obj).longValue() == longValue();
		}
		else if(obj instanceof Real){
			return ((Real)obj).doubleValue() == doubleValue();
		}
		return false;
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
