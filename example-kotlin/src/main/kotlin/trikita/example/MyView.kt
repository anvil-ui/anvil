package trikita.example;

import android.content.Context;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import trikita.anvil.RenderableView;

// Kotlin has a bug with importing static methods
// so we have to import whole hierarchy of classes instead
import trikita.anvil.Nodes.*;
import trikita.anvil.BaseAttrs.*;
import trikita.anvil.v10.Attrs.*;

class MyView(c: Context) : RenderableView(c) {

	var count = 0

	public override fun view() =
		v(javaClass<LinearLayout>(),
			size(FILL, FILL),
			orientation(LinearLayout.VERTICAL),

			v(javaClass<TextView>(),
				text("Count: " + count)),

			v(javaClass<Button>(),
				onClick({ count++ }),
				text("Click me")))
}

