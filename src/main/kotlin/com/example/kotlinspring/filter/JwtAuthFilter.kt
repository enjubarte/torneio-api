package com.example.kotlinspring.filter

import com.example.kotlinspring.service.UsuarioService
import com.example.kotlinspring.utils.JwtUtils
import org.springframework.http.HttpHeaders.AUTHORIZATION
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import java.io.IOException
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import kotlin.jvm.Throws

@Component
class JwtAuthFilter(
    private val usuarioService: UsuarioService,
    private val jwtUtils: JwtUtils
): OncePerRequestFilter() {
    @Throws(IOException::class, ServletException::class)
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {

        val authHeader: String? = request.getHeader(AUTHORIZATION)

        if(authHeader.equals(null) || authHeader?.startsWith("Bearer")!!){
            filterChain.doFilter(request,response)
            return;
        }

        val jwtToken: String = authHeader.substring(7)
        val userLogin: String = jwtUtils.extractLogin(jwtToken)

        if (userLogin.isNotBlank() && SecurityContextHolder.getContext().authentication == null){
            val userDetails = usuarioService.loadUserByUsername(userLogin)
            if(userDetails?.let { jwtUtils.isTokenValid(jwtToken, it) } == true){
                val authToken: UsernamePasswordAuthenticationToken =
                    UsernamePasswordAuthenticationToken(userDetails,null, userDetails.authorities)
                authToken.details = WebAuthenticationDetailsSource().buildDetails(request)
                SecurityContextHolder.getContext().authentication = authToken
            }
        }

        filterChain.doFilter(request,response)
    }
}