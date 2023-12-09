package com.greengift.participant.data.api

import com.greengift.participant.data.dto.FestivalAllDTO
import com.greengift.participant.data.dto.FestivalJoinDTO
import com.greengift.participant.data.dto.FestivalResultDTO
import com.greengift.participant.data.util.ApiUtils
import com.greengift.participant.data.util.HttpRoutes
import com.greengift.participant.domain.repository.FestivalRepository
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody

class FestivalApi(
    private val client: HttpClient
): FestivalRepository {
    override suspend fun joinFestival(festivalJoinDTO: FestivalJoinDTO): ApiUtils.ApiResult<String> {
        return client.post(HttpRoutes.FESTIVAL_JOIN){ setBody(festivalJoinDTO) }.body()
    }

    override suspend fun getFestivalResult(festivalId: Long): ApiUtils.ApiResult<FestivalResultDTO> {
        return client.get(HttpRoutes.FESTIVAL_RESULT + "/$festivalId").body()
    }

    override suspend fun getFestivalAll(): ApiUtils.ApiResult<List<FestivalAllDTO>> {
        return client.get(HttpRoutes.FESTIVAL_ALL).body()
    }
}