package com.template.service.aspect;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.validation.BindingResult;

import com.template.common.exception.JsonSerializationException;
import com.template.common.util.JsonUtil;

public class BaseAspect {

	/**
	 * 
	 * @param joinPoint
	 * @return
	 */
	protected Method resolveMethod(ProceedingJoinPoint joinPoint) {
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		Class<?> targetClass = joinPoint.getTarget().getClass();

		Method method = getDeclaredMethodFor(targetClass, signature.getName(),
				signature.getMethod().getParameterTypes());
		if (method == null) {
			throw new IllegalStateException("Cannot resolve target method: " + signature.getMethod().getName());
		}
		return method;
	}

	protected String getMethodName(ProceedingJoinPoint joinPoint) {
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		String methodName = signature.getName();
		return methodName;
	}

	protected String getClassName(ProceedingJoinPoint joinPoint) {
		return joinPoint.getTarget().getClass().getName();
	}

	protected String formatMethod(ProceedingJoinPoint joinPoint) {
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		String className = joinPoint.getTarget().getClass().getName();
		String methodName = signature.getName();
		return String.format("class=%s,method=%s", className, methodName);
	}

	protected String formatArgs(ProceedingJoinPoint joinPoint) {
		Object[] args = joinPoint.getArgs();
		List<Object> params = new ArrayList<>(args.length);
		try {
			for (Object arg : args) {
				if (arg instanceof BindingResult) {
					continue;
				}
				params.add(arg);
			}
			return String.format("args=%s", JsonUtil.prettyPrint(JsonUtil.toJson(params)));

		} catch (JsonSerializationException e) {
			System.out.println(e);
		}
		return "";
	}

	protected String formatReturn(Object ret) {
		try {
			return String.format("ret=%s", JsonUtil.prettyPrint(JsonUtil.toJson(ret)));

		} catch (JsonSerializationException e) {

		}
		return "";
	}

	/**
	 * Get declared method with provided name and parameterTypes in given class and
	 * its super classes. All parameters should be valid.
	 *
	 * @param clazz          class where the method is located
	 * @param name           method name
	 * @param parameterTypes method parameter type list
	 * @return resolved method, null if not found
	 */
	private Method getDeclaredMethodFor(Class<?> clazz, String name, Class<?>... parameterTypes) {
		try {
			return clazz.getDeclaredMethod(name, parameterTypes);
		} catch (NoSuchMethodException e) {
			Class<?> superClass = clazz.getSuperclass();
			if (superClass != null) {
				return getDeclaredMethodFor(superClass, name, parameterTypes);
			}
		}
		return null;
	}
}
