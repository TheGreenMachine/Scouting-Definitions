package com.edinarobotics.scouting.definitions.database.changes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Transaction objects are the basic method of applying {@link Change}
 * objects to a
 * {@link com.edinarobotics.scouting.definitions.database.Database Database}.
 * Transactions are sets of Change objects that are executed sequentially
 * and atomically. Either all change succeed or none of them are applied.
 * The atomic nature of transactions is the responsibility of the Database
 * implementation.
 */
public class Transaction {
	protected List<Change> changes;
	
	/**
	 * Constructs a new, empty Transaction. By default this
	 * transaction contains no {@link Change} objects and will do
	 * nothing if applied to the
	 * {@link com.edinarobotics.scouting.definitions.database.Database Database}.
	 */
	public Transaction(){
		this.changes = new ArrayList<Change>();
	}
	
	/**
	 * Constructs a new Transaction containing all elements
	 * of {@code changes} in the order they are returned
	 * by the {@link Collection}'s iterator.
	 * @param changes The Collection of {@link Change} objects
	 * to be added to this Transaction.
	 * @see ArrayList#ArrayList(Collection)
	 */
	public Transaction(Collection<Change> changes){
		this.changes = new ArrayList<Change>(changes);
	}
	
	/**
	 * Appends a {@link Change} to the end of this
	 * Transaction.
	 * @param change The Change to be appended to
	 * this Transaction.
	 * @see List#add(Object)
	 */
	public void add(Change change){
		getChanges().add(change);
	}
	
	/**
	 * Removes a {@link Change} from this Transaction.
	 * This method removes only the first occurrence of
	 * {@code Change}.
	 * @param change The Change to be removed from
	 * this list, if present.
	 * @see List#remove(Object)
	 */
	public void remove(Change change){
		getChanges().remove(change);
	}
	
	/**
	 * Returns the number of {@link Change} objects in this Transaction.
	 * If this value is greater than {@link Integer#MAX_VALUE},
	 * returns {@code Integer.MAX_VALUE}.
	 * @return The number of Change objects in this Transaction.
	 * @see List#size()
	 */
	public int size(){
		return getChanges().size();
	}
	
	/**
	 * Returns the {@link List} used internally to store
	 * the {@link Transaction} objects. This List cannot
	 * be modified.
	 * @return A List object containing all
	 * {@link Change} objects in this Transaction.
	 */
	public List<Change> getChanges(){
		return Collections.unmodifiableList(changes);
	}
	
	/**
	 * Returns a hash code value for this Transaction as
	 * defined in {@link Object#hashCode()}.
	 * @return A hash code value for this object.
	 * @see List#hashCode()
	 */
	public int hashCode(){
		return getChanges().hashCode();
	}
	
	/**
	 * Determines whether or not some object is equal
	 * to this Transaction.
	 * An {@code Object} is equal to this one if it is
	 * also a Transaction and if the other Transaction's
	 * internal {@link List} is equal to this Transaction's
	 * internal List (from {@link #getChanges()}).
	 * @param obj The object to be tested for equality against this one.
	 * @return {@code true} if the objects are equal as defined above,
	 * {@code false} otherwise.
	 */
	public boolean equals(Object obj){
		if(obj instanceof Transaction){
			return ((Transaction) obj).getChanges().equals(getChanges());
		}
		return false;
	}
}
