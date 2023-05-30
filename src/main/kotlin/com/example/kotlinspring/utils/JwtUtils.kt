package com.example.kotlinspring.utils

import com.example.kotlinspring.model.Usuario
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.stereotype.Component
import java.util.*
import java.util.concurrent.TimeUnit
import java.util.function.Function
import kotlin.collections.HashMap

@Component
class JwtUtils {
    val secret: String = "secret"

    fun extractLogin(token: String): String {
        return extractClaim(token, Claims::getSubject) as String
    }

    private fun createToken(claims: HashMap<String, Any>, usuario: Usuario): String{
        return Jwts.builder()
            .setSubject(usuario.id.toString())
            .claim("authorities",usuario.authorities)
            .setIssuedAt(Date(System.currentTimeMillis()))
            .setExpiration(Date(System.currentTimeMillis() + TimeUnit.HOURS.toMillis(24)))
            .signWith(SignatureAlgorithm.HS256,secret)
            .compact()
    }

    fun generateToken(usuario: Usuario): String{
        val claims = HashMap<String, Any>()
        return createToken(claims,usuario)
    }

    fun extractClaim(token: String, resolver: Function<Claims,Any>) : Any{
        val claims: Claims = extractAllClaims(token)
        return resolver.apply(claims)
    }

    private fun extractAllClaims(token: String): Claims {
        return Jwts.parser().setSigningKey(secret).parseClaimsJwt(token).body
    }

    fun isTokenValid(token: String, usuario: Usuario): Boolean{
        val login = extractLogin(token)
        return (login == usuario.login && !isTokenExpired(token))
    }

    private fun isTokenExpired(token: String): Boolean {
        return extractExpiration(token).before(Date())
    }

    fun extractExpiration(token: String): Date {
        return extractClaim(token,Claims::getExpiration) as Date
    }
}