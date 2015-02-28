package com.bingzer.android.patterns.implementor;

import java.util.Collection;
import java.util.HashSet;

/**
 * Patter to force the use of Interface instead of concrete class
 * @param <T>
 */
public abstract class ImplementorFactory<T> {

    private Collection<Class<? extends T>> list = new HashSet<>();

    ///////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Adds an implementation of T interface.
     * @param concreteClass the concrete class
     * @throws ImplementorException if concreteClass is an interface
     */
    public void addImplementation(Class<? extends T> concreteClass) throws ImplementorException {
        if (concreteClass.isInterface())
            throw new ImplementorException("Expecting a concrete class - Got Interface: " + concreteClass);
        list.add(concreteClass);
    }

    /**
     * Returns the sub interface of type T
     * @param interfaceClazz the clazz
     * @param <E> sub interface of T
     * @throws ImplementorException if something goes wrong
     */
    @SuppressWarnings("unchecked")
    public <E extends T> E getImplementation(Class<E> interfaceClazz) throws ImplementorException {
        if (interfaceClazz == null)
            throw new ImplementorException(new NullPointerException("clazz"));
        if (!interfaceClazz.isInterface())
            throw new ImplementorException("Expecting an interface: " + interfaceClazz);

        for(Class<? extends T> concreteClass : list){
            if(interfaceClazz.isAssignableFrom(concreteClass)){
                try {
                    return (E) newInstance(concreteClass);
                } catch (InstantiationException e) {
                    throw new ImplementorException("Cannot instantiate " + concreteClass, e);
                } catch (IllegalAccessException e) {
                    throw new ImplementorException(e);
                }
            }
        }

        throw new ImplementorException("There's no implementation found for " + interfaceClazz);
    }

    /**
     * Delegate the instance creation to the actual implementor of this factory class.
     * Usually the implementation/concrete class is a package private class reside
     * in the same package of the actual implementor of this factory class.
     * @param clazz the concrete. Usually reside in the same package
     * @param <E> the concrete type
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    protected abstract <E extends T> E newInstance(Class<E> clazz) throws InstantiationException, IllegalAccessException;
}
