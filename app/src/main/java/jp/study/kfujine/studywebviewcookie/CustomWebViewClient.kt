package jp.study.kfujine.studywebviewcookie

import android.graphics.Bitmap
import android.webkit.CookieManager
import android.webkit.WebView
import android.webkit.WebViewClient

/**
 * Created by fuji on 2017/05/21.
 */

class CustomWebViewClient : WebViewClient() {

    override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
        super.onPageStarted(view, url, favicon)
    }

    override fun onPageFinished(view: WebView?, url: String?) {
        super.onPageFinished(view, url)
    }

    fun setCookie(cookieValue:String) {
        val cm = CookieManager.getInstance()
        cm.setAcceptCookie(true)
        cm.setCookie(Consts.DOMAIN, cookieValue)
    }

    fun getCookie():String? {
        val cm = CookieManager.getInstance()
        cm.setAcceptCookie(true)

        return cm.getCookie(Consts.DOMAIN)
    }
}
