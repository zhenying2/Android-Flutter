package com.example.gonggu.utils

object Constants {
    const val TAG : String = "로그"
}

enum class SEARCH_TYPE {
    USER
}

enum class RESPONSE_STATE {
    OKAY,
    FAIL
}

object API {
    const val BASE_URL : String = "https://toyproject-sook.herokuapp.com/"

    const val SEARCH_USERS : String = "api/v1/(로그인)"
    const val POST_USERS : String = "api/v1/user"
}