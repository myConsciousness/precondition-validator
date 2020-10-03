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
 * 前提条件を判定する処理を定義したインターフェースです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
public interface Preconditions {

    /**
     * 引数として渡された {@code object} オブジェクトの参照が {@code null} ではないことを保証します。 
     * <p>
     * {@code object} オブジェクトの参照が {@code null} である場合には {@link NullPointerException}
     * が必ず実行時に発生します。
     *
     * @param object 検査対象のオブジェクト
     *
     * @throws NullPointerException 引数として {@code null} が渡された場合
     */
    static void requireNonNull(Object object) {
        if (object == null) {
            throw new NullPointerException();
        }
    }

    /**
     * 引数として指定された {@code sequence} オブジェクトの文字列が空文字列ではないことを保証します。 
     * <p>
     * {@code sequence} オブジェクトの文字列が空文字列の場合は {@link IllegalSequenceFoundException}
     * が必ず実行時に発生します。
     * <p>
     * 引数として指定された {@code sequence} オブジェクトの参照が {@code null} である可能性がある場合は
     * {@link #requireNonEmpty(String)} メソッドを使用してください。
     *
     * @param sequence 検査対象の文字列
     *
     * @throws NullPointerException          引数として {@code null} が渡された場合
     * @throws IllegalSequenceFoundException 引数として空文字列が渡された場合
     */
    static void requireNonBlank(String sequence) {
        requireNonBlank(sequence, new IllegalSequenceFoundException("String must not be blank"));
    }

    /**
     * 引数として与えられた {@code sequence} が空文字列ではないことを保証します。 
     * <p>
     * 与えられた {@code sequence} の値が空文字列の場合は {@code exception} オブジェクトをスローします。
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
    static void requireNonBlank(String sequence, RuntimeException exception) {
        requireNonNull(exception);

        if (sequence.isEmpty()) {
            throw exception;
        }
    }

    /**
     * 引数として指定された {@code sequence} オブジェクトの参照が {@code null}
     * 、または文字列が空文字列ではないことを保証します。 
     * <p>
     * {@code sequence} オブジェクトの参照が {@code null} の場合は {@link NullPointerException}
     * が必ず実行時に発生します。
     * <p>
     * {@code sequence} オブジェクトの文字列が空文字列の場合は {@link IllegalSequenceFoundException}
     * が必ず実行時に発生します。
     * <p>
     * 任意の例外オブジェクトを指定する場合は {@link #requireNonEmpty(String, RuntimeException)}
     * メソッドを使用してください。
     *
     * @param sequence 検査対象の文字列
     *
     * @throws NullPointerException          引数として {@code null} が渡された場合
     * @throws IllegalSequenceFoundException 引数として空文字列が渡された場合
     */
    static void requireNonEmpty(String sequence) {
        requireNonNull(sequence);
        requireNonBlank(sequence);
    }

    /**
     * 引数として渡された {@code sequence} の値が {@code null} または空文字列ではないことを保証します。 
     * <p>
     * {@code null} または空文字列である場合は例外をスローします。
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
    static void requireNonEmpty(String sequence, RuntimeException exception) {
        requireNonNull(exception);
        requireNonNull(sequence);
        requireNonBlank(sequence, exception);
    }

    /**
     * 引数として指定された {@code number} の数値が正数であることを保証します。 
     * <p>
     * 引数として指定された {@code number} の数値が負数である場合は {@link IllegalNumberFoundException}
     * が必ず実行時に発生します。
     * <p>
     * 任意の例外オブジェクトを指定する場合は {@link #requirePositive(int, RuntimeException)}
     * メソッドを使用してください。
     *
     * @param number 検査対象の数値
     *
     * @throws IllegalNumberFoundException 引数として指定された {@code number} の数値が負数の場合
     */
    static void requirePositive(int number) {
        requirePositive(number,
                new IllegalNumberFoundException(String.format("Number must be positive but %s was given", number)));
    }

    /**
     * 引数として指定された {@code number} の数値が正数であることを保証します。 
     * <p>
     * 任意の例外オブジェクトを指定しない場合は {@link #requirePositive(int)} メソッドを使用してください。
     *
     * @param number    検査対象の数値
     * @param exception 前提条件を満たさなかった場合にスローされる任意の例外オブジェクト
     *
     * @exception NullPointerException        引数として渡された例外オブジェクトが {@code null} の場合
     * @exception IllegalNumberFoundException {@link #requirePositive(int)
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
     * 引数として指定された {@code number} が正数である場合は引数として指定された任意の例外オブジェクトをスローします。
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
     * 引数として指定された {@code index} が {@code to}
     * で指定された範囲内の数値ではない場合は、引数として指定された任意の例外オブジェクトをスローします。
     * {@link #requireRange(int, int)} メソッドから実行された場合は
     * {@link IndexOutOfBoundsException} を例外オブジェクトとしてスローします。
     * <p>
     * 任意の例外オブジェクトを指定しない場合は {@link #requireRange(int, int)} メソッドを使用してください。
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
     * 引数として指定された {@code index} が範囲外にある数値である場合は引数として指定された任意の例外オブジェクトをスローします。
     * {@link #requireRange(int, int, int)} メソッドから実行され、引数として指定された {@code index} が
     * {@code from} から {@code to} で指定された範囲内の数値ではない場合は
     * {@link IndexOutOfBoundsException} を例外オブジェクトとしてスローします。
     * <p>
     * 任意の例外オブジェクトを指定しない場合は {@link #requireRange(int, int, int)} メソッドを使用してください。
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
     * 引数として指定された {@code list} が {@code null} または空リストではないことを保証します。 
     * <p>
     * 引数として渡された {@code list} が空リストの場合は引数として渡された任意の例外オブジェクトをスローします。
     * {@link #requireNonEmpty(List)} メソッドから実行され 、引数として渡された {@code list} が空リストの場合は
     * {@link IllegalArrayFoundException} を例外オブジェクトとしてスローします。
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
     * @param list 検査対象のリスト
     *
     * @throws IllegalMapFoundException 引数として渡された {@code list} に要素が含まれていない場合
     */
    static void requireNonEmpty(Map<?, ?> map) {
        requireNonEmpty(map, new IllegalMapFoundException("Map must contain at least one or more elements"));
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
     * @param map       検査対象のマップ
     * @param exception 前提条件を満たさなかった場合にスローされる任意の例外オブジェクト
     *
     * @exception NullPointerException     引数として渡された {@code map} が
     *                                     {@null} の場合、または引数として渡された任意の例外オブジェクトが
     *                                     {@code null} の場合
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
     * @param 検査対象の配列
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
     * 引数として渡された配列が空配列の場合は引数として渡された任意の例外オブジェクトをスローします。
     * {@link #requireNonEmpty(Object[])} メソッドから実行されて、引数として渡された配列が空配列の場合は
     * {@link IllegalArrayFoundException} を例外オブジェクトとしてスローします。
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
     * @param sequence 検査対象の文字列
     * @param prefix   接頭語
     *
     * @exception IllegalSequenceFoundException 引数として渡された文字列が {@code prefix}
     *                                          で指定された接頭語で始まらない場合
     */
    static void requireStartWith(String sequence, String prefix) {
        requireStartWith(sequence, prefix, new IllegalSequenceFoundException(
                String.format("String must start with the %s prefix, but %s was passed", prefix, sequence)));
    }

    /**
     * 引数として指定された文字列が {@code prefix} で指定された接頭語で始まることを保証します。
     * <p>
     * 引数として指定された文字列が {@code prefix} で指定された接頭語で始まらない場合は、実行時に
     * 引数として指定された任意の例外オブジェクトがスローされます。
     * <p>
     * 任意の例外オブジェクトを指定しない場合は {@link #requireStartWith(String, String)} メソッドを使用してください。
     *
     * @param sequence  検査対象の文字列
     * @param prefix    接頭語
     * @param exception 前提条件を満たしていなかった場合にスローされる任意の例外オブジェクト
     *
     * @exception NullPointerException 引数として指定された任意の例外オブジェクトが {@code null} の場合
     */
    static void requireStartWith(String sequence, String prefix, RuntimeException exception) {
        requireNonNull(exception);

        if (!sequence.startsWith(prefix)) {
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
     * @param sequence 検査対象の文字列
     * @param suffix   接尾語
     *
     * @exception IllegalSequenceFoundException 引数として渡された文字列が {@code suffix}
     *                                          で指定された接尾語で終わらない場合
     */
    static void requireEndWith(String sequence, String suffix) {
        requireEndWith(sequence, suffix, new IllegalSequenceFoundException(
                String.format("String must end with the %s prefix, but %s was passed", suffix, sequence)));
    }

    /**
     * 引数として指定された文字列が {@code suffix} で指定された接尾語で終わることを保証します。
     * <p>
     * 引数として指定された文字列が {@code suffix} で指定された接尾語で終わらない場合は、実行時に
     * 引数として指定された任意の例外オブジェクトがスローされます。
     * <p>
     * 任意の例外オブジェクトを指定する場合は {@link #requireEndWith(String, String)} メソッドを使用してください。
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