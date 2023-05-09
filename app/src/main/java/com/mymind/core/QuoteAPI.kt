package com.mymind.core

import com.mymind.core.base.BaseHttpRequest

class QuoteAPI : BaseHttpRequest() {

    override val apikey: String = "6Vb6UQwnXAr2clEVCVLCNw==apexCaZIkYh9yrsn"
    override val url: String = "https://api.api-ninjas.com/v1/quotes?category=inspirational"
    override val gettingFieldName: String = "quote"
}