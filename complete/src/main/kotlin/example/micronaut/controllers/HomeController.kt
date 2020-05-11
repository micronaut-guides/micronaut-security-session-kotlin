package example.micronaut.controllers

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.security.annotation.Secured
import io.micronaut.security.rules.SecurityRule
import io.micronaut.views.View
import java.security.Principal

@Controller("/") // <2>
@Secured(SecurityRule.IS_ANONYMOUS) // <1>
class HomeController {

    @Get("/") // <3>
    @View("home") // <4>
    fun index(principal: Principal?): Map<String, Any> { // <5>
        val data = mutableMapOf<String, Any>()
        data["loggedIn"] = (principal != null) as Any
        if (principal != null) {
            data["username"] = principal.name as Any
        }
        return data
    }
}
