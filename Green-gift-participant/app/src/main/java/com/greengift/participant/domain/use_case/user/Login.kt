package com.greengift.participant.domain.use_case.user

import com.greengift.participant.data.dto.LoginDTO
import com.greengift.participant.data.util.ApiUtils
import com.greengift.participant.data.util.GreenError
import com.greengift.participant.domain.repository.UserRepository
import com.greengift.participant.util.Resource
import io.ktor.client.call.body
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class Login @Inject constructor(
    private val repository: UserRepository
){
    operator fun invoke(loginDTO: LoginDTO): Flow<Resource<String>> = flow {
        try {
            emit(Resource.Loading())
            val r = repository.login(loginDTO)
            val r2 = r.body<ApiUtils.ApiResult<String>>()
            when(r2.success) {
                true -> { emit(Resource.Success(r.headers["Authorization"])) }
                false -> { emit(Resource.Error(r2.error?.message ?: "예상하지 못한 에러입니다."))}
            }
        } catch(e: Exception){
            val error = GreenError().getErrorMessage(e)
            emit(Resource.Error(error))
        }
    }
}