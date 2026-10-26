//package com.infy.utility;
//
//import org.aspectj.lang.annotation.AfterThrowing;
//
//@Aspect
//public class Aspect {
//    private static final Logger LOGGER =
//            LoggerFactory.getLogger(LoggingAspect.class);
//	@AfterThrowing(
//		    pointcut = "execution(* com.infy.service.CourseLessonServiceImpl.*(..))",
//		    throwing = "exception"
//		)
//		public void logAfterThrowing(Exception exception) {
//
//		    LOGGER.error(
//		        "Service method failed. Exception: {}",
//		        exception.getMessage()
//		    );
//		}
//}

package com.infy.utility;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class LoggingAspect {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(LoggingAspect.class);

    @AfterThrowing(
        pointcut = "execution(* com.infy.service.CourseLessonServiceImpl.*(..))",
        throwing = "exception"
    )
    public void logAfterThrowing(Exception exception) {

        LOGGER.error(
            "Service method failed. Exception: {}",
            exception.getMessage()
        );
    }
}