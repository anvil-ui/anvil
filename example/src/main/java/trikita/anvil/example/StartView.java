package trikita.anvil.example;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.AbsListView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import trikita.anvil.Backstack;
import trikita.anvil.RenderableView;
import trikita.anvil.RenderableAdapter;
import static trikita.anvil.v10.Attrs.*;

//
// A screen with a big black button to start new task (or to resume the current
// one). Also contains a list of finished tasks (failed or completed).
//
// When scrolled, the list applies the parallax effect to the header.
//
public class StartView extends RenderableView {

	final static int MIN_TOP_HEIGHT = 120;
	final static int MAX_TOP_HEIGHT = 260;
	final static int BUTTON_SIZE = (int) (MIN_TOP_HEIGHT * 0.8);
	final static int BACKGROUND_COLOR = 0xff3f51b5;

	private Backstack mBackstack;

	private int mTopViewHeight = MAX_TOP_HEIGHT;

	private RenderableAdapter mTasksAdapter = new TasksAdapter();
	
	private AbsListView.OnScrollListener mScrollListener = new AbsListView.OnScrollListener() {
		public void onScroll(AbsListView v, int first, int count, int total) {
			int top = 0;
			if (v.getChildCount() > 0) {
				top = -v.getChildAt(0).getTop() + first * v.getChildAt(0).getHeight();
				if (top >= 0) {
					mTopViewHeight = Math.max((int) (MAX_TOP_HEIGHT -
								top/(getResources().getDisplayMetrics().density)/1.5f), MIN_TOP_HEIGHT);
				}
			}
		}
		public void onScrollStateChanged(AbsListView v, int state) {}
	};

	public StartView(Context c) {
		super(c);
		mBackstack = ((MainActivity) c).getBackstack();
	}

	@Override
	public ViewNode view() {
		return isPortrait() ? portraitView() : landscapeView();
	}

	private ViewNode landscapeView() {
		return
			v(LinearLayout.class,
					size(FILL, FILL),
					backgroundColor(BACKGROUND_COLOR),

					v(FrameLayout.class,
						size(FILL, FILL).weight(1),
						blackButton()),

					listView(false));
	}

	private ViewNode portraitView() {
		return
			v(FrameLayout.class,
					size(FILL, FILL),
					backgroundColor(BACKGROUND_COLOR),

					listView(true),

					v(FrameLayout.class,
						size(FILL, dip(mTopViewHeight)),
						blackButton()),

					 v(View.class,
						 size(FILL, dip(2)).margin(0, dip(mTopViewHeight), 0, 0),
						 visibility(mTopViewHeight < MAX_TOP_HEIGHT ? View.VISIBLE : View.GONE),
						 backgroundColor(0x20000000)));
	}

	private ViewNode blackButton() {
		return
			v(TextView.class,
					size(dip(BUTTON_SIZE), dip(BUTTON_SIZE)).gravity(CENTER),
					clickable(true),
					gravity(CENTER),
					backgroundResource(R.drawable.black_button),
					textColor(Color.WHITE),
					textSize(24),
					typeface("RobotoCondensed-Light.ttf"),
					onClick((v) -> mBackstack.navigate(new CountDownView(getContext()))),
					text(Tasks.getInstance().getCurrent() == null ? R.string.create : R.string.resume));
	}

	private ViewNode listView(boolean withMargin) {
		return 
			v(ListView.class,
					size(FILL, FILL).margin(0, (withMargin ? dip(mTopViewHeight) : 0), 0, 0).weight(1),
					adapter((ListAdapter) mTasksAdapter),
					divider(null),
					overScrollMode(ScrollView.OVER_SCROLL_NEVER),
					onItemClick((adapterView, v, pos, id) -> {
						Tasks.Task task = (Tasks.Task) mTasksAdapter.getItem(pos);
						// make it a current task
						mBackstack.navigate(new CountDownView(getContext()).withTask(task));
					}),
					onScroll(mScrollListener));
	}

	//
	// A list adapter that shows merged list of completed and failed tasks
	//
	private class TasksAdapter extends RenderableAdapter {
		private int completedSize;
		private int failedSize;

		@Override
		public int getCount() {
			completedSize = Tasks.getInstance().getCompleted().size();
			failedSize = Tasks.getInstance().getFailed().size();
			return completedSize + failedSize + (completedSize > 0 ? 1 : 0) + (failedSize > 0 ? 1 : 0);
		}

		@Override
		public Tasks.Task getItem(int pos) {
			int completedOffset = (completedSize > 0 ? 1 : 0);
			int failedOffset = completedOffset + (failedSize > 0 ? 1 : 0);
			if (pos == 0 && completedSize > 0) {
				return null;
			} else if (pos - completedOffset < completedSize) {
				return Tasks.getInstance().getCompleted().get(pos - 1);
			} else if (pos - completedOffset == completedSize) {
				return null;
			} else {
				return Tasks.getInstance().getFailed().get(pos - completedOffset - completedSize - 1);
			}
		}

		@Override
		public boolean isEnabled(int pos) {
			return getItem(pos) != null;
		}

		@Override
		public ViewNode itemView(int pos) {
			Tasks.Task task = getItem(pos);
			boolean isHeader = (task == null);
			String itemText;
			String itemDate = "";
			if (task == null) {
				if (pos == 0) {
					itemText = getContext().getString(R.string.list_group_completed);
				} else {
					itemText = getContext().getString(R.string.list_group_failed);
				}
			} else {
				itemText = task.getName();
				Calendar now = Calendar.getInstance();
				Calendar calendar = Calendar.getInstance();
				calendar.setTimeInMillis(task.getCompletionTime());
				if (calendar.get(Calendar.YEAR) != now.get(Calendar.YEAR)) {
					// If something happened in the previous years - show year, month and date
					itemDate = new SimpleDateFormat("MMM d, ''yy").format(calendar.getTime());
				} else if (calendar.get(Calendar.DAY_OF_YEAR) != now.get(Calendar.DAY_OF_YEAR)) {
					// For current year show only month and date
					itemDate = new SimpleDateFormat("MMM d").format(calendar.getTime());
				} else {
					// For today's tasks show only hours and minutes
					itemDate = new SimpleDateFormat("HH:mm").format(calendar.getTime());
				}
			}

			return
				v(LinearLayout.class,
						size(FILL, WRAP),
						v(TextView.class,
							size(WRAP, dip(48)).weight(1),
							gravity(CENTER_VERTICAL),
							typeface("RobotoCondensed-Bold.ttf"),
							padding(dip(8)),
							textSize(24),
							textColor(task == null ? Color.WHITE: 0x90ffffff),
							text(itemText)),
						v(TextView.class,
							size(WRAP, dip(48)).weight(0),
							gravity(CENTER_VERTICAL),
							typeface("RobotoCondensed-Bold.ttf"),
							padding(dip(8)),
							textSize(24),
							textColor(Color.WHITE),
							text(itemDate)));
		}
	};
}
