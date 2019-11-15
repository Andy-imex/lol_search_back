package com.project.riot.repository

import com.project.riot.domain.entity.Summoner
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface SummonerRepository : JpaRepository<Summoner, Long>{
    fun findByAccountId(accountId:String?): Summoner?
}