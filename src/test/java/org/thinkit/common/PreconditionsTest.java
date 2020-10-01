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

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * {@link Preconditions} インターフェースのテストクラスです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
final class PreconditionsTest {

    /**
     * 文字列が空白だった場合の例外メッセージ
     */
    private static final String EXCEPTION_MESSAGE_FOR_BLANK_STRING = "String must not be blank";

    /**
     * 数値が負数だった場合の例外メッセージ
     */
    private static final String EXCEPTION_MESSAGE_FOR_NEGATIVE_NUMBER = "Number must be positive but %s was given";

    /**
     * 数値が正数だった場合の例外メッセージ
     */
    private static final String EXCEPTION_MESSAGE_FOR_POSITIVE_NUMBER = "Number must be negative but %s was given";

    /**
     * 範囲外だった場合の例外メッセージ
     */
    private static final String EXCEPTION_MESSAGE_FOR_OUT_OF_BOUNDS_TO = "Index %s out-of-bounds for range from length 0 to length %s";

    /**
     * 範囲外だった場合の例外メッセージ
     */
    private static final String EXCEPTION_MESSAGE_FOR_OUT_OF_BOUNDS_FROM_TO = "Index %s out-of-bounds for range from length %s to length %s";

    /**
     * {@link Preconditions#requireNonNull(Object)} メソッドのテストケースを管理するインナークラスです。
     *
     * @author Kato Shinya
     * @since 1.0
     * @version 1.0
     */
    @Nested
    class TestRequireNonNull {

        @Test
        void testWhenObjectIsNull() {
            assertThrows(NullPointerException.class, () -> Preconditions.requireNonNull(null));
        }

        @Test
        void testWhenObjectIsNotNull() {
            assertDoesNotThrow(() -> Preconditions.requireNonNull(""));
        }
    }

    /**
     * {@link Preconditions#requireNonBlank(String)} メソッドのテストケースを管理するインナークラスです。
     *
     * @author Kato Shinya
     * @since 1.0
     * @version 1.0
     */
    @Nested
    class TestRequireNonBlankString {

        @Test
        void testWhenStringIsBlank() {
            final IllegalSequenceFoundException exception = assertThrows(IllegalSequenceFoundException.class,
                    () -> Preconditions.requireNonBlank(""));
            assertEquals(EXCEPTION_MESSAGE_FOR_BLANK_STRING, exception.getMessage());
        }

        @ParameterizedTest
        @ValueSource(strings = { "test", "t", "te", "¥r", "¥" })
        void testWhenStringIsNotBlank(String testParameter) {
            assertDoesNotThrow(() -> Preconditions.requireNonBlank(testParameter));
        }
    }

    /**
     * {@link Preconditions#requireNonBlank(String, RuntimeException)}
     * メソッドのテストケースを管理するインナークラスです。
     *
     * @author Kato Shinya
     * @since 1.0
     * @version 1.0
     */
    @Nested
    class TestRequireNonBlankStringWithException {

        @ParameterizedTest
        @ValueSource(strings = { "test", "t", "te", "¥r", "¥" })
        void testWhenExceptionIsNull(String testParameter) {
            assertThrows(NullPointerException.class, () -> Preconditions.requireNonBlank(testParameter, null));
        }

        @Test
        void testWhenStringIsBlank() {
            assertThrows(TestException.class, () -> Preconditions.requireNonBlank("", new TestException()));
        }

        @Test
        void testWhenStringIsBlankAndExceptionIsNull() {
            assertThrows(NullPointerException.class, () -> Preconditions.requireNonBlank("", null));
        }

        @ParameterizedTest
        @ValueSource(strings = { "test", "t", "te", "¥r", "¥" })
        void testWhenStringIsNotBlank(String testParameter) {
            assertDoesNotThrow(() -> Preconditions.requireNonBlank(testParameter, new TestException()));
        }
    }

    /**
     * {@link Preconditions#requireNonEmpty(String)} メソッドのテストケースを管理するインナークラスです。
     *
     * @author Kato Shinya
     * @since 1.0
     * @version 1.0
     */
    @Nested
    class TestRequireNonEmptyString {

        @Test
        void testWhenStringIsBlankIsNull() {
            String empty = null;
            assertThrows(NullPointerException.class, () -> Preconditions.requireNonEmpty(empty));
        }

        @Test
        void testWhenStringIsBlank() {
            final IllegalSequenceFoundException exception = assertThrows(IllegalSequenceFoundException.class,
                    () -> Preconditions.requireNonEmpty(""));
            assertEquals(EXCEPTION_MESSAGE_FOR_BLANK_STRING, exception.getMessage());
        }

        @ParameterizedTest
        @ValueSource(strings = { "test", "t", "te", "¥r", "¥" })
        void testWhenStringIsNotEmpty(String testParameter) {
            assertDoesNotThrow(() -> Preconditions.requireNonEmpty(testParameter));
        }
    }

    /**
     * {@link Preconditions#requireNonEmpty(String, RuntimeException)}
     * メソッドのテストケースを管理するインナークラスです。
     *
     * @author Kato Shinya
     * @since 1.0
     * @version 1.0
     */
    @Nested
    class TestRequireNonEmptyStringWithException {

        @Test
        void testWhenExceptionIsNull() {
            assertThrows(NullPointerException.class, () -> Preconditions.requireNonEmpty("", null));
        }

        @Test
        void testWhenStringIsNull() {
            String empty = null;
            assertThrows(NullPointerException.class, () -> Preconditions.requireNonEmpty(empty, new TestException()));
        }

        @Test
        void testWhenStringAndExceptionAreNull() {
            String empty = null;
            assertThrows(NullPointerException.class, () -> Preconditions.requireNonEmpty(empty, null));
        }

        @Test
        void testWhenStringIsBlank() {
            assertThrows(TestException.class, () -> Preconditions.requireNonEmpty("", new TestException()));
        }

        @ParameterizedTest
        @ValueSource(strings = { "test", "t", "te", "¥r", "¥" })
        void testWhenStringIsNotBlank(String testParameter) {
            assertDoesNotThrow(() -> Preconditions.requireNonEmpty(testParameter, new TestException()));
        }
    }

    /**
     * {@link Preconditions#requirePositive(int)} メソッドのテストケースを管理するインナークラスです。
     *
     * @author Kato Shinya
     * @since 1.0
     * @version 1.0
     */
    @Nested
    class TestRequirePositive {

        @ParameterizedTest
        @ValueSource(ints = { -1, -10, -100, -150, -500 })
        void testWhenNumberIsNegative(int testParameter) {
            final IllegalNumberFoundException exception = assertThrows(IllegalNumberFoundException.class,
                    () -> Preconditions.requirePositive(testParameter));
            assertEquals(String.format(EXCEPTION_MESSAGE_FOR_NEGATIVE_NUMBER, testParameter), exception.getMessage());
        }

        @ParameterizedTest
        @ValueSource(ints = { 0, 1, 10, 100, 150, 500 })
        void testWhenNumberIsPositive(int testParameter) {
            assertDoesNotThrow(() -> Preconditions.requirePositive(testParameter));
        }
    }

    /**
     * {@link Preconditions#requirePositive(int, RuntimeException)}
     * メソッドのテストケースを管理するインナークラスです。
     *
     * @author Kato Shinya
     * @since 1.0
     * @version 1.0
     */
    @Nested
    class TestRequirePositiveWithException {

        @Test
        void testWhenExceptionIsNull() {
            assertThrows(NullPointerException.class, () -> Preconditions.requirePositive(0, null));
        }

        @Test
        void testWhenNumberIsNegativeAndExceptionIsNull() {
            assertThrows(NullPointerException.class, () -> Preconditions.requirePositive(-1, null));
        }

        @ParameterizedTest
        @ValueSource(ints = { -1, -10, -100, -150, -500 })
        void testWhenNumberIsNegative(int testParameter) {
            assertThrows(TestException.class, () -> Preconditions.requirePositive(testParameter, new TestException()));
        }

        @ParameterizedTest
        @ValueSource(ints = { 0, 1, 10, 100, 150, 500 })
        void testWhenNumberIsPositive(int testParameter) {
            assertDoesNotThrow(() -> Preconditions.requirePositive(testParameter, new TestException()));
        }
    }

    /**
     * {@link Preconditions#requireNegative(int)} メソッドのテストケースを管理するインナークラスです。
     *
     * @author Kato Shinya
     * @since 1.0
     * @version 1.0
     */
    @Nested
    class TestRequireNegative {

        @ParameterizedTest
        @ValueSource(ints = { 0, 1, 10, 100, 150, 500 })
        void testWhenNumberIsPositive(int testParameter) {
            final IllegalNumberFoundException exception = assertThrows(IllegalNumberFoundException.class,
                    () -> Preconditions.requireNegative(testParameter));
            assertEquals(String.format(EXCEPTION_MESSAGE_FOR_POSITIVE_NUMBER, testParameter), exception.getMessage());
        }

        @ParameterizedTest
        @ValueSource(ints = { -1, -10, -100, -150, -500 })
        void testWhenNumberIsNegative(int testParameter) {
            assertDoesNotThrow(() -> Preconditions.requireNegative(testParameter));
        }
    }

    /**
     * {@link Preconditions#requireNegative(int, RuntimeException)}
     * メソッドのテストケースを管理するインナークラスです。
     *
     * @author Kato Shinya
     * @since 1.0
     * @version 1.0
     */
    @Nested
    class TestRequireNegativeWithException {

        @Test
        void testWhenExceptionIsNull() {
            assertThrows(NullPointerException.class, () -> Preconditions.requireNegative(-1, null));
        }

        @Test
        void testWhenNumberIsPositiveAndExceptionIsNull() {
            assertThrows(NullPointerException.class, () -> Preconditions.requireNegative(0, null));
        }

        @ParameterizedTest
        @ValueSource(ints = { 0, 1, 10, 100, 150, 500 })
        void testWhenNumberIsPositive(int testParameter) {
            assertThrows(TestException.class, () -> Preconditions.requireNegative(testParameter, new TestException()));
        }

        @ParameterizedTest
        @ValueSource(ints = { -1, -10, -100, -150, -500 })
        void testWhenNumberIsNegative(int testParameter) {
            assertDoesNotThrow(() -> Preconditions.requireNegative(testParameter, new TestException()));
        }
    }

    /**
     * {@link Preconditions#requireRange(int, int)} メソッドのテストケースを管理するインナークラスです。
     *
     * @author Kato Shinya
     * @since 1.0
     * @version 1.0
     */
    @Nested
    class TestRequireRangeTo {

        @ParameterizedTest
        @ValueSource(ints = { 1, 10, 100, 150, 1000 })
        void testWhenNumberIsOutOfRangeFromPositiveToZero(int testParameter) {
            assertThrows(IndexOutOfBoundsException.class, () -> Preconditions.requireRange(testParameter, 0));
        }

        @ParameterizedTest
        @ValueSource(ints = { -100, -50, -10, -1 })
        void testWhenNumberIsInRangeFromNegativeToZero(int testParameter) {
            assertDoesNotThrow(() -> Preconditions.requireRange(testParameter, 0));
        }
    }

    /**
     * {@link Preconditions#requireRange(int, int, RuntimeException)}
     * メソッドのテストケースを管理するインナークラスです。
     *
     * @author Kato Shinya
     * @since 1.0
     * @version 1.0
     */
    @Nested
    class TestRequireRangeToWithException {

        @Test
        void testWhenExceptionIsNull() {
            assertThrows(NullPointerException.class, () -> Preconditions.requireRange(0, 10, null));
        }

        @ParameterizedTest
        @ValueSource(ints = { 1, 10, 100, 150, 1000 })
        void testWhenNumberIsOutOfRangeFromPositiveToZero(int testParameter) {
            assertThrows(IndexOutOfBoundsException.class, () -> Preconditions.requireRange(testParameter, 0));
        }

        @ParameterizedTest
        @ValueSource(ints = { -100, -50, -10, -1 })
        void testWhenNumberIsInRangeFromNegativeToZero(int testParameter) {
            assertDoesNotThrow(() -> Preconditions.requireRange(testParameter, 0));
        }
    }

    /**
     * {@link Preconditions#requireRange(int, int, RuntimeException)}
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
     * {@link Preconditions#requireRange(int, int, int, RuntimeException)}
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
     * {@link Preconditions#requireNonEmpty(java.util.List)}
     * メソッドのテストケースを管理するインナークラスです。
     *
     * @author Kato Shinya
     * @since 1.0
     * @version 1.0
     */
    @Nested
    class TestRequireNonEmptyList {
    }

    /**
     * {@link Preconditions#requireNonEmpty(java.util.List, RuntimeException)}
     * メソッドのテストケースを管理するインナークラスです。
     *
     * @author Kato Shinya
     * @since 1.0
     * @version 1.0
     */
    @Nested
    class TestRequireNonEmptyListWithException {
    }

    /**
     * {@link Preconditions#requireNonEmpty(java.util.Map)}
     * メソッドのテストケースを管理するインナークラスです。
     *
     * @author Kato Shinya
     * @since 1.0
     * @version 1.0
     */
    @Nested
    class TestRequireNonEmptyMap {
    }

    /**
     * {@link Preconditions#requireNonEmpty(java.util.Map, RuntimeException)}
     * メソッドのテストケースを管理するインナークラスです。
     *
     * @author Kato Shinya
     * @since 1.0
     * @version 1.0
     */
    @Nested
    class TestRequireNonEmptyMapWithException {
    }

    /**
     * {@link Preconditions#requireNonEmpty(Object[])} メソッドのテストケースを管理するインナークラスです。
     *
     * @author Kato Shinya
     * @since 1.0
     * @version 1.0
     */
    @Nested
    class TestRequireNonEmptyArray {
    }

    /**
     * {@link Preconditions#requireNonEmpty(Object[], RuntimeException)}
     * メソッドのテストケースを管理するインナークラスです。
     *
     * @author Kato Shinya
     * @since 1.0
     * @version 1.0
     */
    @Nested
    class TestRequireNonEmptyArrayWithException {
    }
}
