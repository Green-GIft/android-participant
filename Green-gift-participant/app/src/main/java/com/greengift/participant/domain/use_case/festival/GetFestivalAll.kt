package com.greengift.participant.domain.use_case.festival

import com.greengift.participant.data.dto.FestivalAllDTO
import com.greengift.participant.data.util.GreenError
import com.greengift.participant.domain.repository.FestivalRepository
import com.greengift.participant.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetFestivalAll @Inject constructor(
    private val repository: FestivalRepository
) {
    operator fun invoke(): Flow<Resource<List<FestivalAllDTO>>> = flow {
        try {
            emit(Resource.Loading())
            val r = repository.getFestivalAll()
            when(r.success) {
                true -> { emit(Resource.Success(r.response)) }
                false -> { emit(Resource.Error(r.error?.message ?: "GetFestivalAll: 예상하지 못한 에러입니다."))}
            }
        } catch(e: Exception){
            val error = GreenError().getErrorMessage(e)
            emit(Resource.Error(error))
        }
    }
}