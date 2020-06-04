package android.igorkostenko.applicasterapp.view.ui.details

import android.content.Context
import android.content.Intent
import android.igorkostenko.applicasterapp.R
import android.net.Uri
import android.os.Bundle
import android.widget.MediaController
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_video.*

class VideoActivity : AppCompatActivity() {
    companion object {
        private const val hrefId = "hrefId"
        fun createIntent(
            context: Context,
            href: String
        ): Intent {
            val intent = Intent(context, VideoActivity::class.java)
            return intent.putExtra(hrefId, href)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video)
        val link = intent.getStringExtra(hrefId)
        val mediaController = MediaController(this)
        mediaController.setAnchorView(videoView)
        videoView.setMediaController(mediaController)
        videoView.setVideoURI(Uri.parse(link))
        videoView.start()
    }
}
