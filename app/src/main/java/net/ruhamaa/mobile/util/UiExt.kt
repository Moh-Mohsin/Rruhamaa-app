package net.ruhamaa.mobile.util

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import net.ruhamaa.mobile.BuildConfig


fun Context.toast(message: String, length: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, length).show()
}
fun Context.toast(res: Int, length: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, res, length).show()
}
fun Fragment.toast(message: String, length: Int = Toast.LENGTH_SHORT) {
    context?.toast(message, length)
}
fun Fragment.toast(res: Int, length: Int = Toast.LENGTH_SHORT) {
    requireContext().toast(res, length)
}

fun View.toast(message: String, length: Int = Toast.LENGTH_SHORT) {
    context.toast(message, length)
}


fun Context.toastDebug(message: String, length: Int = Toast.LENGTH_SHORT) {
    if (BuildConfig.DEBUG){
        Toast.makeText(this, message, length).show()
        Log.d("toastDebug", message)
    }
}

fun Fragment.toastDebug(message: String, length: Int = Toast.LENGTH_SHORT) {
    if (BuildConfig.DEBUG)
        context?.toast(message, length)
}

