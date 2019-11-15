package com.project.riot.domain.entity

import javax.persistence.*

@Entity
@Table(name="miniseries")
data class MiniSeries(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val miniSeriesId: Int,

        val progress: String,
        val losses: Int,
        val target: Int,
        val wins: Int
)