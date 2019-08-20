/*
 * Copyright 2016-2019 David Karnok
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package hu.akarnokd.rxjava3.functions;

import io.reactivex.rxjava3.functions.Function3;

/**
 * A {@link Function3} with suppressed exception on its
 * {@link #apply(Object, Object, Object)} method.
 *
 * @param <T1> the first argument type
 * @param <T2> the second argument type
 * @param <T3> the third argument type
 * @param <R> the output value type
 */
public interface PlainFunction3<T1, T2, T3, R>
extends Function3<T1, T2, T3, R> {

    @Override
    R apply(T1 t1, T2 t2, T3 t3);
}
