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

import java.util.List;
import java.util.Map;

/**
 * 前提条件を判定する処理を定義したインターフェースです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
public interface Precondition {

    /**
     * 引数として渡された {@code object} オブジェクトの参照が {@code null} であるか判定します。 {@code object}
     * オブジェクトの参照が {@code null} である場合には {@link NullPointerException} が必ず実行時に発生します。
     *
     * @param object {@code null} 判定対象のオブジェクト
     *
     * @throws NullPointerException 引数として {@code null} が渡された場合
     */
    static void requireNonNull(Object object) {
        if (object == null) {
            throw new NullPointerException();
        }
    }

    /**
     * 引数として指定された {@code sequence} オブジェクトの文字列が空文字列であるか判定します。 {@code sequence}
     * オブジェクトの文字列が空文字列の場合は {@link IllegalSequenceFoundException} が必ず実行時に発生します。
     * <p>
     * 引数として指定された {@code sequence} オブジェクトの参照が {@code null} である可能性がある場合は
     * {@link #requireNonEmpty(String)} メソッドを使用してください。
     *
     * @param sequence 判定対象の文字列
     *
     * @throws NullPointerException          引数として {@code null} が渡された場合
     * @throws IllegalSequenceFoundException 引数として空文字列が渡された場合
     */
    static void requireNonBlank(String sequence) {
        requireNonBlank(sequence, new IllegalSequenceFoundException("String must not be blank"));
    }

    /**
     * 引数として与えられた {@code sequence} が空文字列か判定します。与えられた {@code sequence} の値が空文字列の場合は
     * {@code exception} オブジェクトをスローします。
     * <p>
     * 任意の例外を指定しない場合は {@link #requireNonBlank(String)} メソッドを使用してください。
     *
     * @param sequence  検査対象の文字列
     * @param exception 前提条件を満たさなかった場合にスローされる任意の例外オブジェクト
     *
     * @exception NullPointerException          引数として渡された例外オブジェクトが {@code null} の場合
     * @exception IllegalSequenceFoundException {@link #requireNonBlank(String)}
     *                                          メソッドから実行され、引数として渡された
     *                                          {@code sequence} が空文字列の場合
     */
    static void requireNonBlank(String sequence, Throwable exception) {
        requireNonNull(exception);

        if (sequence.isEmpty()) {
            error(exception);
        }
    }

    /**
     * 引数として指定された {@code sequence} オブジェクトの参照が {@code null} 、または文字列が空文字列であるか判定します。
     * <p>
     * {@code sequence} オブジェクトの参照が {@code null} の場合は {@link NullPointerException}
     * が必ず実行時に発生します。
     * <p>
     * {@code sequence} オブジェクトの文字列が空文字列の場合は {@link IllegalSequenceFoundException}
     * が必ず実行時に発生します。
     * <p>
     * 任意の例外オブジェクトを指定する場合は {@link #requireNonEmpty(String, Throwable)}
     * メソッドを使用してください。
     *
     * @param sequence 判定対象の文字列
     *
     * @throws NullPointerException          引数として {@code null} が渡された場合
     * @throws IllegalSequenceFoundException 引数として空文字列が渡された場合
     */
    static void requireNonEmpty(String sequence) {
        requireNonNull(sequence);
        requireNonBlank(sequence);
    }

    /**
     * 引数として渡された {@code sequence} の値が {@code null} または空文字列であるか判定します。 {@code null}
     * または空文字列である場合は例外をスローします。
     * <p>
     * 任意の例外オブジェクトを指定しない場合は {@link #requireNonEmpty(String)} メソッドを使用してください。
     *
     * @param sequence  検査対象の文字列
     * @param exception 前提条件を満たさなかった場合にスローされる任意の例外オブジェクト
     *
     * @exception NullPointerException          引数として渡された例外オブジェクトが {@code null}
     *                                          の場合、または引数として渡された {@code sequence} が
     *                                          {@code null} の場合
     * @exception IllegalSequenceFoundException 引数として渡された {@code sequence} が空文字列の場合
     */
    static void requireNonEmpty(String sequence, Throwable exception) {
        requireNonNull(exception);
        requireNonNull(sequence);
        requireNonBlank(sequence, exception);
    }

    /**
     * 引数として指定された {@code number} の数値が正数であるか判定します。引数として指定された {@code number}
     * の数値が負数である場合は {@link IllegalNumberFoundException} が必ず実行時に発生します。
     * <p>
     * 任意の例外オブジェクトを指定する場合は {@link #requirePositive(int, Throwable)} メソッドを使用してください。
     *
     * @param number 判定対象の数値
     *
     * @throws IllegalNumberFoundException 引数として指定された {@code number} の数値が負数の場合
     */
    static void requirePositive(int number) {
        requirePositive(number,
                new IllegalNumberFoundException(String.format("Number must be positive but %s was given", number)));
    }

    /**
     * 引数として指定された {@code number} の数値が正数であるか判定します。
     * <p>
     * 任意の例外オブジェクトを指定しない場合は {@link #requirePositive(int)} メソッドを使用してください。
     *
     * @param number    判定対象の数値
     * @param exception 前提条件を満たさなかった場合にスローされる任意の例外オブジェクト
     *
     * @exception NullPointerException        引数として渡された例外オブジェクトが {@code null} の場合
     * @exception IllegalNumberFoundException {@link #requirePositive(int)
     *                                        メソッドから実行され、指定された {@code number}
     *                                        の数値が負数の場合
     */
    static void requirePositive(int number, Throwable exception) {
        requireNonNull(exception);

        if (number < 0) {
            error(exception);
        }
    }

    /**
     * 引数として指定された {@code number} の数値が負数であるか判定します。引数として指定された {@code number}
     * の数値が正数である場合は {@link IllegalNumberFoundException} が必ず実行時に発生します。
     * <p>
     * 任意の例外オブジェクトを指定する場合は {@link #requireNegative(int, Throwable)} メソッドを使用してください。
     *
     * @param number 判定対象の数値
     *
     * @throws IllegalNumberFoundException 引数として指定された {@code number} の数値が正数の場合
     */
    static void requireNegative(int number) {
        requireNegative(number,
                new IllegalNumberFoundException(String.format("Number must be negative but %s was given", number)));
    }

    /**
     * 引数として指定された {@code number} の数値が負数であるか判定します。引数として指定された {@code number}
     * が正数である場合は引数として指定された任意の例外オブジェクトをスローします。
     * <p>
     * 任意の例外オブジェクトを指定しない場合は {@link #requireNegative(int)} メソッドを使用してください。
     *
     * @param number    検査対象の数値
     * @param exception 前提条件を満たさなかった場合にスローされる任意の例外オブジェクト
     *
     * @exception NullPointerException        引数として渡された例外オブジェクトが {@code null} の場合
     * @exception IllegalNumberFoundException {@link #requireNegative(int)}
     *                                        メソッドから実行され、引数として渡された {@code number}
     *                                        の数値が正数の場合
     */
    static void requireNegative(int number, Throwable exception) {
        requireNonNull(exception);

        if (number < 0) {
            error(exception);
        }
    }

    /**
     * 引数として指定された {@code index} が {@code 0} から {@code to}
     * で指定された範囲内の数値か判定します。引数として指定された {@code index} が範囲外にある数値である場合は
     * {@link IndexOutOfBoundsException} が必ず実行時に発生します。
     * <p>
     * 任意の例外オブジェクトを指定する場合は {@link #requireRange(int, int, Throwable)} メソッドを使用してください。
     *
     * @param index 判定対象のインデックス
     * @param to    判定時の上限値
     *
     * @throws IndexOutOfBoundsException 引数として指定された {@code number} の数値が {@code 0} から
     *                                   {@code to} で指定された範囲内に存在しない場合
     */
    static void requireRange(int index, int to) {
        requireRange(index, to, new IndexOutOfBoundsException(
                String.format("Index %s out-of-bounds for range from length 0 to length %s", index, to)));
    }

    /**
     * 引数として指定された {@code index} が {@code 0} から {@code to}
     * で指定された範囲内の数値か判定します。引数として指定された {@code index} が {@code 0} から {@code to}
     * で指定された範囲内の数値ではない場合は、引数として指定された任意の例外オブジェクトをスローします。
     * {@link #requireRange(int, int)} メソッドから実行された場合は
     * {@link IndexOutOfBoundsException} を例外オブジェクトとしてスローします。
     * <p>
     * 任意の例外オブジェクトを指定しない場合は {@link #requireRange(int, int)} メソッドを使用してください。
     *
     * @param index     判定対象のインデックス
     * @param to        判定時の上限値
     * @param exception 前提条件を満たさなかった場合にスローされる任意の例外オブジェクト
     *
     * @exception NullPointerException      引数として渡された例外オブジェクトが {@code null} の場合
     * @exception IndexOutOfBoundsException {@link #requireRange(int, int)}
     *                                      メソッドから実行され、引数として指定された {@code number}
     *                                      の数値が {@code 0} から {@code to}
     *                                      で指定された範囲内に存在しない場合
     */
    static void requireRange(int index, int to, Throwable exception) {
        requireNonNull(exception);

        if (index < 0 || to < index) {
            error(exception);
        }
    }

    /**
     * 引数として指定された {@code index} が {@code from} から {@code to}
     * で指定された範囲内の数値か判定します。引数として指定された {@code index} が範囲外にある数値である場合は
     * {@link IndexOutOfBoundsException} が必ず実行時に発生します。
     * <p>
     * 任意の例外オブジェクトを指定する場合は {@link #requireRange(int, int, int, Throwable)}
     * メソッドを使用してください。
     *
     * @param index 判定対象のインデックス
     * @param from  判定時の最低値
     * @param to    判定時の上限値
     *
     * @throws IndexOutOfBoundsException 引数として指定された {@code number} の数値が {@code from}
     *                                   から {@code to} で指定された範囲内に存在しない場合
     */
    static void requireRange(int index, int from, int to) {
        requireRange(index, from, to, new IndexOutOfBoundsException(
                String.format("Index %s out-of-bounds for range from length %s to length %s", index, from, to)));
    }

    /**
     * 引数として指定された {@code index} が {@code from} から {@code to}
     * で指定された範囲内の数値か判定します。引数として指定された {@code index}
     * が範囲外にある数値である場合は引数として指定された任意の例外オブジェクトをスローします。
     * {@link #requireRange(int, int, int)} メソッドから実行され、引数として指定された {@code index} が
     * {@code from} から {@code to} で指定された範囲内の数値ではない場合は
     * {@link IndexOutOfBoundsException} を例外オブジェクトとしてスローします。
     * <p>
     * 任意の例外オブジェクトを指定しない場合は {@link #requireRange(int, int, int)} メソッドを使用してください。
     *
     * @param index     判定対象のインデックス
     * @param from      判定時の最低値
     * @param to        判定時の上限値
     * @param exception 前提条件を満たさなかった場合にスローされる任意の例外オブジェクト
     *
     * @exception NullPointerException      引数として渡された例外オブジェクトが {@code null} の場合
     * @exception IndexOutOfBoundsException 引数として指定された {@code number} の数値が
     *                                      {@code from} から {@code to}
     *                                      で指定された範囲内に存在しない場合
     */
    static void requireRange(int index, int from, int to, Throwable exception) {
        requireNonNull(exception);

        if (index < from || to < index) {
            error(exception);
        }
    }

    /**
     * 引数として渡された {@code list} が空か判定します。
     * <p>
     * この {@link #requireNonEmpty(List)} メソッドは {@link List#isEmpty()} メソッドでの判定前に
     * {@code null} の判定を行いません。
     *
     * @param list 判定対象のリスト
     *
     * @throws IllegalArrayFoundException 引数として渡された {@code list} に要素が含まれていない場合
     */
    static void requireNonEmpty(List<?> list) {
        if (list.isEmpty()) {
            throw new IllegalArrayFoundException("List must contain at least one or more elements");
        }
    }

    /**
     * 引数として渡された {@code map} が空か判定します。
     * <p>
     * この {@link #requireNonEmpty(Map)} メソッドは {@link Map#isEmpty()} メソッドでの判定前に
     * {@code null} の判定を行いません。
     *
     * @param list 判定対象のリスト
     *
     * @throws IllegalMapFoundException 引数として渡された {@code list} に要素が含まれていない場合
     */
    static void requireNonEmpty(Map<?, ?> map) {
        if (map.isEmpty()) {
            throw new IllegalMapFoundException("Map must contain at least one or more elements");
        }
    }

    /**
     * 引数として渡された {@code exception} の例外オブジェクトをスローします。
     * <p>
     * 前提条件を検査する各静的メソッドで前提条件を満たさない値を検知した際にこの {@link #error(Throwable)}
     * メソッドを使用してください。
     *
     * @param exception スローされる例外オブジェクト
     */
    private static void error(Throwable exception) {
        try {
            throw exception;
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}