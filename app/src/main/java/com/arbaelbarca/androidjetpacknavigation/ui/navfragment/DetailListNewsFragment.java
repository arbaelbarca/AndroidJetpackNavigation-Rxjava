package com.arbaelbarca.androidjetpacknavigation.ui.navfragment;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.arbaelbarca.androidjetpacknavigation.R;
import com.arbaelbarca.depedencymodule.model.modeltopheadlines.ArticlesItem;
import com.bumptech.glide.Glide;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailListNewsFragment extends Fragment {

    private ImageView imgDetail;
    private TextView txtTitle, txtDesc;
    private ArticlesItem articlesItem;
    private NavController navController;

    public DetailListNewsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            articlesItem = bundle.getParcelable("data");
        }

        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                // Handle the back button event
//                navController.navigate(R.id.action_detailListFragment_to_homeFragment);
                navController.navigateUp();
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail_list_news, container, false);
        imgDetail = view.findViewById(R.id.imgNews);
        txtTitle = view.findViewById(R.id.txtTitleNews);
        txtDesc = view.findViewById(R.id.txtDesc);
        navController = Navigation.findNavController(getActivity(), R.id.my_nav_host_fragment);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getData();
    }

    private void getData() {
        if (articlesItem != null) {
            txtTitle.setText(articlesItem.getTitle());
            txtDesc.setText(articlesItem.getDescription());

            Glide.with(getActivity())
                    .load(articlesItem.getUrlToImage())
                    .into(imgDetail);
        }


    }

}
