package com.project.riot.service

import com.project.riot.domain.entity.League
import com.project.riot.repository.LeagueRepository
import org.springframework.stereotype.Service


@Service
class LeagueService(val leagueRepository: LeagueRepository) {

    fun save(newLeague: League) {
        leagueRepository.save(newLeague)
    }

    fun findAllBySummonerId(summonerId: String?): List<League> {
        return leagueRepository.findAllBySummonerId(summonerId)
    }

    fun findBySummonerIdAndQueueType(summonerId: String?, queueType: String?): League? {
        return leagueRepository.findBySummonerIdAndQueueType(summonerId, queueType)
    }

}