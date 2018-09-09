package inatel.br.nfccontrol.di.qualifier;

import static java.lang.annotation.RetentionPolicy.RUNTIME;


import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import javax.inject.Qualifier;

/**
 * Interface used to keep refresh token to renew the api token.
 */
@Qualifier
@Documented
@Retention(RUNTIME)
public @interface RefreshTokenPreference {
}