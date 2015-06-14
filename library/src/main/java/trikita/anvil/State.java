package trikita.anvil;

import java.util.TimerTask;
import java.util.ArrayDeque;
import java.util.Timer;

/**
 * State is a micro FSM (finite state machine) with only two states - on and off.
 * It's mostly used to build animations, but can also be used as a construction
 * block for other more complex FSMs.
 *
 * All state objects can be in either on or off state, or in the transition
 * from one to another. Transitions are managed by the State.Factory which
 * allows to perform arbitrary actions when the transition is finished (like
 * calling Anvil.render).
 *
 * Anvil class provides one global State factory that does Anvil.render() on
 * each state transition.
 */
public class State {

	/**
	 * Factory listener. onStateChanged() callback is called when any of the
	 * created states produced by the factory finishes the transition from one
	 * state to the opporite one.
	 */
	public interface Listener {
		public void onStateChanged(State state);
	}

	/**
	 * States can only be created from the factory. Anvil class provides the
	 * newState() method, thus behaving like a factory which runs Anvil.render()
	 * on each transition.
	 */
	public static class Factory {
		private Listener mListener;
		private Timer mTimer = new Timer();

		/** Create a new factory with the given listener */
		public Factory(Listener listener) {
			mListener = listener;
		}

		/** Create a new state object with the given default state */
		public State newState(boolean on) {
			return new State(this, on ? ON : OFF);
		}

		void post(TimerTask task, long delay) {
			mTimer.schedule(task, delay);
		}

		void notifyListener(State state) {
			if (mListener != null) {
				mListener.onStateChanged(state);
			}
		}
	}

	/**
	 * This is an internal class representing each state transition.
	 * It contains a new state, transition duration and two optional callbacks
	 * (Runnables) to be executed before and after the transition.
	 */
	private static class StateChange {
		public final boolean state;
		public final long delay;
		public final Runnable before;
		public final Runnable after;

		public StateChange(boolean state, long delay, Runnable before, Runnable after) {
			this.state = state;
			this.delay = delay;
			this.before = before;
			this.after = after;
		}
	}

	/** State is off */
	public final static int OFF = 0;
	/** State is on */
	public final static int ON = 1;
	/** State is transitioning from off to on */
	public final static int TURNING_ON = 2;
	/** State is transitioning from on to off */
	public final static int TURNING_OFF = 3;

	private int mState = OFF;
	private Factory mFactory;
	private TimerTask mTimerTask;
	/** A queue of pending transitions */
	private ArrayDeque<StateChange> mQueue = new ArrayDeque<StateChange>();

	private State(Factory factory, int state) {
		mFactory = factory;
		mState = state;
	}

	/** Enqueues a new immediate state transition to the given value */
	public State set(boolean value) {
		return set(value, 0, null, null);
	}

	/** Enqueues a new state transition to the given value */
	public State set(boolean value, long delay) {
		return set(value, delay, null, null);
	}

	/**
	 * Enqueues a new state transition to the given value with the callback to
	 * be executed before the transition.
	 */
	public State set(boolean value, long delay, Runnable before) {
		return set(value, delay, before, null);
	}

	/**
	 * Enqueues a new state transition to the given value with two (optional)
	 * callbacks to be executed before and after the transition.
	 */
	public State set(boolean value, long delay, Runnable before, Runnable after) {
		mQueue.offer(new StateChange(value, delay, before, after));
		update();
		return this;
	}

	/**
	 * Returns current state (State.ON, State.OFF or interim states
	 * State.TURNING_ON and State.TURNING_OFF)
	 */
	public int get() {
		return mState;
	}

	/** Returns true when the state is on or turning on */
	public boolean isOn() {
		return get() == ON || get() == TURNING_ON;
	}

	/** Returns true when the state is off or turning off */
	public boolean isOff() {
		return !isOn();
	}

	/** Fetches the next transition from the queue and applies it */
	private void update() {
		if (mTimerTask != null) {
			return;
		}
		final StateChange change = mQueue.poll();
		if (change == null) {
			return;
		}
		if (change.delay == 0) {
			if (change.before != null) {
				change.before.run();
			}
			mState = change.state ? ON : OFF;
			mFactory.notifyListener(this);
			if (change.after != null) {
				change.after.run();
			}
			this.update();
		} else {
			if (change.before != null) {
				change.before.run();
			}
			mTimerTask = new TimerTask() {
				private int mPrevState = mState;

				public void run() {
					mState = change.state ? ON : OFF;
					mFactory.notifyListener(State.this);
					mTimerTask = null;
					if (change.after != null) {
						change.after.run();
					}
					update();
				}

				public boolean cancel() {
					mState = mPrevState;
					return super.cancel();
				}
			};
			mState = change.state ? TURNING_ON : TURNING_OFF;
			mFactory.post(mTimerTask, change.delay);
		}
	}

	/**
	 * Forces all pending transitions to be completed immediately. Transition
	 * callbacks will be called anyway
	 */
	public State force() {
		if (mTimerTask != null) {
			// Replace the queue, so that recursive update() calls would do nothing
			ArrayDeque<StateChange> pending = new ArrayDeque<>(mQueue);
			mQueue.clear();

			// Cancel the task, but apply the state change anyway
			mTimerTask.cancel();
			mTimerTask.run();

			// For each pending transition - apply it immediatelly
			StateChange change;
			while ((change = pending.poll()) != null) {
				if (change.before != null) {
					change.before.run();
				}
				mState = change.state ? ON : OFF;
				mFactory.notifyListener(this);
				if (change.after != null) {
					change.after.run();
				}
			}
		}
		return this;
	}

	/**
	 * Cancels all pending transitions immediately. Transition callbacks will not
	 * be called.
	 */
	public State cancel() {
		if (mTimerTask != null) {
			// Cancel current task
			mTimerTask.cancel();
			while (mQueue.poll() != null);
		}
		return this;
	}
}
