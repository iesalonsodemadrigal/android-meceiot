package edu.iesam.meceiot.core.data.local.db

interface GeneralDao<T : Cache> {
    fun getAll(): List<T>
}