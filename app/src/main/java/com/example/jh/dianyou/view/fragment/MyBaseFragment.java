/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 *
 * @author Fernando Cejas (the android10 coder)
 */
package com.example.jh.dianyou.view.fragment;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.example.jh.base.YaMvpDiFragment;
import com.example.jh.base.YaPresenter;
import com.example.jh.base.YaView;
import com.example.jh.dianyou.AndroidApplication;
import com.example.jh.dianyou.di.components.ApplicationComponent;


/**
 * Base {@link Fragment} class for every fragment in this application.
 */
public abstract class MyBaseFragment<V extends YaView, P extends YaPresenter<V>, C> extends YaMvpDiFragment<V, P, C> {
  protected static final String ARG_PARAM1 = "param1";
  protected static final String ARG_PARAM2 = "param2";


  protected String mParam1;
  protected String mParam2;
  protected OnFragmentInteractionListener mListener;
  ProgressDialog dialog;
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (getArguments() != null) {
      mParam1 = getArguments().getString(ARG_PARAM1);
      mParam2 = getArguments().getString(ARG_PARAM2);
    }
  }



  public interface OnFragmentInteractionListener {
    // TODO: Update argument type and name
    void onFragmentInteraction(int id);
    void onShowRed(int count);
  }

  public void showRed(int count) {
    if (mListener != null) {
      mListener.onShowRed(count);
    }
  }

  @Override
  public void onAttach(Context context) {
    super.onAttach(context);
    if (context instanceof OnFragmentInteractionListener) {
      mListener = (OnFragmentInteractionListener) context;
    } else {
      throw new RuntimeException(context.toString()
              + " must implement OnFragmentInteractionListener");
    }
  }

  @Override
  public void onDetach() {
    super.onDetach();
    mListener = null;
  }

  protected ApplicationComponent getApplicationComponent() {
    return ((AndroidApplication)getActivity().getApplication()).getApplicationComponent();
  }
}
