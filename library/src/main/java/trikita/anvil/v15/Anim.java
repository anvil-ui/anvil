package trikita.anvil.v15;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.view.View;

import java.util.*;

import trikita.anvil.Nodes;

public final class Anim {

	//
	//
	//
	public static class SimpleAnimationNode implements Nodes.AttrNode {
		final boolean trigger;

		final List<PropertyValuesHolder> props = new ArrayList<>();
		final List<Animator.AnimatorListener> listeners = new ArrayList<>();
		final List<Runnable> runnables = new ArrayList<>();

		AnimatorSet anim;
		int delayMs = 0;
		int durationMs = 250;

		public SimpleAnimationNode(boolean trigger) {
			this.trigger = trigger;
		}

		public void apply(View v) {
			if (this.trigger) {
				this.anim = new AnimatorSet();
				this.anim.setStartDelay(this.delayMs);
				this.anim.setDuration(this.durationMs);
				for (PropertyValuesHolder p : props) {
					this.anim.play(ObjectAnimator.ofPropertyValuesHolder(v, p));
				}
				for (Animator.AnimatorListener l : listeners) {
					this.anim.addListener(l);
				}
				for (final Runnable r : runnables) {
					this.anim.addListener(new AnimatorListenerAdapter() {
						public void onAnimationEnd(Animator anim) {
							r.run();
						}
					});
				}
				this.anim.start();
			}
		}

		public int hashCode() {
			return anim.hashCode();
		}

		public boolean equals(Object obj) {
			if (getClass() != obj.getClass()) {
				return false;
			}
			SimpleAnimationNode n = (SimpleAnimationNode) obj;
			if (n.trigger != this.trigger) {
				if (n.anim != null && n.anim.isRunning()) {
					n.anim.cancel();
				}
				return false;
			}
			return true;
		}

		public SimpleAnimationNode of(String prop, float ...values) {
			this.props.add(PropertyValuesHolder.ofFloat(prop, values));
			return this;
		}

		public SimpleAnimationNode delay(int ms) {
			this.delayMs = ms;
			return this;
		}

		public SimpleAnimationNode duration(int ms) {
			this.durationMs = ms;
			return this;
		}

		public SimpleAnimationNode listener(Runnable r) {
			this.runnables.add(r);
			return this;
		}

		public SimpleAnimationNode listener(Animator.AnimatorListener l) {
			this.listeners.add(l);
			return this;
		}
	}

	public static SimpleAnimationNode anim(boolean trigger) {
		return new SimpleAnimationNode(trigger);
	}

	//
	//
	//
	private static class AnimationNode implements Nodes.AttrNode {
		final boolean trigger;
		final Animator anim;

		public AnimationNode(boolean trigger, Animator anim) {
			this.trigger = trigger;
			this.anim = anim;
		}

		public void apply(View v) {
			if (this.trigger) {
				this.anim.setTarget(v);
				this.anim.start();
			}
		}

		public int hashCode() {
			return anim.hashCode();
		}

		public boolean equals(Object obj) {
			if (getClass() != obj.getClass()) {
				return false;
			}
			AnimationNode n = (AnimationNode) obj;
			if (n.trigger != this.trigger) {
				if (n.anim.isRunning()) {
					n.anim.cancel();
				}
				return false;
			}
			return true;
		}
	}

	public static AnimationNode anim(boolean trigger, Animator a) {
		return new AnimationNode(trigger, a);
	}
}
