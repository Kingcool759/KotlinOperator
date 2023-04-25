/*
 * Copyright (c) 2017 LingoChamp Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.ko.activity

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.os.FileUtils
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.annotation.NonNull
import com.example.ko.R
import com.example.ko.logD
import com.liulishuo.okdownload.DownloadListener
import com.liulishuo.okdownload.DownloadTask
import com.liulishuo.okdownload.StatusUtil
import com.liulishuo.okdownload.core.breakpoint.BreakpointInfo
import com.liulishuo.okdownload.core.cause.EndCause
import com.liulishuo.okdownload.core.cause.ResumeFailedCause
import com.liulishuo.okdownload.core.listener.DownloadListener1
import com.liulishuo.okdownload.core.listener.assist.Listener1Assist
import java.io.File
import java.io.FileInputStream
import java.io.InputStream
import java.security.MessageDigest

/**
 * On this demo you can see the simplest way to download a task.
 */
class SingleActivity : BaseSampleActivity() {

    private var task: DownloadTask? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single)
        initSingleDownload(
            findViewById<View>(R.id.statusTv) as TextView,
            findViewById<View>(R.id.progressBar) as ProgressBar,
            findViewById(R.id.actionView),
            findViewById<View>(R.id.actionTv) as TextView
        )
    }

    override fun titleRes(): Int = R.string.single_download_title

    override fun onDestroy() {
        super.onDestroy()
        task?.cancel()
    }

    private fun initSingleDownload(
        statusTv: TextView,
        progressBar: ProgressBar,
        actionView: View,
        actionTv: TextView
    ) {
        initTask()
        initStatus(statusTv, progressBar)
        initAction(actionView, actionTv, statusTv, progressBar)
    }

    private fun initTask() {
        val filename = "single-test"
        val url = "https://cdn.llscdn.com/yy/files/xs8qmxn8-lls-LLS-5.8-800-20171207-111607.apk"
        val parentFile = DemoUtil.getParentFile(this)
        task = DownloadTask.Builder(url, parentFile)
            .setFilename(filename)
            .setFilenameFromResponse(false)//是否使用 response header or url path 作为文件名，此时会忽略指定的文件名，默认false
            .setPassIfAlreadyCompleted(true)//如果文件已经下载完成，再次下载时，是否忽略下载，默认为true(忽略)，设为false会从头下载
            .setConnectionCount(1)  //需要用几个线程来下载文件，默认根据文件大小确定；如果文件已经 split block，则设置后无效
            .setPreAllocateLength(false) //在获取资源长度后，设置是否需要为文件预分配长度，默认false
            .setMinIntervalMillisCallbackProcess(300) //通知调用者的频率，避免anr，默认3000
            .setWifiRequired(false)//是否只允许wifi下载，默认为false
            .setAutoCallbackToUIThread(false) //是否在主线程通知调用者，默认为true
            //.setHeaderMapFields(new HashMap<String, List<String>>())//设置请求头
            //.addHeader(String key, String value)//追加请求头
            .setPriority(0)//设置优先级，默认值是0，值越大下载优先级越高
            .setReadBufferSize(4096)//设置读取缓存区大小，默认4096
            .setFlushBufferSize(16384)//设置写入缓存区大小，默认16384
            .setSyncBufferSize(65536)//写入到文件的缓冲区大小，默认65536
            .setSyncBufferIntervalMillis(2000) //写入文件的最小时间间隔，默认2000
            .build()
    }

    private fun initStatus(statusTv: TextView, progressBar: ProgressBar) = task?.let {
        val status = StatusUtil.getStatus(it)
        if (status == StatusUtil.Status.COMPLETED) {
            progressBar.progress = progressBar.max
        }
        statusTv.text = status.toString()
        StatusUtil.getCurrentInfo(it)?.let { info ->
            Log.d(TAG, "init status with: $info")
            DemoUtil.calcProgressToView(progressBar, info.totalOffset, info.totalLength)
        }
    }

    private fun initAction(
        actionView: View,
        actionTv: TextView,
        statusTv: TextView,
        progressBar: ProgressBar
    ) {
        actionTv.setText(R.string.start)
        actionView.setOnClickListener {
            logD(TAG, "task: $task, task.tag: ${task?.tag}")
            task?.let {
                if (it.tag != null) {
                    // to cancel
                    it.cancel()
                } else {
                    if (StatusUtil.getStatus(it) == StatusUtil.Status.COMPLETED) {
                        com.blankj.utilcode.util.FileUtils.delete(DemoUtil.getParentFile(this))
                    }
                    // to start
                    actionTv.setText(R.string.cancel)
                    startTask(statusTv, progressBar, actionTv)
                    it.tag = "mark-task-started"
                }
            }
        }
    }

    private fun startTask(
        statusTv: TextView,
        progressBar: ProgressBar,
        actionTv: TextView
    ) {
        val listener = object : DownloadListener1() {
            override fun taskStart(task: DownloadTask, model: Listener1Assist.Listener1Model) {
                logD(TAG, "taskStart# ")
                statusTv.setText(R.string.task_start)
                actionTv.setText(R.string.cancel) // 点击可取消/暂停
            }

            override fun taskEnd(
                task: DownloadTask,
                cause: EndCause,
                realCause: java.lang.Exception?,
                model: Listener1Assist.Listener1Model
            ) {
                logD(TAG, "taskEnd# cause: $cause, realCause: $realCause ")
                this@SingleActivity.runOnUiThread {
                    task.tag = null
                    if (cause == EndCause.COMPLETED) {
//                    val realMd5 = fileToMD5(task.file!!.absolutePath)
//                    if (!realMd5!!.equals("f836a37a5eee5dec0611ce15a76e8fd5", ignoreCase = true)) {
//                        Log.e(TAG, "file is wrong because of md5 is wrong $realMd5")
//                    }
                        statusTv.setText(R.string.success)
                        actionTv.setText(R.string.restart) // 点击可重新开启
                    } else {
                        actionTv.setText(R.string.start)
                    }
                    statusTv.text = StatusUtil.getStatus(task).toString()
                    realCause?.let {
                        Log.e(TAG, "download error", it)
                        statusTv.setText(R.string.fail)
                    }
                }
            }

            override fun retry(task: DownloadTask, cause: ResumeFailedCause) {
                logD(TAG, "retry# cause: $cause ")
            }

            override fun connected(
                task: DownloadTask,
                blockCount: Int,
                currentOffset: Long,
                totalLength: Long
            ) {
                logD(
                    TAG,
                    "connected# blockCount: $blockCount, currentOffset: $currentOffset,  totalLength: $totalLength"
                )
            }

            override fun progress(task: DownloadTask, currentOffset: Long, totalLength: Long) {
                logD(TAG, "progress# currentOffset: $currentOffset,  totalLength: $totalLength")
                DemoUtil.calcProgressToView(progressBar, currentOffset, totalLength)
            }
        }
        task?.enqueue(listener)
    }

    companion object {

        private const val TAG = "SingleActivity"

        fun fileToMD5(filePath: String): String? {
            var inputStream: InputStream? = null
            try {
                inputStream = FileInputStream(filePath)
                val buffer = ByteArray(1024)
                val digest = MessageDigest.getInstance("MD5")
                var numRead = 0
                while (numRead != -1) {
                    numRead = inputStream.read(buffer)
                    if (numRead > 0) {
                        digest.update(buffer, 0, numRead)
                    }
                }
                val md5Bytes = digest.digest()
                return convertHashToString(md5Bytes)
            } catch (ignored: Exception) {
                return null
            } finally {
                if (inputStream != null) {
                    try {
                        inputStream.close()
                    } catch (e: Exception) {
                        Log.e(TAG, "file to md5 failed", e)
                    }
                }
            }
        }

        @SuppressLint("DefaultLocale")
        private fun convertHashToString(md5Bytes: ByteArray): String = StringBuffer().apply {
            md5Bytes.forEach { byte ->
                append(((byte.toInt() and 0xff) + 0x100).toString(16).substring(1))
            }
        }.toString().toUpperCase()
    }
}

object DemoUtil {
    fun calcProgressToView(progressBar: ProgressBar, offset: Long, total: Long) {
        val percent = offset.toFloat() / total
        progressBar.progress = (percent * progressBar.max).toInt()
    }

    fun getParentFile(@NonNull context: Context): File {
        val externalSaveDir = context.externalCacheDir
        return externalSaveDir ?: context.cacheDir
    }
}