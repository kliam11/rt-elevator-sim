package scheduler;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import other.SchedulerState;
import other.SchedulerState.Transition;

public class SchedulerStateMachineTest {
	
	@Test
	void schedulerStateTest() {
		//create a SchedulerStateMachine variable and test its state change
		SchedulerStateMachine ssm = new SchedulerStateMachine();
		assertTrue(ssm.getCurrentState() == SchedulerState.IDLE);
		
		ssm.onNext(Transition.RECEIVED_MESSAGE);
		assertTrue(ssm.getCurrentState() == SchedulerState.BUSY);
		
		ssm.onNext(Transition.FINISHED_SCHEDULING);
		assertTrue(ssm.getCurrentState() == SchedulerState.IDLE);
		
		ssm.onNext(Transition.FINISHED_SCHEDULING);
		assertTrue(ssm.getCurrentState() == SchedulerState.ILLEGAL);
	}
}
