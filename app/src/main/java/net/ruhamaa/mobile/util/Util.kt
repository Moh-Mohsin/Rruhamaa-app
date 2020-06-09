package net.ruhamaa.mobile.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*



@Throws(IOException::class)
fun getCompressed(context: Context, path: String): File {

    val SDF =
        SimpleDateFormat("yyyymmddhhmmss", Locale.getDefault())

    //getting device external cache directory, might not be available on some devices,
    // so our code fall back to internal storage cache directory, which is always available but in smaller quantity
    var cacheDir = context.externalCacheDir
    if (cacheDir == null) //fall back
        cacheDir = context.cacheDir
    val rootDir = cacheDir!!.absolutePath + "/ImageCompressor"
    val root = File(rootDir)
    //Create ImageCompressor folder if it doesnt already exists.
    if (!root.exists()) root.mkdirs()
    //decode and resize the original bitmap from @param path.
    val bitmap: Bitmap = decodeImageFromFiles(
        path,  /* your desired width*/
        300,  /*your desired height*/
        300
    )!!
    //create placeholder for the compressed image file
    val compressed = File(
        root,
        SDF.format(Date()) + ".jpg" /*Your desired format*/
    )
    //convert the decoded bitmap to stream
    val byteArrayOutputStream = ByteArrayOutputStream()
    /*compress bitmap into byteArrayOutputStream
        Bitmap.compress(Format, Quality, OutputStream)
        Where Quality ranges from 1–100.
        */bitmap.compress(Bitmap.CompressFormat.JPEG, 80, byteArrayOutputStream)
    /*
        Right now, we have our bitmap inside byteArrayOutputStream Object, all we need next is to write it to the compressed file we created earlier,
        java.io.FileOutputStream can help us do just That!
        */
    val fileOutputStream = FileOutputStream(compressed)
    fileOutputStream.write(byteArrayOutputStream.toByteArray())
    fileOutputStream.flush()
    fileOutputStream.close()
    //File written, return to the caller. Done!
    return compressed
}
fun decodeImageFromFiles(path: String?, width: Int, height: Int): Bitmap? {
    val scaleOptions = BitmapFactory.Options()
    scaleOptions.inJustDecodeBounds = true
    BitmapFactory.decodeFile(path, scaleOptions)
    var scale = 1
    while (scaleOptions.outWidth / scale / 2 >= width
        && scaleOptions.outHeight / scale / 2 >= height
    ) {
        scale *= 2
    }
    // decode with the sample size
    val outOptions = BitmapFactory.Options()
    outOptions.inSampleSize = scale
    return BitmapFactory.decodeFile(path, outOptions)
}