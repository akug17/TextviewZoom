package socialmedia.akash.com.textviewzoom

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.TextView



class MainActivity  : AppCompatActivity(),View.OnTouchListener {
    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
       return false
    }
    val STEP = 200f

    var mRatio = 1.0f
    var mBaseDist: Int = 0
    var mBaseRatio: Float = 0.toFloat()
    var fontsize = 13f
//    val move: Float = 200f
//    var ratio: Float = 0.1f
//    var basedist: Int? = 1
//    var baseratio: Float? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txt_zoom.setTextSize(mRatio + 13);
    }
    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.pointerCount == 2) {
            val action = event.action
            val pureaction = action and MotionEvent.ACTION_MASK
            if (pureaction == MotionEvent.ACTION_POINTER_DOWN) {
                mBaseDist = getDistance(event)
                mBaseRatio = mRatio
            } else {
                val delta = (getDistance(event) - mBaseDist) / STEP
                val multi = Math.pow(2.0, delta.toDouble()).toFloat()
                mRatio = Math.min(1024.0f, Math.max(0.1f, mBaseRatio * multi))
                txt_zoom.setTextSize(mRatio + 13)
            }
        }
        return true
    }

    fun getDistance(event: MotionEvent): Int {
        val dx = (event.getX(0) - event.getX(1)).toInt()
        val dy = (event.getY(0) - event.getY(1)).toInt()
        return Math.sqrt((dx * dx + dy * dy).toDouble()).toInt()
    }


//    override fun onTouchEvent(event: MotionEvent?): Boolean {
//
//        if (event?.pointerCount == 2) {
//
//            var action: Int = event?.action
//            var mainaction: Int = action + MotionEvent.ACTION_MASK
//
//            if (mainaction == MotionEvent.ACTION_POINTER_DOWN) {
//                basedist = getdistance(event)
//
//
//                baseratio = ratio
//            } else {
//                var scale: Float = (getdistance(event)?.minus(basedist!!))!! / move
//                var factor: Float = Math.pow(2.0, scale.toDouble()) as Float
//
//                ratio = Math.min(1024.0f, Math.max(0.1f, baseratio!! * factor))
//
//                txt_zoom.setTextSize(ratio + 15)
//            }
//
//
//        }
//
//
//        return true
//    }
//
//
//    private fun getdistance(event: MotionEvent): Int? {
//var dx :Int = (event.getX(0)-event.getX(1)) as Int
//
//        var dy :Int =(event.getY(0)-event.getY(1)) as Int
//return (Math.sqrt(dx*dx + dy*dy as Double)) as Int
//    }

}