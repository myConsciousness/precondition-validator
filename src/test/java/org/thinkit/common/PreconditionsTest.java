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

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * {@link Preconditions} インターフェースのテストクラスです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
final class PreconditionsTest {

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
            final IllegalStringFoundException exception = assertThrows(IllegalStringFoundException.class,
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
            RuntimeException emptyException = null;
            assertThrows(NullPointerException.class,
                    () -> Preconditions.requireNonBlank(testParameter, emptyException));
        }

        @Test
        void testWhenStringIsBlank() {
            assertThrows(TestException.class, () -> Preconditions.requireNonBlank("", new TestException()));
        }

        @Test
        void testWhenStringIsBlankAndExceptionIsNull() {
            RuntimeException emptyException = null;
            assertThrows(NullPointerException.class, () -> Preconditions.requireNonBlank("", emptyException));
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
            final IllegalStringFoundException exception = assertThrows(IllegalStringFoundException.class,
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
            RuntimeException emptyException = null;
            assertThrows(NullPointerException.class, () -> Preconditions.requireNonEmpty("", emptyException));
        }

        @Test
        void testWhenStringIsNull() {
            String empty = null;
            assertThrows(NullPointerException.class, () -> Preconditions.requireNonEmpty(empty, new TestException()));
        }

        @Test
        void testWhenStringAndExceptionAreNull() {
            String empty = null;
            RuntimeException emptyException = null;
            assertThrows(NullPointerException.class, () -> Preconditions.requireNonEmpty(empty, emptyException));
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
            RuntimeException emptyException = null;
            assertThrows(NullPointerException.class, () -> Preconditions.requirePositive(0, emptyException));
        }

        @Test
        void testWhenNumberIsNegativeAndExceptionIsNull() {
            RuntimeException emptyException = null;
            assertThrows(NullPointerException.class, () -> Preconditions.requirePositive(-1, emptyException));
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
     * {@link Preconditions#requirePositive(long)} メソッドのテストケースを管理するインナークラスです。
     *
     * @author Kato Shinya
     * @since 1.0
     * @version 1.0
     */
    @Nested
    class TestRequireLongPositive {

        @ParameterizedTest
        @ValueSource(longs = { -1, -10, -100, -150, -500 })
        void testWhenNumberIsNegative(long testParameter) {
            final IllegalNumberFoundException exception = assertThrows(IllegalNumberFoundException.class,
                    () -> Preconditions.requirePositive(testParameter));
            assertEquals(String.format(EXCEPTION_MESSAGE_FOR_NEGATIVE_LONG_NUMBER, testParameter),
                    exception.getMessage());
        }

        @ParameterizedTest
        @ValueSource(longs = { 0, 1, 10, 100, 150, 500 })
        void testWhenNumberIsPositive(long testParameter) {
            assertDoesNotThrow(() -> Preconditions.requirePositive(testParameter));
        }
    }

    /**
     * {@link Preconditions#requirePositive(long, RuntimeException)}
     * メソッドのテストケースを管理するインナークラスです。
     *
     * @author Kato Shinya
     * @since 1.0
     * @version 1.0
     */
    @Nested
    class TestRequireLongPositiveWithException {

        @Test
        void testWhenExceptionIsNull() {
            RuntimeException emptyException = null;
            assertThrows(NullPointerException.class, () -> Preconditions.requirePositive(0L, emptyException));
        }

        @Test
        void testWhenNumberIsNegativeAndExceptionIsNull() {
            RuntimeException emptyException = null;
            assertThrows(NullPointerException.class, () -> Preconditions.requirePositive(-1L, emptyException));
        }

        @ParameterizedTest
        @ValueSource(longs = { -1, -10, -100, -150, -500 })
        void testWhenNumberIsNegative(long testParameter) {
            assertThrows(TestException.class, () -> Preconditions.requirePositive(testParameter, new TestException()));
        }

        @ParameterizedTest
        @ValueSource(longs = { 0, 1, 10, 100, 150, 500 })
        void testWhenNumberIsPositive(long testParameter) {
            assertDoesNotThrow(() -> Preconditions.requirePositive(testParameter, new TestException()));
        }
    }

    /**
     * {@link Preconditions#requirePositive(short)} メソッドのテストケースを管理するインナークラスです。
     *
     * @author Kato Shinya
     * @since 1.0
     * @version 1.0
     */
    @Nested
    class TestRequireShortPositive {

        @ParameterizedTest
        @ValueSource(shorts = { -1, -10, -100, -150, -500 })
        void testWhenNumberIsNegative(short testParameter) {
            final IllegalNumberFoundException exception = assertThrows(IllegalNumberFoundException.class,
                    () -> Preconditions.requirePositive(testParameter));
            assertEquals(String.format(EXCEPTION_MESSAGE_FOR_NEGATIVE_SHORT_NUMBER, testParameter),
                    exception.getMessage());
        }

        @ParameterizedTest
        @ValueSource(shorts = { 0, 1, 10, 100, 150, 500 })
        void testWhenNumberIsPositive(short testParameter) {
            assertDoesNotThrow(() -> Preconditions.requirePositive(testParameter));
        }
    }

    /**
     * {@link Preconditions#requirePositive(short, RuntimeException)}
     * メソッドのテストケースを管理するインナークラスです。
     *
     * @author Kato Shinya
     * @since 1.0
     * @version 1.0
     */
    @Nested
    class TestRequireShortPositiveWithException {

        @Test
        void testWhenExceptionIsNull() {
            RuntimeException emptyException = null;
            short number = 0;
            assertThrows(NullPointerException.class, () -> Preconditions.requirePositive(number, emptyException));
        }

        @Test
        void testWhenNumberIsNegativeAndExceptionIsNull() {
            RuntimeException emptyException = null;
            short number = -1;
            assertThrows(NullPointerException.class, () -> Preconditions.requirePositive(number, emptyException));
        }

        @ParameterizedTest
        @ValueSource(shorts = { -1, -10, -100, -150, -500 })
        void testWhenNumberIsNegative(short testParameter) {
            assertThrows(TestException.class, () -> Preconditions.requirePositive(testParameter, new TestException()));
        }

        @ParameterizedTest
        @ValueSource(shorts = { 0, 1, 10, 100, 150, 500 })
        void testWhenNumberIsPositive(short testParameter) {
            assertDoesNotThrow(() -> Preconditions.requirePositive(testParameter, new TestException()));
        }
    }

    /**
     * {@link Preconditions#requirePositive(byte)} メソッドのテストケースを管理するインナークラスです。
     *
     * @author Kato Shinya
     * @since 1.0
     * @version 1.0
     */
    @Nested
    class TestRequireBytePositive {

        @ParameterizedTest
        @ValueSource(bytes = { -1, -2, -10, -100 })
        void testWhenNumberIsNegative(byte testParameter) {
            final IllegalNumberFoundException exception = assertThrows(IllegalNumberFoundException.class,
                    () -> Preconditions.requirePositive(testParameter));
            assertEquals(String.format(EXCEPTION_MESSAGE_FOR_NEGATIVE_BYTE_NUMBER, testParameter),
                    exception.getMessage());
        }

        @ParameterizedTest
        @ValueSource(bytes = { 0, 1, 2, 10, 100 })
        void testWhenNumberIsPositive(byte testParameter) {
            assertDoesNotThrow(() -> Preconditions.requirePositive(testParameter));
        }
    }

    /**
     * {@link Preconditions#requirePositive(byte, RuntimeException)}
     * メソッドのテストケースを管理するインナークラスです。
     *
     * @author Kato Shinya
     * @since 1.0
     * @version 1.0
     */
    @Nested
    class TestRequireBytePositiveWithException {

        @Test
        void testWhenExceptionIsNull() {
            RuntimeException emptyException = null;
            byte number = 0;
            assertThrows(NullPointerException.class, () -> Preconditions.requirePositive(number, emptyException));
        }

        @Test
        void testWhenNumberIsNegativeAndExceptionIsNull() {
            RuntimeException emptyException = null;
            byte number = -1;
            assertThrows(NullPointerException.class, () -> Preconditions.requirePositive(number, emptyException));
        }

        @ParameterizedTest
        @ValueSource(bytes = { -1, -2, -10, -100 })
        void testWhenNumberIsNegative(byte testParameter) {
            assertThrows(TestException.class, () -> Preconditions.requirePositive(testParameter, new TestException()));
        }

        @ParameterizedTest
        @ValueSource(bytes = { 0, 1, 2, 10, 100 })
        void testWhenNumberIsPositive(byte testParameter) {
            assertDoesNotThrow(() -> Preconditions.requirePositive(testParameter, new TestException()));
        }
    }

    /**
     * {@link Preconditions#requirePositive(float)} メソッドのテストケースを管理するインナークラスです。
     *
     * @author Kato Shinya
     * @since 1.0
     * @version 1.0
     */
    @Nested
    class TestRequireFloatPositive {

        @ParameterizedTest
        @ValueSource(floats = { -0.1f, 0.01f - 0.2f, -10.0f, -100.0f })
        void testWhenNumberIsNegative(float testParameter) {
            final IllegalNumberFoundException exception = assertThrows(IllegalNumberFoundException.class,
                    () -> Preconditions.requirePositive(testParameter));
            assertEquals(String.format(EXCEPTION_MESSAGE_FOR_NEGATIVE_FLOAT_NUMBER, testParameter),
                    exception.getMessage());
        }

        @ParameterizedTest
        @ValueSource(floats = { 0.0f, 0.01f, 0.1f, 0.2f, 10.0f, 100.0f })
        void testWhenNumberIsPositive(float testParameter) {
            assertDoesNotThrow(() -> Preconditions.requirePositive(testParameter));
        }
    }

    /**
     * {@link Preconditions#requirePositive(float, RuntimeException)}
     * メソッドのテストケースを管理するインナークラスです。
     *
     * @author Kato Shinya
     * @since 1.0
     * @version 1.0
     */
    @Nested
    class TestRequireFloatPositiveWithException {

        @Test
        void testWhenExceptionIsNull() {
            RuntimeException emptyException = null;
            float number = 0.0f;
            assertThrows(NullPointerException.class, () -> Preconditions.requirePositive(number, emptyException));
        }

        @Test
        void testWhenNumberIsNegativeAndExceptionIsNull() {
            RuntimeException emptyException = null;
            float number = -1.0f;
            assertThrows(NullPointerException.class, () -> Preconditions.requirePositive(number, emptyException));
        }

        @ParameterizedTest
        @ValueSource(floats = { -0.1f, 0.01f - 0.2f, -10.0f, -100.0f })
        void testWhenNumberIsNegative(float testParameter) {
            assertThrows(TestException.class, () -> Preconditions.requirePositive(testParameter, new TestException()));
        }

        @ParameterizedTest
        @ValueSource(floats = { 0.0f, 0.01f, 0.1f, 0.2f, 10.0f, 100.0f })
        void testWhenNumberIsPositive(float testParameter) {
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
            RuntimeException emptyException = null;
            assertThrows(NullPointerException.class, () -> Preconditions.requireNegative(-1, emptyException));
        }

        @Test
        void testWhenNumberIsPositiveAndExceptionIsNull() {
            RuntimeException emptyException = null;
            assertThrows(NullPointerException.class, () -> Preconditions.requireNegative(0, emptyException));
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
     * {@link Preconditions#requireNegative(long)} メソッドのテストケースを管理するインナークラスです。
     *
     * @author Kato Shinya
     * @since 1.0
     * @version 1.0
     */
    @Nested
    class TestRequireLongNegative {

        @ParameterizedTest
        @ValueSource(longs = { 0, 1, 10, 100, 150, 500 })
        void testWhenNumberIsPositive(long testParameter) {
            final IllegalNumberFoundException exception = assertThrows(IllegalNumberFoundException.class,
                    () -> Preconditions.requireNegative(testParameter));
            assertEquals(String.format(EXCEPTION_MESSAGE_FOR_POSITIVE_LONG_NUMBER, testParameter),
                    exception.getMessage());
        }

        @ParameterizedTest
        @ValueSource(longs = { -1, -10, -100, -150, -500 })
        void testWhenNumberIsNegative(long testParameter) {
            assertDoesNotThrow(() -> Preconditions.requireNegative(testParameter));
        }
    }

    /**
     * {@link Preconditions#requireNegative(long, RuntimeException)}
     * メソッドのテストケースを管理するインナークラスです。
     *
     * @author Kato Shinya
     * @since 1.0
     * @version 1.0
     */
    @Nested
    class TestRequireLongNegativeWithException {

        @Test
        void testWhenExceptionIsNull() {
            RuntimeException emptyException = null;
            assertThrows(NullPointerException.class, () -> Preconditions.requireNegative(-1L, emptyException));
        }

        @Test
        void testWhenNumberIsPositiveAndExceptionIsNull() {
            RuntimeException emptyException = null;
            assertThrows(NullPointerException.class, () -> Preconditions.requireNegative(0L, emptyException));
        }

        @ParameterizedTest
        @ValueSource(longs = { 0, 1, 10, 100, 150, 500 })
        void testWhenNumberIsPositive(long testParameter) {
            assertThrows(TestException.class, () -> Preconditions.requireNegative(testParameter, new TestException()));
        }

        @ParameterizedTest
        @ValueSource(longs = { -1, -10, -100, -150, -500 })
        void testWhenNumberIsNegative(long testParameter) {
            assertDoesNotThrow(() -> Preconditions.requireNegative(testParameter, new TestException()));
        }
    }

    /**
     * {@link Preconditions#requireNegative(short)} メソッドのテストケースを管理するインナークラスです。
     *
     * @author Kato Shinya
     * @since 1.0
     * @version 1.0
     */
    @Nested
    class TestRequireShortNegative {

        @ParameterizedTest
        @ValueSource(shorts = { 0, 1, 10, 100, 150, 500 })
        void testWhenNumberIsPositive(short testParameter) {
            final IllegalNumberFoundException exception = assertThrows(IllegalNumberFoundException.class,
                    () -> Preconditions.requireNegative(testParameter));
            assertEquals(String.format(EXCEPTION_MESSAGE_FOR_POSITIVE_SHORT_NUMBER, testParameter),
                    exception.getMessage());
        }

        @ParameterizedTest
        @ValueSource(shorts = { -1, -10, -100, -150, -500 })
        void testWhenNumberIsNegative(short testParameter) {
            assertDoesNotThrow(() -> Preconditions.requireNegative(testParameter));
        }
    }

    /**
     * {@link Preconditions#requireNegative(short, RuntimeException)}
     * メソッドのテストケースを管理するインナークラスです。
     *
     * @author Kato Shinya
     * @since 1.0
     * @version 1.0
     */
    @Nested
    class TestRequireShortNegativeWithException {

        @Test
        void testWhenExceptionIsNull() {
            RuntimeException emptyException = null;
            short number = -1;
            assertThrows(NullPointerException.class, () -> Preconditions.requireNegative(number, emptyException));
        }

        @Test
        void testWhenNumberIsPositiveAndExceptionIsNull() {
            RuntimeException emptyException = null;
            short number = 0;
            assertThrows(NullPointerException.class, () -> Preconditions.requireNegative(number, emptyException));
        }

        @ParameterizedTest
        @ValueSource(shorts = { 0, 1, 10, 100, 150, 500 })
        void testWhenNumberIsPositive(short testParameter) {
            assertThrows(TestException.class, () -> Preconditions.requireNegative(testParameter, new TestException()));
        }

        @ParameterizedTest
        @ValueSource(shorts = { -1, -10, -100, -150, -500 })
        void testWhenNumberIsNegative(short testParameter) {
            assertDoesNotThrow(() -> Preconditions.requireNegative(testParameter, new TestException()));
        }
    }

    /**
     * {@link Preconditions#requireNegative(byte)} メソッドのテストケースを管理するインナークラスです。
     *
     * @author Kato Shinya
     * @since 1.0
     * @version 1.0
     */
    @Nested
    class TestRequireByteNegative {

        @ParameterizedTest
        @ValueSource(bytes = { 0, 1, 2, 10, 100 })
        void testWhenNumberIsPositive(byte testParameter) {
            final IllegalNumberFoundException exception = assertThrows(IllegalNumberFoundException.class,
                    () -> Preconditions.requireNegative(testParameter));
            assertEquals(String.format(EXCEPTION_MESSAGE_FOR_POSITIVE_BYTE_NUMBER, testParameter),
                    exception.getMessage());
        }

        @ParameterizedTest
        @ValueSource(bytes = { -1, -2, -10, -100 })
        void testWhenNumberIsNegative(byte testParameter) {
            assertDoesNotThrow(() -> Preconditions.requireNegative(testParameter));
        }
    }

    /**
     * {@link Preconditions#requireNegative(byte, RuntimeException)}
     * メソッドのテストケースを管理するインナークラスです。
     *
     * @author Kato Shinya
     * @since 1.0
     * @version 1.0
     */
    @Nested
    class TestRequireBytesNegativeWithException {

        @Test
        void testWhenExceptionIsNull() {
            RuntimeException emptyException = null;
            byte number = -1;
            assertThrows(NullPointerException.class, () -> Preconditions.requireNegative(number, emptyException));
        }

        @Test
        void testWhenNumberIsPositiveAndExceptionIsNull() {
            RuntimeException emptyException = null;
            byte number = 0;
            assertThrows(NullPointerException.class, () -> Preconditions.requireNegative(number, emptyException));
        }

        @ParameterizedTest
        @ValueSource(bytes = { 0, 1, 2, 10, 100 })
        void testWhenNumberIsPositive(byte testParameter) {
            assertThrows(TestException.class, () -> Preconditions.requireNegative(testParameter, new TestException()));
        }

        @ParameterizedTest
        @ValueSource(bytes = { -1, -2, -10, -100 })
        void testWhenNumberIsNegative(byte testParameter) {
            assertDoesNotThrow(() -> Preconditions.requireNegative(testParameter, new TestException()));
        }
    }

    /**
     * {@link Preconditions#requireNegative(float)} メソッドのテストケースを管理するインナークラスです。
     *
     * @author Kato Shinya
     * @since 1.0
     * @version 1.0
     */
    @Nested
    class TestRequireFloatNegative {

        @ParameterizedTest
        @ValueSource(floats = { 0.0f, 0.01f, 0.1f, 0.2f, 10.0f, 100.0f })
        void testWhenNumberIsPositive(float testParameter) {
            final IllegalNumberFoundException exception = assertThrows(IllegalNumberFoundException.class,
                    () -> Preconditions.requireNegative(testParameter));
            assertEquals(String.format(EXCEPTION_MESSAGE_FOR_POSITIVE_FLOAT_NUMBER, testParameter),
                    exception.getMessage());
        }

        @ParameterizedTest
        @ValueSource(floats = { -0.1f, 0.01f - 0.2f, -10.0f, -100.0f })
        void testWhenNumberIsNegative(float testParameter) {
            assertDoesNotThrow(() -> Preconditions.requireNegative(testParameter));
        }
    }

    /**
     * {@link Preconditions#requireNegative(float, RuntimeException)}
     * メソッドのテストケースを管理するインナークラスです。
     *
     * @author Kato Shinya
     * @since 1.0
     * @version 1.0
     */
    @Nested
    class TestRequireFloatNegativeWithException {

        @Test
        void testWhenExceptionIsNull() {
            RuntimeException emptyException = null;
            float number = -1.0f;
            assertThrows(NullPointerException.class, () -> Preconditions.requireNegative(number, emptyException));
        }

        @Test
        void testWhenNumberIsPositiveAndExceptionIsNull() {
            RuntimeException emptyException = null;
            float number = 0.0f;
            assertThrows(NullPointerException.class, () -> Preconditions.requireNegative(number, emptyException));
        }

        @ParameterizedTest
        @ValueSource(floats = { 0.0f, 0.01f, 0.1f, 0.2f, 10.0f, 100.0f })
        void testWhenNumberIsPositive(float testParameter) {
            assertThrows(TestException.class, () -> Preconditions.requireNegative(testParameter, new TestException()));
        }

        @ParameterizedTest
        @ValueSource(floats = { -0.1f, 0.01f - 0.2f, -10.0f, -100.0f })
        void testWhenNumberIsNegative(float testParameter) {
            assertDoesNotThrow(() -> Preconditions.requireNegative(testParameter, new TestException()));
        }
    }

    /**
     * {@link Preconditions#requireRangeFrom(int, int)} メソッドのテストケースを管理するインナークラスです。
     *
     * @author Kato Shinya
     * @since 1.0
     * @version 1.0
     */
    @Nested
    class TestRequireRangeFrom {

        @ParameterizedTest
        @ValueSource(ints = { 1, 10, 100, 150, 1000 })
        void testWhenNumberIsOutOfRangeFromPositiveToZero(int testParameter) {
            final IndexOutOfBoundsException exception = assertThrows(IndexOutOfBoundsException.class,
                    () -> Preconditions.requireRangeFrom(testParameter, testParameter + 1));
            assertEquals(String.format(EXCEPTION_MESSAGE_FOR_OUT_OF_BOUNDS_FROM, testParameter, testParameter + 1),
                    exception.getMessage());
        }

        @ParameterizedTest
        @ValueSource(ints = { -100, -50, -10, -1 })
        void testWhenNumberIsInRangeFromNegativeToZero(int testParameter) {
            assertDoesNotThrow(() -> Preconditions.requireRangeFrom(testParameter, testParameter - 1));
        }
    }

    /**
     * {@link Preconditions#requireRangeFrom(int, int, RuntimeException)}
     * メソッドのテストケースを管理するインナークラスです。
     *
     * @author Kato Shinya
     * @since 1.0
     * @version 1.0
     */
    @Nested
    class TestRequireRangeFromWithException {

        @Test
        void testWhenExceptionIsNull() {
            RuntimeException emptyException = null;
            assertThrows(NullPointerException.class, () -> Preconditions.requireRangeFrom(0, 10, emptyException));
        }

        @ParameterizedTest
        @ValueSource(ints = { 1, 10, 100, 150, 1000 })
        void testWhenNumberIsOutOfRangeFromPositiveToZero(int testParameter) {
            assertThrows(IndexOutOfBoundsException.class,
                    () -> Preconditions.requireRangeFrom(testParameter, testParameter + 1));
        }

        @ParameterizedTest
        @ValueSource(ints = { -100, -50, -10, -1 })
        void testWhenNumberIsInRangeFromNegativeToZero(int testParameter) {
            assertDoesNotThrow(() -> Preconditions.requireRangeFrom(testParameter, testParameter - 1));
        }
    }

    /**
     * {@link Preconditions#requireRangeTo(int, int)} メソッドのテストケースを管理するインナークラスです。
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
            final IndexOutOfBoundsException exception = assertThrows(IndexOutOfBoundsException.class,
                    () -> Preconditions.requireRangeTo(testParameter, testParameter - 1));
            assertEquals(String.format(EXCEPTION_MESSAGE_FOR_OUT_OF_BOUNDS_TO, testParameter, testParameter - 1),
                    exception.getMessage());
        }

        @ParameterizedTest
        @ValueSource(ints = { -100, -50, -10, -1 })
        void testWhenNumberIsInRangeFromNegativeToZero(int testParameter) {
            assertDoesNotThrow(() -> Preconditions.requireRangeTo(testParameter, testParameter + 1));
        }
    }

    /**
     * {@link Preconditions#requireRangeTo(int, int, RuntimeException)}
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
            RuntimeException emptyException = null;
            assertThrows(NullPointerException.class, () -> Preconditions.requireRangeTo(0, 10, emptyException));
        }

        @ParameterizedTest
        @ValueSource(ints = { 1, 10, 100, 150, 1000 })
        void testWhenNumberIsOutOfRangeFromPositiveToZero(int testParameter) {
            assertThrows(IndexOutOfBoundsException.class,
                    () -> Preconditions.requireRangeTo(testParameter, testParameter - 1));
        }

        @ParameterizedTest
        @ValueSource(ints = { -100, -50, -10, -1 })
        void testWhenNumberIsInRangeFromNegativeToZero(int testParameter) {
            assertDoesNotThrow(() -> Preconditions.requireRangeTo(testParameter, testParameter + 1));
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
    @TestInstance(Lifecycle.PER_CLASS)
    class TestRequireRangeFromTo {

        Stream<Arguments> negativeNumbersProvider() {
            return Stream.of(Arguments.of(-5, -10, -1), Arguments.of(-2, -10, -1), Arguments.of(-9, -10, -1),
                    Arguments.of(-1, -10, -1), Arguments.of(-10, -10, -1));
        }

        Stream<Arguments> badNegativeNumbersProvider() {
            return Stream.of(Arguments.of(0, -10, -1), Arguments.of(-11, -10, -1));
        }

        Stream<Arguments> negativeAndPositiveNumbersProvider() {
            return Stream.of(Arguments.of(0, -5, 5), Arguments.of(-4, -5, 5), Arguments.of(4, -5, 5),
                    Arguments.of(-5, -5, 5), Arguments.of(5, -5, 5));
        }

        Stream<Arguments> badNegativeAndPositiveNumbersProvider() {
            return Stream.of(Arguments.of(6, -5, 5), Arguments.of(-6, -5, 5));
        }

        Stream<Arguments> positiveNumbersProvider() {
            return Stream.of(Arguments.of(5, 0, 10), Arguments.of(1, 0, 10), Arguments.of(9, 0, 10),
                    Arguments.of(0, 0, 10), Arguments.of(10, 0, 10));
        }

        Stream<Arguments> badPositiveNumbersProvider() {
            return Stream.of(Arguments.of(11, 0, 10), Arguments.of(-1, 0, 10));
        }

        @ParameterizedTest
        @MethodSource("negativeNumbersProvider")
        void testWhenInRangeFromNegativeToNegative(int index, int from, int to) {
            assertDoesNotThrow(() -> Preconditions.requireRange(index, from, to));
        }

        @ParameterizedTest
        @MethodSource("negativeAndPositiveNumbersProvider")
        void testWhenInRangeFromNegativeToPositive(int index, int from, int to) {
            assertDoesNotThrow(() -> Preconditions.requireRange(index, from, to));
        }

        @ParameterizedTest
        @MethodSource("positiveNumbersProvider")
        void testWhenInRangeFromPositiveToPositive(int index, int from, int to) {
            assertDoesNotThrow(() -> Preconditions.requireRange(index, from, to));
        }

        @ParameterizedTest
        @MethodSource("badNegativeNumbersProvider")
        void testWhenOutOfRangeFromNegativeToNegative(int index, int from, int to) {
            final IndexOutOfBoundsException exception = assertThrows(IndexOutOfBoundsException.class,
                    () -> Preconditions.requireRange(index, from, to));
            assertEquals(String.format(EXCEPTION_MESSAGE_FOR_OUT_OF_BOUNDS_FROM_TO, index, from, to),
                    exception.getMessage());
        }

        @ParameterizedTest
        @MethodSource("badNegativeAndPositiveNumbersProvider")
        void testWhenOutOfRangeFromNegativeToPositive(int index, int from, int to) {
            final IndexOutOfBoundsException exception = assertThrows(IndexOutOfBoundsException.class,
                    () -> Preconditions.requireRange(index, from, to));
            assertEquals(String.format(EXCEPTION_MESSAGE_FOR_OUT_OF_BOUNDS_FROM_TO, index, from, to),
                    exception.getMessage());
        }

        @ParameterizedTest
        @MethodSource("badPositiveNumbersProvider")
        void testWhenOutOfRangeFromPositiveToPositive(int index, int from, int to) {
            final IndexOutOfBoundsException exception = assertThrows(IndexOutOfBoundsException.class,
                    () -> Preconditions.requireRange(index, from, to));
            assertEquals(String.format(EXCEPTION_MESSAGE_FOR_OUT_OF_BOUNDS_FROM_TO, index, from, to),
                    exception.getMessage());
        }
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
    @TestInstance(Lifecycle.PER_CLASS)
    class TestRequireRangeFromToWithException {

        Stream<Arguments> negativeNumbersProvider() {
            return Stream.of(Arguments.of(-5, -10, -1), Arguments.of(-2, -10, -1), Arguments.of(-9, -10, -1),
                    Arguments.of(-1, -10, -1), Arguments.of(-10, -10, -1));
        }

        Stream<Arguments> badNegativeNumbersProvider() {
            return Stream.of(Arguments.of(0, -10, -1), Arguments.of(-11, -10, -1));
        }

        Stream<Arguments> negativeAndPositiveNumbersProvider() {
            return Stream.of(Arguments.of(0, -5, 5), Arguments.of(-4, -5, 5), Arguments.of(4, -5, 5),
                    Arguments.of(-5, -5, 5), Arguments.of(5, -5, 5));
        }

        Stream<Arguments> badNegativeAndPositiveNumbersProvider() {
            return Stream.of(Arguments.of(6, -5, 5), Arguments.of(-6, -5, 5));
        }

        Stream<Arguments> positiveNumbersProvider() {
            return Stream.of(Arguments.of(5, 0, 10), Arguments.of(1, 0, 10), Arguments.of(9, 0, 10),
                    Arguments.of(0, 0, 10), Arguments.of(10, 0, 10));
        }

        Stream<Arguments> badPositiveNumbersProvider() {
            return Stream.of(Arguments.of(11, 0, 10), Arguments.of(-1, 0, 10));
        }

        @ParameterizedTest
        @MethodSource("negativeNumbersProvider")
        void testWhenInRangeFromNegativeToNegative(int index, int from, int to) {
            assertDoesNotThrow(() -> Preconditions.requireRange(index, from, to, new TestException()));
        }

        @Test
        void testWhenExceptionIsNull() {
            RuntimeException emptyException = null;
            assertThrows(NullPointerException.class, () -> Preconditions.requireRange(1, 0, 2, emptyException));
        }

        @ParameterizedTest
        @MethodSource("negativeAndPositiveNumbersProvider")
        void testWhenInRangeFromNegativeToPositive(int index, int from, int to) {
            assertDoesNotThrow(() -> Preconditions.requireRange(index, from, to, new TestException()));
        }

        @ParameterizedTest
        @MethodSource("positiveNumbersProvider")
        void testWhenInRangeFromPositiveToPositive(int index, int from, int to) {
            assertDoesNotThrow(() -> Preconditions.requireRange(index, from, to, new TestException()));
        }

        @ParameterizedTest
        @MethodSource("badNegativeNumbersProvider")
        void testWhenOutOfRangeFromNegativeToNegative(int index, int from, int to) {
            assertThrows(TestException.class, () -> Preconditions.requireRange(index, from, to, new TestException()));
        }

        @ParameterizedTest
        @MethodSource("badNegativeAndPositiveNumbersProvider")
        void testWhenOutOfRangeFromNegativeToPositive(int index, int from, int to) {
            assertThrows(TestException.class, () -> Preconditions.requireRange(index, from, to, new TestException()));
        }

        @ParameterizedTest
        @MethodSource("badPositiveNumbersProvider")
        void testWhenOutOfRangeFromPositiveToPositive(int index, int from, int to) {
            assertThrows(TestException.class, () -> Preconditions.requireRange(index, from, to, new TestException()));
        }
    }

    /**
     * {@link Preconditions#requireNonEmpty(List)} メソッドのテストケースを管理するインナークラスです。
     *
     * @author Kato Shinya
     * @since 1.0
     * @version 1.0
     */
    @Nested
    class TestRequireNonEmptyList {

        @Test
        void testWhenListIsNull() {
            final List<String> empty = null;
            assertThrows(NullPointerException.class, () -> Preconditions.requireNonEmpty(empty));
        }

        @Test
        void testWhenListIsEmpty() {
            final IllegalListFoundException exception = assertThrows(IllegalListFoundException.class,
                    () -> Preconditions.requireNonEmpty(List.of()));
            assertEquals(EXCEPTION_MESSAGE_FOR_EMPTY_LIST, exception.getMessage());
        }

        @Test
        void testWhenListIsNotEmpty() {
            assertDoesNotThrow(() -> Preconditions.requireNonEmpty(List.of("test")));
        }
    }

    /**
     * {@link Preconditions#requireNonEmpty(List, RuntimeException)}
     * メソッドのテストケースを管理するインナークラスです。
     *
     * @author Kato Shinya
     * @since 1.0
     * @version 1.0
     */
    @Nested
    class TestRequireNonEmptyListWithException {

        @Test
        void testWhenExceptionIsNull() {
            RuntimeException emptyException = null;
            assertThrows(NullPointerException.class, () -> Preconditions.requireNonEmpty(List.of(), emptyException));
        }

        @Test
        void testWhenListIsNull() {
            final List<String> empty = null;
            assertThrows(NullPointerException.class, () -> Preconditions.requireNonEmpty(empty));
        }

        @Test
        void testWhenListIsEmpty() {
            assertThrows(TestException.class, () -> Preconditions.requireNonEmpty(List.of(), new TestException()));
        }

        @Test
        void testWhenListIsNotEmpty() {
            assertDoesNotThrow(() -> Preconditions.requireNonEmpty(List.of("test")));
        }
    }

    /**
     * {@link Preconditions#requireNonEmpty(Map)} メソッドのテストケースを管理するインナークラスです。
     *
     * @author Kato Shinya
     * @since 1.0
     * @version 1.0
     */
    @Nested
    class TestRequireNonEmptyMap {

        @Test
        void testWhenMapIsNull() {
            final Map<String, String> empty = null;
            assertThrows(NullPointerException.class, () -> Preconditions.requireNonEmpty(empty));
        }

        @Test
        void testWhenMapIsEmpty() {
            final IllegalMapFoundException exception = assertThrows(IllegalMapFoundException.class,
                    () -> Preconditions.requireNonEmpty(Map.of()));
            assertEquals(EXCEPTION_MESSAGE_FOR_EMPTY_MAP, exception.getMessage());
        }

        @Test
        void testWhenMapIsNotEmpty() {
            assertDoesNotThrow(() -> Preconditions.requireNonEmpty(Map.of("test", "test")));
        }
    }

    /**
     * {@link Preconditions#requireNonEmpty(Map, RuntimeException)}
     * メソッドのテストケースを管理するインナークラスです。
     *
     * @author Kato Shinya
     * @since 1.0
     * @version 1.0
     */
    @Nested
    class TestRequireNonEmptyMapWithException {

        @Test
        void testWhenExceptionIsNull() {
            RuntimeException emptyException = null;
            assertThrows(NullPointerException.class,
                    () -> Preconditions.requireNonEmpty(Map.of("test", "test"), emptyException));
        }

        @Test
        void testWhenMapIsNull() {
            final Map<String, String> empty = null;
            assertThrows(NullPointerException.class, () -> Preconditions.requireNonEmpty(empty, new TestException()));
        }

        @Test
        void testWhenMapIsEmpty() {
            assertThrows(TestException.class, () -> Preconditions.requireNonEmpty(Map.of(), new TestException()));
        }

        @Test
        void testWhenMapIsNotEmpty() {
            assertDoesNotThrow(() -> Preconditions.requireNonEmpty(Map.of("test", "test"), new TestException()));
        }
    }

    /**
     * {@link Preconditions#requireNonEmpty(Set)} メソッドのテストケースを管理するインナークラスです。
     *
     * @author Kato Shinya
     * @since 1.0
     * @version 1.0
     */
    @Nested
    class TestRequireNonEmptySet {

        @Test
        void testWhenSetIsNull() {
            final Set<String> empty = null;
            assertThrows(NullPointerException.class, () -> Preconditions.requireNonEmpty(empty));
        }

        @Test
        void testWhenSetIsEmpty() {
            final IllegalSetFoundException exception = assertThrows(IllegalSetFoundException.class,
                    () -> Preconditions.requireNonEmpty(Set.of()));
            assertEquals(EXCEPTION_MESSAGE_FOR_EMPTY_SET, exception.getMessage());
        }

        @Test
        void testWhenMapIsNotEmpty() {
            assertDoesNotThrow(() -> Preconditions.requireNonEmpty(Set.of("test")));
        }
    }

    /**
     * {@link Preconditions#requireNonEmpty(Set, RuntimeException)}
     * メソッドのテストケースを管理するインナークラスです。
     *
     * @author Kato Shinya
     * @since 1.0
     * @version 1.0
     */
    @Nested
    class TestRequireNonEmptySetWithException {

        @Test
        void testWhenExceptionIsNull() {
            RuntimeException emptyException = null;
            assertThrows(NullPointerException.class,
                    () -> Preconditions.requireNonEmpty(Set.of("test"), emptyException));
        }

        @Test
        void testWhenSetIsNull() {
            final Set<String> empty = null;
            assertThrows(NullPointerException.class, () -> Preconditions.requireNonEmpty(empty, new TestException()));
        }

        @Test
        void testWhenSetIsEmpty() {
            assertThrows(TestException.class, () -> Preconditions.requireNonEmpty(Set.of(), new TestException()));
        }

        @Test
        void testWhenSetIsNotEmpty() {
            assertDoesNotThrow(() -> Preconditions.requireNonEmpty(Set.of("test"), new TestException()));
        }
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

        @Test
        void testWhenArrayIsEmpty() {
            final IllegalArrayFoundException exception = assertThrows(IllegalArrayFoundException.class,
                    () -> Preconditions.requireNonEmpty(new String[] {}));
            assertEquals(EXCEPTION_MESSAGE_FOR_EMPTY_ARRAY, exception.getMessage());
        }

        @Test
        void testWhenArrayIsNotEmpty() {
            assertDoesNotThrow(() -> Preconditions.requireNonEmpty(new String[] { "" }));
        }
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

        @Test
        void testWhenExceptionIsNull() {
            RuntimeException emptyException = null;
            assertThrows(NullPointerException.class,
                    () -> Preconditions.requireNonEmpty(new String[] { "" }, emptyException));
        }

        @Test
        void testWhenArrayIsEmpty() {
            assertThrows(TestException.class,
                    () -> Preconditions.requireNonEmpty(new String[] {}, new TestException()));
        }

        @Test
        void testWhenArrayIsNotEmpty() {
            assertDoesNotThrow(() -> Preconditions.requireNonEmpty(new String[] { "" }, new TestException()));
        }
    }

    /**
     * {@link Preconditions#requireStartWith(String, String)}
     * メソッドのテストケースを管理するインナークラスです。
     *
     * @author Kato Shinya
     * @since 1.0
     * @version 1.0
     */
    @Nested
    class TestRequireStartWith {

        @ParameterizedTest
        @ValueSource(strings = { "t", "est", "e", "sequence", "est", "tset" })
        void testWhenStringDoesNotStartWith(String testParameter) {
            final String testSequence = " test sequence";
            final IllegalStringFoundException exception = assertThrows(IllegalStringFoundException.class,
                    () -> Preconditions.requireStartWith(testSequence, testParameter));
            assertEquals(String.format("String must start with the %s prefix, but %s was given", testParameter,
                    testSequence), exception.getMessage());
        }

        @ParameterizedTest
        @ValueSource(strings = { " ", " test sequence", " test", " tes", " te", " t" })
        void testWhenStringStartsWith(String testParameter) {
            assertDoesNotThrow(() -> Preconditions.requireStartWith(" test sequence", testParameter));
        }
    }

    /**
     * {@link Preconditions#requireStartWith(String, String, int)}
     * メソッドのテストケースを管理するインナークラスです。
     *
     * @author Kato Shinya
     * @since 1.0
     * @version 1.0
     */
    @Nested
    @TestInstance(Lifecycle.PER_CLASS)
    class TestRequireStartWithStringWithOffset {

        Stream<Arguments> stringAndOffsetProvider() {
            int offset = 0;
            return Stream.of(Arguments.of(" test sequence", " test sequence", offset++),
                    Arguments.of(" test sequence", "test", offset++), Arguments.of(" test sequence", "est ", offset++),
                    Arguments.of(" test sequence", "st s", offset++), Arguments.of(" test sequence", "t se", offset++),
                    Arguments.of(" test sequence", " sequence", offset++));
        }

        Stream<Arguments> badStringAndOffsetProvider() {
            int offset = 0;
            return Stream.of(Arguments.of(" test sequence", "test sequence", offset++),
                    Arguments.of(" test sequence", "est", offset++), Arguments.of(" test sequence", "st s", offset++),
                    Arguments.of(" test sequence", "t se", offset++), Arguments.of(" test sequence", " seq", offset++),
                    Arguments.of(" test sequence", "sequence", offset++));
        }

        @ParameterizedTest
        @MethodSource("badStringAndOffsetProvider")
        void testWhenStringDoesNotStartWith(String sequence, String prefix, int offset) {
            final IllegalStringFoundException exception = assertThrows(IllegalStringFoundException.class,
                    () -> Preconditions.requireStartWith(sequence, prefix, offset));
            assertEquals(String.format("String must start with the %s prefix from %s index, but %s was given", prefix,
                    offset, sequence), exception.getMessage());
        }

        @ParameterizedTest
        @MethodSource("stringAndOffsetProvider")
        void testWhenStringStartsWith(String sequence, String prefix, int offset) {
            assertDoesNotThrow(() -> Preconditions.requireStartWith(sequence, prefix, offset));
        }
    }

    /**
     * {@link Preconditions#requireStartWith(String, String, RuntimeException)}
     * メソッドのテストケースを管理するインナークラスです。
     *
     * @author Kato Shinya
     * @since 1.0
     * @version 1.0
     */
    @Nested
    class TestRequireStartWithStringWithException {

        @Test
        void testWhenExceptionIsNull() {
            RuntimeException emptyException = null;
            assertThrows(NullPointerException.class, () -> Preconditions.requireStartWith("", "", emptyException));
        }

        @ParameterizedTest
        @ValueSource(strings = { "est", "e", "sequence", "est", "tset" })
        void testWhenStringDoesNotStartWith(String testParameter) {
            assertThrows(TestException.class,
                    () -> Preconditions.requireStartWith("test sequence", testParameter, new TestException()));
        }

        @ParameterizedTest
        @ValueSource(strings = { "test sequence", "test", "tes", "te", "t" })
        void testWhenStringStartsWith(String testParameter) {
            assertDoesNotThrow(
                    () -> Preconditions.requireStartWith("test sequence", testParameter, new TestException()));
        }
    }

    /**
     * {@link Preconditions#requireStartWith(String, String, int, RuntimeException)}
     * メソッドのテストケースを管理するインナークラスです。
     *
     * @author Kato Shinya
     * @since 1.0
     * @version 1.0
     */
    @Nested
    @TestInstance(Lifecycle.PER_CLASS)
    class TestRequireStartWithStringWithOffsetAndException {

        Stream<Arguments> stringAndOffsetProvider() {
            int offset = 0;
            return Stream.of(Arguments.of(" test sequence", " test sequence", offset++),
                    Arguments.of(" test sequence", "test", offset++), Arguments.of(" test sequence", "est ", offset++),
                    Arguments.of(" test sequence", "st s", offset++), Arguments.of(" test sequence", "t se", offset++),
                    Arguments.of(" test sequence", " sequence", offset++));
        }

        Stream<Arguments> badStringAndOffsetProvider() {
            int offset = 0;
            return Stream.of(Arguments.of(" test sequence", "test sequence", offset++),
                    Arguments.of(" test sequence", "est", offset++), Arguments.of(" test sequence", "st s", offset++),
                    Arguments.of(" test sequence", "t se", offset++), Arguments.of(" test sequence", " seq", offset++),
                    Arguments.of(" test sequence", "sequence", offset++));
        }

        @Test
        void testWhenExceptionIsNull() {
            RuntimeException emptyException = null;
            assertThrows(NullPointerException.class,
                    () -> Preconditions.requireStartWith("test", "test", 0, emptyException));
        }

        @ParameterizedTest
        @MethodSource("badStringAndOffsetProvider")
        void testWhenStringDoesNotStartWith(String sequence, String prefix, int offset) {
            assertThrows(TestException.class,
                    () -> Preconditions.requireStartWith(sequence, prefix, offset, new TestException()));

        }

        @ParameterizedTest
        @MethodSource("stringAndOffsetProvider")
        void testWhenStringStartsWith(String sequence, String prefix, int offset) {
            assertDoesNotThrow(() -> Preconditions.requireStartWith(sequence, prefix, offset, new TestException()));
        }
    }

    /**
     * {@link Preconditions#requireEndWith(String, String)}
     * メソッドのテストケースを管理するインナークラスです。
     *
     * @author Kato Shinya
     * @since 1.0
     * @version 1.0
     */
    @Nested
    class TestRequireEndWith {

        @ParameterizedTest
        @ValueSource(strings = { "test", "sequence", "c", "ce", "nce", "ence" })
        void testWhenStringDoesNotEndWith(String testParameter) {
            final String testSequence = "test sequence ";
            final IllegalStringFoundException exception = assertThrows(IllegalStringFoundException.class,
                    () -> Preconditions.requireEndWith(testSequence, testParameter));
            assertEquals(
                    String.format("String must end with the %s suffix, but %s was given", testParameter, testSequence),
                    exception.getMessage());
        }

        @ParameterizedTest
        @ValueSource(strings = { " ", "test sequence ", "e ", "ce ", "nce ", "ence " })
        void testWhenStringEndsWith(String testParameter) {
            assertDoesNotThrow(() -> Preconditions.requireEndWith("test sequence ", testParameter));
        }
    }

    /**
     * {@link Preconditions#requireEndWith(String, String, RuntimeException)}
     * メソッドのテストケースを管理するインナークラスです。
     *
     * @author Kato Shinya
     * @since 1.0
     * @version 1.0
     */
    @Nested
    class TestRequireEndWithStringWithException {

        @Test
        void testWhenExceptionIsNull() {
            RuntimeException emptyException = null;
            assertThrows(NullPointerException.class, () -> Preconditions.requireEndWith("", "", emptyException));
        }

        @ParameterizedTest
        @ValueSource(strings = { "test", "sequence", "c", "ce", "nce", "ence" })
        void testWhenStringDoesNotEndWith(String testParameter) {
            assertThrows(TestException.class,
                    () -> Preconditions.requireEndWith("test sequence ", testParameter, new TestException()));

        }

        @ParameterizedTest
        @ValueSource(strings = { " ", "test sequence ", "e ", "ce ", "nce ", "ence " })
        void testWhenStringEndsWith(String testParameter) {
            assertDoesNotThrow(
                    () -> Preconditions.requireEndWith("test sequence ", testParameter, new TestException()));
        }
    }

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
     * long数値が負数だった場合の例外メッセージ
     */
    private static final String EXCEPTION_MESSAGE_FOR_NEGATIVE_LONG_NUMBER = "Long number must be positive but %s was given";

    /**
     * long数値が正数だった場合の例外メッセージ
     */
    private static final String EXCEPTION_MESSAGE_FOR_POSITIVE_LONG_NUMBER = "Long number must be negative but %s was given";

    /**
     * short数値が負数だった場合の例外メッセージ
     */
    private static final String EXCEPTION_MESSAGE_FOR_NEGATIVE_SHORT_NUMBER = "Short number must be positive but %s was given";

    /**
     * short数値が正数だった場合の例外メッセージ
     */
    private static final String EXCEPTION_MESSAGE_FOR_POSITIVE_SHORT_NUMBER = "Short number must be negative but %s was given";

    /**
     * byte数値が負数だった場合の例外メッセージ
     */
    private static final String EXCEPTION_MESSAGE_FOR_NEGATIVE_BYTE_NUMBER = "Byte number must be positive but %s was given";

    /**
     * byte数値が正数だった場合の例外メッセージ
     */
    private static final String EXCEPTION_MESSAGE_FOR_POSITIVE_BYTE_NUMBER = "Byte number must be negative but %s was given";

    /**
     * byte数値が負数だった場合の例外メッセージ
     */
    private static final String EXCEPTION_MESSAGE_FOR_NEGATIVE_FLOAT_NUMBER = "Float number must be positive but %s was given";

    /**
     * byte数値が正数だった場合の例外メッセージ
     */
    private static final String EXCEPTION_MESSAGE_FOR_POSITIVE_FLOAT_NUMBER = "Float number must be negative but %s was given";

    /**
     * 範囲外だった場合の例外メッセージ
     */
    private static final String EXCEPTION_MESSAGE_FOR_OUT_OF_BOUNDS_FROM = "Index %s out-of-bounds for range from length %s";

    /**
     * 範囲外だった場合の例外メッセージ
     */
    private static final String EXCEPTION_MESSAGE_FOR_OUT_OF_BOUNDS_TO = "Index %s out-of-bounds for range from length 0 to length %s";

    /**
     * 範囲外だった場合の例外メッセージ
     */
    private static final String EXCEPTION_MESSAGE_FOR_OUT_OF_BOUNDS_FROM_TO = "Index %s out-of-bounds for range from length %s to length %s";

    /*
     * 空配列だった場合の例外メッセージ
     */
    private static final String EXCEPTION_MESSAGE_FOR_EMPTY_ARRAY = "Array must contain at least one or more elements";

    /*
     * 空リストだった場合の例外メッセージ
     */
    private static final String EXCEPTION_MESSAGE_FOR_EMPTY_LIST = "List must contain at least one or more elements";

    /**
     * 空マップだった場合の例外メッセージ
     */
    private static final String EXCEPTION_MESSAGE_FOR_EMPTY_MAP = "Map must contain at least one or more elements";

    /**
     * 空セットだった場合の例外メッセージ
     */
    private static final String EXCEPTION_MESSAGE_FOR_EMPTY_SET = "Set must contain at least one or more elements";
}
