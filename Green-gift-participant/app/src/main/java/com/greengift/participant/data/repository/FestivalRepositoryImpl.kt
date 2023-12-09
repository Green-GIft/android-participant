package com.greengift.participant.data.repository

import com.greengift.participant.data.api.FestivalApi
import com.greengift.participant.data.dto.FestivalAllDTO
import com.greengift.participant.data.dto.FestivalJoinDTO
import com.greengift.participant.data.dto.FestivalResultDTO
import com.greengift.participant.data.util.ApiUtils
import com.greengift.participant.domain.repository.FestivalRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FestivalRepositoryImpl @Inject constructor(
    private val api: FestivalApi
): FestivalRepository{
    override suspend fun joinFestival(festivalJoinDTO: FestivalJoinDTO): ApiUtils.ApiResult<String> {
        return api.joinFestival(festivalJoinDTO)
    }

    override suspend fun getFestivalResult(festivalId: Long): ApiUtils.ApiResult<FestivalResultDTO> {
        return api.getFestivalResult(festivalId)
    }

    override suspend fun getFestivalAll(): ApiUtils.ApiResult<List<FestivalAllDTO>> {
        return api.getFestivalAll()
    }
}