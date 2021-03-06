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

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.thinkit.common.base.precondition.exception.PreconditionFailedException;

/**
 * The {@link Preconditions} provides a way to ensure the preconditions for
 * arguments and data at the start of the process.
 *
 * <p>
 * Each of the features provided by the {@link Preconditions} validates for
 * preconditions, and if the data being validated does not meet the
 * preconditions, it will always throw an exception corresponding to the
 * validation process.
 *
 * <p>
 * In addition, each validation method has an option that allows you to specify
 * an arbitrary exception object or detailed message when an exception occurs,
 * so you can validate preconditions according to the implementation of your
 * application. The optional exception object must inherit from
 * {@link RuntimeException} .
 *
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
 * &gt;&gt; PreconditionFailedException
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
 * @since 1.0.0
 */
public final class Preconditions {

    /**
     * The default constructor.
     */
    private Preconditions() {
    }

    /**
     * Ensures that the reference to the {@code object} object passed as an argument
     * is not {@code null} . 
     *
     * <p>
     * {@link NullPointerException} is always raised at runtime if the reference to
     * the {@code object} object is {@code null} .
     *
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
    public static void requireNonNull(Object object) {
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
    public static void requireNonNull(Object object, String message) {
        requireNonNull(object, new NullPointerException(message));
    }

    /**
     * Ensures that the reference to the {@code object} object passed as an argument
     * is not {@code null} . 
     *
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
    public static void requireNonNull(Object object, RuntimeException exception) {
        if (object == null) {
            throw exception;
        }
    }

    /**
     * Ensures that the string of the {@code string} object given as an argument is
     * not an empty string. 
     *
     * <p>
     * {@link PreconditionFailedException} is always raised at runtime if the string
     * of the {@code string} object is an empty string.
     *
     * <p>
     * Use the {@link #requireNonEmpty(String)} method if a reference to a
     * {@code string} object specified as an argument is likely to be {@code null} .
     *
     * <p>
     * Use the {@link #requireNonBlank(String, String)} method if you want to print
     * out an arbitrary detailed message when an exception is thrown. Also, to throw
     * an arbitrary exception object when an exception is thrown, use the
     * {@link #requireNonBlank(String, RuntimeException)} method.
     *
     * <pre>
     * PreconditionFailedException will be thrown if the argument passed to string is an empty string.
     * <code>
     * Preconditions.requireNonBlank("");
     * &gt;&gt; PreconditionFailedException
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
     * @throws PreconditionFailedException If an empty string is passed as an
     *                                     argument
     */
    public static void requireNonBlank(String string) {
        requireNonBlank(string, new PreconditionFailedException("String must not be blank"));
    }

    /**
     * Ensures that the string of the {@code string} object given as an argument is
     * not an empty string. 
     *
     * <p>
     * If the string of a {@code string} object is an empty string,
     * {@link PreconditionFailedException} will always be raised at runtime. The
     * {@code message} passed as an argument is output as a detailed message when an
     * exception occurs.
     *
     * <p>
     * Use the {@link #requireNonEmpty(String)} method if a reference to a
     * {@code string} object specified as an argument is likely to be {@code null} .
     *
     * <pre>
     * PreconditionFailedException will be thrown if the argument passed to string is an empty string.
     * A message passed as an argument will be printed as a detailed message when an exception occurs.
     * <code>
     * Preconditions.requireNonBlank("", "any message");
     * &gt;&gt; PreconditionFailedException
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
     * @throws PreconditionFailedException If an empty string is passed as an
     *                                     argument
     */
    public static void requireNonBlank(String string, String message) {
        requireNonBlank(string, new PreconditionFailedException(message));
    }

    /**
     * Ensures that {@code string} given as an argument is not an empty string. 
     *
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
     * @exception PreconditionFailedException If executed by the
     *                                        {@link #requireNonBlank(String)}
     *                                        method and the {@code string} passed
     *                                        as an argument is an empty string
     */
    public static void requireNonBlank(String string, RuntimeException exception) {
        requireNonNull(exception);

        if (string.isEmpty()) {
            throw exception;
        }
    }

    /**
     * Ensures that the reference to the {@code string} object specified as an
     * argument is not {@code null} or the string is not an empty string. 
     *
     * <p>
     * {@link NullPointerException} is always raised at runtime if the reference to
     * a {@code string} object is {@code null} . And
     * {@link PreconditionFailedException} is always raised at runtime if the string
     * of the {@code string} object is an empty string.
     *
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
     * PreconditionFailedException will be thrown if the argument passed to string is an empty string.
     * <code>
     * Preconditions.requireNonEmpty("");
     * &gt;&gt; PreconditionFailedException
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
     * @throws PreconditionFailedException If an empty string is passed as an
     *                                     argument
     */
    public static void requireNonEmpty(String string) {
        requireNonNull(string);
        requireNonBlank(string);
    }

    /**
     * Ensures that the reference to the {@code string} object specified as an
     * argument is not {@code null} or the string is not an empty string. 
     *
     * <p>
     * If the {@code string} object is referenced by {@code null} , then
     * {@link NullPointerException} is always raised at runtime. The {@code message}
     * passed as an argument is output as a detailed message when an exception
     * occurs.
     *
     * <p>
     * If the string of a {@code string} object is an empty string,
     * {@link PreconditionFailedException} is always raised at runtime. The
     * {@code message} passed as an argument is output as a detailed message when an
     * exception occurs.
     *
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
     * PreconditionFailedException will be thrown if the argument passed to string is an empty string.
     * A message passed as an argument will be printed as a detailed message when an exception occurs.
     * <code>
     * Preconditions.requireNonEmpty("", "any message");
     * &gt;&gt; PreconditionFailedException
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
     * @throws PreconditionFailedException If an empty string is passed as an
     *                                     argument
     */
    public static void requireNonEmpty(String string, String message) {
        requireNonNull(string, message);
        requireNonBlank(string, message);
    }

    /**
     * Ensures that the value of {@code string} passed as an argument is not
     * {@code null} or an empty string. 
     *
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
     * @exception PreconditionFailedException If {@code string} passed as an
     *                                        argument is an empty string
     */
    public static void requireNonEmpty(String string, RuntimeException exception) {
        requireNonNull(exception);
        requireNonNull(string);
        requireNonBlank(string, exception);
    }

    /**
     * Ensures that the {@code number} argument is a positive number. 
     *
     * <p>
     * If the argument {@code number} is negative,
     * {@link PreconditionFailedException} is always raised at runtime.
     *
     * <p>
     * To specify an arbitrary exception object, use the
     * {@link #requirePositive(int, RuntimeException)} method.
     *
     * <pre>
     * PreconditionFailedException will be thrown if the number passed as an argument is negative.
     * <code>
     * Preconditions.requirePositive(-1);
     * &gt;&gt; PreconditionFailedException
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
     * @throws PreconditionFailedException If the number of {@code number} specified
     *                                     as an argument is a negative number
     */
    public static void requirePositive(int number) {
        requirePositive(number,
                new PreconditionFailedException(String.format("Number must be positive but %s was given", number)));
    }

    /**
     * Ensures that the {@code number} argument is a positive number. 
     *
     * <p>
     * If the argument {@code number} is negative,
     * {@link PreconditionFailedException} will always be raised at runtime. The
     * {@code message} passed as an argument is output as a detailed message when an
     * exception occurs.
     *
     * <p>
     * To specify an arbitrary exception object, use the
     * {@link #requirePositive(int, RuntimeException)} method.
     *
     * <pre>
     * If the argument number is negative PreconditionFailedException will be thrown.
     * The message passed as an argument will be output as a detailed message when an exception occurs.
     * <code>
     * Preconditions.requirePositive(-1, "any message");
     * &gt;&gt; PreconditionFailedException
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
     * @throws PreconditionFailedException If the number of {@code number} specified
     *                                     as an argument is a negative number
     */
    public static void requirePositive(int number, String message) {
        requirePositive(number, new PreconditionFailedException(message));
    }

    /**
     * Ensures that the {@code number} argument is a positive number. 
     *
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
     * @exception PreconditionFailedException If the specified {@code number} is
     *                                        negative
     */
    public static void requirePositive(int number, RuntimeException exception) {
        requireNonNull(exception);

        if (number < 0) {
            throw exception;
        }
    }

    /**
     * Ensures that the {@code number} argument is a positive long number. 
     *
     * <p>
     * If the argument {@code number} is negative,
     * {@link PreconditionFailedException} is always raised at runtime.
     *
     * <p>
     * To specify an arbitrary exception object, use the
     * {@link #requirePositive(long, RuntimeException)} method.
     *
     * <pre>
     * PreconditionFailedException will be thrown if the long number passed as an argument is negative.
     * <code>
     * Preconditions.requirePositive(-1L);
     * &gt;&gt; PreconditionFailedException
     * </code>
     * </pre>
     *
     * <pre>
     * If the long number passed as a number is positive, it does nothing and ends the validation process.
     * <code>
     * Preconditions.requirePositive(0L);
     * </code>
     * </pre>
     *
     * @param number The long number to be validated
     *
     * @throws PreconditionFailedException If the long number of {@code number}
     *                                     specified as an argument is a negative
     *                                     number
     */
    public static void requirePositive(long number) {
        requirePositive(number, new PreconditionFailedException(
                String.format("Long number must be positive but %s was given", number)));
    }

    /**
     * Ensures that the {@code number} argument is a positive long number. 
     *
     * <p>
     * If the argument {@code number} is negative,
     * {@link PreconditionFailedException} will always be raised at runtime. The
     * {@code message} passed as an argument is output as a detailed message when an
     * exception occurs.
     *
     * <p>
     * To specify an arbitrary exception object, use the
     * {@link #requirePositive(long, RuntimeException)} method.
     *
     * <pre>
     * If the argument long number is negative PreconditionFailedException will be thrown.
     * The message passed as an argument will be output as a detailed message when an exception occurs.
     * <code>
     * Preconditions.requirePositive(-1L, "any message");
     * &gt;&gt; PreconditionFailedException
     * </code>
     * </pre>
     *
     * <pre>
     * If the long number passed as an argument is a positive number, it does nothing and exits the validation process.
     * <code>
     * Preconditions.requirePositive(0L, "any message");
     * </code>
     * </pre>
     *
     * @param number  The long number to be validated
     * @param message Detailed messages to be output on exception throwing
     *
     * @throws PreconditionFailedException If the long number of {@code number}
     *                                     specified as an argument is a negative
     *                                     number
     */
    public static void requirePositive(long number, String message) {
        requirePositive(number, new PreconditionFailedException(message));
    }

    /**
     * Ensures that the {@code number} argument is a positive long number. 
     *
     * <p>
     * If you do not specify an arbitrary exception object, use the
     * {@link #requirePositive(long)} method.
     *
     * <pre>
     * If the long number passed as an argument is negative, then any exception object specified as an argument will be thrown.
     * <code>
     * Preconditions.requirePositive(-1L, new AnyRuntimeException());
     * &gt;&gt; AnyRuntimeException
     * </code>
     * </pre>
     *
     * <pre>
     * If the long number passed as an argument is a positive number, it does nothing and exits the validation process.
     * <code>
     * Preconditions.requirePositive(0L, new AnyRuntimeException());
     * </code>
     * </pre>
     *
     * @param number    The long number to be validated
     * @param exception Any exception object that is thrown if the preconditions are
     *                  not met
     *
     * @exception NullPointerException        If the exception object passed as an
     *                                        argument is {@code null}
     * @exception PreconditionFailedException If the specified long {@code number}
     *                                        is negative
     */
    public static void requirePositive(long number, RuntimeException exception) {
        requireNonNull(exception);

        if (number < 0L) {
            throw exception;
        }
    }

    /**
     * Ensures that the {@code number} argument is a positive short number. 
     *
     * <p>
     * If the argument {@code number} is negative,
     * {@link PreconditionFailedException} is always raised at runtime.
     *
     * <p>
     * To specify an arbitrary exception object, use the
     * {@link #requirePositive(short, RuntimeException)} method.
     *
     * <pre>
     * PreconditionFailedException will be thrown if the short number passed as an argument is negative.
     * <code>
     * short number = -1;
     * Preconditions.requirePositive(number);
     * &gt;&gt; PreconditionFailedException
     * </code>
     * </pre>
     *
     * <pre>
     * If the short number passed as a number is positive, it does nothing and ends the validation process.
     * <code>
     * short number = 0;
     * Preconditions.requirePositive(number);
     * </code>
     * </pre>
     *
     * @param number The short number to be validated
     *
     * @throws PreconditionFailedException If the short number of {@code number}
     *                                     specified as an argument is a negative
     *                                     number
     */
    public static void requirePositive(short number) {
        requirePositive(number, new PreconditionFailedException(
                String.format("Short number must be positive but %s was given", number)));
    }

    /**
     * Ensures that the {@code number} argument is a positive short number. 
     *
     * <p>
     * If the argument {@code number} is negative,
     * {@link PreconditionFailedException} will always be raised at runtime. The
     * {@code message} passed as an argument is output as a detailed message when an
     * exception occurs.
     *
     * <p>
     * To specify an arbitrary exception object, use the
     * {@link #requirePositive(short, RuntimeException)} method.
     *
     * <pre>
     * If the argument short number is negative PreconditionFailedException will be thrown.
     * The message passed as an argument will be output as a detailed message when an exception occurs.
     * <code>
     * short number = -1;
     * Preconditions.requirePositive(number, "any message");
     * &gt;&gt; PreconditionFailedException
     * </code>
     * </pre>
     *
     * <pre>
     * If the short number passed as an argument is a positive number, it does nothing and exits the validation process.
     * <code>
     * short number = 0;
     * Preconditions.requirePositive(number, "any message");
     * </code>
     * </pre>
     *
     * @param number  The short number to be validated
     * @param message Detailed messages to be output on exception throwing
     *
     * @throws PreconditionFailedException If the short number of {@code number}
     *                                     specified as an argument is a negative
     *                                     number
     */
    public static void requirePositive(short number, String message) {
        requirePositive(number, new PreconditionFailedException(message));
    }

    /**
     * Ensures that the {@code number} argument is a positive short number. 
     *
     * <p>
     * If you do not specify an arbitrary exception object, use the
     * {@link #requirePositive(short)} method.
     *
     * <pre>
     * If the short number passed as an argument is negative, then any exception object specified as an argument will be thrown.
     * <code>
     * short number = -1;
     * Preconditions.requirePositive(number, new AnyRuntimeException());
     * &gt;&gt; AnyRuntimeException
     * </code>
     * </pre>
     *
     * <pre>
     * If the short number passed as an argument is a positive number, it does nothing and exits the validation process.
     * <code>
     * short number = 0;
     * Preconditions.requirePositive(number, new AnyRuntimeException());
     * </code>
     * </pre>
     *
     * @param number    The short number to be validated
     * @param exception Any exception object that is thrown if the preconditions are
     *                  not met
     *
     * @exception NullPointerException        If the exception object passed as an
     *                                        argument is {@code null}
     * @exception PreconditionFailedException If the specified short {@code number}
     *                                        is negative
     */
    public static void requirePositive(short number, RuntimeException exception) {
        requireNonNull(exception);

        if (number < 0) {
            throw exception;
        }
    }

    /**
     * Ensures that the {@code number} argument is a positive byte number. 
     *
     * <p>
     * If the argument {@code number} is negative,
     * {@link PreconditionFailedException} is always raised at runtime.
     *
     * <p>
     * To specify an arbitrary exception object, use the
     * {@link #requirePositive(byte, RuntimeException)} method.
     *
     * <pre>
     * PreconditionFailedException will be thrown if the byte number passed as an argument is negative.
     * <code>
     * byte number = -1;
     * Preconditions.requirePositive(number);
     * &gt;&gt; PreconditionFailedException
     * </code>
     * </pre>
     *
     * <pre>
     * If the byte number passed as a number is positive, it does nothing and ends the validation process.
     * <code>
     * byte number = 0;
     * Preconditions.requirePositive(number);
     * </code>
     * </pre>
     *
     * @param number The byte number to be validated
     *
     * @throws PreconditionFailedException If the byte number of {@code number}
     *                                     specified as an argument is a negative
     *                                     number
     */
    public static void requirePositive(byte number) {
        requirePositive(number, new PreconditionFailedException(
                String.format("Byte number must be positive but %s was given", number)));
    }

    /**
     * Ensures that the {@code number} argument is a positive byte number. 
     *
     * <p>
     * If the argument {@code number} is negative,
     * {@link PreconditionFailedException} will always be raised at runtime. The
     * {@code message} passed as an argument is output as a detailed message when an
     * exception occurs.
     *
     * <p>
     * To specify an arbitrary exception object, use the
     * {@link #requirePositive(byte, RuntimeException)} method.
     *
     * <pre>
     * If the argument byte number is negative PreconditionFailedException will be thrown.
     * The message passed as an argument will be output as a detailed message when an exception occurs.
     * <code>
     * byte number = -1;
     * Preconditions.requirePositive(number, "any message");
     * &gt;&gt; PreconditionFailedException
     * </code>
     * </pre>
     *
     * <pre>
     * If the byte number passed as an argument is a positive number, it does nothing and exits the validation process.
     * <code>
     * byte number = 0;
     * Preconditions.requirePositive(number, "any message");
     * </code>
     * </pre>
     *
     * @param number  The byte number to be validated
     * @param message Detailed messages to be output on exception throwing
     *
     * @throws PreconditionFailedException If the byte number of {@code number}
     *                                     specified as an argument is a negative
     *                                     number
     */
    public static void requirePositive(byte number, String message) {
        requirePositive(number, new PreconditionFailedException(message));
    }

    /**
     * Ensures that the {@code number} argument is a positive byte number. 
     *
     * <p>
     * If you do not specify an arbitrary exception object, use the
     * {@link #requirePositive(byte)} method.
     *
     * <pre>
     * If the byte number passed as an argument is negative, then any exception object specified as an argument will be thrown.
     * <code>
     * byte number = -1;
     * Preconditions.requirePositive(number, new AnyRuntimeException());
     * &gt;&gt; AnyRuntimeException
     * </code>
     * </pre>
     *
     * <pre>
     * If the byte number passed as an argument is a positive number, it does nothing and exits the validation process.
     * <code>
     * byte number = 0;
     * Preconditions.requirePositive(number, new AnyRuntimeException());
     * </code>
     * </pre>
     *
     * @param number    The byte number to be validated
     * @param exception Any exception object that is thrown if the preconditions are
     *                  not met
     *
     * @exception NullPointerException        If the exception object passed as an
     *                                        argument is {@code null}
     * @exception PreconditionFailedException If the specified byte {@code number}
     *                                        is negative
     */
    public static void requirePositive(byte number, RuntimeException exception) {
        requireNonNull(exception);

        if (number < 0) {
            throw exception;
        }
    }

    /**
     * Ensures that the {@code number} argument is a positive float number.  
     * <p>
     * If the argument {@code number} is negative,
     * {@link PreconditionFailedException} is always raised at runtime.
     *
     * <p>
     * To specify an arbitrary exception object, use the
     * {@link #requirePositive(float, RuntimeException)} method.
     *
     * <pre>
     * PreconditionFailedException will be thrown if the float number passed as an argument is negative.
     * <code>
     * Preconditions.requirePositive(-1.0f);
     * &gt;&gt; PreconditionFailedException
     * </code>
     * </pre>
     *
     * <pre>
     * If the float number passed as a number is positive, it does nothing and ends the validation process.
     * <code>
     * Preconditions.requirePositive(0.0f);
     * </code>
     * </pre>
     *
     * @param number The float number to be validated
     *
     * @throws PreconditionFailedException If the float number of {@code number}
     *                                     specified as an argument is a negative
     *                                     number
     */
    public static void requirePositive(float number) {
        requirePositive(number, new PreconditionFailedException(
                String.format("Float number must be positive but %s was given", number)));
    }

    /**
     * Ensures that the {@code number} argument is a positive float number. 
     *
     * <p>
     * If the argument {@code number} is negative,
     * {@link PreconditionFailedException} will always be raised at runtime. The
     * {@code message} passed as an argument is output as a detailed message when an
     * exception occurs.
     *
     * <p>
     * To specify an arbitrary exception object, use the
     * {@link #requirePositive(float, RuntimeException)} method.
     *
     * <pre>
     * If the argument float number is negative PreconditionFailedException will be thrown.
     * The message passed as an argument will be output as a detailed message when an exception occurs.
     * <code>
     * Preconditions.requirePositive(-1.0f, "any message");
     * &gt;&gt; PreconditionFailedException
     * </code>
     * </pre>
     *
     * <pre>
     * If the float number passed as an argument is a positive number, it does nothing and exits the validation process.
     * <code>
     * Preconditions.requirePositive(0.0f, "any message");
     * </code>
     * </pre>
     *
     * @param number  The float number to be validated
     * @param message Detailed messages to be output on exception throwing
     *
     * @throws PreconditionFailedException If the float number of {@code number}
     *                                     specified as an argument is a negative
     *                                     number
     */
    public static void requirePositive(float number, String message) {
        requirePositive(number, new PreconditionFailedException(message));
    }

    /**
     * Ensures that the {@code number} argument is a positive float number.  
     * <p>
     * If you do not specify an arbitrary exception object, use the
     * {@link #requirePositive(float)} method.
     *
     * <pre>
     * If the float number passed as an argument is negative, then any exception object specified as an argument will be thrown.
     * <code>
     * Preconditions.requirePositive(-1.0f, new AnyRuntimeException());
     * &gt;&gt; AnyRuntimeException
     * </code>
     * </pre>
     *
     * <pre>
     * If the float number passed as an argument is a positive number, it does nothing and exits the validation process.
     * <code>
     * Preconditions.requirePositive(0.0f, new AnyRuntimeException());
     * </code>
     * </pre>
     *
     * @param number    The float number to be validated
     * @param exception Any exception object that is thrown if the preconditions are
     *                  not met
     *
     * @exception NullPointerException        If the exception object passed as an
     *                                        argument is {@code null}
     * @exception PreconditionFailedException If the specified float {@code number}
     *                                        is negative
     */
    public static void requirePositive(float number, RuntimeException exception) {
        requireNonNull(exception);

        if (number < 0.0f) {
            throw exception;
        }
    }

    /**
     * Ensures that the {@code number} argument is a positive double number. 
     *
     * <p>
     * If the argument {@code number} is negative,
     * {@link PreconditionFailedException} is always raised at runtime.
     *
     * <p>
     * To specify an arbitrary exception object, use the
     * {@link #requirePositive(double, RuntimeException)} method.
     *
     * <pre>
     * PreconditionFailedException will be thrown if the double number passed as an argument is negative.
     * <code>
     * Preconditions.requirePositive(-1.0d);
     * &gt;&gt; PreconditionFailedException
     * </code>
     * </pre>
     *
     * <pre>
     * If the double number passed as a number is positive, it does nothing and ends the validation process.
     * <code>
     * Preconditions.requirePositive(0.0f);
     * </code>
     * </pre>
     *
     * @param number The double number to be validated
     *
     * @throws PreconditionFailedException If the double number of {@code number}
     *                                     specified as an argument is a negative
     *                                     number
     */
    public static void requirePositive(double number) {
        requirePositive(number, new PreconditionFailedException(
                String.format("Double number must be positive but %s was given", number)));
    }

    /**
     * Ensures that the {@code number} argument is a positive double number. 
     *
     * <p>
     * If the argument {@code number} is negative,
     * {@link PreconditionFailedException} will always be raised at runtime. The
     * {@code message} passed as an argument is output as a detailed message when an
     * exception occurs.
     *
     * <p>
     * To specify an arbitrary exception object, use the
     * {@link #requirePositive(double, RuntimeException)} method.
     *
     * <pre>
     * If the argument double number is negative PreconditionFailedException will be thrown.
     * The message passed as an argument will be output as a detailed message when an exception occurs.
     * <code>
     * Preconditions.requirePositive(-1.0d, "any message");
     * &gt;&gt; PreconditionFailedException
     * </code>
     * </pre>
     *
     * <pre>
     * If the double number passed as an argument is a positive number, it does nothing and exits the validation process.
     * <code>
     * Preconditions.requirePositive(0.0d, "any message");
     * </code>
     * </pre>
     *
     * @param number  The double number to be validated
     * @param message Detailed messages to be output on exception throwing
     *
     * @throws PreconditionFailedException If the double number of {@code number}
     *                                     specified as an argument is a negative
     *                                     number
     */
    public static void requirePositive(double number, String message) {
        requirePositive(number, new PreconditionFailedException(message));
    }

    /**
     * Ensures that the {@code number} argument is a positive double number. 
     *
     * <p>
     * If you do not specify an arbitrary exception object, use the
     * {@link #requirePositive(double)} method.
     *
     * <pre>
     * If the double number passed as an argument is negative, then any exception object specified as an argument will be thrown.
     * <code>
     * Preconditions.requirePositive(-1.0d, new AnyRuntimeException());
     * &gt;&gt; AnyRuntimeException
     * </code>
     * </pre>
     *
     * <pre>
     * If the double number passed as an argument is a positive number, it does nothing and exits the validation process.
     * <code>
     * Preconditions.requirePositive(0.0d, new AnyRuntimeException());
     * </code>
     * </pre>
     *
     * @param number    The double number to be validated
     * @param exception Any exception object that is thrown if the preconditions are
     *                  not met
     *
     * @exception NullPointerException        If the exception object passed as an
     *                                        argument is {@code null}
     * @exception PreconditionFailedException If the specified double {@code number}
     *                                        is negative
     */
    public static void requirePositive(double number, RuntimeException exception) {
        requireNonNull(exception);

        if (number < 0.0d) {
            throw exception;
        }
    }

    /**
     * Ensures that the argument {@code number} is a negative number. 
     *
     * <p>
     * If the argument {@code number} is positive,
     * {@link PreconditionFailedException} is always raised at runtime.
     *
     * <p>
     * To specify an arbitrary exception object, use the
     * {@link #requireNegative(int, RuntimeException)} method.
     *
     * <pre>
     * PreconditionFailedException will be thrown if the number passed as an argument is positive.
     * <code>
     * Preconditions.requireNegative(0);
     * &gt;&gt; PreconditionFailedException
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
     * @throws PreconditionFailedException If the number of {@code number} specified
     *                                     as an argument is a positive number
     */
    public static void requireNegative(int number) {
        requireNegative(number,
                new PreconditionFailedException(String.format("Number must be negative but %s was given", number)));
    }

    /**
     * Ensures that the argument {@code number} is a negative number. 
     *
     * <p>
     * If the argument {@code number} is a positive number,
     * {@link PreconditionFailedException} will always be raised at runtime. And the
     * {@code message} passed as an argument is output as a detailed message when an
     * exception occurs.
     *
     * <p>
     * To specify an arbitrary exception object, use the
     * {@link #requireNegative(int, RuntimeException)} method.
     *
     * <pre>
     * If the number argument is positive, PreconditionFailedException will be thrown.
     * The message passed as an argument will be output as a detailed message when an exception occurs.
     * <code>
     * Preconditions.requireNegative(0, "any message");
     * &gt;&gt; PreconditionFailedException
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
     * @throws PreconditionFailedException If the number of {@code number} specified
     *                                     as an argument is a positive number
     */
    public static void requireNegative(int number, String message) {
        requireNegative(number, new PreconditionFailedException(message));
    }

    /**
     * Ensures that the argument {@code number} is a negative number. 
     *
     * <p>
     * If {@code number} is a positive number, throw any exception object specified
     * as an argument.
     *
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
     * @exception PreconditionFailedException If the value of the {@code number}
     *                                        passed as an argument is a positive
     *                                        number
     */
    public static void requireNegative(int number, RuntimeException exception) {
        requireNonNull(exception);

        if (number >= 0) {
            throw exception;
        }
    }

    /**
     * Ensures that the argument {@code number} is a negative long number. 
     *
     * <p>
     * If the argument {@code number} is positive,
     * {@link PreconditionFailedException} is always raised at runtime.
     *
     * <p>
     * To specify an arbitrary exception object, use the
     * {@link #requireNegative(long, RuntimeException)} method.
     *
     * <pre>
     * PreconditionFailedException will be thrown if the long number passed as an argument is positive.
     * <code>
     * Preconditions.requireNegative(0L);
     * &gt;&gt; PreconditionFailedException
     * </code>
     * </pre>
     *
     * <pre>
     * If the long number passed as an argument is a negative number, it does nothing and ends the validation process.
     * <code>
     * Preconditions.requireNegative(-1L);
     * </code>
     * </pre>
     *
     * @param number The long number to be validated
     *
     * @throws PreconditionFailedException If the number of {@code number} specified
     *                                     as an argument is a positive long number
     */
    public static void requireNegative(long number) {
        requireNegative(number, new PreconditionFailedException(
                String.format("Long number must be negative but %s was given", number)));
    }

    /**
     * Ensures that the argument {@code number} is a negative long number. 
     *
     * <p>
     * If the argument {@code number} is a positive number,
     * {@link PreconditionFailedException} will always be raised at runtime. And the
     * {@code message} passed as an argument is output as a detailed message when an
     * exception occurs.
     *
     * <p>
     * To specify an arbitrary exception object, use the
     * {@link #requireNegative(long, RuntimeException)} method.
     *
     * <pre>
     * If the long number argument is positive, PreconditionFailedException will be thrown.
     * The message passed as an argument will be output as a detailed message when an exception occurs.
     * <code>
     * Preconditions.requireNegative(0L, "any message");
     * &gt;&gt; PreconditionFailedException
     * </code>
     * </pre>
     *
     * <pre>
     * If the long number passed as an argument is a negative number, it does nothing and ends the validation process.
     * <code>
     * Preconditions.requireNegative(-1L, "any message");
     * </code>
     * </pre>
     *
     * @param number  The long number to be validated
     * @param message Detailed messages to be output on exception throwing
     *
     * @throws PreconditionFailedException If the number of {@code number} specified
     *                                     as an argument is a positive long number
     */
    public static void requireNegative(long number, String message) {
        requireNegative(number, new PreconditionFailedException(message));
    }

    /**
     * Ensures that the argument {@code number} is a negative long number. 
     *
     * <p>
     * If {@code number} is a positive number, throw any exception object specified
     * as an argument.
     *
     * <p>
     * If you do not specify an arbitrary exception object, use the
     * {@link #requireNegative(long)} method.
     *
     * <pre>
     * If the long number passed as an argument is positive, any exception object specified as an argument will be thrown.
     * <code>
     * Preconditions.requireNegative(0L, new AnyRuntimeException());
     * &gt;&gt; AnyRuntimeException
     * </code>
     * </pre>
     *
     * <pre>
     * If the long number passed as an argument is a negative number, it does nothing and ends the validation process.
     * <code>
     * Preconditions.requireNegative(-1L, new AnyRuntimeException());
     * </code>
     * </pre>
     *
     * @param number    The long number to be validated
     * @param exception Any exception object that is thrown if the preconditions are
     *                  not met
     *
     * @exception NullPointerException        If the exception object passed as an
     *                                        argument is {@code null}
     * @exception PreconditionFailedException If the value of the {@code number}
     *                                        passed as an argument is a positive
     *                                        long number
     */
    public static void requireNegative(long number, RuntimeException exception) {
        requireNonNull(exception);

        if (number >= 0L) {
            throw exception;
        }
    }

    /**
     * Ensures that the argument {@code number} is a negative short number. 
     *
     * <p>
     * If the argument {@code number} is positive,
     * {@link PreconditionFailedException} is always raised at runtime.
     *
     * <p>
     * To specify an arbitrary exception object, use the
     * {@link #requireNegative(short, RuntimeException)} method.
     *
     * <pre>
     * PreconditionFailedException will be thrown if the short number passed as an argument is positive.
     * <code>
     * short number = 0;
     * Preconditions.requireNegative(number);
     * &gt;&gt; PreconditionFailedException
     * </code>
     * </pre>
     *
     * <pre>
     * If the short number passed as an argument is a negative number, it does nothing and ends the validation process.
     * <code>
     * short number = -1;
     * Preconditions.requireNegative(number);
     * </code>
     * </pre>
     *
     * @param number The short number to be validated
     *
     * @throws PreconditionFailedException If the number of {@code number} specified
     *                                     as an argument is a positive short number
     */
    public static void requireNegative(short number) {
        requireNegative(number, new PreconditionFailedException(
                String.format("Short number must be negative but %s was given", number)));
    }

    /**
     * Ensures that the argument {@code number} is a negative short number. 
     *
     * <p>
     * If the argument {@code number} is a positive number,
     * {@link PreconditionFailedException} will always be raised at runtime. And the
     * {@code message} passed as an argument is output as a detailed message when an
     * exception occurs.
     *
     * <p>
     * To specify an arbitrary exception object, use the
     * {@link #requireNegative(short, RuntimeException)} method.
     *
     * <pre>
     * If the short number argument is positive, PreconditionFailedException will be thrown.
     * The message passed as an argument will be output as a detailed message when an exception occurs.
     * <code>
     * short number = -1;
     * Preconditions.requireNegative(number, "any message");
     * &gt;&gt; PreconditionFailedException
     * </code>
     * </pre>
     *
     * <pre>
     * If the short number passed as an argument is a negative number, it does nothing and ends the validation process.
     * <code>
     * short number = -1;
     * Preconditions.requireNegative(number, "any message");
     * </code>
     * </pre>
     *
     * @param number  The short number to be validated
     * @param message Detailed messages to be output on exception throwing
     *
     * @throws PreconditionFailedException If the number of {@code number} specified
     *                                     as an argument is a positive short number
     */
    public static void requireNegative(short number, String message) {
        requireNegative(number, new PreconditionFailedException(message));
    }

    /**
     * Ensures that the argument {@code number} is a negative short number. 
     *
     * <p>
     * If {@code number} is a positive number, throw any exception object specified
     * as an argument.
     *
     * <p>
     * If you do not specify an arbitrary exception object, use the
     * {@link #requireNegative(short)} method.
     *
     * <pre>
     * If the short number passed as an argument is positive, any exception object specified as an argument will be thrown.
     * <code>
     * short number = 0;
     * Preconditions.requireNegative(number, new AnyRuntimeException());
     * &gt;&gt; AnyRuntimeException
     * </code>
     * </pre>
     *
     * <pre>
     * If the short number passed as an argument is a negative number, it does nothing and ends the validation process.
     * <code>
     * short number = -1;
     * Preconditions.requireNegative(number, new AnyRuntimeException());
     * </code>
     * </pre>
     *
     * @param number    The short number to be validated
     * @param exception Any exception object that is thrown if the preconditions are
     *                  not met
     *
     * @exception NullPointerException        If the exception object passed as an
     *                                        argument is {@code null}
     * @exception PreconditionFailedException If the value of the {@code number}
     *                                        passed as an argument is a positive
     *                                        short number
     */
    public static void requireNegative(short number, RuntimeException exception) {
        requireNonNull(exception);

        if (number >= 0) {
            throw exception;
        }
    }

    /**
     * Ensures that the argument {@code number} is a negative byte number.  
     * <p>
     * If the argument {@code number} is positive,
     * {@link PreconditionFailedException} is always raised at runtime.
     *
     * <p>
     * To specify an arbitrary exception object, use the
     * {@link #requireNegative(byte, RuntimeException)} method.
     *
     * <pre>
     * PreconditionFailedException will be thrown if the byte number passed as an argument is positive.
     * <code>
     * byte number = 0;
     * Preconditions.requireNegative(number);
     * &gt;&gt; PreconditionFailedException
     * </code>
     * </pre>
     *
     * <pre>
     * If the byte number passed as an argument is a negative number, it does nothing and ends the validation process.
     * <code>
     * byte number = -1;
     * Preconditions.requireNegative(number);
     * </code>
     * </pre>
     *
     * @param number The byte number to be validated
     *
     * @throws PreconditionFailedException If the number of {@code number} specified
     *                                     as an argument is a positive byte number
     */
    public static void requireNegative(byte number) {
        requireNegative(number, new PreconditionFailedException(
                String.format("Byte number must be negative but %s was given", number)));
    }

    /**
     * Ensures that the argument {@code number} is a negative byte number. 
     *
     * <p>
     * If the argument {@code number} is a positive number,
     * {@link PreconditionFailedException} will always be raised at runtime. And the
     * {@code message} passed as an argument is output as a detailed message when an
     * exception occurs.
     *
     * <p>
     * To specify an arbitrary exception object, use the
     * {@link #requireNegative(byte, RuntimeException)} method.
     *
     * <pre>
     * If the byte number argument is positive, PreconditionFailedException will be thrown.
     * The message passed as an argument will be output as a detailed message when an exception occurs.
     * <code>
     * byte number = -1;
     * Preconditions.requireNegative(number, "any message");
     * &gt;&gt; PreconditionFailedException
     * </code>
     * </pre>
     *
     * <pre>
     * If the byte number passed as an argument is a negative number, it does nothing and ends the validation process.
     * <code>
     * byte number = -1;
     * Preconditions.requireNegative(number, "any message");
     * </code>
     * </pre>
     *
     * @param number  The byte number to be validated
     * @param message Detailed messages to be output on exception throwing
     *
     * @throws PreconditionFailedException If the number of {@code number} specified
     *                                     as an argument is a positive byte number
     */
    public static void requireNegative(byte number, String message) {
        requireNegative(number, new PreconditionFailedException(message));
    }

    /**
     * Ensures that the argument {@code number} is a negative byte number. 
     *
     * <p>
     * If {@code number} is a positive number, throw any exception object specified
     * as an argument.
     *
     * <p>
     * If you do not specify an arbitrary exception object, use the
     * {@link #requireNegative(byte)} method.
     *
     * <pre>
     * If the byte number passed as an argument is positive, any exception object specified as an argument will be thrown.
     * <code>
     * byte number = 0;
     * Preconditions.requireNegative(number, new AnyRuntimeException());
     * &gt;&gt; AnyRuntimeException
     * </code>
     * </pre>
     *
     * <pre>
     * If the byte number passed as an argument is a negative number, it does nothing and ends the validation process.
     * <code>
     * byte number = -1;
     * Preconditions.requireNegative(number, new AnyRuntimeException());
     * </code>
     * </pre>
     *
     * @param number    The byte number to be validated
     * @param exception Any exception object that is thrown if the preconditions are
     *                  not met
     *
     * @exception NullPointerException        If the exception object passed as an
     *                                        argument is {@code null}
     * @exception PreconditionFailedException If the value of the {@code number}
     *                                        passed as an argument is a positive
     *                                        byte number
     */
    public static void requireNegative(byte number, RuntimeException exception) {
        requireNonNull(exception);

        if (number >= 0) {
            throw exception;
        }
    }

    /**
     * Ensures that the argument {@code number} is a negative float number. 
     *
     * <p>
     * If the argument {@code number} is positive,
     * {@link PreconditionFailedException} is always raised at runtime.
     *
     * <p>
     * To specify an arbitrary exception object, use the
     * {@link #requireNegative(float, RuntimeException)} method.
     *
     * <pre>
     * PreconditionFailedException will be thrown if the float number passed as an argument is positive.
     * <code>
     * Preconditions.requireNegative(0.0f);
     * &gt;&gt; PreconditionFailedException
     * </code>
     * </pre>
     *
     * <pre>
     * If the float number passed as an argument is a negative number, it does nothing and ends the validation process.
     * <code>
     * Preconditions.requireNegative(-1.0f);
     * </code>
     * </pre>
     *
     * @param number The float number to be validated
     *
     * @throws PreconditionFailedException If the number of {@code number} specified
     *                                     as an argument is a positive float number
     */
    public static void requireNegative(float number) {
        requireNegative(number, new PreconditionFailedException(
                String.format("Float number must be negative but %s was given", number)));
    }

    /**
     * Ensures that the argument {@code number} is a negative float number. 
     *
     * <p>
     * If the argument {@code number} is a positive number,
     * {@link PreconditionFailedException} will always be raised at runtime. And the
     * {@code message} passed as an argument is output as a detailed message when an
     * exception occurs.
     *
     * <p>
     * To specify an arbitrary exception object, use the
     * {@link #requireNegative(float, RuntimeException)} method.
     *
     * <pre>
     * If the float number argument is positive, PreconditionFailedException will be thrown.
     * The message passed as an argument will be output as a detailed message when an exception occurs.
     * <code>
     * Preconditions.requireNegative(0.0f, "any message");
     * &gt;&gt; PreconditionFailedException
     * </code>
     * </pre>
     *
     * <pre>
     * If the float number passed as an argument is a negative number, it does nothing and ends the validation process.
     * <code>
     * Preconditions.requireNegative(-1.0f, "any message");
     * </code>
     * </pre>
     *
     * @param number  The float number to be validated
     * @param message Detailed messages to be output on exception throwing
     *
     * @throws PreconditionFailedException If the number of {@code number} specified
     *                                     as an argument is a positive float number
     */
    public static void requireNegative(float number, String message) {
        requireNegative(number, new PreconditionFailedException(message));
    }

    /**
     * Ensures that the argument {@code number} is a negative float number. 
     *
     * <p>
     * If {@code number} is a positive number, throw any exception object specified
     * as an argument.
     *
     * <p>
     * If you do not specify an arbitrary exception object, use the
     * {@link #requireNegative(float)} method.
     *
     * <pre>
     * If the float number passed as an argument is positive, any exception object specified as an argument will be thrown.
     * <code>
     * Preconditions.requireNegative(0.0f, new AnyRuntimeException());
     * &gt;&gt; AnyRuntimeException
     * </code>
     * </pre>
     *
     * <pre>
     * If the float number passed as an argument is a negative number, it does nothing and ends the validation process.
     * <code>
     * Preconditions.requireNegative(-1.0f, new AnyRuntimeException());
     * </code>
     * </pre>
     *
     * @param number    The float number to be validated
     * @param exception Any exception object that is thrown if the preconditions are
     *                  not met
     *
     * @exception NullPointerException        If the exception object passed as an
     *                                        argument is {@code null}
     * @exception PreconditionFailedException If the value of the {@code number}
     *                                        passed as an argument is a positive
     *                                        float number
     */
    public static void requireNegative(float number, RuntimeException exception) {
        requireNonNull(exception);

        if (number >= 0.0f) {
            throw exception;
        }
    }

    /**
     * Ensures that the argument {@code number} is a negative double number. 
     *
     * <p>
     * If the argument {@code number} is positive,
     * {@link PreconditionFailedException} is always raised at runtime.
     *
     * <p>
     * To specify an arbitrary exception object, use the
     * {@link #requireNegative(double, RuntimeException)} method.
     *
     * <pre>
     * PreconditionFailedException will be thrown if the double number passed as an argument is positive.
     * <code>
     * Preconditions.requireNegative(0.0d);
     * &gt;&gt; PreconditionFailedException
     * </code>
     * </pre>
     *
     * <pre>
     * If the double number passed as an argument is a negative number, it does nothing and ends the validation process.
     * <code>
     * Preconditions.requireNegative(-1.0d);
     * </code>
     * </pre>
     *
     * @param number The double number to be validated
     *
     * @throws PreconditionFailedException If the number of {@code number} specified
     *                                     as an argument is a positive double
     *                                     number
     */
    public static void requireNegative(double number) {
        requireNegative(number, new PreconditionFailedException(
                String.format("Double number must be negative but %s was given", number)));
    }

    /**
     * Ensures that the argument {@code number} is a negative double number. 
     *
     * <p>
     * If the argument {@code number} is a positive number,
     * {@link PreconditionFailedException} will always be raised at runtime. And the
     * {@code message} passed as an argument is output as a detailed message when an
     * exception occurs.
     *
     * <p>
     * To specify an arbitrary exception object, use the
     * {@link #requireNegative(double, RuntimeException)} method.
     *
     * <pre>
     * If the double number argument is positive, PreconditionFailedException will be thrown.
     * The message passed as an argument will be output as a detailed message when an exception occurs.
     * <code>
     * Preconditions.requireNegative(0.0d, "any message");
     * &gt;&gt; PreconditionFailedException
     * </code>
     * </pre>
     *
     * <pre>
     * If the double number passed as an argument is a negative number, it does nothing and ends the validation process.
     * <code>
     * Preconditions.requireNegative(-1.0d, "any message");
     * </code>
     * </pre>
     *
     * @param number  The double number to be validated
     * @param message Detailed messages to be output on exception throwing
     *
     * @throws PreconditionFailedException If the number of {@code number} specified
     *                                     as an argument is a positive double
     *                                     number
     */
    public static void requireNegative(double number, String message) {
        requireNegative(number, new PreconditionFailedException(message));
    }

    /**
     * Ensures that the argument {@code number} is a negative double number. 
     *
     * <p>
     * If {@code number} is a positive number, throw any exception object specified
     * as an argument.
     *
     * <p>
     * If you do not specify an arbitrary exception object, use the
     * {@link #requireNegative(double)} method.
     *
     * <pre>
     * If the double number passed as an argument is positive, any exception object specified as an argument will be thrown.
     * <code>
     * Preconditions.requireNegative(0.0d, new AnyRuntimeException());
     * &gt;&gt; AnyRuntimeException
     * </code>
     * </pre>
     *
     * <pre>
     * If the double number passed as an argument is a negative number, it does nothing and ends the validation process.
     * <code>
     * Preconditions.requireNegative(-1.0d, new AnyRuntimeException());
     * </code>
     * </pre>
     *
     * @param number    The double number to be validated
     * @param exception Any exception object that is thrown if the preconditions are
     *                  not met
     *
     * @exception NullPointerException        If the exception object passed as an
     *                                        argument is {@code null}
     * @exception PreconditionFailedException If the value of the {@code number}
     *                                        passed as an argument is a positive
     *                                        double number
     */
    public static void requireNegative(double number, RuntimeException exception) {
        requireNonNull(exception);

        if (number >= 0.0d) {
            throw exception;
        }
    }

    /**
     * Ensures that the {@code index} argument is a number within the range
     * specified by {@code from} . 
     *
     * <p>
     * If the {@code index} argument is an out-of-range number,
     * {@link IndexOutOfBoundsException} will always be raised at runtime.
     *
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
    public static void requireRangeFrom(int index, int from) {
        requireRangeFrom(index, from, new IndexOutOfBoundsException(
                String.format("Index %s out-of-bounds for range from length %s", index, from)));
    }

    /**
     * Ensures that the {@code index} argument is a number within the range
     * specified by {@code from} . 
     *
     * <p>
     * If the {@code index} argument is an out-of-range number,
     * {@link IndexOutOfBoundsException} will always be raised at runtime. And the
     * {@code message} passed as an argument is output as a detailed message when an
     * exception occurs.
     *
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
    public static void requireRangeFrom(int index, int from, String message) {
        requireRangeFrom(index, from, new IndexOutOfBoundsException(message));
    }

    /**
     * Ensures that the {@code index} argument is a number within the range
     * specified by {@code from} . 
     *
     * <p>
     * If {@code index} specified as an argument is not within the range specified
     * by {@code to} , throw any exception object specified as an argument.
     *
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
    public static void requireRangeFrom(int index, int from, RuntimeException exception) {
        requireNonNull(exception);

        if (index < from) {
            throw exception;
        }
    }

    /**
     * Ensures that the long {@code index} argument is a number within the range
     * specified by {@code from} . 
     *
     * <p>
     * If the {@code index} argument is an out-of-range number,
     * {@link IndexOutOfBoundsException} will always be raised at runtime.
     *
     * <p>
     * To specify an arbitrary exception object, use the
     * {@link #requireRangeFrom(long, long, RuntimeException)} method.
     *
     * <pre>
     * If the long index passed as an argument is less than the number specified by from, IndexOutOfBoundsException will be thrown.
     * <code>
     * Preconditions.requireRangeFrom(10L, 9L);
     * &gt;&gt; IndexOutOfBoundsException
     * </code>
     * </pre>
     *
     * <pre>
     * If the long index passed as an argument is greater than or equal to the value specified by "from", this function does nothing and exits the validation process.
     * <code>
     * Preconditions.requireRangeFrom(9L, 10L);
     * </code>
     * </pre>
     *
     * @param index The long index to be validated
     * @param from  The lower limit
     *
     * @throws IndexOutOfBoundsException If the long number of the {@code index}
     *                                   argument does not exist within the range
     *                                   specified by {@code from}
     */
    public static void requireRangeFrom(long index, long from) {
        requireRangeFrom(index, from, new IndexOutOfBoundsException(
                String.format("Long index %s out-of-bounds for range from length %s", index, from)));
    }

    /**
     * Ensures that the long {@code index} argument is a number within the range
     * specified by {@code from} . 
     *
     * <p>
     * If the {@code index} argument is an out-of-range number,
     * {@link IndexOutOfBoundsException} will always be raised at runtime. And the
     * {@code message} passed as an argument is output as a detailed message when an
     * exception occurs.
     *
     * <p>
     * To specify an arbitrary exception object, use the
     * {@link #requireRangeFrom(long, long, RuntimeException)} method.
     *
     * <pre>
     * If the long index passed as an argument is less than the number specified by from, IndexOutOfBoundsException will be thrown.
     * A message passed as an argument is output as a detailed message when an exception occurs.
     * <code>
     * Preconditions.requireRangeFrom(10L, 9L, "any message");
     * &gt;&gt; IndexOutOfBoundsException
     * </code>
     * </pre>
     *
     * <pre>
     * If the long index passed as an argument is greater than or equal to the value specified by "from", this function does nothing and exits the validation process.
     * <code>
     * Preconditions.requireRangeFrom(9L, 10L, "any message");
     * </code>
     * </pre>
     *
     * @param index   The long index to be validated
     * @param from    The lower limit
     * @param message Detailed messages to be output on exception throwing
     *
     * @throws IndexOutOfBoundsException If the argument long {@code index} does not
     *                                   fall within the range specified by
     *                                   {@code from}
     */
    public static void requireRangeFrom(long index, long from, String message) {
        requireRangeFrom(index, from, new IndexOutOfBoundsException(message));
    }

    /**
     * Ensures that the long {@code index} argument is a number within the range
     * specified by {@code from} . 
     *
     * <p>
     * If {@code index} specified as an argument is not within the range specified
     * by {@code to} , throw any exception object specified as an argument.
     *
     * <p>
     * If you do not specify an arbitrary exception object, use the
     * {@link #requireRangeFrom(long, long)} method.
     *
     * <pre>
     * If the long index passed as an argument is less than the number specified by from, any of the specified exceptions will be thrown.
     * <code>
     * Preconditions.requireRangeFrom(10L, 9L, new AnyRuntimeException());
     * &gt;&gt; AnyRuntimeException
     * </code>
     * </pre>
     *
     * <pre>
     * If the long index passed as an argument is greater than or equal to the value specified by "from", this function does nothing and exits the validation process.
     * <code>
     * Preconditions.requireRangeFrom(9L, 10L, new AnyRuntimeException());
     * </code>
     * </pre>
     *
     * @param index     The long index to be validated
     * @param from      The lower limit
     * @param exception Any exception object that is thrown if the preconditions are
     *                  not met
     *
     * @exception NullPointerException      If the exception object passed as an
     *                                      argument is {@code null}
     * @exception IndexOutOfBoundsException If the long number of the {@code index}
     *                                      specified as an argument does not fall
     *                                      within the range specified by
     *                                      {@code from}
     */
    public static void requireRangeFrom(long index, long from, RuntimeException exception) {
        requireNonNull(exception);

        if (index < from) {
            throw exception;
        }
    }

    /**
     * Ensures that the short {@code index} argument is a number within the range
     * specified by {@code from} . 
     *
     * <p>
     * If the {@code index} argument is an out-of-range number,
     * {@link IndexOutOfBoundsException} will always be raised at runtime.
     *
     * <p>
     * To specify an arbitrary exception object, use the
     * {@link #requireRangeFrom(short, short, RuntimeException)} method.
     *
     * <pre>
     * If the short index passed as an argument is less than the number specified by from, IndexOutOfBoundsException will be thrown.
     * <code>
     * short index = 10;
     * short from = 9;
     * Preconditions.requireRangeFrom(index, from);
     * &gt;&gt; IndexOutOfBoundsException
     * </code>
     * </pre>
     *
     * <pre>
     * If the short index passed as an argument is greater than or equal to the value specified by "from", this function does nothing and exits the validation process.
     * <code>
     * short index = 9;
     * short from = 10;
     * Preconditions.requireRangeFrom(index, from);
     * </code>
     * </pre>
     *
     * @param index The short index to be validated
     * @param from  The lower limit
     *
     * @throws IndexOutOfBoundsException If the short number of the {@code index}
     *                                   argument does not exist within the range
     *                                   specified by {@code from}
     */
    public static void requireRangeFrom(short index, short from) {
        requireRangeFrom(index, from, new IndexOutOfBoundsException(
                String.format("Short index %s out-of-bounds for range from length %s", index, from)));
    }

    /**
     * Ensures that the short {@code index} argument is a number within the range
     * specified by {@code from} . 
     *
     * <p>
     * If the {@code index} argument is an out-of-range number,
     * {@link IndexOutOfBoundsException} will always be raised at runtime. And the
     * {@code message} passed as an argument is output as a detailed message when an
     * exception occurs.
     *
     * <p>
     * To specify an arbitrary exception object, use the
     * {@link #requireRangeFrom(short, short, RuntimeException)} method.
     *
     * <pre>
     * If the short index passed as an argument is less than the number specified by from, IndexOutOfBoundsException will be thrown.
     * A message passed as an argument is output as a detailed message when an exception occurs.
     * <code>
     * short index = 10;
     * short from = 9;
     * Preconditions.requireRangeFrom(index, from, "any message");
     * &gt;&gt; IndexOutOfBoundsException
     * </code>
     * </pre>
     *
     * <pre>
     * If the short index passed as an argument is greater than or equal to the value specified by "from", this function does nothing and exits the validation process.
     * <code>
     * short index = 9;
     * short index = 10;
     * Preconditions.requireRangeFrom(index, from, "any message");
     * </code>
     * </pre>
     *
     * @param index   The short index to be validated
     * @param from    The lower limit
     * @param message Detailed messages to be output on exception throwing
     *
     * @throws IndexOutOfBoundsException If the argument short {@code index} does
     *                                   not fall within the range specified by
     *                                   {@code from}
     */
    public static void requireRangeFrom(short index, short from, String message) {
        requireRangeFrom(index, from, new IndexOutOfBoundsException(message));
    }

    /**
     * Ensures that the short {@code index} argument is a number within the range
     * specified by {@code from} . 
     *
     * <p>
     * If {@code index} specified as an argument is not within the range specified
     * by {@code to} , throw any exception object specified as an argument.
     *
     * <p>
     * If you do not specify an arbitrary exception object, use the
     * {@link #requireRangeFrom(short, short)} method.
     *
     * <pre>
     * If the short index passed as an argument is less than the number specified by from, any of the specified exceptions will be thrown.
     * <code>
     * short index = 10;
     * short from = 9;
     * Preconditions.requireRangeFrom(index, from, new AnyRuntimeException());
     * &gt;&gt; AnyRuntimeException
     * </code>
     * </pre>
     *
     * <pre>
     * If the short index passed as an argument is greater than or equal to the value specified by "from", this function does nothing and exits the validation process.
     * <code>
     * short index = 9;
     * short from = 10;
     * Preconditions.requireRangeFrom(index, number, new AnyRuntimeException());
     * </code>
     * </pre>
     *
     * @param index     The short index to be validated
     * @param from      The lower limit
     * @param exception Any exception object that is thrown if the preconditions are
     *                  not met
     *
     * @exception NullPointerException      If the exception object passed as an
     *                                      argument is {@code null}
     * @exception IndexOutOfBoundsException If the short number of the {@code index}
     *                                      specified as an argument does not fall
     *                                      within the range specified by
     *                                      {@code from}
     */
    public static void requireRangeFrom(short index, short from, RuntimeException exception) {
        requireNonNull(exception);

        if (index < from) {
            throw exception;
        }
    }

    /**
     * Ensures that the byte {@code index} argument is a number within the range
     * specified by {@code from} . 
     *
     * <p>
     * If the {@code index} argument is an out-of-range number,
     * {@link IndexOutOfBoundsException} will always be raised at runtime.
     *
     * <p>
     * To specify an arbitrary exception object, use the
     * {@link #requireRangeFrom(byte, byte, RuntimeException)} method.
     *
     * <pre>
     * If the byte index passed as an argument is less than the number specified by from, IndexOutOfBoundsException will be thrown.
     * <code>
     * byte index = 10;
     * byte from = 9;
     * Preconditions.requireRangeFrom(index, from);
     * &gt;&gt; IndexOutOfBoundsException
     * </code>
     * </pre>
     *
     * <pre>
     * If the byte index passed as an argument is greater than or equal to the value specified by "from", this function does nothing and exits the validation process.
     * <code>
     * byte index = 9;
     * byte from = 10;
     * Preconditions.requireRangeFrom(index, from);
     * </code>
     * </pre>
     *
     * @param index The byte index to be validated
     * @param from  The lower limit
     *
     * @throws IndexOutOfBoundsException If the byte number of the {@code index}
     *                                   argument does not exist within the range
     *                                   specified by {@code from}
     */
    public static void requireRangeFrom(byte index, byte from) {
        requireRangeFrom(index, from, new IndexOutOfBoundsException(
                String.format("Byte index %s out-of-bounds for range from length %s", index, from)));
    }

    /**
     * Ensures that the byte {@code index} argument is a number within the range
     * specified by {@code from} . 
     *
     * <p>
     * If the {@code index} argument is an out-of-range number,
     * {@link IndexOutOfBoundsException} will always be raised at runtime. And the
     * {@code message} passed as an argument is output as a detailed message when an
     * exception occurs.
     *
     * <p>
     * To specify an arbitrary exception object, use the
     * {@link #requireRangeFrom(byte, byte, RuntimeException)} method.
     *
     * <pre>
     * If the byte index passed as an argument is less than the number specified by from, IndexOutOfBoundsException will be thrown.
     * A message passed as an argument is output as a detailed message when an exception occurs.
     * <code>
     * byte index = 10;
     * byte from = 9;
     * Preconditions.requireRangeFrom(index, from, "any message");
     * &gt;&gt; IndexOutOfBoundsException
     * </code>
     * </pre>
     *
     * <pre>
     * If the byte index passed as an argument is greater than or equal to the value specified by "from", this function does nothing and exits the validation process.
     * <code>
     * byte index = 9;
     * byte index = 10;
     * Preconditions.requireRangeFrom(index, from, "any message");
     * </code>
     * </pre>
     *
     * @param index   The byte index to be validated
     * @param from    The lower limit
     * @param message Detailed messages to be output on exception throwing
     *
     * @throws IndexOutOfBoundsException If the argument byte {@code index} does not
     *                                   fall within the range specified by
     *                                   {@code from}
     */
    public static void requireRangeFrom(byte index, byte from, String message) {
        requireRangeFrom(index, from, new IndexOutOfBoundsException(message));
    }

    /**
     * Ensures that the byte {@code index} argument is a number within the range
     * specified by {@code from} . 
     *
     * <p>
     * If {@code index} specified as an argument is not within the range specified
     * by {@code to} , throw any exception object specified as an argument.
     *
     * <p>
     * If you do not specify an arbitrary exception object, use the
     * {@link #requireRangeFrom(byte, byte)} method.
     *
     * <pre>
     * If the byte index passed as an argument is less than the number specified by from, any of the specified exceptions will be thrown.
     * <code>
     * byte index = 10;
     * byte from = 9;
     * Preconditions.requireRangeFrom(index, from, new AnyRuntimeException());
     * &gt;&gt; AnyRuntimeException
     * </code>
     * </pre>
     *
     * <pre>
     * If the byte index passed as an argument is greater than or equal to the value specified by "from", this function does nothing and exits the validation process.
     * <code>
     * byte index = 9;
     * byte from = 10;
     * Preconditions.requireRangeFrom(index, number, new AnyRuntimeException());
     * </code>
     * </pre>
     *
     * @param index     The byte index to be validated
     * @param from      The lower limit
     * @param exception Any exception object that is thrown if the preconditions are
     *                  not met
     *
     * @exception NullPointerException      If the exception object passed as an
     *                                      argument is {@code null}
     * @exception IndexOutOfBoundsException If the byte number of the {@code index}
     *                                      specified as an argument does not fall
     *                                      within the range specified by
     *                                      {@code from}
     */
    public static void requireRangeFrom(byte index, byte from, RuntimeException exception) {
        requireNonNull(exception);

        if (index < from) {
            throw exception;
        }
    }

    /**
     * Ensures that the float {@code index} argument is a number within the range
     * specified by {@code from} . 
     *
     * <p>
     * If the {@code index} argument is an out-of-range number,
     * {@link IndexOutOfBoundsException} will always be raised at runtime.
     *
     * <p>
     * To specify an arbitrary exception object, use the
     * {@link #requireRangeFrom(float, float, RuntimeException)} method.
     *
     * <pre>
     * If the float index passed as an argument is less than the number specified by from, IndexOutOfBoundsException will be thrown.
     * <code>
     * Preconditions.requireRangeFrom(10.0f, 9.0f);
     * &gt;&gt; IndexOutOfBoundsException
     * </code>
     * </pre>
     *
     * <pre>
     * If the float index passed as an argument is greater than or equal to the value specified by "from", this function does nothing and exits the validation process.
     * <code>
     * Preconditions.requireRangeFrom(9.0f, 10.0f);
     * </code>
     * </pre>
     *
     * @param index The float index to be validated
     * @param from  The lower limit
     *
     * @throws IndexOutOfBoundsException If the float number of the {@code index}
     *                                   argument does not exist within the range
     *                                   specified by {@code from}
     */
    public static void requireRangeFrom(float index, float from) {
        requireRangeFrom(index, from, new IndexOutOfBoundsException(
                String.format("Float index %s out-of-bounds for range from length %s", index, from)));
    }

    /**
     * Ensures that the float {@code index} argument is a number within the range
     * specified by {@code from} . 
     *
     * <p>
     * If the {@code index} argument is an out-of-range number,
     * {@link IndexOutOfBoundsException} will always be raised at runtime. And the
     * {@code message} passed as an argument is output as a detailed message when an
     * exception occurs.
     *
     * <p>
     * To specify an arbitrary exception object, use the
     * {@link #requireRangeFrom(float, float, RuntimeException)} method.
     *
     * <pre>
     * If the float index passed as an argument is less than the number specified by from, IndexOutOfBoundsException will be thrown.
     * A message passed as an argument is output as a detailed message when an exception occurs.
     * <code>
     * Preconditions.requireRangeFrom(10.0f, 9.0f, "any message");
     * &gt;&gt; IndexOutOfBoundsException
     * </code>
     * </pre>
     *
     * <pre>
     * If the float index passed as an argument is greater than or equal to the value specified by "from", this function does nothing and exits the validation process.
     * <code>
     * Preconditions.requireRangeFrom(9.0f, 10.0f, "any message");
     * </code>
     * </pre>
     *
     * @param index   The float index to be validated
     * @param from    The lower limit
     * @param message Detailed messages to be output on exception throwing
     *
     * @throws IndexOutOfBoundsException If the argument float {@code index} does
     *                                   not fall within the range specified by
     *                                   {@code from}
     */
    public static void requireRangeFrom(float index, float from, String message) {
        requireRangeFrom(index, from, new IndexOutOfBoundsException(message));
    }

    /**
     * Ensures that the float {@code index} argument is a number within the range
     * specified by {@code from} . 
     *
     * <p>
     * If {@code index} specified as an argument is not within the range specified
     * by {@code to} , throw any exception object specified as an argument.
     *
     * <p>
     * If you do not specify an arbitrary exception object, use the
     * {@link #requireRangeFrom(float, float)} method.
     *
     * <pre>
     * If the float index passed as an argument is less than the number specified by from, any of the specified exceptions will be thrown.
     * <code>
     * Preconditions.requireRangeFrom(10.0f, 9.0f, new AnyRuntimeException());
     * &gt;&gt; AnyRuntimeException
     * </code>
     * </pre>
     *
     * <pre>
     * If the float index passed as an argument is greater than or equal to the value specified by "from", this function does nothing and exits the validation process.
     * <code>
     * Preconditions.requireRangeFrom(9.0f, 10.0f, new AnyRuntimeException());
     * </code>
     * </pre>
     *
     * @param index     The float index to be validated
     * @param from      The lower limit
     * @param exception Any exception object that is thrown if the preconditions are
     *                  not met
     *
     * @exception NullPointerException      If the exception object passed as an
     *                                      argument is {@code null}
     * @exception IndexOutOfBoundsException If the float number of the {@code index}
     *                                      specified as an argument does not fall
     *                                      within the range specified by
     *                                      {@code from}
     */
    public static void requireRangeFrom(float index, float from, RuntimeException exception) {
        requireNonNull(exception);

        if (index < from) {
            throw exception;
        }
    }

    /**
     * Ensures that the double {@code index} argument is a number within the range
     * specified by {@code from} . 
     *
     * <p>
     * If the {@code index} argument is an out-of-range number,
     * {@link IndexOutOfBoundsException} will always be raised at runtime.
     *
     * <p>
     * To specify an arbitrary exception object, use the
     * {@link #requireRangeFrom(double, double, RuntimeException)} method.
     *
     * <pre>
     * If the double index passed as an argument is less than the number specified by from, IndexOutOfBoundsException will be thrown.
     * <code>
     * Preconditions.requireRangeFrom(10.0d, 9.0d);
     * &gt;&gt; IndexOutOfBoundsException
     * </code>
     * </pre>
     *
     * <pre>
     * If the double index passed as an argument is greater than or equal to the value specified by "from", this function does nothing and exits the validation process.
     * <code>
     * Preconditions.requireRangeFrom(9.0d, 10.0d);
     * </code>
     * </pre>
     *
     * @param index The double index to be validated
     * @param from  The lower limit
     *
     * @throws IndexOutOfBoundsException If the double number of the {@code index}
     *                                   argument does not exist within the range
     *                                   specified by {@code from}
     */
    public static void requireRangeFrom(double index, double from) {
        requireRangeFrom(index, from, new IndexOutOfBoundsException(
                String.format("Double index %s out-of-bounds for range from length %s", index, from)));
    }

    /**
     * Ensures that the double {@code index} argument is a number within the range
     * specified by {@code from} . 
     *
     * <p>
     * If the {@code index} argument is an out-of-range number,
     * {@link IndexOutOfBoundsException} will always be raised at runtime. And the
     * {@code message} passed as an argument is output as a detailed message when an
     * exception occurs.
     *
     * <p>
     * To specify an arbitrary exception object, use the
     * {@link #requireRangeFrom(double, double, RuntimeException)} method.
     *
     * <pre>
     * If the double index passed as an argument is less than the number specified by from, IndexOutOfBoundsException will be thrown.
     * A message passed as an argument is output as a detailed message when an exception occurs.
     * <code>
     * Preconditions.requireRangeFrom(10.0d, 9.0d, "any message");
     * &gt;&gt; IndexOutOfBoundsException
     * </code>
     * </pre>
     *
     * <pre>
     * If the double index passed as an argument is greater than or equal to the value specified by "from", this function does nothing and exits the validation process.
     * <code>
     * Preconditions.requireRangeFrom(9.0d, 10.0d, "any message");
     * </code>
     * </pre>
     *
     * @param index   The double index to be validated
     * @param from    The lower limit
     * @param message Detailed messages to be output on exception throwing
     *
     * @throws IndexOutOfBoundsException If the argument double {@code index} does
     *                                   not fall within the range specified by
     *                                   {@code from}
     */
    public static void requireRangeFrom(double index, double from, String message) {
        requireRangeFrom(index, from, new IndexOutOfBoundsException(message));
    }

    /**
     * Ensures that the double {@code index} argument is a number within the range
     * specified by {@code from} . 
     *
     * <p>
     * If {@code index} specified as an argument is not within the range specified
     * by {@code to} , throw any exception object specified as an argument.
     *
     * <p>
     * If you do not specify an arbitrary exception object, use the
     * {@link #requireRangeFrom(double, double)} method.
     *
     * <pre>
     * If the double index passed as an argument is less than the number specified by from, any of the specified exceptions will be thrown.
     * <code>
     * Preconditions.requireRangeFrom(10.0d, 9.0d, new AnyRuntimeException());
     * &gt;&gt; AnyRuntimeException
     * </code>
     * </pre>
     *
     * <pre>
     * If the double index passed as an argument is greater than or equal to the value specified by "from", this function does nothing and exits the validation process.
     * <code>
     * Preconditions.requireRangeFrom(9.0d, 10.0d, new AnyRuntimeException());
     * </code>
     * </pre>
     *
     * @param index     The double index to be validated
     * @param from      The lower limit
     * @param exception Any exception object that is thrown if the preconditions are
     *                  not met
     *
     * @exception NullPointerException      If the exception object passed as an
     *                                      argument is {@code null}
     * @exception IndexOutOfBoundsException If the double number of the
     *                                      {@code index} specified as an argument
     *                                      does not fall within the range specified
     *                                      by {@code from}
     */
    public static void requireRangeFrom(double index, double from, RuntimeException exception) {
        requireNonNull(exception);

        if (index < from) {
            throw exception;
        }
    }

    /**
     * Ensures that the {@code index} argument is a number within the range
     * specified by {@code to} . 
     *
     * <p>
     * If the {@code index} argument is an out-of-range number,
     * {@link IndexOutOfBoundsException} will always be raised at runtime.
     *
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
    public static void requireRangeTo(int index, int to) {
        requireRangeTo(index, to, new IndexOutOfBoundsException(
                String.format("Index %s out-of-bounds for range from length 0 to length %s", index, to)));
    }

    /**
     * Ensures that the {@code index} argument is a number within the range
     * specified by {@code to} . 
     *
     * <p>
     * If the {@code index} argument is an out-of-range number,
     * {@link IndexOutOfBoundsException} will always be raised at runtime. And the
     * {@code message} passed as an argument is output as a detailed message when an
     * exception occurs.
     *
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
    public static void requireRangeTo(int index, int to, String message) {
        requireRangeTo(index, to, new IndexOutOfBoundsException(message));
    }

    /**
     * Ensures that the {@code index} argument is a number within the range
     * specified by {@code to} . 
     *
     * <p>
     * If {@code index} specified as an argument is not within the range specified
     * by {@code to} , throw any exception object specified as an argument.
     *
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
    public static void requireRangeTo(int index, int to, RuntimeException exception) {
        requireNonNull(exception);

        if (to < index) {
            throw exception;
        }
    }

    /**
     * Ensures that the long {@code index} argument is a number within the range
     * specified by {@code to} . 
     *
     * <p>
     * If the {@code index} argument is an out-of-range number,
     * {@link IndexOutOfBoundsException} will always be raised at runtime.
     *
     * <p>
     * To specify an arbitrary exception object, use the
     * {@link #requireRangeTo(long, long, RuntimeException)} method.
     *
     * <pre>
     * If the long index passed as an argument is greater than the number specified by to, IndexOutOfBoundsException will be thrown.
     * <code>
     * Preconditions.requireRangeTo(10L, 9L);
     * &gt;&gt; IndexOutOfBoundsException
     * </code>
     * </pre>
     *
     * <pre>
     * If the long index passed as an argument is less than or equal to the value specified by "to", this function does nothing and exits the validation process.
     * <code>
     * Preconditions.requireRangeTo(9L, 10L);
     * </code>
     * </pre>
     *
     * @param index The long index to be validated
     * @param to    The upper limit
     *
     * @throws IndexOutOfBoundsException If the long number of the {@code index}
     *                                   argument does not exist within the range
     *                                   specified by {@code to}
     */
    public static void requireRangeTo(long index, long to) {
        requireRangeTo(index, to, new IndexOutOfBoundsException(
                String.format("Long index %s out-of-bounds for range from length 0 to length %s", index, to)));
    }

    /**
     * Ensures that the long {@code index} argument is a number within the range
     * specified by {@code to} . 
     *
     * <p>
     * If the {@code index} argument is an out-of-range number,
     * {@link IndexOutOfBoundsException} will always be raised at runtime. And the
     * {@code message} passed as an argument is output as a detailed message when an
     * exception occurs.
     *
     * <p>
     * To specify an arbitrary exception object, use the
     * {@link #requireRangeTo(long, long, RuntimeException)} method.
     *
     * <pre>
     * If the long index passed as an argument is greater than the number specified by to, IndexOutOfBoundsException will be thrown.
     * A message passed as an argument is output as a detailed message when an exception occurs.
     * <code>
     * Preconditions.requireRangeTo(10L, 9L, "any message");
     * &gt;&gt; IndexOutOfBoundsException
     * </code>
     * </pre>
     *
     * <pre>
     * If the long index passed as an argument is less than or equal to the value specified by "to", this function does nothing and exits the validation process.
     * <code>
     * Preconditions.requireRangeTo(9L, 10L, "any message");
     * </code>
     * </pre>
     *
     * @param index   The long index to be validated
     * @param to      The upper limit
     * @param message Detailed messages to be output on exception throwing
     *
     * @throws IndexOutOfBoundsException If the argument long {@code index} does not
     *                                   fall within the range specified by
     *                                   {@code to}
     */
    public static void requireRangeTo(long index, long to, String message) {
        requireRangeTo(index, to, new IndexOutOfBoundsException(message));
    }

    /**
     * Ensures that the long {@code index} argument is a number within the range
     * specified by {@code to} . 
     *
     * <p>
     * If {@code index} specified as an argument is not within the range specified
     * by {@code to} , throw any exception object specified as an argument.
     *
     * <p>
     * If you do not specify an arbitrary exception object, use the
     * {@link #requireRangeTo(long, long)} method.
     *
     * <pre>
     * If the long index passed as an argument is greater than the number specified by to, any of the specified exceptions will be thrown.
     * <code>
     * Preconditions.requireRangeTo(10L, 9L, new AnyRuntimeException());
     * &gt;&gt; AnyRuntimeException
     * </code>
     * </pre>
     *
     * <pre>
     * If the long index passed as an argument is less than or equal to the value specified by "to", this function does nothing and exits the validation process.
     * <code>
     * Preconditions.requireRangeTo(9L, 10L, new AnyRuntimeException());
     * </code>
     * </pre>
     *
     * @param index     The long index to be validated
     * @param to        The upper limit
     * @param exception Any exception object that is thrown if the preconditions are
     *                  not met
     *
     * @exception NullPointerException      If the exception object passed as an
     *                                      argument is {@code null}
     * @exception IndexOutOfBoundsException If the long number of the {@code index}
     *                                      specified as an argument does not fall
     *                                      within the range specified by {@code to}
     */
    public static void requireRangeTo(long index, long to, RuntimeException exception) {
        requireNonNull(exception);

        if (to < index) {
            throw exception;
        }
    }

    /**
     * Ensures that the short {@code index} argument is a number within the range
     * specified by {@code to} . 
     *
     * <p>
     * If the {@code index} argument is an out-of-range number,
     * {@link IndexOutOfBoundsException} will always be raised at runtime.
     *
     * <p>
     * To specify an arbitrary exception object, use the
     * {@link #requireRangeTo(short, short, RuntimeException)} method.
     *
     * <pre>
     * If the short index passed as an argument is greater than the number specified by to, IndexOutOfBoundsException will be thrown.
     * <code>
     * short index = 10;
     * short to = 9;
     * Preconditions.requireRangeTo(index, to);
     * &gt;&gt; IndexOutOfBoundsException
     * </code>
     * </pre>
     *
     * <pre>
     * If the short index passed as an argument is less than or equal to the value specified by "to", this function does nothing and exits the validation process.
     * <code>
     * short index = 9;
     * short to = 10;
     * Preconditions.requireRangeTo(index, to);
     * </code>
     * </pre>
     *
     * @param index The short index to be validated
     * @param to    The upper limit
     *
     * @throws IndexOutOfBoundsException If the short number of the {@code index}
     *                                   argument does not exist within the range
     *                                   specified by {@code to}
     */
    public static void requireRangeTo(short index, short to) {
        requireRangeTo(index, to, new IndexOutOfBoundsException(
                String.format("Short index %s out-of-bounds for range from length 0 to length %s", index, to)));
    }

    /**
     * Ensures that the short {@code index} argument is a number within the range
     * specified by {@code to} . 
     *
     * <p>
     * If the {@code index} argument is an out-of-range number,
     * {@link IndexOutOfBoundsException} will always be raised at runtime. And the
     * {@code message} passed as an argument is output as a detailed message when an
     * exception occurs.
     *
     * <p>
     * To specify an arbitrary exception object, use the
     * {@link #requireRangeTo(short, short, RuntimeException)} method.
     *
     * <pre>
     * If the short index passed as an argument is greater than the number specified by to, IndexOutOfBoundsException will be thrown.
     * A message passed as an argument is output as a detailed message when an exception occurs.
     * <code>
     * short index = 10;
     * short to = 9;
     * Preconditions.requireRangeTo(index, to, "any message");
     * &gt;&gt; IndexOutOfBoundsException
     * </code>
     * </pre>
     *
     * <pre>
     * If the short index passed as an argument is less than or equal to the value specified by "to", this function does nothing and exits the validation process.
     * <code>
     * short index = 9;
     * short to = 10;
     * Preconditions.requireRangeTo(index, to, "any message");
     * </code>
     * </pre>
     *
     * @param index   The short index to be validated
     * @param to      The upper limit
     * @param message Detailed messages to be output on exception throwing
     *
     * @throws IndexOutOfBoundsException If the argument short {@code index} does
     *                                   not fall within the range specified by
     *                                   {@code to}
     */
    public static void requireRangeTo(short index, short to, String message) {
        requireRangeTo(index, to, new IndexOutOfBoundsException(message));
    }

    /**
     * Ensures that the short {@code index} argument is a number within the range
     * specified by {@code to} . 
     *
     * <p>
     * If {@code index} specified as an argument is not within the range specified
     * by {@code to} , throw any exception object specified as an argument.
     *
     * <p>
     * If you do not specify an arbitrary exception object, use the
     * {@link #requireRangeTo(short, short)} method.
     *
     * <pre>
     * If the short index passed as an argument is greater than the number specified by to, any of the specified exceptions will be thrown.
     * <code>
     * short index = 10;
     * short to = 9;
     * Preconditions.requireRangeTo(index, to, new AnyRuntimeException());
     * &gt;&gt; AnyRuntimeException
     * </code>
     * </pre>
     *
     * <pre>
     * If the short index passed as an argument is less than or equal to the value specified by "to", this function does nothing and exits the validation process.
     * <code>
     * short index = 9;
     * short to = 10;
     * Preconditions.requireRangeTo(index, to, new AnyRuntimeException());
     * </code>
     * </pre>
     *
     * @param index     The short index to be validated
     * @param to        The upper limit
     * @param exception Any exception object that is thrown if the preconditions are
     *                  not met
     *
     * @exception NullPointerException      If the exception object passed as an
     *                                      argument is {@code null}
     * @exception IndexOutOfBoundsException If the short number of the {@code index}
     *                                      specified as an argument does not fall
     *                                      within the range specified by {@code to}
     */
    public static void requireRangeTo(short index, short to, RuntimeException exception) {
        requireNonNull(exception);

        if (to < index) {
            throw exception;
        }
    }

    /**
     * Ensures that the byte {@code index} argument is a number within the range
     * specified by {@code to} . 
     *
     * <p>
     * If the {@code index} argument is an out-of-range number,
     * {@link IndexOutOfBoundsException} will always be raised at runtime.
     *
     * <p>
     * To specify an arbitrary exception object, use the
     * {@link #requireRangeTo(byte, byte, RuntimeException)} method.
     *
     * <pre>
     * If the byte index passed as an argument is greater than the number specified by to, IndexOutOfBoundsException will be thrown.
     * <code>
     * byte index = 10;
     * byte to = 9;
     * Preconditions.requireRangeTo(index, to);
     * &gt;&gt; IndexOutOfBoundsException
     * </code>
     * </pre>
     *
     * <pre>
     * If the byte index passed as an argument is less than or equal to the value specified by "to", this function does nothing and exits the validation process.
     * <code>
     * byte index = 9;
     * byte to = 10;
     * Preconditions.requireRangeTo(index, to);
     * </code>
     * </pre>
     *
     * @param index The byte index to be validated
     * @param to    The upper limit
     *
     * @throws IndexOutOfBoundsException If the byte number of the {@code index}
     *                                   argument does not exist within the range
     *                                   specified by {@code to}
     */
    public static void requireRangeTo(byte index, byte to) {
        requireRangeTo(index, to, new IndexOutOfBoundsException(
                String.format("Byte index %s out-of-bounds for range from length 0 to length %s", index, to)));
    }

    /**
     * Ensures that the byte {@code index} argument is a number within the range
     * specified by {@code to} . 
     *
     * <p>
     * If the {@code index} argument is an out-of-range number,
     * {@link IndexOutOfBoundsException} will always be raised at runtime. And the
     * {@code message} passed as an argument is output as a detailed message when an
     * exception occurs.
     *
     * <p>
     * To specify an arbitrary exception object, use the
     * {@link #requireRangeTo(byte, byte, RuntimeException)} method.
     *
     * <pre>
     * If the byte index passed as an argument is greater than the number specified by to, IndexOutOfBoundsException will be thrown.
     * A message passed as an argument is output as a detailed message when an exception occurs.
     * <code>
     * byte index = 10;
     * byte to = 9;
     * Preconditions.requireRangeTo(index, to, "any message");
     * &gt;&gt; IndexOutOfBoundsException
     * </code>
     * </pre>
     *
     * <pre>
     * If the byte index passed as an argument is less than or equal to the value specified by "to", this function does nothing and exits the validation process.
     * <code>
     * byte index = 9;
     * byte to = 10;
     * Preconditions.requireRangeTo(index, to, "any message");
     * </code>
     * </pre>
     *
     * @param index   The byte index to be validated
     * @param to      The upper limit
     * @param message Detailed messages to be output on exception throwing
     *
     * @throws IndexOutOfBoundsException If the argument byte {@code index} does not
     *                                   fall within the range specified by
     *                                   {@code to}
     */
    public static void requireRangeTo(byte index, byte to, String message) {
        requireRangeTo(index, to, new IndexOutOfBoundsException(message));
    }

    /**
     * Ensures that the byte {@code index} argument is a number within the range
     * specified by {@code to} . 
     *
     * <p>
     * If {@code index} specified as an argument is not within the range specified
     * by {@code to} , throw any exception object specified as an argument.
     *
     * <p>
     * If you do not specify an arbitrary exception object, use the
     * {@link #requireRangeTo(byte, byte)} method.
     *
     * <pre>
     * If the byte index passed as an argument is greater than the number specified by to, any of the specified exceptions will be thrown.
     * <code>
     * byte index = 10;
     * byte to = 9;
     * Preconditions.requireRangeTo(index, to, new AnyRuntimeException());
     * &gt;&gt; AnyRuntimeException
     * </code>
     * </pre>
     *
     * <pre>
     * If the byte index passed as an argument is less than or equal to the value specified by "to", this function does nothing and exits the validation process.
     * <code>
     * byte index = 9;
     * byte to = 10;
     * Preconditions.requireRangeTo(index, to, new AnyRuntimeException());
     * </code>
     * </pre>
     *
     * @param index     The byte index to be validated
     * @param to        The upper limit
     * @param exception Any exception object that is thrown if the preconditions are
     *                  not met
     *
     * @exception NullPointerException      If the exception object passed as an
     *                                      argument is {@code null}
     * @exception IndexOutOfBoundsException If the byte number of the {@code index}
     *                                      specified as an argument does not fall
     *                                      within the range specified by {@code to}
     */
    public static void requireRangeTo(byte index, byte to, RuntimeException exception) {
        requireNonNull(exception);

        if (to < index) {
            throw exception;
        }
    }

    /**
     * Ensures that the float {@code index} argument is a number within the range
     * specified by {@code to} . 
     *
     * <p>
     * If the {@code index} argument is an out-of-range number,
     * {@link IndexOutOfBoundsException} will always be raised at runtime.
     *
     * <p>
     * To specify an arbitrary exception object, use the
     * {@link #requireRangeTo(float, float, RuntimeException)} method.
     *
     * <pre>
     * If the float index passed as an argument is greater than the number specified by to, IndexOutOfBoundsException will be thrown.
     * <code>
     * Preconditions.requireRangeTo(10.0f, 9.0f);
     * &gt;&gt; IndexOutOfBoundsException
     * </code>
     * </pre>
     *
     * <pre>
     * If the float index passed as an argument is less than or equal to the value specified by "to", this function does nothing and exits the validation process.
     * <code>
     * Preconditions.requireRangeTo(9.0f, 10.0f);
     * </code>
     * </pre>
     *
     * @param index The float index to be validated
     * @param to    The upper limit
     *
     * @throws IndexOutOfBoundsException If the float number of the {@code index}
     *                                   argument does not exist within the range
     *                                   specified by {@code to}
     */
    public static void requireRangeTo(float index, float to) {
        requireRangeTo(index, to, new IndexOutOfBoundsException(
                String.format("Float index %s out-of-bounds for range from length 0 to length %s", index, to)));
    }

    /**
     * Ensures that the float {@code index} argument is a number within the range
     * specified by {@code to} . 
     *
     * <p>
     * If the {@code index} argument is an out-of-range number,
     * {@link IndexOutOfBoundsException} will always be raised at runtime. And the
     * {@code message} passed as an argument is output as a detailed message when an
     * exception occurs.
     *
     * <p>
     * To specify an arbitrary exception object, use the
     * {@link #requireRangeTo(float, float, RuntimeException)} method.
     *
     * <pre>
     * If the float index passed as an argument is greater than the number specified by to, IndexOutOfBoundsException will be thrown.
     * A message passed as an argument is output as a detailed message when an exception occurs.
     * <code>
     * Preconditions.requireRangeTo(10.0f, 9.0f, "any message");
     * &gt;&gt; IndexOutOfBoundsException
     * </code>
     * </pre>
     *
     * <pre>
     * If the float index passed as an argument is less than or equal to the value specified by "to", this function does nothing and exits the validation process.
     * <code>
     * Preconditions.requireRangeTo(9.0f, 10.0f, "any message");
     * </code>
     * </pre>
     *
     * @param index   The float index to be validated
     * @param to      The upper limit
     * @param message Detailed messages to be output on exception throwing
     *
     * @throws IndexOutOfBoundsException If the argument float {@code index} does
     *                                   not fall within the range specified by
     *                                   {@code to}
     */
    public static void requireRangeTo(float index, float to, String message) {
        requireRangeTo(index, to, new IndexOutOfBoundsException(message));
    }

    /**
     * Ensures that the float {@code index} argument is a number within the range
     * specified by {@code to} . 
     *
     * <p>
     * If {@code index} specified as an argument is not within the range specified
     * by {@code to} , throw any exception object specified as an argument.
     *
     * <p>
     * If you do not specify an arbitrary exception object, use the
     * {@link #requireRangeTo(float, float)} method.
     *
     * <pre>
     * If the float index passed as an argument is greater than the number specified by to, any of the specified exceptions will be thrown.
     * <code>
     * Preconditions.requireRangeTo(10.0f, 9.0f, new AnyRuntimeException());
     * &gt;&gt; AnyRuntimeException
     * </code>
     * </pre>
     *
     * <pre>
     * If the float index passed as an argument is less than or equal to the value specified by "to", this function does nothing and exits the validation process.
     * <code>
     * Preconditions.requireRangeTo(9.0f, 10.0f, new AnyRuntimeException());
     * </code>
     * </pre>
     *
     * @param index     The float index to be validated
     * @param to        The upper limit
     * @param exception Any exception object that is thrown if the preconditions are
     *                  not met
     *
     * @exception NullPointerException      If the exception object passed as an
     *                                      argument is {@code null}
     * @exception IndexOutOfBoundsException If the float number of the {@code index}
     *                                      specified as an argument does not fall
     *                                      within the range specified by {@code to}
     */
    public static void requireRangeTo(float index, float to, RuntimeException exception) {
        requireNonNull(exception);

        if (to < index) {
            throw exception;
        }
    }

    /**
     * Ensures that the double {@code index} argument is a number within the range
     * specified by {@code to} . 
     *
     * <p>
     * If the {@code index} argument is an out-of-range number,
     * {@link IndexOutOfBoundsException} will always be raised at runtime.
     *
     * <p>
     * To specify an arbitrary exception object, use the
     * {@link #requireRangeTo(double, double, RuntimeException)} method.
     *
     * <pre>
     * If the double index passed as an argument is greater than the number specified by to, IndexOutOfBoundsException will be thrown.
     * <code>
     * Preconditions.requireRangeTo(10.0d, 9.0d);
     * &gt;&gt; IndexOutOfBoundsException
     * </code>
     * </pre>
     *
     * <pre>
     * If the double index passed as an argument is less than or equal to the value specified by "to", this function does nothing and exits the validation process.
     * <code>
     * Preconditions.requireRangeTo(9.0d, 10.0d);
     * </code>
     * </pre>
     *
     * @param index The double index to be validated
     * @param to    The upper limit
     *
     * @throws IndexOutOfBoundsException If the double number of the {@code index}
     *                                   argument does not exist within the range
     *                                   specified by {@code to}
     */
    public static void requireRangeTo(double index, double to) {
        requireRangeTo(index, to, new IndexOutOfBoundsException(
                String.format("Double index %s out-of-bounds for range from length 0 to length %s", index, to)));
    }

    /**
     * Ensures that the double {@code index} argument is a number within the range
     * specified by {@code to} . 
     *
     * <p>
     * If the {@code index} argument is an out-of-range number,
     * {@link IndexOutOfBoundsException} will always be raised at runtime. And the
     * {@code message} passed as an argument is output as a detailed message when an
     * exception occurs.
     *
     * <p>
     * To specify an arbitrary exception object, use the
     * {@link #requireRangeTo(double, double, RuntimeException)} method.
     *
     * <pre>
     * If the double index passed as an argument is greater than the number specified by to, IndexOutOfBoundsException will be thrown.
     * A message passed as an argument is output as a detailed message when an exception occurs.
     * <code>
     * Preconditions.requireRangeTo(10.0d, 9.0d, "any message");
     * &gt;&gt; IndexOutOfBoundsException
     * </code>
     * </pre>
     *
     * <pre>
     * If the double index passed as an argument is less than or equal to the value specified by "to", this function does nothing and exits the validation process.
     * <code>
     * Preconditions.requireRangeTo(9.0d, 10.0d, "any message");
     * </code>
     * </pre>
     *
     * @param index   The double index to be validated
     * @param to      The upper limit
     * @param message Detailed messages to be output on exception throwing
     *
     * @throws IndexOutOfBoundsException If the argument double {@code index} does
     *                                   not fall within the range specified by
     *                                   {@code to}
     */
    public static void requireRangeTo(double index, double to, String message) {
        requireRangeTo(index, to, new IndexOutOfBoundsException(message));
    }

    /**
     * Ensures that the double {@code index} argument is a number within the range
     * specified by {@code to} . 
     *
     * <p>
     * If {@code index} specified as an argument is not within the range specified
     * by {@code to} , throw any exception object specified as an argument.
     *
     * <p>
     * If you do not specify an arbitrary exception object, use the
     * {@link #requireRangeTo(double, double)} method.
     *
     * <pre>
     * If the double index passed as an argument is greater than the number specified by to, any of the specified exceptions will be thrown.
     * <code>
     * Preconditions.requireRangeTo(10.0d, 9.0d, new AnyRuntimeException());
     * &gt;&gt; AnyRuntimeException
     * </code>
     * </pre>
     *
     * <pre>
     * If the double index passed as an argument is less than or equal to the value specified by "to", this function does nothing and exits the validation process.
     * <code>
     * Preconditions.requireRangeTo(9.0d, 10.0d, new AnyRuntimeException());
     * </code>
     * </pre>
     *
     * @param index     The double index to be validated
     * @param to        The upper limit
     * @param exception Any exception object that is thrown if the preconditions are
     *                  not met
     *
     * @exception NullPointerException      If the exception object passed as an
     *                                      argument is {@code null}
     * @exception IndexOutOfBoundsException If the double number of the
     *                                      {@code index} specified as an argument
     *                                      does not fall within the range specified
     *                                      by {@code to}
     */
    public static void requireRangeTo(double index, double to, RuntimeException exception) {
        requireNonNull(exception);

        if (to < index) {
            throw exception;
        }
    }

    /**
     * Ensures that the {@code index} argument is within the range specified by
     * {@code from} to {@code to} . 
     *
     * <p>
     * If the {@code index} argument is an out-of-range number,
     * {@link IndexOutOfBoundsException} is always raised at runtime.
     *
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
    public static void requireRange(int index, int from, int to) {
        requireRange(index, from, to, new IndexOutOfBoundsException(
                String.format("Index %s out-of-bounds for range from length %s to length %s", index, from, to)));
    }

    /**
     * Ensures that the {@code index} argument is within the range specified by
     * {@code from} to {@code to} . 
     *
     * <p>
     * If the {@code index} argument is an out-of-range number,
     * {@link IndexOutOfBoundsException} will always be raised at runtime. The
     * {@code message} passed as an argument is output as a detailed message when an
     * exception occurs.
     *
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
    public static void requireRange(int index, int from, int to, String message) {
        requireRange(index, from, to, new IndexOutOfBoundsException(message));
    }

    /**
     * Ensures that the {@code index} argument is within the range specified by
     * {@code from} to {@code to} . 
     *
     * <p>
     * Throws an arbitrary exception object if the argument {@code index} is an
     * out-of-range number. Execute from the {@link #requireRange(int, int, int)}
     * method, and throw {@link IndexOutOfBoundsException} as an exception object if
     * the {@code index} argument is not a number within the range specified by
     * {@code from} to {@code to} .
     *
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
    public static void requireRange(int index, int from, int to, RuntimeException exception) {
        requireNonNull(exception);

        if (index < from || to < index) {
            throw exception;
        }
    }

    /**
     * Ensures that the long {@code index} argument is within the range specified by
     * {@code from} to {@code to} . 
     *
     * <p>
     * If the {@code index} argument is an out-of-range number,
     * {@link IndexOutOfBoundsException} is always raised at runtime.
     *
     * <p>
     * To specify an arbitrary exception object, use the
     * {@link #requireRange(long, long, long, RuntimeException)} method.
     *
     * <pre>
     * IndexOutOfBoundsException will be thrown if the long index passed as an argument is not within the range specified by from and to.
     * <code>
     * Preconditions.requireRange(10L, 0L, 9L);
     * &gt;&gt; IndexOutOfBoundsException
     * </code>
     * </pre>
     *
     * <pre>
     * If the long index passed as an argument is within the range of from and to, it does nothing and ends the validation process.
     * <code>
     * Preconditions.requireRange(9L, 0L, 10L);
     * </code>
     * </pre>
     *
     * @param index The long index to be validated
     * @param from  The lower limit
     * @param to    The upper limit
     *
     * @throws IndexOutOfBoundsException If the long number of the {@code index}
     *                                   argument does not fall within the range
     *                                   specified by {@code from} to {@code to}
     */
    public static void requireRange(long index, long from, long to) {
        requireRange(index, from, to, new IndexOutOfBoundsException(
                String.format("Long index %s out-of-bounds for range from length %s to length %s", index, from, to)));
    }

    /**
     * Ensures that the long {@code index} argument is within the range specified by
     * {@code from} to {@code to} . 
     *
     * <p>
     * If the {@code index} argument is an out-of-range number,
     * {@link IndexOutOfBoundsException} will always be raised at runtime. The
     * {@code message} passed as an argument is output as a detailed message when an
     * exception occurs.
     *
     * <p>
     * To specify an arbitrary exception object, use the
     * {@link #requireRange(long, long, long, RuntimeException)} method.
     *
     * <pre>
     * IndexOutOfBoundsException will be thrown if the long index passed as an argument is not within the range specified by from and to.
     * A message passed as an argument is output as a detailed message when an exception occurs.
     * <code>
     * Preconditions.requireRange(10L, 0L, 9L, "any message");
     * &gt;&gt; IndexOutOfBoundsException
     * </code>
     * </pre>
     *
     * <pre>
     * If the long index passed as an argument is within the range of from and to, it does nothing and ends the validation process.
     * <code>
     * Preconditions.requireRange(9L, 0L, 10L, "any message");
     * </code>
     * </pre>
     *
     * @param index   The long index to be validated
     * @param from    The lower limit
     * @param to      The upper limit
     * @param message Detailed messages to be output on exception throwing
     *
     * @throws IndexOutOfBoundsException If the long number of the {@code index}
     *                                   argument does not fall within the range
     *                                   specified by {@code from} to {@code to}
     */
    public static void requireRange(long index, long from, long to, String message) {
        requireRange(index, from, to, new IndexOutOfBoundsException(message));
    }

    /**
     * Ensures that the long {@code index} argument is within the range specified by
     * {@code from} to {@code to} . 
     *
     * <p>
     * Throws an arbitrary exception object if the argument {@code index} is an
     * out-of-range number. Execute from the {@link #requireRange(long, long, long)}
     * method, and throw {@link IndexOutOfBoundsException} as an exception object if
     * the {@code index} argument is not a number within the range specified by
     * {@code from} to {@code to} .
     *
     * <p>
     * If you do not specify an arbitrary exception object, use the
     * {@link #requireRange(long, long, long)} method.
     *
     * <pre>
     * If the long index passed as an argument is not within the range specified by from and to, any exception object passed as an argument will be thrown.
     * <code>
     * Preconditions.requireRange(10L, 0L, 9L, new AnyRuntimeException());
     * &gt;&gt; AnyRuntimeException
     * </code>
     * </pre>
     *
     * <pre>
     * If the long index passed as an argument is within the range of from and to, it does nothing and ends the validation process.
     * <code>
     * Preconditions.requireRange(9L, 0L, 10L, new AnyRuntimeException());
     * </code>
     * </pre>
     *
     * @param index     The long index to be validated
     * @param from      The upper limit
     * @param to        The lower limit
     * @param exception Any exception object that is thrown if the preconditions are
     *                  not met
     *
     * @exception NullPointerException      If the exception object passed as an
     *                                      argument is {@code null}
     * @exception IndexOutOfBoundsException If the long number of the {@code index}
     *                                      argument does not fall within the range
     *                                      specified by {@code from} to {@code to}
     */
    public static void requireRange(long index, long from, long to, RuntimeException exception) {
        requireNonNull(exception);

        if (index < from || to < index) {
            throw exception;
        }
    }

    /**
     * Ensures that the short {@code index} argument is within the range specified
     * by {@code from} to {@code to} . 
     *
     * <p>
     * If the {@code index} argument is an out-of-range number,
     * {@link IndexOutOfBoundsException} is always raised at runtime.
     *
     * <p>
     * To specify an arbitrary exception object, use the
     * {@link #requireRange(short, short, short, RuntimeException)} method.
     *
     * <pre>
     * IndexOutOfBoundsException will be thrown if the short index passed as an argument is not within the range specified by from and to.
     * <code>
     * short index = 10;
     * short from = 0;
     * short to = 9;
     * Preconditions.requireRange(index, from, to);
     * &gt;&gt; IndexOutOfBoundsException
     * </code>
     * </pre>
     *
     * <pre>
     * If the short index passed as an argument is within the range of from and to, it does nothing and ends the validation process.
     * <code>
     * short index = 9;
     * short from = 0;
     * short to = 10;
     * Preconditions.requireRange(index, from, to);
     * </code>
     * </pre>
     *
     * @param index The short index to be validated
     * @param from  The lower limit
     * @param to    The upper limit
     *
     * @throws IndexOutOfBoundsException If the short number of the {@code index}
     *                                   argument does not fall within the range
     *                                   specified by {@code from} to {@code to}
     */
    public static void requireRange(short index, short from, short to) {
        requireRange(index, from, to, new IndexOutOfBoundsException(
                String.format("Short index %s out-of-bounds for range from length %s to length %s", index, from, to)));
    }

    /**
     * Ensures that the short {@code index} argument is within the range specified
     * by {@code from} to {@code to} . 
     *
     * <p>
     * If the {@code index} argument is an out-of-range number,
     * {@link IndexOutOfBoundsException} will always be raised at runtime. The
     * {@code message} passed as an argument is output as a detailed message when an
     * exception occurs.
     *
     * <p>
     * To specify an arbitrary exception object, use the
     * {@link #requireRange(short, short, short, RuntimeException)} method.
     *
     * <pre>
     * IndexOutOfBoundsException will be thrown if the short index passed as an argument is not within the range specified by from and to.
     * A message passed as an argument is output as a detailed message when an exception occurs.
     * <code>
     * short index = 10;
     * short from = 0;
     * short to = 9;
     * Preconditions.requireRange(index, from, to, "any message");
     * &gt;&gt; IndexOutOfBoundsException
     * </code>
     * </pre>
     *
     * <pre>
     * If the short index passed as an argument is within the range of from and to, it does nothing and ends the validation process.
     * <code>
     * short index = 9;
     * short from = 0;
     * short to = 10;
     * Preconditions.requireRange(index, from, to, "any message");
     * </code>
     * </pre>
     *
     * @param index   The short index to be validated
     * @param from    The lower limit
     * @param to      The upper limit
     * @param message Detailed messages to be output on exception throwing
     *
     * @throws IndexOutOfBoundsException If the short number of the {@code index}
     *                                   argument does not fall within the range
     *                                   specified by {@code from} to {@code to}
     */
    public static void requireRange(short index, short from, short to, String message) {
        requireRange(index, from, to, new IndexOutOfBoundsException(message));
    }

    /**
     * Ensures that the short {@code index} argument is within the range specified
     * by {@code from} to {@code to} . 
     *
     * <p>
     * Throws an arbitrary exception object if the argument {@code index} is an
     * out-of-range number. Execute from the
     * {@link #requireRange(short, short, short)} method, and throw
     * {@link IndexOutOfBoundsException} as an exception object if the {@code index}
     * argument is not a number within the range specified by {@code from} to
     * {@code to} .
     *
     * <p>
     * If you do not specify an arbitrary exception object, use the
     * {@link #requireRange(short, short, short)} method.
     *
     * <pre>
     * If the short index passed as an argument is not within the range specified by from and to, any exception object passed as an argument will be thrown.
     * <code>
     * short index = 10;
     * short from = 0;
     * short to = 9;
     * Preconditions.requireRange(index, from, to, new AnyRuntimeException());
     * &gt;&gt; AnyRuntimeException
     * </code>
     * </pre>
     *
     * <pre>
     * If the short index passed as an argument is within the range of from and to, it does nothing and ends the validation process.
     * <code>
     * short index = 9;
     * short from = 0;
     * short to = 10;
     * Preconditions.requireRange(index, from, to, new AnyRuntimeException());
     * </code>
     * </pre>
     *
     * @param index     The short index to be validated
     * @param from      The upper limit
     * @param to        The lower limit
     * @param exception Any exception object that is thrown if the preconditions are
     *                  not met
     *
     * @exception NullPointerException      If the exception object passed as an
     *                                      argument is {@code null}
     * @exception IndexOutOfBoundsException If the short number of the {@code index}
     *                                      argument does not fall within the range
     *                                      specified by {@code from} to {@code to}
     */
    public static void requireRange(short index, short from, short to, RuntimeException exception) {
        requireNonNull(exception);

        if (index < from || to < index) {
            throw exception;
        }
    }

    /**
     * Ensures that the byte {@code index} argument is within the range specified by
     * {@code from} to {@code to} . 
     *
     * <p>
     * If the {@code index} argument is an out-of-range number,
     * {@link IndexOutOfBoundsException} is always raised at runtime.
     *
     * <p>
     * To specify an arbitrary exception object, use the
     * {@link #requireRange(byte, byte, byte, RuntimeException)} method.
     *
     * <pre>
     * IndexOutOfBoundsException will be thrown if the byte index passed as an argument is not within the range specified by from and to.
     * <code>
     * byte index = 10;
     * byte from = 0;
     * byte to = 9;
     * Preconditions.requireRange(index, from, to);
     * &gt;&gt; IndexOutOfBoundsException
     * </code>
     * </pre>
     *
     * <pre>
     * If the byte index passed as an argument is within the range of from and to, it does nothing and ends the validation process.
     * <code>
     * byte index = 9;
     * byte from = 0;
     * byte to = 10;
     * Preconditions.requireRange(index, from, to);
     * </code>
     * </pre>
     *
     * @param index The byte index to be validated
     * @param from  The lower limit
     * @param to    The upper limit
     *
     * @throws IndexOutOfBoundsException If the byte number of the {@code index}
     *                                   argument does not fall within the range
     *                                   specified by {@code from} to {@code to}
     */
    public static void requireRange(byte index, byte from, byte to) {
        requireRange(index, from, to, new IndexOutOfBoundsException(
                String.format("Byte index %s out-of-bounds for range from length %s to length %s", index, from, to)));
    }

    /**
     * Ensures that the byte {@code index} argument is within the range specified by
     * {@code from} to {@code to} . 
     *
     * <p>
     * If the {@code index} argument is an out-of-range number,
     * {@link IndexOutOfBoundsException} will always be raised at runtime. The
     * {@code message} passed as an argument is output as a detailed message when an
     * exception occurs.
     *
     * <p>
     * To specify an arbitrary exception object, use the
     * {@link #requireRange(byte, byte, byte, RuntimeException)} method.
     *
     * <pre>
     * IndexOutOfBoundsException will be thrown if the byte index passed as an argument is not within the range specified by from and to.
     * A message passed as an argument is output as a detailed message when an exception occurs.
     * <code>
     * byte index = 10;
     * byte from = 0;
     * byte to = 9;
     * Preconditions.requireRange(index, from, to, "any message");
     * &gt;&gt; IndexOutOfBoundsException
     * </code>
     * </pre>
     *
     * <pre>
     * If the byte index passed as an argument is within the range of from and to, it does nothing and ends the validation process.
     * <code>
     * byte index = 9;
     * byte from = 0;
     * byte to = 10;
     * Preconditions.requireRange(index, from, to, "any message");
     * </code>
     * </pre>
     *
     * @param index   The byte index to be validated
     * @param from    The lower limit
     * @param to      The upper limit
     * @param message Detailed messages to be output on exception throwing
     *
     * @throws IndexOutOfBoundsException If the byte number of the {@code index}
     *                                   argument does not fall within the range
     *                                   specified by {@code from} to {@code to}
     */
    public static void requireRange(byte index, byte from, byte to, String message) {
        requireRange(index, from, to, new IndexOutOfBoundsException(message));
    }

    /**
     * Ensures that the byte {@code index} argument is within the range specified by
     * {@code from} to {@code to} . 
     *
     * <p>
     * Throws an arbitrary exception object if the argument {@code index} is an
     * out-of-range number. Execute from the {@link #requireRange(byte, byte, byte)}
     * method, and throw {@link IndexOutOfBoundsException} as an exception object if
     * the {@code index} argument is not a number within the range specified by
     * {@code from} to {@code to} .
     *
     * <p>
     * If you do not specify an arbitrary exception object, use the
     * {@link #requireRange(byte, byte, byte)} method.
     *
     * <pre>
     * If the byte index passed as an argument is not within the range specified by from and to, any exception object passed as an argument will be thrown.
     * <code>
     * byte index = 10;
     * byte from = 0;
     * byte to = 9;
     * Preconditions.requireRange(index, from, to, new AnyRuntimeException());
     * &gt;&gt; AnyRuntimeException
     * </code>
     * </pre>
     *
     * <pre>
     * If the byte index passed as an argument is within the range of from and to, it does nothing and ends the validation process.
     * <code>
     * byte index = 9;
     * byte from = 0;
     * byte to = 10;
     * Preconditions.requireRange(index, from, to, new AnyRuntimeException());
     * </code>
     * </pre>
     *
     * @param index     The byte index to be validated
     * @param from      The upper limit
     * @param to        The lower limit
     * @param exception Any exception object that is thrown if the preconditions are
     *                  not met
     *
     * @exception NullPointerException      If the exception object passed as an
     *                                      argument is {@code null}
     * @exception IndexOutOfBoundsException If the byte number of the {@code index}
     *                                      argument does not fall within the range
     *                                      specified by {@code from} to {@code to}
     */
    public static void requireRange(byte index, byte from, byte to, RuntimeException exception) {
        requireNonNull(exception);

        if (index < from || to < index) {
            throw exception;
        }
    }

    /**
     * Ensures that the float {@code index} argument is within the range specified
     * by {@code from} to {@code to} . 
     *
     * <p>
     * If the {@code index} argument is an out-of-range number,
     * {@link IndexOutOfBoundsException} is always raised at runtime.
     *
     * <p>
     * To specify an arbitrary exception object, use the
     * {@link #requireRange(float, float, float, RuntimeException)} method.
     *
     * <pre>
     * IndexOutOfBoundsException will be thrown if the float index passed as an argument is not within the range specified by from and to.
     * <code>
     * Preconditions.requireRange(10.0f, 0.0f, 9.0f);
     * &gt;&gt; IndexOutOfBoundsException
     * </code>
     * </pre>
     *
     * <pre>
     * If the float index passed as an argument is within the range of from and to, it does nothing and ends the validation process.
     * <code>
     * Preconditions.requireRange(9.0f, 0.0f, 10.0f);
     * </code>
     * </pre>
     *
     * @param index The float index to be validated
     * @param from  The lower limit
     * @param to    The upper limit
     *
     * @throws IndexOutOfBoundsException If the float number of the {@code index}
     *                                   argument does not fall within the range
     *                                   specified by {@code from} to {@code to}
     */
    public static void requireRange(float index, float from, float to) {
        requireRange(index, from, to, new IndexOutOfBoundsException(
                String.format("Float index %s out-of-bounds for range from length %s to length %s", index, from, to)));
    }

    /**
     * Ensures that the float {@code index} argument is within the range specified
     * by {@code from} to {@code to} . 
     *
     * <p>
     * If the {@code index} argument is an out-of-range number,
     * {@link IndexOutOfBoundsException} will always be raised at runtime. The
     * {@code message} passed as an argument is output as a detailed message when an
     * exception occurs.
     *
     * <p>
     * To specify an arbitrary exception object, use the
     * {@link #requireRange(float, float, float, RuntimeException)} method.
     *
     * <pre>
     * IndexOutOfBoundsException will be thrown if the float index passed as an argument is not within the range specified by from and to.
     * A message passed as an argument is output as a detailed message when an exception occurs.
     * <code>
     * Preconditions.requireRange(10.0f, 0.0f, 9.0f, "any message");
     * &gt;&gt; IndexOutOfBoundsException
     * </code>
     * </pre>
     *
     * <pre>
     * If the float index passed as an argument is within the range of from and to, it does nothing and ends the validation process.
     * <code>
     * Preconditions.requireRange(9.0f, 0.0f, 10.0f, "any message");
     * </code>
     * </pre>
     *
     * @param index   The float index to be validated
     * @param from    The lower limit
     * @param to      The upper limit
     * @param message Detailed messages to be output on exception throwing
     *
     * @throws IndexOutOfBoundsException If the float number of the {@code index}
     *                                   argument does not fall within the range
     *                                   specified by {@code from} to {@code to}
     */
    public static void requireRange(float index, float from, float to, String message) {
        requireRange(index, from, to, new IndexOutOfBoundsException(message));
    }

    /**
     * Ensures that the float {@code index} argument is within the range specified
     * by {@code from} to {@code to} . 
     *
     * <p>
     * Throws an arbitrary exception object if the argument {@code index} is an
     * out-of-range number. Execute from the
     * {@link #requireRange(float, float, float)} method, and throw
     * {@link IndexOutOfBoundsException} as an exception object if the {@code index}
     * argument is not a number within the range specified by {@code from} to
     * {@code to} .
     *
     * <p>
     * If you do not specify an arbitrary exception object, use the
     * {@link #requireRange(float, float, float)} method.
     *
     * <pre>
     * If the float index passed as an argument is not within the range specified by from and to, any exception object passed as an argument will be thrown.
     * <code>
     * Preconditions.requireRange(10.0f, 0.0f, 9.0f, new AnyRuntimeException());
     * &gt;&gt; AnyRuntimeException
     * </code>
     * </pre>
     *
     * <pre>
     * If the float index passed as an argument is within the range of from and to, it does nothing and ends the validation process.
     * <code>
     * Preconditions.requireRange(9.0f, 0.0f, 10.0f, new AnyRuntimeException());
     * </code>
     * </pre>
     *
     * @param index     The float index to be validated
     * @param from      The upper limit
     * @param to        The lower limit
     * @param exception Any exception object that is thrown if the preconditions are
     *                  not met
     *
     * @exception NullPointerException      If the exception object passed as an
     *                                      argument is {@code null}
     * @exception IndexOutOfBoundsException If the float number of the {@code index}
     *                                      argument does not fall within the range
     *                                      specified by {@code from} to {@code to}
     */
    public static void requireRange(float index, float from, float to, RuntimeException exception) {
        requireNonNull(exception);

        if (index < from || to < index) {
            throw exception;
        }
    }

    /**
     * Ensures that the double {@code index} argument is within the range specified
     * by {@code from} to {@code to} .  
     * <p>
     * If the {@code index} argument is an out-of-range number,
     * {@link IndexOutOfBoundsException} is always raised at runtime.
     *
     * <p>
     * To specify an arbitrary exception object, use the
     * {@link #requireRange(double, double, double, RuntimeException)} method.
     *
     * <pre>
     * IndexOutOfBoundsException will be thrown if the double index passed as an argument is not within the range specified by from and to.
     * <code>
     * Preconditions.requireRange(10.0d, 0.0d, 9.0d);
     * &gt;&gt; IndexOutOfBoundsException
     * </code>
     * </pre>
     *
     * <pre>
     * If the double index passed as an argument is within the range of from and to, it does nothing and ends the validation process.
     * <code>
     * Preconditions.requireRange(9.0d, 0.0d, 10.0d);
     * </code>
     * </pre>
     *
     * @param index The double index to be validated
     * @param from  The lower limit
     * @param to    The upper limit
     *
     * @throws IndexOutOfBoundsException If the double number of the {@code index}
     *                                   argument does not fall within the range
     *                                   specified by {@code from} to {@code to}
     */
    public static void requireRange(double index, double from, double to) {
        requireRange(index, from, to, new IndexOutOfBoundsException(
                String.format("Double index %s out-of-bounds for range from length %s to length %s", index, from, to)));
    }

    /**
     * Ensures that the double {@code index} argument is within the range specified
     * by {@code from} to {@code to} . 
     *
     * <p>
     * If the {@code index} argument is an out-of-range number,
     * {@link IndexOutOfBoundsException} will always be raised at runtime. The
     * {@code message} passed as an argument is output as a detailed message when an
     * exception occurs.
     *
     * <p>
     * To specify an arbitrary exception object, use the
     * {@link #requireRange(double, double, double, RuntimeException)} method.
     *
     * <pre>
     * IndexOutOfBoundsException will be thrown if the double index passed as an argument is not within the range specified by from and to.
     * A message passed as an argument is output as a detailed message when an exception occurs.
     * <code>
     * Preconditions.requireRange(10.0d, 0.0d, 9.0d, "any message");
     * &gt;&gt; IndexOutOfBoundsException
     * </code>
     * </pre>
     *
     * <pre>
     * If the double index passed as an argument is within the range of from and to, it does nothing and ends the validation process.
     * <code>
     * Preconditions.requireRange(9.0d, 0.0d, 10.0d, "any message");
     * </code>
     * </pre>
     *
     * @param index   The double index to be validated
     * @param from    The lower limit
     * @param to      The upper limit
     * @param message Detailed messages to be output on exception throwing
     *
     * @throws IndexOutOfBoundsException If the double number of the {@code index}
     *                                   argument does not fall within the range
     *                                   specified by {@code from} to {@code to}
     */
    public static void requireRange(double index, double from, double to, String message) {
        requireRange(index, from, to, new IndexOutOfBoundsException(message));
    }

    /**
     * Ensures that the double {@code index} argument is within the range specified
     * by {@code from} to {@code to} . 
     *
     * <p>
     * Throws an arbitrary exception object if the argument {@code index} is an
     * out-of-range number. Execute from the
     * {@link #requireRange(double, double, double)} method, and throw
     * {@link IndexOutOfBoundsException} as an exception object if the {@code index}
     * argument is not a number within the range specified by {@code from} to
     * {@code to} .
     *
     * <p>
     * If you do not specify an arbitrary exception object, use the
     * {@link #requireRange(double, double, double)} method.
     *
     * <pre>
     * If the double index passed as an argument is not within the range specified by from and to, any exception object passed as an argument will be thrown.
     * <code>
     * Preconditions.requireRange(10.0d, 0.0d, 9.0d, new AnyRuntimeException());
     * &gt;&gt; AnyRuntimeException
     * </code>
     * </pre>
     *
     * <pre>
     * If the double index passed as an argument is within the range of from and to, it does nothing and ends the validation process.
     * <code>
     * Preconditions.requireRange(9.0d, 0.0d, 10.0d, new AnyRuntimeException());
     * </code>
     * </pre>
     *
     * @param index     The double index to be validated
     * @param from      The upper limit
     * @param to        The lower limit
     * @param exception Any exception object that is thrown if the preconditions are
     *                  not met
     *
     * @exception NullPointerException      If the exception object passed as an
     *                                      argument is {@code null}
     * @exception IndexOutOfBoundsException If the double number of the
     *                                      {@code index} argument does not fall
     *                                      within the range specified by
     *                                      {@code from} to {@code to}
     */
    public static void requireRange(double index, double from, double to, RuntimeException exception) {
        requireNonNull(exception);

        if (index < from || to < index) {
            throw exception;
        }
    }

    /**
     * Ensures that {@code list} passed as an argument is not {@code null} or an
     * empty list. 
     *
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
     * PreconditionFailedException will be thrown if the list passed as an argument is an empty list.
     * <code>
     * Preconditions.requireNonEmpty(List.of());
     * &gt;&gt; PreconditionFailedException
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
     * @exception NullPointerException        If {@code list} is passed as an
     *                                        argument and {@code null} is passed
     * @exception PreconditionFailedException If {@code list} passed as an argument
     *                                        is an empty list
     */
    public static void requireNonEmpty(List<?> list) {
        requireNonEmpty(list, new PreconditionFailedException("List must contain at least one or more elements"));
    }

    /**
     * Ensures that {@code list} passed as an argument is not {@code null} or an
     * empty list. 
     *
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
     * PreconditionFailedException is thrown if the list argument is an empty list.
     * The message argument is output as a detailed message when an exception occurs.
     * <code>
     * Preconditions.requireNonEmpty(List.of(), "any message");
     * &gt;&gt; PreconditionFailedException
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
     * @exception NullPointerException        If {@code list} is passed as an
     *                                        argument and {@code null} is passed
     * @exception PreconditionFailedException If {@code list} passed as an argument
     *                                        is an empty list
     */
    public static void requireNonEmpty(List<?> list, String message) {
        requireNonEmpty(list, new PreconditionFailedException(message));
    }

    /**
     * Ensures that {@code list} given as an argument is not {@code null} or an
     * empty list. 
     *
     * <p>
     * If {@code list} is an empty list, throw any exception object passed as an
     * argument. If it is executed by the {@link #requireNonEmpty(List)} method and
     * {@code list} is an empty list, it throws {@link PreconditionFailedException}
     * as an exception object.
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
     * @exception NullPointerException        If {@code list} passed as an argument
     *                                        is {@code null} or if the exception
     *                                        object passed as an argument is
     *                                        {@code null}
     * @exception PreconditionFailedException If it is executed by the
     *                                        {@link #requireNonEmpty(List)} method
     *                                        and the {@code list} passed as an
     *                                        argument is an empty list
     */
    public static void requireNonEmpty(List<?> list, RuntimeException exception) {
        requireNonNull(list);
        requireNonNull(exception);

        if (list.isEmpty()) {
            throw exception;
        }
    }

    /**
     * Ensures that {@code map} passed as an argument is not {@code null} or an
     * empty map. 
     *
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
     * PreconditionFailedException will be thrown if the map passed as an argument is an empty map.
     * <code>
     * Preconditions.requireNonEmpty(Map.of());
     * &gt;&gt; PreconditionFailedException
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
     * @throws PreconditionFailedException If the {@code map} passed as an argument
     *                                     does not contain any elements
     */
    public static void requireNonEmpty(Map<?, ?> map) {
        requireNonEmpty(map, new PreconditionFailedException("Map must contain at least one or more elements"));
    }

    /**
     * Ensures that {@code map} passed as an argument is not {@code null} or an
     * empty map. 
     *
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
     * If the map argument is an empty map, PreconditionFailedException will be thrown.
     * The message passed as an argument is output as a detailed message when an exception occurs.
     * <code>
     * Preconditions.requireNonEmpty(Map.of(), "any message");
     * &gt;&gt; PreconditionFailedException
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
     * @throws PreconditionFailedException If the {@code map} passed as an argument
     *                                     does not contain any elements
     */
    public static void requireNonEmpty(Map<?, ?> map, String message) {
        requireNonEmpty(map, new PreconditionFailedException(message));
    }

    /**
     * Ensures that {@code map} passed as an argument is not {@code null} or an
     * empty map. 
     *
     * <p>
     * If {@code map} is an empty map, any exception object passed as an argument
     * will be returned. Executed by the {@link #requireNonEmpty(Map)} method, if
     * the {@code map} passed as argument is an empty map, throws
     * {@link PreconditionFailedException} as an exception object.
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
     * @exception NullPointerException        If {@code map} passed as an argument
     *                                        is {@code null} , or if any exception
     *                                        object passed as an argument is
     *                                        {@code null}
     * @exception PreconditionFailedException If the {@code map} argument is an
     *                                        empty map
     */
    public static void requireNonEmpty(Map<?, ?> map, RuntimeException exception) {
        requireNonNull(map);
        requireNonNull(exception);

        if (map.isEmpty()) {
            throw exception;
        }
    }

    /**
     * Ensures that {@code set} passed as an argument is not {@code null} or an
     * empty set. 
     *
     * <p>
     * To specify an arbitrary exception object, use the
     * {@link #requireNonEmpty(Set, RuntimeException)} method.
     *
     * <pre>
     * If the set argument is null, NullPointerException will be thrown.
     * <code>
     * Preconditions.requireNonEmpty(null);
     * &gt;&gt; NullPointerException
     * </code>
     * </pre>
     *
     * <pre>
     * PreconditionFailedException will be thrown if the set passed as an argument is an empty set.
     * <code>
     * Preconditions.requireNonEmpty(Set.of());
     * &gt;&gt; PreconditionFailedException
     * </code>
     * </pre>
     *
     * <pre>
     * If the set passed as an argument is not null and is not an empty set, the validation process is ended without doing anything.
     * <code>
     * Preconditions.requireNonEmpty(Set.of("test"));
     * </code>
     * </pre>
     *
     * @param set The set to be validated
     *
     * @throws PreconditionFailedException If the {@code set} passed as an argument
     *                                     does not contain any elements
     */
    public static void requireNonEmpty(Set<?> set) {
        requireNonEmpty(set, new PreconditionFailedException("Set must contain at least one or more elements"));
    }

    /**
     * Ensures that {@code set} passed as an argument is not {@code null} or an
     * empty set. 
     *
     * <p>
     * To specify an arbitrary exception object, use the
     * {@link #requireNonEmpty(Set, RuntimeException)} method. The {@code message}
     * passed as an argument will be printed as a detailed message when an exception
     * is thrown.
     *
     * <pre>
     * If the set argument is null, NullPointerException will be thrown.
     * A message passed as an argument will be printed as a detailed message if an exception is raised.
     * <code>
     * Preconditions.requireNonEmpty(null, "any message");
     * &gt;&gt; NullPointerException
     * </code>
     * </pre>
     *
     * <pre>
     * If the set argument is an empty set, PreconditionFailedException will be thrown.
     * The message passed as an argument is output as a detailed message when an exception occurs.
     * <code>
     * Preconditions.requireNonEmpty(Set.of(), "any message");
     * &gt;&gt; PreconditionFailedException
     * </code>
     * </pre>
     *
     * <pre>
     * If the set passed as an argument is not null and is not an empty set, the validation process is ended without doing anything.
     * <code>
     * Preconditions.requireNonEmpty(Set.of("test"), "any message");
     * </code>
     * </pre>
     *
     * @param set     The set to be validated
     * @param message Detailed messages to be output on exception throwing
     *
     * @throws PreconditionFailedException If the {@code set} passed as an argument
     *                                     does not contain any elements
     */
    public static void requireNonEmpty(Set<?> set, String message) {
        requireNonEmpty(set, new PreconditionFailedException(message));
    }

    /**
     * Ensures that {@code set} passed as an argument is not {@code null} or an
     * empty set. 
     *
     * <p>
     * If {@code set} is an empty set, any exception object passed as an argument
     * will be returned. Executed by the {@link #requireNonEmpty(Set)} method, if
     * the {@code set} passed as argument is an empty set, throws
     * {@link PreconditionFailedException} as an exception object.
     * <p>
     * If you do not specify an arbitrary exception object, use the
     * {@link #requireNonEmpty(Set)} method.
     *
     * <pre>
     * If the set argument is null, NullPointerException will be thrown.
     * <code>
     * Preconditions.requireNonEmpty(null, new AnyRuntimeException());
     * &gt;&gt; NullPointerException
     * </code>
     * </pre>
     *
     * <pre>
     * If the set argument is an empty set, any exception object passed as an argument will be thrown.
     * <code>
     * Preconditions.requireNonEmpty(Set.of(), new AnyRuntimeException());
     * &gt;&gt; AnyRuntimeException
     * </code>
     * </pre>
     *
     * <pre>
     * If the set passed as an argument is not null and is not an empty set, the validation process is ended without doing anything.
     * <code>
     * Preconditions.requireNonEmpty(Set.of("test"), new AnyRuntimeException());
     * </code>
     * </pre>
     *
     * @param set       The set to be validated
     * @param exception Any exception object that is thrown if the preconditions are
     *                  not met
     *
     * @exception NullPointerException        If {@code set} passed as an argument
     *                                        is {@code null} , or if any exception
     *                                        object passed as an argument is
     *                                        {@code null}
     * @exception PreconditionFailedException If the {@code set} argument is an
     *                                        empty set
     */
    public static void requireNonEmpty(Set<?> set, RuntimeException exception) {
        requireNonNull(set);
        requireNonNull(exception);

        if (set.isEmpty()) {
            throw exception;
        }
    }

    /**
     * Ensures that the array passed as an argument is not {@code null} or an empty
     * array. 
     *
     * <p>
     * Throws {@link PreconditionFailedException} if the argument is either
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
     * PreconditionFailedException will be thrown if the array argument is an empty array.
     * <code>
     * Preconditions.requireNonEmpty(new String[] {});
     * &gt;&gt; PreconditionFailedException
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
     * @exception NullPointerException        If the array passed as an argument is
     *                                        {@code null}
     * @exception PreconditionFailedException If the array passed as an argument is
     *                                        an empty array
     */
    public static void requireNonEmpty(Object[] array) {
        requireNonEmpty(Arrays.asList(array),
                new PreconditionFailedException("Array must contain at least one or more elements"));
    }

    /**
     * Ensures that the array passed as an argument is not {@code null} or an empty
     * array. 
     *
     * <p>
     * Throws {@link PreconditionFailedException} if the array argument is
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
     * PreconditionFailedException is thrown if the array argument is an empty array.
     * The message argument is output as a detailed message when an exception is raised.
     * <code>
     * Preconditions.requireNonEmpty(new String[] {}, "any message");
     * &gt;&gt; PreconditionFailedException
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
     * @exception NullPointerException        If the array passed as an argument is
     *                                        {@code null}
     * @exception PreconditionFailedException If the array passed as an argument is
     *                                        an empty array
     */
    public static void requireNonEmpty(Object[] array, String message) {
        requireNonEmpty(Arrays.asList(array), new PreconditionFailedException(message));
    }

    /**
     * Ensures that the array passed as a number is not {@code null} or an empty
     * array. 
     *
     * <p>
     * If the array passed as an argument is an empty array, throw an arbitrary
     * exception object passed as an argument. Execute a
     * {@link #requireNonEmpty(Object[])} method and then throw
     * {@link PreconditionFailedException} if the array passed in as argument is an
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
     * @exception NullPointerException        If the array passed as an argument is
     *                                        {@code null} or if the exception
     *                                        object passed as an argument is
     *                                        {@code null}
     * @exception PreconditionFailedException If the method
     *                                        {@link #requireNonEmpty(Object[])} is
     *                                        executed and the array passed as an
     *                                        argument is an empty array
     */
    public static void requireNonEmpty(Object[] array, RuntimeException exception) {
        requireNonEmpty(Arrays.asList(array), exception);
    }

    /**
     * Ensures that a given string begins with the prefix specified by
     * {@code prefix} .
     *
     * <p>
     * If the argument does not begin with a prefix specified by {@code prefix},
     * {@link PreconditionFailedException} will be thrown as an exception object at
     * runtime.
     *
     * <p>
     * To specify an arbitrary exception object, use the
     * {@link #requireStartWith(String, String, RuntimeException)} method.
     *
     * <pre>
     * If the string specified as an argument does not begin with a prefix, PreconditionFailedException will be thrown.
     * <code>
     * Preconditions.requireStartWith("test", "est");
     * &gt;&gt; PreconditionFailedException
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
     * @exception PreconditionFailedException If a string passed as an argument does
     *                                        not start with a prefix specified by
     *                                        {@code prefix}
     */
    public static void requireStartWith(String string, String prefix) {
        requireStartWith(string, prefix, new PreconditionFailedException(
                String.format("String must start with the %s prefix, but %s was given", prefix, string)));
    }

    /**
     * Ensures that a given string begins with the prefix specified by
     * {@code prefix} .
     *
     * <p>
     * If the argument doesn't start with a prefix specified by {@code prefix}, then
     * {@link PreconditionFailedException} will be thrown at runtime as an exception
     * object. The {@code message} passed as an argument will be printed as a
     * detailed message when the exception occurs.
     *
     * <p>
     * To specify an arbitrary exception object, use the
     * {@link #requireStartWith(String, String, RuntimeException)} method.
     *
     * <pre>
     * If the string argument does not begin with the prefix prefix, PreconditionFailedException will be thrown.
     * A message passed as an argument will be printed as a detailed message when an exception occurs.
     * <code>
     * Preconditions.requireStartWith("test", "est", "any message");
     * &gt;&gt; PreconditionFailedException
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
     * @exception PreconditionFailedException If a string passed as an argument does
     *                                        not start with a prefix specified by
     *                                        {@code prefix}
     */
    public static void requireStartWith(String string, String prefix, String message) {
        requireStartWith(string, prefix, new PreconditionFailedException(message));
    }

    /**
     * Ensures that the argument starts with the prefix specified by {@code prefix}
     * at the specified search start point.
     *
     * <p>
     * If the argument does not begin with a prefix specified by {@code prefix},
     * {@link PreconditionFailedException} will be thrown as an exception object at
     * runtime.
     *
     * <p>
     * To specify an arbitrary exception object, use the
     * {@link #requireStartWith(String, String, int, RuntimeException)} method.
     *
     * <pre>
     * If the string specified as an argument does not begin at the search start position specified by offset and the prefix specified by prefix, PreconditionFailedException will be thrown.
     * <code>
     * Preconditions.requireStartWith("test", "st", 1);
     * &gt;&gt; PreconditionFailedException
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
     * @exception PreconditionFailedException If a string passed as an argument does
     *                                        not start with a prefix specified by
     *                                        {@code prefix} from the specified
     *                                        search start point
     */
    public static void requireStartWith(String string, String prefix, int offset) {
        requireStartWith(string, prefix, offset, new PreconditionFailedException(String.format(
                "String must start with the %s prefix from %s index, but %s was given", prefix, offset, string)));
    }

    /**
     * Ensures that the argument starts with the prefix specified by {@code prefix}
     * at the specified search start point.
     *
     * <p>
     * If the argument doesn't start with a prefix specified by {@code prefix}, then
     * {@link PreconditionFailedException} will be thrown at runtime as an exception
     * object. The {@code message} passed as an argument will be printed as a
     * detailed message when the exception occurs.
     *
     * <p>
     * To specify an arbitrary exception object, use the
     * {@link #requireStartWith(String, String, int, RuntimeException)} method.
     *
     * <pre>
     * If the string argument does not begin at the search start position specified by offset and the prefix specified by prefix, an PreconditionFailedException will be thrown.
     * A message passed as an argument will be printed as a detailed message when an exception occurs.
     * <code>
     * Preconditions.requireStartWith("test", "st", 1, "any message");
     * &gt;&gt; PreconditionFailedException
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
     * @exception PreconditionFailedException If a string passed as an argument does
     *                                        not begin with the prefix specified by
     *                                        {@code prefix} at the specified search
     *                                        start position
     */
    public static void requireStartWith(String string, String prefix, int offset, String message) {
        requireStartWith(string, prefix, offset, new PreconditionFailedException(message));
    }

    /**
     * Ensures that a given string begins with the prefix specified by
     * {@code prefix} .
     *
     * <p>
     * If a string specified as an argument does not begin with a prefix specified
     * by {@code prefix} , any exception object specified as an argument will be
     * thrown at runtime.
     *
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
    public static void requireStartWith(String string, String prefix, RuntimeException exception) {
        requireStartWith(string, prefix, 0, exception);
    }

    /**
     * Ensures that the argument starts with the prefix specified by {@code prefix}
     * at the specified search start point.
     *
     * <p>
     * If the string specified as an argument does not begin with a prefix specified
     * by {@code prefix}, any exception object passed as an argument is thrown.
     *
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
    public static void requireStartWith(String string, String prefix, int offset, RuntimeException exception) {
        requireNonNull(exception);

        if (!string.startsWith(prefix, offset)) {
            throw exception;
        }
    }

    /**
     * Ensures that the string specified as an argument ends with the suffix
     * specified by {@code suffix} .
     *
     * <p>
     * If the string specified as an argument does not end with the suffix specified
     * by {@code suffix} , then {@link PreconditionFailedException} is thrown as an
     * exception object at runtime.
     *
     * <p>
     * To specify an arbitrary exception object, use the
     * {@link #requireEndWith(String, String, RuntimeException)} method.
     *
     * <pre>
     * If the string specified as an argument does not end with the suffix specified by suffix, PreconditionFailedException will be thrown.
     * <code>
     * Preconditions.requireEndWith("test", "es");
     * &gt;&gt; PreconditionFailedException
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
     * @exception PreconditionFailedException If the string passed as an argument
     *                                        does not end with the suffix specified
     *                                        by {@code suffix}
     */
    public static void requireEndWith(String string, String suffix) {
        requireEndWith(string, suffix, new PreconditionFailedException(
                String.format("String must end with the %s suffix, but %s was given", suffix, string)));
    }

    /**
     * Ensures that the string specified as an argument ends with the suffix
     * specified by {@code suffix} .
     *
     * <p>
     * If the string passed as an argument does not end with the suffix specified by
     * {@code suffix}, {@link PreconditionFailedException} will be thrown as an
     * exception object at runtime. Any {@code message} passed as an argument will
     * be printed as a detail message when the exception occurs.
     *
     * <p>
     * To specify an arbitrary exception object, use the
     * {@link #requireEndWith(String, String, RuntimeException)} method.
     *
     * <pre>
     * If the string argument does not end with the suffix specified by suffix, PreconditionFailedException will be thrown.
     * A message passed as an argument will be printed as a detailed message if an exception is thrown.
     * <code>
     * Preconditions.requireEndWith("test", "es", "any message");
     * &gt;&gt; PreconditionFailedException
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
     * @exception PreconditionFailedException If the string passed as an argument
     *                                        does not end with the suffix specified
     *                                        by {@code suffix}
     */
    public static void requireEndWith(String string, String suffix, String message) {
        requireEndWith(string, suffix, new PreconditionFailedException(message));
    }

    /**
     * Ensures that the string specified as an argument ends with the suffix
     * specified by {@code suffix} .
     *
     * <p>
     * If the string specified as an argument does not end with the suffix specified
     * by {@code suffix} , then any exception object specified as an argument will
     * be thrown at runtime.
     *
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
    public static void requireEndWith(String string, String suffix, RuntimeException exception) {
        requireNonNull(exception);

        if (!string.endsWith(suffix)) {
            throw exception;
        }
    }

    /**
     * Ensures that the boolean value given as an argument is {@code true} .
     *
     * <p>
     * To specify an arbitrary exception object, use the
     * {@link #requireTrue(boolean, RuntimeException)} method.
     *
     * <pre>
     * If the boolean is false, then {@link PreconditionFailedException} is thrown.
     * <code>
     * Preconditions.requireTrue(false);
     * &gt;&gt; PreconditionFailedException
     * </code>
     * </pre>
     *
     * <pre>
     * If the boolean is true, the function does nothing and ends the validation process.
     * <code>
     * Preconditions.requireTrue(true);
     * </code>
     * </pre>
     *
     * @param bool The value to be validated
     *
     * @exception PreconditionFailedException If the boolean value given as an
     *                                        argument is {@code false}
     */
    public static void requireTrue(boolean bool) {
        requireTrue(bool, new PreconditionFailedException("Boolean must be true, but false was given"));
    }

    /**
     * Ensures that the boolean value given as an argument is {@code true} .
     *
     * <p>
     * If the boolean passed as an argument is {@code false} ,
     * {@link PreconditionFailedException} will be thrown as an exception object at
     * runtime. Any {@code message} passed as an argument will be printed as a
     * detail message when the exception occurs.
     *
     * <p>
     * To specify an arbitrary exception object, use the
     * {@link #requireTrue(boolean, RuntimeException)} method.
     *
     * <pre>
     * If the boolean is false, then {@link PreconditionFailedException} is thrown.
     * <code>
     * Preconditions.requireTrue(false, "any message");
     * &gt;&gt; PreconditionFailedException
     * </code>
     * </pre>
     *
     * <pre>
     * If the boolean is true, the function does nothing and ends the validation process.
     * <code>
     * Preconditions.requireTrue(true, "any message");
     * </code>
     * </pre>
     *
     * @param bool    The value to be validated
     * @param message Detailed messages to be output on exception throwing
     *
     * @exception PreconditionFailedException If the boolean value given as an
     *                                        argument is {@code false}
     */
    public static void requireTrue(boolean bool, String message) {
        requireTrue(bool, new PreconditionFailedException(message));
    }

    /**
     * Ensures that the boolean value given as an argument is {@code true} .
     *
     * <p>
     * If the boolean specified as an argument is {@code false} , then any exception
     * object specified as an argument will be thrown at runtime.
     *
     * <p>
     * Use {@link #requireTrue(boolean)} method if you do not specify any exception
     * objects.
     *
     * <pre>
     * If the boolean is false, then any exception object specified as an argument thrown.
     * <code>
     * Preconditions.requireTrue(false, new AnyRuntimeException());
     * &gt;&gt; AnyRuntimeException
     * </code>
     * </pre>
     *
     * <pre>
     * If the boolean is true, the function does nothing and ends the validation process.
     * <code>
     * Preconditions.requireTrue(true, new AnyRuntimeException());
     * </code>
     * </pre>
     *
     * @param bool      The value to be validated
     * @param exception Any exception object that is thrown if the preconditions are
     *                  not met
     *
     * @exception NullPointerException If any exception object specified as an
     *                                 argument is {@code null}
     */
    public static void requireTrue(boolean bool, RuntimeException exception) {
        requireNonNull(exception);

        if (!bool) {
            throw exception;
        }
    }

    /**
     * Ensures that the boolean value given as an argument is {@code false} .
     *
     * <p>
     * To specify an arbitrary exception object, use the
     * {@link #requireFalse(boolean, RuntimeException)} method.
     *
     * <pre>
     * If the boolean is true, then {@link PreconditionFailedException} is thrown.
     * <code>
     * Preconditions.requireFalse(true);
     * &gt;&gt; PreconditionFailedException
     * </code>
     * </pre>
     *
     * <pre>
     * If the boolean is false, the function does nothing and ends the validation process.
     * <code>
     * Preconditions.requireFalse(false);
     * </code>
     * </pre>
     *
     * @param bool The value to be validated
     *
     * @exception PreconditionFailedException If the boolean value given as an
     *                                        argument is {@code true}
     */
    public static void requireFalse(boolean bool) {
        requireFalse(bool, new PreconditionFailedException("Boolean must be false, but true was given"));
    }

    /**
     * Ensures that the boolean value given as an argument is {@code false} .
     *
     * <p>
     * If the boolean passed as an argument is {@code true} ,
     * {@link PreconditionFailedException} will be thrown as an exception object at
     * runtime. Any {@code message} passed as an argument will be printed as a
     * detail message when the exception occurs.
     *
     * <p>
     * To specify an arbitrary exception object, use the
     * {@link #requireFalse(boolean, RuntimeException)} method.
     *
     * <pre>
     * If the boolean is true, then {@link PreconditionFailedException} is thrown.
     * <code>
     * Preconditions.requireFalse(true, "any message");
     * &gt;&gt; PreconditionFailedException
     * </code>
     * </pre>
     *
     * <pre>
     * If the boolean is false, the function does nothing and ends the validation process.
     * <code>
     * Preconditions.requireFalse(false, "any message");
     * </code>
     * </pre>
     *
     * @param bool    The value to be validated
     * @param message Detailed messages to be output on exception throwing
     *
     * @exception PreconditionFailedException If the boolean value given as an
     *                                        argument is {@code true}
     */
    public static void requireFalse(boolean bool, String message) {
        requireFalse(bool, new PreconditionFailedException(message));
    }

    /**
     * Ensures that the boolean value given as an argument is {@code false} .
     *
     * <p>
     * If the boolean specified as an argument is {@code true} , then any exception
     * object specified as an argument will be thrown at runtime.
     *
     * <p>
     * Use {@link #requireFalse(boolean)} method if you do not specify any exception
     * objects.
     *
     * <pre>
     * If the boolean is true, then any exception object specified as an argument is thrown.
     * <code>
     * Preconditions.requireFalse(true, new AnyRuntimeException());
     * &gt;&gt; AnyRuntimeException
     * </code>
     * </pre>
     *
     * <pre>
     * If the boolean is false, the function does nothing and ends the validation process.
     * <code>
     * Preconditions.requireFalse(false, new AnyRuntimeException());
     * </code>
     * </pre>
     *
     * @param bool      The value to be validated
     * @param exception Any exception object that is thrown if the preconditions are
     *                  not met
     *
     * @exception NullPointerException If any exception object specified as an
     *                                 argument is {@code null}
     */
    public static void requireFalse(boolean bool, RuntimeException exception) {
        requireNonNull(exception);

        if (bool) {
            throw exception;
        }
    }
}
