package javen.example.com.flexiblerecyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;
import java.util.List;

import javen.example.com.flexiblerecyclerview.adapter.CommonAdapter;
import javen.example.com.flexiblerecyclerview.bean.BeeBean;
import javen.example.com.flexiblerecyclerview.bean.CatBean;
import javen.example.com.flexiblerecyclerview.bean.FooterBean;
import javen.example.com.flexiblerecyclerview.bean.HeaderBean;
import javen.example.com.flexiblerecyclerview.bean.iinterface.DisplayItem;
import javen.example.com.flexiblerecyclerview.decoration.MarginDecoration;
import javen.example.com.flexiblerecyclerview.helper.DataHelper;
import javen.example.com.flexiblerecyclerview.helper.LayoutManagerHelper;

public class MainActivity extends AppCompatActivity {
    private List<DisplayItem> animals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(javen.example.com.flexiblerecyclerview.R.layout.activity_main);
        initData();
        initView();
    }

    private void initData() {
        List<Integer> beeDataList = DataHelper.BEE_ICONS;
        List<Integer> catDataList = DataHelper.CAT_ICONS;
        animals = new ArrayList<>();

        for (int i = 0; i < beeDataList.size(); i++) {
            animals.add(new BeeBean(beeDataList.get(i)));
            animals.add(new CatBean(catDataList.get(i), "name" + i));
        }


    }

    private void initView() {
        FlexibleRecyclerView recyclerView = findViewById(javen.example.com.flexiblerecyclerview.R.id.recycler_view);
        RecyclerView.LayoutManager layoutManager = recyclerView.createGridLayoutManager(LayoutManagerHelper.GRID_TYPE, 2);

        if (layoutManager instanceof LinearLayoutManager) {
            ((LinearLayoutManager) layoutManager).setSmoothScrollbarEnabled(true);
        }

        recyclerView.addItemDecoration(new MarginDecoration(MainActivity.this, javen.example.com.flexiblerecyclerview.R.dimen.material_8dp));
        // recyclerView.addItemDecoration(new DividerDecoration(MainActivity.this, R.dimen.material_4dp, R.dimen.material_8dp, DividerDecoration.BOTH_LINE_TYPE));

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        CommonAdapter commonAdapter = new CommonAdapter<>(MainActivity.this, animals);
        commonAdapter.addHeaderAndFooter(false, false, new HeaderBean(javen.example.com.flexiblerecyclerview.R.drawable.dahuangmao_18), new FooterBean(javen.example.com.flexiblerecyclerview.R.drawable.dahuangmao_19));
        recyclerView.setAdapter(commonAdapter);
    }


}
