package com.olddog.reflect;

import com.olddog.reflect.support.AnnotationA;
import com.olddog.reflect.support.AnnotationB;
import com.olddog.reflect.support.InterfaceWithAnotationA;
import com.olddog.reflect.support.InterfaceWithAnotationB;
import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;

import static org.junit.Assert.*;

/**
 * AnnotatedElementTest
 *
 * @author yong.han
 * 2019/7/17
 */
public class AnnotatedElementTest {

//    getAnnotation
//    getAnnotations
//    getAnnotationsByType
//    getDeclaredAnnotation
//    getDeclaredAnnotations
//    getDeclaredAnnotationsByType
//    isAnnotationPresent

    @Test
    public void test() {
        AnnotatedElement annotatedElement = InterfaceWithAnotationB.class;

        Annotation[] annotations =  annotatedElement.getAnnotations();
        Annotation[] declaredAnnotations =  annotatedElement.getDeclaredAnnotations();


        assertNull(annotatedElement.getAnnotation(AnnotationA.class));
        assertNull(annotatedElement.getAnnotation(AnnotationB.class));


        assertNull(annotatedElement.getDeclaredAnnotation(AnnotationA.class));
        assertNull(annotatedElement.getDeclaredAnnotation(AnnotationB.class));


    }

}
