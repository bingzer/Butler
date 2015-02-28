package com.bingzer.android.encoders;

import com.bingzer.android.patterns.implementor.ImplementorException;
import com.bingzer.android.patterns.implementor.ImplementorFactory;

public final class EncoderFactory extends ImplementorFactory<Encoder>{

    public EncoderFactory() throws ImplementorException {
        addImplementation(GifEncoderImpl.class);
        addImplementation(VideoEncoderImpl.class);
    }

    @Override
    protected <E extends Encoder> E newInstance(Class<E> clazz) throws InstantiationException, IllegalAccessException {
        return clazz.newInstance();
    }
}
