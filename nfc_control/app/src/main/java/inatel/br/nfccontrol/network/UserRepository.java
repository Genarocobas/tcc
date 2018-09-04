package inatel.br.nfccontrol.network;

import javax.inject.Inject;

import inatel.br.nfccontrol.data.model.User;
import inatel.br.nfccontrol.data.model.UserAuthentication;
import inatel.br.nfccontrol.di.qualifier.RetrofitQualifier;
import io.reactivex.Observable;
import retrofit2.Retrofit;

/**
 * Repository to connect with {@link UserService}.
 */
public class UserRepository {

  private UserService mService;

  @Inject
  public UserRepository(@RetrofitQualifier Retrofit retrofit) {
    mService = retrofit.create(UserService.class);
  }

  public Observable<UserAuthentication> login(User user) {
    return mService.login(user);
  }

  public Observable<User> getUsers() {
    return mService.getUsers();
  }

  public Observable<User> getAuthenticatedUser(User user) {
    return mService.getAuthenticatedUser(user.getServerId(), user.getAccessToken());
  }
}