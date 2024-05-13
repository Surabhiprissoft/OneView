package com.sbi.oneview.ui.home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sbi.oneview.R;
import com.sbi.oneview.ui.adapters.CourouselAdapter;
import com.sbi.oneview.ui.adapters.RecentTransactionAdapter;
import com.sbi.oneview.utils.CommonUtils;
import com.sbi.oneview.utils.CustomIndicatorView;

import java.util.ArrayList;


public class TransitCardDashboardFragment extends Fragment {


    TextView tvDashboard,tvCurrentDate,tvRecentTransaction,tvQuickAccess,tvMyCards,tvCardDetails,tvAnalytics;
    TextView tvTopUpRupee,tvSpendRupee,tvSinceLastTopUp,tvSuccessTxns,tvSinceLastLogin,tvLastStatementGenerated;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_transit_card_dashboard, container, false);



    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvDashboard = view.findViewById(R.id.tvHeader);
        tvCurrentDate = view.findViewById(R.id.tvDate);
        tvQuickAccess = view.findViewById(R.id.tvQuickAccess);
        tvMyCards = view.findViewById(R.id.tvMyCards);
        tvCardDetails = view.findViewById(R.id.tvCardDetails);
        tvRecentTransaction = view.findViewById(R.id.tvRecentTransaction);
        tvTopUpRupee = view.findViewById(R.id.tvTopUpRupee);
        tvSpendRupee = view.findViewById(R.id.tvSpendRupee);
        tvSinceLastTopUp  = view.findViewById(R.id.tvSinceLastTopUp);
        tvSuccessTxns = view.findViewById(R.id.tvSuccessTxns);
        tvSinceLastLogin =  view.findViewById(R.id.tvSinceLastLogin);
        tvLastStatementGenerated = view.findViewById(R.id.tvLastStatementGenerated);
        tvAnalytics = view.findViewById(R.id.tvAnalytics);

        tvCurrentDate.setText(CommonUtils.setCurrentDate());

        tvTopUpRupee.setText(getActivity().getString(R.string.Rs)+" 22,500/-");
        tvSpendRupee.setText(getActivity().getString(R.string.Rs)+" 5000/-");

        tvDashboard.setText("Dashboard");
        CommonUtils.setGradientColor(tvSinceLastTopUp);
        CommonUtils.setGradientColor(tvSuccessTxns);
        CommonUtils.setGradientColor(tvSinceLastLogin);
        CommonUtils.setGradientColor(tvLastStatementGenerated);
        CommonUtils.setGradientColor(tvAnalytics);

        CommonUtils.setGradientColor(tvDashboard);
        CommonUtils.setGradientColor(tvQuickAccess);
        CommonUtils.setGradientColor(tvMyCards);
        CommonUtils.setGradientColor(tvCardDetails);
        CommonUtils.setGradientColor(tvRecentTransaction);
        CommonUtils.setGradientColor(tvTopUpRupee);
        CommonUtils.setGradientColor(tvSpendRupee);


        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        CustomIndicatorView customIndicatorView = view.findViewById(R.id.customIndicator);
        ArrayList<Integer> arrayList = new ArrayList<>();
        //motionLayout.transitionToEnd();
        //Add multiple images to arraylist.

       /* arrayList.add("https://images.unsplash.com/photo-1692528131755-d4e366b2adf0?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxlZGl0b3JpYWwtZmVlZHwzNXx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=500&q=60");
        arrayList.add("https://images.unsplash.com/photo-1692862582645-3b6fd47b7513?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxlZGl0b3JpYWwtZmVlZHw0MXx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=500&q=60");
        arrayList.add("https://images.unsplash.com/photo-1692584927805-d4096552a5ba?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxlZGl0b3JpYWwtZmVlZHw0Nnx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=500&q=60");
        arrayList.add("https://images.unsplash.com/photo-1692854236272-cc49076a2629?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxlZGl0b3JpYWwtZmVlZHw1MXx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=500&q=60");
        arrayList.add("https://images.unsplash.com/photo-1681207751526-a091f2c6a538?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxlZGl0b3JpYWwtZmVlZHwyODF8fHxlbnwwfHx8fHw%3D&auto=format&fit=crop&w=500&q=60");
        arrayList.add("https://images.unsplash.com/photo-1692610365998-c628604f5d9f?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxlZGl0b3JpYWwtZmVlZHwyODZ8fHxlbnwwfHx8fHw%3D&auto=format&fit=crop&w=500&q=60");
        arrayList.add("https://images.unsplash.com/photo-1692854236272-cc49076a2629?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxlZGl0b3JpYWwtZmVlZHw1MXx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=500&q=60");
        arrayList.add("https://images.unsplash.com/photo-1681207751526-a091f2c6a538?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxlZGl0b3JpYWwtZmVlZHwyODF8fHxlbnwwfHx8fHw%3D&auto=format&fit=crop&w=500&q=60");
        arrayList.add("https://images.unsplash.com/photo-1692610365998-c628604f5d9f?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxlZGl0b3JpYWwtZmVlZHwyODZ8fHxlbnwwfHx8fHw%3D&auto=format&fit=crop&w=500&q=60");

*/

        arrayList.add(R.drawable.city_mumbai);
        arrayList.add(R.drawable.city_noida);
        arrayList.add(R.drawable.city_nagpur);
        arrayList.add(R.drawable.city_chennai);
        arrayList.add(R.drawable.city_kanpur);

        CourouselAdapter adapter = new CourouselAdapter(getActivity(), arrayList,customIndicatorView);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(adapter);


        RecyclerView rvRecentTransaction = view.findViewById(R.id.rvRecentTransaction);

        // Create an instance of the adapter
        RecentTransactionAdapter adapterRecentTransaction = new RecentTransactionAdapter(getActivity());

        // Set the adapter to the RecyclerView
        rvRecentTransaction.setAdapter(adapterRecentTransaction);

        // Set layout manager to position the items
        rvRecentTransaction.setLayoutManager(new LinearLayoutManager(getActivity()));
    }


}