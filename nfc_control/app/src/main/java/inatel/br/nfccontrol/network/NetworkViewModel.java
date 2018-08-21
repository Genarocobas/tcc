package inatel.br.nfccontrol.network;

import android.databinding.BaseObservable;
import android.databinding.ObservableField;
import android.util.Log;
import android.view.View;

import io.reactivex.Observable;

/**
 * Base generic ViewModel to handle networking operations such as show and hide loading/errors.
 * This class should be incrementally grown as needed.
 */
public abstract class NetworkViewModel<T> extends BaseObservable {

  private static final String TAG = "NetworkViewModel";
  /**
   * {@link ObservableField} to represent if the loading screen should be show.
   * <h1>This attribute must only be used to Data Binding.</h1>
   */
  public final ObservableField<Integer> loadingVisibility;

  public NetworkViewModel() {
    loadingVisibility = new ObservableField<>(View.GONE);
  }

  /**
   * Shows the loading screen and subscribe to the {@link Observable} which will receive the
   * response (success or failure).
   */
  protected void makeRequest() {
    showLoadingScreen();
    getRequestObservable().subscribe(this::setResult, this::handleError);
  }

  /**
   * Shows the loading screen and subscribe to the observable param which will receive the
   * response (success or failure).
   */
  protected void makeRequest(Observable<T> observable) {
    showLoadingScreen();
    observable.subscribe(this::setResult, this::handleError);
  }

  private void setResult(T result) {
    Log.d(TAG, "setResult");
    hideLoadingScreen();
    onResult(result);
  }

  private void handleError(Throwable throwable) {
    Log.d(TAG, "handleError" + throwable.getMessage());
    hideLoadingScreen();
    onError(throwable);
  }

  /**
   * Show the loading screen.
   */
  private void showLoadingScreen() {
    loadingVisibility.set(View.VISIBLE);
  }

  /**
   * Hide the loading screen.
   */
  private void hideLoadingScreen() {
    loadingVisibility.set(View.GONE);
  }

  /**
   * Get the {@link Observable} related to the ViewModel action flow.
   *
   * @return observable to be executed and subscribed.
   */
  public abstract Observable<T> getRequestObservable();

  /**
   * Method called when the {@link Observable} execution was successful.
   *
   * @param result result from observable.
   */
  public abstract void onResult(T result);

  /**
   * Method called when the {@Link Observable} execution was unsuccessful.
   *
   * @param throwable result from observable.
   */
  public abstract void onError(Throwable throwable);

}