# 随便混混
-keep class com.nwhhades.**{*;}
-dontwarn com.nwhhades.**

#EasyHttp
# OkHttp3
-keepattributes Signature
-keepattributes *Annotation*
-keep class okhttp3.** {*;}
-keep interface okhttp3.** {*;}
-dontwarn okhttp3.**
-dontwarn okio.**