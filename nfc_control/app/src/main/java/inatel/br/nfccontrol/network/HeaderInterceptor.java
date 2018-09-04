package inatel.br.nfccontrol.network;

import inatel.br.nfccontrol.BuildConfig;
import inatel.br.nfccontrol.di.Injector;
import inatel.br.nfccontrol.utils.Logger;
import inatel.br.nfccontrol.utils.SecurityHelper;
import java.io.IOException;
import javax.inject.Inject;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Create interceptor to add to http requests header.
 */
public class HeaderInterceptor implements Interceptor {

  private static final String TAG = Logger.getTag();

  private static final String HTTP_HEADER_AUTHORIZATION = "Authorization";

  private static final String HTTP_AUTHORIZATION_PREFIX = "Bearer ";

  private static final String HTTP_BASIC_AUTHORIZATION_PREFIX = "Basic ";

  @Inject
  SecurityHelper mSecurityHelper;

  public HeaderInterceptor() {
    Injector.getApplicationComponent().inject(this);
  }

  @Override
  public Response intercept(Chain chain) throws IOException {
    Request request = chain.request();

    String savedToken = mSecurityHelper.getApplicationApiToken();

    /*if (savedToken != null) {
      request = request.newBuilder().addHeader(HTTP_HEADER_AUTHORIZATION,
          HTTP_AUTHORIZATION_PREFIX + savedToken).build();
    } else {
      request = request.newBuilder().addHeader(HTTP_HEADER_AUTHORIZATION,
          HTTP_BASIC_AUTHORIZATION_PREFIX + BuildConfig.BASIC_AUTHORIZATION_HEADER_TOKEN)
          .build();
    }*/

    return chain.proceed(request);
  }
}
