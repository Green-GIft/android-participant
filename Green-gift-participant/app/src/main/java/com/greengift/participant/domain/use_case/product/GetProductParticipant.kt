package com.greengift.participant.domain.use_case.product

import com.greengift.participant.data.dto.ProductParticipantDTO
import com.greengift.participant.data.util.GreenError
import com.greengift.participant.domain.repository.ProductRepository
import com.greengift.participant.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetProductParticipant @Inject constructor(
    private val repository: ProductRepository
) {
    operator fun invoke(): Flow<Resource<List<ProductParticipantDTO>>> = flow {
        try {
            emit(Resource.Loading())
            val r = repository.getProductParticipant()
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