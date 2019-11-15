package com.project.riot.domain.entity
import javax.persistence.*

@Entity
@Table(name = "league")
data class League(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long,

        val freshBlood: Boolean,

        val hotStreak: Boolean,
        val inactive: Boolean,
        var leagueId: String,
        var leaguePoints: Int,
        var losses: Int,
        val queueType: String?,
        var rank: String,
        val summonerId: String,
        val summonerName: String,
        var tier: String,
        val veteran: Boolean,
        var wins: Int,

        @OneToOne  //1대1 관계매핑
        @JoinColumn(name = "mini_series_id")
        val miniSeries: MiniSeries?
)