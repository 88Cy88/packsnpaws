// Generated by view binder compiler. Do not edit!
package sauceda.carlos.packsnpaws.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;
import sauceda.carlos.packsnpaws.R;

public final class ActivityDescriptionPetBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final ImageButton configPet;

  @NonNull
  public final Button sendmsj;

  private ActivityDescriptionPetBinding(@NonNull LinearLayout rootView,
      @NonNull ImageButton configPet, @NonNull Button sendmsj) {
    this.rootView = rootView;
    this.configPet = configPet;
    this.sendmsj = sendmsj;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityDescriptionPetBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityDescriptionPetBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_description_pet, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityDescriptionPetBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.config_pet;
      ImageButton configPet = ViewBindings.findChildViewById(rootView, id);
      if (configPet == null) {
        break missingId;
      }

      id = R.id.sendmsj;
      Button sendmsj = ViewBindings.findChildViewById(rootView, id);
      if (sendmsj == null) {
        break missingId;
      }

      return new ActivityDescriptionPetBinding((LinearLayout) rootView, configPet, sendmsj);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}