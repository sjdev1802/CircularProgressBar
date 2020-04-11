package com.example.circularprogressbar

import android.view.animation.Animation
import android.view.animation.Transformation
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin


class CircleAngleAnimation(private val circle: CircularAnimationView) : Animation() {
    private val oldAngle: Float = circle.getAngle()
    private var newAngle: Float = 0f

    override fun applyTransformation(
        interpolatedTime: Float,
        transformation: Transformation?
    ) {
        val angle = oldAngle + (newAngle - oldAngle) * interpolatedTime
        circle.setAngle(angle)
        val newX = getNewX(angle)
        val newY = getNewY(angle)
        /*
        * Log.d("angle", "angle = $angle, newX = $newX, newY = $newY")
        * */
        circle.setNewIndicatorCoordinates(newX, newY)
        circle.requestLayout()
    }

    private fun getNewX(angle: Float): Float {
        val oriX = circle.getOriX();
        val radius = circle.getRadius()
        val reqAngle = (angle * PI / 180).toFloat()
        val sinV = sin(reqAngle)
        /*
        * to debug use - Log.d("angle", "oriX = $oriX, reqAngle = $reqAngle, sinReq = $sinV, radius = $radius")
        * */
        return oriX + radius * sin(reqAngle)
    }

    private fun getNewY(angle: Float): Float {
        val oriY = circle.getOriY();
        val radius = circle.getRadius()
        val reqAngle = (angle * PI / 180).toFloat()
        val sinV = cos(reqAngle)
        /*
        * to debug use - Log.d("angle", "oriY = $oriY, reqAngle = $reqAngle, cosReq = $sinV, radius = $radius")
        * */
        return oriY + radius * (1 - cos(reqAngle))
    }

    fun setAngle(angle: Float) {
        this.newAngle = angle
    }
}