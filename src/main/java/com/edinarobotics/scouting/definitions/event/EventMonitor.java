package com.edinarobotics.scouting.definitions.event;

import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.Retention;

/**
 * This annotation is used to mark class methods
 * that act as {@link Event} monitors.
 * The type of event to which this monitor is bound is determined
 * by reflection. It is the responsibility of the event firing
 * class to properly bind these monitors.<br/>
 * Event monitors receive both the fired Event object and
 * the {@link Result} of the action but have no say in whether
 * or not the event is cancelled. They are notified <em>after</em>
 * the action has finished.<br/>
 * Event monitor methods must accept only two parameters: an
 * Event subclass and a Result value.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface EventMonitor {

}