package com.edinarobotics.scouting.definitions.database.changes;

import com.edinarobotics.scouting.definitions.database.references.InvalidReferenceException;
import com.edinarobotics.scouting.definitions.database.references.TableReference;

/**
 * This {@link Change} is used to delete an entire
 * {@link com.edinarobotics.scouting.definitions.database.Database Database}
 * table.
 */
public final class TableDeletionChange implements Change{
	private TableReference targetTable;
	
	/**
	 * Constructs a new TableDeletionChange targeting the table
	 * referenced by {@code targetTable}.
	 * @param targetTable The {@link TableReference} targeting
	 * the table to be deleted.
	 */
	public TableDeletionChange(TableReference targetTable){
		this.targetTable = targetTable;
	}
	
	/**
	 * Constructs a new TableDeletionChange targeting the table
	 * referenced by {@code targetTableReference}.
	 * @param targetTableReference The {@code String} table
	 * reference string pointing to the table to be deleted.
	 * @throws InvalidReferenceException If {@code targetTableReference}
	 * cannot be parsed by {@link TableReference}.
	 */
	public TableDeletionChange(String targetTableReference) throws InvalidReferenceException{
		this(new TableReference(targetTableReference));
	}
	
	/**
	 * Returns the {@link TableReference} of the table targeted
	 * by this TableDeletionChange.
	 * @return The TableReference of the targeted table.
	 */
	public TableReference getTargetTable(){
		return targetTable;
	}
	
	/**
	 * Returns the {@code String} name of the table targeted
	 * by this TableDeletionChange.
	 * @return The name of the table that is targeted for
	 * deletion.
	 */
	public String getTargetTableName(){
		return getTargetTable().getTableName();
	}
	
	/**
	 * Returns a hash code value for this TableDeletionChange
	 * as defined in {@link Object#hashCode()}.
	 * @return A hash code value for this object.
	 */
	public int hashCode(){
		return targetTable.hashCode();
	}
	
	/**
	 * Determines whether or not some object is equal to
	 * this TableDeletionChange.
	 * An object is equal to this one if it is also a
	 * TableDeletionChange and if its
	 * {@link #getTargetTable()} method returns a
	 * {@link TableReference} that is equal to this
	 * object's internal TableReference.
	 * @param obj The object to be tested for equality against this one.
	 * @return {@code true} if {@code obj} is equal
	 * to this TableDeletionChange as defined above,
	 * {@code false} otherwise.
	 */
	public boolean equals(Object obj){
		if(obj instanceof TableDeletionChange){
			return ((TableDeletionChange) obj).getTargetTable().equals(getTargetTable());
		}
		return false;
	}
}
