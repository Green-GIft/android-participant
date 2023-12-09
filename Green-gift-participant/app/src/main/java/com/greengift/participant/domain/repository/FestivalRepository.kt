package com.greengift.participant.domain.repository

import com.greengift.participant.data.dto.FestivalAllDTO
import com.greengift.participant.data.dto.FestivalJoinDTO
import com.greengift.participant.data.dto.FestivalResultDTO
import com.greengift.participant.data.util.ApiUtils

interface FestivalRepository {
    suspend fun joinFestival(festivalJoinDTO: FestivalJoinDTO): ApiUtils.ApiResult<String>
    suspend fun getFestivalResult(festivalId: Long): ApiUtils.ApiResult<FestivalResultDTO>
    suspend fun getFestivalAll(): ApiUtils.ApiResult<List<FestivalAllDTO>>
}