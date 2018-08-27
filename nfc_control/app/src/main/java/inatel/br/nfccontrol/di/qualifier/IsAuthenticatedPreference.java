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
 * Interface used to keep the Shared preference test.
 *
 * @author Daniela Mara Silva <danielamara@inatel.br>
 * @since 30/01/2018.
 */
@Qualifier
@Documented
@Retention(RUNTIME)
public @interface IsAuthenticatedPreference {
}