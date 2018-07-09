package example.micronaut.services

import org.apache.velocity.VelocityContext
import org.apache.velocity.app.VelocityEngine

import javax.annotation.PostConstruct
import javax.inject.Singleton
import java.io.StringWriter
import java.nio.charset.StandardCharsets
import java.util.Properties

@Singleton // <1>
class VelocityService {

    private var velocityEngine: VelocityEngine? = null

    @PostConstruct // <2>
    internal fun initialize() {
        val p = Properties()
        p.setProperty("resource.loader", "class")
        p.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader")
        velocityEngine = VelocityEngine(p)
    }

    fun render(name: String, data: Map<String, Any>): String {
        val writer = StringWriter()
        val velocityContext = VelocityContext(data)
        velocityEngine!!.mergeTemplate("templates/$name", StandardCharsets.UTF_8.name(), velocityContext, writer)
        return writer.toString()
    }
}