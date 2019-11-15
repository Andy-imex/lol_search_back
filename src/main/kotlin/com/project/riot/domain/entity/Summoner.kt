package com.project.riot.domain.entity
import javax.persistence.*

@Entity
@Table(name="summoner")
data class Summoner(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val idx:Long,

    val id: String,

    val accountId: String?,
    var name: String,
    var profileIconId: Int,
    val puuid: String,
    var revisionDate: Long,
    var summonerLevel: Int,
    var renewalTime:Long,
    var nickName:String?
)
