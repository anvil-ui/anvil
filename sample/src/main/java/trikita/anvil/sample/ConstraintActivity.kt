package trikita.anvil.sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.Barrier
import androidx.constraintlayout.widget.ConstraintSet
import androidx.constraintlayout.widget.ConstraintSet.VERTICAL_GUIDELINE
import androidx.core.view.ViewCompat
import trikita.anvil.BaseDSL.*
import trikita.anvil.DSL.backgroundResource
import trikita.anvil.DSL.button
import trikita.anvil.DSL.id
import trikita.anvil.DSL.text
import trikita.anvil.DSL.textView
import trikita.anvil.RenderableView
import trikita.anvil.constraint.layout.ConstraintDSL
import trikita.anvil.constraint.layout.ConstraintDSL.barrier
import trikita.anvil.constraint.layout.ConstraintDSL.bottomConstraintToParent
import trikita.anvil.constraint.layout.ConstraintDSL.chain
import trikita.anvil.constraint.layout.ConstraintDSL.circleConstraint
import trikita.anvil.constraint.layout.ConstraintDSL.constraintLayout
import trikita.anvil.constraint.layout.ConstraintDSL.contentId
import trikita.anvil.constraint.layout.ConstraintDSL.group
import trikita.anvil.constraint.layout.ConstraintDSL.guideline
import trikita.anvil.constraint.layout.ConstraintDSL.leftConstraintToParent
import trikita.anvil.constraint.layout.ConstraintDSL.leftConstraintToRight
import trikita.anvil.constraint.layout.ConstraintDSL.placeholder
import trikita.anvil.constraint.layout.ConstraintDSL.rightConstraintToLeft
import trikita.anvil.constraint.layout.ConstraintDSL.rightConstraintToParent
import trikita.anvil.constraint.layout.ConstraintDSL.topConstraintToBottom
import trikita.anvil.constraint.layout.ConstraintDSL.topConstraintToParent


class ConstraintActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val chainTitle = genId()
        val barrierTitle = genId()
        val guidelineTitle = genId()
        val circleTitle = genId()
        val guidelineId = genId()
        val txtId = genId()
        val btnId = genId()
        val layoutId = genId()
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

        setContentView(object : RenderableView(this) {
            override fun view() {
                constraintLayout {
                    id(layoutId)
                    size(MATCH, MATCH)
                    padding(dip(8))

                    textView {
                        ConstraintDSL.id(chainTitle)
                        text("Chain")
                        textSize(sip(24f))
                        leftConstraintToParent()
                        topConstraintToParent()
                    }

                    textView {
                        ConstraintDSL.id(txt1Id)
                        size(WRAP, WRAP)
                        text("Text1")

                        topConstraintToBottom(chainTitle)
                        leftConstraintToParent()
                        rightConstraintToLeft(txt2Id)
                        chain(
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
                        ConstraintDSL.id(txt2Id)
                        size(WRAP, WRAP)
                        text("Text2")

                        topConstraintToBottom(chainTitle)
                    }
                    textView {
                        ConstraintDSL.id(txt3Id)
                        size(WRAP, WRAP)
                        text("Text3")

                        topConstraintToBottom(chainTitle)
                    }

                    textView {
                        ConstraintDSL.id(barrierTitle)
                        text("Barrier")
                        textSize(sip(24f))

                        leftConstraintToParent()
                        topConstraintToBottom(txt1Id)
                    }
                    textView {
                        ConstraintDSL.id(txt4Id)
                        size(WRAP, WRAP)
                        text("Text4444444444")


                        topConstraintToBottom(barrierTitle)
                        leftConstraintToParent()
                    }

                    textView {
                        ConstraintDSL.id(txt5Id)
                        size(WRAP, WRAP)
                        text("Text5")

                        topConstraintToBottom(txt4Id)
                        leftConstraintToParent()
                    }

                    barrier {
                        ConstraintDSL.id(barrierId)
                        ConstraintDSL.type(Barrier.END)
                        ConstraintDSL.referencedIds(intArrayOf(txt4Id, txt5Id))
                    }

                    textView {
                        ConstraintDSL.id(txt6Id)
                        size(WRAP, WRAP)
                        text("Text6")

                        topConstraintToBottom(txt5Id)
                        leftConstraintToRight(barrierId)
                    }

                    textView {
                        ConstraintDSL.id(guidelineTitle)
                        text("Guideline")
                        textSize(sip(24f))

                        leftConstraintToParent()
                        topConstraintToBottom(txt6Id)
                    }

                    guideline {
                        ConstraintDSL.id(guidelineId)
                        ConstraintDSL.orientation(VERTICAL_GUIDELINE)
                        ConstraintDSL.guidelinePercent(0.3f)
                    }

                    button {
                        ConstraintDSL.id(btnId)
                        size(WRAP, WRAP)
                        text("Button")
                        margin(dip(16))

                        leftConstraintToRight(guidelineId)
                        topConstraintToBottom(guidelineTitle)
                    }

                    textView {
                        ConstraintDSL.id(circleTitle)
                        text("Circle")
                        textSize(sip(24f))

                        leftConstraintToParent()
                        topConstraintToBottom(btnId)
                    }

                    textView {
                        ConstraintDSL.id(txtId)
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
                        ConstraintDSL.id(angle0)
                        text("0")
                    }
                    textView {
                        ConstraintDSL.id(angle45)
                        size(WRAP, WRAP)
                        text("45")
                    }
                    textView {
                        ConstraintDSL.id(angle90)
                        size(WRAP, WRAP)
                        text("90")
                    }
                    textView {
                        ConstraintDSL.id(angle135)
                        text("135")
                    }
                    textView {
                        ConstraintDSL.id(angle180)
                        text("180")
                    }
                    textView {
                        ConstraintDSL.id(angle225)
                        text("225")
                    }
                    textView {
                        ConstraintDSL.id(angle270)
                        text("270")
                    }
                    textView {
                        ConstraintDSL.id(angle315)
                        text("315")
                    }
                    textView {
                        ConstraintDSL.id(angle340)
                        text("340")
                    }

                    group {
                        ConstraintDSL.id(groupId)
                        visibility(false)
                        ConstraintDSL.referencedIds(
                            intArrayOf(
                                angle225,
                                angle135,
                                angle45,
                                angle315
                            )
                        )
                    }

                    placeholder {
                        ConstraintDSL.id(placeholderId)
                        contentId(angle270)
                        margin(dip(100), 0, 0, dip(100))

                        leftConstraintToParent()
                        bottomConstraintToParent()
                    }
                }
            }
        })

    }

    private fun genId(): Int {
        return ViewCompat.generateViewId()
    }

}
