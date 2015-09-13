package trikita.anvil;

public class B {

	private int count = 0;
	private long start;
	private long end;
	private long timeout;
	private String label;

	public B(String label, long timeout) {
		this.label = label;
		this.timeout = timeout;
	}

	public B(String label) {
		this(label, 1000000000L);
	}

	public boolean done() {
		long t = System.nanoTime();
		if (this.count == 0) {
			this.start = this.end = t;
		} else {
			long d = t - this.end;
		}
		this.end = t;
		this.count++;
		return this.end - this.start >= this.timeout || count >= 1000000;
	}

	public String report() {
		return "Benchmark " + this.label + "\t\t" + this.ops() + "\t" + (this.op() / 1000) + "us/op";
	}

	public int ops() {
		return this.count - 1;
	}

	public double op() {
		return this.time()/this.ops();
	}

	public long time() {
		return (this.end - this.start);
	}
}
