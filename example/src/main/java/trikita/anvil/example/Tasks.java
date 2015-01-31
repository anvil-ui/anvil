package trikita.anvil.example;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Tasks {

	public static class Task implements Serializable {

		static final int NEVER = -1;

		private String name;
		private long duration;
		private long started = NEVER;
		private long stopped = NEVER;
		private long remainder;

		public String getName() {
			return name;
		}

		public long getDuration() {
			return this.duration;
		}

		public long getRemainder() {
			if (this.isRunning()) {
				return this.started + this.remainder - now();
			} else {
				return this.remainder;
			}
		}

		public long getCompletionTime() {
			return this.stopped;
		}

		public Task setName(String name) {
			this.name = name;
			return this;
		}

		public Task setDuration(long millis) {
			this.started = NEVER;
			this.stopped = NEVER;
			this.duration = millis;
			this.remainder = millis;
			return this;
		}

		public boolean isNew() {
			return this.started == NEVER && this.stopped == NEVER;
		}

		public boolean isRunning() {
			return this.started != NEVER && this.stopped == NEVER;
		}

		public boolean isPaused() {
			return this.started == NEVER && this.stopped != NEVER;
		}

		public boolean isFinished() {
			return this.started != NEVER && this.stopped != NEVER;
		}

		public Task start() {
			assert this.isPaused() || this.isNew();
			this.stopped = NEVER;
			this.started = now();
			Tasks.getInstance().current = this;
			return this;
		}

		public Task stop() {
			assert this == Tasks.getInstance().current;
			this.stopped = now();
			// todo stopped - started <= remainder
			if (this.stopped - this.started <= this.remainder) {
				Tasks.getInstance().completed.add(0, this);
			} else {
				Tasks.getInstance().failed.add(0, this);
			}
			Tasks.getInstance().current = null;
			this.remainder = 0;
			return this;
		}

		public Task pause() {
			assert this == Tasks.getInstance().current;
			this.remainder = this.started + this.remainder - now();
			this.started = NEVER;
			this.stopped = now();
			return this;
		}

		public void remove() {
			Tasks.getInstance().failed.remove(this);
			Tasks.getInstance().completed.remove(this);
			if (Tasks.getInstance().current == this) {
				Tasks.getInstance().current = null;
			}
		}
	}

	private static long now() {
		return System.currentTimeMillis();
	}

	private List<Task> failed = new ArrayList<>();
	private List<Task> completed = new ArrayList<>();
	private Task current;

	private Tasks() {}

	private static Tasks instance = null;

	public static Tasks getInstance() {
		if (instance == null) {
			instance = new Tasks();
		}
		return instance;
	}

	public Task create(String name, long duration) {
		Task t = new Task();
		t.setName(name);
		t.setDuration(duration);
		return t;
	}

	public Task getCurrent() {
		return current;
	}

	public List<Task> getCompleted() {
		return completed;
	}

	public List<Task> getFailed() {
		return failed;
	}

	public void save(Context c) {
		try {
			ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream
					(c.getFilesDir().getPath() + "/tasks"));
			os.writeObject(current);
			os.writeObject(completed);
			os.writeObject(failed);
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void load(Context c) {
		try {
			ObjectInputStream is = new ObjectInputStream(new FileInputStream
					(c.getFilesDir().getPath() + "/tasks"));
			current = (Task) is.readObject();
			completed = (List<Task>) is.readObject();
			failed = (List<Task>) is.readObject();
			is.close();
		} catch (ClassNotFoundException|IOException e) {
			e.printStackTrace();
		}
	}
}
