package com.project.riot.controller

import com.project.riot.service.RiotApiService
import org.springframework.web.bind.annotation.*


@CrossOrigin(maxAge = 3000)
@RestController
@RequestMapping("/match-list")
class MatchListController(
        private val riotApiService: RiotApiService
) {

    @GetMapping("/{accountId}/{endIndex}/{beginIndex}")
    fun getUserMatchListDataByAccountId(
            @PathVariable("accountId") AccountId: String,
            @PathVariable("endIndex") EndIndex: Int,
            @PathVariable("beginIndex") BeginIndex: Int
    ): String {
        return riotApiService.getMatchListDataByAccountId(AccountId, EndIndex, BeginIndex)
    }


}