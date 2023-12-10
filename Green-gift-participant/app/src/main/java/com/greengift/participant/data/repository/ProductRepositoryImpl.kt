package com.greengift.participant.data.repository

import com.greengift.participant.data.api.ProductApi
import com.greengift.participant.data.dto.ProductAllDTO
import com.greengift.participant.data.dto.ProductParticipantDTO
import com.greengift.participant.data.util.ApiUtils
import com.greengift.participant.domain.repository.ProductRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductRepositoryImpl @Inject constructor(
    private val api: ProductApi
): ProductRepository {
    override suspend fun buyProduct(productId: Long): ApiUtils.ApiResult<String> {
        return api.buyProduct(productId)
    }

    override suspend fun getProductAll(): ApiUtils.ApiResult<List<ProductAllDTO>> {
        return api.getProductAll()
    }

    override suspend fun getProductParticipant(): ApiUtils.ApiResult<List<ProductParticipantDTO>> {
        return api.getProductParticipant()
    }
}