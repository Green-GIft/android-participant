package com.greengift.participant.data.api

import com.greengift.participant.data.dto.ProductAllDTO
import com.greengift.participant.data.dto.ProductParticipantDTO
import com.greengift.participant.data.util.ApiUtils
import com.greengift.participant.data.util.HttpRoutes
import com.greengift.participant.domain.repository.ProductRepository
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post

class ProductApi (
    private val client: HttpClient
): ProductRepository {
    override suspend fun buyProduct(productId: Long): ApiUtils.ApiResult<String> {
        return client.post(HttpRoutes.PRODUCT_BUY + "/$productId").body()
    }

    override suspend fun getProductAll(): ApiUtils.ApiResult<List<ProductAllDTO>> {
        return client.get(HttpRoutes.PRODUCT_ALL).body()
    }

    override suspend fun getProductParticipant(): ApiUtils.ApiResult<List<ProductParticipantDTO>> {
        return client.get(HttpRoutes.PRODUCT_PARTICIPANT).body()
    }
}