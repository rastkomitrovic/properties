package com.agency.properties.auth

import com.google.common.base.Supplier
import com.google.common.collect.ImmutableMap
import io.jsonwebtoken.*
import io.jsonwebtoken.impl.compression.GzipCompressionCodec
import org.joda.time.DateTime
import io.jsonwebtoken.impl.TextCodec.BASE64
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.util.*
import org.apache.commons.lang3.StringUtils.substringBeforeLast
import java.lang.IllegalArgumentException


@Service
class JWTTokenService(
        @Value("\${jwt.issuer:properties}") val issuer: String,
        @Value("\${jwt.expiration-sec:86400}") val expirationSec: Int,
        @Value("\${jwt.clock-skew-sec:300}") val clockSkewSec: Int,
        @Value("\${jwt.secret:secret}") val secret: String
) : Clock {

    fun permanent(attributes: Map<String, String>): String {
        return newToken(attributes, 0)
    }

    fun expiring(attributes: Map<String, String>): String {
        return newToken(attributes, expirationSec)
    }

    fun newToken(attributes: Map<String, String>, expiresInSec: Int): String {
        val now = DateTime.now()
        val claims = Jwts.claims().setIssuer(issuer).setIssuedAt(now.toDate())
        if (expiresInSec > 0) {
            val expiresAt = now.plusSeconds(expiresInSec)
            claims.expiration = expiresAt.toDate()
        }
        claims.putAll(attributes)

        return Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS256, BASE64.encode(secret)).compressWith(COMPRESSION_CODEC).compact()
    }

    fun verify(token: String): Map<String, String> {
        val parser = Jwts.parser()
                .requireIssuer(issuer)
                .setClock(this)
                .setAllowedClockSkewSeconds(clockSkewSec.toLong())
                .setSigningKey(BASE64.encode(secret))
        return parseClaims { parser.parseClaimsJwt(token).body }
    }

    fun untrusted(token: String): Map<String, String> {
        val parser = Jwts.parser()
                .requireIssuer(issuer)
                .setClock(this)
                .setAllowedClockSkewSeconds(clockSkewSec.toLong())

        val withoutSignature = substringBeforeLast(token, DOT) + DOT

        return parseClaims { parser.parseClaimsJwt(withoutSignature).body }
    }

    fun parseClaims(toClaims: Supplier<Claims>): Map<String, String> {
        return try {
            val claims = toClaims.get()
            val builder = ImmutableMap.builder<String, String>()

            claims.entries.forEach { builder.put(it.key, it.value.toString()) }

            builder.build()
        } catch (ex: IllegalArgumentException) {
            ImmutableMap.of()
        } catch (ex: JwtException) {
            ImmutableMap.of()
        }
    }

    override fun now(): Date {
        return DateTime.now().toDate()
    }

    companion object {
        private const val DOT = "."
        private val COMPRESSION_CODEC = GzipCompressionCodec()
    }
}