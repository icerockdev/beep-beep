package org.thechance.service_notification.di

import com.google.firebase.messaging.FirebaseMessaging
import com.mongodb.ConnectionString
import com.mongodb.reactivestreams.client.MongoClient
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.dsl.module
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo

@Module
@ComponentScan("org.thechance.service_notification")
class NotificationModule

val kmongoModule = module {
    single {
        val username = System.getenv("MONGO_USERNAME")
        val password = System.getenv("MONGO_PASSWORD")
        val connectionString =
            ConnectionString("mongodb://$username:$password@mongodb:27017")
        KMongo.createClient(connectionString)
    }

    single {
        val DATA_BASE_NAME = System.getenv("MONGO_DB_NAME").toString()
        get<MongoClient>().coroutine.getDatabase(DATA_BASE_NAME)
    }
}

val firebaseModule = module {
    single {
        FirebaseMessaging.getInstance()
    }
}
