package com.project.riot.controller

import com.project.riot.service.RiotApiService
import org.springframework.web.bind.annotation.*

@CrossOrigin(maxAge = 3000)
@RestController
@RequestMapping("match")
class MatchController(
        private val riotApiService: RiotApiService
) {

    @GetMapping("/{matchId}")
    fun getUserMatchDataByAccountId(@PathVariable("matchId") MatchId: Long): String {
        return riotApiService.getMatchDataByAccountId(MatchId)
    }


}