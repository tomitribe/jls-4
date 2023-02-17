/*
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
package org.example.jls;

import org.junit.Test;

import java.io.File;
import java.net.URI;
import java.util.Comparator;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * EveryFlavorBean implements Comparator, Predicate and Consumer with types that are bound.
 * EveryFlavorBean also implements Supplier, but does not bind the generic type <T>
 *
 * EveryFlavorBean is referenced as a raw type and the compiler will no longer check the
 * bound types of Comparator, Predicate or Consumer.
 */
public class AssignableTypes2Test {

    @Test
    public void test() {

        final EveryFlavorBean bean = new EveryFlavorBean();

        // Reference as raw types
        {
            final Comparator comparator = bean;
            final Predicate predicate = bean;
            final Consumer consumer = bean;
        }

        // Reference as parameterized types
        {
            final Comparator<File> comparator = bean;
            final Predicate<String> predicate = bean;
            final Consumer<URI> consumer = bean;
        }

        // Reference as unbounded types -- suddenly valid and does compile
        {
            final Comparator<Object> comparator = bean;
            final Predicate<Object> predicate = bean;
            final Consumer<Object> consumer = bean;
        }

        // Reference as other bounded types -- suddenly valid and does compile
        {
            final Comparator<Boolean> comparator = bean;
            final Predicate<Boolean> predicate = bean;
            final Consumer<Boolean> consumer = bean;
        }

        // Reference as wildcard types
        {
            final Comparator<?> comparator = bean;
            final Predicate<?> predicate = bean;
            final Consumer<?> consumer = bean;
        }
    }

    public static class EveryFlavorBean<T> implements
            Supplier<T>,
            Comparator<File>,
            Predicate<String>,
            Consumer<URI> {

        @Override
        public T get() {
            return null;
        }

        @Override
        public int compare(final File o1, final File o2) {
            return 0;
        }

        @Override
        public void accept(final URI uri) {

        }

        @Override
        public boolean test(final String s) {
            return false;
        }
    }
}
