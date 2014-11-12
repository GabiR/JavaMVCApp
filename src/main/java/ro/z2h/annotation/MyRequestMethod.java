package ro.z2h.annotation;

import java.lang.annotation.*;

/**
 * Project MVCApp
 * Package ro.z2h.annotation
 * Created by Juvie on 11.11.2014.
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyRequestMethod {
    String methodType() default "GET";
    String urlPath();
}
