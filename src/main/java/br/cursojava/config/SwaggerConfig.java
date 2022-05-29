package br.cursojava.config;

import javax.ws.rs.core.Application;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.info.License;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;


@OpenAPIDefinition(
    tags = {
        @Tag(name = "CursoJava", description = "API de curso")
    },
    info = @Info(
        title = "Curso Java",
        version = "1.0.0",
        contact = @Contact(
            name = "Hendrew Martins",
            email = "hendrewmartins@hotmail.com"
        ),
        license = @License(
            name="MIT",
            url = "https://opensource.org/licenses/MIT"
        )
    )
)
public class SwaggerConfig extends Application {
    
}
