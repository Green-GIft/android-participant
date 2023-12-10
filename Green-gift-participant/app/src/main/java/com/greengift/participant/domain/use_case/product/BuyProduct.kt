package com.greengift.participant.domain.use_case.product

import com.greengift.participant.data.util.GreenError
import com.greengift.participant.domain.repository.ProductRepository
import com.greengift.participant.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class BuyProduct @Inject constructor(
    private val repository: ProductRepository
) {
    operator fun invoke(productId: Long): Flow<Resource<String>> = flow {
        try {
            emit(Resource.Loading())
            val r = repository.buyProduct(productId)
            when(r.success) {
                true -> { emit(Resource.Success(r.response)) }
                false -> { emit(Resource.Error(r.error?.message ?: "예상하지 못한 에러입니다."))}
            }
        } catch (e: Exception){
            val error = GreenError().getErrorMessage(e)
            emit(Resource.Error(error))
        }
    }
}