package com.example.videoplayer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.PermissionRequest
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.single.PermissionListener
import java.io.File

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var fileList: List<File>
    private lateinit var customAdapter: CustomAdapter

    val path = File(System.getenv("EXTERNAL_STORAGE"))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        askPermission()
    }

    private fun askPermission() {

        val permissionListener = object : PermissionListener {
            override fun onPermissionGranted(response: PermissionGrantedResponse?) {
                displayFiles()
            }

            override fun onPermissionDenied(response: PermissionDeniedResponse?) {
                Toast.makeText(
                    this@MainActivity,
                    "Storage Permission is Required!!",
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun onPermissionRationaleShouldBeShown(
                p0: com.karumi.dexter.listener.PermissionRequest?,
                token: PermissionToken?
            ) {

                token?.continuePermissionRequest()
            }
        }
        Dexter.withContext(this)
            .withPermission(Manifest.permission.DYNAMIC_RECEIVER_NOT_EXPORTED_PERMISSION)
            .withListener(permissionListener)
            .check()


    }

    private fun displayFiles() {
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        fileList = ArrayList()
        fileList.addAll(findVideos(path))
    }

    private fun findVideos(file: File): ArrayList<File> {
        val myVideos = ArrayList<File>()
        val allFiles = file.listFiles()

        for (singleFile: File in allFiles) {
            if (singleFile.isDirectory && !singleFile.isHidden) {
                myVideos.addAll(findVideos(singleFile));
            }
            else
        }
        return
    }
}