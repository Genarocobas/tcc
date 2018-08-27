package inatel.br.nfccontrol.network;

import javax.inject.Inject;

import inatel.br.nfccontrol.data.model.User;
import inatel.br.nfccontrol.data.model.UserAuthentication;
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

  public Observable<UserAuthentication> login(User user){
    return mRepository.login(user).compose(applySchedulers()).share();
  }

  public Observable<User> getUsers(){
    return mRepository.getUsers().compose(applySchedulers()).share();
  }
}
