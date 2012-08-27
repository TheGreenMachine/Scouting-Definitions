package com.edinarobotics.scouting.definitions.database.changes;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import com.edinarobotics.scouting.definitions.database.Column;
import com.edinarobotics.scouting.definitions.database.InvalidSchemaException;
import com.edinarobotics.scouting.definitions.database.references.ColumnReference;
import com.edinarobotics.scouting.definitions.database.types.Integer;
import com.edinarobotics.scouting.definitions.database.references.InvalidReferenceException;
import com.edinarobotics.scouting.definitions.database.references.TableReference;

/**
 * This {@link Change} can be used to create a full
 * {@link com.edinarobotics.scouting.definitions.database.Database Database}
 * table.
 * It takes a table name, a {@link Set} of {@link Column} objects and an indication
 * of which column is to act as the new table's primary key column.
 */
public final class TableCreationChange implements Change{
	private TableReference newTableRef;
	private Set<Column> newColumns;
	
	/**
	 * Constructs a TableCreationChange which will attempt to create a table
	 * with name {@code newTableName}, columns {@code newColumns} and
	 * primary key column {@code primaryKeyColumn}.
	 * @param newTableName The name of the table to be created.
	 * @param newColumns A {@link Set} of the {@link Column} objects
	 * to be created in this new table.
	 * @param primaryKeyColumn The Column to act as the primary key
	 * column for the new table. 
	 * @throws InvalidReferenceException If {@code newTableName} is not a valid
	 * table reference {@code String} or if one of the Column objects in
	 * {@code newColumns} is a Column in another table.
	 * @throws InvalidSchemaException If {@code newColumns} is an empty set,
	 * if the {@code primaryKeyColumn} is not in {@code newColumns} or
	 * if two Column objects have the same name.
	 */
	public TableCreationChange(String newTableName, Set<Column> newColumns, Column primaryKeyColumn) throws InvalidReferenceException, InvalidSchemaException{
		this(new TableReference(newTableName), newColumns, primaryKeyColumn);
	}
	
	/**
	 * Constructs a new TableCreationChange which will attempt to create a table
	 * with reference {@code newTableReference}, columns {@code newColumns} and
	 * primary key column {@code primaryKeyColumn}.
	 * @param newTableReference The {@link TableReference} for the table that is
	 * to be created.
	 * @param newColumns A {@link Set} of the {@link Column} objects to be created
	 * in this new table.
	 * @param primaryKeyColumn The Column to act as the primary key column for
	 * the new table.
	 * @throws InvalidReferenceException If {@code newTableName} is not a valid
	 * table reference {@code String} or if one of the Column objects in
	 * {@code newColumns} is a Column in another table.
	 * @throws InvalidSchemaException If {@code newColumns} is an empty set,
	 * if the {@code primaryKeyColumn} is not in {@code newColumns} or
	 * if two Column objects have the same name.
	 */
	public TableCreationChange(TableReference newTableReference, Set<Column> newColumns, Column primaryKeyColumn) throws InvalidReferenceException, InvalidSchemaException{
		this.newTableRef = newTableReference;
		if(newColumns.size() <= 0){
			throw new InvalidSchemaException("A table cannot be created without columns.");
		}
		if(!newColumns.contains(primaryKeyColumn)){
			throw new InvalidSchemaException("A table must have a primary key column.");
		}
		if(!primaryKeyColumn.getType().equals(Integer.class)){
			throw new InvalidSchemaException("The primary key column for a table must be an Integer column.");
		}
		for(Column col : newColumns){
			if(!col.getReference().getTableReference().equals(newTableRef)){
				throw new InvalidReferenceException(String.format("Column \"%s\" is not defined for the table \"%s\".",col.getColumnName(), newTableRef.getTableName()));
			}
		}
		for(Column col : newColumns){
			for(Column col2 : newColumns){
				if(!(col.equals(col2)) && col.getColumnName().equals(col2.getColumnName())){
					throw new InvalidSchemaException(String.format("Cannot create two columns with the same name (%s).", col.getColumnName()));
				}
			}
		}
		this.newColumns = new HashSet<Column>(newColumns);
	}
	
	/**
	 * Returns the name of the table that is to be created.
	 * @return The {@code String} name of the table to be created.
	 */
	public String getTargetTableName(){
		return newTableRef.getTableName();
	}
	
	/**
	 * Returns the {@link TableReference} for the table that
	 * is to be created.
	 * @return The TableReference that the new table will have
	 * after its creation.
	 */
	public TableReference getTargetTableReference(){
		return newTableRef;
	}
	
	/**
	 * Returns a {@link Set} of the {@link Column} objects
	 * representing the columns that will be in the newly
	 * created table.
	 * @return A Set of the columns that will be in the
	 * new table.
	 */
	public Set<Column> getNewColumns(){
		return Collections.unmodifiableSet(newColumns);
	}
	
	/**
	 * Returns a {@link Set} of the {@link ColumnReference}
	 * objects for each of the {@link Column} objects
	 * that will be inserted into the new table.
	 * @return The ColumnReference objects for each of the
	 * columns that will be in the newly created table.
	 */
	public Set<ColumnReference> getNewColumnReferences(){
		Set<ColumnReference> newSet = new HashSet<ColumnReference>();
		for(Column col : newColumns){
			newSet.add(col.getReference());
		}
		return Collections.unmodifiableSet(newSet);
	}
	
	/**
	 * Returns a {@link Set} of the names of the
	 * {@link Column} objects that will be inseted
	 * into the new table.
	 * @return A Set of the {@code String} names
	 * for each of the columns that will be in the newly
	 * created table.
	 */
	public Set<String> getNewColumnNames(){
		Set<String> newSet = new HashSet<String>();
		for(Column col : newColumns){
			newSet.add(col.getColumnName());
		}
		return newSet;
	}
	
	/**
	 * Returns a hash code value for this TableCreationChange
	 * as defined in {@link Object#hashCode()}.
	 * @return A hash code value for this object.
	 */
	public int hashCode(){
		return newTableRef.hashCode() + newColumns.hashCode();
	}
	
	/**
	 * Determines whether or not some object is equal to this
	 * TableCreationChange.
	 * An {@code Object} is equal to this one if it is also a
	 * TableCreationChange, if its internal {@link TableReference}
	 * value (from {@link #getTargetTableReference()}) is equal
	 * to that of this object and if its {@link Set} of new
	 * {@link Column} objects (from {@link #getNewColumns()})
	 * is equal to that of this TableCreationChange.
	 * @param obj The object to be tested for equality against this one.
	 * @return {@code true} if the objects are equal as defined
	 * above, {@code false} otherwise.
	 */
	public boolean equals(Object obj){
		if(obj instanceof TableCreationChange){
			TableCreationChange tCChange = (TableCreationChange) obj;
			return tCChange.getTargetTableReference().equals(getTargetTableReference()) && tCChange.getNewColumns().equals(getNewColumns());
		}
		return false;
	}
}
