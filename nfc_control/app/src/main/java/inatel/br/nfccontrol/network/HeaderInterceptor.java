package inatel.br.nfccontrol.network;

import java.io.IOException;

import inatel.br.nfccontrol.di.Injector;
import inatel.br.nfccontrol.utils.Logger;
import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * Create interceptor to add to http requests header.
 */
public class HeaderInterceptor implements Interceptor {

  private static final String TAG = Logger.getTag();

  private static final String HTTP_HEADER_AUTHORIZATION = "Authorization";

  private static final String HTTP_AUTHORIZATION_PREFIX = "Bearer ";

  private static final String HTTP_BASIC_AUTHORIZATION_PREFIX = "Basic ";


  public HeaderInterceptor() {
    Injector.getApplicationComponent().inject(this);
  }

  @Override
  public Response intercept(Chain chain) throws IOException {
    return chain.call().execute();
  }
}
