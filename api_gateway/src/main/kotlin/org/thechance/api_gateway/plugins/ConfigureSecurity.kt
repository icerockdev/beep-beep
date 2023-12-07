package org.thechance.api_gateway.plugins

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import org.thechance.api_gateway.data.model.authenticate.TokenType
import io.ktor.server.response.*

fun Application.configureJWTAuthentication() {

    val jwtSecret = System.getenv("JWT_SECRET").toString()
    val jwtDomain = System.getenv("JWT_ISSUER").toString()
    val jwtAudience = System.getenv("JWT_AUDIENCE").toString()
    val jwtRealm = System.getenv("JWT_REALM").toString()

    authentication {
        jwt("auth-jwt") {
            realm = jwtRealm
            verifier(
                JWT.require(Algorithm.HMAC256(jwtSecret))
                    .withAudience(jwtAudience)
                    .withIssuer(jwtDomain)
                    .withClaim("tokenType", TokenType.ACCESS_TOKEN.name)
                    .build()
            )
            validate { credential ->
                if (credential.payload.audience.contains(jwtAudience)) {
                    JWTPrincipal(credential.payload)
                } else
                    null
            }
            respondUnauthorized()
        }

        jwt("refresh-jwt") {
            realm = jwtRealm
            verifier(
                JWT.require(Algorithm.HMAC256(jwtSecret))
                    .withAudience(jwtAudience)
                    .withIssuer(jwtDomain)
                    .withClaim("tokenType", TokenType.REFRESH_TOKEN.name)
                    .build()
            )
            validate { credential ->
                if (credential.payload.audience.contains(jwtAudience)) {
                    JWTPrincipal(credential.payload)
                } else
                    null
            }

            respondUnauthorized()
        }
    }
}

private fun JWTAuthenticationProvider.Config.respondUnauthorized() {
    challenge { _, _ ->
        call.respond(UnauthorizedResponse())
    }
}