// Generated by view binder compiler. Do not edit!
package sauceda.carlos.packsnpaws.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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

public final class Activity3homeBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final ImageButton chatsBtn;

  @NonNull
  public final ImageButton exitbtn;

  @NonNull
  public final ImageButton homeBtn;

  @NonNull
  public final HorizontalScrollView horizontalScrollView;

  @NonNull
  public final HorizontalScrollView horizontalScrollView2;

  @NonNull
  public final LinearLayout linearLayout;

  @NonNull
  public final LinearLayout linearLayout2;

  @NonNull
  public final ImageButton mascotaImagenDescipcion;

  @NonNull
  public final ImageButton petsBtn;

  @NonNull
  public final ImageButton profileBtn;

  @NonNull
  public final RelativeLayout relativeLayout;

  @NonNull
  public final TextView textView;

  private Activity3homeBinding(@NonNull ConstraintLayout rootView, @NonNull ImageButton chatsBtn,
      @NonNull ImageButton exitbtn, @NonNull ImageButton homeBtn,
      @NonNull HorizontalScrollView horizontalScrollView,
      @NonNull HorizontalScrollView horizontalScrollView2, @NonNull LinearLayout linearLayout,
      @NonNull LinearLayout linearLayout2, @NonNull ImageButton mascotaImagenDescipcion,
      @NonNull ImageButton petsBtn, @NonNull ImageButton profileBtn,
      @NonNull RelativeLayout relativeLayout, @NonNull TextView textView) {
    this.rootView = rootView;
    this.chatsBtn = chatsBtn;
    this.exitbtn = exitbtn;
    this.homeBtn = homeBtn;
    this.horizontalScrollView = horizontalScrollView;
    this.horizontalScrollView2 = horizontalScrollView2;
    this.linearLayout = linearLayout;
    this.linearLayout2 = linearLayout2;
    this.mascotaImagenDescipcion = mascotaImagenDescipcion;
    this.petsBtn = petsBtn;
    this.profileBtn = profileBtn;
    this.relativeLayout = relativeLayout;
    this.textView = textView;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static Activity3homeBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static Activity3homeBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_3home, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static Activity3homeBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.chats_btn;
      ImageButton chatsBtn = ViewBindings.findChildViewById(rootView, id);
      if (chatsBtn == null) {
        break missingId;
      }

      id = R.id.exitbtn;
      ImageButton exitbtn = ViewBindings.findChildViewById(rootView, id);
      if (exitbtn == null) {
        break missingId;
      }

      id = R.id.home_btn;
      ImageButton homeBtn = ViewBindings.findChildViewById(rootView, id);
      if (homeBtn == null) {
        break missingId;
      }

      id = R.id.horizontalScrollView;
      HorizontalScrollView horizontalScrollView = ViewBindings.findChildViewById(rootView, id);
      if (horizontalScrollView == null) {
        break missingId;
      }

      id = R.id.horizontalScrollView2;
      HorizontalScrollView horizontalScrollView2 = ViewBindings.findChildViewById(rootView, id);
      if (horizontalScrollView2 == null) {
        break missingId;
      }

      id = R.id.linearLayout;
      LinearLayout linearLayout = ViewBindings.findChildViewById(rootView, id);
      if (linearLayout == null) {
        break missingId;
      }

      id = R.id.linearLayout2;
      LinearLayout linearLayout2 = ViewBindings.findChildViewById(rootView, id);
      if (linearLayout2 == null) {
        break missingId;
      }

      id = R.id.mascotaImagenDescipcion;
      ImageButton mascotaImagenDescipcion = ViewBindings.findChildViewById(rootView, id);
      if (mascotaImagenDescipcion == null) {
        break missingId;
      }

      id = R.id.pets_btn;
      ImageButton petsBtn = ViewBindings.findChildViewById(rootView, id);
      if (petsBtn == null) {
        break missingId;
      }

      id = R.id.profile_btn;
      ImageButton profileBtn = ViewBindings.findChildViewById(rootView, id);
      if (profileBtn == null) {
        break missingId;
      }

      id = R.id.relativeLayout;
      RelativeLayout relativeLayout = ViewBindings.findChildViewById(rootView, id);
      if (relativeLayout == null) {
        break missingId;
      }

      id = R.id.textView;
      TextView textView = ViewBindings.findChildViewById(rootView, id);
      if (textView == null) {
        break missingId;
      }

      return new Activity3homeBinding((ConstraintLayout) rootView, chatsBtn, exitbtn, homeBtn,
          horizontalScrollView, horizontalScrollView2, linearLayout, linearLayout2,
          mascotaImagenDescipcion, petsBtn, profileBtn, relativeLayout, textView);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}