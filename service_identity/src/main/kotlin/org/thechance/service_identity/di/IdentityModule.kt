package org.thechance.service_identity.di


import com.mongodb.ConnectionString
import com.mongodb.MongoClientSettings
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.dsl.module
import org.litote.kmongo.reactivestreams.KMongo

@Module
@ComponentScan("org.thechance.service_identity")
class IdentityModule

val kmongoModule = module {
    single {
        val username = System.getenv("MONGO_USERNAME")
        val password = System.getenv("MONGO_PASSWORD")
        val connectionString = ConnectionString("mongodb://$username:$password@mongodb:27017")
        val settings = MongoClientSettings.builder()
            .applyConnectionString(connectionString)
            .build()
        KMongo.createClient(settings)
    }
}