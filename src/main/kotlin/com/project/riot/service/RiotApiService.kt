package com.project.riot.service

import com.project.riot.exception.RestException
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.WebClientResponseException


@Service
class RiotApiService(
        @Value("\${riot.api.url}")
        val apiUrl: String,
        @Value("\${riot.api.token.key}")
        val tokenKey: String
) {
    fun getSummonersByName(name: String): String {
        return getApiData("${apiUrl}/summoner/v4/summoners/by-name/${name}")
    }

    fun getLeaguesBySummonerId(summonerId: String): String {
        return getApiData("${apiUrl}/league/v4/entries/by-summoner/${summonerId}")
    }

    fun getMatchListDataByAccountId(accountId: String, endIndex: Int, beginIndex: Int): String {
        return getApiData("${apiUrl}/match/v4/matchlists/by-account/${accountId}?endIndex=${endIndex}&beginIndex=${beginIndex}")
    }

    fun getMatchDataByAccountId(matchId: Long): String {
        return getApiData("${apiUrl}/match/v4/matches/${matchId}")
    }

    private fun getApiData(url:String): String {
        return WebClient
                .builder()
                .defaultHeader("X-Riot-Token", tokenKey)
                .build()
                .get()
                .uri(url)
                .retrieve()  // Response를받아옴
                .bodyToMono(String::class.java)//가져온 body를 모노로 바꿔줌
                .doOnError(WebClientResponseException::class.java){
                    throw RestException(HttpStatus.NOT_FOUND, "검색한 소환사 이름이 없습니다.") //해당 에러가 전부다 string 으로 날라감 전부다 Mono 로 변경해서 String 으로 리턴하는걸 바꿔야될듯
                }
                .block() ?: ""
    }


}





