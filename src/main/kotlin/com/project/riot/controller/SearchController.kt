package com.project.riot.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.project.riot.domain.entity.Summoner
import com.project.riot.service.RiotApiService
import com.project.riot.service.SummonerService
import org.springframework.web.bind.annotation.*
import java.util.*

@CrossOrigin(maxAge = 3000)
@RestController
@RequestMapping("search")
class SearchController(
        private val summonerService: SummonerService,
        private val riotApiService: RiotApiService
) {

    private val objectMapper = ObjectMapper().registerKotlinModule()


    @GetMapping("/{searchId}")
    fun getUserInfoByName(@PathVariable("searchId") SearchId: String): String? {

        val summonerObject = objectMapper.readValue<Summoner>(riotApiService.getSummonersByName(SearchId))
        val summonerAccountId = summonerObject.accountId
        val getSummoner:Summoner? = summonerService.findByAccountId(summonerAccountId)
        return if (getSummoner === null) {
            summonerObject.renewalTime = Date().time //로컬datetime
            summonerObject.nickName = null
            summonerService.save(summonerObject)
            objectMapper.writeValueAsString(summonerObject)
        } else objectMapper.writeValueAsString(summonerService.findByAccountId(summonerAccountId))
    }

    @PutMapping("/{searchId}")
    fun getUserInfoUpdateUserInfoByName(
            @PathVariable("searchId") SearchId: String
    ): String {
        val summonerObject = objectMapper.readValue<Summoner>(riotApiService.getSummonersByName(SearchId))
        val summonerAccountId = summonerObject.accountId
        val newData = summonerService.findByAccountId(summonerAccountId)

        if (newData != null) {
            newData.name = summonerObject.name
            newData.summonerLevel = summonerObject.summonerLevel
            newData.profileIconId = summonerObject.profileIconId
            newData.revisionDate = summonerObject.revisionDate
            newData.renewalTime = Date().time
            summonerService.save(newData)
        }

        return objectMapper.writeValueAsString(newData)
    }


}
