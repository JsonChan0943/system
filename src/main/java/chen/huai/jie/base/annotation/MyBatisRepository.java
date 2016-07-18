package chen.huai.jie.base.annotation;

import org.springframework.stereotype.Repository;

/**
 * 用来标记DAO的注解
 * 只有被此注解修饰的接口会被扫描成DAO
 * @author Administrator
 *
 */
@Repository
public @interface MyBatisRepository {
	String value() default "";
}
