package trikita.example;

import android.content.Context;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import trikita.anvil.RenderableView;

import trikita.anvil.RenderableView;
import trikita.anvil.BaseAttrs.*;
import trikita.anvil.v10.Attrs.*;

class MySugarView(c: Context) : RenderableView(c) {

	var count = 0

	public override fun view() =
		v<LinearLayout> {
			- size(FILL, FILL)
			- orientation(LinearLayout.VERTICAL)

			v<TextView> {
				- text("Clicked: " + count)
			}
			v<Button> {
				- text("Click me")
				- onClick {
					println("Button clicked")
					count++
				}
			}
		}
}

