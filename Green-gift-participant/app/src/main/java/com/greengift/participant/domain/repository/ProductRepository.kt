package com.greengift.participant.domain.repository

import com.greengift.participant.data.dto.ProductAllDTO
import com.greengift.participant.data.dto.ProductParticipantDTO
import com.greengift.participant.data.util.ApiUtils

interface ProductRepository {
    suspend fun buyProduct(productId: Long): ApiUtils.ApiResult<String>
    suspend fun getProductAll(): ApiUtils.ApiResult<List<ProductAllDTO>>
    suspend fun getProductParticipant(): ApiUtils.ApiResult<List<ProductParticipantDTO>>
}