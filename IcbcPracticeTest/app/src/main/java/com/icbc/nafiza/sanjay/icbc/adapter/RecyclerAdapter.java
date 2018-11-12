package com.icbc.nafiza.sanjay.icbc.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.icbc.nafiza.sanjay.icbc.R;
import com.icbc.nafiza.sanjay.icbc.activities.DetailActivity;
import com.icbc.nafiza.sanjay.icbc.bean.Item;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.QuestionViewHolder> {
List<Item> questionList;
Context ctx;
RecyclerView recyclerView;


public RecyclerAdapter(List<Item> questionList, Context ctx, RecyclerView recyclerView){
    this.questionList = questionList;
    this.ctx = ctx;
    this.recyclerView = recyclerView;
}

    @NonNull
    @Override
    public QuestionViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_layout,viewGroup,false);

view.setOnClickListener(onClickListener);
        return new QuestionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionViewHolder questionViewHolder, int position) {
    questionViewHolder.txtViewItem.setText(questionList.get(position).getQuestion());
        questionViewHolder.txtViewItemId.setText(ctx.getResources().getString(R.string.txtQuestionNo) + " "
                + Integer.toString(questionList.get(position).getId()));
    }

    @Override
    public int getItemCount() {
        return questionList.size();
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int itemPosition = recyclerView.getChildLayoutPosition(v);
            Intent intent = new Intent(ctx, DetailActivity.class);

            /*intent.putExtra("question", questionList.get(itemPosition).getQuestion());
            intent.putExtra("answer", questionList.get(itemPosition).getAnswer());
            intent.putExtra("dist1", questionList.get(itemPosition).getDistractor1());
            intent.putExtra("dist2", questionList.get(itemPosition).getDistractor2());
            intent.putExtra("dist3", questionList.get(itemPosition).getDistractor3());
*/
            intent.putExtra("questionId", questionList.get(itemPosition).getId());

            ctx.startActivity(intent);
            //System.out.println("<<<<<<<<<<<ItemPos>>>>>>>>" + itemPosition);
        }
    };

    public static class QuestionViewHolder extends RecyclerView.ViewHolder{
        TextView txtViewItem,txtViewItemId;
        public QuestionViewHolder(View view){
            super(view);
            txtViewItem = (TextView)view.findViewById(R.id.txtViewItem);
            txtViewItemId = (TextView)view.findViewById(R.id.txtViewItemId);
        }




    }



}
