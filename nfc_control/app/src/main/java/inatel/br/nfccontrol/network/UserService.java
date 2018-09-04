package inatel.br.nfccontrol.network;

import inatel.br.nfccontrol.data.model.User;
import inatel.br.nfccontrol.data.model.UserAuthentication;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Interface of the network requests to the restaurant end points.
 */
public interface UserService {

  @POST("employees/login")
  Observable<UserAuthentication> login(@Body User user);

  @GET("employees")
  Observable<User> getUsers();

  @GET("employees/{id}")
  Observable<User> getAuthenticatedUser(@Path("id") int id, @Query("access_token=") String access);
}
