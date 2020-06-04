package android.igorkostenko.applicasterapp.view.ui.details

import android.content.Context
import android.content.Intent
import android.igorkostenko.applicasterapp.R
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_web_view.*

class WebViewActivity : AppCompatActivity() {
    companion object {
        private const val hrefId = "hrefId"
        fun createIntent(
            context: Context,
            href: String
        ): Intent {
            val intent = Intent(context, WebViewActivity::class.java)
            return intent.putExtra(hrefId, href)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)
        val link = intent.getStringExtra(hrefId)
        webView.loadUrl(link)
    }
}
