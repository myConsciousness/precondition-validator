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

package org.thinkit.common.base.precondition;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import org.thinkit.common.base.precondition.exception.PreconditionFailedException;

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
            final PreconditionFailedException exception = assertThrows(PreconditionFailedException.class,
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
            final PreconditionFailedException exception = assertThrows(PreconditionFailedException.class,
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
            final PreconditionFailedException exception = assertThrows(PreconditionFailedException.class,
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
            final PreconditionFailedException exception = assertThrows(PreconditionFailedException.class,
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
            final PreconditionFailedException exception = assertThrows(PreconditionFailedException.class,
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
            final PreconditionFailedException exception = assertThrows(PreconditionFailedException.class,
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
        @ValueSource(floats = { -0.1f, -0.01f, -0.2f, -10.0f, -100.0f })
        void testWhenNumberIsNegative(float testParameter) {
            final PreconditionFailedException exception = assertThrows(PreconditionFailedException.class,
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
        @ValueSource(floats = { -0.1f, -0.01f, -0.2f, -10.0f, -100.0f })
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
     * {@link Preconditions#requirePositive(double)} メソッドのテストケースを管理するインナークラスです。
     *
     * @author Kato Shinya
     * @since 1.0
     * @version 1.0
     */
    @Nested
    class TestRequireDoublePositive {

        @ParameterizedTest
        @ValueSource(doubles = { -0.1d, -0.01d, -0.2d, -10.0d, -100.0d })
        void testWhenNumberIsNegative(double testParameter) {
            final PreconditionFailedException exception = assertThrows(PreconditionFailedException.class,
                    () -> Preconditions.requirePositive(testParameter));
            assertEquals(String.format(EXCEPTION_MESSAGE_FOR_NEGATIVE_DOUBLE_NUMBER, testParameter),
                    exception.getMessage());
        }

        @ParameterizedTest
        @ValueSource(doubles = { 0.0d, 0.01d, 0.1d, 0.2d, 10.0d, 100.0d })
        void testWhenNumberIsPositive(double testParameter) {
            assertDoesNotThrow(() -> Preconditions.requirePositive(testParameter));
        }
    }

    /**
     * {@link Preconditions#requirePositive(double, RuntimeException)}
     * メソッドのテストケースを管理するインナークラスです。
     *
     * @author Kato Shinya
     * @since 1.0
     * @version 1.0
     */
    @Nested
    class TestRequireDoublePositiveWithException {

        @Test
        void testWhenExceptionIsNull() {
            RuntimeException emptyException = null;
            double number = 0.0d;
            assertThrows(NullPointerException.class, () -> Preconditions.requirePositive(number, emptyException));
        }

        @Test
        void testWhenNumberIsNegativeAndExceptionIsNull() {
            RuntimeException emptyException = null;
            double number = -1.0d;
            assertThrows(NullPointerException.class, () -> Preconditions.requirePositive(number, emptyException));
        }

        @ParameterizedTest
        @ValueSource(doubles = { -0.1d, -0.01d, -0.2d, -10.0d, -100.0d })
        void testWhenNumberIsNegative(double testParameter) {
            assertThrows(TestException.class, () -> Preconditions.requirePositive(testParameter, new TestException()));
        }

        @ParameterizedTest
        @ValueSource(doubles = { 0.0d, 0.01d, 0.1d, 0.2d, 10.0d, 100.0d })
        void testWhenNumberIsPositive(double testParameter) {
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
            final PreconditionFailedException exception = assertThrows(PreconditionFailedException.class,
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
            final PreconditionFailedException exception = assertThrows(PreconditionFailedException.class,
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
            final PreconditionFailedException exception = assertThrows(PreconditionFailedException.class,
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
            final PreconditionFailedException exception = assertThrows(PreconditionFailedException.class,
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
            final PreconditionFailedException exception = assertThrows(PreconditionFailedException.class,
                    () -> Preconditions.requireNegative(testParameter));
            assertEquals(String.format(EXCEPTION_MESSAGE_FOR_POSITIVE_FLOAT_NUMBER, testParameter),
                    exception.getMessage());
        }

        @ParameterizedTest
        @ValueSource(floats = { -0.1f, -0.01f, -0.2f, -10.0f, -100.0f })
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
        @ValueSource(floats = { -0.1f, -0.01f, -0.2f, -10.0f, -100.0f })
        void testWhenNumberIsNegative(float testParameter) {
            assertDoesNotThrow(() -> Preconditions.requireNegative(testParameter, new TestException()));
        }
    }

    /**
     * {@link Preconditions#requireNegative(double)} メソッドのテストケースを管理するインナークラスです。
     *
     * @author Kato Shinya
     * @since 1.0
     * @version 1.0
     */
    @Nested
    class TestRequireDoubleNegative {

        @ParameterizedTest
        @ValueSource(doubles = { 0.0d, 0.01d, 0.1d, 0.2d, 10.0d, 100.0d })
        void testWhenNumberIsPositive(double testParameter) {
            final PreconditionFailedException exception = assertThrows(PreconditionFailedException.class,
                    () -> Preconditions.requireNegative(testParameter));
            assertEquals(String.format(EXCEPTION_MESSAGE_FOR_POSITIVE_DOUBLE_NUMBER, testParameter),
                    exception.getMessage());
        }

        @ParameterizedTest
        @ValueSource(doubles = { -0.1d, -0.01d, -0.2d, -10.0d, -100.0d })
        void testWhenNumberIsNegative(double testParameter) {
            assertDoesNotThrow(() -> Preconditions.requireNegative(testParameter));
        }
    }

    /**
     * {@link Preconditions#requireNegative(double, RuntimeException)}
     * メソッドのテストケースを管理するインナークラスです。
     *
     * @author Kato Shinya
     * @since 1.0
     * @version 1.0
     */
    @Nested
    class TestRequireDoubleNegativeWithException {

        @Test
        void testWhenExceptionIsNull() {
            RuntimeException emptyException = null;
            double number = -1.0d;
            assertThrows(NullPointerException.class, () -> Preconditions.requireNegative(number, emptyException));
        }

        @Test
        void testWhenNumberIsPositiveAndExceptionIsNull() {
            RuntimeException emptyException = null;
            double number = 0.0d;
            assertThrows(NullPointerException.class, () -> Preconditions.requireNegative(number, emptyException));
        }

        @ParameterizedTest
        @ValueSource(doubles = { 0.0d, 0.01d, 0.1d, 0.2d, 10.0d, 100.0d })
        void testWhenNumberIsPositive(double testParameter) {
            assertThrows(TestException.class, () -> Preconditions.requireNegative(testParameter, new TestException()));
        }

        @ParameterizedTest
        @ValueSource(doubles = { -0.1d, -0.01d, -0.2d, -10.0d, -100.0d })
        void testWhenNumberIsNegative(double testParameter) {
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
     * {@link Preconditions#requireRangeFrom(long, long)} メソッドのテストケースを管理するインナークラスです。
     *
     * @author Kato Shinya
     * @since 1.0
     * @version 1.0
     */
    @Nested
    class TestRequireLongRangeFrom {

        @ParameterizedTest
        @ValueSource(longs = { 1L, 10L, 100L, 150L, 1000L })
        void testWhenNumberIsOutOfRangeFromPositiveToZero(long testParameter) {
            final IndexOutOfBoundsException exception = assertThrows(IndexOutOfBoundsException.class,
                    () -> Preconditions.requireRangeFrom(testParameter, testParameter + 1));
            assertEquals(String.format(EXCEPTION_MESSAGE_LONG_FOR_OUT_OF_BOUNDS_FROM, testParameter, testParameter + 1),
                    exception.getMessage());
        }

        @ParameterizedTest
        @ValueSource(longs = { -100L, -50L, -10L, -1L })
        void testWhenNumberIsInRangeFromNegativeToZero(long testParameter) {
            assertDoesNotThrow(() -> Preconditions.requireRangeFrom(testParameter, testParameter - 1));
        }
    }

    /**
     * {@link Preconditions#requireRangeFrom(long, long, RuntimeException)}
     * メソッドのテストケースを管理するインナークラスです。
     *
     * @author Kato Shinya
     * @since 1.0
     * @version 1.0
     */
    @Nested
    class TestRequireLongRangeFromWithException {

        @Test
        void testWhenExceptionIsNull() {
            RuntimeException emptyException = null;
            assertThrows(NullPointerException.class, () -> Preconditions.requireRangeFrom(0L, 10L, emptyException));
        }

        @ParameterizedTest
        @ValueSource(longs = { 1L, 10L, 100L, 150L, 1000L })
        void testWhenNumberIsOutOfRangeFromPositiveToZero(long testParameter) {
            assertThrows(IndexOutOfBoundsException.class,
                    () -> Preconditions.requireRangeFrom(testParameter, testParameter + 1));
        }

        @ParameterizedTest
        @ValueSource(longs = { -100L, -50L, -10L, -1L })
        void testWhenNumberIsInRangeFromNegativeToZero(long testParameter) {
            assertDoesNotThrow(() -> Preconditions.requireRangeFrom(testParameter, testParameter - 1));
        }
    }

    /**
     * {@link Preconditions#requireRangeFrom(short, short)}
     * メソッドのテストケースを管理するインナークラスです。
     *
     * @author Kato Shinya
     * @since 1.0
     * @version 1.0
     */
    @Nested
    class TestRequireShortRangeFrom {

        @ParameterizedTest
        @ValueSource(shorts = { 1, 10, 100, 150, 1000 })
        void testWhenNumberIsOutOfRangeFromPositiveToZero(short testParameter) {
            short offset = 1;
            short from = (short) (testParameter + offset);

            final IndexOutOfBoundsException exception = assertThrows(IndexOutOfBoundsException.class,
                    () -> Preconditions.requireRangeFrom(testParameter, from));
            assertEquals(
                    String.format(EXCEPTION_MESSAGE_SHORT_FOR_OUT_OF_BOUNDS_FROM, testParameter, testParameter + 1),
                    exception.getMessage());
        }

        @ParameterizedTest
        @ValueSource(shorts = { -100, -50, -10, -1 })
        void testWhenNumberIsInRangeFromNegativeToZero(short testParameter) {
            short offset = 1;
            short from = (short) (testParameter - offset);
            assertDoesNotThrow(() -> Preconditions.requireRangeFrom(testParameter, from));
        }
    }

    /**
     * {@link Preconditions#requireRangeFrom(short, short, RuntimeException)}
     * メソッドのテストケースを管理するインナークラスです。
     *
     * @author Kato Shinya
     * @since 1.0
     * @version 1.0
     */
    @Nested
    class TestRequireShortRangeFromWithException {

        @Test
        void testWhenExceptionIsNull() {
            RuntimeException emptyException = null;
            short index = 0;
            short from = 10;
            assertThrows(NullPointerException.class, () -> Preconditions.requireRangeFrom(index, from, emptyException));
        }

        @ParameterizedTest
        @ValueSource(shorts = { 1, 10, 100, 150, 1000 })
        void testWhenNumberIsOutOfRangeFromPositiveToZero(short testParameter) {
            short offset = 1;
            short from = (short) (testParameter + offset);
            assertThrows(IndexOutOfBoundsException.class, () -> Preconditions.requireRangeFrom(testParameter, from));
        }

        @ParameterizedTest
        @ValueSource(shorts = { -100, -50, -10, -1 })
        void testWhenNumberIsInRangeFromNegativeToZero(short testParameter) {
            short offset = 1;
            short from = (short) (testParameter - offset);
            assertDoesNotThrow(() -> Preconditions.requireRangeFrom(testParameter, from));
        }
    }

    /**
     * {@link Preconditions#requireRangeFrom(byte, byte)} メソッドのテストケースを管理するインナークラスです。
     *
     * @author Kato Shinya
     * @since 1.0
     * @version 1.0
     */
    @Nested
    class TestRequireByteRangeFrom {

        @ParameterizedTest
        @ValueSource(bytes = { 1, 2, 10, 99 })
        void testWhenNumberIsOutOfRangeFromPositiveToZero(byte testParameter) {
            byte offset = 1;
            byte from = (byte) (testParameter + offset);

            final IndexOutOfBoundsException exception = assertThrows(IndexOutOfBoundsException.class,
                    () -> Preconditions.requireRangeFrom(testParameter, from));
            assertEquals(String.format(EXCEPTION_MESSAGE_BYTE_FOR_OUT_OF_BOUNDS_FROM, testParameter, testParameter + 1),
                    exception.getMessage());
        }

        @ParameterizedTest
        @ValueSource(bytes = { -99, -50, -10, -1 })
        void testWhenNumberIsInRangeFromNegativeToZero(byte testParameter) {
            byte offset = 1;
            byte from = (byte) (testParameter - offset);
            assertDoesNotThrow(() -> Preconditions.requireRangeFrom(testParameter, from));
        }
    }

    /**
     * {@link Preconditions#requireRangeFrom(byte, byte, RuntimeException)}
     * メソッドのテストケースを管理するインナークラスです。
     *
     * @author Kato Shinya
     * @since 1.0
     * @version 1.0
     */
    @Nested
    class TestRequireByteRangeFromWithException {

        @Test
        void testWhenExceptionIsNull() {
            RuntimeException emptyException = null;
            byte index = 0;
            byte from = 10;
            assertThrows(NullPointerException.class, () -> Preconditions.requireRangeFrom(index, from, emptyException));
        }

        @ParameterizedTest
        @ValueSource(bytes = { 1, 2, 10, 99 })
        void testWhenNumberIsOutOfRangeFromPositiveToZero(byte testParameter) {
            byte offset = 1;
            byte from = (byte) (testParameter + offset);
            assertThrows(IndexOutOfBoundsException.class, () -> Preconditions.requireRangeFrom(testParameter, from));
        }

        @ParameterizedTest
        @ValueSource(bytes = { -99, -50, -10, -2, -1 })
        void testWhenNumberIsInRangeFromNegativeToZero(byte testParameter) {
            byte offset = 1;
            byte from = (byte) (testParameter - offset);
            assertDoesNotThrow(() -> Preconditions.requireRangeFrom(testParameter, from));
        }
    }

    /**
     * {@link Preconditions#requireRangeFrom(float, float)}
     * メソッドのテストケースを管理するインナークラスです。
     *
     * @author Kato Shinya
     * @since 1.0
     * @version 1.0
     */
    @Nested
    class TestRequireFloatRangeFrom {

        @ParameterizedTest
        @ValueSource(floats = { 0.1f, 0.01f, 1.0f, 10.0f, 100.0f, 150.0f, 1000.0f })
        void testWhenNumberIsOutOfRangeFromPositiveToZero(float testParameter) {
            final IndexOutOfBoundsException exception = assertThrows(IndexOutOfBoundsException.class,
                    () -> Preconditions.requireRangeFrom(testParameter, testParameter + 0.01f));
            assertEquals(
                    String.format(EXCEPTION_MESSAGE_FLOAT_FOR_OUT_OF_BOUNDS_FROM, testParameter, testParameter + 0.01f),
                    exception.getMessage());
        }

        @ParameterizedTest
        @ValueSource(floats = { -100.0f, -50.0f, -10.0f, -1.0f, -0.1f, -0.01f })
        void testWhenNumberIsInRangeFromNegativeToZero(float testParameter) {
            assertDoesNotThrow(() -> Preconditions.requireRangeFrom(testParameter, testParameter - 0.01f));
        }
    }

    /**
     * {@link Preconditions#requireRangeFrom(float, float, RuntimeException)}
     * メソッドのテストケースを管理するインナークラスです。
     *
     * @author Kato Shinya
     * @since 1.0
     * @version 1.0
     */
    @Nested
    class TestRequireFloatRangeFromWithException {

        @Test
        void testWhenExceptionIsNull() {
            RuntimeException emptyException = null;
            assertThrows(NullPointerException.class, () -> Preconditions.requireRangeFrom(0.0f, 10.0f, emptyException));
        }

        @ParameterizedTest
        @ValueSource(floats = { 1.0f, 0.1f, 0.01f, 10.0f, 100.0f, 150.0f, 1000.0f })
        void testWhenNumberIsOutOfRangeFromPositiveToZero(float testParameter) {
            assertThrows(IndexOutOfBoundsException.class,
                    () -> Preconditions.requireRangeFrom(testParameter, testParameter + 0.01f));
        }

        @ParameterizedTest
        @ValueSource(floats = { -100.0f, -50.0f, -10.0f, -1.0f, -0.1f, -0.01f })
        void testWhenNumberIsInRangeFromNegativeToZero(float testParameter) {
            assertDoesNotThrow(() -> Preconditions.requireRangeFrom(testParameter, testParameter - 0.01f));
        }
    }

    /**
     * {@link Preconditions#requireRangeFrom(double, double)}
     * メソッドのテストケースを管理するインナークラスです。
     *
     * @author Kato Shinya
     * @since 1.0
     * @version 1.0
     */
    @Nested
    class TestRequireDoubleRangeFrom {

        @ParameterizedTest
        @ValueSource(doubles = { 0.1d, 0.01d, 1.0d, 10.0d, 100.0d, 150.0d, 1000.0d })
        void testWhenNumberIsOutOfRangeFromPositiveToZero(double testParameter) {
            final IndexOutOfBoundsException exception = assertThrows(IndexOutOfBoundsException.class,
                    () -> Preconditions.requireRangeFrom(testParameter, testParameter + 0.01d));
            assertEquals(String.format(EXCEPTION_MESSAGE_DOUBLE_FOR_OUT_OF_BOUNDS_FROM, testParameter,
                    testParameter + 0.01d), exception.getMessage());
        }

        @ParameterizedTest
        @ValueSource(doubles = { -100.0d, -50.0d, -10.0d, -1.0d, -0.1d, -0.01d })
        void testWhenNumberIsInRangeFromNegativeToZero(double testParameter) {
            assertDoesNotThrow(() -> Preconditions.requireRangeFrom(testParameter, testParameter - 0.01d));
        }
    }

    /**
     * {@link Preconditions#requireRangeFrom(double, double, RuntimeException)}
     * メソッドのテストケースを管理するインナークラスです。
     *
     * @author Kato Shinya
     * @since 1.0
     * @version 1.0
     */
    @Nested
    class TestRequireDoubleRangeFromWithException {

        @Test
        void testWhenExceptionIsNull() {
            RuntimeException emptyException = null;
            assertThrows(NullPointerException.class, () -> Preconditions.requireRangeFrom(0.0d, 10.0d, emptyException));
        }

        @ParameterizedTest
        @ValueSource(doubles = { 1.0d, 0.1d, 0.01d, 10.0d, 100.0d, 150.0d, 1000.0d })
        void testWhenNumberIsOutOfRangeFromPositiveToZero(double testParameter) {
            assertThrows(IndexOutOfBoundsException.class,
                    () -> Preconditions.requireRangeFrom(testParameter, testParameter + 0.01d));
        }

        @ParameterizedTest
        @ValueSource(doubles = { -100.0d, -50.0d, -10.0d, -1.0d, -0.1d, -0.01d })
        void testWhenNumberIsInRangeFromNegativeToZero(double testParameter) {
            assertDoesNotThrow(() -> Preconditions.requireRangeFrom(testParameter, testParameter - 0.01d));
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
     * {@link Preconditions#requireRangeTo(long, long)} メソッドのテストケースを管理するインナークラスです。
     *
     * @author Kato Shinya
     * @since 1.0
     * @version 1.0
     */
    @Nested
    class TestRequireLongRangeTo {

        @ParameterizedTest
        @ValueSource(longs = { 1L, 10L, 100L, 150L, 1000L })
        void testWhenNumberIsOutOfRangeFromPositiveToZero(long testParameter) {
            final IndexOutOfBoundsException exception = assertThrows(IndexOutOfBoundsException.class,
                    () -> Preconditions.requireRangeTo(testParameter, testParameter - 1L));
            assertEquals(String.format(EXCEPTION_MESSAGE_LONG_FOR_OUT_OF_BOUNDS_TO, testParameter, testParameter - 1L),
                    exception.getMessage());
        }

        @ParameterizedTest
        @ValueSource(longs = { -100L, -50L, -10L, -2L, -1L })
        void testWhenNumberIsInRangeFromNegativeToZero(long testParameter) {
            assertDoesNotThrow(() -> Preconditions.requireRangeTo(testParameter, testParameter + 1L));
        }
    }

    /**
     * {@link Preconditions#requireRangeTo(long, long, RuntimeException)}
     * メソッドのテストケースを管理するインナークラスです。
     *
     * @author Kato Shinya
     * @since 1.0
     * @version 1.0
     */
    @Nested
    class TestRequireLongRangeToWithException {

        @Test
        void testWhenExceptionIsNull() {
            RuntimeException emptyException = null;
            assertThrows(NullPointerException.class, () -> Preconditions.requireRangeTo(0L, 10L, emptyException));
        }

        @ParameterizedTest
        @ValueSource(longs = { 1L, 10L, 100L, 150L, 1000L })
        void testWhenNumberIsOutOfRangeFromPositiveToZero(long testParameter) {
            assertThrows(IndexOutOfBoundsException.class,
                    () -> Preconditions.requireRangeTo(testParameter, testParameter - 1L));
        }

        @ParameterizedTest
        @ValueSource(longs = { -100L, -50L, -10L, -1L })
        void testWhenNumberIsInRangeFromNegativeToZero(long testParameter) {
            assertDoesNotThrow(() -> Preconditions.requireRangeTo(testParameter, testParameter + 1L));
        }
    }

    /**
     * {@link Preconditions#requireRangeTo(short, short)} メソッドのテストケースを管理するインナークラスです。
     *
     * @author Kato Shinya
     * @since 1.0
     * @version 1.0
     */
    @Nested
    class TestRequireShortRangeTo {

        @ParameterizedTest
        @ValueSource(shorts = { 1, 10, 100, 150, 1000 })
        void testWhenNumberIsOutOfRangeFromPositiveToZero(short testParameter) {
            short offset = 1;
            short to = (short) (testParameter - offset);

            final IndexOutOfBoundsException exception = assertThrows(IndexOutOfBoundsException.class,
                    () -> Preconditions.requireRangeTo(testParameter, to));
            assertEquals(String.format(EXCEPTION_MESSAGE_SHORT_FOR_OUT_OF_BOUNDS_TO, testParameter, testParameter - 1),
                    exception.getMessage());
        }

        @ParameterizedTest
        @ValueSource(shorts = { -100, -50, -10, -2, -1 })
        void testWhenNumberIsInRangeFromNegativeToZero(short testParameter) {
            short offset = 1;
            short to = (short) (testParameter + offset);
            assertDoesNotThrow(() -> Preconditions.requireRangeTo(testParameter, to));
        }
    }

    /**
     * {@link Preconditions#requireRangeTo(short, short, RuntimeException)}
     * メソッドのテストケースを管理するインナークラスです。
     *
     * @author Kato Shinya
     * @since 1.0
     * @version 1.0
     */
    @Nested
    class TestRequireShortRangeToWithException {

        @Test
        void testWhenExceptionIsNull() {
            RuntimeException emptyException = null;
            short index = 0;
            short to = 10;
            assertThrows(NullPointerException.class, () -> Preconditions.requireRangeTo(index, to, emptyException));
        }

        @ParameterizedTest
        @ValueSource(shorts = { 1, 10, 100, 150, 1000 })
        void testWhenNumberIsOutOfRangeFromPositiveToZero(short testParameter) {
            short offset = 1;
            short to = (short) (testParameter - offset);
            assertThrows(IndexOutOfBoundsException.class, () -> Preconditions.requireRangeTo(testParameter, to));
        }

        @ParameterizedTest
        @ValueSource(shorts = { -100, -50, -10, -2, -1 })
        void testWhenNumberIsInRangeFromNegativeToZero(short testParameter) {
            short offset = 1;
            short to = (short) (testParameter + offset);
            assertDoesNotThrow(() -> Preconditions.requireRangeTo(testParameter, to));
        }
    }

    /**
     * {@link Preconditions#requireRangeTo(byte, byte)} メソッドのテストケースを管理するインナークラスです。
     *
     * @author Kato Shinya
     * @since 1.0
     * @version 1.0
     */
    @Nested
    class TestRequireByteRangeTo {

        @ParameterizedTest
        @ValueSource(bytes = { 1, 2, 10, 99 })
        void testWhenNumberIsOutOfRangeFromPositiveToZero(byte testParameter) {
            byte offset = 1;
            byte to = (byte) (testParameter - offset);

            final IndexOutOfBoundsException exception = assertThrows(IndexOutOfBoundsException.class,
                    () -> Preconditions.requireRangeTo(testParameter, to));
            assertEquals(String.format(EXCEPTION_MESSAGE_BYTE_FOR_OUT_OF_BOUNDS_TO, testParameter, testParameter - 1),
                    exception.getMessage());
        }

        @ParameterizedTest
        @ValueSource(bytes = { -99, -50, -10, -2, -1 })
        void testWhenNumberIsInRangeFromNegativeToZero(byte testParameter) {
            byte offset = 1;
            byte to = (byte) (testParameter + offset);
            assertDoesNotThrow(() -> Preconditions.requireRangeTo(testParameter, to));
        }
    }

    /**
     * {@link Preconditions#requireRangeTo(byte, byte, RuntimeException)}
     * メソッドのテストケースを管理するインナークラスです。
     *
     * @author Kato Shinya
     * @since 1.0
     * @version 1.0
     */
    @Nested
    class TestRequireByteRangeToWithException {

        @Test
        void testWhenExceptionIsNull() {
            RuntimeException emptyException = null;
            byte index = 0;
            byte to = 10;
            assertThrows(NullPointerException.class, () -> Preconditions.requireRangeTo(index, to, emptyException));
        }

        @ParameterizedTest
        @ValueSource(bytes = { 1, 10, 99 })
        void testWhenNumberIsOutOfRangeFromPositiveToZero(byte testParameter) {
            byte offset = 1;
            byte to = (byte) (testParameter - offset);
            assertThrows(IndexOutOfBoundsException.class, () -> Preconditions.requireRangeTo(testParameter, to));
        }

        @ParameterizedTest
        @ValueSource(bytes = { -99, -50, -10, -2, -1 })
        void testWhenNumberIsInRangeFromNegativeToZero(byte testParameter) {
            byte offset = 1;
            byte to = (byte) (testParameter + offset);
            assertDoesNotThrow(() -> Preconditions.requireRangeTo(testParameter, to));
        }
    }

    /**
     * {@link Preconditions#requireRangeTo(float, float)} メソッドのテストケースを管理するインナークラスです。
     *
     * @author Kato Shinya
     * @since 1.0
     * @version 1.0
     */
    @Nested
    class TestRequireFloatRangeTo {

        @ParameterizedTest
        @ValueSource(floats = { 1.0f, 0.1f, 0.01f, 2.0f, 10.0f, 100.0f, 1000.0f })
        void testWhenNumberIsOutOfRangeFromPositiveToZero(float testParameter) {
            final IndexOutOfBoundsException exception = assertThrows(IndexOutOfBoundsException.class,
                    () -> Preconditions.requireRangeTo(testParameter, testParameter - 0.01f));
            assertEquals(
                    String.format(EXCEPTION_MESSAGE_FLOAT_FOR_OUT_OF_BOUNDS_TO, testParameter, testParameter - 0.01f),
                    exception.getMessage());
        }

        @ParameterizedTest
        @ValueSource(floats = { -1000.0f, -100.0f, -50.0f, -10.0f, -2.0f, -1.0f, -0.1f, -0.01f })
        void testWhenNumberIsInRangeFromNegativeToZero(float testParameter) {
            assertDoesNotThrow(() -> Preconditions.requireRangeTo(testParameter, testParameter + 0.01f));
        }
    }

    /**
     * {@link Preconditions#requireRangeTo(float, float, RuntimeException)}
     * メソッドのテストケースを管理するインナークラスです。
     *
     * @author Kato Shinya
     * @since 1.0
     * @version 1.0
     */
    @Nested
    class TestRequireFloatRangeToWithException {

        @Test
        void testWhenExceptionIsNull() {
            RuntimeException emptyException = null;
            assertThrows(NullPointerException.class, () -> Preconditions.requireRangeTo(0.0f, 1.0f, emptyException));
        }

        @ParameterizedTest
        @ValueSource(floats = { -1000.0f, -100.0f, -50.0f, -10.0f, -2.0f, -1.0f, -0.1f, -0.01f })
        void testWhenNumberIsOutOfRangeFromPositiveToZero(float testParameter) {
            assertThrows(IndexOutOfBoundsException.class,
                    () -> Preconditions.requireRangeTo(testParameter, testParameter - 0.01f));
        }

        @ParameterizedTest
        @ValueSource(floats = { 1.0f, 0.1f, 0.01f, 2.0f, 10.0f, 100.0f, 1000.0f })
        void testWhenNumberIsInRangeFromNegativeToZero(float testParameter) {
            assertDoesNotThrow(() -> Preconditions.requireRangeTo(testParameter, testParameter + 0.01f));
        }
    }

    /**
     * {@link Preconditions#requireRangeTo(double, double)}
     * メソッドのテストケースを管理するインナークラスです。
     *
     * @author Kato Shinya
     * @since 1.0
     * @version 1.0
     */
    @Nested
    class TestRequireDoubleRangeTo {

        @ParameterizedTest
        @ValueSource(doubles = { 1.0d, 0.1d, 0.01d, 2.0d, 10.0d, 100.0d, 1000.0d })
        void testWhenNumberIsOutOfRangeFromPositiveToZero(double testParameter) {
            final IndexOutOfBoundsException exception = assertThrows(IndexOutOfBoundsException.class,
                    () -> Preconditions.requireRangeTo(testParameter, testParameter - 0.01d));
            assertEquals(
                    String.format(EXCEPTION_MESSAGE_DOUBLE_FOR_OUT_OF_BOUNDS_TO, testParameter, testParameter - 0.01d),
                    exception.getMessage());
        }

        @ParameterizedTest
        @ValueSource(doubles = { -1000.0d, -100.0d, -50.0d, -10.0d, -2.0d, -1.0d, -0.1d, -0.01d })
        void testWhenNumberIsInRangeFromNegativeToZero(double testParameter) {
            assertDoesNotThrow(() -> Preconditions.requireRangeTo(testParameter, testParameter + 0.01d));
        }
    }

    /**
     * {@link Preconditions#requireRangeTo(double, double, RuntimeException)}
     * メソッドのテストケースを管理するインナークラスです。
     *
     * @author Kato Shinya
     * @since 1.0
     * @version 1.0
     */
    @Nested
    class TestRequireDoubleRangeToWithException {

        @Test
        void testWhenExceptionIsNull() {
            RuntimeException emptyException = null;
            assertThrows(NullPointerException.class, () -> Preconditions.requireRangeTo(0.0d, 1.0d, emptyException));
        }

        @ParameterizedTest
        @ValueSource(doubles = { -1000.0d, -100.0d, -50.0d, -10.0d, -2.0d, -1.0d, -0.1d, -0.01d })
        void testWhenNumberIsOutOfRangeFromPositiveToZero(double testParameter) {
            assertThrows(IndexOutOfBoundsException.class,
                    () -> Preconditions.requireRangeTo(testParameter, testParameter - 0.01d));
        }

        @ParameterizedTest
        @ValueSource(doubles = { 1.0d, 0.1d, 0.01d, 2.0d, 10.0d, 100.0d, 1000.0d })
        void testWhenNumberIsInRangeFromNegativeToZero(double testParameter) {
            assertDoesNotThrow(() -> Preconditions.requireRangeTo(testParameter, testParameter + 0.01d));
        }
    }

    /**
     * {@link Preconditions#requireRange(int, int, int)} メソッドのテストケースを管理するインナークラスです。
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
     * {@link Preconditions#requireRange(long, long, long)}
     * メソッドのテストケースを管理するインナークラスです。
     *
     * @author Kato Shinya
     * @since 1.0
     * @version 1.0
     */
    @Nested
    @TestInstance(Lifecycle.PER_CLASS)
    class TestRequireLongRangeFromTo {

        Stream<Arguments> negativeNumbersProvider() {
            return Stream.of(Arguments.of(-5L, -10L, -1L), Arguments.of(-2L, -10L, -1L), Arguments.of(-9L, -10L, -1L),
                    Arguments.of(-1L, -10L, -1L), Arguments.of(-10L, -10L, -1L));
        }

        Stream<Arguments> badNegativeNumbersProvider() {
            return Stream.of(Arguments.of(0L, -10L, -1L), Arguments.of(-11L, -10L, -1L));
        }

        Stream<Arguments> negativeAndPositiveNumbersProvider() {
            return Stream.of(Arguments.of(0L, -5L, 5L), Arguments.of(-4L, -5L, 5L), Arguments.of(4L, -5L, 5L),
                    Arguments.of(-5L, -5L, 5L), Arguments.of(5L, -5L, 5L));
        }

        Stream<Arguments> badNegativeAndPositiveNumbersProvider() {
            return Stream.of(Arguments.of(6L, -5L, 5L), Arguments.of(-6L, -5L, 5L));
        }

        Stream<Arguments> positiveNumbersProvider() {
            return Stream.of(Arguments.of(5L, 0L, 10L), Arguments.of(1L, 0L, 10L), Arguments.of(9L, 0L, 10L),
                    Arguments.of(0L, 0L, 10L), Arguments.of(10L, 0L, 10L));
        }

        Stream<Arguments> badPositiveNumbersProvider() {
            return Stream.of(Arguments.of(11L, 0L, 10L), Arguments.of(-1L, 0L, 10L));
        }

        @ParameterizedTest
        @MethodSource("negativeNumbersProvider")
        void testWhenInRangeFromNegativeToNegative(long index, long from, long to) {
            assertDoesNotThrow(() -> Preconditions.requireRange(index, from, to));
        }

        @ParameterizedTest
        @MethodSource("negativeAndPositiveNumbersProvider")
        void testWhenInRangeFromNegativeToPositive(long index, long from, long to) {
            assertDoesNotThrow(() -> Preconditions.requireRange(index, from, to));
        }

        @ParameterizedTest
        @MethodSource("positiveNumbersProvider")
        void testWhenInRangeFromPositiveToPositive(long index, long from, long to) {
            assertDoesNotThrow(() -> Preconditions.requireRange(index, from, to));
        }

        @ParameterizedTest
        @MethodSource("badNegativeNumbersProvider")
        void testWhenOutOfRangeFromNegativeToNegative(long index, long from, long to) {
            final IndexOutOfBoundsException exception = assertThrows(IndexOutOfBoundsException.class,
                    () -> Preconditions.requireRange(index, from, to));
            assertEquals(String.format(EXCEPTION_MESSAGE_LONG_FOR_OUT_OF_BOUNDS_FROM_TO, index, from, to),
                    exception.getMessage());
        }

        @ParameterizedTest
        @MethodSource("badNegativeAndPositiveNumbersProvider")
        void testWhenOutOfRangeFromNegativeToPositive(long index, long from, long to) {
            final IndexOutOfBoundsException exception = assertThrows(IndexOutOfBoundsException.class,
                    () -> Preconditions.requireRange(index, from, to));
            assertEquals(String.format(EXCEPTION_MESSAGE_LONG_FOR_OUT_OF_BOUNDS_FROM_TO, index, from, to),
                    exception.getMessage());
        }

        @ParameterizedTest
        @MethodSource("badPositiveNumbersProvider")
        void testWhenOutOfRangeFromPositiveToPositive(long index, long from, long to) {
            final IndexOutOfBoundsException exception = assertThrows(IndexOutOfBoundsException.class,
                    () -> Preconditions.requireRange(index, from, to));
            assertEquals(String.format(EXCEPTION_MESSAGE_LONG_FOR_OUT_OF_BOUNDS_FROM_TO, index, from, to),
                    exception.getMessage());
        }
    }

    /**
     * {@link Preconditions#requireRange(long, long, long, RuntimeException)}
     * メソッドのテストケースを管理するインナークラスです。
     *
     * @author Kato Shinya
     * @since 1.0
     * @version 1.0
     */
    @Nested
    @TestInstance(Lifecycle.PER_CLASS)
    class TestRequireLongRangeFromToWithException {

        Stream<Arguments> negativeNumbersProvider() {
            return Stream.of(Arguments.of(-5L, -10L, -1L), Arguments.of(-2L, -10L, -1L), Arguments.of(-9L, -10L, -1L),
                    Arguments.of(-1L, -10L, -1L), Arguments.of(-10L, -10L, -1L));
        }

        Stream<Arguments> badNegativeNumbersProvider() {
            return Stream.of(Arguments.of(0L, -10L, -1L), Arguments.of(-11L, -10L, -1L));
        }

        Stream<Arguments> negativeAndPositiveNumbersProvider() {
            return Stream.of(Arguments.of(0L, -5L, 5L), Arguments.of(-4L, -5L, 5L), Arguments.of(4L, -5L, 5L),
                    Arguments.of(-5L, -5L, 5L), Arguments.of(5L, -5L, 5L));
        }

        Stream<Arguments> badNegativeAndPositiveNumbersProvider() {
            return Stream.of(Arguments.of(6L, -5L, 5L), Arguments.of(-6L, -5L, 5L));
        }

        Stream<Arguments> positiveNumbersProvider() {
            return Stream.of(Arguments.of(5L, 0L, 10L), Arguments.of(1L, 0L, 10L), Arguments.of(9L, 0L, 10L),
                    Arguments.of(0L, 0L, 10L), Arguments.of(10L, 0L, 10L));
        }

        Stream<Arguments> badPositiveNumbersProvider() {
            return Stream.of(Arguments.of(11L, 0L, 10L), Arguments.of(-1L, 0L, 10L));
        }

        @ParameterizedTest
        @MethodSource("negativeNumbersProvider")
        void testWhenInRangeFromNegativeToNegative(long index, long from, long to) {
            assertDoesNotThrow(() -> Preconditions.requireRange(index, from, to, new TestException()));
        }

        @Test
        void testWhenExceptionIsNull() {
            RuntimeException emptyException = null;
            assertThrows(NullPointerException.class, () -> Preconditions.requireRange(1L, 0L, 2L, emptyException));
        }

        @ParameterizedTest
        @MethodSource("negativeAndPositiveNumbersProvider")
        void testWhenInRangeFromNegativeToPositive(long index, long from, long to) {
            assertDoesNotThrow(() -> Preconditions.requireRange(index, from, to, new TestException()));
        }

        @ParameterizedTest
        @MethodSource("positiveNumbersProvider")
        void testWhenInRangeFromPositiveToPositive(long index, long from, long to) {
            assertDoesNotThrow(() -> Preconditions.requireRange(index, from, to, new TestException()));
        }

        @ParameterizedTest
        @MethodSource("badNegativeNumbersProvider")
        void testWhenOutOfRangeFromNegativeToNegative(long index, long from, long to) {
            assertThrows(TestException.class, () -> Preconditions.requireRange(index, from, to, new TestException()));
        }

        @ParameterizedTest
        @MethodSource("badNegativeAndPositiveNumbersProvider")
        void testWhenOutOfRangeFromNegativeToPositive(long index, long from, long to) {
            assertThrows(TestException.class, () -> Preconditions.requireRange(index, from, to, new TestException()));
        }

        @ParameterizedTest
        @MethodSource("badPositiveNumbersProvider")
        void testWhenOutOfRangeFromPositiveToPositive(long index, long from, long to) {
            assertThrows(TestException.class, () -> Preconditions.requireRange(index, from, to, new TestException()));
        }
    }

    /**
     * {@link Preconditions#requireRange(short, short, short)}
     * メソッドのテストケースを管理するインナークラスです。
     *
     * @author Kato Shinya
     * @since 1.0
     * @version 1.0
     */
    @Nested
    @TestInstance(Lifecycle.PER_CLASS)
    class TestRequireShortRangeFromTo {

        Stream<Arguments> negativeNumbersProvider() {
            return Stream.of(Arguments.of((short) -5, (short) -10, (short) -1),
                    Arguments.of((short) -2, (short) -10, (short) -1),
                    Arguments.of((short) -9, (short) -10, (short) -1),
                    Arguments.of((short) -1, (short) -10, (short) -1),
                    Arguments.of((short) -10, (short) -10, (short) -1));
        }

        Stream<Arguments> badNegativeNumbersProvider() {
            return Stream.of(Arguments.of((short) 0, (short) -10, (short) -1),
                    Arguments.of((short) -11, (short) -10, (short) -1));
        }

        Stream<Arguments> negativeAndPositiveNumbersProvider() {
            return Stream.of(Arguments.of((short) 0, (short) -5, (short) 5),
                    Arguments.of((short) -4, (short) -5, (short) 5), Arguments.of((short) 4, (short) -5, (short) 5),
                    Arguments.of((short) -5, (short) -5, (short) 5), Arguments.of((short) 5, (short) -5, (short) 5));
        }

        Stream<Arguments> badNegativeAndPositiveNumbersProvider() {
            return Stream.of(Arguments.of((short) 6, (short) -5, (short) 5),
                    Arguments.of((short) -6, (short) -5, (short) 5));
        }

        Stream<Arguments> positiveNumbersProvider() {
            return Stream.of(Arguments.of((short) 5, (short) 0, (short) 10),
                    Arguments.of((short) 1, (short) 0, (short) 10), Arguments.of((short) 9, (short) 0, (short) 10),
                    Arguments.of((short) 0, (short) 0, (short) 10), Arguments.of((short) 10, (short) 0, (short) 10));
        }

        Stream<Arguments> badPositiveNumbersProvider() {
            return Stream.of(Arguments.of((short) 11, (short) 0, (short) 10),
                    Arguments.of((short) -1, (short) 0, (short) 10));
        }

        @ParameterizedTest
        @MethodSource("negativeNumbersProvider")
        void testWhenInRangeFromNegativeToNegative(short index, short from, short to) {
            assertDoesNotThrow(() -> Preconditions.requireRange(index, from, to));
        }

        @ParameterizedTest
        @MethodSource("negativeAndPositiveNumbersProvider")
        void testWhenInRangeFromNegativeToPositive(short index, short from, short to) {
            assertDoesNotThrow(() -> Preconditions.requireRange(index, from, to));
        }

        @ParameterizedTest
        @MethodSource("positiveNumbersProvider")
        void testWhenInRangeFromPositiveToPositive(short index, short from, short to) {
            assertDoesNotThrow(() -> Preconditions.requireRange(index, from, to));
        }

        @ParameterizedTest
        @MethodSource("badNegativeNumbersProvider")
        void testWhenOutOfRangeFromNegativeToNegative(short index, short from, short to) {
            final IndexOutOfBoundsException exception = assertThrows(IndexOutOfBoundsException.class,
                    () -> Preconditions.requireRange(index, from, to));
            assertEquals(String.format(EXCEPTION_MESSAGE_SHORT_FOR_OUT_OF_BOUNDS_FROM_TO, index, from, to),
                    exception.getMessage());
        }

        @ParameterizedTest
        @MethodSource("badNegativeAndPositiveNumbersProvider")
        void testWhenOutOfRangeFromNegativeToPositive(short index, short from, short to) {
            final IndexOutOfBoundsException exception = assertThrows(IndexOutOfBoundsException.class,
                    () -> Preconditions.requireRange(index, from, to));
            assertEquals(String.format(EXCEPTION_MESSAGE_SHORT_FOR_OUT_OF_BOUNDS_FROM_TO, index, from, to),
                    exception.getMessage());
        }

        @ParameterizedTest
        @MethodSource("badPositiveNumbersProvider")
        void testWhenOutOfRangeFromPositiveToPositive(short index, short from, short to) {
            final IndexOutOfBoundsException exception = assertThrows(IndexOutOfBoundsException.class,
                    () -> Preconditions.requireRange(index, from, to));
            assertEquals(String.format(EXCEPTION_MESSAGE_SHORT_FOR_OUT_OF_BOUNDS_FROM_TO, index, from, to),
                    exception.getMessage());
        }
    }

    /**
     * {@link Preconditions#requireRange(short, short, short, RuntimeException)}
     * メソッドのテストケースを管理するインナークラスです。
     *
     * @author Kato Shinya
     * @since 1.0
     * @version 1.0
     */
    @Nested
    @TestInstance(Lifecycle.PER_CLASS)
    class TestRequireShortRangeFromToWithException {

        Stream<Arguments> negativeNumbersProvider() {
            return Stream.of(Arguments.of((short) -5, (short) -10, (short) -1),
                    Arguments.of((short) -2, (short) -10, (short) -1),
                    Arguments.of((short) -9, (short) -10, (short) -1),
                    Arguments.of((short) -1, (short) -10, (short) -1),
                    Arguments.of((short) -10, (short) -10, (short) -1));
        }

        Stream<Arguments> badNegativeNumbersProvider() {
            return Stream.of(Arguments.of((short) 0, (short) -10, (short) -1),
                    Arguments.of((short) -11, (short) -10, (short) -1));
        }

        Stream<Arguments> negativeAndPositiveNumbersProvider() {
            return Stream.of(Arguments.of((short) 0, (short) -5, (short) 5),
                    Arguments.of((short) -4, (short) -5, (short) 5), Arguments.of((short) 4, (short) -5, (short) 5),
                    Arguments.of((short) -5, (short) -5, (short) 5), Arguments.of((short) 5, (short) -5, (short) 5));
        }

        Stream<Arguments> badNegativeAndPositiveNumbersProvider() {
            return Stream.of(Arguments.of((short) 6, (short) -5, (short) 5),
                    Arguments.of((short) -6, (short) -5, (short) 5));
        }

        Stream<Arguments> positiveNumbersProvider() {
            return Stream.of(Arguments.of((short) 5, (short) 0, (short) 10),
                    Arguments.of((short) 1, (short) 0, (short) 10), Arguments.of((short) 9, (short) 0, (short) 10),
                    Arguments.of((short) 0, (short) 0, (short) 10), Arguments.of((short) 10, (short) 0, (short) 10));
        }

        Stream<Arguments> badPositiveNumbersProvider() {
            return Stream.of(Arguments.of((short) 11, (short) 0, (short) 10),
                    Arguments.of((short) -1, (short) 0, (short) 10));
        }

        @ParameterizedTest
        @MethodSource("negativeNumbersProvider")
        void testWhenInRangeFromNegativeToNegative(short index, short from, short to) {
            assertDoesNotThrow(() -> Preconditions.requireRange(index, from, to, new TestException()));
        }

        @Test
        void testWhenExceptionIsNull() {
            RuntimeException emptyException = null;
            assertThrows(NullPointerException.class,
                    () -> Preconditions.requireRange((short) 1, (short) 0, (short) 2, emptyException));
        }

        @ParameterizedTest
        @MethodSource("negativeAndPositiveNumbersProvider")
        void testWhenInRangeFromNegativeToPositive(short index, short from, short to) {
            assertDoesNotThrow(() -> Preconditions.requireRange(index, from, to, new TestException()));
        }

        @ParameterizedTest
        @MethodSource("positiveNumbersProvider")
        void testWhenInRangeFromPositiveToPositive(short index, short from, short to) {
            assertDoesNotThrow(() -> Preconditions.requireRange(index, from, to, new TestException()));
        }

        @ParameterizedTest
        @MethodSource("badNegativeNumbersProvider")
        void testWhenOutOfRangeFromNegativeToNegative(short index, short from, short to) {
            assertThrows(TestException.class, () -> Preconditions.requireRange(index, from, to, new TestException()));
        }

        @ParameterizedTest
        @MethodSource("badNegativeAndPositiveNumbersProvider")
        void testWhenOutOfRangeFromNegativeToPositive(short index, short from, short to) {
            assertThrows(TestException.class, () -> Preconditions.requireRange(index, from, to, new TestException()));
        }

        @ParameterizedTest
        @MethodSource("badPositiveNumbersProvider")
        void testWhenOutOfRangeFromPositiveToPositive(short index, short from, short to) {
            assertThrows(TestException.class, () -> Preconditions.requireRange(index, from, to, new TestException()));
        }
    }

    /**
     * {@link Preconditions#requireRange(byte, byte, byte)}
     * メソッドのテストケースを管理するインナークラスです。
     *
     * @author Kato Shinya
     * @since 1.0
     * @version 1.0
     */
    @Nested
    @TestInstance(Lifecycle.PER_CLASS)
    class TestRequireByteRangeFromTo {

        Stream<Arguments> negativeNumbersProvider() {
            return Stream.of(Arguments.of((byte) -5, (byte) -10, (byte) -1),
                    Arguments.of((byte) -2, (byte) -10, (byte) -1), Arguments.of((byte) -9, (byte) -10, (byte) -1),
                    Arguments.of((byte) -1, (byte) -10, (byte) -1), Arguments.of((byte) -10, (byte) -10, (byte) -1));
        }

        Stream<Arguments> badNegativeNumbersProvider() {
            return Stream.of(Arguments.of((byte) 0, (byte) -10, (byte) -1),
                    Arguments.of((byte) -11, (byte) -10, (byte) -1));
        }

        Stream<Arguments> negativeAndPositiveNumbersProvider() {
            return Stream.of(Arguments.of((byte) 0, (byte) -5, (byte) 5), Arguments.of((byte) -4, (byte) -5, (byte) 5),
                    Arguments.of((byte) 4, (byte) -5, (byte) 5), Arguments.of((byte) -5, (byte) -5, (byte) 5),
                    Arguments.of((byte) 5, (byte) -5, (byte) 5));
        }

        Stream<Arguments> badNegativeAndPositiveNumbersProvider() {
            return Stream.of(Arguments.of((byte) 6, (byte) -5, (byte) 5), Arguments.of((byte) -6, (byte) -5, (byte) 5));
        }

        Stream<Arguments> positiveNumbersProvider() {
            return Stream.of(Arguments.of((byte) 5, (byte) 0, (byte) 10), Arguments.of((byte) 1, (byte) 0, (byte) 10),
                    Arguments.of((byte) 9, (byte) 0, (byte) 10), Arguments.of((byte) 0, (byte) 0, (byte) 10),
                    Arguments.of((byte) 10, (byte) 0, (byte) 10));
        }

        Stream<Arguments> badPositiveNumbersProvider() {
            return Stream.of(Arguments.of((byte) 11, (byte) 0, (byte) 10),
                    Arguments.of((byte) -1, (byte) 0, (byte) 10));
        }

        @ParameterizedTest
        @MethodSource("negativeNumbersProvider")
        void testWhenInRangeFromNegativeToNegative(byte index, byte from, byte to) {
            assertDoesNotThrow(() -> Preconditions.requireRange(index, from, to));
        }

        @ParameterizedTest
        @MethodSource("negativeAndPositiveNumbersProvider")
        void testWhenInRangeFromNegativeToPositive(byte index, byte from, byte to) {
            assertDoesNotThrow(() -> Preconditions.requireRange(index, from, to));
        }

        @ParameterizedTest
        @MethodSource("positiveNumbersProvider")
        void testWhenInRangeFromPositiveToPositive(byte index, byte from, byte to) {
            assertDoesNotThrow(() -> Preconditions.requireRange(index, from, to));
        }

        @ParameterizedTest
        @MethodSource("badNegativeNumbersProvider")
        void testWhenOutOfRangeFromNegativeToNegative(byte index, byte from, byte to) {
            final IndexOutOfBoundsException exception = assertThrows(IndexOutOfBoundsException.class,
                    () -> Preconditions.requireRange(index, from, to));
            assertEquals(String.format(EXCEPTION_MESSAGE_BYTE_FOR_OUT_OF_BOUNDS_FROM_TO, index, from, to),
                    exception.getMessage());
        }

        @ParameterizedTest
        @MethodSource("badNegativeAndPositiveNumbersProvider")
        void testWhenOutOfRangeFromNegativeToPositive(byte index, byte from, byte to) {
            final IndexOutOfBoundsException exception = assertThrows(IndexOutOfBoundsException.class,
                    () -> Preconditions.requireRange(index, from, to));
            assertEquals(String.format(EXCEPTION_MESSAGE_BYTE_FOR_OUT_OF_BOUNDS_FROM_TO, index, from, to),
                    exception.getMessage());
        }

        @ParameterizedTest
        @MethodSource("badPositiveNumbersProvider")
        void testWhenOutOfRangeFromPositiveToPositive(byte index, byte from, byte to) {
            final IndexOutOfBoundsException exception = assertThrows(IndexOutOfBoundsException.class,
                    () -> Preconditions.requireRange(index, from, to));
            assertEquals(String.format(EXCEPTION_MESSAGE_BYTE_FOR_OUT_OF_BOUNDS_FROM_TO, index, from, to),
                    exception.getMessage());
        }
    }

    /**
     * {@link Preconditions#requireRange(byte, byte, byte, RuntimeException)}
     * メソッドのテストケースを管理するインナークラスです。
     *
     * @author Kato Shinya
     * @since 1.0
     * @version 1.0
     */
    @Nested
    @TestInstance(Lifecycle.PER_CLASS)
    class TestRequireByteRangeFromToWithException {

        Stream<Arguments> negativeNumbersProvider() {
            return Stream.of(Arguments.of((byte) -5, (byte) -10, (byte) -1),
                    Arguments.of((byte) -2, (byte) -10, (byte) -1), Arguments.of((byte) -9, (byte) -10, (byte) -1),
                    Arguments.of((byte) -1, (byte) -10, (byte) -1), Arguments.of((byte) -10, (byte) -10, (byte) -1));
        }

        Stream<Arguments> badNegativeNumbersProvider() {
            return Stream.of(Arguments.of((byte) 0, (byte) -10, (byte) -1),
                    Arguments.of((byte) -11, (byte) -10, (byte) -1));
        }

        Stream<Arguments> negativeAndPositiveNumbersProvider() {
            return Stream.of(Arguments.of((byte) 0, (byte) -5, (byte) 5), Arguments.of((byte) -4, (byte) -5, (byte) 5),
                    Arguments.of((byte) 4, (byte) -5, (byte) 5), Arguments.of((byte) -5, (byte) -5, (byte) 5),
                    Arguments.of((byte) 5, (byte) -5, (byte) 5));
        }

        Stream<Arguments> badNegativeAndPositiveNumbersProvider() {
            return Stream.of(Arguments.of((byte) 6, (byte) -5, (byte) 5), Arguments.of((byte) -6, (byte) -5, (byte) 5));
        }

        Stream<Arguments> positiveNumbersProvider() {
            return Stream.of(Arguments.of((byte) 5, (byte) 0, (byte) 10), Arguments.of((byte) 1, (byte) 0, (byte) 10),
                    Arguments.of((byte) 9, (byte) 0, (byte) 10), Arguments.of((byte) 0, (byte) 0, (byte) 10),
                    Arguments.of((byte) 10, (byte) 0, (byte) 10));
        }

        Stream<Arguments> badPositiveNumbersProvider() {
            return Stream.of(Arguments.of((byte) 11, (byte) 0, (byte) 10),
                    Arguments.of((byte) -1, (byte) 0, (byte) 10));
        }

        @ParameterizedTest
        @MethodSource("negativeNumbersProvider")
        void testWhenInRangeFromNegativeToNegative(byte index, byte from, byte to) {
            assertDoesNotThrow(() -> Preconditions.requireRange(index, from, to, new TestException()));
        }

        @Test
        void testWhenExceptionIsNull() {
            RuntimeException emptyException = null;
            assertThrows(NullPointerException.class,
                    () -> Preconditions.requireRange((byte) 1, (byte) 0, (byte) 2, emptyException));
        }

        @ParameterizedTest
        @MethodSource("negativeAndPositiveNumbersProvider")
        void testWhenInRangeFromNegativeToPositive(byte index, byte from, byte to) {
            assertDoesNotThrow(() -> Preconditions.requireRange(index, from, to, new TestException()));
        }

        @ParameterizedTest
        @MethodSource("positiveNumbersProvider")
        void testWhenInRangeFromPositiveToPositive(byte index, byte from, byte to) {
            assertDoesNotThrow(() -> Preconditions.requireRange(index, from, to, new TestException()));
        }

        @ParameterizedTest
        @MethodSource("badNegativeNumbersProvider")
        void testWhenOutOfRangeFromNegativeToNegative(byte index, byte from, byte to) {
            assertThrows(TestException.class, () -> Preconditions.requireRange(index, from, to, new TestException()));
        }

        @ParameterizedTest
        @MethodSource("badNegativeAndPositiveNumbersProvider")
        void testWhenOutOfRangeFromNegativeToPositive(byte index, byte from, byte to) {
            assertThrows(TestException.class, () -> Preconditions.requireRange(index, from, to, new TestException()));
        }

        @ParameterizedTest
        @MethodSource("badPositiveNumbersProvider")
        void testWhenOutOfRangeFromPositiveToPositive(byte index, byte from, byte to) {
            assertThrows(TestException.class, () -> Preconditions.requireRange(index, from, to, new TestException()));
        }
    }

    /**
     * {@link Preconditions#requireRange(float, float, float)}
     * メソッドのテストケースを管理するインナークラスです。
     *
     * @author Kato Shinya
     * @since 1.0
     * @version 1.0
     */
    @Nested
    @TestInstance(Lifecycle.PER_CLASS)
    class TestRequireFloatRangeFromTo {

        Stream<Arguments> negativeNumbersProvider() {
            return Stream.of(Arguments.of(-5.0f, -10.0f, -1.0f), Arguments.of(-2.0f, -10.0f, -1.0f),
                    Arguments.of(-9.0f, -10.0f, -1.0f), Arguments.of(-1.0f, -10.0f, -1.0f),
                    Arguments.of(-10.0f, -10.0f, -1.0f));
        }

        Stream<Arguments> badNegativeNumbersProvider() {
            return Stream.of(Arguments.of(0.0f, -10.0f, -1.0f), Arguments.of(-11.0f, -10.0f, -1.0f));
        }

        Stream<Arguments> negativeAndPositiveNumbersProvider() {
            return Stream.of(Arguments.of(0.0f, -5.0f, 5.0f), Arguments.of(-4.0f, -5.0f, 5.0f),
                    Arguments.of(4.0f, -5.0f, 5.0f), Arguments.of(-5.0f, -5.0f, 5.0f), Arguments.of(5.0f, -5.0f, 5.0f));
        }

        Stream<Arguments> badNegativeAndPositiveNumbersProvider() {
            return Stream.of(Arguments.of(6.0f, -5.0f, 5.0f), Arguments.of(-6.0f, -5.0f, 5.0f));
        }

        Stream<Arguments> positiveNumbersProvider() {
            return Stream.of(Arguments.of(5.0f, 0.0f, 10.0f), Arguments.of(1.0f, 0.0f, 10.0f),
                    Arguments.of(9.0f, 0.0f, 10.0f), Arguments.of(0.0f, 0.0f, 10.0f), Arguments.of(10.0f, 0.0f, 10.0f));
        }

        Stream<Arguments> badPositiveNumbersProvider() {
            return Stream.of(Arguments.of(11.0f, 0.0f, 10.0f), Arguments.of(-1.0f, 0.0f, 10.0f));
        }

        @ParameterizedTest
        @MethodSource("negativeNumbersProvider")
        void testWhenInRangeFromNegativeToNegative(float index, float from, float to) {
            assertDoesNotThrow(() -> Preconditions.requireRange(index, from, to));
        }

        @ParameterizedTest
        @MethodSource("negativeAndPositiveNumbersProvider")
        void testWhenInRangeFromNegativeToPositive(float index, float from, float to) {
            assertDoesNotThrow(() -> Preconditions.requireRange(index, from, to));
        }

        @ParameterizedTest
        @MethodSource("positiveNumbersProvider")
        void testWhenInRangeFromPositiveToPositive(float index, float from, float to) {
            assertDoesNotThrow(() -> Preconditions.requireRange(index, from, to));
        }

        @ParameterizedTest
        @MethodSource("badNegativeNumbersProvider")
        void testWhenOutOfRangeFromNegativeToNegative(float index, float from, float to) {
            final IndexOutOfBoundsException exception = assertThrows(IndexOutOfBoundsException.class,
                    () -> Preconditions.requireRange(index, from, to));
            assertEquals(String.format(EXCEPTION_MESSAGE_FLOAT_FOR_OUT_OF_BOUNDS_FROM_TO, index, from, to),
                    exception.getMessage());
        }

        @ParameterizedTest
        @MethodSource("badNegativeAndPositiveNumbersProvider")
        void testWhenOutOfRangeFromNegativeToPositive(float index, float from, float to) {
            final IndexOutOfBoundsException exception = assertThrows(IndexOutOfBoundsException.class,
                    () -> Preconditions.requireRange(index, from, to));
            assertEquals(String.format(EXCEPTION_MESSAGE_FLOAT_FOR_OUT_OF_BOUNDS_FROM_TO, index, from, to),
                    exception.getMessage());
        }

        @ParameterizedTest
        @MethodSource("badPositiveNumbersProvider")
        void testWhenOutOfRangeFromPositiveToPositive(float index, float from, float to) {
            final IndexOutOfBoundsException exception = assertThrows(IndexOutOfBoundsException.class,
                    () -> Preconditions.requireRange(index, from, to));
            assertEquals(String.format(EXCEPTION_MESSAGE_FLOAT_FOR_OUT_OF_BOUNDS_FROM_TO, index, from, to),
                    exception.getMessage());
        }
    }

    /**
     * {@link Preconditions#requireRange(float, float, float, RuntimeException)}
     * メソッドのテストケースを管理するインナークラスです。
     *
     * @author Kato Shinya
     * @since 1.0
     * @version 1.0
     */
    @Nested
    @TestInstance(Lifecycle.PER_CLASS)
    class TestRequireFloatRangeFromToWithException {

        Stream<Arguments> negativeNumbersProvider() {
            return Stream.of(Arguments.of(-5.0f, -10.0f, -1.0f), Arguments.of(-2.0f, -10.0f, -1.0f),
                    Arguments.of(-9.0f, -10.0f, -1.0f), Arguments.of(-1.0f, -10.0f, -1.0f),
                    Arguments.of(-10.0f, -10.0f, -1.0f));
        }

        Stream<Arguments> badNegativeNumbersProvider() {
            return Stream.of(Arguments.of(0.0f, -10.0f, -1.0f), Arguments.of(-11.0f, -10.0f, -1.0f));
        }

        Stream<Arguments> negativeAndPositiveNumbersProvider() {
            return Stream.of(Arguments.of(0.0f, -5.0f, 5.0f), Arguments.of(-4.0f, -5.0f, 5.0f),
                    Arguments.of(4.0f, -5.0f, 5.0f), Arguments.of(-5.0f, -5.0f, 5.0f), Arguments.of(5.0f, -5.0f, 5.0f));
        }

        Stream<Arguments> badNegativeAndPositiveNumbersProvider() {
            return Stream.of(Arguments.of(6.0f, -5.0f, 5.0f), Arguments.of(-6.0f, -5.0f, 5.0f));
        }

        Stream<Arguments> positiveNumbersProvider() {
            return Stream.of(Arguments.of(5.0f, 0.0f, 10.0f), Arguments.of(1.0f, 0.0f, 10.0f),
                    Arguments.of(9.0f, 0.0f, 10.0f), Arguments.of(0.0f, 0.0f, 10.0f), Arguments.of(10.0f, 0.0f, 10.0f));
        }

        Stream<Arguments> badPositiveNumbersProvider() {
            return Stream.of(Arguments.of(11.0f, 0.0f, 10.0f), Arguments.of(-1.0f, 0.0f, 10.0f));
        }

        @ParameterizedTest
        @MethodSource("negativeNumbersProvider")
        void testWhenInRangeFromNegativeToNegative(float index, float from, float to) {
            assertDoesNotThrow(() -> Preconditions.requireRange(index, from, to, new TestException()));
        }

        @Test
        void testWhenExceptionIsNull() {
            RuntimeException emptyException = null;
            assertThrows(NullPointerException.class,
                    () -> Preconditions.requireRange(1.0f, 0.0f, 2.0f, emptyException));
        }

        @ParameterizedTest
        @MethodSource("negativeAndPositiveNumbersProvider")
        void testWhenInRangeFromNegativeToPositive(float index, float from, float to) {
            assertDoesNotThrow(() -> Preconditions.requireRange(index, from, to, new TestException()));
        }

        @ParameterizedTest
        @MethodSource("positiveNumbersProvider")
        void testWhenInRangeFromPositiveToPositive(float index, float from, float to) {
            assertDoesNotThrow(() -> Preconditions.requireRange(index, from, to, new TestException()));
        }

        @ParameterizedTest
        @MethodSource("badNegativeNumbersProvider")
        void testWhenOutOfRangeFromNegativeToNegative(float index, float from, float to) {
            assertThrows(TestException.class, () -> Preconditions.requireRange(index, from, to, new TestException()));
        }

        @ParameterizedTest
        @MethodSource("badNegativeAndPositiveNumbersProvider")
        void testWhenOutOfRangeFromNegativeToPositive(float index, float from, float to) {
            assertThrows(TestException.class, () -> Preconditions.requireRange(index, from, to, new TestException()));
        }

        @ParameterizedTest
        @MethodSource("badPositiveNumbersProvider")
        void testWhenOutOfRangeFromPositiveToPositive(float index, float from, float to) {
            assertThrows(TestException.class, () -> Preconditions.requireRange(index, from, to, new TestException()));
        }
    }

    /**
     * {@link Preconditions#requireRange(double, double, double)}
     * メソッドのテストケースを管理するインナークラスです。
     *
     * @author Kato Shinya
     * @since 1.0
     * @version 1.0
     */
    @Nested
    @TestInstance(Lifecycle.PER_CLASS)
    class TestRequireDoubleRangeFromTo {

        Stream<Arguments> negativeNumbersProvider() {
            return Stream.of(Arguments.of(-5.0d, -10.0d, -1.0d), Arguments.of(-2.0d, -10.0d, -1.0d),
                    Arguments.of(-9.0d, -10.0d, -1.0d), Arguments.of(-1.0d, -10.0d, -1.0d),
                    Arguments.of(-10.0d, -10.0d, -1.0d));
        }

        Stream<Arguments> badNegativeNumbersProvider() {
            return Stream.of(Arguments.of(0.0d, -10.0d, -1.0d), Arguments.of(-11.0d, -10.0d, -1.0d));
        }

        Stream<Arguments> negativeAndPositiveNumbersProvider() {
            return Stream.of(Arguments.of(0.0d, -5.0d, 5.0d), Arguments.of(-4.0d, -5.0d, 5.0d),
                    Arguments.of(4.0d, -5.0d, 5.0d), Arguments.of(-5.0d, -5.0d, 5.0d), Arguments.of(5.0d, -5.0d, 5.0d));
        }

        Stream<Arguments> badNegativeAndPositiveNumbersProvider() {
            return Stream.of(Arguments.of(6.0d, -5.0d, 5.0d), Arguments.of(-6.0d, -5.0d, 5.0d));
        }

        Stream<Arguments> positiveNumbersProvider() {
            return Stream.of(Arguments.of(5.0d, 0.0d, 10.0d), Arguments.of(1.0d, 0.0d, 10.0d),
                    Arguments.of(9.0d, 0.0d, 10.0d), Arguments.of(0.0d, 0.0d, 10.0d), Arguments.of(10.0d, 0.0d, 10.0d));
        }

        Stream<Arguments> badPositiveNumbersProvider() {
            return Stream.of(Arguments.of(11.0d, 0.0d, 10.0d), Arguments.of(-1.0d, 0.0d, 10.0d));
        }

        @ParameterizedTest
        @MethodSource("negativeNumbersProvider")
        void testWhenInRangeFromNegativeToNegative(double index, double from, double to) {
            assertDoesNotThrow(() -> Preconditions.requireRange(index, from, to));
        }

        @ParameterizedTest
        @MethodSource("negativeAndPositiveNumbersProvider")
        void testWhenInRangeFromNegativeToPositive(double index, double from, double to) {
            assertDoesNotThrow(() -> Preconditions.requireRange(index, from, to));
        }

        @ParameterizedTest
        @MethodSource("positiveNumbersProvider")
        void testWhenInRangeFromPositiveToPositive(double index, double from, double to) {
            assertDoesNotThrow(() -> Preconditions.requireRange(index, from, to));
        }

        @ParameterizedTest
        @MethodSource("badNegativeNumbersProvider")
        void testWhenOutOfRangeFromNegativeToNegative(double index, double from, double to) {
            final IndexOutOfBoundsException exception = assertThrows(IndexOutOfBoundsException.class,
                    () -> Preconditions.requireRange(index, from, to));
            assertEquals(String.format(EXCEPTION_MESSAGE_DOUBLE_FOR_OUT_OF_BOUNDS_FROM_TO, index, from, to),
                    exception.getMessage());
        }

        @ParameterizedTest
        @MethodSource("badNegativeAndPositiveNumbersProvider")
        void testWhenOutOfRangeFromNegativeToPositive(double index, double from, double to) {
            final IndexOutOfBoundsException exception = assertThrows(IndexOutOfBoundsException.class,
                    () -> Preconditions.requireRange(index, from, to));
            assertEquals(String.format(EXCEPTION_MESSAGE_DOUBLE_FOR_OUT_OF_BOUNDS_FROM_TO, index, from, to),
                    exception.getMessage());
        }

        @ParameterizedTest
        @MethodSource("badPositiveNumbersProvider")
        void testWhenOutOfRangeFromPositiveToPositive(double index, double from, double to) {
            final IndexOutOfBoundsException exception = assertThrows(IndexOutOfBoundsException.class,
                    () -> Preconditions.requireRange(index, from, to));
            assertEquals(String.format(EXCEPTION_MESSAGE_DOUBLE_FOR_OUT_OF_BOUNDS_FROM_TO, index, from, to),
                    exception.getMessage());
        }
    }

    /**
     * {@link Preconditions#requireRange(double, double, double, RuntimeException)}
     * メソッドのテストケースを管理するインナークラスです。
     *
     * @author Kato Shinya
     * @since 1.0
     * @version 1.0
     */
    @Nested
    @TestInstance(Lifecycle.PER_CLASS)
    class TestRequireDoubleRangeFromToWithException {

        Stream<Arguments> negativeNumbersProvider() {
            return Stream.of(Arguments.of(-5.0d, -10.0d, -1.0d), Arguments.of(-2.0d, -10.0d, -1.0d),
                    Arguments.of(-9.0d, -10.0d, -1.0d), Arguments.of(-1.0d, -10.0d, -1.0d),
                    Arguments.of(-10.0d, -10.0d, -1.0d));
        }

        Stream<Arguments> badNegativeNumbersProvider() {
            return Stream.of(Arguments.of(0.0d, -10.0d, -1.0d), Arguments.of(-11.0d, -10.0d, -1.0d));
        }

        Stream<Arguments> negativeAndPositiveNumbersProvider() {
            return Stream.of(Arguments.of(0.0d, -5.0d, 5.0d), Arguments.of(-4.0d, -5.0d, 5.0d),
                    Arguments.of(4.0d, -5.0d, 5.0d), Arguments.of(-5.0d, -5.0d, 5.0d), Arguments.of(5.0d, -5.0d, 5.0d));
        }

        Stream<Arguments> badNegativeAndPositiveNumbersProvider() {
            return Stream.of(Arguments.of(6.0d, -5.0d, 5.0d), Arguments.of(-6.0d, -5.0d, 5.0d));
        }

        Stream<Arguments> positiveNumbersProvider() {
            return Stream.of(Arguments.of(5.0d, 0.0d, 10.0d), Arguments.of(1.0d, 0.0d, 10.0d),
                    Arguments.of(9.0d, 0.0d, 10.0d), Arguments.of(0.0d, 0.0d, 10.0d), Arguments.of(10.0d, 0.0d, 10.0d));
        }

        Stream<Arguments> badPositiveNumbersProvider() {
            return Stream.of(Arguments.of(11.0d, 0.0d, 10.0d), Arguments.of(-1.0d, 0.0d, 10.0d));
        }

        @ParameterizedTest
        @MethodSource("negativeNumbersProvider")
        void testWhenInRangeFromNegativeToNegative(double index, double from, double to) {
            assertDoesNotThrow(() -> Preconditions.requireRange(index, from, to, new TestException()));
        }

        @Test
        void testWhenExceptionIsNull() {
            RuntimeException emptyException = null;
            assertThrows(NullPointerException.class,
                    () -> Preconditions.requireRange(1.0d, 0.0d, 2.0d, emptyException));
        }

        @ParameterizedTest
        @MethodSource("negativeAndPositiveNumbersProvider")
        void testWhenInRangeFromNegativeToPositive(double index, double from, double to) {
            assertDoesNotThrow(() -> Preconditions.requireRange(index, from, to, new TestException()));
        }

        @ParameterizedTest
        @MethodSource("positiveNumbersProvider")
        void testWhenInRangeFromPositiveToPositive(double index, double from, double to) {
            assertDoesNotThrow(() -> Preconditions.requireRange(index, from, to, new TestException()));
        }

        @ParameterizedTest
        @MethodSource("badNegativeNumbersProvider")
        void testWhenOutOfRangeFromNegativeToNegative(double index, double from, double to) {
            assertThrows(TestException.class, () -> Preconditions.requireRange(index, from, to, new TestException()));
        }

        @ParameterizedTest
        @MethodSource("badNegativeAndPositiveNumbersProvider")
        void testWhenOutOfRangeFromNegativeToPositive(double index, double from, double to) {
            assertThrows(TestException.class, () -> Preconditions.requireRange(index, from, to, new TestException()));
        }

        @ParameterizedTest
        @MethodSource("badPositiveNumbersProvider")
        void testWhenOutOfRangeFromPositiveToPositive(double index, double from, double to) {
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
            final PreconditionFailedException exception = assertThrows(PreconditionFailedException.class,
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
            final PreconditionFailedException exception = assertThrows(PreconditionFailedException.class,
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
            final PreconditionFailedException exception = assertThrows(PreconditionFailedException.class,
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
            final PreconditionFailedException exception = assertThrows(PreconditionFailedException.class,
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
            final PreconditionFailedException exception = assertThrows(PreconditionFailedException.class,
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
            final PreconditionFailedException exception = assertThrows(PreconditionFailedException.class,
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
            final PreconditionFailedException exception = assertThrows(PreconditionFailedException.class,
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
     * float数値が負数だった場合の例外メッセージ
     */
    private static final String EXCEPTION_MESSAGE_FOR_NEGATIVE_FLOAT_NUMBER = "Float number must be positive but %s was given";

    /**
     * float数値が正数だった場合の例外メッセージ
     */
    private static final String EXCEPTION_MESSAGE_FOR_POSITIVE_FLOAT_NUMBER = "Float number must be negative but %s was given";

    /**
     * double数値が負数だった場合の例外メッセージ
     */
    private static final String EXCEPTION_MESSAGE_FOR_NEGATIVE_DOUBLE_NUMBER = "Double number must be positive but %s was given";

    /**
     * double数値が正数だった場合の例外メッセージ
     */
    private static final String EXCEPTION_MESSAGE_FOR_POSITIVE_DOUBLE_NUMBER = "Double number must be negative but %s was given";

    /**
     * 範囲外だった場合の例外メッセージ
     */
    private static final String EXCEPTION_MESSAGE_FOR_OUT_OF_BOUNDS_FROM = "Index %s out-of-bounds for range from length %s";

    /**
     * 範囲外だった場合の例外メッセージ
     */
    private static final String EXCEPTION_MESSAGE_LONG_FOR_OUT_OF_BOUNDS_FROM = "Long index %s out-of-bounds for range from length %s";

    /**
     * 範囲外だった場合の例外メッセージ
     */
    private static final String EXCEPTION_MESSAGE_SHORT_FOR_OUT_OF_BOUNDS_FROM = "Short index %s out-of-bounds for range from length %s";

    /**
     * 範囲外だった場合の例外メッセージ
     */
    private static final String EXCEPTION_MESSAGE_BYTE_FOR_OUT_OF_BOUNDS_FROM = "Byte index %s out-of-bounds for range from length %s";

    /**
     * 範囲外だった場合の例外メッセージ
     */
    private static final String EXCEPTION_MESSAGE_FLOAT_FOR_OUT_OF_BOUNDS_FROM = "Float index %s out-of-bounds for range from length %s";

    /**
     * 範囲外だった場合の例外メッセージ
     */
    private static final String EXCEPTION_MESSAGE_DOUBLE_FOR_OUT_OF_BOUNDS_FROM = "Double index %s out-of-bounds for range from length %s";

    /**
     * 範囲外だった場合の例外メッセージ
     */
    private static final String EXCEPTION_MESSAGE_FOR_OUT_OF_BOUNDS_TO = "Index %s out-of-bounds for range from length 0 to length %s";

    /**
     * 範囲外だった場合の例外メッセージ
     */
    private static final String EXCEPTION_MESSAGE_LONG_FOR_OUT_OF_BOUNDS_TO = "Long index %s out-of-bounds for range from length 0 to length %s";

    /**
     * 範囲外だった場合の例外メッセージ
     */
    private static final String EXCEPTION_MESSAGE_SHORT_FOR_OUT_OF_BOUNDS_TO = "Short index %s out-of-bounds for range from length 0 to length %s";

    /**
     * 範囲外だった場合の例外メッセージ
     */
    private static final String EXCEPTION_MESSAGE_BYTE_FOR_OUT_OF_BOUNDS_TO = "Byte index %s out-of-bounds for range from length 0 to length %s";

    /**
     * 範囲外だった場合の例外メッセージ
     */
    private static final String EXCEPTION_MESSAGE_FLOAT_FOR_OUT_OF_BOUNDS_TO = "Float index %s out-of-bounds for range from length 0 to length %s";

    /**
     * 範囲外だった場合の例外メッセージ
     */
    private static final String EXCEPTION_MESSAGE_DOUBLE_FOR_OUT_OF_BOUNDS_TO = "Double index %s out-of-bounds for range from length 0 to length %s";

    /**
     * 範囲外だった場合の例外メッセージ
     */
    private static final String EXCEPTION_MESSAGE_FOR_OUT_OF_BOUNDS_FROM_TO = "Index %s out-of-bounds for range from length %s to length %s";

    /**
     * 範囲外だった場合の例外メッセージ
     */
    private static final String EXCEPTION_MESSAGE_LONG_FOR_OUT_OF_BOUNDS_FROM_TO = "Long index %s out-of-bounds for range from length %s to length %s";

    /**
     * 範囲外だった場合の例外メッセージ
     */
    private static final String EXCEPTION_MESSAGE_SHORT_FOR_OUT_OF_BOUNDS_FROM_TO = "Short index %s out-of-bounds for range from length %s to length %s";

    /**
     * 範囲外だった場合の例外メッセージ
     */
    private static final String EXCEPTION_MESSAGE_BYTE_FOR_OUT_OF_BOUNDS_FROM_TO = "Byte index %s out-of-bounds for range from length %s to length %s";

    /**
     * 範囲外だった場合の例外メッセージ
     */
    private static final String EXCEPTION_MESSAGE_FLOAT_FOR_OUT_OF_BOUNDS_FROM_TO = "Float index %s out-of-bounds for range from length %s to length %s";

    /**
     * 範囲外だった場合の例外メッセージ
     */
    private static final String EXCEPTION_MESSAGE_DOUBLE_FOR_OUT_OF_BOUNDS_FROM_TO = "Double index %s out-of-bounds for range from length %s to length %s";

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
