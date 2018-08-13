package example.micronaut.controllers


import example.micronaut.services.VelocityService
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Produces
import io.micronaut.security.Secured
import io.micronaut.views.View
import java.util.HashMap

@Secured("isAnonymous()") // <1>
@Controller("/login") // <2>
class LoginAuthController {

    @Get("/auth") // <3>
    @View("auth") // <4>
    fun auth(): Map<String, Any> {
        return HashMap()
    }

    @Get("/authFailed") // <5>
    @View("auth") // <4>
    fun authFailed(): Map<String, Any> {
        return mapOf(Pair("errors", true))
    }
}