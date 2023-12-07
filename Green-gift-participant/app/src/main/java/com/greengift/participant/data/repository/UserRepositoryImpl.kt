package com.greengift.participant.data.repository

import com.greengift.participant.data.api.UserApi
import com.greengift.participant.data.dto.GradeDTO
import com.greengift.participant.data.dto.LoginDTO
import com.greengift.participant.data.dto.SignupDTO
import com.greengift.participant.data.util.ApiUtils
import com.greengift.participant.domain.repository.UserRepository
import io.ktor.client.statement.HttpResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepositoryImpl @Inject constructor(
    private val api: UserApi
): UserRepository {
    override suspend fun login(loginDTO: LoginDTO): HttpResponse {
        return api.login(loginDTO)
    }

    override suspend fun signup(signupDTO: SignupDTO): ApiUtils.ApiResult<String>{
        return api.signup(signupDTO)
    }
    override suspend fun getGrade(): ApiUtils.ApiResult<GradeDTO>{
        return api.getGrade()
    }
}