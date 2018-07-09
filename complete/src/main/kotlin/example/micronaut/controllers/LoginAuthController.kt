package example.micronaut.controllers


import example.micronaut.services.VelocityService
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Produces
import io.micronaut.security.Secured
import java.util.HashMap

@Secured("isAnonymous()") // <1>
@Controller("/login") // <2>
class LoginAuthController(protected val velocityService: VelocityService) { // <3>

    @Produces(MediaType.TEXT_HTML) // <4>
    @Get("/auth") // <5>
    fun auth(): String {
        return velocityService.render("auth.vm", HashMap()) // <6>
    }

    @Produces(MediaType.TEXT_HTML) // <4>
    @Get("/authFailed") // <7>
    fun authFailed(): String {
        return velocityService.render("auth.vm", authFailedModel()) // <6>
    }

    private fun authFailedModel(): Map<String, Any> {
        return mapOf(Pair("errors", true))
    }
}