package com.edinarobotics.scouting.definitions.database;

import com.edinarobotics.scouting.definitions.database.references.ColumnReference;
import com.edinarobotics.scouting.definitions.database.types.Data;
import com.edinarobotics.scouting.definitions.database.Database;

/**
 * This class represents the configuration of a column of data
 * in a {@link Database} table.
 * It tracks the full {@link ColumnReference} and the {@link Data}
 * type of the column.
 */
public class Column {
	private ColumnReference reference;
	private Class<? extends Data> type;
	
	/**
	 * Constructs a Column object using the given {@link ColumnReference}
	 * and the {@link Class} object to define the Column.
	 * @param reference The full ColumnReference to name the Column.
	 * @param type The Class of type {@link Data} to define the type of the Column.
	 */
	public Column(ColumnReference reference, Class<? extends Data> type){
		this.reference = reference;
		this.type = type;
	}
	
	/**
	 * Returns the {@link ColumnReference} object used to define the
	 * name of this Column.
	 * @return The ColumnReference object representing the
	 * name of this Column.
	 */
	public ColumnReference getReference(){
		return reference;
	}
	
	/**
	 * Returns the {@code String} name of this Column.
	 * @return The name of this Column.
	 * @see ColumnReference#getColumnName()
	 */
	public String getColumnName(){
		return reference.getColumnName();
	}
	
	/**
	 * Returns the {@link Class} object (a subclass of {@link Data})
	 * representing the type of this Column.
	 * @return The Class object of the type of this Column.
	 */
	public Class<? extends Data> getType(){
		return type;
	}
	
	/**
	 * Returns a hash code value for this object as described in
	 * {@link Object#hashCode()}.
	 * @return A hash code value for this Column.
	 */
	public int hashCode(){
		return getReference().hashCode() + getType().hashCode();
	}
	
	/**
	 * Indicates whether some Object is equal to this one.
	 * <br/>
	 * An Object is equal to this Column if it
	 * is also a Column object,<br/>
	 * if it has an equal {@link ColumnReference}<br/>
	 * and if it has the same {@link Class} as a type.
	 * @param obj The object to be tested for equality against this one.
	 * @return {@code true} if the objects are equal as defined above
	 * {@code false} otherwise.
	 */
	public boolean equals(Object obj){
		if(obj instanceof Column){
			Column otherColumn = (Column) obj;
			return otherColumn.getReference().equals(getReference()) && otherColumn.getType().equals(getType());
		}
		return false;
	}
}
