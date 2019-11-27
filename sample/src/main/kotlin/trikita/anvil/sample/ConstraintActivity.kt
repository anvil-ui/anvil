package trikita.anvil.sample

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.Barrier
import androidx.constraintlayout.widget.ConstraintSet
import androidx.constraintlayout.widget.ConstraintSet.VERTICAL_GUIDELINE
import androidx.core.view.ViewCompat
import trikita.anvil.*

class ConstraintActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val layoutId = genId()
        val chainTitle = genId()
        val barrierTitle = genId()
        val guidelineTitle = genId()
        val circleTitle = genId()
        val guidelineId = genId()
        val txtId = genId()
        val btnId = genId()
        val txt1Id = genId()
        val txt2Id = genId()
        val txt3Id = genId()
        val txt4Id = genId()
        val txt5Id = genId()
        val txt6Id = genId()
        val barrierId = genId()
        val angle0 = genId()
        val angle45 = genId()
        val angle90 = genId()
        val angle135 = genId()
        val angle180 = genId()
        val angle225 = genId()
        val angle270 = genId()
        val angle315 = genId()
        val angle340 = genId()
        val groupId = genId()
        val placeholderId = genId()

        renderableContentView {
            constraintLayout {
                id(layoutId)
                size(MATCH, MATCH)
                padding(dip(8))

                textView {
                    constraintId(chainTitle)
                    size(WRAP, WRAP)
                    text("Chain")
                    textSize(sip(24f))
                    backgroundColor(0xffff7f7fL.toInt())
                    leftConstraintToParent()
                    topConstraintToParent()
                }

                textView {
                    constraintId(txt1Id)
                    size(WRAP, WRAP)
                    text("Text1")

                    topConstraintToBottom(chainTitle)
                    leftConstraintToParent()
                    rightConstraintToLeft(txt2Id)
                    horizontalChain(
                        ConstraintSet.PARENT_ID,
                        ConstraintSet.LEFT,
                        ConstraintSet.PARENT_ID,
                        ConstraintSet.RIGHT,
                        intArrayOf(txt1Id, txt2Id, txt3Id),
                        null,
                        ConstraintSet.CHAIN_SPREAD_INSIDE
                    )
                }
                textView {
                    constraintId(txt2Id)
                    size(WRAP, WRAP)
                    text("Text2")

                    topConstraintToBottom(chainTitle)
                }
                textView {
                    constraintId(txt3Id)
                    size(WRAP, WRAP)
                    text("Text3")

                    topConstraintToBottom(chainTitle)
                }

                textView {
                    constraintId(barrierTitle)
                    text("Barrier")
                    textSize(sip(24f))

                    leftConstraintToParent()
                    topConstraintToBottom(txt1Id)
                }
                textView {
                    constraintId(txt4Id)
                    size(WRAP, WRAP)
                    text("Text4444444444")


                    topConstraintToBottom(barrierTitle)
                    leftConstraintToParent()
                }

                textView {
                    constraintId(txt5Id)
                    size(WRAP, WRAP)
                    text("Text5")

                    topConstraintToBottom(txt4Id)
                    leftConstraintToParent()
                }

                barrier {
                    constraintId(barrierId)
                    type(Barrier.END)
                    referencedIds(txt4Id, txt5Id)
                }

                textView {
                    constraintId(txt6Id)
                    size(WRAP, WRAP)
                    text("Text6")

                    topConstraintToBottom(txt5Id)
                    leftConstraintToRight(barrierId)
                }

                textView {
                    constraintId(guidelineTitle)
                    text("Guideline")
                    textSize(sip(24f))

                    leftConstraintToParent()
                    topConstraintToBottom(txt6Id)
                }

                guideline {
                    constraintId(guidelineId)
                    orientation(VERTICAL_GUIDELINE)
                    guidelinePercent(0.3f)
                }

                button {
                    constraintId(btnId)
                    size(WRAP, WRAP)
                    text("Button")
                    margin(dip(16))

                    leftConstraintToRight(guidelineId)
                    topConstraintToBottom(guidelineTitle)

                    onClick {  v ->
                        Toast.makeText(v.context, "Hello, world!", Toast.LENGTH_SHORT).show()
                    }
                }

                textView {
                    constraintId(circleTitle)
                    text("Circle")
                    textSize(sip(24f))

                    leftConstraintToParent()
                    topConstraintToBottom(btnId)
                }

                textView {
                    constraintId(txtId)
                    text("Text")
                    size(WRAP, WRAP)
                    backgroundResource(R.color.colorAccent)

                    rightConstraintToParent()
                    bottomConstraintToParent()
                    leftConstraintToParent()
                    topConstraintToBottom(circleTitle)
                    circleConstraint(angle0, 0, 200)
                    circleConstraint(angle45, 45, 200)
                    circleConstraint(angle90, 90, 200)
                    circleConstraint(angle135, 135, 200)
                    circleConstraint(angle180, 180, 200)
                    circleConstraint(angle225, 225, 200)
                    circleConstraint(angle270, 270, 200)
                    circleConstraint(angle315, 315, 200)
                    circleConstraint(angle340, 340, 200)
                }

                textView {
                    constraintId(angle0)
                    text("0")
                }
                textView {
                    constraintId(angle45)
                    size(WRAP, WRAP)
                    text("45")
                }
                textView {
                    constraintId(angle90)
                    size(WRAP, WRAP)
                    text("90")
                }
                textView {
                    constraintId(angle135)
                    text("135")
                }
                textView {
                    constraintId(angle180)
                    text("180")
                }
                textView {
                    constraintId(angle225)
                    text("225")
                }
                textView {
                    constraintId(angle270)
                    text("270")
                }
                textView {
                    constraintId(angle315)
                    text("315")
                }
                textView {
                    constraintId(angle340)
                    text("340")
                }

                group {
                    constraintId(groupId)
                    visibility(false)
                    referencedIds(
                        angle225,
                        angle135,
                        angle45,
                        angle315
                    )
                }

                placeholder {
                    constraintId(placeholderId)
                    contentId(angle270)
                    margin(dip(100), 0, 0, dip(100))

                    leftConstraintToParent()
                    bottomConstraintToParent()
                }
            }
        }

    }

    private fun genId(): Int = ViewCompat.generateViewId()
}
