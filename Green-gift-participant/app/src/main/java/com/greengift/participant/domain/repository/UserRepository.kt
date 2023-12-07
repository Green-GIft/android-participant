package com.greengift.participant.domain.repository

import com.greengift.participant.data.dto.GradeDTO
import com.greengift.participant.data.dto.LoginDTO
import com.greengift.participant.data.dto.SignupDTO
import com.greengift.participant.data.util.ApiUtils
import io.ktor.client.statement.HttpResponse

interface UserRepository {
    suspend fun login(loginDTO: LoginDTO): HttpResponse
    suspend fun signup(signupDTO: SignupDTO): ApiUtils.ApiResult<String>
    suspend fun getGrade(): ApiUtils.ApiResult<GradeDTO>
}