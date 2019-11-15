package com.project.riot.service

import com.project.riot.domain.entity.Summoner
import com.project.riot.exception.RestException
import com.project.riot.repository.SummonerRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono


@Service
class SummonerService(val summonerRepository: SummonerRepository) {
    fun save(newSummoner: Summoner) {
        summonerRepository.save(newSummoner)
    }
    fun findByAccountId(accountId: String?): Summoner? {
        return summonerRepository.findByAccountId(accountId)
    }

    fun getAllDbSavedSummonerData() : Mono<List<Summoner>> = Mono
            .fromSupplier { // subscribe를 할 때 데이터를 생성하고 싶다면 fromSupplier
                summonerRepository.findAll()
            }.map {
                if(it.isEmpty()) throw RestException(HttpStatus.NOT_FOUND, "데이터가 없습니다")
                else it
            }

    fun deleteDbSavedSummonerDataByAccountId(accountId: String) : Mono<Unit> = Mono
            .fromSupplier {
                summonerRepository.findByAccountId(accountId)
            }
            .map {
                if(it === null) throw RestException(HttpStatus.NOT_FOUND, "삭제할 데이터가 없습니다.")
                else summonerRepository.delete(it)
            }
    fun findByAccountIdToSaveNickName(accountId: String, nickName: String?) : Mono<Summoner> = Mono
            .fromSupplier {
                summonerRepository.findByAccountId(accountId)
            }
            .map {
                if(it === null) throw RestException(HttpStatus.NOT_FOUND, "데이터가 없습니다")
                else {
                    it.nickName = nickName
                    summonerRepository.save(it)
                    it
                }
            }




}