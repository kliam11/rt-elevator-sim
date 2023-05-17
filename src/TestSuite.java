import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

import elevator.ElevatorTest;
import floor.FloorTest;
import other.MessageTest;
import other.MessengerTest;
import scheduler.SchedulerTest;

@Suite
@SelectClasses({ElevatorTest.class,MessageTest.class,MessengerTest.class,SchedulerTest.class})
class TestSuite {
}
