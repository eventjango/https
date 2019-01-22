package com.example.https.callback;

import com.example.https.domain.Rings;

@FunctionalInterface
public interface GetCallback<T> {

    T getValue(Rings rings);
}
