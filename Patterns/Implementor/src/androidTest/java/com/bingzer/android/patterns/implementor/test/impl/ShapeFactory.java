package com.bingzer.android.patterns.implementor.test.impl;

import com.bingzer.android.patterns.implementor.ImplementorException;
import com.bingzer.android.patterns.implementor.ImplementorFactory;
import com.bingzer.android.patterns.implementor.test.Shape;

public class ShapeFactory extends ImplementorFactory<Shape>{

    public ShapeFactory() throws ImplementorException {
        addImplementation(CircleImpl.class);
        addImplementation(RectangleImpl.class);
    }

    @Override
    protected <E extends Shape> E newInstance(Class<E> clazz) throws InstantiationException, IllegalAccessException {
        return clazz.newInstance();
    }
}
