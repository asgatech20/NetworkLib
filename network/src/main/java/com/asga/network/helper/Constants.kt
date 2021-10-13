package com.asga.network.helper

/* Created by ibrahim.ali on 9/28/2021 */
object Constants {
    const val REQUEST_TIMEOUT: Long = 60

    var Bad_Request = 400
    var Unauthorized = 401
    var Forbidden = 402
    var Not_Found = 404
    var MethodNotAllow = 405
    var Not_Acceptable = 406
    var Precondition_Failed = 412
    var Unsupported_Media_Type = 415
    var Internal_Server_Error = 500

    enum class Lang {

        AR("ar"), EN("en"), FR("fr");

        var value: String? = null

        constructor(value: String) {
            this.value = value
        }
    }
}