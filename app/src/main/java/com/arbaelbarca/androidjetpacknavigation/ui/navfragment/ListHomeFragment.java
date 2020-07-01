package com.arbaelbarca.androidjetpacknavigation.ui.navfragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.arbaelbarca.androidjetpacknavigation.onclick.OnClickItem;
import com.arbaelbarca.androidjetpacknavigation.R;
import com.arbaelbarca.androidjetpacknavigation.adapter.AdapterListNews;
import com.arbaelbarca.depedencymodule.model.modeltopheadlines.ArticlesItem;
import com.arbaelbarca.depedencymodule.model.modeltopheadlines.ResponseNewsTopHeadlines;
import com.arbaelbarca.depedencymodule.network.NetworkApi;

import java.util.Objects;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListHomeFragment extends Fragment {


    private RecyclerView rvLsistNews;
    private AdapterListNews adapterListNews;
    private NavController navController;

    public ListHomeFragment() {
        // Required empty public constructor
    }

    private OnClickItem onClickItem = pos -> {
        ArticlesItem articlesItem = adapterListNews.getArticlesItemArrayList().get(pos);

        DetailListNewsFragment homeFragment = new DetailListNewsFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("data", articlesItem);
        homeFragment.setArguments(bundle);
        navController.navigate(R.id.action_homeFragment_to_detailListFragment, bundle);
    };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list_home, container, false);
        rvLsistNews = view.findViewById(R.id.rvLsistNews);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(Objects.requireNonNull(getActivity()), R.id.my_nav_host_fragment);

        initRv();
        getData();
    }

    private void initRv() {
        adapterListNews = new AdapterListNews(getActivity());
        rvLsistNews.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvLsistNews.setHasFixedSize(true);
        rvLsistNews.setAdapter(adapterListNews);

        adapterListNews.setOnClickItem(onClickItem);
    }

    private void getData() {
        NetworkApi.getInstance()
                .getAPI()
                .getListTopHeadlines("id")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ResponseNewsTopHeadlines>() {

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(ResponseNewsTopHeadlines responseNewsTopHeadlines) {
                        if (responseNewsTopHeadlines.getArticles().size() > 0) {
                            adapterListNews.setArticlesItemArrayList(responseNewsTopHeadlines.getArticles());
                            adapterListNews.notifyDataSetChanged();
                        }

                    }
                });
    }
}
