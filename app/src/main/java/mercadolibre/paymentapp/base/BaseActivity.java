package mercadolibre.paymentapp.base;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import mercadolibre.paymentapp.R;

public abstract class BaseActivity extends AppCompatActivity {
  protected ViewGroup progressView;
  protected ViewGroup rootView;

  @BindView(R.id.toolbar)
  public
  Toolbar toolbar;


  @SuppressLint("ResourceType")
  @Override
  public void setContentView(int layoutResID) {
    super.setContentView(layoutResID);
    ButterKnife.bind(this);
    setSupportActionBar(toolbar);
    rootView =  (ViewGroup) findViewById(android.R.id.content);
    progressView = (ViewGroup) LayoutInflater.from(this).inflate(R.layout.view_progress, rootView,
        false);
    progressView.setOnTouchListener((v, e) -> true);
  }

  public void showProgressBar(){
    rootView.setEnabled(false);
    if (progressView.getParent() == null) {
      rootView.addView(progressView);
    }
    progressView.setVisibility(View.VISIBLE);
  }

  public void hideKeyboard(){
    View view = this.getCurrentFocus();
    if (view != null) {
      InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
      imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
  }

  public void hideProgressBar(){
    progressView.setVisibility(View.GONE);
    rootView.removeView(progressView);
    rootView.setEnabled(true);
  }

  public void showMessage(String text) {
    Snackbar snackbar = Snackbar.make(rootView, text, Snackbar.LENGTH_LONG);
    View snackbarView = snackbar.getView();
    TextView textSnackBar = snackbarView.findViewById(android.support.design.R.id.snackbar_text);
    textSnackBar.setMaxLines(4);
    snackbar.setActionTextColor(ContextCompat.getColor(rootView.getContext(), android.R.color.white));
    snackbar.setAction(R.string.action_close, v -> snackbar.dismiss());
    snackbar.show();
  }
}
