package com.example.tony.tonydemo.View;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.tony.tonydemo.BaseFragment;
import com.example.tony.tonydemo.DB.News;
import com.example.tony.tonydemo.DB.NewsOpe;
import com.example.tony.tonydemo.Presenter.IPresenter;
import com.example.tony.tonydemo.R;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class AboutFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    @Bind(R.id.btnAddNews)
    Button btnAdd;

    @Bind(R.id.txtTitle)
    EditText txtTitle;

    @Bind(R.id.writer)
    EditText txtWriter;



    public AboutFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AboutFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AboutFragment newInstance(String param1, String param2) {
        AboutFragment fragment = new AboutFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_about, container, false);
        ButterKnife.bind(this,view);
            // Inflate the layout for this fragment
        return view;
    }

    @OnClick(R.id.btnAddNews)
    public void AddNews()
    {
        NewsOpe newtmp = new NewsOpe(getActivity());
        newtmp.insert(txtTitle.getText().toString(),txtWriter.getText().toString(), new java.util.Date().getTime());
        //newtmp.insert("今天天气真好","任伟", new java.util.Date().getTime());

        ArrayList<News> newslist = new ArrayList<>();
        newslist = newtmp.getAllNews();

        for (int i = 0; i < newslist.size(); i++) {
            Log.e("result",newslist.get(i).getWriter());
        }
    }

    @OnClick(R.id.btnDelete)
    public void DeleteNews()
    {
        NewsOpe newtmp = new NewsOpe(getActivity());
        boolean res = newtmp.deleteByTitle("你好");


        ArrayList<News> newslist = new ArrayList<>();
        newslist = newtmp.getAllNews();

        for (int i = 0; i < newslist.size(); i++) {
            Log.e("result",newslist.get(i).getWriter()+"/"+newslist.get(i).getTitle());
        }
    }
    @OnClick(R.id.btnQuery)
    public void QueryNews()
    {
        NewsOpe newtmp = new NewsOpe(getActivity());
        News tmp = newtmp.getNewsByTitle("你好");
        Log.e("result",tmp.getWriter()+"/"+tmp.getTitle());
    }

}
