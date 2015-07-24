package trikita.anvil;

import android.test.AndroidTestCase;
import static org.junit.Assert.*;
import org.junit.Test;
import trikita.anvil.State;
import java.util.ArrayDeque;

public class StateTest extends AndroidTestCase {

	// Check that state can be toggled and that getters match the value
	public void testStateSync() {
		State a = Anvil.newState(true);
		State b = Anvil.newState(false);

		assertEquals(a.get(), State.ON);
		assertEquals(b.get(), State.OFF);

		assertTrue(a.isOn());
		assertFalse(a.isOff());
		assertTrue(b.isOff());
		assertFalse(b.isOn());

		a.set(true);
		b.set(true);

		assertTrue(a.isOn());
		assertTrue(b.isOn());

		a.set(false);
		b.set(true);

		assertFalse(a.isOn());
		assertTrue(b.isOn());
	}

	// Check that state can be toggled and that factory callback is called
	public void testStateAsync() {
		final int called[] = {0};
		State.Factory mFactory = new State.Factory(new State.Listener() {
			public void onStateChanged(State state) {
				assertEquals(state.get(), State.OFF);
				called[0] = 1;
			}
		});

		State a = mFactory.newState(true);
		a.set(false);
		assertEquals(called[0], 1);
	}

	public void testStateWithDelays() {
		// Check that state can be toggled with a delay and that it enters the
		// intermediate state
		State a = Anvil.newState(false);
		a.set(true, 100);
		assertEquals(a.get(), State.TURNING_ON);
		assertTrue(a.isOn());
		try { Thread.sleep(150); } catch (Exception e) {}
		assertEquals(a.get(), State.ON);
	}

	public void testStateCancel() {
		// Check that enqueued state transitions can be forced to finish
		State a = Anvil.newState(false);
		a.set(true, 10000)
			.set(false, 10000)
			.set(true, 10000);
		assertEquals(a.get(), State.TURNING_ON);

		a.cancel();
		assertEquals(a.get(), State.OFF);

		// Check that cancellation restores the previous state, even if 
		// it was the same as the new one
		a = Anvil.newState(true);
		a.set(true, 10000)
			.set(false, 10000);
		assertEquals(a.get(), State.TURNING_ON);

		a.cancel();
		assertEquals(a.get(), State.ON);
	}

	public void testStateForce() {
		// Check that enqueued state transitions can be forced to finish
		// and that callbacks are called
		
		State a = Anvil.newState(false);
		a.set(true, 10000)
			.set(false, 10000)
			.set(false, 10000)
			.set(true, 10000);
		a.force();
		assertEquals(a.get(), State.ON);

		final int count[] = {0, 0, 0, 0, 0, 0, 0};
		State.Factory factory = new State.Factory(new State.Listener() {
			public void onStateChanged(State s) {
				System.out.println("called with state " + s.get());
				count[0] = count[0] + 1;
				count[count[0]] = s.get();
			}
		});
		a = factory.newState(true);
		a.set(true, 10000)
			.set(false, 10000)
			.set(true, 10000)
			.set(true, 10000)
			.set(false, 10000);
		a.force();
		assertEquals(a.get(), State.OFF);

		assertEquals(count[0], 6); // callback fired 6 times with one transition state
		assertEquals(count[1], State.TURNING_ON);
		assertEquals(count[2], State.ON);
		assertEquals(count[3], State.OFF);
		assertEquals(count[4], State.ON);
		assertEquals(count[5], State.ON);
		assertEquals(count[6], State.OFF);
	}

	public void testStateRender() {
		// Check that state update calls Anvil.render()
	}
}
