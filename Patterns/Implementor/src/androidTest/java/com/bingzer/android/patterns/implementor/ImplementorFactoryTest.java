package com.bingzer.android.patterns.implementor;

import android.test.AndroidTestCase;

import com.bingzer.android.patterns.implementor.test.Circle;
import com.bingzer.android.patterns.implementor.test.Rectangle;
import com.bingzer.android.patterns.implementor.test.Shape;
import com.bingzer.android.patterns.implementor.test.Triangle;
import com.bingzer.android.patterns.implementor.test.impl.ShapeFactory;

public class ImplementorFactoryTest extends AndroidTestCase {

    private ImplementorFactory<Shape> factory;

    @Override
    protected void setUp() throws Exception {
        factory = new ShapeFactory();
    }

    public void testAddImplementation() throws Exception {
        try {
            factory.getImplementation(Triangle.class);
            fail("Should throw an exception");
        }
        catch (Exception e){
            // good! because there's no implementation for Triangle.class yet
        }

        factory.addImplementation(MyTriangle.class);
        assertNotNull(factory.getImplementation(Triangle.class));
    }

    public void testAddImplementation_UsingInterface() throws Exception {
        try {
            factory.addImplementation(Triangle.class);
            fail("Should throw an exception");
        }
        catch (Exception e){
            // good. because
            // we are trying adds an interface as an implementation
        }
    }

    public void testGetImplementation() throws Exception {
        Circle circle = factory.getImplementation(Circle.class);
        assertTrue(!circle.getClass().isInterface());

        Rectangle rectangle = factory.getImplementation(Rectangle.class);
        assertTrue(!circle.getClass().isInterface());
    }

    public void testGetImplementation_DoesNotExists() throws Exception {
        try {
            Triangle triangle = factory.getImplementation(Triangle.class);
            fail("Should throw an exception");
        }
        catch (ImplementorException e){
            // good!
        }
    }

    public void testGetImplemenation_UsingConcretClass() throws Exception {
        try {
            factory.addImplementation(MyTriangle.class);
            Triangle triangle = factory.getImplementation(MyTriangle.class);
            fail("Should throw an exception");
        }
        catch (ImplementorException e){
            // good
        }
    }


    /////////////////////////////////////////////////////////////////////////////////

    public static class MyTriangle implements Triangle {

    }
}
