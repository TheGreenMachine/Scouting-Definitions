package com.edinarobotics.scouting.definitions.database.changes;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import com.edinarobotics.scouting.definitions.database.InvalidSchemaException;
import com.edinarobotics.scouting.definitions.database.InvalidTargetException;
import com.edinarobotics.scouting.definitions.database.references.ColumnReference;
import com.edinarobotics.scouting.definitions.database.references.InvalidReferenceException;
import com.edinarobotics.scouting.definitions.database.references.TableReference;
import com.edinarobotics.scouting.definitions.database.types.Data;

/**
 * This {@link Change} is used to insert a new row of
 * data into a
 * {@link com.edinarobotics.scouting.definitions.database.Database Database}
 * table.
 * <br/>
 * This Change cannot be used to edit an existing row (detected by the primary key value).
 * It is the responsibility of Database implementations to prevent this usage and to throw
 * an exception.
 * <br/>
 * Additionally, the data included in a RowInsertionChange must define values for all
 * database columns. The only column that can be left undefined is the table's
 * primary key column. Database implementations must enforce this restriction
 * and automatically assign primary key values to inserted rows if no primary key
 * is explicitly defined.
 */
public final class RowInsertionChange implements Change{
	private Map<ColumnReference, Data> newValues;
	
	/**
	 * Constructs a new RowInsertionChange that attempts to create a row containing
	 * the values defined in the {@code newValues} {@link Map}.
	 * @param newValues The Map of {@link Data} values to be inserted in the
	 * columns defined by their linked {@link ColumnReference} objects.
	 * @throws InvalidSchemaException If {@code newValues} is an empty map. It is impossible
	 * to insert zero columns into a table.
	 * @throws InvalidTargetException If the ColumnReference objects in {@code newValues}
	 * reference columns in different tables. A RowInsertionChange can only insert values
	 * into a single table.
	 */
	public RowInsertionChange(Map<ColumnReference, Data> newValues) throws InvalidSchemaException, InvalidTargetException{
		if (newValues.size() <= 0){
			throw new InvalidSchemaException("Cannot insert zero columns into a table.");
		}
		TableReference table = ((ColumnReference) newValues.keySet().toArray()[0]).getTableReference();
		for(ColumnReference col : newValues.keySet()){
			if(!col.getTableReference().equals(table)){
				throw new InvalidTargetException("Cannot insert data into different tables.");
			}
		}
		this.newValues = new HashMap<ColumnReference, Data>(newValues);
	}
	
	/**
	 * Constructs a RowInsertionChange that attempts to create a row in the table
	 * referenced by {@code tableReference} containing the values in the
	 * {@link Map} {@code newValues}.
	 * {@code newValues} is a map of {@code String} column names
	 * (<em>not</em> column references) to {@link Data} values.
	 * @param tableReference The {@link TableReference} indicating the table
	 * into which this row is to be inserted.
	 * @param newValues The Map containing the values to be inserted into
	 * {@code tableReference}.
	 * @throws InvalidReferenceException If either {@code tableReference} or the {@code String}
	 * objects in {@code newValues} are not valid when used as a part of a {@link ColumnReference}.
	 * @throws InvalidSchemaException If {@code newValues} is an empty map. It is impossible
	 * to insert zero columns into a table.
	 * @throws InvalidTargetException If the ColumnReference objects in {@code newValues}
	 * reference columns in different tables. A RowInsertionChange can only insert values
	 * into a single table.
	 */
	public RowInsertionChange(TableReference tableReference, Map<String, Data> newValues) throws InvalidReferenceException, InvalidSchemaException, InvalidTargetException{
		this(mapStringToColumnReference(tableReference.toString(), newValues));
	}
	
	/**
	 * Constructs a RowInsertionChange that attempts to create a row containing
	 * the values in {@code newValues}, in the table referenced by the table
	 * reference {@code String}, {@code tableReference}.
	 * {@code newValues} follows the same format as in {@link #RowInsertionChange(TableReference, Map)}.
	 * The only difference between this constructor and {@link #RowInsertionChange(TableReference, Map)}
	 * is that this constructor uses a table reference {@link String} instead of a
	 * {@link TableReference} object.
	 * @param tableReference The table reference {@code String} indicating the table
	 * into which this row is to be inserted.
	 * @param newValues The {@link Map} containing the values to be inserted into
	 * {@code tableReference}.
	 * @throws InvalidReferenceException If either {@code tableReference} or
	 * the {@code String} keys in {@code newValues} are not valid when used
	 * as a part of a {@link ColumnReference}.
	 * @throws InvalidSchemaException If {@code newValues} is an empty map. It is impossible
	 * to insert zero columns into a table.
	 * @throws InvalidTargetException If the ColumnReference objects in {@code newValues}
	 * reference columns in different tables. A RowInsertionChange can only insert values
	 * into a single table.
	 * @see #RowInsertionChange(TableReference, Map)
	 */
	public RowInsertionChange(String tableReference, Map<String, Data> newValues) throws InvalidReferenceException, InvalidSchemaException, InvalidTargetException{
		this(new TableReference(tableReference), newValues);
	}
	
	/**
	 * This is a private method used to convert a {@code String} table reference and a Map of
	 * {@code String} column names into a map with {@link ColumnReference} keys.
	 * @param tableReferenceString The {@code String} to use as the table
	 * name when creating ColumnReference objects.
	 * @param oldMap The map with {@code String} keys to be converted to a map with
	 * ColumnReference keys.
	 * @return A map with ColumnReference keys created from {@code tableReferenceString} and
	 * {@code oldMap}.
	 * @throws InvalidReferenceException If {@code tableReferenceString} and the keys from
	 * {@code oldMap} produce an invalid ColumnReference.
	 */
	private static Map<ColumnReference, Data> mapStringToColumnReference(String tableReferenceString, Map<String, Data> oldMap) throws InvalidReferenceException{
		Map<ColumnReference, Data> newMap = new HashMap<ColumnReference, Data>();
		for(String key : oldMap.keySet()){
			newMap.put(new ColumnReference(tableReferenceString+"."+key), oldMap.get(key));
		}
		return newMap;
	}
	
	/**
	 * Returns the {@link Map} containing the {@link ColumnReference} values
	 * and {@link Data} objects to be inserted.
	 * @return The Map containing the values to be inserted.
	 */
	public Map<ColumnReference, Data> getValues(){
		return Collections.unmodifiableMap(newValues);
	}
	
	/**
	 * Returns the {@link TableReference} object referencing the table into which
	 * the new row is to be inserted.
	 * @return
	 */
	public TableReference getTargetTable(){
		return ((ColumnReference) getValues().keySet().toArray()[0]).getTableReference();
	}
	
	/**
	 * Returns the name of the table into which the new row is to be inserted.
	 * @return The {@code String} name of the table into which the new row is
	 * to be inserted.
	 */
	public String getTargetTableName(){
		return getTargetTable().getTableName();
	}
	
	/**
	 * Returns a hash code value for this RowInsertionChange as defined in
	 * {@link Object#hashCode()}.
	 * @return A hash code value for this object.
	 */
	public int hashCode(){
		return getValues().hashCode();
	}
	
	/**
	 * Determines whether some object is equal to this RowInsertionChange.
	 * An {@code Object} is equal to this one if it is also a RowInsertionChange
	 * and if its internal {@link Data} mapping (from {@link #getValues()}) is
	 * equal to this RowInsertionChange's internal mapping.
	 * @param obj The object to be tested for equality against this one.
	 * @return {@code true} if the objects are equal as defined above,
	 * {@code false} otherwise.
	 */
	public boolean equals(Object obj){
		if(obj instanceof RowInsertionChange){
			return ((RowInsertionChange) obj).getValues().equals(getValues());
		}
		return false;
	}
}
