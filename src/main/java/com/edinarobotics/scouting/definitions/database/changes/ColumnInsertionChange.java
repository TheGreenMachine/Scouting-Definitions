package com.edinarobotics.scouting.definitions.database.changes;

import com.edinarobotics.scouting.definitions.database.Column;
import com.edinarobotics.scouting.definitions.database.references.ColumnReference;
import com.edinarobotics.scouting.definitions.database.references.InvalidReferenceException;
import com.edinarobotics.scouting.definitions.database.types.Data;

/**
 * This change is used to insert a new {@link Column}
 * into a {@link com.edinarobotics.scouting.definitions.database.Database Database}
 * table.
 * <br/>
 * It is the responsibility of the Database implementation to ensure
 * that inserting a Column that has the same name as
 * an existing Column (but not necessarily the same type) will
 * fail and throw an exception. To change the type of a Column,
 * delete the Column and create a new one.
 */
public final class ColumnInsertionChange implements Change{
	private Column newColumn;
	
	/**
	 * Constructs a new ColumnInsertionChange that will attempt
	 * to create the given {@link Column}.
	 * @param newColumn The Column to create.
	 */
	public ColumnInsertionChange(Column newColumn){
		this.newColumn = newColumn;
	}
	
	/**
	 * Constructs a new ColumnInsertionChange that will attempt
	 * to create a {@link Column} with the given {@link ColumnReference}
	 * and the given {@link Class} type.
	 * @param newColumnReference The ColumnReference that will define
	 * the name of the created Column.
	 * @param newType The {@link Class} object that will define the
	 * type of the created Column.
	 * @see Column#Column(ColumnReference, Class)
	 */
	public ColumnInsertionChange(ColumnReference newColumnReference, Class<? extends Data> newType){
		this(new Column(newColumnReference, newType));
	}
	
	/**
	 * Constructs a new ColumnInsertionChange that will attempt
	 * to create a {@link Column} with the given {@code String}
	 * column reference and the given {@link Class} type.
	 * @param newColumnReference The {@code String} column reference
	 * that will define the name of the created Column.
	 * @param newType The {@link Class} object that will define the
	 * type of the created Column.
	 * @throws InvalidReferenceException If {@code newColumnReference}
	 * is not a valid column reference {@code String}.
	 * @see ColumnReference
	 * @see #ColumnInsertionChange(ColumnReference, Class)
	 */
	public ColumnInsertionChange(String newColumnReference, Class<? extends Data> newType) throws InvalidReferenceException{
		this(new ColumnReference(newColumnReference), newType);
	}
	
	/**
	 * Returns the {@link Column} representing
	 * the configuration of the Column that is
	 * to be created.
	 * @return The Column representing the
	 * configuration of the requested new Column.
	 */
	public Column getNewColumn(){
		return newColumn;
	}
	
	/**
	 * Returns the {@link ColumnReference} representing
	 * the name of the {@link Column} that is to be
	 * created.
	 * @return The ColumnReference representing the
	 * name of the requested new Column.
	 * @see Column#getReference()
	 */
	public ColumnReference getNewColumnReference(){
		return getNewColumn().getReference();
	}
	
	/**
	 * Returns the {@code String} name of the
	 * {@link Column} that is to be created.
	 * @return The name of the requested
	 * new Column.
	 * @see Column#getColumnName()
	 */
	public String getNewColumnName(){
		return getNewColumn().getColumnName();
	}
	
	/**
	 * Returns the {@link Class} object representing
	 * the type of the {@link Column} that is to be
	 * created.
	 * @return The type Class of the requested
	 * new Column.
	 */
	public Class<? extends Data> getNewColumnType(){
		return getNewColumn().getType();
	}
	
	/**
	 * Returns a hash code value for this
	 * ColumnInsertionChange as defined in
	 * {@link Object#hashCode()}.
	 * @return A hash code value for this object.
	 */
	public int hashCode(){
		return getNewColumn().hashCode();
	}
	
	/**
	 * Determines whether or not some object is equal to this ColumnInsertionChange.
	 * An object is equal to this one if it is also a ColumnInsertionChange and
	 * if its {@link #getNewColumn()} method returns a {@link Column} that
	 * is equal to the Column returned by this ColumnInsertionChange's
	 * {@code getNewColumn()} method.
	 * @param obj The object to be tested for equality against this one.
	 * @return {@code true} if the objects are equal as defined above, {@code false}
	 * otherwise.
	 */
	public boolean equals(Object obj){
		if(obj instanceof ColumnInsertionChange){
			return ((ColumnInsertionChange) obj).getNewColumn().equals(getNewColumn());
		}
		return false;
	}
}
