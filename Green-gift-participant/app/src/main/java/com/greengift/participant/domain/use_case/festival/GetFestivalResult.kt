package com.greengift.participant.domain.use_case.festival

import com.greengift.participant.data.dto.FestivalResultDTO
import com.greengift.participant.data.util.GreenError
import com.greengift.participant.domain.repository.FestivalRepository
import com.greengift.participant.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetFestivalResult @Inject constructor(
    private val repository: FestivalRepository
) {
    operator fun invoke(festivalId: Long): Flow<Resource<FestivalResultDTO?>> = flow {
        try {
            emit(Resource.Loading())
            val r = repository.getFestivalResult(festivalId)
            when(r.success) {
                true -> { emit(Resource.Success(r.response)) }
                false -> { emit(Resource.Error(r.error?.message ?: "예상하지 못한 에러입니다."))}
            }
        } catch(e: Exception){
            val error = GreenError().getErrorMessage(e)
            emit(Resource.Error(error))
        }
    }
}