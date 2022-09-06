package com.codelab.newsapplication.app

import android.app.Application
import com.codelab.newsapplication.data.NewsRepository
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp // Hilt 코드 생성을 시작
class NewsApplication : Application() {
    override fun onCreate() {
        super.onCreate() // 의존성 주입 이루어짐 (bytecode 변환)
    }
}