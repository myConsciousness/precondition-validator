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
     * specified by {@code to} . 
     * <p>
     * If the {@code index} argument is an out-of-range number,
     * {@link IndexOutOfBoundsException} will always be raised at runtime.
     * <p>
     * To specify an arbitrary exception object, use the
     * {@link #requireRange(int, int, RuntimeException)} method.
     *
     * <pre>
     * If the index passed as an argument is greater than the number specified by to, IndexOutOfBoundsException will be thrown.
     * <code>
     * Preconditions.requireRange(10, 9);
     * &gt;&gt; IndexOutOfBoundsException
     * </code>
     * </pre>
     *
     * <pre>
     * If the index passed as an argument is less than or equal to the value specified by "to", this function does nothing and exits the validation process.
     * <code>
     * Preconditions.requireRange(9, 10);
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
    static void requireRange(int index, int to) {
        requireRange(index, to, new IndexOutOfBoundsException(
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
     * {@link #requireRange(int, int, RuntimeException)} method.
     *
     * <pre>
     * If the index passed as an argument is greater than the number specified by to, IndexOutOfBoundsException will be thrown.
     * A message passed as an argument is output as a detailed message when an exception occurs.
     * <code>
     * Preconditions.requireRange(10, 9, "any message");
     * &gt;&gt; IndexOutOfBoundsException
     * </code>
     * </pre>
     *
     * <pre>
     * If the index passed as an argument is less than or equal to the value specified by "to", this function does nothing and exits the validation process.
     * <code>
     * Preconditions.requireRange(9, 10, "any message");
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
    static void requireRange(int index, int to, String message) {
        requireRange(index, to, new IndexOutOfBoundsException(message));
    }

    /**
     * Ensures that the {@code index} argument is a number within the range
     * specified by {@code to} . 
     * <p>
     * If {@code index} specified as an argument is not within the range specified
     * by {@code to} , throw any exception object specified as an argument.
     * <p>
     * If you do not specify an arbitrary exception object, use the
     * {@link #requireRange(int, int)} method.
     *
     * <pre>
     * If the index passed as an argument is greater than the number specified by to, any of the specified exceptions will be thrown.
     * <code>
     * Preconditions.requireRange(10, 9, new AnyRuntimeException());
     * &gt;&gt; AnyRuntimeException
     * </code>
     * </pre>
     *
     * <pre>
     * If the index passed as an argument is less than or equal to the value specified by "to", this function does nothing and exits the validation process.
     * <code>
     * Preconditions.requireRange(9, 10, new AnyRuntimeException());
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
    static void requireRange(int index, int to, RuntimeException exception) {
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
     * IllegalArrayFoundException will be thrown if the list passed as an argument is an empty list.
     * <code>
     * Preconditions.requireNonEmpty(List.of());
     * &gt;&gt; IllegalArrayFoundException
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
     * @exception NullPointerException       If {@code list} is passed as an
     *                                       argument and {@code null} is passed
     * @exception IllegalArrayFoundException If {@code list} passed as an argument
     *                                       is an empty list
     */
    static void requireNonEmpty(List<?> list) {
        requireNonEmpty(list, new IllegalArrayFoundException("List must contain at least one or more elements"));
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
     * IllegalArrayFoundException is thrown if the list argument is an empty list.
     * The message argument is output as a detailed message when an exception occurs.
     * <code>
     * Preconditions.requireNonEmpty(List.of(), "any message");
     * &gt;&gt; IllegalArrayFoundException
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
     * @exception NullPointerException       If {@code list} is passed as an
     *                                       argument and {@code null} is passed
     * @exception IllegalArrayFoundException If {@code list} passed as an argument
     *                                       is an empty list
     */
    static void requireNonEmpty(List<?> list, String message) {
        requireNonEmpty(list, new IllegalArrayFoundException(message));
    }

    /**
     * Ensures that {@code list} given as an argument is not {@code null} or an
     * empty list. 
     * <p>
     * If {@code list} is an empty list, throw any exception object passed as an
     * argument. If it is executed by the {@link #requireNonEmpty(List)} method and
     * {@code list} is an empty list, it throws {@link IllegalArrayFoundException}
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
     * @exception NullPointerException       If {@code list} passed as an argument
     *                                       is {@code null} or if the exception
     *                                       object passed as an argument is
     *                                       {@code null}
     * @exception IllegalArrayFoundException If it is executed by the
     *                                       {@link #requireNonEmpty(List)} method
     *                                       and the {@code list} passed as an
     *                                       argument is an empty list
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
        requireNonEmpty(Arrays.asList(array));
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
        requireNonEmpty(Arrays.asList(array), message);
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
     * {@link IllegalSequenceFoundException} will be thrown as an exception object
     * at runtime.
     * <p>
     * To specify an arbitrary exception object, use the
     * {@link #requireStartWith(String, String, RuntimeException)} method.
     *
     * <pre>
     * If the sequence specified as an argument does not begin with a prefix, IllegalSequenceFoundException will be thrown.
     * <code>
     * Preconditions.requireStartWith("test", "est");
     * &gt;&gt; IllegalSequenceFoundException
     * </code>
     * </pre>
     *
     * <pre>
     * If the sequence argument begins with a prefix, the function does nothing and ends the validation process.
     * <code>
     * Preconditions.requireStartWith("test", "test");
     * </code>
     * </pre>
     *
     * @param sequence The string to be validated
     * @param prefix   The prefix
     *
     * @exception IllegalSequenceFoundException If a string passed as an argument
     *                                          does not start with a prefix
     *                                          specified by {@code prefix}
     */
    static void requireStartWith(String sequence, String prefix) {
        requireStartWith(sequence, prefix, new IllegalSequenceFoundException(
                String.format("String must start with the %s prefix, but %s was given", prefix, sequence)));
    }

    /**
     * Ensures that a given string begins with the prefix specified by
     * {@code prefix} .
     * <p>
     * If the argument doesn't start with a prefix specified by {@code prefix}, then
     * {@link IllegalSequenceFoundException} will be thrown at runtime as an
     * exception object. The {@code message} passed as an argument will be printed
     * as a detailed message when the exception occurs.
     * <p>
     * To specify an arbitrary exception object, use the
     * {@link #requireStartWith(String, String, RuntimeException)} method.
     *
     * <pre>
     * If the sequence argument does not begin with the prefix prefix, IllegalSequenceFoundException will be thrown.
     * A message passed as an argument will be printed as a detailed message when an exception occurs.
     * <code>
     * Preconditions.requireStartWith("test", "est", "any message");
     * &gt;&gt; IllegalSequenceFoundException
     * </code>
     * </pre>
     *
     * <pre>
     * If the sequence argument begins with a prefix, the function does nothing and ends the validation process.
     * <code>
     * Preconditions.requireStartWith("test", "test", "any message");
     * </code>
     * </pre>
     *
     * @param sequence The string to be validated
     * @param prefix   The prefix
     * @param message  Detailed messages to be output on exception throwing
     *
     * @exception IllegalSequenceFoundException If a string passed as an argument
     *                                          does not start with a prefix
     *                                          specified by {@code prefix}
     */
    static void requireStartWith(String sequence, String prefix, String message) {
        requireStartWith(sequence, prefix, new IllegalSequenceFoundException(message));
    }

    /**
     * Ensures that the argument starts with the prefix specified by {@code prefix}
     * at the specified search start point.
     * <p>
     * If the argument does not begin with a prefix specified by {@code prefix},
     * {@link IllegalSequenceFoundException} will be thrown as an exception object
     * at runtime.
     * <p>
     * To specify an arbitrary exception object, use the
     * {@link #requireStartWith(String, String, int, RuntimeException)} method.
     *
     * <pre>
     * If the sequence specified as an argument does not begin at the search start position specified by offset and the prefix specified by prefix, IllegalSequenceFoundException will be thrown.
     * <code>
     * Preconditions.requireStartWith("test", "st", 1);
     * &gt;&gt; IllegalSequenceFoundException
     * </code>
     * </pre>
     *
     * <pre>
     * If the sequence specified as an argument begins with a prefix specified by prefix from the search start position specified by offset, the function does nothing and ends the validation process.
     * <code>
     * Preconditions.requireStartWith("test", "est", 1);
     * </code>
     * </pre>
     *
     * @param sequence The string to be validated
     * @param prefix   The prefix
     * @param offset   The offset
     *
     * @exception IllegalSequenceFoundException If a string passed as an argument
     *                                          does not start with a prefix
     *                                          specified by {@code prefix} from the
     *                                          specified search start point
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