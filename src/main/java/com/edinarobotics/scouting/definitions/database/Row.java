package com.edinarobotics.scouting.definitions.database;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

import com.edinarobotics.scouting.definitions.database.references.InvalidReferenceException;
import com.edinarobotics.scouting.definitions.database.references.RowReference;
import com.edinarobotics.scouting.definitions.database.types.Data;
import com.edinarobotics.scouting.definitions.database.types.Integer;

/**
 * Row represents a row of data from a {@link Database}. It is the basic
 * result structure of a {@link com.edinarobotics.scouting.definitions.database.queries.Query Query}.<br/>
 * Row objects give snapshots of the state of the data stored in a
 * {@link com.edinarobotics.scouting.definitions.database.Database Database}.
 */
public class Row {
	private Map<Column, Data> values;
	private Column primaryKeyColumn;
	
	/**
	 * Constructs a Row object that contains the given mapping
	 * of values to columns and uses a given {@link Column} as the primary
	 * key value.
	 * @param values The values to be stored in this Row object.
	 * @param primaryKeyColumn The Column object to use as the primary key Column.
	 * @throws NoSuchColumnException If {@code primaryKeyColumn} is not a key in {@code values}. 
	 */
	public Row(Map<Column, Data> values, Column primaryKeyColumn) throws NoSuchColumnException{
		if(!values.keySet().contains(primaryKeyColumn)){
			throw new NoSuchColumnException("Primary key column \""+primaryKeyColumn.getColumnName()+"\" does not exist.");
		}
		this.values = values;
		this.primaryKeyColumn = primaryKeyColumn;
	}
	
	/**
	 * Returns the value of this Row's primary key as an {@link Integer}.
	 * @return The Integer value of this Row's primary key.
	 */
	public Integer getPrimaryKeyValue(){
		try{
			return (Integer)getColumnValue(primaryKeyColumn.getColumnName());
		}catch(NoSuchColumnException e){
			//If this ever happens, something has gone very wrong.
			e.printStackTrace();
			return new Integer(0);
		}
	}
	
	/**
	 * Returns the {@link Column} acting as the primary key column for this Row.
	 * @return The primary key Column for this Row.
	 */
	public Column getPrimaryKeyColumn(){
		return primaryKeyColumn;
	}
	
	/**
	 * Returns the value stored in a {@link Column} for this row.
	 * The Column is specified by its {@code String} name.
	 * The Column object's name is case-sensitive.
	 * @param columnName The name of the Column to query.
	 * @return The {@link Data} value stored in the requested Column.
	 * @throws NoSuchColumnException If the requested column is not in this Row.
	 * @see Column#getColumnName()
	 * @see #getColumnValue(Column)
	 */
	public Data getColumnValue(String columnName) throws NoSuchColumnException{
		Column col = getColumnFromName(columnName);
		if(col == null){
			throw new NoSuchColumnException("Column \""+columnName+"\" does not exist.");
		}
		return getColumnValue(col);
	}
	
	/**
	 * Returns the value stored in a {@link Column} for this row.
	 * @param column The Column to query.
	 * @return The {@link Data} value stored in the requested Column.
	 * @throws NoSuchColumnException If the requested column is not in this Row.
	 * @see #getColumnValue(String)
	 */
	public Data getColumnValue(Column column) throws NoSuchColumnException{
		if(!values.keySet().contains(column)){
			throw new NoSuchColumnException("Column "+column.getColumnName()+" does not exist.");
		}
		return values.get(column);
	}
	
	/**
	 * This is a private method used to retrieve the {@link Column} object
	 * referenced by its {@code String} name.
	 * It is used in {@link #getColumnValue(String)}
	 * @param columnName The {@code String} name of the Column to
	 * retrieve. This name is case-sensitive.
	 * @return The Column represented by {@code columnName} or
	 * {@code null} if no such column exists.
	 * @see #getColumnValue(String)
	 */
	private Column getColumnFromName(String columnName){
		for (Column col : values.keySet()){
			if(col.getColumnName().equals(columnName)){
				return col;
			}
		}
		return null;
	}
	
	/**
	 * Returns the {@link Column} objects that are used in this Row.
	 * @return A {@link Set} containing all Column objects in this
	 * Row.
	 */
	public Set<Column> getColumns(){
		return Collections.unmodifiableSet(values.keySet());
	}
	
	/**
	 * Returns the mapping of {@link Column} objects to {@link Data} objects
	 * represented by this row as a {@link Map}.
	 * @return A Map containing the Column objects in this
	 * Row and their associated Data values.
	 */
	public Map<Column, Data> getValues(){
		return Collections.unmodifiableMap(values);
	}
	
	/**
	 * Returns the {@link RowReference} object that can be used to reference
	 * this Row.
	 * @return The RowReference that references this Row.
	 */
	public RowReference getRowReference(){
		try{
			return new RowReference(getPrimaryKeyColumn().getReference().getTableReference().getTableName()+"#"+getPrimaryKeyValue().toString());
		}catch(InvalidReferenceException e){
			//This should never happen.
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Returns a hash code value for this Row.
	 * This hash code follows the contract of {@link Object#hashCode()}.
	 * @return A hash code value for this Row.
	 */
	public int hashCode(){
		return values.hashCode() + primaryKeyColumn.hashCode();
	}
	
	/**
	 * Tests whether or not this Row is equal to another object.
	 * <br/>
	 * Another object is equal to this Row if it is also a Row,<br/>
	 * the two Row objects contain the same mappings of {@link Column}
	 * objects to {@link Data} objects<br/>
	 * and if the two Row objects' primary key Column identities are equal.
	 * @param obj The object to be tested for equality against this one.
	 * @return {@code true} if the objects are equal as defined above, {@code false} otherwise.
	 * @see Map#equals(Object)
	 * @see Column#equals(Object)
	 * @see #getValues()
	 * @see #getPrimaryKeyColumn()
	 */
	public boolean equals(Object obj){
		if(obj instanceof Row){
			Row otherRow = (Row) obj;
			return otherRow.getValues().equals(getValues()) && otherRow.getPrimaryKeyColumn().equals(getPrimaryKeyColumn());
		}
		return false;
	}
}
