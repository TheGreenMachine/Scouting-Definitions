package com.edinarobotics.scouting.manager.event;

/**
 * This interface is used in {@link Event} objects that can be cancelled.
 * {@link Event}s implementing this interface can be cancelled by plugins.
 */
public interface Cancellable {
	
	/**
	 * Gets the cancellation state of this {@link Event}. A cancelled
	 * event will still pass through other event listeners but will
	 * not be executed by the system.
	 * @return {@code true} if the {@link Event} is cancelled, {@code false}
	 * otherwise.
	 * @see #setCancelled(boolean)
	 */
	public boolean isCancelled();
	
	/**
	 * Sets the cancellation state of this {@link Event} to {@code cancel}.
	 * @param cancel The new cancellation state of this event.
	 * @see #isCancelled()
	 */
	public void setCancelled(boolean cancel);
}
