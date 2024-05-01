package com.sbi.oneview.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;




public class BaseFragment extends Fragment implements SessionManager.SessionExpiredListener {
    private ProgressDialog mProgressDialog;
    Context context;


    public void showToast(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // SessionManager.getInstance().setSessionExpiredListener(this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       /* SessionManager.getInstance().onUserInteraction();

        if (SessionManager.getInstance().isSessionExpired())
        {
          CommonUtils.showDialog(getActivity());
        }*/
    }

    public void showLoading() {
        hideLoading();
       // mProgressDialog = CommonUtils.showLoadingDialog(getActivity());
    }

    public void hideLoading() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }


    @Override
    public void onSessionExpired() {
       // CommonUtils.showSessionTimeoutDialog(getActivity());

    }
}