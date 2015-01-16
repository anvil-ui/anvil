package trikita.anvil.v4;

import android.view.View;

import static trikita.v.Render.*;

public class Props {
  public static Node onInflate(final android.view.ViewStub.OnInflateListener listener) {
    return new Node(new SimpleSetter(listener) {
      public void set(View v) {
        if (v instanceof android.view.ViewStub)
          ((android.view.ViewStub) v).setOnInflateListener(new android.view.ViewStub.OnInflateListener() {
            public void onInflate(android.view.ViewStub a0, android.view.View a1) {
              listener.onInflate(a0, a1);
              render();
            }
        });
      }
    });
  }
  public static Node onChildClick(final android.widget.ExpandableListView.OnChildClickListener listener) {
    return new Node(new SimpleSetter(listener) {
      public void set(View v) {
        if (v instanceof android.widget.ExpandableListView)
          ((android.widget.ExpandableListView) v).setOnChildClickListener(new android.widget.ExpandableListView.OnChildClickListener() {
            public boolean onChildClick(android.widget.ExpandableListView a0, android.view.View a1, int a2, int a3, long a4) {
              boolean r = listener.onChildClick(a0, a1, a2, a3, a4);
              render();
              return r;
            }
        });
      }
    });
  }
  public static Node onZoomOutClick(final android.view.View.OnClickListener listener) {
    return new Node(new SimpleSetter(listener) {
      public void set(View v) {
        if (v instanceof android.widget.ZoomControls)
          ((android.widget.ZoomControls) v).setOnZoomOutClickListener(new android.view.View.OnClickListener() {
            public void onClick(android.view.View a0) {
              listener.onClick(a0);
              render();
            }
        });
      }
    });
  }
  public static Node onCompletion(final android.media.MediaPlayer.OnCompletionListener listener) {
    return new Node(new SimpleSetter(listener) {
      public void set(View v) {
        if (v instanceof android.widget.VideoView)
          ((android.widget.VideoView) v).setOnCompletionListener(new android.media.MediaPlayer.OnCompletionListener() {
            public void onCompletion(android.media.MediaPlayer a0) {
              listener.onCompletion(a0);
              render();
            }
        });
      }
    });
  }
  public static Node onDrawerScroll(final android.widget.SlidingDrawer.OnDrawerScrollListener listener) {
    return new Node(new SimpleSetter(listener) {
      public void set(View v) {
        if (v instanceof android.widget.SlidingDrawer)
          ((android.widget.SlidingDrawer) v).setOnDrawerScrollListener(new android.widget.SlidingDrawer.OnDrawerScrollListener() {
            public void onScrollStarted() {
              listener.onScrollStarted();
              render();
            }
            public void onScrollEnded() {
              listener.onScrollEnded();
              render();
            }
        });
      }
    });
  }
  public static Node onLongClick(final android.view.View.OnLongClickListener listener) {
    return new Node(new SimpleSetter(listener) {
      public void set(View v) {
        if (v instanceof android.view.View)
          ((android.view.View) v).setOnLongClickListener(new android.view.View.OnLongClickListener() {
            public boolean onLongClick(android.view.View a0) {
              boolean r = listener.onLongClick(a0);
              render();
              return r;
            }
        });
      }
    });
  }
  public static Node onClick(final android.view.View.OnClickListener listener) {
    return new Node(new SimpleSetter(listener) {
      public void set(View v) {
        if (v instanceof android.view.View)
          ((android.view.View) v).setOnClickListener(new android.view.View.OnClickListener() {
            public void onClick(android.view.View a0) {
              listener.onClick(a0);
              render();
            }
        });
        if (v instanceof android.widget.AdapterView)
          ((android.widget.AdapterView) v).setOnClickListener(new android.view.View.OnClickListener() {
            public void onClick(android.view.View a0) {
              listener.onClick(a0);
              render();
            }
        });
        if (v instanceof android.widget.AutoCompleteTextView)
          ((android.widget.AutoCompleteTextView) v).setOnClickListener(new android.view.View.OnClickListener() {
            public void onClick(android.view.View a0) {
              listener.onClick(a0);
              render();
            }
        });
      }
    });
  }
  public static Node onTabChanged(final android.widget.TabHost.OnTabChangeListener listener) {
    return new Node(new SimpleSetter(listener) {
      public void set(View v) {
        if (v instanceof android.widget.TabHost)
          ((android.widget.TabHost) v).setOnTabChangedListener(new android.widget.TabHost.OnTabChangeListener() {
            public void onTabChanged(java.lang.String a0) {
              listener.onTabChanged(a0);
              render();
            }
        });
      }
    });
  }
  public static Node onItemClick(final android.widget.AdapterView.OnItemClickListener listener) {
    return new Node(new SimpleSetter(listener) {
      public void set(View v) {
        if (v instanceof android.widget.AdapterView)
          ((android.widget.AdapterView) v).setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
            public void onItemClick(android.widget.AdapterView a0, android.view.View a1, int a2, long a3) {
              listener.onItemClick(a0, a1, a2, a3);
              render();
            }
        });
        if (v instanceof android.widget.AutoCompleteTextView)
          ((android.widget.AutoCompleteTextView) v).setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
            public void onItemClick(android.widget.AdapterView a0, android.view.View a1, int a2, long a3) {
              listener.onItemClick(a0, a1, a2, a3);
              render();
            }
        });
        if (v instanceof android.widget.ExpandableListView)
          ((android.widget.ExpandableListView) v).setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
            public void onItemClick(android.widget.AdapterView a0, android.view.View a1, int a2, long a3) {
              listener.onItemClick(a0, a1, a2, a3);
              render();
            }
        });
        if (v instanceof android.widget.Spinner)
          ((android.widget.Spinner) v).setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
            public void onItemClick(android.widget.AdapterView a0, android.view.View a1, int a2, long a3) {
              listener.onItemClick(a0, a1, a2, a3);
              render();
            }
        });
      }
    });
  }
  public static Node onCheckedChange(final android.widget.RadioGroup.OnCheckedChangeListener listener) {
    return new Node(new SimpleSetter(listener) {
      public void set(View v) {
        if (v instanceof android.widget.RadioGroup)
          ((android.widget.RadioGroup) v).setOnCheckedChangeListener(new android.widget.RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(android.widget.RadioGroup a0, int a1) {
              listener.onCheckedChanged(a0, a1);
              render();
            }
        });
      }
    });
  }
  public static Node onError(final android.media.MediaPlayer.OnErrorListener listener) {
    return new Node(new SimpleSetter(listener) {
      public void set(View v) {
        if (v instanceof android.widget.VideoView)
          ((android.widget.VideoView) v).setOnErrorListener(new android.media.MediaPlayer.OnErrorListener() {
            public boolean onError(android.media.MediaPlayer a0, int a1, int a2) {
              boolean r = listener.onError(a0, a1, a2);
              render();
              return r;
            }
        });
      }
    });
  }
  public static Node onGroupClick(final android.widget.ExpandableListView.OnGroupClickListener listener) {
    return new Node(new SimpleSetter(listener) {
      public void set(View v) {
        if (v instanceof android.widget.ExpandableListView)
          ((android.widget.ExpandableListView) v).setOnGroupClickListener(new android.widget.ExpandableListView.OnGroupClickListener() {
            public boolean onGroupClick(android.widget.ExpandableListView a0, android.view.View a1, int a2, long a3) {
              boolean r = listener.onGroupClick(a0, a1, a2, a3);
              render();
              return r;
            }
        });
      }
    });
  }
  public static Node onDrawerOpen(final android.widget.SlidingDrawer.OnDrawerOpenListener listener) {
    return new Node(new SimpleSetter(listener) {
      public void set(View v) {
        if (v instanceof android.widget.SlidingDrawer)
          ((android.widget.SlidingDrawer) v).setOnDrawerOpenListener(new android.widget.SlidingDrawer.OnDrawerOpenListener() {
            public void onDrawerOpened() {
              listener.onDrawerOpened();
              render();
            }
        });
      }
    });
  }
  public static Node onItemSelected(final android.widget.AdapterView.OnItemSelectedListener listener) {
    return new Node(new SimpleSetter(listener) {
      public void set(View v) {
        if (v instanceof android.widget.AdapterView)
          ((android.widget.AdapterView) v).setOnItemSelectedListener(new android.widget.AdapterView.OnItemSelectedListener() {
            public void onNothingSelected(android.widget.AdapterView a0) {
              listener.onNothingSelected(a0);
              render();
            }
            public void onItemSelected(android.widget.AdapterView a0, android.view.View a1, int a2, long a3) {
              listener.onItemSelected(a0, a1, a2, a3);
              render();
            }
        });
        if (v instanceof android.widget.AutoCompleteTextView)
          ((android.widget.AutoCompleteTextView) v).setOnItemSelectedListener(new android.widget.AdapterView.OnItemSelectedListener() {
            public void onNothingSelected(android.widget.AdapterView a0) {
              listener.onNothingSelected(a0);
              render();
            }
            public void onItemSelected(android.widget.AdapterView a0, android.view.View a1, int a2, long a3) {
              listener.onItemSelected(a0, a1, a2, a3);
              render();
            }
        });
      }
    });
  }
  public static Node onGroupCollapse(final android.widget.ExpandableListView.OnGroupCollapseListener listener) {
    return new Node(new SimpleSetter(listener) {
      public void set(View v) {
        if (v instanceof android.widget.ExpandableListView)
          ((android.widget.ExpandableListView) v).setOnGroupCollapseListener(new android.widget.ExpandableListView.OnGroupCollapseListener() {
            public void onGroupCollapse(int a0) {
              listener.onGroupCollapse(a0);
              render();
            }
        });
      }
    });
  }
  public static Node onPrepared(final android.media.MediaPlayer.OnPreparedListener listener) {
    return new Node(new SimpleSetter(listener) {
      public void set(View v) {
        if (v instanceof android.widget.VideoView)
          ((android.widget.VideoView) v).setOnPreparedListener(new android.media.MediaPlayer.OnPreparedListener() {
            public void onPrepared(android.media.MediaPlayer a0) {
              listener.onPrepared(a0);
              render();
            }
        });
      }
    });
  }
  public static Node onKey(final android.view.View.OnKeyListener listener) {
    return new Node(new SimpleSetter(listener) {
      public void set(View v) {
        if (v instanceof android.view.View)
          ((android.view.View) v).setOnKeyListener(new android.view.View.OnKeyListener() {
            public boolean onKey(android.view.View a0, int a1, android.view.KeyEvent a2) {
              boolean r = listener.onKey(a0, a1, a2);
              render();
              return r;
            }
        });
      }
    });
  }
  public static Node onFocusChange(final android.view.View.OnFocusChangeListener listener) {
    return new Node(new SimpleSetter(listener) {
      public void set(View v) {
        if (v instanceof android.view.View)
          ((android.view.View) v).setOnFocusChangeListener(new android.view.View.OnFocusChangeListener() {
            public void onFocusChange(android.view.View a0, boolean a1) {
              listener.onFocusChange(a0, a1);
              render();
            }
        });
      }
    });
  }
  public static Node onGroupExpand(final android.widget.ExpandableListView.OnGroupExpandListener listener) {
    return new Node(new SimpleSetter(listener) {
      public void set(View v) {
        if (v instanceof android.widget.ExpandableListView)
          ((android.widget.ExpandableListView) v).setOnGroupExpandListener(new android.widget.ExpandableListView.OnGroupExpandListener() {
            public void onGroupExpand(int a0) {
              listener.onGroupExpand(a0);
              render();
            }
        });
      }
    });
  }
  public static Node onDrawerClose(final android.widget.SlidingDrawer.OnDrawerCloseListener listener) {
    return new Node(new SimpleSetter(listener) {
      public void set(View v) {
        if (v instanceof android.widget.SlidingDrawer)
          ((android.widget.SlidingDrawer) v).setOnDrawerCloseListener(new android.widget.SlidingDrawer.OnDrawerCloseListener() {
            public void onDrawerClosed() {
              listener.onDrawerClosed();
              render();
            }
        });
      }
    });
  }
  public static Node onHierarchyChange(final android.view.ViewGroup.OnHierarchyChangeListener listener) {
    return new Node(new SimpleSetter(listener) {
      public void set(View v) {
        if (v instanceof android.view.ViewGroup)
          ((android.view.ViewGroup) v).setOnHierarchyChangeListener(new android.view.ViewGroup.OnHierarchyChangeListener() {
            public void onChildViewAdded(android.view.View a0, android.view.View a1) {
              listener.onChildViewAdded(a0, a1);
              render();
            }
            public void onChildViewRemoved(android.view.View a0, android.view.View a1) {
              listener.onChildViewRemoved(a0, a1);
              render();
            }
        });
        if (v instanceof android.widget.TableRow)
          ((android.widget.TableRow) v).setOnHierarchyChangeListener(new android.view.ViewGroup.OnHierarchyChangeListener() {
            public void onChildViewAdded(android.view.View a0, android.view.View a1) {
              listener.onChildViewAdded(a0, a1);
              render();
            }
            public void onChildViewRemoved(android.view.View a0, android.view.View a1) {
              listener.onChildViewRemoved(a0, a1);
              render();
            }
        });
        if (v instanceof android.widget.TableLayout)
          ((android.widget.TableLayout) v).setOnHierarchyChangeListener(new android.view.ViewGroup.OnHierarchyChangeListener() {
            public void onChildViewAdded(android.view.View a0, android.view.View a1) {
              listener.onChildViewAdded(a0, a1);
              render();
            }
            public void onChildViewRemoved(android.view.View a0, android.view.View a1) {
              listener.onChildViewRemoved(a0, a1);
              render();
            }
        });
        if (v instanceof android.widget.RadioGroup)
          ((android.widget.RadioGroup) v).setOnHierarchyChangeListener(new android.view.ViewGroup.OnHierarchyChangeListener() {
            public void onChildViewAdded(android.view.View a0, android.view.View a1) {
              listener.onChildViewAdded(a0, a1);
              render();
            }
            public void onChildViewRemoved(android.view.View a0, android.view.View a1) {
              listener.onChildViewRemoved(a0, a1);
              render();
            }
        });
      }
    });
  }
  public static Node onChronometerTick(final android.widget.Chronometer.OnChronometerTickListener listener) {
    return new Node(new SimpleSetter(listener) {
      public void set(View v) {
        if (v instanceof android.widget.Chronometer)
          ((android.widget.Chronometer) v).setOnChronometerTickListener(new android.widget.Chronometer.OnChronometerTickListener() {
            public void onChronometerTick(android.widget.Chronometer a0) {
              listener.onChronometerTick(a0);
              render();
            }
        });
      }
    });
  }
  public static Node onTouch(final android.view.View.OnTouchListener listener) {
    return new Node(new SimpleSetter(listener) {
      public void set(View v) {
        if (v instanceof android.view.View)
          ((android.view.View) v).setOnTouchListener(new android.view.View.OnTouchListener() {
            public boolean onTouch(android.view.View a0, android.view.MotionEvent a1) {
              boolean r = listener.onTouch(a0, a1);
              render();
              return r;
            }
        });
      }
    });
  }
  public static Node onEditorAction(final android.widget.TextView.OnEditorActionListener listener) {
    return new Node(new SimpleSetter(listener) {
      public void set(View v) {
        if (v instanceof android.widget.TextView)
          ((android.widget.TextView) v).setOnEditorActionListener(new android.widget.TextView.OnEditorActionListener() {
            public boolean onEditorAction(android.widget.TextView a0, int a1, android.view.KeyEvent a2) {
              boolean r = listener.onEditorAction(a0, a1, a2);
              render();
              return r;
            }
        });
      }
    });
  }
  public static Node onTimeChanged(final android.widget.TimePicker.OnTimeChangedListener listener) {
    return new Node(new SimpleSetter(listener) {
      public void set(View v) {
        if (v instanceof android.widget.TimePicker)
          ((android.widget.TimePicker) v).setOnTimeChangedListener(new android.widget.TimePicker.OnTimeChangedListener() {
            public void onTimeChanged(android.widget.TimePicker a0, int a1, int a2) {
              listener.onTimeChanged(a0, a1, a2);
              render();
            }
        });
      }
    });
  }
  public static Node onSeekBarChange(final android.widget.SeekBar.OnSeekBarChangeListener listener) {
    return new Node(new SimpleSetter(listener) {
      public void set(View v) {
        if (v instanceof android.widget.SeekBar)
          ((android.widget.SeekBar) v).setOnSeekBarChangeListener(new android.widget.SeekBar.OnSeekBarChangeListener() {
            public void onStartTrackingTouch(android.widget.SeekBar a0) {
              listener.onStartTrackingTouch(a0);
              render();
            }
            public void onStopTrackingTouch(android.widget.SeekBar a0) {
              listener.onStopTrackingTouch(a0);
              render();
            }
            public void onProgressChanged(android.widget.SeekBar a0, int a1, boolean a2) {
              listener.onProgressChanged(a0, a1, a2);
              render();
            }
        });
      }
    });
  }
  public static Node onRatingBarChange(final android.widget.RatingBar.OnRatingBarChangeListener listener) {
    return new Node(new SimpleSetter(listener) {
      public void set(View v) {
        if (v instanceof android.widget.RatingBar)
          ((android.widget.RatingBar) v).setOnRatingBarChangeListener(new android.widget.RatingBar.OnRatingBarChangeListener() {
            public void onRatingChanged(android.widget.RatingBar a0, float a1, boolean a2) {
              listener.onRatingChanged(a0, a1, a2);
              render();
            }
        });
      }
    });
  }
  public static Node onZoomInClick(final android.view.View.OnClickListener listener) {
    return new Node(new SimpleSetter(listener) {
      public void set(View v) {
        if (v instanceof android.widget.ZoomControls)
          ((android.widget.ZoomControls) v).setOnZoomInClickListener(new android.view.View.OnClickListener() {
            public void onClick(android.view.View a0) {
              listener.onClick(a0);
              render();
            }
        });
      }
    });
  }
  public static Node onCreateContextMenu(final android.view.View.OnCreateContextMenuListener listener) {
    return new Node(new SimpleSetter(listener) {
      public void set(View v) {
        if (v instanceof android.view.View)
          ((android.view.View) v).setOnCreateContextMenuListener(new android.view.View.OnCreateContextMenuListener() {
            public void onCreateContextMenu(android.view.ContextMenu a0, android.view.View a1, android.view.ContextMenu.ContextMenuInfo a2) {
              listener.onCreateContextMenu(a0, a1, a2);
              render();
            }
        });
      }
    });
  }
  public static Node onKeyboardAction(final android.inputmethodservice.KeyboardView.OnKeyboardActionListener listener) {
    return new Node(new SimpleSetter(listener) {
      public void set(View v) {
        if (v instanceof android.inputmethodservice.KeyboardView)
          ((android.inputmethodservice.KeyboardView) v).setOnKeyboardActionListener(new android.inputmethodservice.KeyboardView.OnKeyboardActionListener() {
            public void onKey(int a0, int[] a1) {
              listener.onKey(a0, a1);
              render();
            }
            public void swipeRight() {
              listener.swipeRight();
              render();
            }
            public void swipeLeft() {
              listener.swipeLeft();
              render();
            }
            public void swipeUp() {
              listener.swipeUp();
              render();
            }
            public void swipeDown() {
              listener.swipeDown();
              render();
            }
            public void onPress(int a0) {
              listener.onPress(a0);
              render();
            }
            public void onRelease(int a0) {
              listener.onRelease(a0);
              render();
            }
            public void onText(java.lang.CharSequence a0) {
              listener.onText(a0);
              render();
            }
        });
      }
    });
  }
  public static Node onItemLongClick(final android.widget.AdapterView.OnItemLongClickListener listener) {
    return new Node(new SimpleSetter(listener) {
      public void set(View v) {
        if (v instanceof android.widget.AdapterView)
          ((android.widget.AdapterView) v).setOnItemLongClickListener(new android.widget.AdapterView.OnItemLongClickListener() {
            public boolean onItemLongClick(android.widget.AdapterView a0, android.view.View a1, int a2, long a3) {
              boolean r = listener.onItemLongClick(a0, a1, a2, a3);
              render();
              return r;
            }
        });
      }
    });
  }
  public static Node onCheckedChange(final android.widget.CompoundButton.OnCheckedChangeListener listener) {
    return new Node(new SimpleSetter(listener) {
      public void set(View v) {
        if (v instanceof android.widget.CompoundButton)
          ((android.widget.CompoundButton) v).setOnCheckedChangeListener(new android.widget.CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(android.widget.CompoundButton a0, boolean a1) {
              listener.onCheckedChanged(a0, a1);
              render();
            }
        });
      }
    });
  }
  public static Node onScroll(final android.widget.AbsListView.OnScrollListener listener) {
    return new Node(new SimpleSetter(listener) {
      public void set(View v) {
        if (v instanceof android.widget.AbsListView)
          ((android.widget.AbsListView) v).setOnScrollListener(new android.widget.AbsListView.OnScrollListener() {
            public void onScroll(android.widget.AbsListView a0, int a1, int a2, int a3) {
              listener.onScroll(a0, a1, a2, a3);
              render();
            }
            public void onScrollStateChanged(android.widget.AbsListView a0, int a1) {
              listener.onScrollStateChanged(a0, a1);
              render();
            }
        });
      }
    });
  }
  public static Node fastScrollEnabled(final boolean val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.AbsListView)
          ((android.widget.AbsListView) v).setFastScrollEnabled(val);
      }
    });
  }
  public static Node buttonDrawable(final int val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.CompoundButton)
          ((android.widget.CompoundButton) v).setButtonDrawable(val);
      }
    });
  }
  public static Node scrollBarStyle(final int val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.webkit.WebView)
          ((android.webkit.WebView) v).setScrollBarStyle(val);
        if (v instanceof android.view.View)
          ((android.view.View) v).setScrollBarStyle(val);
      }
    });
  }
  public static Node textKeepState(final java.lang.CharSequence val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.TextView)
          ((android.widget.TextView) v).setTextKeepState(val);
      }
    });
  }
  public static Node mapTrackballToArrowKeys(final boolean val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.webkit.WebView)
          ((android.webkit.WebView) v).setMapTrackballToArrowKeys(val);
      }
    });
  }
  public static Node completionHint(final java.lang.CharSequence val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.AutoCompleteTextView)
          ((android.widget.AutoCompleteTextView) v).setCompletionHint(val);
      }
    });
  }
  public static Node maxEms(final int val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.TextView)
          ((android.widget.TextView) v).setMaxEms(val);
      }
    });
  }
  public static Node drawingCacheEnabled(final boolean val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.view.View)
          ((android.view.View) v).setDrawingCacheEnabled(val);
      }
    });
  }
  public static Node transcriptMode(final int val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.AbsListView)
          ((android.widget.AbsListView) v).setTranscriptMode(val);
      }
    });
  }
  public static Node inflatedId(final int val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.view.ViewStub)
          ((android.view.ViewStub) v).setInflatedId(val);
      }
    });
  }
  public static Node gestureVisible(final boolean val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.gesture.GestureOverlayView)
          ((android.gesture.GestureOverlayView) v).setGestureVisible(val);
      }
    });
  }
  public static Node previewEnabled(final boolean val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.inputmethodservice.KeyboardView)
          ((android.inputmethodservice.KeyboardView) v).setPreviewEnabled(val);
      }
    });
  }
  public static Node keepScreenOn(final boolean val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.view.View)
          ((android.view.View) v).setKeepScreenOn(val);
      }
    });
  }
  public static Node linksClickable(final boolean val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.TextView)
          ((android.widget.TextView) v).setLinksClickable(val);
      }
    });
  }
  public static Node filterWatcher(final android.text.TextWatcher val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.DialerFilter)
          ((android.widget.DialerFilter) v).setFilterWatcher(val);
      }
    });
  }
  public static Node itemsCanFocus(final boolean val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.ListView)
          ((android.widget.ListView) v).setItemsCanFocus(val);
      }
    });
  }
  public static Node selected(final boolean val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.view.View)
          ((android.view.View) v).setSelected(val);
        if (v instanceof android.widget.TextView)
          ((android.widget.TextView) v).setSelected(val);
        if (v instanceof android.widget.ImageView)
          ((android.widget.ImageView) v).setSelected(val);
      }
    });
  }
  public static Node descendantFocusability(final int val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.view.ViewGroup)
          ((android.view.ViewGroup) v).setDescendantFocusability(val);
      }
    });
  }
  public static Node minWidth(final int val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.TextView)
          ((android.widget.TextView) v).setMinWidth(val);
      }
    });
  }
  public static Node keyListener(final android.text.method.KeyListener val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.TextView)
          ((android.widget.TextView) v).setKeyListener(val);
      }
    });
  }
  public static Node gestureStrokeAngleThreshold(final float val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.gesture.GestureOverlayView)
          ((android.gesture.GestureOverlayView) v).setGestureStrokeAngleThreshold(val);
      }
    });
  }
  public static Node checked(final boolean val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.CompoundButton)
          ((android.widget.CompoundButton) v).setChecked(val);
        if (v instanceof android.widget.ToggleButton)
          ((android.widget.ToggleButton) v).setChecked(val);
        if (v instanceof android.widget.CheckedTextView)
          ((android.widget.CheckedTextView) v).setChecked(val);
      }
    });
  }
  public static Node minimumHeight(final int val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.view.View)
          ((android.view.View) v).setMinimumHeight(val);
      }
    });
  }
  public static Node videoURI(final android.net.Uri val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.VideoView)
          ((android.widget.VideoView) v).setVideoURI(val);
      }
    });
  }
  public static Node max(final int val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.ProgressBar)
          ((android.widget.ProgressBar) v).setMax(val);
        if (v instanceof android.widget.RatingBar)
          ((android.widget.RatingBar) v).setMax(val);
        if (v instanceof android.widget.AbsSeekBar)
          ((android.widget.AbsSeekBar) v).setMax(val);
      }
    });
  }
  public static Node prompt(final java.lang.CharSequence val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.Spinner)
          ((android.widget.Spinner) v).setPrompt(val);
      }
    });
  }
  public static Node fadingEdgeLength(final int val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.view.View)
          ((android.view.View) v).setFadingEdgeLength(val);
      }
    });
  }
  public static Node flipInterval(final int val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.ViewFlipper)
          ((android.widget.ViewFlipper) v).setFlipInterval(val);
      }
    });
  }
  public static Node childIndicator(final android.graphics.drawable.Drawable val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.ExpandableListView)
          ((android.widget.ExpandableListView) v).setChildIndicator(val);
      }
    });
  }
  public static Node minimumWidth(final int val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.view.View)
          ((android.view.View) v).setMinimumWidth(val);
      }
    });
  }
  public static Node shrinkAllColumns(final boolean val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.TableLayout)
          ((android.widget.TableLayout) v).setShrinkAllColumns(val);
      }
    });
  }
  public static Node linkTextColor(final int val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.TextView)
          ((android.widget.TextView) v).setLinkTextColor(val);
      }
    });
  }
  public static Node width(final int val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.TextView)
          ((android.widget.TextView) v).setWidth(val);
      }
    });
  }
  public static Node alwaysDrawnWithCacheEnabled(final boolean val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.view.ViewGroup)
          ((android.view.ViewGroup) v).setAlwaysDrawnWithCacheEnabled(val);
      }
    });
  }
  public static Node footerDividersEnabled(final boolean val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.ListView)
          ((android.widget.ListView) v).setFooterDividersEnabled(val);
      }
    });
  }
  public static Node renderMode(final int val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.opengl.GLSurfaceView)
          ((android.opengl.GLSurfaceView) v).setRenderMode(val);
      }
    });
  }
  public static Node choiceMode(final int val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.ListView)
          ((android.widget.ListView) v).setChoiceMode(val);
      }
    });
  }
  public static Node verticalSpacing(final int val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.GridView)
          ((android.widget.GridView) v).setVerticalSpacing(val);
      }
    });
  }
  public static Node layoutParams(final android.view.ViewGroup.LayoutParams val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.webkit.WebView)
          ((android.webkit.WebView) v).setLayoutParams(val);
        if (v instanceof android.view.View)
          ((android.view.View) v).setLayoutParams(val);
      }
    });
  }
  public static Node measureAllChildren(final boolean val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.FrameLayout)
          ((android.widget.FrameLayout) v).setMeasureAllChildren(val);
      }
    });
  }
  public static Node weightSum(final float val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.LinearLayout)
          ((android.widget.LinearLayout) v).setWeightSum(val);
      }
    });
  }
  public static Node nextFocusRightId(final int val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.view.View)
          ((android.view.View) v).setNextFocusRightId(val);
      }
    });
  }
  public static Node maxWidth(final int val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.TextView)
          ((android.widget.TextView) v).setMaxWidth(val);
        if (v instanceof android.widget.ImageView)
          ((android.widget.ImageView) v).setMaxWidth(val);
      }
    });
  }
  public static Node baselineAlignedChildIndex(final int val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.LinearLayout)
          ((android.widget.LinearLayout) v).setBaselineAlignedChildIndex(val);
      }
    });
  }
  public static Node id(final int val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.view.View)
          ((android.view.View) v).setId(val);
      }
    });
  }
  public static Node text(final int val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.TextView)
          ((android.widget.TextView) v).setText(val);
      }
    });
  }
  public static Node debugFlags(final int val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.opengl.GLSurfaceView)
          ((android.opengl.GLSurfaceView) v).setDebugFlags(val);
      }
    });
  }
  public static Node fadeEnabled(final boolean val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.gesture.GestureOverlayView)
          ((android.gesture.GestureOverlayView) v).setFadeEnabled(val);
      }
    });
  }
  public static Node uncertainGestureColor(final int val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.gesture.GestureOverlayView)
          ((android.gesture.GestureOverlayView) v).setUncertainGestureColor(val);
      }
    });
  }
  public static Node animationCacheEnabled(final boolean val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.view.ViewGroup)
          ((android.view.ViewGroup) v).setAnimationCacheEnabled(val);
      }
    });
  }
  public static Node keyboard(final android.inputmethodservice.Keyboard val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.inputmethodservice.KeyboardView)
          ((android.inputmethodservice.KeyboardView) v).setKeyboard(val);
      }
    });
  }
  public static Node gestureStrokeSquarenessTreshold(final float val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.gesture.GestureOverlayView)
          ((android.gesture.GestureOverlayView) v).setGestureStrokeSquarenessTreshold(val);
      }
    });
  }
  public static Node hint(final java.lang.CharSequence val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.TextView)
          ((android.widget.TextView) v).setHint(val);
      }
    });
  }
  public static Node movementMethod(final android.text.method.MovementMethod val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.TextView)
          ((android.widget.TextView) v).setMovementMethod(val);
      }
    });
  }
  public static Node format(final java.lang.String val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.Chronometer)
          ((android.widget.Chronometer) v).setFormat(val);
      }
    });
  }
  public static Node baselineAligned(final boolean val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.LinearLayout)
          ((android.widget.LinearLayout) v).setBaselineAligned(val);
      }
    });
  }
  public static Node networkAvailable(final boolean val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.webkit.WebView)
          ((android.webkit.WebView) v).setNetworkAvailable(val);
      }
    });
  }
  public static Node gesture(final android.gesture.Gesture val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.gesture.GestureOverlayView)
          ((android.gesture.GestureOverlayView) v).setGesture(val);
      }
    });
  }
  public static Node filterText(final java.lang.String val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.AbsListView)
          ((android.widget.AbsListView) v).setFilterText(val);
      }
    });
  }
  public static Node stretchMode(final int val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.GridView)
          ((android.widget.GridView) v).setStretchMode(val);
      }
    });
  }
  public static Node isIndicator(final boolean val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.RatingBar)
          ((android.widget.RatingBar) v).setIsIndicator(val);
      }
    });
  }
  public static Node promptId(final int val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.Spinner)
          ((android.widget.Spinner) v).setPromptId(val);
      }
    });
  }
  public static Node isZoomOutEnabled(final boolean val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.ZoomControls)
          ((android.widget.ZoomControls) v).setIsZoomOutEnabled(val);
      }
    });
  }
  public static Node colorFilter(final android.graphics.ColorFilter val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.ImageView)
          ((android.widget.ImageView) v).setColorFilter(val);
      }
    });
  }
  public static Node groupIndicator(final android.graphics.drawable.Drawable val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.ExpandableListView)
          ((android.widget.ExpandableListView) v).setGroupIndicator(val);
      }
    });
  }
  public static Node animation(final android.view.animation.Animation val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.view.View)
          ((android.view.View) v).setAnimation(val);
      }
    });
  }
  public static Node layoutAnimationListener(final android.view.animation.Animation.AnimationListener val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.view.ViewGroup)
          ((android.view.ViewGroup) v).setLayoutAnimationListener(val);
      }
    });
  }
  public static Node dropDownHeight(final int val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.AutoCompleteTextView)
          ((android.widget.AutoCompleteTextView) v).setDropDownHeight(val);
      }
    });
  }
  public static Node rawInputType(final int val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.TextView)
          ((android.widget.TextView) v).setRawInputType(val);
      }
    });
  }
  public static Node nextFocusLeftId(final int val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.view.View)
          ((android.view.View) v).setNextFocusLeftId(val);
      }
    });
  }
  public static Node horizontalFadingEdgeEnabled(final boolean val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.view.View)
          ((android.view.View) v).setHorizontalFadingEdgeEnabled(val);
      }
    });
  }
  public static Node textOn(final java.lang.CharSequence val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.ToggleButton)
          ((android.widget.ToggleButton) v).setTextOn(val);
      }
    });
  }
  public static Node currentHour(final java.lang.Integer val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.TimePicker)
          ((android.widget.TimePicker) v).setCurrentHour(val);
      }
    });
  }
  public static Node freezesText(final boolean val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.TextView)
          ((android.widget.TextView) v).setFreezesText(val);
      }
    });
  }
  public static Node textColor(final android.content.res.ColorStateList val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.TextView)
          ((android.widget.TextView) v).setTextColor(val);
      }
    });
  }
  public static Node hintTextColor(final int val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.TextView)
          ((android.widget.TextView) v).setHintTextColor(val);
      }
    });
  }
  public static Node keyProgressIncrement(final int val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.AbsSeekBar)
          ((android.widget.AbsSeekBar) v).setKeyProgressIncrement(val);
      }
    });
  }
  public static Node renderer(final android.opengl.GLSurfaceView.Renderer val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.opengl.GLSurfaceView)
          ((android.opengl.GLSurfaceView) v).setRenderer(val);
      }
    });
  }
  public static Node progressDrawable(final android.graphics.drawable.Drawable val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.ProgressBar)
          ((android.widget.ProgressBar) v).setProgressDrawable(val);
      }
    });
  }
  public static Node scrollContainer(final boolean val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.view.View)
          ((android.view.View) v).setScrollContainer(val);
      }
    });
  }
  public static Node selectedGroup(final int val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.ExpandableListView)
          ((android.widget.ExpandableListView) v).setSelectedGroup(val);
      }
    });
  }
  public static Node focusable(final boolean val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.view.View)
          ((android.view.View) v).setFocusable(val);
        if (v instanceof android.widget.AdapterView)
          ((android.widget.AdapterView) v).setFocusable(val);
      }
    });
  }
  public static Node columnWidth(final int val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.GridView)
          ((android.widget.GridView) v).setColumnWidth(val);
      }
    });
  }
  public static Node pressed(final boolean val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.view.View)
          ((android.view.View) v).setPressed(val);
      }
    });
  }
  public static Node scaleType(final android.widget.ImageView.ScaleType val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.ImageView)
          ((android.widget.ImageView) v).setScaleType(val);
      }
    });
  }
  public static Node tag(final java.lang.Object val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.view.View)
          ((android.view.View) v).setTag(val);
      }
    });
  }
  public static Node callbackDuringFling(final boolean val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.Gallery)
          ((android.widget.Gallery) v).setCallbackDuringFling(val);
      }
    });
  }
  public static Node headerDividersEnabled(final boolean val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.ListView)
          ((android.widget.ListView) v).setHeaderDividersEnabled(val);
      }
    });
  }
  public static Node compoundDrawablePadding(final int val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.TextView)
          ((android.widget.TextView) v).setCompoundDrawablePadding(val);
      }
    });
  }
  public static Node verticalScrollbarOverlay(final boolean val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.webkit.WebView)
          ((android.webkit.WebView) v).setVerticalScrollbarOverlay(val);
      }
    });
  }
  public static Node imageURI(final android.net.Uri val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.ImageView)
          ((android.widget.ImageView) v).setImageURI(val);
        if (v instanceof android.widget.ImageSwitcher)
          ((android.widget.ImageSwitcher) v).setImageURI(val);
      }
    });
  }
  public static Node eGLConfigChooser(final android.opengl.GLSurfaceView.EGLConfigChooser val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.opengl.GLSurfaceView)
          ((android.opengl.GLSurfaceView) v).setEGLConfigChooser(val);
      }
    });
  }
  public static Node dividerHeight(final int val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.ListView)
          ((android.widget.ListView) v).setDividerHeight(val);
      }
    });
  }
  public static Node childDivider(final android.graphics.drawable.Drawable val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.ExpandableListView)
          ((android.widget.ExpandableListView) v).setChildDivider(val);
      }
    });
  }
  public static Node currentTabByTag(final java.lang.String val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.TabHost)
          ((android.widget.TabHost) v).setCurrentTabByTag(val);
      }
    });
  }
  public static Node orientation(final int val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.gesture.GestureOverlayView)
          ((android.gesture.GestureOverlayView) v).setOrientation(val);
        if (v instanceof android.widget.LinearLayout)
          ((android.widget.LinearLayout) v).setOrientation(val);
      }
    });
  }
  public static Node textOff(final java.lang.CharSequence val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.ToggleButton)
          ((android.widget.ToggleButton) v).setTextOff(val);
      }
    });
  }
  public static Node smoothScrollbarEnabled(final boolean val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.AbsListView)
          ((android.widget.AbsListView) v).setSmoothScrollbarEnabled(val);
      }
    });
  }
  public static Node ellipsize(final android.text.TextUtils.TruncateAt val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.TextView)
          ((android.widget.TextView) v).setEllipsize(val);
        if (v instanceof android.widget.EditText)
          ((android.widget.EditText) v).setEllipsize(val);
      }
    });
  }
  public static Node horizontalScrollBarEnabled(final boolean val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.view.View)
          ((android.view.View) v).setHorizontalScrollBarEnabled(val);
      }
    });
  }
  public static Node stretchAllColumns(final boolean val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.TableLayout)
          ((android.widget.TableLayout) v).setStretchAllColumns(val);
      }
    });
  }
  public static Node rating(final float val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.RatingBar)
          ((android.widget.RatingBar) v).setRating(val);
      }
    });
  }
  public static Node selector(final android.graphics.drawable.Drawable val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.AbsListView)
          ((android.widget.AbsListView) v).setSelector(val);
      }
    });
  }
  public static Node animateFirstView(final boolean val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.ViewAnimator)
          ((android.widget.ViewAnimator) v).setAnimateFirstView(val);
      }
    });
  }
  public static Node isZoomInEnabled(final boolean val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.ZoomControls)
          ((android.widget.ZoomControls) v).setIsZoomInEnabled(val);
      }
    });
  }
  public static Node downloadListener(final android.webkit.DownloadListener val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.webkit.WebView)
          ((android.webkit.WebView) v).setDownloadListener(val);
      }
    });
  }
  public static Node indeterminateDrawable(final android.graphics.drawable.Drawable val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.ProgressBar)
          ((android.widget.ProgressBar) v).setIndeterminateDrawable(val);
      }
    });
  }
  public static Node duplicateParentStateEnabled(final boolean val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.view.View)
          ((android.view.View) v).setDuplicateParentStateEnabled(val);
      }
    });
  }
  public static Node fadeOffset(final long val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.gesture.GestureOverlayView)
          ((android.gesture.GestureOverlayView) v).setFadeOffset(val);
      }
    });
  }
  public static Node indeterminate(final boolean val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.ProgressBar)
          ((android.widget.ProgressBar) v).setIndeterminate(val);
      }
    });
  }
  public static Node factory(final android.widget.ViewSwitcher.ViewFactory val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.ViewSwitcher)
          ((android.widget.ViewSwitcher) v).setFactory(val);
      }
    });
  }
  public static Node divider(final android.graphics.drawable.Drawable val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.ListView)
          ((android.widget.ListView) v).setDivider(val);
      }
    });
  }
  public static Node threshold(final int val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.AutoCompleteTextView)
          ((android.widget.AutoCompleteTextView) v).setThreshold(val);
      }
    });
  }
  public static Node selectAllOnFocus(final boolean val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.TextView)
          ((android.widget.TextView) v).setSelectAllOnFocus(val);
      }
    });
  }
  public static Node focusableInTouchMode(final boolean val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.view.View)
          ((android.view.View) v).setFocusableInTouchMode(val);
        if (v instanceof android.widget.AdapterView)
          ((android.widget.AdapterView) v).setFocusableInTouchMode(val);
      }
    });
  }
  public static Node checkMarkDrawable(final android.graphics.drawable.Drawable val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.CheckedTextView)
          ((android.widget.CheckedTextView) v).setCheckMarkDrawable(val);
      }
    });
  }
  public static Node inputType(final int val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.TextView)
          ((android.widget.TextView) v).setInputType(val);
      }
    });
  }
  public static Node soundEffectsEnabled(final boolean val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.view.View)
          ((android.view.View) v).setSoundEffectsEnabled(val);
      }
    });
  }
  public static Node dividerDrawable(final int val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.TabWidget)
          ((android.widget.TabWidget) v).setDividerDrawable(val);
      }
    });
  }
  public static Node horizontallyScrolling(final boolean val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.TextView)
          ((android.widget.TextView) v).setHorizontallyScrolling(val);
      }
    });
  }
  public static Node imageResource(final int val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.ImageView)
          ((android.widget.ImageView) v).setImageResource(val);
        if (v instanceof android.widget.ImageSwitcher)
          ((android.widget.ImageSwitcher) v).setImageResource(val);
      }
    });
  }
  public static Node mode(final int val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.DialerFilter)
          ((android.widget.DialerFilter) v).setMode(val);
      }
    });
  }
  public static Node typeface(final android.graphics.Typeface val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.TextView)
          ((android.widget.TextView) v).setTypeface(val);
      }
    });
  }
  public static Node up(final android.app.LocalActivityManager val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.TabHost)
          ((android.widget.TabHost) v).setup(val);
      }
    });
  }
  public static Node lettersWatcher(final android.text.TextWatcher val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.DialerFilter)
          ((android.widget.DialerFilter) v).setLettersWatcher(val);
      }
    });
  }
  public static Node enabled(final boolean val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.view.View)
          ((android.view.View) v).setEnabled(val);
        if (v instanceof android.widget.TabWidget)
          ((android.widget.TabWidget) v).setEnabled(val);
        if (v instanceof android.widget.MediaController)
          ((android.widget.MediaController) v).setEnabled(val);
        if (v instanceof android.widget.DatePicker)
          ((android.widget.DatePicker) v).setEnabled(val);
        if (v instanceof android.widget.ZoomButton)
          ((android.widget.ZoomButton) v).setEnabled(val);
        if (v instanceof android.widget.TimePicker)
          ((android.widget.TimePicker) v).setEnabled(val);
      }
    });
  }
  public static Node persistentDrawingCache(final int val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.view.ViewGroup)
          ((android.view.ViewGroup) v).setPersistentDrawingCache(val);
      }
    });
  }
  public static Node stepSize(final float val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.RatingBar)
          ((android.widget.RatingBar) v).setStepSize(val);
      }
    });
  }
  public static Node proximityCorrectionEnabled(final boolean val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.inputmethodservice.KeyboardView)
          ((android.inputmethodservice.KeyboardView) v).setProximityCorrectionEnabled(val);
      }
    });
  }
  public static Node dropDownAnchor(final int val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.AutoCompleteTextView)
          ((android.widget.AutoCompleteTextView) v).setDropDownAnchor(val);
      }
    });
  }
  public static Node drawingCacheQuality(final int val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.view.View)
          ((android.view.View) v).setDrawingCacheQuality(val);
      }
    });
  }
  public static Node base(final long val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.Chronometer)
          ((android.widget.Chronometer) v).setBase(val);
      }
    });
  }
  public static Node currentTab(final int val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.TabWidget)
          ((android.widget.TabWidget) v).setCurrentTab(val);
        if (v instanceof android.widget.TabHost)
          ((android.widget.TabHost) v).setCurrentTab(val);
      }
    });
  }
  public static Node contentDescription(final java.lang.CharSequence val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.view.View)
          ((android.view.View) v).setContentDescription(val);
      }
    });
  }
  public static Node imageMatrix(final android.graphics.Matrix val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.ImageView)
          ((android.widget.ImageView) v).setImageMatrix(val);
      }
    });
  }
  public static Node unselectedAlpha(final float val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.Gallery)
          ((android.widget.Gallery) v).setUnselectedAlpha(val);
      }
    });
  }
  public static Node inAnimation(final android.view.animation.Animation val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.ViewAnimator)
          ((android.widget.ViewAnimator) v).setInAnimation(val);
      }
    });
  }
  public static Node imeOptions(final int val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.TextView)
          ((android.widget.TextView) v).setImeOptions(val);
      }
    });
  }
  public static Node popupParent(final android.view.View val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.inputmethodservice.KeyboardView)
          ((android.inputmethodservice.KeyboardView) v).setPopupParent(val);
      }
    });
  }
  public static Node longClickable(final boolean val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.view.View)
          ((android.view.View) v).setLongClickable(val);
      }
    });
  }
  public static Node digitsWatcher(final android.text.TextWatcher val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.DialerFilter)
          ((android.widget.DialerFilter) v).setDigitsWatcher(val);
      }
    });
  }
  public static Node smoothScrollingEnabled(final boolean val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.HorizontalScrollView)
          ((android.widget.HorizontalScrollView) v).setSmoothScrollingEnabled(val);
        if (v instanceof android.widget.ScrollView)
          ((android.widget.ScrollView) v).setSmoothScrollingEnabled(val);
      }
    });
  }
  public static Node nextFocusDownId(final int val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.view.View)
          ((android.view.View) v).setNextFocusDownId(val);
      }
    });
  }
  public static Node horizontalGravity(final int val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.RelativeLayout)
          ((android.widget.RelativeLayout) v).setHorizontalGravity(val);
        if (v instanceof android.widget.LinearLayout)
          ((android.widget.LinearLayout) v).setHorizontalGravity(val);
      }
    });
  }
  public static Node cacheColorHint(final int val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.AbsListView)
          ((android.widget.AbsListView) v).setCacheColorHint(val);
        if (v instanceof android.widget.ListView)
          ((android.widget.ListView) v).setCacheColorHint(val);
      }
    });
  }
  public static Node horizontalSpacing(final int val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.GridView)
          ((android.widget.GridView) v).setHorizontalSpacing(val);
      }
    });
  }
  public static Node maxHeight(final int val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.TextView)
          ((android.widget.TextView) v).setMaxHeight(val);
        if (v instanceof android.widget.ImageView)
          ((android.widget.ImageView) v).setMaxHeight(val);
      }
    });
  }
  public static Node displayedChild(final int val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.ViewAnimator)
          ((android.widget.ViewAnimator) v).setDisplayedChild(val);
      }
    });
  }
  public static Node thumbOffset(final int val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.AbsSeekBar)
          ((android.widget.AbsSeekBar) v).setThumbOffset(val);
      }
    });
  }
  public static Node textFilterEnabled(final boolean val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.AbsListView)
          ((android.widget.AbsListView) v).setTextFilterEnabled(val);
      }
    });
  }
  public static Node spacing(final int val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.Gallery)
          ((android.widget.Gallery) v).setSpacing(val);
      }
    });
  }
  public static Node error(final java.lang.CharSequence val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.TextView)
          ((android.widget.TextView) v).setError(val);
      }
    });
  }
  public static Node webViewClient(final android.webkit.WebViewClient val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.webkit.WebView)
          ((android.webkit.WebView) v).setWebViewClient(val);
      }
    });
  }
  public static Node adapter(final android.widget.ExpandableListAdapter val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.ExpandableListView)
          ((android.widget.ExpandableListView) v).setAdapter(val);
      }
    });
  }
  public static Node is24HourView(final java.lang.Boolean val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.TimePicker)
          ((android.widget.TimePicker) v).setIs24HourView(val);
      }
    });
  }
  public static Node buttonDrawable(final android.graphics.drawable.Drawable val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.CompoundButton)
          ((android.widget.CompoundButton) v).setButtonDrawable(val);
      }
    });
  }
  public static Node extractedText(final android.view.inputmethod.ExtractedText val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.TextView)
          ((android.widget.TextView) v).setExtractedText(val);
        if (v instanceof android.inputmethodservice.ExtractEditText)
          ((android.inputmethodservice.ExtractEditText) v).setExtractedText(val);
      }
    });
  }
  public static Node backgroundColor(final int val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.webkit.WebView)
          ((android.webkit.WebView) v).setBackgroundColor(val);
        if (v instanceof android.view.View)
          ((android.view.View) v).setBackgroundColor(val);
      }
    });
  }
  public static Node includeFontPadding(final boolean val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.TextView)
          ((android.widget.TextView) v).setIncludeFontPadding(val);
      }
    });
  }
  public static Node cursorVisible(final boolean val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.TextView)
          ((android.widget.TextView) v).setCursorVisible(val);
      }
    });
  }
  public static Node thumb(final android.graphics.drawable.Drawable val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.AbsSeekBar)
          ((android.widget.AbsSeekBar) v).setThumb(val);
      }
    });
  }
  public static Node interpolator(final android.view.animation.Interpolator val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.ProgressBar)
          ((android.widget.ProgressBar) v).setInterpolator(val);
      }
    });
  }
  public static Node imageBitmap(final android.graphics.Bitmap val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.ImageView)
          ((android.widget.ImageView) v).setImageBitmap(val);
      }
    });
  }
  public static Node drawSelectorOnTop(final boolean val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.AbsListView)
          ((android.widget.AbsListView) v).setDrawSelectorOnTop(val);
      }
    });
  }
  public static Node progress(final int val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.ProgressBar)
          ((android.widget.ProgressBar) v).setProgress(val);
      }
    });
  }
  public static Node foregroundGravity(final int val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.FrameLayout)
          ((android.widget.FrameLayout) v).setForegroundGravity(val);
      }
    });
  }
  public static Node verticalGravity(final int val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.RelativeLayout)
          ((android.widget.RelativeLayout) v).setVerticalGravity(val);
        if (v instanceof android.widget.LinearLayout)
          ((android.widget.LinearLayout) v).setVerticalGravity(val);
      }
    });
  }
  public static Node emptyView(final android.view.View val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.AdapterView)
          ((android.widget.AdapterView) v).setEmptyView(val);
      }
    });
  }
  public static Node dropDownWidth(final int val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.AutoCompleteTextView)
          ((android.widget.AutoCompleteTextView) v).setDropDownWidth(val);
      }
    });
  }
  public static Node currentText(final java.lang.CharSequence val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.TextSwitcher)
          ((android.widget.TextSwitcher) v).setCurrentText(val);
      }
    });
  }
  public static Node adapter(final android.widget.SpinnerAdapter val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.AbsSpinner)
          ((android.widget.AbsSpinner) v).setAdapter(val);
      }
    });
  }
  public static Node imageLevel(final int val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.ImageView)
          ((android.widget.ImageView) v).setImageLevel(val);
      }
    });
  }
  public static Node editableFactory(final android.text.Editable.Factory val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.TextView)
          ((android.widget.TextView) v).setEditableFactory(val);
      }
    });
  }
  public static Node adapter(final android.widget.Adapter val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.AdapterView)
          ((android.widget.AdapterView) v).setAdapter(val);
      }
    });
  }
  public static Node scrollingCacheEnabled(final boolean val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.AbsListView)
          ((android.widget.AbsListView) v).setScrollingCacheEnabled(val);
      }
    });
  }
  public static Node height(final int val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.TextView)
          ((android.widget.TextView) v).setHeight(val);
      }
    });
  }
  public static Node autoLinkMask(final int val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.TextView)
          ((android.widget.TextView) v).setAutoLinkMask(val);
      }
    });
  }
  public static Node backgroundResource(final int val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.view.View)
          ((android.view.View) v).setBackgroundResource(val);
      }
    });
  }
  public static Node linkTextColor(final android.content.res.ColorStateList val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.TextView)
          ((android.widget.TextView) v).setLinkTextColor(val);
      }
    });
  }
  public static Node imageDrawable(final android.graphics.drawable.Drawable val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.ImageView)
          ((android.widget.ImageView) v).setImageDrawable(val);
        if (v instanceof android.widget.ImageSwitcher)
          ((android.widget.ImageSwitcher) v).setImageDrawable(val);
      }
    });
  }
  public static Node scroller(final android.widget.Scroller val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.TextView)
          ((android.widget.TextView) v).setScroller(val);
      }
    });
  }
  public static Node minHeight(final int val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.TextView)
          ((android.widget.TextView) v).setMinHeight(val);
      }
    });
  }
  public static Node foreground(final android.graphics.drawable.Drawable val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.FrameLayout)
          ((android.widget.FrameLayout) v).setForeground(val);
      }
    });
  }
  public static Node willNotDraw(final boolean val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.view.View)
          ((android.view.View) v).setWillNotDraw(val);
      }
    });
  }
  public static Node tokenizer(final android.widget.MultiAutoCompleteTextView.Tokenizer val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.MultiAutoCompleteTextView)
          ((android.widget.MultiAutoCompleteTextView) v).setTokenizer(val);
      }
    });
  }
  public static Node layoutResource(final int val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.view.ViewStub)
          ((android.view.ViewStub) v).setLayoutResource(val);
      }
    });
  }
  public static Node fillViewport(final boolean val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.HorizontalScrollView)
          ((android.widget.HorizontalScrollView) v).setFillViewport(val);
        if (v instanceof android.widget.ScrollView)
          ((android.widget.ScrollView) v).setFillViewport(val);
      }
    });
  }
  public static Node verticalFadingEdgeEnabled(final boolean val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.view.View)
          ((android.view.View) v).setVerticalFadingEdgeEnabled(val);
      }
    });
  }
  public static Node adapter(final android.widget.ListAdapter val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        // Here setAdapter expects <? extends ListAdapter,Filterable>
        // if (v instanceof android.widget.AutoCompleteTextView)
        //   if (val instanceof android.widget.ListAdapter && val instanceof android.widget.Filterable)
        //     ???
        
        if (v instanceof android.widget.ExpandableListView)
          ((android.widget.ExpandableListView) v).setAdapter(val);
        if (v instanceof android.widget.ListView)
          ((android.widget.ListView) v).setAdapter(val);
        if (v instanceof android.widget.GridView)
          ((android.widget.GridView) v).setAdapter(val);
      }
    });
  }
  public static Node nextFocusUpId(final int val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.view.View)
          ((android.view.View) v).setNextFocusUpId(val);
      }
    });
  }
  public static Node transformationMethod(final android.text.method.TransformationMethod val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.TextView)
          ((android.widget.TextView) v).setTransformationMethod(val);
      }
    });
  }
  public static Node selection(final int val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.AbsSpinner)
          ((android.widget.AbsSpinner) v).setSelection(val);
        if (v instanceof android.widget.AdapterView)
          ((android.widget.AdapterView) v).setSelection(val);
        if (v instanceof android.widget.ListView)
          ((android.widget.ListView) v).setSelection(val);
        if (v instanceof android.widget.GridView)
          ((android.widget.GridView) v).setSelection(val);
        if (v instanceof android.widget.EditText)
          ((android.widget.EditText) v).setSelection(val);
      }
    });
  }
  public static Node highlightColor(final int val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.TextView)
          ((android.widget.TextView) v).setHighlightColor(val);
      }
    });
  }
  public static Node saveEnabled(final boolean val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.view.View)
          ((android.view.View) v).setSaveEnabled(val);
      }
    });
  }
  public static Node drawingCacheBackgroundColor(final int val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.view.View)
          ((android.view.View) v).setDrawingCacheBackgroundColor(val);
      }
    });
  }
  public static Node layoutAnimation(final android.view.animation.LayoutAnimationController val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.view.ViewGroup)
          ((android.view.ViewGroup) v).setLayoutAnimation(val);
      }
    });
  }
  public static Node ems(final int val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.TextView)
          ((android.widget.TextView) v).setEms(val);
      }
    });
  }
  public static Node outAnimation(final android.view.animation.Animation val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.ViewAnimator)
          ((android.widget.ViewAnimator) v).setOutAnimation(val);
      }
    });
  }
  public static Node textScaleX(final float val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.TextView)
          ((android.widget.TextView) v).setTextScaleX(val);
      }
    });
  }
  public static Node stackFromBottom(final boolean val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.AbsListView)
          ((android.widget.AbsListView) v).setStackFromBottom(val);
      }
    });
  }
  public static Node mediaPlayer(final android.widget.MediaController.MediaPlayerControl val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.MediaController)
          ((android.widget.MediaController) v).setMediaPlayer(val);
      }
    });
  }
  public static Node listSelection(final int val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.AutoCompleteTextView)
          ((android.widget.AutoCompleteTextView) v).setListSelection(val);
      }
    });
  }
  public static Node singleLine(final boolean val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.TextView)
          ((android.widget.TextView) v).setSingleLine(val);
      }
    });
  }
  public static Node lines(final int val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.TextView)
          ((android.widget.TextView) v).setLines(val);
      }
    });
  }
  public static Node gestureStrokeType(final int val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.gesture.GestureOverlayView)
          ((android.gesture.GestureOverlayView) v).setGestureStrokeType(val);
      }
    });
  }
  public static Node selector(final int val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.AbsListView)
          ((android.widget.AbsListView) v).setSelector(val);
      }
    });
  }
  public static Node horizontalScrollbarOverlay(final boolean val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.webkit.WebView)
          ((android.webkit.WebView) v).setHorizontalScrollbarOverlay(val);
      }
    });
  }
  public static Node currentMinute(final java.lang.Integer val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.TimePicker)
          ((android.widget.TimePicker) v).setCurrentMinute(val);
      }
    });
  }
  public static Node alpha(final int val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.ImageView)
          ((android.widget.ImageView) v).setAlpha(val);
      }
    });
  }
  public static Node inputExtras(final int val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        try {
        	if (v instanceof android.widget.TextView)
        		((android.widget.TextView) v).setInputExtras(val);
        } catch (org.xmlpull.v1.XmlPullParserException e) {
        	e.printStackTrace();
        } catch (java.io.IOException e) {
        	e.printStackTrace();
        }
        
        
      }
    });
  }
  public static Node verticalScrollBarEnabled(final boolean val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.view.View)
          ((android.view.View) v).setVerticalScrollBarEnabled(val);
      }
    });
  }
  public static Node minEms(final int val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.TextView)
          ((android.widget.TextView) v).setMinEms(val);
      }
    });
  }
  public static Node numColumns(final int val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.GridView)
          ((android.widget.GridView) v).setNumColumns(val);
      }
    });
  }
  public static Node gestureColor(final int val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.gesture.GestureOverlayView)
          ((android.gesture.GestureOverlayView) v).setGestureColor(val);
      }
    });
  }
  public static Node clickable(final boolean val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.view.View)
          ((android.view.View) v).setClickable(val);
      }
    });
  }
  public static Node gravity(final int val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.TextView)
          ((android.widget.TextView) v).setGravity(val);
        if (v instanceof android.widget.RelativeLayout)
          ((android.widget.RelativeLayout) v).setGravity(val);
        if (v instanceof android.widget.GridView)
          ((android.widget.GridView) v).setGravity(val);
        if (v instanceof android.widget.Gallery)
          ((android.widget.Gallery) v).setGravity(val);
        if (v instanceof android.widget.LinearLayout)
          ((android.widget.LinearLayout) v).setGravity(val);
      }
    });
  }
  public static Node spannableFactory(final android.text.Spannable.Factory val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.TextView)
          ((android.widget.TextView) v).setSpannableFactory(val);
      }
    });
  }
  public static Node anchorView(final android.view.View val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.MediaController)
          ((android.widget.MediaController) v).setAnchorView(val);
      }
    });
  }
  public static Node adjustViewBounds(final boolean val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.ImageView)
          ((android.widget.ImageView) v).setAdjustViewBounds(val);
      }
    });
  }
  public static Node animationDuration(final int val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.Gallery)
          ((android.widget.Gallery) v).setAnimationDuration(val);
      }
    });
  }
  public static Node shifted(final boolean val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.inputmethodservice.KeyboardView)
          ((android.inputmethodservice.KeyboardView) v).setShifted(val);
      }
    });
  }
  public static Node filters(final android.text.InputFilter[] val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.TextView)
          ((android.widget.TextView) v).setFilters(val);
      }
    });
  }
  public static Node ignoreGravity(final int val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.RelativeLayout)
          ((android.widget.RelativeLayout) v).setIgnoreGravity(val);
      }
    });
  }
  public static Node eventsInterceptionEnabled(final boolean val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.gesture.GestureOverlayView)
          ((android.gesture.GestureOverlayView) v).setEventsInterceptionEnabled(val);
      }
    });
  }
  public static Node text(final java.lang.CharSequence val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.TextView)
          ((android.widget.TextView) v).setText(val);
        if (v instanceof android.widget.TextSwitcher)
          ((android.widget.TextSwitcher) v).setText(val);
      }
    });
  }
  public static Node gestureStrokeLengthThreshold(final float val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.gesture.GestureOverlayView)
          ((android.gesture.GestureOverlayView) v).setGestureStrokeLengthThreshold(val);
      }
    });
  }
  public static Node privateImeOptions(final java.lang.String val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.TextView)
          ((android.widget.TextView) v).setPrivateImeOptions(val);
      }
    });
  }
  public static Node willNotCacheDrawing(final boolean val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.view.View)
          ((android.view.View) v).setWillNotCacheDrawing(val);
      }
    });
  }
  public static Node initialScale(final int val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.webkit.WebView)
          ((android.webkit.WebView) v).setInitialScale(val);
      }
    });
  }
  public static Node paintFlags(final int val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.TextView)
          ((android.widget.TextView) v).setPaintFlags(val);
      }
    });
  }
  public static Node validator(final android.widget.AutoCompleteTextView.Validator val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.AutoCompleteTextView)
          ((android.widget.AutoCompleteTextView) v).setValidator(val);
      }
    });
  }
  public static Node backgroundDrawable(final android.graphics.drawable.Drawable val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.view.View)
          ((android.view.View) v).setBackgroundDrawable(val);
        if (v instanceof android.widget.ToggleButton)
          ((android.widget.ToggleButton) v).setBackgroundDrawable(val);
      }
    });
  }
  public static Node clipToPadding(final boolean val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.view.ViewGroup)
          ((android.view.ViewGroup) v).setClipToPadding(val);
      }
    });
  }
  public static Node gestureStrokeWidth(final float val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.gesture.GestureOverlayView)
          ((android.gesture.GestureOverlayView) v).setGestureStrokeWidth(val);
      }
    });
  }
  public static Node webChromeClient(final android.webkit.WebChromeClient val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.webkit.WebView)
          ((android.webkit.WebView) v).setWebChromeClient(val);
      }
    });
  }
  public static Node touchDelegate(final android.view.TouchDelegate val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.view.View)
          ((android.view.View) v).setTouchDelegate(val);
      }
    });
  }
  public static Node clipChildren(final boolean val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.view.ViewGroup)
          ((android.view.ViewGroup) v).setClipChildren(val);
      }
    });
  }
  public static Node hapticFeedbackEnabled(final boolean val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.view.View)
          ((android.view.View) v).setHapticFeedbackEnabled(val);
      }
    });
  }
  public static Node recyclerListener(final android.widget.AbsListView.RecyclerListener val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.AbsListView)
          ((android.widget.AbsListView) v).setRecyclerListener(val);
      }
    });
  }
  public static Node textSize(final float val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.TextView)
          ((android.widget.TextView) v).setTextSize(val);
      }
    });
  }
  public static Node eGLConfigChooser(final boolean val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.opengl.GLSurfaceView)
          ((android.opengl.GLSurfaceView) v).setEGLConfigChooser(val);
      }
    });
  }
  public static Node minLines(final int val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.TextView)
          ((android.widget.TextView) v).setMinLines(val);
      }
    });
  }
  public static Node certificate(final android.net.http.SslCertificate val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.webkit.WebView)
          ((android.webkit.WebView) v).setCertificate(val);
      }
    });
  }
  public static Node textColor(final int val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.TextView)
          ((android.widget.TextView) v).setTextColor(val);
      }
    });
  }
  public static Node mediaController(final android.widget.MediaController val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.VideoView)
          ((android.widget.VideoView) v).setMediaController(val);
      }
    });
  }
  public static Node visibility(final int val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.view.View)
          ((android.view.View) v).setVisibility(val);
        if (v instanceof android.view.ViewStub)
          ((android.view.ViewStub) v).setVisibility(val);
        if (v instanceof android.widget.ProgressBar)
          ((android.widget.ProgressBar) v).setVisibility(val);
      }
    });
  }
  public static Node addStatesFromChildren(final boolean val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.view.ViewGroup)
          ((android.view.ViewGroup) v).setAddStatesFromChildren(val);
      }
    });
  }
  public static Node zoomSpeed(final long val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.ZoomControls)
          ((android.widget.ZoomControls) v).setZoomSpeed(val);
        if (v instanceof android.widget.ZoomButton)
          ((android.widget.ZoomButton) v).setZoomSpeed(val);
      }
    });
  }
  public static Node pictureListener(final android.webkit.WebView.PictureListener val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.webkit.WebView)
          ((android.webkit.WebView) v).setPictureListener(val);
      }
    });
  }
  public static Node marqueeRepeatLimit(final int val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.TextView)
          ((android.widget.TextView) v).setMarqueeRepeatLimit(val);
      }
    });
  }
  public static Node dividerDrawable(final android.graphics.drawable.Drawable val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.TabWidget)
          ((android.widget.TabWidget) v).setDividerDrawable(val);
      }
    });
  }
  public static Node checkMarkDrawable(final int val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.CheckedTextView)
          ((android.widget.CheckedTextView) v).setCheckMarkDrawable(val);
      }
    });
  }
  public static Node videoPath(final java.lang.String val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.VideoView)
          ((android.widget.VideoView) v).setVideoPath(val);
      }
    });
  }
  public static Node gLWrapper(final android.opengl.GLSurfaceView.GLWrapper val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.opengl.GLSurfaceView)
          ((android.opengl.GLSurfaceView) v).setGLWrapper(val);
      }
    });
  }
  public static Node maxLines(final int val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.TextView)
          ((android.widget.TextView) v).setMaxLines(val);
      }
    });
  }
  public static Node numStars(final int val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.RatingBar)
          ((android.widget.RatingBar) v).setNumStars(val);
      }
    });
  }
  public static Node hint(final int val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.TextView)
          ((android.widget.TextView) v).setHint(val);
      }
    });
  }
  public static Node secondaryProgress(final int val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.ProgressBar)
          ((android.widget.ProgressBar) v).setSecondaryProgress(val);
      }
    });
  }
  public static Node verticalCorrection(final int val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.inputmethodservice.KeyboardView)
          ((android.inputmethodservice.KeyboardView) v).setVerticalCorrection(val);
      }
    });
  }
  public static Node hintTextColor(final android.content.res.ColorStateList val) {
    return new Node(new SimpleSetter(val) {
      public void set(View v) {
        if (v instanceof android.widget.TextView)
          ((android.widget.TextView) v).setHintTextColor(val);
      }
    });
  }
}

