package inatel.br.nfccontrol.network;

import inatel.br.nfccontrol.data.model.User;
import inatel.br.nfccontrol.data.model.UserAuthentication;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Interface of the network requests to the restaurant end points.
 */
public interface UserService {

  @POST("employees/login")
  Observable<UserAuthentication> login(@Body User user);

  @GET("employees")
  Observable<User> getUsers();
}
