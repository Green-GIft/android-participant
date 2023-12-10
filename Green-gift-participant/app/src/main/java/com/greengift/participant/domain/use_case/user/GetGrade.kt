package com.greengift.participant.domain.use_case.user

import com.greengift.participant.data.dto.GradeDTO
import com.greengift.participant.data.util.GreenError
import com.greengift.participant.domain.repository.UserRepository
import com.greengift.participant.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetGrade @Inject constructor(
    private val repository: UserRepository
){
    operator fun invoke(): Flow<Resource<GradeDTO>> = flow {
        try {
            emit(Resource.Loading())
            val r = repository.getGrade()
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