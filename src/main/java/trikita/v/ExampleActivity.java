package trikita.v;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import static trikita.v.Render.*;
import static trikita.anvil.v10.Props.*;

public class ExampleActivity extends Activity implements Renderable {

	public final static String tag = "ExampleActivity";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		render(this);
	}

	private String mText = "Hello";
	private int mNumClicks = 0;

	View.OnClickListener onButtonClicked = new View.OnClickListener() {
		public void onClick(View v) {
			Log.d(tag, "Button clicked!");
			mNumClicks++;
			mText = "Clicked " + mNumClicks;
		}
	};

	private int mProgress = 50;

	SeekBar.OnSeekBarChangeListener onSeek = new SeekBar.OnSeekBarChangeListener() {
		public void onStopTrackingTouch(SeekBar sb) {}
		public void onStartTrackingTouch(SeekBar sb) {}
		public void onProgressChanged(SeekBar sb, int progress, boolean fromUser) {
			Log.d(tag, "Seekbar changed");
			mProgress = progress;
		}
	};

	public View.OnClickListener setProgressTo(final int value) {
		return new View.OnClickListener() {
			public void onClick(View v) {
				mProgress = value;
			}
		};
	}

	private boolean mVisible = true;

	View.OnClickListener toggleVisibility = new View.OnClickListener() {
		public void onClick(View v) {
			Log.d(tag, "toggleVisibility clicked");
			mVisible = !mVisible;
		}
	};

	//
	//
	//

	public ViewGroup getRootView() {
		return (ViewGroup) getWindow().getDecorView().findViewById(android.R.id.content);
	}

	public Node view() {
		return
			v(LinearLayout.class,
					orientation(LinearLayout.VERTICAL),
					v(TextView.class,
						text("Click the button below:")),
					v(TextView.class,
						text(mText)),
					v(Button.class,
						text("Click me"),
						onClick(onButtonClicked)),
					v(TextView.class,
						text("Here's some seekbar:")),
					v(SeekBar.class,
						max(100),
						onSeekBarChange(onSeek),
						progress(mProgress)),
					v(Button.class,
						text("Set progress to 50%"),
						onClick(setProgressTo(50))),
					v(TextView.class,
						text("Toggle visibility:")),
					v(Button.class,
						text("Toggle"),
						onClick(toggleVisibility)),
					(mVisible ?
						v(TextView.class,
							text("Click button to hide this text")) : null),
					v(TextView.class,
						text("That's all!")));
	}
}
