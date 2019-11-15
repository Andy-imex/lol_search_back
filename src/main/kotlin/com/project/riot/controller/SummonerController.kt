package com.project.riot.controller

import com.project.riot.domain.entity.Summoner
import com.project.riot.exception.RestException
import com.project.riot.service.SummonerService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono

@CrossOrigin(maxAge=3000)
@RestController
@RequestMapping("summoner")
class SummonerController(
        val summonerService: SummonerService
) {
    @GetMapping
    fun getAllDbSavedSummonerData() = summonerService.getAllDbSavedSummonerData()

    @DeleteMapping("/{accountId}")
    fun deleteSummonerByAccountId(@PathVariable accountId: String) : Mono<ResponseEntity<String>> =
            summonerService.deleteDbSavedSummonerDataByAccountId(accountId)
                    .map {
                        if(it === null) throw RestException(HttpStatus.NOT_FOUND, "삭제할 데이터가 없습니다.")
                        else ResponseEntity.status(HttpStatus.NO_CONTENT).body("ok")
                    }

    @PutMapping("/{accountId}/{nickName}")
    fun updateSummonerNickNameByAccountId(
            @PathVariable accountId: String,
            @PathVariable nickName: String?
    ): Mono<Summoner> {
        return summonerService.findByAccountIdToSaveNickName(accountId, nickName)
    }


}