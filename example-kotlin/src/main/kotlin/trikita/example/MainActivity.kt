package trikita.example;

import android.app.Activity;
import android.os.Bundle;

class MainActivity : Activity() {
	public override fun onCreate(b: Bundle?) {
		super.onCreate(b)
		setContentView(MySugarView(this))
	}
}

