package com.asga.network.helper

/* Created by ibrahim.ali on 9/28/2021 */
data class Label(
        var ar: String = "",
        var en: String = "",
        var fr: String = ""
)

open class Labels {

    companion object {
        val someThingWrong = Label("حدث خطأ", "some thing wrong")
        val noInternet = Label("لا يوجد اتصال بالانترنت", "no internet connection", "no internet connection")
        val badRequest = Label("اقتراح غير جيد", "Bad Request", "Bad Request")
        val Unauthorized = Label("غير مصرح بالدخول", "Unauthorized", "Unauthorized")
        val Forbidden = Label("ممنوع الدخول", "Forbidden", "Forbidden")
        val Not_Found = Label("غير موجود", "Not Found", "NotFound")
        val MethodNotAllow = Label("طريقة غير مسموح بها", "Method Not Allow", "Method Not Allow")
        val Not_Acceptable = Label("غير مقبول", "Not Acceptable", "Not Acceptable")
        val Precondition_Failed = Label("فشل الشرط المسبق", "Precondition Failed", "Precondition Failed")
        val Unsupported_Media_Type = Label("نوع وسائط غير مدعوم", "Unsupported Media Type", "Unsupported Media Type")
        val Internal_Server_Error = Label("خطأ في الخادم الداخلي", "Internal Server Error", "Internal Server Error")
    }
}
