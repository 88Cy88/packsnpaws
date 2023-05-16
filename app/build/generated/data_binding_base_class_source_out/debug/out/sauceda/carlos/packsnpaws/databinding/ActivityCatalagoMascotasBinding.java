// Generated by view binder compiler. Do not edit!
package sauceda.carlos.packsnpaws.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;
import sauceda.carlos.packsnpaws.R;

public final class ActivityCatalagoMascotasBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final ImageButton backBtn;

  @NonNull
  public final ImageView configCuidador;

  @NonNull
  public final TextView descripcionCortaPet;

  @NonNull
  public final LinearLayout linearLayout;

  @NonNull
  public final LinearLayout linearLayout7;

  @NonNull
  public final TextView nombrePet;

  @NonNull
  public final RatingBar ratingMascota;

  @NonNull
  public final HorizontalScrollView scrollMascotas;

  @NonNull
  public final TextView textView4;

  private ActivityCatalagoMascotasBinding(@NonNull ConstraintLayout rootView,
      @NonNull ImageButton backBtn, @NonNull ImageView configCuidador,
      @NonNull TextView descripcionCortaPet, @NonNull LinearLayout linearLayout,
      @NonNull LinearLayout linearLayout7, @NonNull TextView nombrePet,
      @NonNull RatingBar ratingMascota, @NonNull HorizontalScrollView scrollMascotas,
      @NonNull TextView textView4) {
    this.rootView = rootView;
    this.backBtn = backBtn;
    this.configCuidador = configCuidador;
    this.descripcionCortaPet = descripcionCortaPet;
    this.linearLayout = linearLayout;
    this.linearLayout7 = linearLayout7;
    this.nombrePet = nombrePet;
    this.ratingMascota = ratingMascota;
    this.scrollMascotas = scrollMascotas;
    this.textView4 = textView4;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityCatalagoMascotasBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityCatalagoMascotasBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_catalago_mascotas, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityCatalagoMascotasBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.back_btn;
      ImageButton backBtn = ViewBindings.findChildViewById(rootView, id);
      if (backBtn == null) {
        break missingId;
      }

      id = R.id.config_cuidador;
      ImageView configCuidador = ViewBindings.findChildViewById(rootView, id);
      if (configCuidador == null) {
        break missingId;
      }

      id = R.id.descripcionCortaPet;
      TextView descripcionCortaPet = ViewBindings.findChildViewById(rootView, id);
      if (descripcionCortaPet == null) {
        break missingId;
      }

      id = R.id.linearLayout;
      LinearLayout linearLayout = ViewBindings.findChildViewById(rootView, id);
      if (linearLayout == null) {
        break missingId;
      }

      id = R.id.linearLayout7;
      LinearLayout linearLayout7 = ViewBindings.findChildViewById(rootView, id);
      if (linearLayout7 == null) {
        break missingId;
      }

      id = R.id.nombrePet;
      TextView nombrePet = ViewBindings.findChildViewById(rootView, id);
      if (nombrePet == null) {
        break missingId;
      }

      id = R.id.ratingMascota;
      RatingBar ratingMascota = ViewBindings.findChildViewById(rootView, id);
      if (ratingMascota == null) {
        break missingId;
      }

      id = R.id.scrollMascotas;
      HorizontalScrollView scrollMascotas = ViewBindings.findChildViewById(rootView, id);
      if (scrollMascotas == null) {
        break missingId;
      }

      id = R.id.textView4;
      TextView textView4 = ViewBindings.findChildViewById(rootView, id);
      if (textView4 == null) {
        break missingId;
      }

      return new ActivityCatalagoMascotasBinding((ConstraintLayout) rootView, backBtn,
          configCuidador, descripcionCortaPet, linearLayout, linearLayout7, nombrePet,
          ratingMascota, scrollMascotas, textView4);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
