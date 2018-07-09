package example.micronaut.controllers

import example.micronaut.services.VelocityService
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Produces
import io.micronaut.security.Secured
import java.security.Principal

@Secured("isAnonymous()") // <1>
@Controller("/") // <2>
class HomeController(protected val velocityService: VelocityService) { // <3>

    @Produces(MediaType.TEXT_HTML) // <4>
    @Get("/") // <5>
    fun index(principal: Principal?): String { // <6>
        return velocityService.render("home.vm", homeModel(principal))
    }

    private fun homeModel(principal: Principal?): Map<String, Any> {
        val data = mutableMapOf<String, Any>()
        data["loggedIn"] = (principal != null) as Any
        if (principal != null) {
            data["username"] = principal.name as Any
        }
        return data
    }
}