package trikita.anvil;

import android.os.Handler;
import android.os.Looper;

import java.util.ArrayDeque;

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
	 * state to the opposite one.
	 */
	public interface Listener {
		void onStateChanged(State state);
	}

	/**
	 * States can only be created from the factory. Anvil class provides the
	 * newState() method, thus behaving like a factory which runs Anvil.render()
	 * on each transition.
	 */
	public static class Factory {
		private final Listener listener;
		private final Handler handler = new Handler(Looper.getMainLooper());

		/** Create a new factory with the given listener */
		public Factory(Listener listener) {
			this.listener = listener;
		}

		/** Create a new state object with the given default state */
		public State newState(Object state) {
			return new State(this, state);
		}

		void post(Runnable r, long delay) {
			this.handler.postDelayed(r, delay);
		}

		void cancel(Runnable r) {
			this.handler.removeCallbacks(r);
		}

		void notifyListener(State state) {
			if (this.listener != null) {
				this.listener.onStateChanged(state);
			}
		}
	}

	/**
	 * This is an internal class representing each state transition.
	 * It contains a new state, transition duration and two optional callbacks
	 * (Runnables) to be executed before and after the transition.
	 */
	private static class StateChange {
		public final Object state;
		public final long delay;
		public final Runnable before;
		public final Runnable after;

		public StateChange(Object state, long delay, Runnable before, Runnable after) {
			this.state = state;
			this.delay = delay;
			this.before = before;
			this.after = after;
		}
	}

	/** Common state names to avoid user from writing new enums every time */
	public final static int ON = 1;
	public final static int OFF = 0;
	public final static int NONE = -1;

	private Object mState = OFF;
	private Object mPrevState = OFF;
	private Factory mFactory;
	private Runnable mDelayedRunnable;

	/** A queue of pending transitions */
	private ArrayDeque<StateChange> mQueue = new ArrayDeque<StateChange>();

	private State(Factory factory, Object state) {
		mFactory = factory;
		mState = state;
	}

	/** Enqueues a new immediate state transition to the given value */
	public State set(Object value) {
		return set(value, 0, null, null);
	}

	/** Enqueues a new state transition to the given value */
	public State set(Object value, long delay) {
		return set(value, delay, null, null);
	}

	/**
	 * Enqueues a new state transition to the given value with the callback to
	 * be executed after the transition.
	 */
	public State set(Object value, long delay, Runnable after) {
		return set(value, delay, null, after);
	}

	/**
	 * Enqueues a new state transition to the given value with two (optional)
	 * callbacks to be executed before and after the transition.
	 */
	public State set(Object value, long delay, Runnable before, Runnable after) {
		mQueue.offer(new StateChange(value, delay, before, after));
		update();
		return this;
	}

	/**
	 * Returns current state (State.ON, State.OFF or interim states
	 * State.TURNING_ON and State.TURNING_OFF)
	 */
	public Object get() {
		return mState;
	}

	/** Returns true when the state is in the given state or moving towards this state */
	public boolean is(Object state) {
		return get() == null ? state == null : get().equals(state);
	}

	/** Fetches the next transition from the queue and applies it */
	private void update() {
		if (mDelayedRunnable != null) {
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
			mState = change.state; // FIXME clear turning on flag
			mFactory.notifyListener(this);
			if (change.after != null) {
				change.after.run();
			}
			this.update();
		} else {
			if (change.before != null) {
				change.before.run();
			}
			mDelayedRunnable = new Runnable() {
				public void run() {
					mState = change.state;
					mFactory.notifyListener(State.this);
					mDelayedRunnable = null;
					if (change.after != null) {
						change.after.run();
					}
					update();
				}
			};

			mPrevState = mState;
			mState = change.state; // FIXME turning on flag
			mFactory.post(mDelayedRunnable, change.delay);
			mFactory.notifyListener(this);
		}
	}

	/**
	 * Forces all pending transitions to be completed immediately. Transition
	 * callbacks will be called anyway
	 */
	public State force() {
		if (mDelayedRunnable != null) {
			// Replace the queue, so that recursive update() calls would do nothing
			ArrayDeque<StateChange> pending = new ArrayDeque<>(mQueue);
			mQueue.clear();

			// Cancel the task, but apply the state change anyway
			mFactory.cancel(mDelayedRunnable);
			mDelayedRunnable.run();
			mDelayedRunnable = null;

			// For each pending transition - apply it immediately
			StateChange change;
			while ((change = pending.poll()) != null) {
				if (change.before != null) {
					change.before.run();
				}
				mState = change.state; // TODO clear turning on flag
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
		if (mDelayedRunnable != null) {
			// Cancel current task
			mState = mPrevState;
			mFactory.cancel(mDelayedRunnable);
			mDelayedRunnable = null;
			while (mQueue.poll() != null) {}
		}
		return this;
	}

	/**
	 * A default state factory. Calls Anvil.render() on every state change.
	 */
	private static State.Factory stateFactory =
		new State.Factory(new State.Listener() {
			public void onStateChanged(State state) {
				Anvil.render();
			}
		});

	/**
	 * A helper for making new State objects
	 */
	public static State init(Object state) {
		return stateFactory.newState(state);
	}
}
