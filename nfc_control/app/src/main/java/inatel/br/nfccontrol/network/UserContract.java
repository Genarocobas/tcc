package inatel.br.nfccontrol.network;

import javax.inject.Inject;

import inatel.br.nfccontrol.data.model.User;
import io.reactivex.Observable;

/**
 * Contract to define the interface of user methods.
 */
public class UserContract extends BaseContract {

  @Inject
  UserRepository mRepository;

  @Inject
  public UserContract() {
  }

  public Observable<User> login(User user){
    return mRepository.login(user);
  }
}
