/*
 * Copyright (C) 2018 Instituto Nacional de Telecomunicações
 *
 * All rights are reserved. Reproduction in whole or part is
 * prohibited without the written consent of the copyright owner.
 *
 */

package inatel.br.nfccontrol.di.qualifier;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import javax.inject.Qualifier;

/**
 * Qualifier to identify injections related to {@link retrofit2.Retrofit}.
 *
 * @author Guilherme Yabu <guilhermeyabu@inatel.br>
 * @since 30/01/2018.
 */
@Qualifier
@Documented
@Retention(RUNTIME)
public @interface RetrofitQualifier {
}