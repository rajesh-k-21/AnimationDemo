package com.madlab.animationdemo

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var textView: AppCompatTextView

    var objectAnimator: ObjectAnimator? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.textViewHelloWorld)

        buttonFadeIn.setOnClickListener(this)
        buttonFadeOut.setOnClickListener(this)
        buttonBlink.setOnClickListener(this)
        buttonZoomIn.setOnClickListener(this)
        buttonZoomOut.setOnClickListener(this)
        buttonRotate.setOnClickListener(this)
        buttonMove.setOnClickListener(this)
        buttonBounce.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.buttonFadeIn -> {
                objectAnimator?.end()
                AnimatorSet().apply {
                    play(createObjectAnimator(0.5F, "alpha"))
                    duration = 1000
                    start()
                }

                //startAnimation(R.anim.animation_fade_in)
            }
            R.id.buttonFadeOut -> {
                objectAnimator?.end()
                AnimatorSet().apply {
                    play(createObjectAnimator(1.0F, "alpha"))
                    duration = 1000
                    start()
                }

                //startAnimation(R.anim.animation_fade_out)
            }
            R.id.buttonBlink -> {
                objectAnimator?.end()
                AnimatorSet().apply {
                    play(
                        createObjectAnimator(0.0F, "alpha", true)
                    )
                    duration = 1000
                    start()
                }

                //startAnimation(R.anim.animation_blink)
            }
            R.id.buttonZoomIn -> {
                objectAnimator?.end()
                AnimatorSet().apply {
                    playTogether(
                        createObjectAnimator(1.0F, "scaleX"),
                        createObjectAnimator(1.0F, "scaleY")
                    )
                    duration = 1000
                    start()
                }


                //startAnimation(R.anim.animation_zoom_in)
            }
            R.id.buttonZoomOut -> {
                objectAnimator?.end()

                AnimatorSet().apply {
                    playTogether(
                        createObjectAnimator(0.5F, "scaleX"),
                        createObjectAnimator(0.5F, "scaleX")
                    )
                    duration = 1000
                    start()
                }

                //startAnimation(R.anim.animation_zoom_out)
            }
            R.id.buttonRotate -> {
                objectAnimator?.end()

                AnimatorSet().apply {
                    play(createObjectAnimator(360F, "rotation"))
                    duration = 2000
                    start()
                }

                //startAnimation(R.anim.animation_rotate)
            }
            R.id.buttonMove -> {
                objectAnimator?.end()

                AnimatorSet().apply {
                    play(createObjectAnimator(330F, View.TRANSLATION_X.toString()))
                    duration = 1000
                    start()
                }

                //startAnimation(R.anim.animation_move)
            }
            R.id.buttonBounce -> {
                objectAnimator?.end()

                AnimatorSet().apply {
                    playTogether(
                        createObjectAnimator(0.0F, "y"),
                        createObjectAnimator(0.0F, "scaleY")
                    )
                    interpolator = BounceInterpolator()
                    duration = 2000
                    start()
                }

                //startAnimation(R.anim.animation_bounce)
            }
        }
    }

    private fun startAnimation(animId: Int) {
        fun createAnimation(): Animation {
            return AnimationUtils.loadAnimation(applicationContext, animId)
        }
        textViewHelloWorld.startAnimation(createAnimation())
    }

    private fun createObjectAnimator(
        value: Float,
        type: String,
        isBlink: Boolean = false
    ): Animator {
        objectAnimator = ObjectAnimator.ofFloat(textView, type, value)
        if (isBlink) {
            objectAnimator?.interpolator = AccelerateDecelerateInterpolator()
            objectAnimator?.repeatCount = ObjectAnimator.INFINITE
            objectAnimator?.repeatMode = ObjectAnimator.REVERSE
        }
        return objectAnimator!!
    }
}