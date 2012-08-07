package com.edinarobotics.scouting.definitions.database.queries.constraints;

import com.edinarobotics.scouting.definitions.database.Row;
import com.edinarobotics.scouting.definitions.database.references.ColumnReference;
import com.edinarobotics.scouting.definitions.database.references.InvalidReferenceException;
import com.edinarobotics.scouting.definitions.database.references.TableReference;

/**
 * This class is the parent of all database
 * {@link com.edinarobotics.scouting.definitions.database.queries.Query Query}
 * constraints.
 * It represents some limiting factor on the results of a database query.
 */
public abstract class Constraint {
	private ColumnReference columnReference;
	
	/**
	 * This constructor is used by subclasses to set
	 * the {@link ColumnReference} in this Constraint.
	 * @param columnReference The ColumnReference for
	 * the column on which this Constraint operates.
	 */
	public Constraint(ColumnReference columnReference){
		this.columnReference = columnReference;
	}
	
	/**
	 * This constructor is used by subclasses to set
	 * the {@link ColumnReference} in this Constraint.
	 * @param columnReference The ColumnReference for
	 * the column on which this Constraint operates.
	 * @throws InvalidReferenceException If {@code columnReference}
	 * is not a valid column reference {@code String}.
	 * @see ColumnReference#ColumnReference(String)
	 */
	public Constraint(String columnReference) throws InvalidReferenceException{
		this(new ColumnReference(columnReference));
	}
	
	/**
	 * Returns the {@link ColumnReference} for the column
	 * on which this Constraint operates.
	 * @return The ColumnReference for the column on which
	 * this Constraint operates.
	 */
	public ColumnReference getColumnReference(){
		return columnReference;
	}
	
	/**
	 * Returns the column reference {@code String} for the column
	 * on which this Constraint operates.
	 * @return The column reference {@code String} for the column on which
	 * this Constraint operates.
	 */
	public String getColumnName(){
		return getColumnReference().getColumnName();
	}
	
	/**
	 * Returns the {@link TableReference} for the table on which
	 * this Constraint operates.
	 * @return The TableReference for the table on which this
	 * Constraint operates.
	 */
	public TableReference getTableReference(){
		return getColumnReference().getTableReference();
	}
	
	/**
	 * Returns the name of the table on which this
	 * Constraint operates.
	 * @return The {@code String} name of the table on
	 * which this Constraint operates.
	 */
	public String getTableName(){
		return getTableReference().getTableName();
	}
	
	/**
	 * Determines whether or not a {@link Row} satisfies the
	 * requirements of this Constraint.
	 * @param row The Row object to check against this Constraint.
	 * @return {@code true} if the Row satisfies this Constraint,
	 * {@code false} otherwise.
	 */
	public abstract boolean satisfiesConstraint(Row row);
}
