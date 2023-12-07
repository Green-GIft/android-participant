package com.greengift.participant.domain.use_case.user

import com.greengift.participant.data.dto.SignupDTO
import com.greengift.participant.data.util.GreenError
import com.greengift.participant.domain.repository.UserRepository
import com.greengift.participant.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SignUp @Inject constructor(
    private val repository: UserRepository
){
    operator fun invoke(signupDTO: SignupDTO): Flow<Resource<String>> = flow {
        try {
            emit(Resource.Loading())
            val r = repository.signup(signupDTO)
            when(r.success) {
                true -> { emit(Resource.Success(null)) }
                false -> { emit(Resource.Error(r.error?.message ?: "예상하지 못한 에러입니다."))}
            }
        } catch(e: Exception){
            val error = GreenError().getErrorMessage(e)
            emit(Resource.Error(error))
        }
    }
}