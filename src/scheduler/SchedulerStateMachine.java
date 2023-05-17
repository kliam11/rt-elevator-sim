package scheduler;

import other.SchedulerState;
import other.SchedulerState.Transition;

/**
 * 
 * A class for the scheduler's state machine
 */
public class SchedulerStateMachine {

	/* The current Elevator state */
	private SchedulerState state;

	/**
	 * Creates a new Elevator state machine
	 */
	public SchedulerStateMachine() {
		this.state = SchedulerState.IDLE;
	}

	/**
	 * Transition to the next state
	 */
	public void onNext(Transition transition) {
		this.state = this.state.next(transition);
	}

	/**
	 * Get the current state
	 */
	public SchedulerState getCurrentState() {
		return this.state;
	}
}