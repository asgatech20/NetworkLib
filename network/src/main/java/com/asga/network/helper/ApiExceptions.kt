package com.asga.network.helper

/* Created by ibrahim.ali on 9/28/2021 */

/**
 * Exception when communicating with the remote api. Contains http [statusCode].
 * message using label to localize message
 */
class ApiException(private var lang: Constants.Lang, private val statusCode: Int?) : Exception() {
    override val message: String
        get() {
            val label = when (statusCode) {
                Constants.Bad_Request -> Labels.badRequest
                Constants.Unauthorized -> Labels.Unauthorized
                Constants.Forbidden -> Labels.Forbidden
                Constants.Not_Found -> Labels.Not_Found
                Constants.MethodNotAllow -> Labels.MethodNotAllow
                Constants.Not_Acceptable -> Labels.Not_Acceptable
                Constants.Precondition_Failed -> Labels.Precondition_Failed
                Constants.Unsupported_Media_Type -> Labels.Unsupported_Media_Type
                else -> Labels.Internal_Server_Error
            }
            return getLabel(label, lang)
        }

    private fun getLabel(label: Label, lang: Constants.Lang): String =
        if (lang == Constants.Lang.AR) label.ar else label.en
}

/**
 * Exception indicating that device is not connected to the internet
 */
class NoInternetException(private var lang: Constants.Lang) : Exception() {
    override val message: String
        get() = if (lang == Constants.Lang.AR) Labels.noInternet.ar else Labels.noInternet.en
}

/**
 * Not handled unexpected exception
 */
class UnexpectedException(cause: Exception) : Exception(cause)
