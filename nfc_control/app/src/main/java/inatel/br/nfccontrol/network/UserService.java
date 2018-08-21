package inatel.br.nfccontrol.network;

import inatel.br.nfccontrol.data.model.User;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Interface of the network requests to the restaurant end points.
 */
public interface UserService {

  @GET("/user/{email}/{password}")
  Observable<User> login(@Path("email") String email, @Path("password") String password);
}
