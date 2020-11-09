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
 * The {@link Preconditions} interface provides a way to ensure the
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
 * If the data passed as an argument satisfies the specified preconditions, the
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
 * &gt;&gt; IllegalStringFoundException
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
     * Ensures that the string of the {@code string} object given as an argument is
     * not an empty string. 
     * <p>
     * {@link IllegalStringFoundException} is always raised at runtime if the string
     * of the {@code string} object is an empty string.
     * <p>
     * Use the {@link #requireNonEmpty(String)} method if a reference to a
     * {@code string} object specified as an argument is likely to be {@code null} .
     * <p>
     * Use the {@link #requireNonBlank(String, String)} method if you want to print
     * out an arbitrary detailed message when an exception is thrown. Also, to throw
     * an arbitrary exception object when an exception is thrown, use the
     * {@link #requireNonBlank(String, RuntimeException)} method.
     *
     * <pre>
     * IllegalStringFoundException will be thrown if the argument passed to string is an empty string.
     * <code>
     * Preconditions.requireNonBlank("");
     * &gt;&gt; IllegalStringFoundException
     * </code>
     * </pre>
     *
     * <pre>
     * If the argument string is not an empty string, the function does nothing and ends the validation process.
     * <code>
     * Preconditions.requireNonBlank("test");
     * </code>
     * </pre>
     *
     * @param string The string to be validated
     *
     * @throws NullPointerException        If {@code null} is passed as an argument
     * @throws IllegalStringFoundException If an empty string is passed as an
     *                                     argument
     */
    static void requireNonBlank(String string) {
        requireNonBlank(string, new IllegalStringFoundException("String must not be blank"));
    }

    /**
     * Ensures that the string of the {@code string} object given as an argument is
     * not an empty string. 
     * <p>
     * If the string of a {@code string} object is an empty string,
     * {@link IllegalStringFoundException} will always be raised at runtime. The
     * {@code message} passed as an argument is output as a detailed message when an
     * exception occurs.
     * <p>
     * Use the {@link #requireNonEmpty(String)} method if a reference to a
     * {@code string} object specified as an argument is likely to be {@code null} .
     *
     * <pre>
     * IllegalStringFoundException will be thrown if the argument passed to string is an empty string.
     * A message passed as an argument will be printed as a detailed message when an exception occurs.
     * <code>
     * Preconditions.requireNonBlank("", "any message");
     * &gt;&gt; IllegalStringFoundException
     * </code>
     * </pre>
     *
     * <pre>
     * If the argument string is not an empty string, the function does nothing and ends the validation process.
     * <code>
     * Preconditions.requireNonBlank("test", "any message");
     * </code>
     * </pre>
     *
     * @param string  The string to be validated
     * @param message Detailed messages to be output on exception throwing
     *
     * @throws NullPointerException        If {@code null} is passed as an argument
     * @throws IllegalStringFoundException If an empty string is passed as an
     *                                     argument
     */
    static void requireNonBlank(String string, String message) {
        requireNonBlank(string, new IllegalStringFoundException(message));
    }

    /**
     * Ensures that {@code string} given as an argument is not an empty string. 
     * <p>
     * Throws an {@code exception} object if the argument {@code string} is an empty
     * string. If you do not specify any exceptions, use the
     * {@link #requireNonBlank(String)} method.
     *
     * <pre>
     * If the argument string is an empty string, any exception object specified as an argument will be thrown.
     * <code>
     * Preconditions.requireNonBlank("", new AnyRuntimeException());
     * &gt;&gt; AnyRuntimeException
     * </code>
     * </pre>
     *
     * <pre>
     * If the argument string is not an empty string, the function does nothing and ends the validation process.
     * <code>
     * Preconditions.requireNonBlank("test", new AnyRuntimeException());
     * </code>
     * </pre>
     *
     * @param string    The string to be validated
     * @param exception Any exception object that is thrown if the preconditions are
     *                  not met
     *
     * @exception NullPointerException        If the exception object passed as an
     *                                        argument is {@code null}
     * @exception IllegalStringFoundException If executed by the
     *                                        {@link #requireNonBlank(String)}
     *                                        method and the {@code string} passed
     *                                        as an argument is an empty string
     */
    static void requireNonBlank(String string, RuntimeException exception) {
        requireNonNull(exception);

        if (string.isEmpty()) {
            throw exception;
        }
    }

    /**
     * Ensures that the reference to the {@code string} object specified as an
     * argument is not {@code null} or the string is not an empty string. 
     * <p>
     * {@link NullPointerException} is always raised at runtime if the reference to
     * a {@code string} object is {@code null} . And
     * {@link IllegalStringFoundException} is always raised at runtime if the string
     * of the {@code string} object is an empty string.
     * <p>
     * To specify an arbitrary exception object, use the
     * {@link #requireNonEmpty(String, RuntimeException)} method.
     *
     * <pre>
     * NullPointerException will be thrown if the argument passed to string is null.
     * <code>
     * Preconditions.requireNonEmpty(null);
     * &gt;&gt; NullPointerException
     * </code>
     * </pre>
     *
     * <pre>
     * IllegalStringFoundException will be thrown if the argument passed to string is an empty string.
     * <code>
     * Preconditions.requireNonEmpty("");
     * &gt;&gt; IllegalStringFoundException
     * </code>
     * </pre>
     *
     * <pre>
     * If the argument string is not null and is not an empty string, it does nothing and ends the validation process.
     * <code>
     * Preconditions.requireNonEmpty("test");
     * </code>
     * </pre>
     *
     * @param string The string to be validated
     *
     * @throws NullPointerException        If {@code null} is passed as an argument
     * @throws IllegalStringFoundException If an empty string is passed as an
     *                                     argument
     */
    static void requireNonEmpty(String string) {
        requireNonNull(string);
        requireNonBlank(string);
    }

    /**
     * Ensures that the reference to the {@code string} object specified as an
     * argument is not {@code null} or the string is not an empty string. 
     * <p>
     * If the {@code string} object is referenced by {@code null} , then
     * {@link NullPointerException} is always raised at runtime. The {@code message}
     * passed as an argument is output as a detailed message when an exception
     * occurs.
     * <p>
     * If the string of a {@code string} object is an empty string,
     * {@link IllegalStringFoundException} is always raised at runtime. The
     * {@code message} passed as an argument is output as a detailed message when an
     * exception occurs.
     * <p>
     * To specify an arbitrary exception object, use the
     * {@link #requireNonEmpty(String, RuntimeException)} method.
     *
     * <pre>
     * If the string argument is null, NullPointerException will be thrown.
     * A message passed as an argument will be printed as a detailed message if an exception is raised.
     * <code>
     * Preconditions.requireNonEmpty(null, "any message");
     * &gt;&gt; NullPointerException
     * </code>
     * </pre>
     *
     * <pre>
     * IllegalStringFoundException will be thrown if the argument passed to string is an empty string.
     * A message passed as an argument will be printed as a detailed message when an exception occurs.
     * <code>
     * Preconditions.requireNonEmpty("", "any message");
     * &gt;&gt; IllegalStringFoundException
     * </code>
     * </pre>
     *
     * <pre>
     * If the argument string is not null and is not an empty string, it does nothing and ends the validation process.
     * <code>
     * Preconditions.requireNonEmpty("test", "any message");
     * </code>
     * </pre>
     *
     * @param string  The string to be validated
     * @param message Detailed messages to be output on exception throwing
     *
     * @throws NullPointerException        If {@code null} is passed as an argument
     * @throws IllegalStringFoundException If an empty string is passed as an
     *                                     argument
     */
    static void requireNonEmpty(String string, String message) {
        requireNonNull(string, message);
        requireNonBlank(string, message);
    }

    /**
     * Ensures that the value of {@code string} passed as an argument is not
     * {@code null} or an empty string. 
     * <p>
     * Throws an exception if it is {@code null} or an empty string. If you do not
     * specify an arbitrary exception object, use the
     * {@link #requireNonEmpty(String)} method.
     *
     * <pre>
     * NullPointerException will be thrown if the argument passed to string is null.
     * <code>
     * Preconditions.requireNonEmpty(null, new AnyRuntimeException());
     * &gt;&gt; NullPointerException
     * </code>
     * </pre>
     *
     * <pre>
     * If the argument string is an empty string, any exception object specified as an argument will be thrown.
     * <code>
     * Preconditions.requireNonEmpty("", new AnyRuntimeException());
     * &gt;&gt; AnyRuntimeException
     * </code>
     * </pre>
     *
     * <pre>
     * If the argument string is not null and is not an empty string, it does nothing and ends the validation process.
     * <code>
     * Preconditions.requireNonEmpty("test", new AnyRuntimeException());
     * </code>
     * </pre>
     *
     * @param string    The string to be validated
     * @param exception Any exception object that is thrown if the preconditions are
     *                  not met
     *
     * @exception NullPointerException        If the exception object passed as an
     *                                        argument is {@code null} or if the
     *                                        {@code string} passed as an argument
     *                                        is {@code null}
     * @exception IllegalStringFoundException If {@code string} passed as an
     *                                        argument is an empty string
     */
    static void requireNonEmpty(String string, RuntimeException exception) {
        requireNonNull(exception);
        requireNonNull(string);
        requireNonBlank(string, exception);
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
     * Ensures that the {@code number} argument is a positive number. 
     * <p>
     * If you do not specify an arbitrary exception object, use the
     * {@link #requirePositive(int)} method.
     *
     * <pre>
     * If the number passed as an argument is negative, then any exception object specified as an argument will be thrown.
     * <code>
     * Preconditions.requirePositive(-1, new AnyRuntimeException());
     * &gt;&gt; AnyRuntimeException
     * </code>
     * </pre>
     *
     * <pre>
     * If the number passed as an argument is a positive number, it does nothing and exits the validation process.
     * <code>
     * Preconditions.requirePositive(0, new AnyRuntimeException());
     * </code>
     * </pre>
     *
     * @param number    The number to be validated
     * @param exception Any exception object that is thrown if the preconditions are
     *                  not met
     *
     * @exception NullPointerException        If the exception object passed as an
     *                                        argument is {@code null}
     * @exception IllegalNumberFoundException If the specified {@code number} is
     *                                        negative
     */
    static void requirePositive(int number, RuntimeException exception) {
        requireNonNull(exception);

        if (number < 0) {
            throw exception;
        }
    }

    /**
     * Ensures that the argument {@code number} is a negative number. 
     * <p>
     * If the argument {@code number} is positive,
     * {@link IllegalNumberFoundException} is always raised at runtime.
     * <p>
     * To specify an arbitrary exception object, use the
     * {@link #requireNegative(int, RuntimeException)} method.
     *
     * <pre>
     * IllegalNumberFoundException will be thrown if the number passed as an argument is positive.
     * <code>
     * Preconditions.requireNegative(0);
     * &gt;&gt; IllegalNumberFoundException
     * </code>
     * </pre>
     *
     * <pre>
     * If number passed as an argument is a negative number, it does nothing and ends the validation process.
     * <code>
     * Preconditions.requireNegative(-1);
     * </code>
     * </pre>
     *
     * @param number The number to be validated
     *
     * @throws IllegalNumberFoundException If the number of {@code number} specified
     *                                     as an argument is a positive number
     */
    static void requireNegative(int number) {
        requireNegative(number,
                new IllegalNumberFoundException(String.format("Number must be negative but %s was given", number)));
    }

    /**
     * Ensures that the argument {@code number} is a negative number. 
     * <p>
     * If the argument {@code number} is a positive number,
     * {@link IllegalNumberFoundException} will always be raised at runtime. And the
     * {@code message} passed as an argument is output as a detailed message when an
     * exception occurs.
     * <p>
     * To specify an arbitrary exception object, use the
     * {@link #requireNegative(int, RuntimeException)} method.
     *
     * <pre>
     * If the number argument is positive, IllegalNumberFoundException will be thrown.
     * The message passed as an argument will be output as a detailed message when an exception occurs.
     * <code>
     * Preconditions.requireNegative(0, "any message");
     * &gt;&gt; IllegalNumberFoundException
     * </code>
     * </pre>
     *
     * <pre>
     * If number passed as an argument is a negative number, it does nothing and ends the validation process.
     * <code>
     * Preconditions.requireNegative(-1, "any message");
     * </code>
     * </pre>
     *
     * @param number  The number to be validated
     * @param message Detailed messages to be output on exception throwing
     *
     * @throws IllegalNumberFoundException If the number of {@code number} specified
     *                                     as an argument is a positive number
     */
    static void requireNegative(int number, String message) {
        requireNegative(number, new IllegalNumberFoundException(message));
    }

    /**
     * Ensures that the argument {@code number} is a negative number. 
     * <p>
     * If {@code number} is a positive number, throw any exception object specified
     * as an argument.
     * <p>
     * If you do not specify an arbitrary exception object, use the
     * {@link #requireNegative(int)} method.
     *
     * <pre>
     * If the number passed as an argument is positive, any exception object specified as an argument will be thrown.
     * <code>
     * Preconditions.requireNegative(0, new AnyRuntimeException());
     * &gt;&gt; AnyRuntimeException
     * </code>
     * </pre>
     *
     * <pre>
     * If number passed as an argument is a negative number, it does nothing and ends the validation process.
     * <code>
     * Preconditions.requireNegative(-1, new AnyRuntimeException());
     * </code>
     * </pre>
     *
     * @param number    The number to be validated
     * @param exception Any exception object that is thrown if the preconditions are
     *                  not met
     *
     * @exception NullPointerException        If the exception object passed as an
     *                                        argument is {@code null}
     * @exception IllegalNumberFoundException If the value of the {@code number}
     *                                        passed as an argument is a positive
     *                                        number
     */
    static void requireNegative(int number, RuntimeException exception) {
        requireNonNull(exception);

        if (number >= 0) {
            throw exception;
        }
    }

    /**
     * Ensures that the {@code index} argument is a number within the range
     * specified by {@code from} . 
     * <p>
     * If the {@code index} argument is an out-of-range number,
     * {@link IndexOutOfBoundsException} will always be raised at runtime.
     * <p>
     * To specify an arbitrary exception object, use the
     * {@link #requireRangeFrom(int, int, RuntimeException)} method.
     *
     * <pre>
     * If the index passed as an argument is less than the number specified by from, IndexOutOfBoundsException will be thrown.
     * <code>
     * Preconditions.requireRangeFrom(10, 9);
     * &gt;&gt; IndexOutOfBoundsException
     * </code>
     * </pre>
     *
     * <pre>
     * If the index passed as an argument is greater than or equal to the value specified by "from", this function does nothing and exits the validation process.
     * <code>
     * Preconditions.requireRangeFrom(9, 10);
     * </code>
     * </pre>
     *
     * @param index The index to be validated
     * @param from  The lower limit
     *
     * @throws IndexOutOfBoundsException If the number of the {@code index} argument
     *                                   does not exist within the range specified
     *                                   by {@code from}
     */
    static void requireRangeFrom(int index, int from) {
        requireRangeFrom(index, from, new IndexOutOfBoundsException(
                String.format("Index %s out-of-bounds for range from length %s", index, from)));
    }

    /**
     * Ensures that the {@code index} argument is a number within the range
     * specified by {@code from} . 
     * <p>
     * If the {@code index} argument is an out-of-range number,
     * {@link IndexOutOfBoundsException} will always be raised at runtime. And the
     * {@code message} passed as an argument is output as a detailed message when an
     * exception occurs.
     * <p>
     * To specify an arbitrary exception object, use the
     * {@link #requireRangeFrom(int, int, RuntimeException)} method.
     *
     * <pre>
     * If the index passed as an argument is less than the number specified by from, IndexOutOfBoundsException will be thrown.
     * A message passed as an argument is output as a detailed message when an exception occurs.
     * <code>
     * Preconditions.requireRangeFrom(10, 9, "any message");
     * &gt;&gt; IndexOutOfBoundsException
     * </code>
     * </pre>
     *
     * <pre>
     * If the index passed as an argument is greater than or equal to the value specified by "from", this function does nothing and exits the validation process.
     * <code>
     * Preconditions.requireRangeFrom(9, 10, "any message");
     * </code>
     * </pre>
     *
     * @param index   The index to be validated
     * @param from    The lower limit
     * @param message Detailed messages to be output on exception throwing
     *
     * @throws IndexOutOfBoundsException If the argument {@code index} does not fall
     *                                   within the range specified by {@code from}
     */
    static void requireRangeFrom(int index, int from, String message) {
        requireRangeFrom(index, from, new IndexOutOfBoundsException(message));
    }

    /**
     * Ensures that the {@code index} argument is a number within the range
     * specified by {@code from} . 
     * <p>
     * If {@code index} specified as an argument is not within the range specified
     * by {@code to} , throw any exception object specified as an argument.
     * <p>
     * If you do not specify an arbitrary exception object, use the
     * {@link #requireRangeFrom(int, int)} method.
     *
     * <pre>
     * If the index passed as an argument is less than the number specified by from, any of the specified exceptions will be thrown.
     * <code>
     * Preconditions.requireRangeFrom(10, 9, new AnyRuntimeException());
     * &gt;&gt; AnyRuntimeException
     * </code>
     * </pre>
     *
     * <pre>
     * If the index passed as an argument is greater than or equal to the value specified by "from", this function does nothing and exits the validation process.
     * <code>
     * Preconditions.requireRangeFrom(9, 10, new AnyRuntimeException());
     * </code>
     * </pre>
     *
     * @param index     The index to be validated
     * @param from      The lower limit
     * @param exception Any exception object that is thrown if the preconditions are
     *                  not met
     *
     * @exception NullPointerException      If the exception object passed as an
     *                                      argument is {@code null}
     * @exception IndexOutOfBoundsException If the number of the {@code index}
     *                                      specified as an argument does not fall
     *                                      within the range specified by
     *                                      {@code from}
     */
    static void requireRangeFrom(int index, int from, RuntimeException exception) {
        requireNonNull(exception);

        if (index < from) {
            throw exception;
        }
    }

    /**
     * Ensures that the {@code index} argument is a number within the range
     * specified by {@code to} . 
     * <p>
     * If the {@code index} argument is an out-of-range number,
     * {@link IndexOutOfBoundsException} will always be raised at runtime.
     * <p>
     * To specify an arbitrary exception object, use the
     * {@link #requireRangeTo(int, int, RuntimeException)} method.
     *
     * <pre>
     * If the index passed as an argument is greater than the number specified by to, IndexOutOfBoundsException will be thrown.
     * <code>
     * Preconditions.requireRangeTo(10, 9);
     * &gt;&gt; IndexOutOfBoundsException
     * </code>
     * </pre>
     *
     * <pre>
     * If the index passed as an argument is less than or equal to the value specified by "to", this function does nothing and exits the validation process.
     * <code>
     * Preconditions.requireRangeTo(9, 10);
     * </code>
     * </pre>
     *
     * @param index The index to be validated
     * @param to    The upper limit
     *
     * @throws IndexOutOfBoundsException If the number of the {@code index} argument
     *                                   does not exist within the range specified
     *                                   by {@code to}
     */
    static void requireRangeTo(int index, int to) {
        requireRangeTo(index, to, new IndexOutOfBoundsException(
                String.format("Index %s out-of-bounds for range from length 0 to length %s", index, to)));
    }

    /**
     * Ensures that the {@code index} argument is a number within the range
     * specified by {@code to} . 
     * <p>
     * If the {@code index} argument is an out-of-range number,
     * {@link IndexOutOfBoundsException} will always be raised at runtime. And the
     * {@code message} passed as an argument is output as a detailed message when an
     * exception occurs.
     * <p>
     * To specify an arbitrary exception object, use the
     * {@link #requireRangeTo(int, int, RuntimeException)} method.
     *
     * <pre>
     * If the index passed as an argument is greater than the number specified by to, IndexOutOfBoundsException will be thrown.
     * A message passed as an argument is output as a detailed message when an exception occurs.
     * <code>
     * Preconditions.requireRangeTo(10, 9, "any message");
     * &gt;&gt; IndexOutOfBoundsException
     * </code>
     * </pre>
     *
     * <pre>
     * If the index passed as an argument is less than or equal to the value specified by "to", this function does nothing and exits the validation process.
     * <code>
     * Preconditions.requireRangeTo(9, 10, "any message");
     * </code>
     * </pre>
     *
     * @param index   The index to be validated
     * @param to      The upper limit
     * @param message Detailed messages to be output on exception throwing
     *
     * @throws IndexOutOfBoundsException If the argument {@code index} does not fall
     *                                   within the range specified by {@code to}
     */
    static void requireRangeTo(int index, int to, String message) {
        requireRangeTo(index, to, new IndexOutOfBoundsException(message));
    }

    /**
     * Ensures that the {@code index} argument is a number within the range
     * specified by {@code to} . 
     * <p>
     * If {@code index} specified as an argument is not within the range specified
     * by {@code to} , throw any exception object specified as an argument.
     * <p>
     * If you do not specify an arbitrary exception object, use the
     * {@link #requireRangeTo(int, int)} method.
     *
     * <pre>
     * If the index passed as an argument is greater than the number specified by to, any of the specified exceptions will be thrown.
     * <code>
     * Preconditions.requireRangeTo(10, 9, new AnyRuntimeException());
     * &gt;&gt; AnyRuntimeException
     * </code>
     * </pre>
     *
     * <pre>
     * If the index passed as an argument is less than or equal to the value specified by "to", this function does nothing and exits the validation process.
     * <code>
     * Preconditions.requireRangeTo(9, 10, new AnyRuntimeException());
     * </code>
     * </pre>
     *
     * @param index     The index to be validated
     * @param to        The upper limit
     * @param exception Any exception object that is thrown if the preconditions are
     *                  not met
     *
     * @exception NullPointerException      If the exception object passed as an
     *                                      argument is {@code null}
     * @exception IndexOutOfBoundsException If the number of the {@code index}
     *                                      specified as an argument does not fall
     *                                      within the range specified by {@code to}
     */
    static void requireRangeTo(int index, int to, RuntimeException exception) {
        requireNonNull(exception);

        if (to < index) {
            throw exception;
        }
    }

    /**
     * Ensures that the {@code index} argument is within the range specified by
     * {@code from} to {@code to} . 
     * <p>
     * If the {@code index} argument is an out-of-range number,
     * {@link IndexOutOfBoundsException} is always raised at runtime.
     * <p>
     * To specify an arbitrary exception object, use the
     * {@link #requireRange(int, int, int, RuntimeException)} method.
     *
     * <pre>
     * IndexOutOfBoundsException will be thrown if the index passed as an argument is not within the range specified by from and to.
     * <code>
     * Preconditions.requireRange(10, 0, 9);
     * &gt;&gt; IndexOutOfBoundsException
     * </code>
     * </pre>
     *
     * <pre>
     * If the index passed as an argument is within the range of from and to, it does nothing and ends the validation process.
     * <code>
     * Preconditions.requireRange(9, 0, 10);
     * </code>
     * </pre>
     *
     * @param index The index to be validated
     * @param from  The lower limit
     * @param to    The upper limit
     *
     * @throws IndexOutOfBoundsException If the number of the {@code index} argument
     *                                   does not fall within the range specified by
     *                                   {@code from} to {@code to}
     */
    static void requireRange(int index, int from, int to) {
        requireRange(index, from, to, new IndexOutOfBoundsException(
                String.format("Index %s out-of-bounds for range from length %s to length %s", index, from, to)));
    }

    /**
     * Ensures that the {@code index} argument is within the range specified by
     * {@code from} to {@code to} . 
     * <p>
     * If the {@code index} argument is an out-of-range number,
     * {@link IndexOutOfBoundsException} will always be raised at runtime. The
     * {@code message} passed as an argument is output as a detailed message when an
     * exception occurs.
     * <p>
     * To specify an arbitrary exception object, use the
     * {@link #requireRange(int, int, int, RuntimeException)} method.
     *
     * <pre>
     * IndexOutOfBoundsException will be thrown if the index passed as an argument is not within the range specified by from and to.
     * A message passed as an argument is output as a detailed message when an exception occurs.
     * <code>
     * Preconditions.requireRange(10, 0, 9, "any message");
     * &gt;&gt; IndexOutOfBoundsException
     * </code>
     * </pre>
     *
     * <pre>
     * If the index passed as an argument is within the range of from and to, it does nothing and ends the validation process.
     * <code>
     * Preconditions.requireRange(9, 0, 10, "any message");
     * </code>
     * </pre>
     *
     * @param index   The index to be validated
     * @param from    The lower limit
     * @param to      The upper limit
     * @param message Detailed messages to be output on exception throwing
     *
     * @throws IndexOutOfBoundsException If the number of the {@code index} argument
     *                                   does not fall within the range specified by
     *                                   {@code from} to {@code to}
     */
    static void requireRange(int index, int from, int to, String message) {
        requireRange(index, from, to, new IndexOutOfBoundsException(message));
    }

    /**
     * Ensures that the {@code index} argument is within the range specified by
     * {@code from} to {@code to} . 
     * <p>
     * Throws an arbitrary exception object if the argument {@code index} is an
     * out-of-range number. Execute from the {@link #requireRange(int, int, int)}
     * method, and throw {@link IndexOutOfBoundsException} as an exception object if
     * the {@code index} argument is not a number within the range specified by
     * {@code from} to {@code to} .
     * <p>
     * If you do not specify an arbitrary exception object, use the
     * {@link #requireRange(int, int, int)} method.
     *
     * <pre>
     * If the index passed as an argument is not within the range specified by from and to, any exception object passed as an argument will be thrown.
     * <code>
     * Preconditions.requireRange(10, 0, 9, new AnyRuntimeException());
     * &gt;&gt; AnyRuntimeException
     * </code>
     * </pre>
     *
     * <pre>
     * If the index passed as an argument is within the range of from and to, it does nothing and ends the validation process.
     * <code>
     * Preconditions.requireRange(9, 0, 10, new AnyRuntimeException());
     * </code>
     * </pre>
     *
     * @param index     The index to be validated
     * @param from      The upper limit
     * @param to        The lower limit
     * @param exception Any exception object that is thrown if the preconditions are
     *                  not met
     *
     * @exception NullPointerException      If the exception object passed as an
     *                                      argument is {@code null}
     * @exception IndexOutOfBoundsException If the number of the {@code index}
     *                                      argument does not fall within the range
     *                                      specified by {@code from} to {@code to}
     */
    static void requireRange(int index, int from, int to, RuntimeException exception) {
        requireNonNull(exception);

        if (index < from || to < index) {
            throw exception;
        }
    }

    /**
     * Ensures that {@code list} passed as an argument is not {@code null} or an
     * empty list. 
     * <p>
     * To specify an arbitrary exception object, use the
     * {@link #requireNonEmpty(List, RuntimeException)} method.
     *
     * <pre>
     * NullPointerException will be thrown if the list passed as an argument is null.
     * <code>
     * Preconditions.requireNonEmpty(null);
     * &gt;&gt; NullPointerException
     * </code>
     * </pre>
     *
     * <pre>
     * IllegalListFoundException will be thrown if the list passed as an argument is an empty list.
     * <code>
     * Preconditions.requireNonEmpty(List.of());
     * &gt;&gt; IllegalListFoundException
     * </code>
     * </pre>
     *
     * <pre>
     * If the list passed as an argument is not null and is not an empty list, the validation process is terminated without doing anything.
     * <code>
     * Preconditions.requireNonEmpty(List.of("test"));
     * </code>
     * </pre>
     *
     * @param list The list to be validated
     *
     * @exception NullPointerException      If {@code list} is passed as an argument
     *                                      and {@code null} is passed
     * @exception IllegalListFoundException If {@code list} passed as an argument is
     *                                      an empty list
     */
    static void requireNonEmpty(List<?> list) {
        requireNonEmpty(list, new IllegalListFoundException("List must contain at least one or more elements"));
    }

    /**
     * Ensures that {@code list} passed as an argument is not {@code null} or an
     * empty list. 
     * <p>
     * To specify an arbitrary exception object, use
     * {@link #requireNonEmpty(List, RuntimeException)} method. The {@code message}
     * passed as an argument will be printed as a detailed message when an exception
     * is thrown.
     *
     * <pre>
     * If the list argument is null, NullPointerException will be thrown.
     * A message passed as an argument will be output as a detailed message when an exception occurs.
     * <code>
     * Preconditions.requireNonEmpty(null, "any message");
     * &gt;&gt; NullPointerException
     * </code>
     * </pre>
     *
     * <pre>
     * IllegalListFoundException is thrown if the list argument is an empty list.
     * The message argument is output as a detailed message when an exception occurs.
     * <code>
     * Preconditions.requireNonEmpty(List.of(), "any message");
     * &gt;&gt; IllegalListFoundException
     * </code>
     * </pre>
     *
     * <pre>
     * If the list passed as an argument is not null and is not an empty list, the validation process is terminated without doing anything.
     * <code>
     * Preconditions.requireNonEmpty(List.of("test"), "any message");
     * </code>
     * </pre>
     *
     * @param list    The list to be validated
     * @param message Detailed messages to be output on exception throwing
     *
     * @exception NullPointerException      If {@code list} is passed as an argument
     *                                      and {@code null} is passed
     * @exception IllegalListFoundException If {@code list} passed as an argument is
     *                                      an empty list
     */
    static void requireNonEmpty(List<?> list, String message) {
        requireNonEmpty(list, new IllegalListFoundException(message));
    }

    /**
     * Ensures that {@code list} given as an argument is not {@code null} or an
     * empty list. 
     * <p>
     * If {@code list} is an empty list, throw any exception object passed as an
     * argument. If it is executed by the {@link #requireNonEmpty(List)} method and
     * {@code list} is an empty list, it throws {@link IllegalListFoundException} as
     * an exception object.
     *
     * <pre>
     * NullPointerException will be thrown if the list passed as an argument is null.
     * <code>
     * Preconditions.requireNonEmpty(null, new AnyRuntimeException());
     * &gt;&gt; NullPointerException
     * </code>
     * </pre>
     *
     * <pre>
     * If the list passed as an argument is an empty list, any exception object passed as an argument is thrown.
     * <code>
     * Preconditions.requireNonEmpty(List.of(), new AnyRuntimeException());
     * &gt;&gt; AnyRuntimeException
     * </code>
     * </pre>
     *
     * <pre>
     * If the list passed as an argument is not null and is not an empty list, the validation process is terminated without doing anything.
     * <code>
     * Preconditions.requireNonEmpty(List.of("test"), new AnyRuntimeException());
     * </code>
     * </pre>
     *
     * @param list      The list to be validated
     * @param exception Any exception object that is thrown if the preconditions are
     *                  not met
     *
     * @exception NullPointerException      If {@code list} passed as an argument is
     *                                      {@code null} or if the exception object
     *                                      passed as an argument is {@code null}
     * @exception IllegalListFoundException If it is executed by the
     *                                      {@link #requireNonEmpty(List)} method
     *                                      and the {@code list} passed as an
     *                                      argument is an empty list
     */
    static void requireNonEmpty(List<?> list, RuntimeException exception) {
        requireNonNull(list);
        requireNonNull(exception);

        if (list.isEmpty()) {
            throw exception;
        }
    }

    /**
     * Ensures that {@code map} passed as an argument is not {@code null} or an
     * empty map. 
     * <p>
     * To specify an arbitrary exception object, use the
     * {@link #requireNonEmpty(Map, RuntimeException)} method.
     *
     * <pre>
     * If the map argument is null, NullPointerException will be thrown.
     * <code>
     * Preconditions.requireNonEmpty(null);
     * &gt;&gt; NullPointerException
     * </code>
     * </pre>
     *
     * <pre>
     * IllegalMapFoundException will be thrown if the map passed as an argument is an empty map.
     * <code>
     * Preconditions.requireNonEmpty(Map.of());
     * &gt;&gt; IllegalMapFoundException
     * </code>
     * </pre>
     *
     * <pre>
     * If the map passed as an argument is not null and is not an empty map, the validation process is ended without doing anything.
     * <code>
     * Preconditions.requireNonEmpty(Map.of("test", "test"));
     * </code>
     * </pre>
     *
     * @param map The map to be validated
     *
     * @throws IllegalMapFoundException If the {@code map} passed as an argument
     *                                  does not contain any elements
     */
    static void requireNonEmpty(Map<?, ?> map) {
        requireNonEmpty(map, new IllegalMapFoundException("Map must contain at least one or more elements"));
    }

    /**
     * Ensures that {@code map} passed as an argument is not {@code null} or an
     * empty map. 
     * <p>
     * To specify an arbitrary exception object, use the
     * {@link #requireNonEmpty(Map, RuntimeException)} method. The {@code message}
     * passed as an argument will be printed as a detailed message when an exception
     * is thrown.
     *
     * <pre>
     * If the map argument is null, NullPointerException will be thrown.
     * A message passed as an argument will be printed as a detailed message if an exception is raised.
     * <code>
     * Preconditions.requireNonEmpty(null, "any message");
     * &gt;&gt; NullPointerException
     * </code>
     * </pre>
     *
     * <pre>
     * If the map argument is an empty map, IllegalMapFoundException will be thrown.
     * The message passed as an argument is output as a detailed message when an exception occurs.
     * <code>
     * Preconditions.requireNonEmpty(Map.of(), "any message");
     * &gt;&gt; IllegalMapFoundException
     * </code>
     * </pre>
     *
     * <pre>
     * If the map passed as an argument is not null and is not an empty map, the validation process is ended without doing anything.
     * <code>
     * Preconditions.requireNonEmpty(Map.of("test", "test"), "any message");
     * </code>
     * </pre>
     *
     * @param map     The map to be validated
     * @param message Detailed messages to be output on exception throwing
     *
     * @throws IllegalMapFoundException If the {@code map} passed as an argument
     *                                  does not contain any elements
     */
    static void requireNonEmpty(Map<?, ?> map, String message) {
        requireNonEmpty(map, new IllegalMapFoundException(message));
    }

    /**
     * Ensures that {@code map} passed as an argument is not {@code null} or an
     * empty map. 
     * <p>
     * If {@code map} is an empty map, any exception object passed as an argument
     * will be returned. Executed by the {@link #requireNonEmpty(Map)} method, if
     * the {@code map} passed as argument is an empty map, throws
     * {@link IllegalMapFoundException} as an exception object.
     * <p>
     * If you do not specify an arbitrary exception object, use the
     * {@link #requireNonEmpty(Map)} method.
     *
     * <pre>
     * If the map argument is null, NullPointerException will be thrown.
     * <code>
     * Preconditions.requireNonEmpty(null, new AnyRuntimeException());
     * &gt;&gt; NullPointerException
     * </code>
     * </pre>
     *
     * <pre>
     * If the map argument is an empty map, any exception object passed as an argument will be thrown.
     * <code>
     * Preconditions.requireNonEmpty(Map.of(), new AnyRuntimeException());
     * &gt;&gt; AnyRuntimeException
     * </code>
     * </pre>
     *
     * <pre>
     * If the map passed as an argument is not null and is not an empty map, the validation process is ended without doing anything.
     * <code>
     * Preconditions.requireNonEmpty(Map.of("test", "test"), new AnyRuntimeException());
     * </code>
     * </pre>
     *
     * @param map       The map to be validated
     * @param exception Any exception object that is thrown if the preconditions are
     *                  not met
     *
     * @exception NullPointerException     If {@code map} passed as an argument is
     *                                     {@code null} , or if any exception object
     *                                     passed as an argument is {@code null}
     * @exception IllegalMapFoundException If the {@code map} argument is an empty
     *                                     map
     */
    static void requireNonEmpty(Map<?, ?> map, RuntimeException exception) {
        requireNonNull(map);
        requireNonNull(exception);

        if (map.isEmpty()) {
            throw exception;
        }
    }

    /**
     * Ensures that the array passed as an argument is not {@code null} or an empty
     * array. 
     * <p>
     * Throws {@link IllegalArrayFoundException} if the argument is either
     * {@code null} or an empty array.
     * <p>
     * To specify an arbitrary exception object, use the
     * {@link #requireNonEmpty(Object[], RuntimeException)} method.
     *
     * <pre>
     * NullPointerException will be thrown if the argument passed to array is null.
     * <code>
     * Preconditions.requireNonEmpty(null);
     * &gt;&gt; NullPointerException
     * </code>
     * </pre>
     *
     * <pre>
     * IllegalArrayFoundException will be thrown if the array argument is an empty array.
     * <code>
     * Preconditions.requireNonEmpty(new String[] {});
     * &gt;&gt; IllegalArrayFoundException
     * </code>
     * </pre>
     *
     * <pre>
     * If the array argument is non-null and is not an empty array, the function does nothing and exits the validation process.
     * <code>
     * Preconditions.requireNonEmpty(new String[] { "" });
     * </code>
     * </pre>
     *
     * @param array The array to be validated
     *
     * @exception NullPointerException       If the array passed as an argument is
     *                                       {@code null}
     * @exception IllegalArrayFoundException If the array passed as an argument is
     *                                       an empty array
     */
    static void requireNonEmpty(Object[] array) {
        requireNonEmpty(Arrays.asList(array),
                new IllegalArrayFoundException("Array must contain at least one or more elements"));
    }

    /**
     * Ensures that the array passed as an argument is not {@code null} or an empty
     * array. 
     * <p>
     * Throws {@link IllegalArrayFoundException} if the array argument is
     * {@code null} or an empty array. The {@code message} passed as an argument
     * will be printed as a detailed message when an exception occurs.
     * <p>
     * To specify an arbitrary exception object, use the
     * {@link #requireNonEmpty(Object[], RuntimeException)} method.
     *
     * <pre>
     * If the array argument is null, NullPointerException will be thrown.
     * A message passed as an argument will be printed as a detailed message when an exception is raised.
     * <code>
     * Preconditions.requireNonEmpty(null, "any message");
     * &gt;&gt; NullPointerException
     * </code>
     * </pre>
     *
     * <pre>
     * IllegalArrayFoundException is thrown if the array argument is an empty array.
     * The message argument is output as a detailed message when an exception is raised.
     * <code>
     * Preconditions.requireNonEmpty(new String[] {}, "any message");
     * &gt;&gt; IllegalArrayFoundException
     * </code>
     * </pre>
     *
     * <pre>
     * If the array argument is non-null and is not an empty array, the function does nothing and exits the validation process.
     * <code>
     * Preconditions.requireNonEmpty(new String[] { "" }, "any message");
     * </code>
     * </pre>
     *
     * @param array   The array to be validated
     * @param message Detailed messages to be output on exception throwing
     *
     * @exception NullPointerException       If the array passed as an argument is
     *                                       {@code null}
     * @exception IllegalArrayFoundException If the array passed as an argument is
     *                                       an empty array
     */
    static void requireNonEmpty(Object[] array, String message) {
        requireNonEmpty(Arrays.asList(array), new IllegalArrayFoundException(message));
    }

    /**
     * Ensures that the array passed as a number is not {@code null} or an empty
     * array. 
     * <p>
     * If the array passed as an argument is an empty array, throw an arbitrary
     * exception object passed as an argument. Execute a
     * {@link #requireNonEmpty(Object[])} method and then throw
     * {@link IllegalArrayFoundException} if the array passed in as argument is an
     * empty array.
     *
     * <pre>
     * A NullPointerException will be thrown if the argument passed to array is null.
     * <code>
     * Preconditions.requireNonEmpty(null, new AnyRuntimeException());
     * &gt;&gt; NullPointerException
     * </code>
     * </pre>
     *
     * <pre>
     * If the array argument is an empty array, then any exception object passed as an argument will be thrown.
     * <code>
     * Preconditions.requireNonEmpty(new String[] {}, new AnyRuntimeException());
     * &gt;&gt; AnyRuntimeException
     * </code>
     * </pre>
     *
     * <pre>
     * If the array argument is non-null and is not an empty array, the function does nothing and exits the validation process.
     * <code>
     * Preconditions.requireNonEmpty(new String[] { "" }, new AnyRuntimeException());
     * </code>
     * </pre>
     *
     * @param array     The array to be validated
     * @param exception Any exception object that is thrown if the preconditions are
     *                  not met
     *
     * @exception NullPointerException       If the array passed as an argument is
     *                                       {@code null} or if the exception object
     *                                       passed as an argument is {@code null}
     * @exception IllegalArrayFoundException If the method
     *                                       {@link #requireNonEmpty(Object[])} is
     *                                       executed and the array passed as an
     *                                       argument is an empty array
     */
    static void requireNonEmpty(Object[] array, RuntimeException exception) {
        requireNonEmpty(Arrays.asList(array), exception);
    }

    /**
     * Ensures that a given string begins with the prefix specified by
     * {@code prefix} .
     * <p>
     * If the argument does not begin with a prefix specified by {@code prefix},
     * {@link IllegalStringFoundException} will be thrown as an exception object at
     * runtime.
     * <p>
     * To specify an arbitrary exception object, use the
     * {@link #requireStartWith(String, String, RuntimeException)} method.
     *
     * <pre>
     * If the string specified as an argument does not begin with a prefix, IllegalStringFoundException will be thrown.
     * <code>
     * Preconditions.requireStartWith("test", "est");
     * &gt;&gt; IllegalStringFoundException
     * </code>
     * </pre>
     *
     * <pre>
     * If the string argument begins with a prefix, the function does nothing and ends the validation process.
     * <code>
     * Preconditions.requireStartWith("test", "test");
     * </code>
     * </pre>
     *
     * @param string The string to be validated
     * @param prefix The prefix
     *
     * @exception IllegalStringFoundException If a string passed as an argument does
     *                                        not start with a prefix specified by
     *                                        {@code prefix}
     */
    static void requireStartWith(String string, String prefix) {
        requireStartWith(string, prefix, new IllegalStringFoundException(
                String.format("String must start with the %s prefix, but %s was given", prefix, string)));
    }

    /**
     * Ensures that a given string begins with the prefix specified by
     * {@code prefix} .
     * <p>
     * If the argument doesn't start with a prefix specified by {@code prefix}, then
     * {@link IllegalStringFoundException} will be thrown at runtime as an exception
     * object. The {@code message} passed as an argument will be printed as a
     * detailed message when the exception occurs.
     * <p>
     * To specify an arbitrary exception object, use the
     * {@link #requireStartWith(String, String, RuntimeException)} method.
     *
     * <pre>
     * If the string argument does not begin with the prefix prefix, IllegalStringFoundException will be thrown.
     * A message passed as an argument will be printed as a detailed message when an exception occurs.
     * <code>
     * Preconditions.requireStartWith("test", "est", "any message");
     * &gt;&gt; IllegalStringFoundException
     * </code>
     * </pre>
     *
     * <pre>
     * If the string argument begins with a prefix, the function does nothing and ends the validation process.
     * <code>
     * Preconditions.requireStartWith("test", "test", "any message");
     * </code>
     * </pre>
     *
     * @param string  The string to be validated
     * @param prefix  The prefix
     * @param message Detailed messages to be output on exception throwing
     *
     * @exception IllegalStringFoundException If a string passed as an argument does
     *                                        not start with a prefix specified by
     *                                        {@code prefix}
     */
    static void requireStartWith(String string, String prefix, String message) {
        requireStartWith(string, prefix, new IllegalStringFoundException(message));
    }

    /**
     * Ensures that the argument starts with the prefix specified by {@code prefix}
     * at the specified search start point.
     * <p>
     * If the argument does not begin with a prefix specified by {@code prefix},
     * {@link IllegalStringFoundException} will be thrown as an exception object at
     * runtime.
     * <p>
     * To specify an arbitrary exception object, use the
     * {@link #requireStartWith(String, String, int, RuntimeException)} method.
     *
     * <pre>
     * If the string specified as an argument does not begin at the search start position specified by offset and the prefix specified by prefix, IllegalStringFoundException will be thrown.
     * <code>
     * Preconditions.requireStartWith("test", "st", 1);
     * &gt;&gt; IllegalStringFoundException
     * </code>
     * </pre>
     *
     * <pre>
     * If the string specified as an argument begins with a prefix specified by prefix from the search start position specified by offset, the function does nothing and ends the validation process.
     * <code>
     * Preconditions.requireStartWith("test", "est", 1);
     * </code>
     * </pre>
     *
     * @param string The string to be validated
     * @param prefix The prefix
     * @param offset The offset
     *
     * @exception IllegalStringFoundException If a string passed as an argument does
     *                                        not start with a prefix specified by
     *                                        {@code prefix} from the specified
     *                                        search start point
     */
    static void requireStartWith(String string, String prefix, int offset) {
        requireStartWith(string, prefix, offset, new IllegalStringFoundException(String.format(
                "String must start with the %s prefix from %s index, but %s was given", prefix, offset, string)));
    }

    /**
     * Ensures that the argument starts with the prefix specified by {@code prefix}
     * at the specified search start point.
     * <p>
     * If the argument doesn't start with a prefix specified by {@code prefix}, then
     * {@link IllegalStringFoundException} will be thrown at runtime as an exception
     * object. The {@code message} passed as an argument will be printed as a
     * detailed message when the exception occurs.
     * <p>
     * To specify an arbitrary exception object, use the
     * {@link #requireStartWith(String, String, int, RuntimeException)} method.
     *
     * <pre>
     * If the string argument does not begin at the search start position specified by offset and the prefix specified by prefix, an IllegalStringFoundException will be thrown.
     * A message passed as an argument will be printed as a detailed message when an exception occurs.
     * <code>
     * Preconditions.requireStartWith("test", "st", 1, "any message");
     * &gt;&gt; IllegalStringFoundException
     * </code>
     * </pre>
     *
     * <pre>
     * If the string specified as an argument begins with a prefix specified by prefix from the search start position specified by offset, the function does nothing and ends the validation process.
     * <code>
     * Preconditions.requireStartWith("test", "est", 1, "any message");
     * </code>
     * </pre>
     *
     * @param string  The string to be validated
     * @param prefix  The prefix
     * @param offset  The offset
     * @param message Detailed messages to be output on exception throwing
     *
     * @exception IllegalStringFoundException If a string passed as an argument does
     *                                        not begin with the prefix specified by
     *                                        {@code prefix} at the specified search
     *                                        start position
     */
    static void requireStartWith(String string, String prefix, int offset, String message) {
        requireStartWith(string, prefix, offset, new IllegalStringFoundException(message));
    }

    /**
     * Ensures that a given string begins with the prefix specified by
     * {@code prefix} .
     * <p>
     * If a string specified as an argument does not begin with a prefix specified
     * by {@code prefix} , any exception object specified as an argument will be
     * thrown at runtime.
     * <p>
     * If you do not specify an arbitrary exception object, use the
     * {@link #requireStartWith(String, String)} method.
     *
     * <pre>
     * If the string argument does not begin with the prefix prefix, then any exception object specified as an argument is thrown.
     * <code>
     * Preconditions.requireStartWith("test", "est", new AnyRuntimeException());
     * &gt;&gt; AnyRuntimeException
     * </code>
     * </pre>
     *
     * <pre>
     * If the string argument begins with a prefix, the function does nothing and ends the validation process.
     * <code>
     * Preconditions.requireStartWith("test", "test", new AnyRuntimeException());
     * </code>
     * </pre>
     *
     * @param string    The string to be validated
     * @param prefix    The prefix
     * @param exception Any exception object that is thrown if the preconditions are
     *                  not met
     *
     * @exception NullPointerException If any exception object specified as an
     *                                 argument is {@code null}
     */
    static void requireStartWith(String string, String prefix, RuntimeException exception) {
        requireStartWith(string, prefix, 0, exception);
    }

    /**
     * Ensures that the argument starts with the prefix specified by {@code prefix}
     * at the specified search start point.
     * <p>
     * If the string specified as an argument does not begin with a prefix specified
     * by {@code prefix}, any exception object passed as an argument is thrown.
     * <p>
     * If you do not specify an arbitrary exception object, use the
     * {@link #requireStartWith(String, String, int)} method.
     *
     * <pre>
     * If the string specified as an argument does not begin at the search start position specified by offset and prefixed by prefix, then any exception object specified as an argument is thrown.
     * <code>
     * Preconditions.requireStartWith("test", "st", 1, new AnyRuntimeException());
     * &gt;&gt; AnyRuntimeException
     * </code>
     * </pre>
     *
     * <pre>
     * If the string specified as an argument begins with a prefix specified by prefix from the search start position specified by offset, the function does nothing and ends the validation process.
     * <code>
     * Preconditions.requireStartWith("test", "est", 1, new AnyRuntimeException());
     * </code>
     * </pre>
     *
     * @param string    The string to be validated
     * @param prefix    The prefix
     * @param offset    The offset
     * @param exception Any exception object that is thrown if the preconditions are
     *                  not met
     *
     * @exception NullPointerException If any exception object passed as an argument
     *                                 is {@code null}
     */
    static void requireStartWith(String string, String prefix, int offset, RuntimeException exception) {
        requireNonNull(exception);

        if (!string.startsWith(prefix, offset)) {
            throw exception;
        }
    }

    /**
     * Ensures that the string specified as an argument ends with the suffix
     * specified by {@code suffix} .
     * <p>
     * If the string specified as an argument does not end with the suffix specified
     * by {@code suffix} , then {@link IllegalStringFoundException} is thrown as an
     * exception object at runtime.
     * <p>
     * To specify an arbitrary exception object, use the
     * {@link #requireEndWith(String, String, RuntimeException)} method.
     *
     * <pre>
     * If the string specified as an argument does not end with the suffix specified by suffix, IllegalStringFoundException will be thrown.
     * <code>
     * Preconditions.requireEndWith("test", "es");
     * &gt;&gt; IllegalStringFoundException
     * </code>
     * </pre>
     *
     * <pre>
     * If the string argument ends with a suffix, the function does nothing and ends the validation process.
     * <code>
     * Preconditions.requireEndWith("test", "est");
     * </code>
     * </pre>
     *
     * @param string The string to be validated
     * @param suffix The suffix
     *
     * @exception IllegalStringFoundException If the string passed as an argument
     *                                        does not end with the suffix specified
     *                                        by {@code suffix}
     */
    static void requireEndWith(String string, String suffix) {
        requireEndWith(string, suffix, new IllegalStringFoundException(
                String.format("String must end with the %s suffix, but %s was given", suffix, string)));
    }

    /**
     * Ensures that the string specified as an argument ends with the suffix
     * specified by {@code suffix} .
     * <p>
     * If the string passed as an argument does not end with the suffix specified by
     * {@code suffix}, {@link IllegalStringFoundException} will be thrown as an
     * exception object at runtime. Any {@code message} passed as an argument will
     * be printed as a detail message when the exception occurs.
     * <p>
     * To specify an arbitrary exception object, use the
     * {@link #requireEndWith(String, String, RuntimeException)} method.
     *
     * <pre>
     * If the string argument does not end with the suffix specified by suffix, IllegalStringFoundException will be thrown.
     * A message passed as an argument will be printed as a detailed message if an exception is thrown.
     * <code>
     * Preconditions.requireEndWith("test", "es", "any message");
     * &gt;&gt; IllegalStringFoundException
     * </code>
     * </pre>
     *
     * <pre>
     * If the string argument ends with a suffix, the function does nothing and ends the validation process.
     * <code>
     * Preconditions.requireEndWith("test", "est", "any message");
     * </code>
     * </pre>
     *
     * @param string  The string to be validated
     * @param suffix  The suffix
     * @param message Detailed messages to be output on exception throwing
     *
     * @exception IllegalStringFoundException If the string passed as an argument
     *                                        does not end with the suffix specified
     *                                        by {@code suffix}
     */
    static void requireEndWith(String string, String suffix, String message) {
        requireEndWith(string, suffix, new IllegalStringFoundException(message));
    }

    /**
     * Ensures that the string specified as an argument ends with the suffix
     * specified by {@code suffix} .
     * <p>
     * If the string specified as an argument does not end with the suffix specified
     * by {@code suffix} , then any exception object specified as an argument will
     * be thrown at runtime.
     * <p>
     * If you want to specify an arbitrary exception object, use the
     * {@link #requireEndWith(String, String)} method.
     *
     * <pre>
     * If the string specified as an argument does not end with the suffix specified by suffix, then any exception object specified as an argument is thrown.
     * <code>
     * Preconditions.requireEndWith("test", "es", new AnyRuntimeException());
     * &gt;&gt; AnyRuntimeException
     * </code>
     * </pre>
     *
     * <pre>
     * If the string argument ends with a suffix, the function does nothing and ends the validation process.
     * <code>
     * Preconditions.requireEndWith("test", "est", new AnyRuntimeException());
     * </code>
     * </pre>
     *
     * @param string    The string to be validated
     * @param suffix    The suffix
     * @param exception Any exception object that is thrown if the preconditions are
     *                  not met
     *
     * @exception NullPointerException If any exception object specified as an
     *                                 argument is {@code null}
     */
    static void requireEndWith(String string, String suffix, RuntimeException exception) {
        requireNonNull(exception);

        if (!string.endsWith(suffix)) {
            throw exception;
        }
    }
}