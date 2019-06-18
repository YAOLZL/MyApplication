package com.example.myapplication.Fragment;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.myapplication.Mengbean;
import com.example.myapplication.R;
import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MengFragment extends Fragment {
    private EditText ed_name,ed_number;
    private Button btn_search;
    private OkHttpClient okHttpClient;
    private RecyclerView meng_rv;
    private Context context;
    private List<Mengbean.ResultBean> list;

    public MengFragment(Context context) {
        this.context = context;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_meng, container, false);
        ed_name=view.findViewById(R.id.ed_name);
        ed_number=view.findViewById(R.id.ed_number);
        btn_search=view.findViewById(R.id.btn_search);
        meng_rv = view.findViewById(R.id.meng_rv);
        okHttpClient=new OkHttpClient();
        getData();
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                meng_rv.setLayoutManager(new LinearLayoutManager(context));
                meng_rv.setAdapter(new MyAdapter());
            }
        });
        return view;
    }

    private void getData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //http://v.juhe.cn/dream/query?q=龙&cid=&full=1&key=a054a33bbbf059555f243c7f4a3d80f2
                String name = ed_name.getText().toString();
                Request request=new Request.Builder().url("http://v.juhe.cn/dream/query?q="+name+"&cid=&full=1&key=a054a33bbbf059555f243c7f4a3d80f2").build();
                try {

                    Response response=okHttpClient.newCall(request).execute();
                    final String string = response.body().string();
                    Log.i("sssssssss",string);
                    Mengbean mengbean = new Gson().fromJson(string, Mengbean.class);
                    list=mengbean.getResult();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(getContext()).inflate(R.layout.item_meng,null,false));
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            Mengbean.ResultBean resultBean = list.get(position);
            holder.name.setText(resultBean.getTitle());
            holder.content.setText(resultBean.getDes());
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            private TextView name;
            private TextView content;
            private TextView contentss;
            public ViewHolder(View view) {
                super(view);
                this.name = view.findViewById(R.id.name);
                this.content = view.findViewById(R.id.content);
                this.contentss = view.findViewById(R.id.contentss);
            }
        }
    }
}
