package com.project.riot.repository

import com.project.riot.domain.entity.League
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface LeagueRepository : JpaRepository<League, Long> {
    fun findAllBySummonerId(summonerId: String?): List<League>

    fun findBySummonerIdAndQueueType(summonerId: String?, queueType: String?): League?
}