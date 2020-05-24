package com.template.service.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import io.prometheus.client.Histogram;

@Aspect
@Order(value = -1)
@Component
public class PrometheusMetricsAspect extends BaseAspect {
	static final Histogram requestLatency = Histogram.build()
			.name("rpc_requests_latency_seconds")
		    .help("Rpc request latency in seconds.")
		    .buckets(0.1, 0.25, 0.5, 1, 2.5, 5)
		    .labelNames("method").register();
	
	@Around("execution(* com.template.service.impl..*.*(..))")
	public Object interceptor(ProceedingJoinPoint joinPoint) throws Throwable {
		
		Object ret = null;
		Histogram.Timer requestTimer = requestLatency.labels(getMethodName(joinPoint)).startTimer();
		try {
			ret = joinPoint.proceed();
		} catch (Throwable e) {
			throw e;
		} finally {
			requestTimer.observeDuration();
		}
		return ret;
	}
}
