// Generated by view binder compiler. Do not edit!
package sauceda.carlos.packsnpaws.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;
import sauceda.carlos.packsnpaws.R;

public final class ActivityCuidadorDescriptionBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final ImageView configCuidador;

  @NonNull
  public final Button sendmsj;

  @NonNull
  public final Button solicitarcuidado;

  private ActivityCuidadorDescriptionBinding(@NonNull LinearLayout rootView,
      @NonNull ImageView configCuidador, @NonNull Button sendmsj,
      @NonNull Button solicitarcuidado) {
    this.rootView = rootView;
    this.configCuidador = configCuidador;
    this.sendmsj = sendmsj;
    this.solicitarcuidado = solicitarcuidado;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityCuidadorDescriptionBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityCuidadorDescriptionBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_cuidador_description, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityCuidadorDescriptionBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.config_cuidador;
      ImageView configCuidador = ViewBindings.findChildViewById(rootView, id);
      if (configCuidador == null) {
        break missingId;
      }

      id = R.id.sendmsj;
      Button sendmsj = ViewBindings.findChildViewById(rootView, id);
      if (sendmsj == null) {
        break missingId;
      }

      id = R.id.solicitarcuidado;
      Button solicitarcuidado = ViewBindings.findChildViewById(rootView, id);
      if (solicitarcuidado == null) {
        break missingId;
      }

      return new ActivityCuidadorDescriptionBinding((LinearLayout) rootView, configCuidador,
          sendmsj, solicitarcuidado);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
