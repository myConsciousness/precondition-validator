/*
 * Copyright 2020 Kato Shinya.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package org.thinkit.common;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

/**
 * {@link Precondition} インターフェースのテストクラスです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
final class PreconditionTest {

    /**
     * {@link Precondition#requireNonNull(Object)} メソッドのテストケースを管理するインナークラスです。
     *
     * @author Kato Shinya
     * @since 1.0
     * @version 1.0
     */
    @Nested
    class TestRequireNonNull {

        @Test
        void requireNonNull() {
            assertThrows(NullPointerException.class, () -> Precondition.requireNonNull(null));
        }
    }

    /**
     * {@link Precondition#requireNonBlank(String)} メソッドのテストケースを管理するインナークラスです。
     *
     * @author Kato Shinya
     * @since 1.0
     * @version 1.0
     */
    @Nested
    class TestRequireNonBlank {
    }

    /**
     * {@link Precondition#requireNonBlank(String, RuntimeException)}
     * メソッドのテストケースを管理するインナークラスです。
     *
     * @author Kato Shinya
     * @since 1.0
     * @version 1.0
     */
    @Nested
    class TestRequireNonBlankWithException {
    }

    /**
     * {@link Precondition#requireNonEmpty(String)} メソッドのテストケースを管理するインナークラスです。
     *
     * @author Kato Shinya
     * @since 1.0
     * @version 1.0
     */
    @Nested
    class TestRequireStringNonEmpty {
    }

    /**
     * {@link Precondition#requireNonEmpty(String, RuntimeException)}
     * メソッドのテストケースを管理するインナークラスです。
     *
     * @author Kato Shinya
     * @since 1.0
     * @version 1.0
     */
    @Nested
    class TestRequireStringNonEmptyWithException {
    }

    /**
     * {@link Precondition#requirePositive(int)} メソッドのテストケースを管理するインナークラスです。
     *
     * @author Kato Shinya
     * @since 1.0
     * @version 1.0
     */
    @Nested
    class TestRequirePositive {
    }

    /**
     * {@link Precondition#requirePositive(int, RuntimeException)}
     * メソッドのテストケースを管理するインナークラスです。
     *
     * @author Kato Shinya
     * @since 1.0
     * @version 1.0
     */
    @Nested
    class TestRequirePositiveWithException {
    }

    /**
     * {@link Precondition#requireNegative(int)} メソッドのテストケースを管理するインナークラスです。
     *
     * @author Kato Shinya
     * @since 1.0
     * @version 1.0
     */
    @Nested
    class TestRequireNegative {
    }

    /**
     * {@link Precondition#requireNegative(int, RuntimeException)}
     * メソッドのテストケースを管理するインナークラスです。
     *
     * @author Kato Shinya
     * @since 1.0
     * @version 1.0
     */
    @Nested
    class TestRequireNegativeWithException {
    }

    /**
     * {@link Precondition#requireRange(int, int)} メソッドのテストケースを管理するインナークラスです。
     *
     * @author Kato Shinya
     * @since 1.0
     * @version 1.0
     */
    @Nested
    class TestRequireRangeTo {
    }

    /**
     * {@link Precondition#requireRange(int, int, RuntimeException)}
     * メソッドのテストケースを管理するインナークラスです。
     *
     * @author Kato Shinya
     * @since 1.0
     * @version 1.0
     */
    @Nested
    class TestRequireRangeToWithException {
    }

    /**
     * {@link Precondition#requireRange(int, int, RuntimeException)}
     * メソッドのテストケースを管理するインナークラスです。
     *
     * @author Kato Shinya
     * @since 1.0
     * @version 1.0
     */
    @Nested
    class TestRequireRangeFromTo {
    }

    /**
     * {@link Precondition#requireRange(int, int, int, RuntimeException)}
     * メソッドのテストケースを管理するインナークラスです。
     *
     * @author Kato Shinya
     * @since 1.0
     * @version 1.0
     */
    @Nested
    class TestRequireRangeFromToWithException {
    }

    /**
     * {@link Precondition#requireNonEmpty(java.util.List)}
     * メソッドのテストケースを管理するインナークラスです。
     *
     * @author Kato Shinya
     * @since 1.0
     * @version 1.0
     */
    @Nested
    class TestRequireListNonEmpty {
    }

    /**
     * {@link Precondition#requireNonEmpty(java.util.List, RuntimeException)}
     * メソッドのテストケースを管理するインナークラスです。
     *
     * @author Kato Shinya
     * @since 1.0
     * @version 1.0
     */
    @Nested
    class TestRequireListNonEmptyWithException {
    }

    /**
     * {@link Precondition#requireNonEmpty(java.util.Map)}
     * メソッドのテストケースを管理するインナークラスです。
     *
     * @author Kato Shinya
     * @since 1.0
     * @version 1.0
     */
    @Nested
    class TestRequireMapNonEmpty {
    }

    /**
     * {@link Precondition#requireNonEmpty(java.util.Map, RuntimeException)}
     * メソッドのテストケースを管理するインナークラスです。
     *
     * @author Kato Shinya
     * @since 1.0
     * @version 1.0
     */
    @Nested
    class TestRequireMapNonEmptyWithException {
    }

    /**
     * {@link Precondition#requireNonEmpty(Object[])} メソッドのテストケースを管理するインナークラスです。
     *
     * @author Kato Shinya
     * @since 1.0
     * @version 1.0
     */
    @Nested
    class TestRequireArrayNonEmpty {
    }

    /**
     * {@link Precondition#requireNonEmpty(Object[], RuntimeException)}
     * メソッドのテストケースを管理するインナークラスです。
     *
     * @author Kato Shinya
     * @since 1.0
     * @version 1.0
     */
    @Nested
    class TestRequireArrayNonEmptyWithException {
    }
}
