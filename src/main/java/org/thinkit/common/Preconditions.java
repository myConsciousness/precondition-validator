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

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * The {@link Preconditions} interface provides a way to guarantee the
 * preconditions for arguments and data at the start of the process.
 * <p>
 * Each of the features provided by the {@link Preconditions} interface
 * validates for preconditions, and if the data being validated does not meet
 * the preconditions, it will always throw an exception corresponding to the
 * validation process.
 * <p>
 * In addition, each validation method has an option that allows you to specify
 * an arbitrary exception object or detailed message when an exception occurs,
 * so you can validate preconditions according to the implementation of your
 * application. The optional exception object must inherit from
 * {@link RuntimeException} .
 * <p>
 * If the data passed as an argument satisfies the specified prerequisites, the
 * validation method terminates without performing any processing other than
 * checking.
 *
 * <pre>
 * For example, here is an example of the following case where a non-empty string is required as a condition for starting a method:
 * <code>
 * Preconditions.requireNonEmpty(null);
 * &gt;&gt; NullPointerException
 *
 * Preconditions.requireNonEmpty("");
 * &gt;&gt; IllegalSequenceFoundException
 * </code>
 *
 * You can also specify arbitrary exception objects and detailed messages as follows:
 * <code>
 * Preconditions.requireNonEmpty("", "any exception message");
 * Preconditions.requireNonEmpty("", new AnyRuntimeException());
 * Preconditions.requireNonEmpty("", new AnyRuntimeException("any exception message"));
 * </code>
 * </pre>
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
public interface Preconditions {

    /**
     * Ensures that the reference to the {@code object} object passed as an argument
     * is not {@code null} . 
     * <p>
     * {@link NullPointerException} is always raised at runtime if the reference to
     * the {@code object} object is {@code null} .
     * <p>
     * Use the {@link #requireNonNull(Object, String)} method if you want to print
     * an arbitrary detailed message when an exception is raised.
     *
     * <pre>
     * If the object passed as an argument is null, NullPointerException will be thrown.
     * <code>
     * Preconditions.requireNonNull(null);
     * &gt;&gt; NullPointerException
     * </code>
     * </pre>
     *
     * <pre>
     * If the object passed as an argument is not null, it does nothing and ends the validation process.
     * <code>
     * Preconditions.requireNonNull("test");
     * </code>
     * </pre>
     *
     * @param object Object to be validated
     *
     * @throws NullPointerException If {@code object} passed as an argument is
     *                              {@code null}
     */
    static void requireNonNull(Object object) {
        requireNonNull(object, new NullPointerException());
    }

    /**
     * Ensures that the reference to the {@code object} object passed as an argument
     * is not {@code null} . 
     * <p>
     * If the {@code object} object is referenced by {@code null} , then
     * {@link NullPointerException} is always raised at runtime. The {@code message}
     * passed as an argument is output as a detailed message when an exception
     * occurs.
     *
     * <pre>
     * If the object passed as an argument is null, NullPointerException will be thrown.
     * The message passed as an argument will be printed as a detailed message when an exception occurs.
     * <code>
     * Preconditions.requireNonNull(null, "any message");
     * &gt;&gt; NullPointerException
     * </code>
     * </pre>
     *
     * <pre>
     * If the object passed as an argument is not null, it does nothing and ends the validation process.
     * <code>
     * Preconditions.requireNonNull("test", "any message");
     * </code>
     * </pre>
     *
     * @param object  Object to be validated
     * @param message Detailed messages to be output on exception throwing
     *
     * @throws NullPointerException If {@code object} passed as an argument is
     *                              {@code null}
     */
    static void requireNonNull(Object object, String message) {
        requireNonNull(object, new NullPointerException(message));
    }

    /**
     * Ensures that the reference to the {@code object} object passed as an argument
     * is not {@code null} . 
     * <p>
     * If the reference to a {@code object} object is {@code null} , any exception
     * object specified as an argument will be thrown.
     *
     * <pre>
     * If the object passed as an argument is null, then any exception object specified as an argument will be thrown.
     * <code>
     * Preconditions.requireNonNull(null, new AnyRuntimeException());
     * &gt;&gt; AnyRuntimeException
     * </code>
     * </pre>
     *
     * <pre>
     * If the object passed as an argument is not null, it does nothing and ends the validation process.
     * <code>
     * Preconditions.requireNonNull("test", new AnyRuntimeException());
     * </code>
     * </pre>
     *
     * @param object    Object to be validated
     * @param exception Any exception object that is thrown if the preconditions are
     *                  not met
     */
    static void requireNonNull(Object object, RuntimeException exception) {
        if (object == null) {
            throw exception;
        }
    }

    /**
     * Ensures that the string of the {@code sequence} object given as an argument
     * is not an empty string. 
     * <p>
     * {@link IllegalSequenceFoundException} is always raised at runtime if the
     * string of the {@code sequence} object is an empty string.
     * <p>
     * Use the {@link #requireNonEmpty(String)} method if a reference to a
     * {@code sequence} object specified as an argument is likely to be {@code null}
     * .
     * <p>
     * Use the {@link #requireNonBlank(String, String)} method if you want to print
     * out an arbitrary detailed message when an exception is thrown. Also, to throw
     * an arbitrary exception object when an exception is thrown, use the
     * {@link #requireNonBlank(String, RuntimeException)} method.
     *
     * <pre>
     * IllegalSequenceFoundException will be thrown if the argument passed to sequence is an empty string.
     * <code>
     * Preconditions.requireNonBlank("");
     * &gt;&gt; IllegalSequenceFoundException
     * </code>
     * </pre>
     *
     * <pre>
     * If the argument sequence is not an empty string, the function does nothing and ends the validation process.
     * <code>
     * Preconditions.requireNonBlank("test");
     * </code>
     * </pre>
     *
     * @param sequence The string to be validated
     *
     * @throws NullPointerException          If {@code null} is passed as an
     *                                       argument
     * @throws IllegalSequenceFoundException If an empty string is passed as an
     *                                       argument
     */
    static void requireNonBlank(String sequence) {
        requireNonBlank(sequence, new IllegalSequenceFoundException("String must not be blank"));
    }

    /**
     * Ensures that the string of the {@code sequence} object given as an argument
     * is not an empty string. 
     * <p>
     * If the string of a {@code sequence} object is an empty string,
     * {@link IllegalSequenceFoundException} will always be raised at runtime. The
     * {@code message} passed as an argument is output as a detailed message when an
     * exception occurs.
     * <p>
     * Use the {@link #requireNonEmpty(String)} method if a reference to a
     * {@code sequence} object specified as an argument is likely to be {@code null}
     * .
     *
     * <pre>
     * IllegalSequenceFoundException will be thrown if the argument passed to sequence is an empty string.
     * A message passed as an argument will be printed as a detailed message when an exception occurs.
     * <code>
     * Preconditions.requireNonBlank("", "any message");
     * &gt;&gt; IllegalSequenceFoundException
     * </code>
     * </pre>
     *
     * <pre>
     * If the argument sequence is not an empty string, the function does nothing and ends the validation process.
     * <code>
     * Preconditions.requireNonBlank("test", "any message");
     * </code>
     * </pre>
     *
     * @param sequence The string to be validated
     * @param message  Detailed messages to be output on exception throwing
     *
     * @throws NullPointerException          If {@code null} is passed as an
     *                                       argument
     * @throws IllegalSequenceFoundException If an empty string is passed as an
     *                                       argument
     */
    static void requireNonBlank(String sequence, String message) {
        requireNonBlank(sequence, new IllegalSequenceFoundException(message));
    }

    /**
     * Ensures that {@code sequence} given as an argument is not an empty string. 
     * <p>
     * Throws an {@code exception} object if the argument {@code sequence} is an
     * empty string. If you do not specify any exceptions, use the
     * {@link #requireNonBlank(String)} method.
     *
     * <pre>
     * If the argument sequence is an empty string, any exception object specified as an argument will be thrown.
     * <code>
     * Preconditions.requireNonBlank("", new AnyRuntimeException());
     * &gt;&gt; AnyRuntimeException
     * </code>
     * </pre>
     *
     * <pre>
     * If the argument sequence is not an empty string, the function does nothing and ends the validation process.
     * <code>
     * Preconditions.requireNonBlank("test", new AnyRuntimeException());
     * </code>
     * </pre>
     *
     * @param sequence  The string to be validated
     * @param exception Any exception object that is thrown if the preconditions are
     *                  not met
     *
     * @exception NullPointerException          If the exception object passed as an
     *                                          argument is {@code null}
     * @exception IllegalSequenceFoundException If executed by the
     *                                          {@link #requireNonBlank(String)}
     *                                          method and the {@code sequence}
     *                                          passed as an argument is an empty
     *                                          string
     */
    static void requireNonBlank(String sequence, RuntimeException exception) {
        requireNonNull(exception);

        if (sequence.isEmpty()) {
            throw exception;
        }
    }

    /**
     * Ensures that the reference to the {@code sequence} object specified as an
     * argument is not {@code null} or the string is not an empty string. 
     * <p>
     * {@link NullPointerException} is always raised at runtime if the reference to
     * a {@code sequence} object is {@code null} . And
     * {@link IllegalSequenceFoundException} is always raised at runtime if the
     * string of the {@code sequence} object is an empty string.
     * <p>
     * To specify an arbitrary exception object, use the
     * {@link #requireNonEmpty(String, RuntimeException)} method.
     *
     * <pre>
     * NullPointerException will be thrown if the argument passed to sequence is null.
     * <code>
     * Preconditions.requireNonEmpty(null);
     * &gt;&gt; NullPointerException
     * </code>
     * </pre>
     *
     * <pre>
     * IllegalSequenceFoundException will be thrown if the argument passed to sequence is an empty string.
     * <code>
     * Preconditions.requireNonEmpty("");
     * &gt;&gt; IllegalSequenceFoundException
     * </code>
     * </pre>
     *
     * <pre>
     * If the argument sequence is not null and is not an empty string, it does nothing and ends the validation process.
     * <code>
     * Preconditions.requireNonEmpty("test");
     * </code>
     * </pre>
     *
     * @param sequence The string to be validated
     *
     * @throws NullPointerException          If {@code null} is passed as an
     *                                       argument
     * @throws IllegalSequenceFoundException If an empty string is passed as an
     *                                       argument
     */
    static void requireNonEmpty(String sequence) {
        requireNonNull(sequence);
        requireNonBlank(sequence);
    }

    /**
     * Ensures that the reference to the {@code sequence} object specified as an
     * argument is not {@code null} or the string is not an empty string. 
     * <p>
     * If the {@code sequence} object is referenced by {@code null} , then
     * {@link NullPointerException} is always raised at runtime. The {@code message}
     * passed as an argument is output as a detailed message when an exception
     * occurs.
     * <p>
     * If the string of a {@code sequence} object is an empty string,
     * {@link IllegalSequenceFoundException} is always raised at runtime. The
     * {@code message} passed as an argument is output as a detailed message when an
     * exception occurs.
     * <p>
     * To specify an arbitrary exception object, use the
     * {@link #requireNonEmpty(String, RuntimeException)} method.
     *
     * <pre>
     * If the sequence argument is null, NullPointerException will be thrown.
     * A message passed as an argument will be printed as a detailed message if an exception is raised.
     * <code>
     * Preconditions.requireNonEmpty(null, "any message");
     * &gt;&gt; NullPointerException
     * </code>
     * </pre>
     *
     * <pre>
     * IllegalSequenceFoundException will be thrown if the argument passed to sequence is an empty string.
     * A message passed as an argument will be printed as a detailed message when an exception occurs.
     * <code>
     * Preconditions.requireNonEmpty("", "any message");
     * &gt;&gt; IllegalSequenceFoundException
     * </code>
     * </pre>
     *
     * <pre>
     * If the argument sequence is not null and is not an empty string, it does nothing and ends the validation process.
     * <code>
     * Preconditions.requireNonEmpty("test", "any message");
     * </code>
     * </pre>
     *
     * @param sequence The string to be validated
     * @param message  Detailed messages to be output on exception throwing
     *
     * @throws NullPointerException          If {@code null} is passed as an
     *                                       argument
     * @throws IllegalSequenceFoundException If an empty string is passed as an
     *                                       argument
     */
    static void requireNonEmpty(String sequence, String message) {
        requireNonNull(sequence, message);
        requireNonBlank(sequence, message);
    }

    /**
     * Ensures that the value of {@code sequence} passed as an argument is not
     * {@code null} or an empty string. 
     * <p>
     * Throws an exception if it is {@code null} or an empty string. If you do not
     * specify an arbitrary exception object, use the
     * {@link #requireNonEmpty(String)} method.
     *
     * <pre>
     * NullPointerException will be thrown if the argument passed to sequence is null.
     * <code>
     * Preconditions.requireNonEmpty(null, new AnyRuntimeException());
     * &gt;&gt; NullPointerException
     * </code>
     * </pre>
     *
     * <pre>
     * If the argument sequence is an empty string, any exception object specified as an argument will be thrown.
     * <code>
     * Preconditions.requireNonEmpty("", new AnyRuntimeException());
     * &gt;&gt; AnyRuntimeException
     * </code>
     * </pre>
     *
     * <pre>
     * If the argument sequence is not null and is not an empty string, it does nothing and ends the validation process.
     * <code>
     * Preconditions.requireNonEmpty("test", new AnyRuntimeException());
     * </code>
     * </pre>
     *
     * @param sequence  The string to be validated
     * @param exception Any exception object that is thrown if the preconditions are
     *                  not met
     *
     * @exception NullPointerException          If the exception object passed as an
     *                                          argument is {@code null} or if the
     *                                          {@code sequence} passed as an
     *                                          argument is {@code null}
     * @exception IllegalSequenceFoundException If {@code sequence} passed as an
     *                                          argument is an empty string
     */
    static void requireNonEmpty(String sequence, RuntimeException exception) {
        requireNonNull(exception);
        requireNonNull(sequence);
        requireNonBlank(sequence, exception);
    }

    /**
     * Ensures that the {@code number} argument is a positive number. 
     * <p>
     * If the argument {@code number} is negative,
     * {@link IllegalNumberFoundException} is always raised at runtime.
     * <p>
     * To specify an arbitrary exception object, use the
     * {@link #requirePositive(int, RuntimeException)} method.
     *
     * <pre>
     * IllegalNumberFoundException will be thrown if the number passed as an argument is negative.
     * <code>
     * Preconditions.requirePositive(-1);
     * &gt;&gt; IllegalNumberFoundException
     * </code>
     * </pre>
     *
     * <pre>
     * If the number passed as a number is positive, it does nothing and ends the validation process.
     * <code>
     * Preconditions.requirePositive(0);
     * </code>
     * </pre>
     *
     * @param number The number to be validated
     *
     * @throws IllegalNumberFoundException If the number of {@code number} specified
     *                                     as an argument is a negative number
     */
    static void requirePositive(int number) {
        requirePositive(number,
                new IllegalNumberFoundException(String.format("Number must be positive but %s was given", number)));
    }

    /**
     * Ensures that the {@code number} argument is a positive number. 
     * <p>
     * If the argument {@code number} is negative,
     * {@link IllegalNumberFoundException} will always be raised at runtime. The
     * {@code message} passed as an argument is output as a detailed message when an
     * exception occurs.
     * <p>
     * To specify an arbitrary exception object, use the
     * {@link #requirePositive(int, RuntimeException)} method.
     *
     * <pre>
     * If the argument number is negative IllegalNumberFoundException will be thrown.
     * The message passed as an argument will be output as a detailed message when an exception occurs.
     * <code>
     * Preconditions.requirePositive(-1, "any message");
     * &gt;&gt; IllegalNumberFoundException
     * </code>
     * </pre>
     *
     * <pre>
     * If the number passed as an argument is a positive number, it does nothing and exits the validation process.
     * <code>
     * Preconditions.requirePositive(0, "any message");
     * </code>
     * </pre>
     *
     * @param number  The number to be validated
     * @param message Detailed messages to be output on exception throwing
     *
     * @throws IllegalNumberFoundException If the number of {@code number} specified
     *                                     as an argument is a negative number
     */
    static void requirePositive(int number, String message) {
        requirePositive(number, new IllegalNumberFoundException(message));
    }

    /**
     * 引数として指定された {@code number} の数値が正数であることを保証します。 
     * <p>
     * 任意の例外オブジェクトを指定しない場合は {@link #requirePositive(int)} メソッドを使用してください。
     *
     * <pre>
     * 引数として渡された number が負数の場合は引数として指定された任意の例外オブジェクトがスローされます。
     * <code>
     * Preconditions.requirePositive(-1, new AnyRuntimeException());
     * &gt;&gt; AnyRuntimeException
     * </code>
     * </pre>
     *
     * <pre>
     * 引数として渡された number が正数の場合は何もせず当該検証処理を終了します。
     * <code>
     * Preconditions.requirePositive(0, new AnyRuntimeException());
     * </code>
     * </pre>
     *
     * @param number    検査対象の数値
     * @param exception 前提条件を満たさなかった場合にスローされる任意の例外オブジェクト
     *
     * @exception NullPointerException        引数として渡された例外オブジェクトが {@code null} の場合
     * @exception IllegalNumberFoundException {@link #requirePositive(int)}
     *                                        メソッドから実行され、指定された {@code number}
     *                                        の数値が負数の場合
     */
    static void requirePositive(int number, RuntimeException exception) {
        requireNonNull(exception);

        if (number < 0) {
            throw exception;
        }
    }

    /**
     * 引数として指定された {@code number} の数値が負数であることを保証します。 
     * <p>
     * 引数として指定された {@code number} の数値が正数である場合は {@link IllegalNumberFoundException}
     * が必ず実行時に発生します。
     * <p>
     * 任意の例外オブジェクトを指定する場合は {@link #requireNegative(int, RuntimeException)}
     * メソッドを使用してください。
     *
     * <pre>
     * 引数として渡された number が正数の場合は IllegalNumberFoundException がスローされます。
     * <code>
     * Preconditions.requireNegative(0);
     * &gt;&gt; IllegalNumberFoundException
     * </code>
     * </pre>
     *
     * <pre>
     * 引数として渡された number が負数の場合は何もせず当該検証処理を終了します。
     * <code>
     * Preconditions.requireNegative(-1);
     * </code>
     * </pre>
     *
     * @param number 検査対象の数値
     *
     * @throws IllegalNumberFoundException 引数として指定された {@code number} の数値が正数の場合
     */
    static void requireNegative(int number) {
        requireNegative(number,
                new IllegalNumberFoundException(String.format("Number must be negative but %s was given", number)));
    }

    /**
     * 引数として指定された {@code number} の数値が負数であることを保証します。 
     * <p>
     * 引数として指定された {@code number} の数値が正数である場合は {@link IllegalNumberFoundException}
     * が必ず実行時に発生します。引数として渡された {@code message} が詳細メッセージとして例外発生時に出力されます。
     * <p>
     * 任意の例外オブジェクトを指定する場合は {@link #requireNegative(int, RuntimeException)}
     * メソッドを使用してください。
     *
     * <pre>
     * 引数として渡された number が正数の場合は IllegalNumberFoundException がスローされます。
     * 引数として渡された message が例外発生時に詳細メッセージとして出力されます。
     * <code>
     * Preconditions.requireNegative(0, "any message");
     * &gt;&gt; IllegalNumberFoundException
     * </code>
     * </pre>
     *
     * <pre>
     * 引数として渡された number が負数の場合は何もせず当該検証処理を終了します。
     * <code>
     * Preconditions.requireNegative(-1, "any message");
     * </code>
     * </pre>
     *
     * @param number  検査対象の数値
     * @param message 例外スロー時に出力される詳細メッセージ
     *
     * @throws IllegalNumberFoundException 引数として指定された {@code number} の数値が正数の場合
     */
    static void requireNegative(int number, String message) {
        requireNegative(number, new IllegalNumberFoundException(message));
    }

    /**
     * 引数として指定された {@code number} の数値が負数であることを保証します。 
     * <p>
     * 引数として指定された {@code number} が正数である場合は引数として指定された任意の例外オブジェクトをスローします。
     * <p>
     * 任意の例外オブジェクトを指定しない場合は {@link #requireNegative(int)} メソッドを使用してください。
     *
     * <pre>
     * 引数として渡された number が正数の場合は引数として指定された任意の例外オブジェクトがスローされます。
     * <code>
     * Preconditions.requireNegative(0, new AnyRuntimeException());
     * &gt;&gt; AnyRuntimeException
     * </code>
     * </pre>
     *
     * <pre>
     * 引数として渡された number が負数の場合は何もせず当該検証処理を終了します。
     * <code>
     * Preconditions.requireNegative(-1, new AnyRuntimeException());
     * </code>
     * </pre>
     *
     * @param number    検査対象の数値
     * @param exception 前提条件を満たさなかった場合にスローされる任意の例外オブジェクト
     *
     * @exception NullPointerException        引数として渡された例外オブジェクトが {@code null} の場合
     * @exception IllegalNumberFoundException {@link #requireNegative(int)}
     *                                        メソッドから実行され、引数として渡された {@code number}
     *                                        の数値が正数の場合
     */
    static void requireNegative(int number, RuntimeException exception) {
        requireNonNull(exception);

        if (number >= 0) {
            throw exception;
        }
    }

    /**
     * 引数として指定された {@code index} が {@code to} で指定された範囲内の数値であることを保証します。 
     * <p>
     * 引数として指定された {@code index} が範囲外にある数値である場合は {@link IndexOutOfBoundsException}
     * が必ず実行時に発生します。
     * <p>
     * 任意の例外オブジェクトを指定する場合は {@link #requireRange(int, int, RuntimeException)}
     * メソッドを使用してください。
     *
     * <pre>
     * 引数として渡された index が to で指定された数値よりも大きい場合は IndexOutOfBoundsException がスローされます。
     * <code>
     * Preconditions.requireRange(10, 9);
     * &gt;&gt; IndexOutOfBoundsException
     * </code>
     * </pre>
     *
     * <pre>
     * 引数として渡された index が to で指定された数値以下の場合は何もせず当該検証処理を終了します。
     * <code>
     * Preconditions.requireRange(9, 10);
     * </code>
     * </pre>
     *
     * @param index 検査対象のインデックス
     * @param to    判定時の上限値
     *
     * @throws IndexOutOfBoundsException 引数として指定された {@code index} の数値が {@code to}
     *                                   で指定された範囲内に存在しない場合
     */
    static void requireRange(int index, int to) {
        requireRange(index, to, new IndexOutOfBoundsException(
                String.format("Index %s out-of-bounds for range from length 0 to length %s", index, to)));
    }

    /**
     * 引数として指定された {@code index} が {@code to} で指定された範囲内の数値であることを保証します。 
     * <p>
     * 引数として指定された {@code index} が範囲外にある数値である場合は {@link IndexOutOfBoundsException}
     * が必ず実行時に発生します。引数として渡された {@code message} が詳細メッセージとして例外発生時に出力されます。
     * <p>
     * 任意の例外オブジェクトを指定する場合は {@link #requireRange(int, int, RuntimeException)}
     * メソッドを使用してください。
     *
     * <pre>
     * 引数として渡された index が to で指定された数値よりも大きい場合は IndexOutOfBoundsException がスローされます。
     * 引数として渡された message が例外発生時に詳細メッセージとして出力されます。
     * <code>
     * Preconditions.requireRange(10, 9, "any message");
     * &gt;&gt; IndexOutOfBoundsException
     * </code>
     * </pre>
     *
     * <pre>
     * 引数として渡された index が to で指定された数値以下の場合は何もせず当該検証処理を終了します。
     * <code>
     * Preconditions.requireRange(9, 10, "any message");
     * </code>
     * </pre>
     *
     * @param index   検査対象のインデックス
     * @param to      判定時の上限値
     * @param message 例外スロー時に出力される詳細メッセージ
     *
     * @throws IndexOutOfBoundsException 引数として指定された {@code index} の数値が {@code to}
     *                                   で指定された範囲内に存在しない場合
     */
    static void requireRange(int index, int to, String message) {
        requireRange(index, to, new IndexOutOfBoundsException(message));
    }

    /**
     * 引数として指定された {@code index} が {@code to} で指定された範囲内の数値であることを保証します。 
     * <p>
     * 引数として指定された {@code index} が {@code to}
     * で指定された範囲内の数値ではない場合は、引数として指定された任意の例外オブジェクトをスローします。
     * {@link #requireRange(int, int)} メソッドから実行された場合は
     * {@link IndexOutOfBoundsException} を例外オブジェクトとしてスローします。
     * <p>
     * 任意の例外オブジェクトを指定しない場合は {@link #requireRange(int, int)} メソッドを使用してください。
     *
     * <pre>
     * 引数として渡された index が to で指定された数値よりも大きい場合は引数として指定された任意の例外がスローされます。
     * <code>
     * Preconditions.requireRange(10, 9, new AnyRuntimeException());
     * &gt;&gt; AnyRuntimeException
     * </code>
     * </pre>
     *
     * <pre>
     * 引数として渡された index が to で指定された数値以下の場合は何もせず当該検証処理を終了します。
     * <code>
     * Preconditions.requireRange(9, 10, new AnyRuntimeException());
     * </code>
     * </pre>
     *
     * @param index     検査対象のインデックス
     * @param to        判定時の上限値
     * @param exception 前提条件を満たさなかった場合にスローされる任意の例外オブジェクト
     *
     * @exception NullPointerException      引数として渡された例外オブジェクトが {@code null} の場合
     * @exception IndexOutOfBoundsException {@link #requireRange(int, int)}
     *                                      メソッドから実行され、引数として指定された {@code index} の数値が
     *                                      {@code to} で指定された範囲内に存在しない場合
     */
    static void requireRange(int index, int to, RuntimeException exception) {
        requireNonNull(exception);

        if (to < index) {
            throw exception;
        }
    }

    /**
     * 引数として指定された {@code index} が {@code from} から {@code to}
     * で指定された範囲内の数値であることを保証します。 
     * <p>
     * 引数として指定された {@code index} が範囲外にある数値である場合は {@link IndexOutOfBoundsException}
     * が必ず実行時に発生します。
     * <p>
     * 任意の例外オブジェクトを指定する場合は {@link #requireRange(int, int, int, RuntimeException)}
     * メソッドを使用してください。
     *
     * <pre>
     * 引数として渡された index が from と to で指定された範囲内ではない場合は IndexOutOfBoundsException がスローされます。
     * <code>
     * Preconditions.requireRange(10, 0, 9);
     * &gt;&gt; IndexOutOfBoundsException
     * </code>
     * </pre>
     *
     * <pre>
     * 引数として渡された index が from と to で指定された範囲内の場合は何もせず当該検証処理を終了します。
     * <code>
     * Preconditions.requireRange(9, 0, 10);
     * </code>
     * </pre>
     *
     * @param index 検査対象のインデックス
     * @param from  判定時の最低値
     * @param to    判定時の上限値
     *
     * @throws IndexOutOfBoundsException 引数として指定された {@code index} の数値が {@code from}
     *                                   から {@code to} で指定された範囲内に存在しない場合
     */
    static void requireRange(int index, int from, int to) {
        requireRange(index, from, to, new IndexOutOfBoundsException(
                String.format("Index %s out-of-bounds for range from length %s to length %s", index, from, to)));
    }

    /**
     * 引数として指定された {@code index} が {@code from} から {@code to}
     * で指定された範囲内の数値であることを保証します。 
     * <p>
     * 引数として指定された {@code index} が範囲外にある数値である場合は {@link IndexOutOfBoundsException}
     * が必ず実行時に発生します。引数として渡された {@code message} が詳細メッセージとして例外発生時に出力されます。
     * <p>
     * 任意の例外オブジェクトを指定する場合は {@link #requireRange(int, int, int, RuntimeException)}
     * メソッドを使用してください。
     *
     * <pre>
     * 引数として渡された index が from と to で指定された範囲内ではない場合は IndexOutOfBoundsException がスローされます。
     * 引数として渡された message が例外発生時に詳細メッセージとして出力されます。
     * <code>
     * Preconditions.requireRange(10, 0, 9, "any message");
     * &gt;&gt; IndexOutOfBoundsException
     * </code>
     * </pre>
     *
     * <pre>
     * 引数として渡された index が from と to で指定された範囲内の場合は何もせず当該検証処理を終了します。
     * <code>
     * Preconditions.requireRange(9, 0, 10, "any message");
     * </code>
     * </pre>
     *
     * @param index   検査対象のインデックス
     * @param from    判定時の最低値
     * @param to      判定時の上限値
     * @param message 例外スロー時に出力される詳細メッセージ
     *
     * @throws IndexOutOfBoundsException 引数として指定された {@code index} の数値が {@code from}
     *                                   から {@code to} で指定された範囲内に存在しない場合
     */
    static void requireRange(int index, int from, int to, String message) {
        requireRange(index, from, to, new IndexOutOfBoundsException(message));
    }

    /**
     * 引数として指定された {@code index} が {@code from} から {@code to}
     * で指定された範囲内の数値であることを保証します。 
     * <p>
     * 引数として指定された {@code index} が範囲外にある数値である場合は引数として指定された任意の例外オブジェクトをスローします。
     * {@link #requireRange(int, int, int)} メソッドから実行され、引数として指定された {@code index} が
     * {@code from} から {@code to} で指定された範囲内の数値ではない場合は
     * {@link IndexOutOfBoundsException} を例外オブジェクトとしてスローします。
     * <p>
     * 任意の例外オブジェクトを指定しない場合は {@link #requireRange(int, int, int)} メソッドを使用してください。
     *
     * <pre>
     * 引数として渡された index が from と to で指定された範囲内ではない場合は引数として渡された任意の例外オブジェクトがスローされます。
     * <code>
     * Preconditions.requireRange(10, 0, 9, new AnyRuntimeException());
     * &gt;&gt; AnyRuntimeException
     * </code>
     * </pre>
     *
     * <pre>
     * 引数として渡された index が from と to で指定された範囲内の場合は何もせず当該検証処理を終了します。
     * <code>
     * Preconditions.requireRange(9, 0, 10, new AnyRuntimeException());
     * </code>
     * </pre>
     *
     * @param index     検査対象のインデックス
     * @param from      判定時の最低値
     * @param to        判定時の上限値
     * @param exception 前提条件を満たさなかった場合にスローされる任意の例外オブジェクト
     *
     * @exception NullPointerException      引数として渡された例外オブジェクトが {@code null} の場合
     * @exception IndexOutOfBoundsException 引数として指定された {@code index} の数値が
     *                                      {@code from} から {@code to}
     *                                      で指定された範囲内に存在しない場合
     */
    static void requireRange(int index, int from, int to, RuntimeException exception) {
        requireNonNull(exception);

        if (index < from || to < index) {
            throw exception;
        }
    }

    /**
     * 引数として渡された {@code list} が {@code null} または空リストではないことを保証します。 
     * <p>
     * 任意の例外オブジェクトを指定する場合は {@link #requireNonEmpty(List, RuntimeException)}
     * メソッドを使用してください。
     *
     * <pre>
     * 引数として渡された list が null の場合は NullPointerException がスローされます。
     * <code>
     * Preconditions.requireNonEmpty(null);
     * &gt;&gt; NullPointerException
     * </code>
     * </pre>
     *
     * <pre>
     * 引数として渡された list が空リストの場合は IllegalArrayFoundException がスローされます。
     * <code>
     * Preconditions.requireNonEmpty(List.of());
     * &gt;&gt; IllegalArrayFoundException
     * </code>
     * </pre>
     *
     * <pre>
     * 引数として渡された list が null ではなく、かつ空リストではない場合は何もせず当該検証処理を終了します。
     * <code>
     * Preconditions.requireNonEmpty(List.of("test"));
     * </code>
     * </pre>
     *
     * @param list 検査対象のリスト
     *
     * @exception NullPointerException       引数として渡された {@code list} が {@code null}
     *                                       が渡された場合
     * @exception IllegalArrayFoundException 引数として渡された {@code list} が空リストの場合
     */
    static void requireNonEmpty(List<?> list) {
        requireNonEmpty(list, new IllegalArrayFoundException("List must contain at least one or more elements"));
    }

    /**
     * 引数として渡された {@code list} が {@code null} または空リストではないことを保証します。 
     * <p>
     * 任意の例外オブジェクトを指定する場合は {@link #requireNonEmpty(List, RuntimeException)}
     * メソッドを使用してください。引数として渡された {@code message} が詳細メッセージとして例外発生時に出力されます。
     *
     * <pre>
     * 引数として渡された list が null の場合は NullPointerException がスローされます。
     * 引数として渡された message が例外発生時に詳細メッセージとして出力されます。
     * <code>
     * Preconditions.requireNonEmpty(null, "any message");
     * &gt;&gt; NullPointerException
     * </code>
     * </pre>
     *
     * <pre>
     * 引数として渡された list が空リストの場合は IllegalArrayFoundException がスローされます。
     * 引数として渡された message が例外発生時に詳細メッセージとして出力されます。
     * <code>
     * Preconditions.requireNonEmpty(List.of(), "any message");
     * &gt;&gt; IllegalArrayFoundException
     * </code>
     * </pre>
     *
     * <pre>
     * 引数として渡された list が null ではなく、かつ空リストではない場合は何もせず当該検証処理を終了します。
     * <code>
     * Preconditions.requireNonEmpty(List.of("test"), "any message");
     * </code>
     * </pre>
     *
     * @param list    検査対象のリスト
     * @param message 例外スロー時に出力される詳細メッセージ
     *
     * @exception NullPointerException       引数として渡された {@code list} が {@code null}
     *                                       が渡された場合
     * @exception IllegalArrayFoundException 引数として渡された {@code list} が空リストの場合
     */
    static void requireNonEmpty(List<?> list, String message) {
        requireNonEmpty(list, new IllegalArrayFoundException(message));
    }

    /**
     * 引数として指定された {@code list} が {@code null} または空リストではないことを保証します。 
     * <p>
     * 引数として渡された {@code list} が空リストの場合は引数として渡された任意の例外オブジェクトをスローします。
     * {@link #requireNonEmpty(List)} メソッドから実行され 、引数として渡された {@code list} が空リストの場合は
     * {@link IllegalArrayFoundException} を例外オブジェクトとしてスローします。
     *
     * <pre>
     * 引数として渡された list が null の場合は NullPointerException がスローされます。
     * <code>
     * Preconditions.requireNonEmpty(null, new AnyRuntimeException());
     * &gt;&gt; NullPointerException
     * </code>
     * </pre>
     *
     * <pre>
     * 引数として渡された list が空リストの場合は引数として渡された任意の例外オブジェクトがスローされます。
     * <code>
     * Preconditions.requireNonEmpty(List.of(), new AnyRuntimeException());
     * &gt;&gt; AnyRuntimeException
     * </code>
     * </pre>
     *
     * <pre>
     * 引数として渡された list が null ではなく、かつ空リストではない場合は何もせず当該検証処理を終了します。
     * <code>
     * Preconditions.requireNonEmpty(List.of("test"), new AnyRuntimeException());
     * </code>
     * </pre>
     *
     * @param list      検査対象のリスト
     * @param exception 前提条件を満たさなかった場合にスローされる任意の例外オブジェクト
     *
     * @exception NullPointerException       引数として渡された {@code list} が {@code null}
     *                                       の場合、または引数として渡された例外オブジェクトが {@code null}
     *                                       の場合
     * @exception IllegalArrayFoundException {@link #requireNonEmpty(List)}
     *                                       メソッドから実行され、引数として渡された {@code list}
     *                                       が空リストの場合
     */
    static void requireNonEmpty(List<?> list, RuntimeException exception) {
        requireNonNull(list);
        requireNonNull(exception);

        if (list.isEmpty()) {
            throw exception;
        }
    }

    /**
     * 引数として渡された {@code map} が {@code null} または空マップではないことを保証します。 
     * <p>
     * 任意の例外オブジェクトを指定する場合は {@link #requireNonEmpty(Map, RuntimeException)}
     * メソッドを使用してください。
     *
     * <pre>
     * 引数として渡された map が null の場合は NullPointerException がスローされます。
     * <code>
     * Preconditions.requireNonEmpty(null);
     * &gt;&gt; NullPointerException
     * </code>
     * </pre>
     *
     * <pre>
     * 引数として渡された map が空マップの場合は IllegalMapFoundException がスローされます。
     * <code>
     * Preconditions.requireNonEmpty(Map.of());
     * &gt;&gt; IllegalMapFoundException
     * </code>
     * </pre>
     *
     * <pre>
     * 引数として渡された map が null ではなく、かつ空マップではない場合は何もせず当該検証処理を終了します。
     * <code>
     * Preconditions.requireNonEmpty(Map.of("test", "test"));
     * </code>
     * </pre>
     *
     * @param map 検査対象のマップ
     *
     * @throws IllegalMapFoundException 引数として渡された {@code list} に要素が含まれていない場合
     */
    static void requireNonEmpty(Map<?, ?> map) {
        requireNonEmpty(map, new IllegalMapFoundException("Map must contain at least one or more elements"));
    }

    /**
     * 引数として渡された {@code map} が {@code null} または空マップではないことを保証します。 
     * <p>
     * 任意の例外オブジェクトを指定する場合は {@link #requireNonEmpty(Map, RuntimeException)}
     * メソッドを使用してください。引数として渡された {@code message} が詳細メッセージとして例外発生時に出力されます。
     *
     * <pre>
     * 引数として渡された map が null の場合は NullPointerException がスローされます。
     * 引数として渡された message が例外発生時に詳細メッセージとして出力されます。
     * <code>
     * Preconditions.requireNonEmpty(null, "any message");
     * &gt;&gt; NullPointerException
     * </code>
     * </pre>
     *
     * <pre>
     * 引数として渡された map が空マップの場合は IllegalMapFoundException がスローされます。
     * 引数として渡された message が例外発生時に詳細メッセージとして出力されます。
     * <code>
     * Preconditions.requireNonEmpty(Map.of(), "any message");
     * &gt;&gt; IllegalMapFoundException
     * </code>
     * </pre>
     *
     * <pre>
     * 引数として渡された map が null ではなく、かつ空マップではない場合は何もせず当該検証処理を終了します。
     * <code>
     * Preconditions.requireNonEmpty(Map.of("test", "test"), "any message");
     * </code>
     * </pre>
     *
     * @param map     検査対象のマップ
     * @param message 例外スロー時に出力される詳細メッセージ
     *
     * @throws IllegalMapFoundException 引数として渡された {@code list} に要素が含まれていない場合
     */
    static void requireNonEmpty(Map<?, ?> map, String message) {
        requireNonEmpty(map, new IllegalMapFoundException(message));
    }

    /**
     * 引数として渡された {@code map} が {@code null} または空マップではないことを保証します。 
     * <p>
     * 引数として指定された {@code map} が空マップの場合は引数として渡された任意の例外オブジェクトを返却します。
     * {@link #requireNonEmpty(Map)} メソッドから実行されて、引数として渡された {@code map} が空マップの場合は
     * {@link IllegalMapFoundException} を例外オブジェクトとしてスローします。
     * <p>
     * 任意の例外オブジェクトを指定しない場合は {@link #requireNonEmpty(Map)} メソッドを使用してください。
     *
     * <pre>
     * 引数として渡された map が null の場合は NullPointerException がスローされます。
     * <code>
     * Preconditions.requireNonEmpty(null, new AnyRuntimeException());
     * &gt;&gt; NullPointerException
     * </code>
     * </pre>
     *
     * <pre>
     * 引数として渡された map が空マップの場合は引数として渡された任意の例外オブジェクトがスローされます。
     * <code>
     * Preconditions.requireNonEmpty(Map.of(), new AnyRuntimeException());
     * &gt;&gt; AnyRuntimeException
     * </code>
     * </pre>
     *
     * <pre>
     * 引数として渡された map が null ではなく、かつ空マップではない場合は何もせず当該検証処理を終了します。
     * <code>
     * Preconditions.requireNonEmpty(Map.of("test", "test"), new AnyRuntimeException());
     * </code>
     * </pre>
     *
     * @param map       検査対象のマップ
     * @param exception 前提条件を満たさなかった場合にスローされる任意の例外オブジェクト
     *
     * @exception NullPointerException     引数として渡された {@code map} が {@code ull}
     *                                     の場合、または引数として渡された任意の例外オブジェクトが {@code null}
     *                                     の場合
     * @exception IllegalMapFoundException {@link #requireNonEmpty(Map)}
     *                                     メソッドから実行されて、引数として渡された {@code map}
     *                                     が空マップの場合
     */
    static void requireNonEmpty(Map<?, ?> map, RuntimeException exception) {
        requireNonNull(map);
        requireNonNull(exception);

        if (map.isEmpty()) {
            throw exception;
        }
    }

    /**
     * 引数として渡された配列が {@code null} または空配列ではないことを保証します。 
     * <p>
     * 引数として渡された配列が {@code null} または空配列の場合は {@link IllegalArrayFoundException}
     * を例外オブジェクトとしてスローします。
     * <p>
     * 任意の例外オブジェクトを指定する場合は {@link #requireNonEmpty(Object[], RuntimeException)}
     * メソッドを使用してください。
     *
     * <pre>
     * 引数として渡された array が null の場合は NullPointerException がスローされます。
     * <code>
     * Preconditions.requireNonEmpty(null);
     * &gt;&gt; NullPointerException
     * </code>
     * </pre>
     *
     * <pre>
     * 引数として渡された array が空配列の場合は IllegalArrayFoundException がスローされます。
     * <code>
     * Preconditions.requireNonEmpty(new String[] {});
     * &gt;&gt; IllegalArrayFoundException
     * </code>
     * </pre>
     *
     * <pre>
     * 引数として渡された array が null ではなく、かつ空配列ではない場合は何もせず当該検証処理を終了します。
     * <code>
     * Preconditions.requireNonEmpty(new String[] { "" });
     * </code>
     * </pre>
     *
     * @param array 検査対象の配列
     *
     * @exception NullPointerException       引数として渡された配列が {@code null} の場合
     * @exception IllegalArrayFoundException 引数として渡された配列が空配列の場合
     */
    static void requireNonEmpty(Object[] array) {
        requireNonEmpty(Arrays.asList(array));
    }

    /**
     * 引数として渡された配列が {@code null} または空配列ではないことを保証します。 
     * <p>
     * 引数として渡された配列が {@code null} または空配列の場合は {@link IllegalArrayFoundException}
     * を例外オブジェクトとしてスローします。引数として渡された {@code message} が詳細メッセージとして例外発生時に出力されます。
     * <p>
     * 任意の例外オブジェクトを指定する場合は {@link #requireNonEmpty(Object[], RuntimeException)}
     * メソッドを使用してください。
     *
     * <pre>
     * 引数として渡された array が null の場合は NullPointerException がスローされます。
     * 引数として渡された message が例外発生時に詳細メッセージとして出力されます。
     * <code>
     * Preconditions.requireNonEmpty(null, "any message");
     * &gt;&gt; NullPointerException
     * </code>
     * </pre>
     *
     * <pre>
     * 引数として渡された array が空配列の場合は IllegalArrayFoundException がスローされます。
     * 引数として渡された message が例外発生時に詳細メッセージとして出力されます。
     * <code>
     * Preconditions.requireNonEmpty(new String[] {}, "any message");
     * &gt;&gt; IllegalArrayFoundException
     * </code>
     * </pre>
     *
     * <pre>
     * 引数として渡された array が null ではなく、かつ空配列ではない場合は何もせず当該検証処理を終了します。
     * <code>
     * Preconditions.requireNonEmpty(new String[] { "" }, "any message");
     * </code>
     * </pre>
     *
     * @param array   検査対象の配列
     * @param message 例外スロー時に出力される詳細メッセージ
     *
     * @exception NullPointerException       引数として渡された配列が {@code null} の場合
     * @exception IllegalArrayFoundException 引数として渡された配列が空配列の場合
     */
    static void requireNonEmpty(Object[] array, String message) {
        requireNonEmpty(Arrays.asList(array), message);
    }

    /**
     * 引数として渡された配列が {@code null} または空配列ではないことを保証します。 
     * <p>
     * 引数として渡された配列が空配列の場合は引数として渡された任意の例外オブジェクトをスローします。
     * {@link #requireNonEmpty(Object[])} メソッドから実行されて、引数として渡された配列が空配列の場合は
     * {@link IllegalArrayFoundException} を例外オブジェクトとしてスローします。
     *
     * <pre>
     * 引数として渡された array が null の場合は NullPointerException がスローされます。
     * <code>
     * Preconditions.requireNonEmpty(null, new AnyRuntimeException());
     * &gt;&gt; NullPointerException
     * </code>
     * </pre>
     *
     * <pre>
     * 引数として渡された array が空配列の場合は引数として渡された任意の例外オブジェクトがスローされます。
     * <code>
     * Preconditions.requireNonEmpty(new String[] {}, new AnyRuntimeException());
     * &gt;&gt; AnyRuntimeException
     * </code>
     * </pre>
     *
     * <pre>
     * 引数として渡された array が null ではなく、かつ空配列ではない場合は何もせず当該検証処理を終了します。
     * <code>
     * Preconditions.requireNonEmpty(new String[] { "" }, new AnyRuntimeException());
     * </code>
     * </pre>
     *
     * @param array     検査対象の配列
     * @param exception 前提条件を満たしていなかった場合にスローされる任意の例外オブジェクト
     *
     * @exception NullPointerException       引数として渡された配列が {@code null}
     *                                       の場合、または引数として渡された例外オブジェクトが {@code null}
     *                                       の場合
     * @exception IllegalArrayFoundException {@link #requireNonEmpty(Object[])}
     *                                       メソッドから実行されて、引数として渡された配列が空配列の場合
     */
    static void requireNonEmpty(Object[] array, RuntimeException exception) {
        requireNonEmpty(Arrays.asList(array), exception);
    }

    /**
     * 引数として指定された文字列が {@code prefix} で指定された接頭語で始まることを保証します。
     * <p>
     * 引数として指定された文字列が {@code prefix} で指定された接頭語で始まらない場合は、実行時に
     * {@link IllegalSequenceFoundException} が例外オブジェクトとしてスローされます。
     * <p>
     * 任意の例外オブジェクトを指定する場合は
     * {@link #requireStartWith(String, String, RuntimeException)} メソッドを使用してください。
     *
     * <pre>
     * 引数として指定された sequence が prefix で指定された接頭語で始まらない場合は IllegalSequenceFoundException がスローされます。
     * <code>
     * Preconditions.requireStartWith("test", "est");
     * &gt;&gt; IllegalSequenceFoundException
     * </code>
     * </pre>
     *
     * <pre>
     * 引数として指定された sequence が prefix で指定された接頭語で始まる場合は何もせず当該検証処理を終了します。
     * <code>
     * Preconditions.requireStartWith("test", "test");
     * </code>
     * </pre>
     *
     * @param sequence 検査対象の文字列
     * @param prefix   接頭語
     *
     * @exception IllegalSequenceFoundException 引数として渡された文字列が {@code prefix}
     *                                          で指定された接頭語で始まらない場合
     */
    static void requireStartWith(String sequence, String prefix) {
        requireStartWith(sequence, prefix, new IllegalSequenceFoundException(
                String.format("String must start with the %s prefix, but %s was given", prefix, sequence)));
    }

    /**
     * 引数として指定された文字列が {@code prefix} で指定された接頭語で始まることを保証します。
     * <p>
     * 引数として指定された文字列が {@code prefix} で指定された接頭語で始まらない場合は、実行時に
     * {@link IllegalSequenceFoundException} が例外オブジェクトとしてスローされます。引数として渡された
     * {@code message} が詳細メッセージとして例外発生時に出力されます。
     * <p>
     * 任意の例外オブジェクトを指定する場合は
     * {@link #requireStartWith(String, String, RuntimeException)} メソッドを使用してください。
     *
     * <pre>
     * 引数として指定された sequence が prefix で指定された接頭語で始まらない場合は IllegalSequenceFoundException がスローされます。
     * 引数として渡された message が例外発生時に詳細メッセージとして出力されます。
     * <code>
     * Preconditions.requireStartWith("test", "est", "any message");
     * &gt;&gt; IllegalSequenceFoundException
     * </code>
     * </pre>
     *
     * <pre>
     * 引数として指定された sequence が prefix で指定された接頭語で始まる場合は何もせず当該検証処理を終了します。
     * <code>
     * Preconditions.requireStartWith("test", "test", "any message");
     * </code>
     * </pre>
     *
     * @param sequence 検査対象の文字列
     * @param prefix   接頭語
     * @param message  例外スロー時に出力される詳細メッセージ
     *
     * @exception IllegalSequenceFoundException 引数として渡された文字列が {@code prefix}
     *                                          で指定された接頭語で始まらない場合
     */
    static void requireStartWith(String sequence, String prefix, String message) {
        requireStartWith(sequence, prefix, new IllegalSequenceFoundException(message));
    }

    /**
     * 引数として指定された文字列が指定された検索開始位置から {@code prefix} で指定された接頭語で始まることを保証します。
     * <p>
     * 引数として指定された文字列が {@code prefix} で指定された接頭語で始まらない場合は、実行時に
     * {@link IllegalSequenceFoundException} が例外オブジェクトとしてスローされます。
     * <p>
     * 任意の例外オブジェクトを指定する場合は
     * {@link #requireStartWith(String, String, int, RuntimeException)}
     * メソッドを使用してください。
     *
     * <pre>
     * 引数として指定された sequence が offset で指定された検索開始位置から prefix で指定された接頭語で始まらない場合は IllegalSequenceFoundException がスローされます。
     * <code>
     * Preconditions.requireStartWith("test", "st", 1);
     * &gt;&gt; IllegalSequenceFoundException
     * </code>
     * </pre>
     *
     * <pre>
     * 引数として指定された sequence が offset で指定された検索開始位置から prefix で指定された接頭語で始まる場合は何もせず当該検証処理を終了します。
     * <code>
     * Preconditions.requireStartWith("test", "est", 1);
     * </code>
     * </pre>
     *
     * @param sequence 検査対象の文字列
     * @param prefix   接頭語
     * @param offset   接頭語の検索開始位置
     *
     * @exception IllegalSequenceFoundException 引数として渡された文字列が指定された検索開始位置から
     *                                          {@code prefix} で指定された接頭語で始まらない場合
     */
    static void requireStartWith(String sequence, String prefix, int offset) {
        requireStartWith(sequence, prefix, offset, new IllegalSequenceFoundException(String.format(
                "String must start with the %s prefix from %s index, but %s was given", prefix, offset, sequence)));
    }

    /**
     * 引数として指定された文字列が指定された検索開始位置から {@code prefix} で指定された接頭語で始まることを保証します。
     * <p>
     * 引数として指定された文字列が {@code prefix} で指定された接頭語で始まらない場合は、実行時に
     * {@link IllegalSequenceFoundException} が例外オブジェクトとしてスローされます。引数として渡された
     * {@code message} が詳細メッセージとして例外発生時に出力されます。
     * <p>
     * 任意の例外オブジェクトを指定する場合は
     * {@link #requireStartWith(String, String, int, RuntimeException)}
     * メソッドを使用してください。
     *
     * <pre>
     * 引数として指定された sequence が offset で指定された検索開始位置から prefix で指定された接頭語で始まらない場合は IllegalSequenceFoundException がスローされます。
     * 引数として渡された message が例外発生時に詳細メッセージとして出力されます。
     * <code>
     * Preconditions.requireStartWith("test", "st", 1, "any message");
     * &gt;&gt; IllegalSequenceFoundException
     * </code>
     * </pre>
     *
     * <pre>
     * 引数として指定された sequence が offset で指定された検索開始位置から prefix で指定された接頭語で始まる場合は何もせず当該検証処理を終了します。
     * <code>
     * Preconditions.requireStartWith("test", "est", 1, "any message");
     * </code>
     * </pre>
     *
     * @param sequence 検査対象の文字列
     * @param prefix   接頭語
     * @param offset   接頭語の検索開始位置
     * @param message  例外スロー時に出力される詳細メッセージ
     *
     * @exception IllegalSequenceFoundException 引数として渡された文字列が指定された検索開始位置から
     *                                          {@code prefix} で指定された接頭語で始まらない場合
     */
    static void requireStartWith(String sequence, String prefix, int offset, String message) {
        requireStartWith(sequence, prefix, offset, new IllegalSequenceFoundException(message));
    }

    /**
     * 引数として指定された文字列が {@code prefix} で指定された接頭語で始まることを保証します。
     * <p>
     * 引数として指定された文字列が {@code prefix} で指定された接頭語で始まらない場合は、実行時に
     * 引数として指定された任意の例外オブジェクトがスローされます。
     * <p>
     * 任意の例外オブジェクトを指定しない場合は {@link #requireStartWith(String, String)} メソッドを使用してください。
     *
     * <pre>
     * 引数として指定された sequence が prefix で指定された接頭語で始まらない場合は引数として指定された任意の例外オブジェクトがスローされます。
     * <code>
     * Preconditions.requireStartWith("test", "est", new AnyRuntimeException());
     * &gt;&gt; AnyRuntimeException
     * </code>
     * </pre>
     *
     * <pre>
     * 引数として指定された sequence が prefix で指定された接頭語で始まる場合は何もせず当該検証処理を終了します。
     * <code>
     * Preconditions.requireStartWith("test", "test", new AnyRuntimeException());
     * </code>
     * </pre>
     *
     * @param sequence  検査対象の文字列
     * @param prefix    接頭語
     * @param exception 前提条件を満たしていなかった場合にスローされる任意の例外オブジェクト
     *
     * @exception NullPointerException 引数として指定された任意の例外オブジェクトが {@code null} の場合
     */
    static void requireStartWith(String sequence, String prefix, RuntimeException exception) {
        requireStartWith(sequence, prefix, 0, exception);
    }

    /**
     * 引数として指定された文字列が指定された検索開始位置から {@code prefix} で指定された接頭語で始まることを保証します。
     * <p>
     * 引数として指定された文字列が {@code prefix} で指定された接頭語で始まらない場合は、引数として渡された任意の
     * 例外オブジェクトがスローされます。
     * <p>
     * 任意の例外オブジェクトを指定しない場合は {@link #requireStartWith(String, String, int)}
     * メソッドを使用してください。
     *
     * <pre>
     * 引数として指定された sequence が offset で指定された検索開始位置から prefix で指定された接頭語で始まらない場合は引数として指定された任意の例外オブジェクトがスローされます。
     * <code>
     * Preconditions.requireStartWith("test", "st", 1, new AnyRuntimeException());
     * &gt;&gt; AnyRuntimeException
     * </code>
     * </pre>
     *
     * <pre>
     * 引数として指定された sequence が offset で指定された検索開始位置から prefix で指定された接頭語で始まる場合は何もせず当該検証処理を終了します。
     * <code>
     * Preconditions.requireStartWith("test", "est", 1, new AnyRuntimeException());
     * </code>
     * </pre>
     *
     * @param sequence  検査対象の文字列
     * @param prefix    接頭語
     * @param offset    接頭語の検索開始位置
     * @param exception 前提条件を満たしていなかった場合にスローされる任意の例外オブジェクト
     *
     * @exception NullPointerException 引数として渡された任意の例外オブジェクトが {@code null} の場合
     */
    static void requireStartWith(String sequence, String prefix, int offset, RuntimeException exception) {
        requireNonNull(exception);

        if (!sequence.startsWith(prefix, offset)) {
            throw exception;
        }
    }

    /**
     * 引数として指定された文字列が {@code suffix} で指定された接尾語で終わることを保証します。
     * <p>
     * 引数として指定された文字列が {@code suffix} で指定された接尾語で終わらない場合は、実行時に
     * {@link IllegalSequenceFoundException} が例外オブジェクトとしてスローされます。
     * <p>
     * 任意の例外オブジェクトを指定する場合は {@link #requireEndWith(String, String, RuntimeException)}
     * メソッドを使用してください。
     *
     * <pre>
     * 引数として指定された sequence が suffix で指定された接尾語で終わらない場合は IllegalSequenceFoundException がスローされます。
     * <code>
     * Preconditions.requireEndWith("test", "es");
     * &gt;&gt; IllegalSequenceFoundException
     * </code>
     * </pre>
     *
     * <pre>
     * 引数として指定された sequence が suffix で指定された接尾語で終わる場合は何もせず当該検証処理を終了します。
     * <code>
     * Preconditions.requireEndWith("test", "est");
     * </code>
     * </pre>
     *
     * @param sequence 検査対象の文字列
     * @param suffix   接尾語
     *
     * @exception IllegalSequenceFoundException 引数として渡された文字列が {@code suffix}
     *                                          で指定された接尾語で終わらない場合
     */
    static void requireEndWith(String sequence, String suffix) {
        requireEndWith(sequence, suffix, new IllegalSequenceFoundException(
                String.format("String must end with the %s suffix, but %s was given", suffix, sequence)));
    }

    /**
     * 引数として指定された文字列が {@code suffix} で指定された接尾語で終わることを保証します。
     * <p>
     * 引数として指定された文字列が {@code suffix} で指定された接尾語で終わらない場合は、実行時に
     * {@link IllegalSequenceFoundException} が例外オブジェクトとしてスローされます。引数として渡された
     * {@code message} が詳細メッセージとして例外発生時に出力されます。
     * <p>
     * 任意の例外オブジェクトを指定する場合は {@link #requireEndWith(String, String, RuntimeException)}
     * メソッドを使用してください。
     *
     * <pre>
     * 引数として指定された sequence が suffix で指定された接尾語で終わらない場合は IllegalSequenceFoundException がスローされます。
     * 引数として渡された message が例外発生時に詳細メッセージとして出力されます。
     * <code>
     * Preconditions.requireEndWith("test", "es", "any message");
     * &gt;&gt; IllegalSequenceFoundException
     * </code>
     * </pre>
     *
     * <pre>
     * 引数として指定された sequence が suffix で指定された接尾語で終わる場合は何もせず当該検証処理を終了します。
     * <code>
     * Preconditions.requireEndWith("test", "est", "any message");
     * </code>
     * </pre>
     *
     * @param sequence 検査対象の文字列
     * @param suffix   接尾語
     * @param message  例外スロー時に出力される詳細メッセージ
     *
     * @exception IllegalSequenceFoundException 引数として渡された文字列が {@code suffix}
     *                                          で指定された接尾語で終わらない場合
     */
    static void requireEndWith(String sequence, String suffix, String message) {
        requireEndWith(sequence, suffix, new IllegalSequenceFoundException(message));
    }

    /**
     * 引数として指定された文字列が {@code suffix} で指定された接尾語で終わることを保証します。
     * <p>
     * 引数として指定された文字列が {@code suffix} で指定された接尾語で終わらない場合は、実行時に
     * 引数として指定された任意の例外オブジェクトがスローされます。
     * <p>
     * 任意の例外オブジェクトを指定する場合は {@link #requireEndWith(String, String)} メソッドを使用してください。
     *
     * <pre>
     * 引数として指定された sequence が suffix で指定された接尾語で終わらない場合は引数として指定された任意の例外オブジェクトがスローされます。
     * <code>
     * Preconditions.requireEndWith("test", "es", new AnyRuntimeException());
     * &gt;&gt; AnyRuntimeException
     * </code>
     * </pre>
     *
     * <pre>
     * 引数として指定された sequence が suffix で指定された接尾語で終わる場合は何もせず当該検証処理を終了します。
     * <code>
     * Preconditions.requireEndWith("test", "est", new AnyRuntimeException());
     * </code>
     * </pre>
     *
     * @param sequence  検査対象の文字列
     * @param suffix    接尾語
     * @param exception 前提条件を満たしていなかった場合にスローされる任意の例外オブジェクト
     *
     * @exception NullPointerException 引数として指定された任意の例外オブジェクトが {@code null} の場合
     */
    static void requireEndWith(String sequence, String suffix, RuntimeException exception) {
        requireNonNull(exception);

        if (!sequence.endsWith(suffix)) {
            throw exception;
        }
    }
}