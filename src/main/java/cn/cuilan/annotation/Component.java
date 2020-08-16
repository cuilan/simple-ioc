package cn.cuilan.annotation;

import java.lang.annotation.*;

/**
 * @author zhang.yan
 * @date 2020/8/16
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Component {

    String value() default "";

}
