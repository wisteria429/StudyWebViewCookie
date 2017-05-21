package jp.study.kfujine.studywebviewcookie

import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.MenuItem
import android.webkit.WebView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    val webView: WebView by lazy {
        //webView変数に最初にアクセスするときに呼ばれる
        findViewById(R.id.web) as WebView
    }

    val toolbar: Toolbar by lazy {
        findViewById(R.id.toolbar) as Toolbar
    }
    val webViewClient = CustomWebViewClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WebView.setWebContentsDebuggingEnabled(true);
        }

        webView.setWebViewClient(webViewClient)
        webView.settings.javaScriptEnabled = true
        //${}を利用することで文字列内に変数等を入れれる
        webView.loadUrl("https://${Consts.DOMAIN}/index.html")

        toolbar.inflateMenu(R.menu.menu_main)
        toolbar.setOnMenuItemClickListener { menuItem: MenuItem? ->

            when (menuItem?.itemId) {
                R.id.action_reload -> reloadAndSetCookie()
                R.id.action_get_cookie -> showCookie()
                else -> {}

            }

            //OnMenuItemClickListenerのreturn値。
            // TODO もっと良い方法ない？
            false

        }
    }

    /**
     * Cookieをセット
     */
    private fun reloadAndSetCookie() {
        Log.d("テスト", "reload")
        webViewClient.setCookie("test=${System.currentTimeMillis()}")
        webView.reload()
    }

    private fun showCookie() {
        val cookie:String? = webViewClient.getCookie()
        Toast.makeText(this, cookie, Toast.LENGTH_SHORT).show()

    }

}
