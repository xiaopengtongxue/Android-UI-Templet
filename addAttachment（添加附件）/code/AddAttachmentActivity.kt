package com.xiapeng.testwidget

import android.app.AlertDialog
import android.content.ContentUris
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.util.Log
import android.view.Gravity
import android.view.View
import kotlinx.android.synthetic.main.activity_add_attachment.*
import java.io.File

class AddAttachmentActivity : AppCompatActivity() ,View.OnClickListener{
    private var isOneFill=false
    private var isTwoFill=false
    private var isThreeFill=false
    private var  oneImagePath=""
    private var  twoImagePath=""
    private var  threeImagePath=""
    private var  allPath=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_attachment)

        oneImage!!.setOnClickListener(this)
        twoImage!!.setOnClickListener(this)
        threeImage!!.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if(v!!.id==R.id.oneImage){
            if(isOneFill){
                deleteImage(1)
            }else{
                addImage(1)
            }
        }else if(v!!.id==R.id.twoImage){
            if(isTwoFill){
                deleteImage(2)
            }else{
                addImage(2)
            }
        }else if(v!!.id==R.id.threeImage){
            if(isThreeFill){
                deleteImage(3)
            }else{
                addImage(3)
            }
        }
    }

    private fun addImage(id:Int) {
        var intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.addCategory(Intent.CATEGORY_OPENABLE)
        intent.setType("*/*")
        startActivityForResult(intent, id)
    }

    private fun deleteImage(id:Int){
        if (id==1){
            if(isThreeFill&&isTwoFill){
                isThreeFill=false
                setImage(android.R.drawable.ic_delete,android.R.drawable.ic_delete,android.R.drawable.ic_input_add)
                setImageVisible(View.VISIBLE,View.VISIBLE,View.VISIBLE)
                threeImagePath=""
            } else if(!isThreeFill&&isTwoFill){
                isTwoFill=false
                setImage(android.R.drawable.ic_delete,android.R.drawable.ic_input_add,android.R.drawable.ic_input_add)
                setImageVisible(View.VISIBLE,View.VISIBLE,View.GONE)
                twoImagePath=""
            }else{
                isOneFill=false
                setImage(android.R.drawable.ic_input_add,android.R.drawable.ic_input_add,android.R.drawable.ic_input_add)
                setImageVisible(View.VISIBLE,View.GONE,View.GONE)
                oneImagePath=""
            }

        } else if (id==2){
            if (isThreeFill){
                isThreeFill=false
                setImage(android.R.drawable.ic_delete,android.R.drawable.ic_delete,android.R.drawable.ic_input_add)
                setImageVisible(View.VISIBLE,View.VISIBLE,View.VISIBLE)
                threeImagePath=""
            }else{
                isTwoFill=false
                setImage(android.R.drawable.ic_delete,android.R.drawable.ic_input_add,android.R.drawable.ic_input_add)
                setImageVisible(View.VISIBLE,View.VISIBLE,View.GONE)
                twoImagePath=""
            }
        } else if (id==3){
            isThreeFill=false
            setImage(android.R.drawable.ic_delete,android.R.drawable.ic_delete,android.R.drawable.ic_input_add)
            setImageVisible(View.VISIBLE,View.VISIBLE,View.VISIBLE)
            threeImagePath=""
        }
    }

    private fun setImage(one:Int,two:Int,three:Int){
        threeImage!!.setImageResource(three)
        twoImage!!.setImageResource(two)
        oneImage!!.setImageResource(one)
    }

    private fun setImageVisible(one:Int,two:Int,three:Int){
        threeImage!!.visibility=three
        twoImage!!.visibility=two
        oneImage!!.visibility=one
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        var uri = data!!.getData()
        if (uri != null) {
            var path=getPath(this,uri)
            if(!path.isEmpty()){
                var file= File(path)
                if(file.length()>5*1024*1024){
                    showDialog()
                    return
                }
            }else{
                return
            }
            Log.d("Path1", path)
            Log.d("Path2", uri.path)
            Log.d("Path3", uri.scheme)
            Log.d("Path4", uri.authority)
            if(requestCode==1) {
                isOneFill=true
                oneImage!!.setImageResource(android.R.drawable.ic_delete)
                twoImage!!.visibility = View.VISIBLE
                oneImagePath=path
            }else if (requestCode==2){
                isTwoFill=true
                twoImage!!.setImageResource(android.R.drawable.ic_delete)
                threeImage!!.visibility=View.VISIBLE
                twoImagePath=path
            }else if(requestCode==3){
                isThreeFill=true
                threeImage!!.setImageResource(android.R.drawable.ic_delete)
                threeImagePath=path
            }
            allPath=oneImagePath+";"+twoImagePath+";"+threeImagePath
            textView!!.setText(allPath)
        }
    }

    fun getPath(context: Context, uri: Uri): String {

        // DocumentProvider
        if (DocumentsContract.isDocumentUri(context, uri)) {
            // ExternalStorageProvider
            if (isExternalStorageDocument(uri)) {
                var docId = DocumentsContract.getDocumentId(uri);
                var split = docId.split(":");
                var type = split[0];

                if ("primary".equals(type)) {
                    return Environment.getExternalStorageDirectory().path + "/" + split[1];
                }
            }
            // DownloadsProvider
            else if (isDownloadsDocument(uri)) {

                var id = DocumentsContract.getDocumentId(uri);
                var contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), id.toLong());

                return getDataColumn(context, contentUri, null, null)!!;
            }
            // MediaProvider
            else if (isMediaDocument(uri)) {
                var docId = DocumentsContract.getDocumentId(uri);
                var split = docId.split(":");
                var type = split[0];

                var contentUri: Uri? = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }

                var selection = "_id=?";
                var selectionArgs = arrayOf(split[1]);

                return getDataColumn(context, contentUri!!, selection, selectionArgs)!!;
            }
        }
        // MediaStore (and general)
        else if ("content".equals(uri.getScheme())) {
            return getDataColumn(context, uri, null, null)!!;
        }
        // File
        else if ("file".equals(uri.getScheme())) {
            return uri.getPath();
        }
        return null.toString();
    }

    fun getDataColumn(context: Context, uri: Uri, selection: String?, selectionArgs: Array<String>?): String? {
        var cursor: Cursor? = null
        val column = "_data"
        val projection = arrayOf(column)

        try {
            cursor = context.contentResolver.query(uri, projection, selection, selectionArgs, null)
            if (cursor != null && cursor.moveToFirst()) {
                val column_index = cursor.getColumnIndexOrThrow(column)
                return cursor.getString(column_index)
            }
        } finally {
            if (cursor != null)
                cursor.close()
        }
        return null
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is ExternalStorageProvider.
     */
    fun isExternalStorageDocument(uri: Uri): Boolean {
        return "com.android.externalstorage.documents" == uri.authority
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    fun isDownloadsDocument(uri: Uri): Boolean {
        return "com.android.providers.downloads.documents" == uri.authority
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    fun isMediaDocument(uri: Uri): Boolean {
        return "com.android.providers.media.documents" == uri.authority
    }

    private fun showDialog(){
        var dialog= AlertDialog.Builder(this,R.style.MyDialog)
                .setMessage("文件大于5M，请从新选择")
                .setPositiveButton("知道了",object : DialogInterface.OnClickListener{
                    override fun onClick(dialog: DialogInterface?, which: Int) {

                    }
                })
                .create()
        dialog.window.setGravity(Gravity.BOTTOM)
        dialog.show()
    }
}
