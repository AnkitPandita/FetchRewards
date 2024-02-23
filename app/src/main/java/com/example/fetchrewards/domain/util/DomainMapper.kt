package com.example.fetchrewards.domain.util

interface DomainMapper<T, DomainModel> {

    fun mapToDomainModel(dto: T): DomainModel
}