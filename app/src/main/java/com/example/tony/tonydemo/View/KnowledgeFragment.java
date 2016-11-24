package com.example.tony.tonydemo.View;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;

import com.example.tony.tonydemo.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class KnowledgeFragment extends Fragment {

//    @Bind(R.id.progressbar)
//    ProgressBar progressbar;

//    @Bind(R.id.spin_kit)
//    SpinKitView spin;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @Bind(R.id.selfview)
    Button selfview;
    @Bind(R.id.GridLayout1)
    GridLayout GridLayout1;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public KnowledgeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment KnowledgeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static KnowledgeFragment newInstance(String param1, String param2) {
        KnowledgeFragment fragment = new KnowledgeFragment();
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
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_third, container, false);
        ButterKnife.bind(this, v);


//        Wave doubleBounce = new Wave();
//        doubleBounce.setBounds(0, 0, 100, 100);
//        doubleBounce.setColor(getResources().getColor(R.color.red));
//        progressbar.setIndeterminateDrawable(doubleBounce);


        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {

    }


    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.selfview,R.id.btnMove})
    public void OnClick(View v)
    {
        switch (v.getId())
        {
            case R.id.selfview:
                startActivity(new Intent(getActivity(),AnimFrameActivity.class));
                break;
            case R.id.btnMove:
                startActivity(new Intent(getActivity(),MoveActivity.class));
                break;


        }


    }


}
