package com.greengift.participant.data.api

import com.greengift.participant.data.dto.GradeDTO
import com.greengift.participant.data.dto.LoginDTO
import com.greengift.participant.data.dto.SignupDTO
import com.greengift.participant.data.util.ApiUtils
import com.greengift.participant.domain.repository.UserRepository
import com.greengift.participant.data.util.HttpRoutes
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse

class UserApi(
    private val client: HttpClient
): UserRepository {
    override suspend fun login(loginDTO: LoginDTO): HttpResponse {
        return client.post(HttpRoutes.LOGIN){ setBody(loginDTO) }
    }
    override suspend fun signup(signupDTO: SignupDTO): ApiUtils.ApiResult<String>{
        return client.post(HttpRoutes.SIGNUP){ setBody(signupDTO) }.body()
    }
    override suspend fun getGrade(): ApiUtils.ApiResult<GradeDTO> {
        return client.get(HttpRoutes.GET_GRADE).body()
    }
}