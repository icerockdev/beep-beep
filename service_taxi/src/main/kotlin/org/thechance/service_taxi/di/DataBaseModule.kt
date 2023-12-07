package org.thechance.service_taxi.di

import com.mongodb.ConnectionString
import com.mongodb.MongoClientSettings
import org.koin.dsl.module
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo
import org.thechance.service_taxi.data.DataBaseContainer

val DataBaseModule = module {
    single {
        val username = System.getenv("MONGO_USERNAME")
        val password = System.getenv("MONGO_PASSWORD")
        val connectionString = ConnectionString("mongodb://$username:$password@mongodb:27017")
        val settings = MongoClientSettings.builder()
            .applyConnectionString(connectionString)
            .build()
        KMongo.createClient(settings).coroutine
    }
    single { DataBaseContainer(get()) }
}