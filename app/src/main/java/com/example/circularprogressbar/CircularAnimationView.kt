package com.example.circularprogressbar

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Paint.ANTI_ALIAS_FLAG
import android.graphics.RectF
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import kotlin.math.roundToInt

class CircularAnimationView @JvmOverloads constructor(
    context: Context, attributeSet: AttributeSet? = null, defStyleResult: Int = 0
) : View(context, attributeSet, defStyleResult) {


    private val startingAngle = -90f;
    private val strokeWidthReq = dpToPix(5f).toFloat();

    private val paint = Paint(ANTI_ALIAS_FLAG);
    private val bgPaint = Paint(ANTI_ALIAS_FLAG)
    private val circlePaint = Paint(ANTI_ALIAS_FLAG)

    private val padding = strokeWidthReq + dpToPix(200f).toFloat();

    private val rect =
        RectF(
            strokeWidthReq,
            strokeWidthReq * 2,
            padding,
            padding + strokeWidthReq
        )

    private var blobX = getOriX()
    private var blobY = getOriY()

    private var angle: Float = 0f

    init {
        paint.apply {
            style = Paint.Style.STROKE;
            strokeWidth = strokeWidthReq;
            color = ResourceUtils.getColor(context, R.color.green);
        }

        bgPaint.apply {
            style = Paint.Style.STROKE
            strokeWidth = strokeWidthReq
            color = ResourceUtils.getColor(context, R.color.warm_grey_opacity_54)
        }

        circlePaint.apply {
            style = Paint.Style.FILL
            strokeWidth = strokeWidthReq * 2;
            color = ResourceUtils.getColor(context, R.color.orange_color)
        }
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if (canvas == null) return
        canvas.drawCircle(rect.centerX(), rect.centerY(), rect.width() * 0.5f, bgPaint)
        canvas.drawArc(rect, startingAngle, angle, false, paint);
        canvas.drawCircle(
            blobX,
            blobY,
            strokeWidthReq * 2,
            circlePaint
        )
    }


    fun getAngle(): Float {
        return angle;
    }

    fun setAngle(angle: Float) {
        this.angle = angle;
    }

    private fun dpToPix(dp: Float): Int {
        return try {
            TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dp,
                resources.displayMetrics
            ).roundToInt()
        } catch (e: Exception) {
            dp.roundToInt()
        }
    }

    fun setNewIndicatorCoordinates(newX: Float, newY: Float) {
        this.blobX = newX
        this.blobY = newY
    }

    fun getOriX(): Float {
        return rect.centerX()
    }

    fun getOriY(): Float {
        return rect.centerY() - rect.width() * 0.5f
    }

    fun getRadius(): Float {
        return rect.width() * 0.5f
    }
}