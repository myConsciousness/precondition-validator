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

package org.thinkit.common.base.precondition.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

/**
 * {@link PreconditionFailedException} クラスのテストクラスです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
final class PreconditionFailedExceptionTest {

    /**
     * <pre>
     * ❏ 概要
     * {@link PreconditionFailedException} クラスのデフォルトコンストラクタの機能を確認する。
     * </pre>
     *
     * <pre>
     * ❏ 観点
     * ・{@link PreconditionFailedException} の生成されたインスタンスが {@code null} ではないこと。
     * </pre>
     *
     * <pre>
     * ❏ 留意点
     * なし
     * </pre>
     */
    @Test
    void testDefaultConstructor() {
        assertNotNull(new PreconditionFailedException());
    }

    /**
     * <pre>
     * ❏ 概要
     * {@link PreconditionFailedException} クラスのコンストラクタの機能を確認する。
     * インスタンス生成時には引数として任意の文字列を渡すこととする。
     * </pre>
     *
     * <pre>
     * ❏ 観点
     * ・{@link PreconditionFailedException} の生成されたインスタンスが {@code null} ではないこと。
     * ・{@link PreconditionFailedException#getMessage()} の返却値がインスタンス生成時に渡した文字列と等価であること。
     * </pre>
     *
     * <pre>
     * ❏ 留意点
     * なし
     * </pre>
     */
    @Test
    void testConstructorWithMessage() {
        final String message = "This is a test message.";
        final PreconditionFailedException exception = new PreconditionFailedException(message);

        assertNotNull(exception);
        assertEquals(message, exception.getMessage());
    }

    /**
     * <pre>
     * ❏ 概要
     * {@link LogicExeption} クラスのコンストラクタの機能を確認する。
     * インスタンス生成時には引数として任意の文字列と例外情報を渡すこととする。
     * </pre>
     *
     * <pre>
     * ❏ 観点
     * ・{@link PreconditionFailedException} の生成されたインスタンスが {@code null} ではないこと。
     * ・{@link PreconditionFailedException#getCause()} の返却値が {@code null} ではないこと。
     * ・{@link PreconditionFailedException#getMessage()} の返却値がインスタンス生成時に渡した文字列と等価であること。
     * </pre>
     *
     * <pre>
     * ❏ 留意点
     * なし
     * </pre>
     */
    @Test
    void testConstructorWithMessageAndException() {
        final String message = "This is a test message.";
        PreconditionFailedException exception = null;

        try {
            new ArrayList<>(0).get(1);
        } catch (IndexOutOfBoundsException e) {
            exception = new PreconditionFailedException(message, e);
        }

        assertNotNull(exception);
        assertNotNull(exception.getCause());
        assertEquals(message, exception.getMessage());
    }

    /**
     * <pre>
     * ❏ 概要
     * {@link LogicExeption} クラスのコンストラクタの機能を確認する。
     * インスタンス生成時には引数として任意の例外情報を渡すこととする。
     * </pre>
     *
     * <pre>
     * ❏ 観点
     * ・{@link PreconditionFailedException} の生成されたインスタンスが {@code null} ではないこと。
     * ・{@link PreconditionFailedException#getCause()} の返却値が {@code null} ではないこと。
     * </pre>
     *
     * <pre>
     * ❏ 留意点
     * なし
     * </pre>
     */
    @Test
    void testConstructorWithException() {

        PreconditionFailedException exception = null;

        try {
            new ArrayList<>(0).get(1);
        } catch (IndexOutOfBoundsException e) {
            exception = new PreconditionFailedException(e);
        }

        assertNotNull(exception);
        assertNotNull(exception.getCause());
    }
}