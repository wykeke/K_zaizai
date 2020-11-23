package com.example.k_zaizai.fragment

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.ResolveInfo
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.media.ExifInterface
import android.net.Uri
import android.os.Build
import android.os.Build.ID
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.FileProvider
import androidx.core.content.contentValuesOf
import androidx.fragment.app.Fragment
import com.example.k_zaizai.R
import com.example.k_zaizai.activity.LoginActivity
import com.example.k_zaizai.activity.UserActivity
import com.example.k_zaizai.adapter.MyApplication
import kotlinx.android.synthetic.main.me_fragment.*
import org.litepal.LitePal
import java.io.File
import java.time.format.ResolverStyle

class Me_Fragment : Fragment() {

    val takePhoto = 1
    val fromAlbum = 2
    lateinit var imageUri: Uri
    lateinit var outputImage: File

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.me_fragment, container, false)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //设置昵称和ID的显示
        val bundle = activity?.intent?.extras
//        val id = bundle?.getLong("ID")
//        val db = LitePal.find(User::class.java,1)
//        Log.d("db", db.toString())
        userID.text = bundle?.getString("user")
        name.text = bundle?.getString("name")


        tx.setOnClickListener {

//            outputImage = File(activity?.externalCacheDir,"output_image.jpg")
//            if (outputImage.exists()){
//                outputImage.delete()
//            }
//            outputImage.createNewFile()
//            imageUri = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
//                FileProvider.getUriForFile(MyApplication.mcontext,"com.example.cameraalbumtest.filrprovider",outputImage)
//            }else{
//                Uri.fromFile(outputImage)
//            }
//
//            //启动相机程序
//            val intent = Intent("android.media.action.IMAGE_CAPTURE")
//            intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri)
//            startActivityForResult(intent,takePhoto)

            // 打开文件选择器
            val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
            intent.addCategory(Intent.CATEGORY_OPENABLE)
            // 指定只显示图片
            intent.type = "image/*"
            startActivityForResult(intent, fromAlbum)

        }

        //设置个人资料界面
        User.setOnClickListener {
            val intent = Intent(activity,LoginActivity::class.java)
            startActivity(intent)
        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            takePhoto -> {
                if (resultCode == Activity.RESULT_OK) {
                    //将拍摄的照片显示出来
                    //val bitmap = BitmapFactory.decodeStream()
                    //tx.setImageBitmap(rotateIfRequired(bitmap))

                }
            }
            fromAlbum -> {
                if (resultCode == Activity.RESULT_OK && data != null) {
                    data.data?.let {
                        val bitmap = getBirmapFromUri(it)
                            tx.setImageBitmap(bitmap)
                    }
                }
            }

        }

    }

    private fun rotateIfRequired(bitmap: Bitmap): Bitmap {
        val exif = ExifInterface(outputImage.path) // 实例化图片信息类
        val orientation = exif.getAttributeInt(
            ExifInterface.TAG_ORIENTATION,
            ExifInterface.ORIENTATION_NORMAL
        ) // 拿取图片的旋转角度,模式为标准顺时针。
        return when (orientation) {
            ExifInterface.ORIENTATION_ROTATE_90 -> rotateBitmap(bitmap, 90)
            ExifInterface.ORIENTATION_ROTATE_180 -> rotateBitmap(bitmap, 180)
            ExifInterface.ORIENTATION_ROTATE_270 -> rotateBitmap(bitmap, 270)
            else -> bitmap
        }
    }

    private fun rotateBitmap(bitmap: Bitmap, degree: Int): Bitmap {
        val matrix = Matrix()  // 矩阵对象
        matrix.postRotate(degree.toFloat()) // 顺时针旋转角度
        val rotatedBitmap = Bitmap.createBitmap(
            bitmap,
            0,
            0,
            bitmap.width,
            bitmap.height,
            matrix,
            true
        ) // 截取图片并且使用矩阵对象旋转图片，同时也截取原图片
        bitmap.recycle() // 将不再需要的Bitmap 对象回收
        return rotatedBitmap
    }

    private fun getBirmapFromUri(uri: Uri) = activity?.contentResolver
        ?.openFileDescriptor(uri, "r")?.use {
            BitmapFactory.decodeFileDescriptor(it.fileDescriptor)
        }


}