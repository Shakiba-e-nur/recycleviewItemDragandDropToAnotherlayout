package com.example.test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.DragEvent;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.test.databinding.ActivityMainBinding;
import com.squareup.picasso.Picasso;

import org.sufficientlysecure.htmltextview.HtmlHttpImageGetter;
import org.sufficientlysecure.htmltextview.HtmlTextView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements View.OnDragListener, RecyclerViewAdapter.OnOptionItemClickListener {
    ActivityMainBinding mBinding;
    RecyclerViewAdapter mAdapter;
    ItemTouchHelper touchHelper;
    TextView dropped;
    TextView dropTarget;
    String s;

    ArrayList<Test> stringArrayList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding= DataBindingUtil.setContentView(this,R.layout.activity_main);
        mBinding.textView.setText("hdfsghjd");
        populateRecyclerView();

    }
    private void populateRecyclerView() {
        Test test=new Test(1,"Item 1");
        Test test1=new Test(2,"Item 2");
        Test test2=new Test(3,"Item 3");
        Test test3=new Test(4,"Item 4");
        stringArrayList.add(test);
        stringArrayList.add(test1);
        stringArrayList.add(test2);
        stringArrayList.add(test3);
        mAdapter = new RecyclerViewAdapter(stringArrayList,this::onSelect);
//        ItemTouchHelper.Callback callback =
//                new ItemMoveCallback(mAdapter);
//        touchHelper = new ItemTouchHelper(callback);
//        touchHelper.attachToRecyclerView(mBinding.recyclerView);
        mBinding.textView.setOnDragListener(this);
        mBinding.recyclerView.setAdapter(mAdapter);

    }


    @Override
    public boolean onDrag(View v, DragEvent event) {
        if (event.getAction()==DragEvent.ACTION_DROP){
//            dropped=(TextView) event.getLocalState();
//            dropTarget=(TextView) v;
//            if(dropped.equals(dropTarget))
//            {
               // mBinding.textView.setVisibility(s);
                mBinding.textView.setText(s);
           // }
        }
        return true;
    }

    @Override
    public void onSelect(String text,TextView textView) {
        s=text;
        dropTarget=textView;
        Log.d("text", "onSelect: "+s);
        //mBinding.textView.setText(s);
    }
}