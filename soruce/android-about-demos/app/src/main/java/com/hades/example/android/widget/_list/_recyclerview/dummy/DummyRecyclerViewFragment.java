package com.hades.example.android.widget._list._recyclerview.dummy;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hades.example.android.R;
import com.hades.example.android.lib.mock.DummyContent;
import com.hades.example.android.lib.mock.DummyItem;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class DummyRecyclerViewFragment extends Fragment {

    private static final String ARG_COLUMN_COUNT = "column_count";
    private int mColumnCount = 1;

    private OnListFragmentInteractionListener mListener;
    private RecyclerView mList;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public DummyRecyclerViewFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static DummyRecyclerViewFragment newInstance(int columnCount) {
        DummyRecyclerViewFragment fragment = new DummyRecyclerViewFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.widget_recyclerview_dummy, container, false);
        mList = view.findViewById(R.id.list);
        initList(view);
        return view;
    }

    private void initList(View view) {
        Context context = view.getContext();
        // PO:RecyclerView.setHasFixedSize(true)
        mList.setHasFixedSize(true);
        if (mColumnCount <= 1) {
            mList.setLayoutManager(new LinearLayoutManager(context));
        } else {
            mList.setLayoutManager(new GridLayoutManager(context, mColumnCount));
        }
        mList.setAdapter(new DummyRecyclerViewAdapter(DummyContent.ITEMS(), mListener));
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(DummyItem item);
    }
}
