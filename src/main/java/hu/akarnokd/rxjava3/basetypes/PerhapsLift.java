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

package hu.akarnokd.rxjava3.basetypes;

import org.reactivestreams.Subscriber;

import io.reactivex.rxjava3.exceptions.Exceptions;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.internal.functions.ObjectHelper;
import io.reactivex.rxjava3.internal.subscriptions.EmptySubscription;

/**
 * Map the downstream Subscriber into an upstream Subscriber.
 *
 * @param <T> the upstream value type
 * @param <R> the downstream value type
 */
final class PerhapsLift<T, R> extends Perhaps<R> {

    final Perhaps<T> source;

    final Function<Subscriber<? super R>, Subscriber<? super T>> onLift;

    PerhapsLift(Perhaps<T> source, Function<Subscriber<? super R>, Subscriber<? super T>> onLift) {
        this.source = source;
        this.onLift = onLift;
    }

    @Override
    protected void subscribeActual(Subscriber<? super R> s) {
        Subscriber<? super T> parent;

        try {
            parent = ObjectHelper.requireNonNull(onLift.apply(s), "The onLift returned a null Subscriber");
        } catch (Throwable ex) {
            Exceptions.throwIfFatal(ex);
            EmptySubscription.error(ex, s);
            return;
        }

        source.subscribe(parent);
    }

}
