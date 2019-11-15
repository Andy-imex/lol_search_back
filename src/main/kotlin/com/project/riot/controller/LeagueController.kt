package com.project.riot.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.project.riot.domain.entity.League
import com.project.riot.service.LeagueService
import com.project.riot.service.RiotApiService
import org.springframework.web.bind.annotation.*


@CrossOrigin(maxAge = 3000)
@RestController
@RequestMapping("league")
class LeagueController(
        private val leagueService: LeagueService,
        private val riotApiService: RiotApiService
) {
    private val objectMapper = ObjectMapper().registerKotlinModule()


    @GetMapping("/{summonerId}")
    fun getUserRecordByAccountId(@PathVariable("summonerId") SummonerId: String): String? {
        return if (leagueService.findAllBySummonerId(SummonerId).isEmpty()) {
            val leagues: List<League> = objectMapper.readValue(riotApiService.getLeaguesBySummonerId(SummonerId))
            leagues.forEach {
                leagueService.save(it)
            }
            riotApiService.getLeaguesBySummonerId(SummonerId)
        } else {
            objectMapper.writeValueAsString(leagueService.findAllBySummonerId(SummonerId))
        }
    }

    @PutMapping("/{summonerId}")
    fun getUpdateUserRecordBySummonerId(@PathVariable("summonerId") SummonerId: String): String {

        val leagues: List<League> = objectMapper.readValue(riotApiService.getLeaguesBySummonerId(SummonerId))

        leagues.map { searchLeague ->
            val dbLeague: League? = leagueService.findBySummonerIdAndQueueType(SummonerId, searchLeague.queueType)
            if (dbLeague == null) {
                leagueService.save(searchLeague)
            } else {
                dbLeague.tier = searchLeague.tier
                dbLeague.rank = searchLeague.rank
                dbLeague.leaguePoints = searchLeague.leaguePoints
                dbLeague.wins = searchLeague.wins
                dbLeague.losses = searchLeague.losses
                dbLeague.leagueId = searchLeague.leagueId

                val copiedD = dbLeague.copy(
                        tier = searchLeague.tier
                )
                leagueService.save(dbLeague)
            }
        }
        return riotApiService.getLeaguesBySummonerId(SummonerId)
    }
}